package com.example.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Countries
import com.example.ui.databinding.MovieListBinding

internal class MoviesAdapter(private val context: Context,private val moviesList: ArrayList<Countries>) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val  movieListBinding  = DataBindingUtil.inflate<MovieListBinding>( LayoutInflater.from(parent.context),R.layout.movie_list, parent, false)
        return MyViewHolder(movieListBinding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
            holder.bind(movie)
    }
    internal inner class MyViewHolder(private val movieListBinding: MovieListBinding) :
        RecyclerView.ViewHolder(movieListBinding.root) {
            fun bind(countries: Countries){
                movieListBinding.countries=countries
                movieListBinding.executePendingBindings()
                movieListBinding.relativeLayout.setOnClickListener {
                    context.startActivity(Intent(context,ViewDetailsActivity::class.java)
                        .putExtra("movieList",countries))
                }
            }
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
}