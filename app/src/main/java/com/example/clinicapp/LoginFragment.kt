package com.example.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val techUser = "technician"
        val techPass = "tech"
        val docUser = "doctor"
        val docPass = "doc"
        val expUser = "expert"
        val expPass = "exp"


        binding.create.setOnClickListener {
            if(binding.username.text.toString() == techUser && binding.password.text.toString() == techPass){
                findNavController().navigate(R.id.action_loginFragment_to_patientsListFragment)
            }
            else if(binding.username.text.toString() == docUser && binding.password.text.toString() == docPass){
                findNavController().navigate(R.id.action_loginFragment_to_doctorFragment)
            }
            else if(binding.username.text.toString() == expUser && binding.password.text.toString() == expPass){
                findNavController().navigate(R.id.action_loginFragment_to_expertFragment)
            }
            else{
                Toast.makeText(activity,"Username or Password is incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}