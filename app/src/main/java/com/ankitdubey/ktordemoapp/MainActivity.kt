package com.ankitdubey.ktordemoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ankitdubey.ktordemoapp.networking.DataState
import com.ankitdubey.ktordemoapp.networking.RandomUserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var repository: RandomUserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = RandomUserRepository(ktorHttpClient)
        GlobalScope.launch {
            repository.fetchUserData().collect {
                when (it) {
                    is DataState.Loading -> Log.e("Status", "Loading")

                    is DataState.Error -> Log.e("Status", "error ${it.exception}")

                    is DataState.Success -> {
                        Log.e("Status", "Data loaded. ${it.data}")
                    }
                }
            }
        }

    }
}