package com.example.utiles

import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader

class  ResponseError {

    companion object{
        fun errorBody(response: Response<JsonObject>) {
            var reader: BufferedReader? = null
            try {
                if (response.code() == 401) {

                }
                else  if (response.code() == 500) {

                }
                else {
                    reader = BufferedReader(InputStreamReader(response.errorBody()!!.byteStream()))
                    if (reader != null) {
                        try {
                            val finallyError = reader.readLine()
                            val jsonObjectError = JSONObject(finallyError)
                            val message = jsonObjectError.optString("message")
                        } catch (e: JSONException) {
                            e.printStackTrace()

                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}