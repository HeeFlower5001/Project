package com.seed

import com.seed.model.app.LoginRequest
import com.seed.model.store.Store
import com.seed.model.store.StoreRepository
import com.seed.model.store.inside.Category
import com.seed.model.store.inside.Reservation
import com.seed.model.store.inside.Table
import com.seed.model.user.User
import com.seed.model.user.UserRepository
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.testing.*
import kotlinx.datetime.*
import kotlin.test.*

// rd /s /q "C:\Users\heeflower5001\Documents\GitHub\Hee\Framework\Ktor\seedProject\build"

class RoutingTestAccountLogin {
    @Test
    fun testLoginWithValidCredentials() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        UserRepository.addUser("validId", "validPw", "validName", "validNickName", "validCallNumber")

        val response = client.post("/account/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest("validId", "validPw"))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val expectedResponse = """{"message":"반갑습니다.","user":{"id":"validId","pw":"validPw","name":"validName","nickname":"validNickName","callNumber":"validCallNumber","countNoShow":0}}""".trimIndent()

        // 응답 본문 비교
        assertEquals(expectedResponse, response.bodyAsText())
    }

    @Test
    fun testLoginWithInvalidCredentials() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/account/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest("invalidId", "invalidPassword"))
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("ID, PW가 틀렸습니다.", response.bodyAsText())
    }
}

class RoutingTestAccountRegister {
    @Test
    fun testRegisterWithValidCredentials() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/account/register") {
            contentType(ContentType.Application.Json)
            setBody(User("newId", "newPassword", "newName", "newNickName", "newCallNumber"))
        }

        assertEquals(HttpStatusCode.Created, response.status)
        assertEquals("""{"message":"반갑습니다!","user":{"id":"newId","pw":"newPassword","name":"newName","nickname":"newNickName","callNumber":"newCallNumber","countNoShow":0}}""".trimIndent(),
            response.bodyAsText())
    }

    @Test
    fun testRegisterWithExistingUser() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        UserRepository.addUser("existingId", "pw", "name", "nickname", "callNumber")

        val response = client.post("/account/register") {
            contentType(ContentType.Application.Json)
            setBody(User("existingId", "password", "newName", "newNickName", "newCallNumber"))
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("이미 존재하는 ID입니다.", response.bodyAsText())
    }
}

class RoutingTestHome {
    @Test
    fun testGetStore() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val store = Store(id = null, "name", 50.0, 50.0, "address", Category.SINGING_ROOM)
        StoreRepository.addStore(store)

        val response = client.get("/") {
            url {
                parameters.append("latitudeStart", "0.0")
                parameters.append("latitudeEnd", "100.0")
                parameters.append("longitudeStart", "0.0")
                parameters.append("longitudeEnd", "100.0")
            }
        }

        // 상태 코드 확인
        assertEquals(HttpStatusCode.OK, response.status)
        // 응답 데이터 확인
        val stores = response.body<List<Store>>()
        assertTrue(stores.isNotEmpty())
        assertEquals("name", stores[0].name)
    }

    @Test
    fun testGetStoreWithInvalidLocation() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.get{
            url {
                parameters.append("latitudeStart", "null")
                parameters.append("latitudeEnd", "100.0")
                parameters.append("longitudeStart", "0.0")
                parameters.append("longitudeEnd", "100.0")
            }
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("Invalid latitudeStart", response.bodyAsText())
    }


    @Test
    fun testPostStore() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val store = Store(0, "name", 0.0, 0.0, "address", Category.RESTAURANT)

        val response = client.post("/") {
            contentType(ContentType.Application.Json)
            setBody(store)
        }

        assertEquals(HttpStatusCode.Found, response.status)
        assertEquals("/store/${store.id}", response.headers["Location"])
    }
}

class RoutingTestStoreId {
    @Test
    fun testGetStoreId() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val store = Store(0, "name", 50.0, 50.0, "address", Category.CAFE)
        store.tables?.add(0, Table(4))

        store.tables?.let {it[0].time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}

        StoreRepository.addStore(store)

        val response = client.get("/store/${store.id}") {
            contentType(ContentType.Application.Json)
            setBody(store)
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("""{"id":0,"name":"name","latitude":50.0,"longitude":50.0,"address":"address","category":"CAFE","review":[],"tables":[{"seat":4,"time":"${store.tables?.let{it[0].time}}","reservationAvailable":true}],"waitingList":{"list":[]},"menus":[]}""", response.bodyAsText())
    }

    @Test
    fun testPostStoreId() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val user = User("id", "pw", "name", "nickname", "callNumber")
        val store = Store(0, "name", 50.0, 50.0, "address", Category.CAFE)
        val reservation = Reservation(user, 3, Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()), 0)

        StoreRepository.addStore(store)

        val response = client.post("/store/${store.id}") {
            contentType(ContentType.Application.Json)
            setBody(reservation)
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("예약에 성공했습니다.", response.bodyAsText())
    }

    @Test
    fun testPostStoreIdWithInvalidStore() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val invalidStoreId = 999L
        val user = User("id", "pw", "name", "nickname", "callNumber")
        val reservation = Reservation(user, 3, Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()), 0)

        val response = client.post("/store/${invalidStoreId}") {
            contentType(ContentType.Application.Json)
            setBody(reservation)
        }

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals("Invalid or missing ID", response.bodyAsText())
    }
}