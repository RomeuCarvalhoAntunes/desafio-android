package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.data.state.DataState
import com.picpay.desafio.android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initData() {
        registerObserverUsers()
        mainActivityViewModel.setStateEventUsers(UserStateEvent.getUsersEvent)
    }

    private fun registerObserverUsers() {
        mainActivityViewModel.dataStateUsers.observe(this) { dataState ->
            when (dataState) {
                is DataState.Error -> {
                    binding.userListProgressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "erro na request", Toast.LENGTH_SHORT)
                        .show()
                }
                DataState.Loading -> {
                    binding.userListProgressBar.visibility = View.VISIBLE

                }
                is DataState.Success -> {
                    binding.userListProgressBar.visibility = View.GONE
                    val adapter = UserListAdapter()
                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager = LinearLayoutManager(this)
                    adapter.users = dataState.data
                }
            }
        }
    }

}
