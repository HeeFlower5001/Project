package com.seed.model.store

import com.seed.model.store.inside.Menu
import com.seed.model.store.inside.Review
import com.seed.model.store.inside.Table
import com.seed.model.store.inside.Category
import com.seed.model.store.inside.Reservation
import com.seed.model.store.owner.WaitingList
import kotlinx.serialization.Serializable

@Serializable
data class Store (
    val id: Long?,  // 점포를 구분하기 위한 고유 번호
    var name: String?,
    var latitude: Double?,
    var longitude: Double?,
    val address: String?,
    val category: Category?,
    var review: MutableList<Review> = mutableListOf(),
    var tables:  MutableList<Table> = mutableListOf(),
    var waitingList: WaitingList = WaitingList(),
    var menus: MutableList<Menu> = mutableListOf()
) {
    fun checkReservation(reservation: Reservation) : Boolean {
        // 1. 경영주에게 예약 요청을 보내는 로직
        println("예약을 허용할까요? : y or n\n ")
        println("예약 사람 수 : ${reservation.numberOfPeople}")
        println("예약 시간: ${reservation.whatTime}\n")
        println("예약 테이블 번호: ${reservation.whereTable}\n")

        // 문법 수정
//        val input = readLine()?.toString() ?: throw NullPointerException()
//
//        // 2. 경영주의 반응을 받고 그에 맞게 로직 처리
////        if (input == "y") {
////            store.waitingList.addList(reservation)
////
////            return true
////        }
////
////        return false

        return true
    }
}