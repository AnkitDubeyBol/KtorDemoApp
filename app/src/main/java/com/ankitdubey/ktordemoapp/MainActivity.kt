package com.ankitdubey.ktordemoapp

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ankitdubey.ktordemoapp.miza_custom_views.MizaDropDown
import com.ankitdubey.ktordemoapp.miza_custom_views.MizaEditText
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


        /*repository = RandomUserRepository(ktorHttpClient)
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
        }*/
        val mizaEt = findViewById<MizaEditText>(R.id.dob)
        mizaEt.setOnClickListener {
            mizaEt.selectDate(supportFragmentManager) {
                Log.e("hurrah","$it")
            }
        }
        val items = listOf("-Select-","Agra","Kanpur","Lucknow","Noida","Ghaziabad")
        val mizaDropDown = findViewById<MizaDropDown>(R.id.select_city)
        mizaDropDown.setItems(items){
            Log.e("hurrah","$it")
        }
    }
}