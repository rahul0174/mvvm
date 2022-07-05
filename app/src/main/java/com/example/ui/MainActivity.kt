package com.example.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.model.Countries
import com.example.repository.ExceptionHandler
import com.example.ui.databinding.ActivityMainBinding
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.MainViewModelFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("activityFirst","onCreate")
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainBinding.shimmerFrameLayout.startShimmer()
        val response = (application as MainApplication).repository
        mainViewModel= ViewModelProvider(this,MainViewModelFactory(response))[MainViewModel::class.java]

        mainViewModel.liveDataCounty.observe(this){ it ->
            when(it){
                is ExceptionHandler.Loading->{}
                is ExceptionHandler.Success->{
                    mainBinding.shimmerFrameLayout.stopShimmer()
                    mainBinding.shimmerFrameLayout.visibility=View.GONE
                    mainBinding.countryNameRecyclerView.visibility=View.VISIBLE
                    it.data?.let {
                        Log.d("countryList" ,it.countries[0].name)
                        mainViewModel.findByStateID(it.countries[0].countryId)
                        getDataInRecyclerView(it.countries)
                    }
                }
                is ExceptionHandler.Error->{
                    Toast.makeText(this@MainActivity,it.errorMessage,Toast.LENGTH_SHORT).show()
                }
            }
        }
        mainViewModel.liveDataStateList.observe(this){ it ->
            when(it){
                is ExceptionHandler.Loading->{}
                is ExceptionHandler.Success->{
                    it.data?.let {
                        Log.d("statesList" ,it.states[0].name)
                        mainViewModel.findByCitiesID(it.states[0].stateId)
                    }
                }
                is ExceptionHandler.Error->{
                    Toast.makeText(this@MainActivity,it.errorMessage,Toast.LENGTH_SHORT).show()
                }
            }
        }
        mainViewModel.liveDataCitiesList.observe(this){ it ->
            when(it){
                is ExceptionHandler.Loading->{}
                is ExceptionHandler.Success->{
                    it.data?.let {
                        Log.d("citesList" ,it.cities[0].name)
                    }
                }
                is ExceptionHandler.Error->{
                    Toast.makeText(this@MainActivity,it.errorMessage,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("activityFirst","onStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("activityFirst","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("activityFirst","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("activityFirst","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("activityFirst","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("activityFirst","onDestroy")
    }
    private fun getDataInRecyclerView(countries : ArrayList<Countries>){
        val  linearLayoutManager= LinearLayoutManager(this)
        mainBinding.countryNameRecyclerView.layoutManager = linearLayoutManager
        val moviesAdapter=MoviesAdapter(this@MainActivity,countries)
        mainBinding.countryNameRecyclerView.adapter=moviesAdapter
    }
}