package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.mappers.UserMap
import com.picpay.desafio.android.data.network.models.UserModel
import org.junit.Test

import org.junit.Assert.*

/*
Test cases:
1. fromDb_success()
    a) convert UserEntity to UserModel
2. fromDBArray_success()
    a) convert List<UserEntity> to List<UserModel>
3. toDb_success()
    a) convert UserModel to UserEntity
4. toDBArray_success()
    a) convert List<UserModel> to List<UserEntity>
*/



class UserMapTest {

    private var userEntity: UserEntity =
        UserEntity("MOCK_USERNAME", "MOCK_NAME", "IMG_URL", "ID")
    private var userModel: UserModel =
        UserModel("ID", "MOCK_NAME", "IMG_URL", "MOCK_USERNAME")

    private var userEntityList: List<UserEntity> = listOf(
        UserEntity("MOCK_USERNAME_01", "MOCK_NAME_01", "MOCK_IMG_URL_01", "MOCK_ID_01"),
        UserEntity("MOCK_USERNAME_02", "MOCK_NAME_02", "MOCK_IMG_URL_02", "MOCK_ID_02"),
        UserEntity("MOCK_USERNAME_03", "MOCK_NAME_03", "MOCK_IMG_URL_03", "MOCK_ID_03"),
        UserEntity("MOCK_USERNAME_04", "MOCK_NAME_04", "MOCK_IMG_URL_04", "MOCK_ID_04"),
        UserEntity("MOCK_USERNAME_05", "MOCK_NAME_05", "MOCK_IMG_URL_05", "MOCK_ID_05"),
        UserEntity("MOCK_USERNAME_06", "MOCK_NAME_06", "MOCK_IMG_URL_06", "MOCK_ID_06"),
    )
    private var userModelList: List<UserModel> = listOf(
        UserModel("MOCK_ID_01", "MOCK_NAME_01", "MOCK_IMG_URL_01", "MOCK_USERNAME_01"),
        UserModel("MOCK_ID_02", "MOCK_NAME_02", "MOCK_IMG_URL_02", "MOCK_USERNAME_02"),
        UserModel("MOCK_ID_03", "MOCK_NAME_03", "MOCK_IMG_URL_03", "MOCK_USERNAME_03"),
        UserModel("MOCK_ID_04", "MOCK_NAME_04", "MOCK_IMG_URL_04", "MOCK_USERNAME_04"),
        UserModel("MOCK_ID_05", "MOCK_NAME_05", "MOCK_IMG_URL_05", "MOCK_USERNAME_05"),
        UserModel("MOCK_ID_06", "MOCK_NAME_06", "MOCK_IMG_URL_06", "MOCK_USERNAME_06"),
    )

    @Test
    fun fromDb_success() {
        assertEquals(UserMap.fromDB(userEntity), userModel)
    }

    @Test
    fun fromDBArray_success() {
        assertEquals(UserMap.fromDBArray(userEntityList), userModelList)
    }

    @Test
    fun toDb_success() {
        assertEquals(UserMap.toDB(userModel), userEntity)
    }

    @Test
    fun toDBArray_success() {
        assertEquals(UserMap.toDBArray(userModelList), userEntityList)
    }
}