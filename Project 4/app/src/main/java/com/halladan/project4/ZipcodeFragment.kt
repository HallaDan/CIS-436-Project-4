package com.halladan.project4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.halladan.project4.databinding.FragmentZipcodeBinding


class ZipcodeFragment : Fragment() {

    private var _binding: FragmentZipcodeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate the layout for this fragment
        _binding = FragmentZipcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initialize view model
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.initialize(requireActivity().application)

        // when clicked enter, pass the entered zipcode to MainViewModel
        // specifically the getWeatherInfo() to be used in
        // the api call
        binding.btnEnter.setOnClickListener {
            val zipcode = binding.etZipcode.text.toString()
            viewModel.getWeatherInfo(zipcode)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}