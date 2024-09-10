package com.halladan.project4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.halladan.project4.databinding.FragmentGifBinding

class GifFragment : Fragment() {

    private var _binding : FragmentGifBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        // observe livedata and update UI accordingly
        viewModel.location.observe(viewLifecycleOwner) { location ->
            binding.locationTV.text = "Location: $location"
        }
        viewModel.temperature.observe(viewLifecycleOwner) { temperature ->
            binding.tempTV.text = "Temperature: $temperature° F"
            tempGIF(temperature.toString().toDouble())
        }
        viewModel.feelsLike.observe(viewLifecycleOwner) { feelslike ->
            binding.feelslLikeTV.text = "Feels Like: $feelslike° F"
        }
        viewModel.condition.observe(viewLifecycleOwner) { condition ->
            binding.conditionTV.text = "Condition: $condition"
        }
        viewModel.windSpeed.observe(viewLifecycleOwner) { windSpeed ->
            binding.windSpeedTV.text = "Wind Speed: $windSpeed MPH"
        }
        viewModel.humidity.observe(viewLifecycleOwner) { humidity ->
            binding.humidityTV.text = "Humidity: $humidity%"
        }

    }
    // function to load specific gif depending on the temperature
    fun tempGIF(temp : Double) {
        if(temp < 32.0){
            Glide.with(this).load("https://media.giphy.com/media/v1." +
                    "Y2lkPTc5MGI3NjExa3R5MXdubDc0N3gzd3ptenplcTZnZXRtdzNycGMxOWVmZWly" +
                    "ZWtmNyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/IRg7hSjarLJeM/giphy.gif").into(binding.gifImageView)
        }
        else if(temp in 32.0..50.0) {
            Glide.with(this).load("https://media.giphy.com/media/v1." +
                    "Y2lkPTc5MGI3NjExbm03bzA2b2R3emZ4cGJ1Mjg3NHRjMW1oNHFxNWg3Y2ZwOHdl" +
                    "MmpmZiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/SSi0nINo5sll1LiZeo/giphy.gif").into(binding.gifImageView)
        }
        else if(temp in 51.0..65.0) {
            Glide.with(this).load("https://media.giphy.com/media/v1." +
                    "Y2lkPTc5MGI3NjExYTUwdHlzaXZ1bTl1eHM2dHk5dXhsd3hiZW5uNzZ3YmRsdjE2MW" +
                    "c4ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/sY8rIKtI3sU0brCeQq/giphy.gif").into(binding.gifImageView)
        }
        else if(temp in 66.0..79.0) {
            Glide.with(this).load("https://media.giphy.com/media/v1." +
                    "Y2lkPTc5MGI3NjExZDVvaG9ubzY4ZnBsbTQ3NjU1MWx3bmJwYWNsMnQ5emlpZGp5OX" +
                    "huZCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/bXYfZa6fK2aGfXSACd/giphy.gif").into(binding.gifImageView)
        }
        else if(temp >= 80.0) {
            Glide.with(this).load("https://media.giphy.com/media/v1." +
                    "Y2lkPTc5MGI3NjExcDN6bnFlZ3pleG5jYndhNnY0eDdkOThpaXc4eTd2eDNqbzV1dGRl" +
                    "ZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/26BREnyYXsPOxlUKk/giphy.gif").into(binding.gifImageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}