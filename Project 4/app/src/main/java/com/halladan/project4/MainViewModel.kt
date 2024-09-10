package com.halladan.project4

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // live data initialization
    private val _location = MutableLiveData<String>()
    val location : LiveData<String> = _location
    private val _temperature = MutableLiveData<String>()
    val temperature : LiveData<String> = _temperature
    private val _feelsLike = MutableLiveData<String>()
    val feelsLike : LiveData<String> = _feelsLike
    private val _condition = MutableLiveData<String>()
    val condition : LiveData<String> = _condition
    private val _windSpeed = MutableLiveData<String>()
    val windSpeed : LiveData<String> = _windSpeed
    private val _humidity = MutableLiveData<String>()
    val humidity : LiveData<String> = _humidity


    private lateinit var application: Application

    // function to initialize view model
    fun initialize(application: Application) {
        this.application = application
    }

    // function to fetch current weather using volley
    fun getWeatherInfo(zipcode : String) {
        val weatherURL = "https://api.weatherapi.com/v1/current.json?" +
                "key=YOUR_API_KEY&q=$zipcode&aqi=no"

        // volley request set up
        val queue = Volley.newRequestQueue(application)
        val objectRequest = JsonObjectRequest(
            Request.Method.GET, weatherURL, null,
            { response ->
                // test to see if im getting a proper response
                Log.d("WeatherAPIResponse", "Response: $response")

                // parse the data from the response
                val locationObject = response.getJSONObject("location")
                val currentObject = response.getJSONObject("current")
                val conditionObject = currentObject.getJSONObject("condition")

                // extract the data from location/current/condition object
                val locationName = locationObject.getString("name")
                val tempF = currentObject.getString("temp_f")
                val feelsLikeTempF = currentObject.getString("feelslike_f")
                val currentCondition = conditionObject.getString("text")
                val windSpeedMPH = currentObject.getString("wind_mph")
                val humidityLevel = currentObject.getString("humidity")

                // update livedata with the extracted data
                _location.value = locationName.toString()
                _temperature.value = tempF.toString()
                _feelsLike.value = feelsLikeTempF.toString()
                _condition.value = currentCondition.toString()
                _windSpeed.value = windSpeedMPH.toString()
                _humidity.value = humidityLevel.toString()
            },
            { error ->
                // short error handling
                Log.e(ContentValues.TAG, "Error fetching weather info for " +
                        "$zipcode: ${error.toString()}", error)
            }
        )
        // add the request to the request queue
        queue.add(objectRequest)
    }

}
