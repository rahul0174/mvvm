package com.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.model.LoginRequest
import com.example.ui.databinding.ActivityViewLoginDetailsBinding

class ViewLoginDetailsActivity : AppCompatActivity() {
    private lateinit var viewLoginDetailsBinding: ActivityViewLoginDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewLoginDetailsBinding=DataBindingUtil.setContentView(this,R.layout.activity_view_login_details)
        val loginRequest=intent?.getSerializableExtra("loginResponse") as LoginRequest
         viewLoginDetailsBinding.loginRequest = loginRequest

    }
}