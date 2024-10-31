package com.seed.model.app

import com.seed.model.user.*
import com.seed.model.store.Store
import com.seed.model.store.StoreRepository
import com.seed.model.store.inside.Category
import com.seed.model.store.inside.Menu
import com.seed.model.store.inside.Reservation
import com.seed.model.store.inside.Table
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

import kotlinx.serialization.Serializable

@Serializable
class Sample {
    fun addSampleUser() {
        val user1 = User("HeeFlower5001", "5001", "김희승", "Hee1121", "010-4013-5698")
        val user2 = User("Sample", "Sample", "Sample", "Sample", "Sample")

        UserRepository.addUser(user1)
        UserRepository.addUser(user2)
    }

    fun addSampleStore() {
        val owner = UserRepository.findUser("HeeFlower5001", "5001")

        if (owner == null) throw NullPointerException()

        val store1 = Store(1, "한국항공대학교", 37.5998, 126.8655, "경기 고양시 덕양구 항공대학로 76", Category.RESTAURANT)
        val store2 = Store(2, "홍대입구 놀거리", 37.5571, 126.9253, "홍대입구 놀거리", Category.CAFE)

        StoreRepository.addStore(store1)
        StoreRepository.addStore(store2)

        val menu = Menu("sample", 0, "sample")

        store1.menus?.add(menu)
        store2.menus?.add(menu)
    }

    fun addSampleReservation() {
        val user = UserRepository.Users[1]

        val reservation = Reservation(user, 1, Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()), 3)

        StoreRepository.stores[0].checkReservation(reservation)
    }

    fun addSampleTable() {
        var a = Table(2)
        var b = Table(4)
        var c = Table(6)

        b.toggleReservationAvailable()

        StoreRepository.stores[0].tables?.add(a)
        StoreRepository.stores[0].tables?.add(a)
        StoreRepository.stores[0].tables?.add(b)
        StoreRepository.stores[0].tables?.add(c)
    }
}

