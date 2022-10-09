package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/9 9:59
 * Email: lijt@eetrust.com
 */
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val userName: String,
    val pwd: String,
    @ColumnInfo(defaultValue = "'NULL'")
    val photo: String,
    val create: Long = System.currentTimeMillis()
) {
    override fun toString(): String {
        return "User(id=$id, userName='$userName', pwd='$pwd', photo='$photo', create=$create)"
    }
}
