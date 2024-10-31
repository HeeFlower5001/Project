package com.seed.model.store.owner

import com.seed.model.store.inside.Reservation
import kotlinx.serialization.Serializable
import kotlinx.datetime.*

@Serializable
class WaitingList(

) {
    var list : MutableList<Reservation>? = mutableListOf()

    fun addList(reservation: Reservation){
        if (list == null) {
            println("list is null")
        }

        else {
            list!!.add(reservation)

            sortReservationListByTime()
        }
    }

    fun delList(index: Int) {
        list?.removeAt(index)
    }

    fun delList(reservation: Reservation) {
        if (list == null) {
            println("list is null")
        }

        else {
            for (i in 0 until list!!.size) {
                if (list!![i] == reservation) {
                    list!!.removeAt(i)
                }
            }
        }
    }

    // 특정 시간대의 Reservation을 삭제
    fun delList(whatTime: LocalDateTime, whereTable: Int) {
        if (list == null) {
            println("list is null")
        }

        else {
            for (i in 0 until list!!.size) {
                if (list!![i].whatTime == whatTime && list!![i].whereTable == whereTable) {
                    list!!.removeAt(i)
                    break
                }
            }

        }
    }

    // 맨 앞 시간대의 Reservation을 삭제하고 반환
    fun dequeList() : Reservation? {
        if (list == null) {
            println("list is null")

            return null
        }

        else {
            val reservation = list!![0]

            list!!.removeAt(0)

            return reservation
        }
    }

    // list를 테이블별로 정렬
    fun sortReservationListByTable() {
        if (list == null) {
            println("list is null")
        }

        else {
            list!!.sortBy { it.whereTable }
        }
    }

    // list를 시간별로 정렬
    fun sortReservationListByTime() {
        if (list == null) {
            println("list is null")
        }

        else {
            list!!.sortBy {it.whatTime}
        }
    }
}

