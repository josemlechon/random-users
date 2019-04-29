package com.jml.random.users.home.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jml.random.users.R
import com.jml.random.users.home.vm.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    val detailViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
