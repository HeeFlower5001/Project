package com.seed.model.store

import com.seed.model.store.inside.Category

object StoreRepository {
    val stores = mutableListOf<Store>()

    init {
        stores.add(
            Store(1, "한국항공대학교", 37.5998, 126.8655, "경기 고양시 덕양구 항공대학로 76", Category.RESTAURANT)
        )
        stores.add(
            Store(2, "홍대입구 놀거리", 37.5571, 126.9253, "홍대입구 놀거리", Category.CAFE)
        )
    }

    fun findStoreById(id: Long) = stores.find{ it.id == id}

    fun findStoreByName(name: String) = stores.find{ it.name == name}

    fun findStoreByLocation(latitudeStart: Double, latitudeEnd: Double, longitudeStart: Double, longitudeEnd: Double): List<Store> {
        return stores.filter { store ->
            store.latitude!! in latitudeStart .. latitudeEnd && store.longitude!! in longitudeStart .. longitudeEnd
        }
    }

    fun addStore(store: Store){
        if(store.id?.let { findStoreById(it) } != null){
            println("이미 존재하는 점포입니다. ")
        }else {
            stores.add(store)
            println("새 점포를 추가했습니다.")
        }
    }


    fun getStore(name: String, address: String): List<Store>{
        // 좌표 필터 추가
        val existStore = stores.filter{it.name == name && it.address == address}
        return if(existStore.isEmpty()){
            println("해당 점포는 존재하지 않습니다.")
             emptyList()
        }else {
            existStore
        }
    }

    fun getAllStores():List<Store> = stores

    fun editStore(store: Store){
        val isStoreExist = stores.find{it.id == store.id}
        if(isStoreExist != null){
            stores.removeIf{it.id == store.id}
            stores.add(store)
        }else println("수정할 점포가 존재하지 않습니다.")
    }
}