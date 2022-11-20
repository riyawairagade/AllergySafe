package com.example.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.databinding.FragmentDoctorBinding


class DoctorFragment : Fragment() {
    private var _binding: FragmentDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Doctor"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newPatient.setOnClickListener {
            findNavController().navigate(DoctorFragmentDirections.actionDoctorFragmentToDetailsFragment())
        }
        binding.existingPatient.setOnClickListener {
            findNavController().navigate(DoctorFragmentDirections.actionDoctorFragmentToDoctorPatientsListFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}