package com.evertec.everteplacetopay.data

import com.evertec.everteplacetopay.data.model.UserEntity

object DataController {

    var userData: UserEntity? = null
    var id: Long = 0

    init {

    }


    fun getUserId(): Long {
        return id
    }

    fun setData(userEntity: UserEntity) {
        userData = userEntity
        id = userData!!.userId
    }

    fun cleanData() {
        userData = null
        id = -1
    }


}