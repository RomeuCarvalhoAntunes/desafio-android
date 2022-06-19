package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.network.models.UserModel

object UserMap {
    fun fromDBArray(usersEntity: List<UserEntity>): List<UserModel> = usersEntity.map { UserModel(it.id, it.name, it.imgUrl, it.username) }

    fun fromDB(userEntity: UserEntity): UserModel = UserModel(userEntity.id, userEntity.name, userEntity.imgUrl, userEntity.username)

    fun toDB(userModel: UserModel): UserEntity = UserEntity(userModel.username, userModel.name, userModel.imgUrl, userModel.id)
}