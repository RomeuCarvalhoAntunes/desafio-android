package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.mappers.UserMap
import com.picpay.desafio.android.data.network.models.UserModel
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.utils.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

/*
Test cases:
1. getUsers_success_NetworkAndCache()
    a) get users from server
    b) insert users into cache
    c) confirm cache insertion
    d) listen for emission from flow
2. getUsers_fail_Network()
3. getUsers_fail_CacheInsert()
4. getUsers_fail_CacheReturn()
* */


@InternalCoroutinesApi
class UserUseCasesTest {

    // dependencies
    private lateinit var repository: UserRepository

    @Test
    fun getUsers_success_NetworkAndCache() = runBlocking {
        val teste = arrayListOf(UserModel("1", "mock_01", "xxx_1", "user1"))
        repository = FakeUserRepository(
            cacheSource = HashMap(),
            networkSource = teste
        )

        val arraylist = UserMap.toDBArray(teste)

        repository.getUsers().collect(object : FlowCollector<DataState<List<UserEntity>?>> {
            override suspend fun emit(value: DataState<List<UserEntity>?>) {
                Assert.assertEquals(DataState.Success(arraylist), value)
            }
        })
    }
}