package com.picpay.desafio.android.data.local.source

import com.picpay.desafio.android.data.dependencies.FakeUserCacheDataSourceImpl
import com.picpay.desafio.android.data.local.database.entities.UserEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test



class UserCacheDataSourceTest {

     private val fakeDbInitialized = hashMapOf(
         Pair(
             "MOCK_USERNAME_01",
             UserEntity("MOCK_USERNAME_01", "MOCK_NAME_01", "MOCK_IMG_URL_01", "MOCK_ID_01")
         ),
         Pair(
             "MOCK_USERNAME_02",
             UserEntity("MOCK_USERNAME_02", "MOCK_NAME_02", "MOCK_IMG_URL_02", "MOCK_ID_02")
         ),
         Pair(
             "MOCK_USERNAME_03",
             UserEntity("MOCK_USERNAME_03", "MOCK_NAME_03", "MOCK_IMG_URL_03", "MOCK_ID_03")
         ),
         Pair(
             "MOCK_USERNAME_04",
             UserEntity("MOCK_USERNAME_04", "MOCK_NAME_04", "MOCK_IMG_URL_04", "MOCK_ID_04")
         ),
         Pair(
             "MOCK_USERNAME_05",
             UserEntity("MOCK_USERNAME_05", "MOCK_NAME_05", "MOCK_IMG_URL_05", "MOCK_ID_05")
         ),
         Pair(
             "MOCK_USERNAME_06",
             UserEntity("MOCK_USERNAME_06", "MOCK_NAME_06", "MOCK_IMG_URL_06", "MOCK_ID_06")
         ),
     )

    @Test
    fun userInsert_success() = runBlocking {
        val userEntity = UserEntity("MOCK_USERNAME", "MOCK_NAME", "IMG_URL", "ID")
        val userCacheDataSource = FakeUserCacheDataSourceImpl(HashMap())
        Assert.assertEquals(1, userCacheDataSource.insertUser(userEntity))
    }


    @Test
    fun getAll_success() = runBlocking {
        val userCacheDataSource = FakeUserCacheDataSourceImpl(HashMap())
        Assert.assertEquals(emptyList<UserEntity>(), userCacheDataSource.getAllUsers())
    }

    @Test
    fun getUserByUsername_success() = runBlocking {
        val userCacheDataSource = FakeUserCacheDataSourceImpl(fakeDbInitialized)
        Assert.assertEquals(
            UserEntity(
                "MOCK_USERNAME_04",
                "MOCK_NAME_04",
                "MOCK_IMG_URL_04",
                "MOCK_ID_04"
            ), userCacheDataSource.getUserByUsername("MOCK_USERNAME_04")
        )
    }

    @Test
    fun deleteUser_success() = runBlocking {
        val userCacheDataSource = FakeUserCacheDataSourceImpl(fakeDbInitialized)
        Assert.assertEquals(1, userCacheDataSource.deleteUser("MOCK_USERNAME_04"))
    }

}