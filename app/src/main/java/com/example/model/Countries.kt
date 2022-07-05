package com.example.model

import java.io.Serializable
import java.util.ArrayList


data class Countries (
	val countryId : Int,
	val sortname : String,
	val name : String,
	 val phonecode : Int
):Serializable
data class CountriesList (
	 val countries : ArrayList<Countries>
):Serializable