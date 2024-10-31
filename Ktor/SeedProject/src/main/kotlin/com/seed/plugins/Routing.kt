package com.seed.plugins

import com.seed.model.app.*
import com.seed.model.store.Store
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import com.seed.model.store.StoreRepository
import com.seed.model.store.StoreRepository.findStoreByLocation
import com.seed.model.store.inside.Reservation
import com.seed.model.user.User
import com.seed.model.user.UserRepository

@Serializable
data class CreateTableRequest(val tableName: String, val columns: List<String>)

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        /*
        route("/create-table") {
            post {
                val createTableRequest = call.receive<CreateTableRequest>()

                val tableName = createTableRequest.tableName
                val columns = createTableRequest.columns
                if (columns.size !in 1..9) {
                    call.respond(HttpStatusCode.BadRequest, "열의 개수가 1개부터 9개여야 합니다.")
                    return@post
                }

                val table = createTable(tableName, columns)
                transaction {
                    SchemaUtils.create(table)
                }
                call.respond(HttpStatusCode.OK, "테이블 \"$tableName\" 이 $columns 개 열로 생성되었습니다.")
            }
        }
        */

        route("/account") {
            post("/login") {
                // 로그인 정보 받기
                val request = call.receive<LoginRequest>()
                val user = UserRepository.findUser(request.id, request.pw)

                // 로그인 정보 일치 여부 확인
                // 테스트를 통과하지 못하면 각각에 맞는 오류 메시지 출력
                if (user == null) {
                    call.respond(HttpStatusCode.BadRequest, "ID, PW가 틀렸습니다.")
                    return@post
                }

                val response = LoginResponse("반갑습니다.", user)

                // 로그인 정보 일치 여부 확인
                call.respond(HttpStatusCode.OK, response)
                return@post
            }

            post ("/register") {
                // 회원가입 정보 받기
                val newUser = call.receive<User>()

                // 회원가입 오류 확인
                if (UserRepository.isExistId(newUser.id!!)) {
                    call.respond(HttpStatusCode.BadRequest, "이미 존재하는 ID입니다.")
                    return@post
                }

                // 회원가입 오류 확인 성공
                UserRepository.addUser(newUser)
                val loginResponse = LoginResponse("반갑습니다!", newUser)
                call.respond(HttpStatusCode.Created, loginResponse)
                return@post
            }
        }

        // 1,2번째랑, 3번째의 반환값이 다르다
        // 1,2번째는 반환값은 아이디, 이름, 위도, 경도, 주소, 카테고리
        // 3번째는 음식 메뉴, 사진, 테이블, 리뷰

        route("/") {
            get {
                // 스토어 제공
                val latitudeStart = call.request.queryParameters["latitudeStart"]?.toDoubleOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid latitudeStart")
                val latitudeEnd = call.request.queryParameters["latitudeEnd"]?.toDoubleOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid latitudeEnd")
                val longitudeStart = call.request.queryParameters["longitudeStart"]?.toDoubleOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid longitudeStart")
                val longitudeEnd = call.request.queryParameters["longitudeEnd"]?.toDoubleOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid longitudeEnd")

                val storesByLocation = findStoreByLocation(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd)

                call.respond(storesByLocation)
            }

            post {
                val store = call.receive<Store>()
                call.respondRedirect("/store/${store.id}")
            }
        }

        route ("{name}") {
            get {
                val name: String =
                    call.parameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid Input")
                val store = StoreRepository.findStoreByName(name) ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid name")
                call.respond(store)
            }
        }

        route ("store/{id}") {
            get {
                val id : Long = call.parameters["id"]?.toLongOrNull() ?: throw IllegalArgumentException("Invalid or missing ID")
                val store = StoreRepository.findStoreById(id) ?: throw IllegalArgumentException("Invalid or missing ID")
                call.respond(store)
            }

            post {
                val id : Long = call.parameters["id"]?.toLongOrNull() ?: throw IllegalArgumentException("Invalid or missing ID")
                val store = StoreRepository.findStoreById(id)

                if (store == null) {
                    call.respond(HttpStatusCode.NotFound, "Invalid or missing ID")
                    return@post
                }

                // 예약을 받기
                val reservation = call.receive<Reservation>()

                if (store.checkReservation(reservation)) {
                    call.respondText("예약에 성공했습니다.")
                }

                else {
                    call.respondText("예약에 실패했습니다.")
                }
            }
        }

        /*
        1차 프로토타이핑 이후에 업데이트 하기

        route("/alarm") {
            get {
                // User Class의 reservation History 만들기
            }

            post {

            }
        }

        get("/user/{id}") {

        }

        get("/favorite") {


        }
         */
    }
}

fun createTable(tableName: String, columns: List<String>): Table {
    return object : Table(tableName) {
        //테이블 객체의 프로퍼티 오버라이드
        override val columns: List<Column<*>> = columns.map { varchar(it, 255) }
    }
}