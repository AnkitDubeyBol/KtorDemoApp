package com.ankitdubey.ktordemoapp.networking

import com.ankitdubey.ktordemoapp.data.RandomUserDao
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RandomUserRepository (private val client : HttpClient){

    suspend fun fetchUserData() : Flow<DataState<RandomUserDao>>  = flow {
        emit(DataState.Loading)
        try {
            val user = client.get<RandomUserDao>("https://randomuser.me/api/")
            emit(DataState.Success(user))
        }catch (ex : Exception){
            emit(DataState.Error(ex))
        }
    }
}
