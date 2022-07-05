package com.example.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.model.Countries
import com.example.model.LoginRequest
import com.example.repository.ExceptionHandler
import com.example.ui.databinding.ActivityViewDetailsBinding
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.MainViewModelFactory

class ViewDetailsActivity : AppCompatActivity() {
    private lateinit var viewDetailsBinding: ActivityViewDetailsBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var countries: Countries
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activitySecond","onCreate")
        super.onCreate(savedInstanceState)
        viewDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_details)
        val response = (application as MainApplication).repository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(response))[MainViewModel::class.java]
        countries = intent?.getSerializableExtra("movieList") as Countries
        viewDetailsBinding.countries = countries
        viewDetailsBinding.executePendingBindings()

        viewDetailsBinding.submitBtn.setOnClickListener {
            viewDetailsBinding.progressBar1.visibility= View.VISIBLE
            val loginRequest = LoginRequest(
                "", "", "", "", "",
                "", "", "android@yopmail.com", viewDetailsBinding.login.text.toString(),
                "android"
            )
            mainViewModel.sendLoginData(loginRequest)
        }
        mainViewModel.liveDataLoginRequest.observe(this){ it ->
            when(it){
                is ExceptionHandler.Loading->{}
                is ExceptionHandler.Success->{
                    viewDetailsBinding.progressBar1.visibility= View.GONE
                    it.data?.let {
                        startActivity(Intent(this,ViewLoginDetailsActivity::class.java)
                            .putExtra("loginResponse",it)) }
                }
                is ExceptionHandler.Error->{
                    viewDetailsBinding.progressBar1.visibility= View.GONE
                    it.errorMessage
                    Toast.makeText(this@ViewDetailsActivity,it.errorMessage,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("activitySecond","onStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("activitySecond","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("activitySecond","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("activitySecond","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("activitySecond","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("activitySecond","onDestroy")
    }
}