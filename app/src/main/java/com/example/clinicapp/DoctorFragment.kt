package com.example.clinicapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.databinding.FragmentDetailsBinding
import com.example.clinicapp.databinding.FragmentDoctorBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DoctorFragment : Fragment() {
    private var _binding: FragmentDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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