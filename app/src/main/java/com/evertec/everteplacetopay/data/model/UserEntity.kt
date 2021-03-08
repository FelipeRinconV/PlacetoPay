package com.evertec.everteplacetopay.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserEntity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    var userId: Long,
    val name: String,
    val password: String,

    ) {
    constructor(name: String, password: String) : this(0, name, password)
}



