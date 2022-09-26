package com.example.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.create.setOnClickListener {
            var text = ""
            val duration = Toast.LENGTH_SHORT
            val yrs = binding.experience.text.toString().toInt()
            when {
                yrs == 0 -> {
                    text = "You are a Technician"
                }
                yrs in 1..9 -> {
                    text = "You are a Doctor"
                }
                yrs >= 10 -> {
                    text = "You are an Expert"
                }
            }
            val toast = Toast.makeText(this.context, text, duration)
            toast.show()
        }

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }
}