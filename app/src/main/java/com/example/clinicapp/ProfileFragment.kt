package com.example.clinicapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.databinding.FragmentProfileBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Register"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        binding.create.setOnClickListener {
            var text = ""
            val duration = Toast.LENGTH_SHORT
            val yrs = binding.experience.editText?.text.toString()
            val name = binding.username.editText?.text.toString()
            val clinic = binding.clinic.editText?.text.toString()
            val edu = binding.education.editText?.text.toString()
            val cases = binding.cases.editText?.text.toString()
            var type : String = ""

            when {
                yrs.toInt() == 0 -> {
                    text = "You are a Technician"
                    type = "Technician"
                    binding.pword.text = "tech"
                }
                yrs.toInt() in 1..9 -> {
                    text = "You are a Doctor"
                    type = "Doctor"
                    binding.pword.text = "doc"
                }
                yrs.toInt() >= 10 -> {
                    text = "You are an Expert"
                    type = "Expert"
                    binding.pword.text = "exp"
                }
            }

            val user = hashMapOf("Name" to name, "Clinic" to clinic,"Education" to edu,"Years" to yrs,"Cases" to cases, "Type" to type)
            db.collection("Users").add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(javaClass.simpleName, "DocumentSnapshot written with ID: ${documentReference.id}")
                    binding.uname.text = documentReference.id
                }
                .addOnFailureListener { e ->
                    Log.w(javaClass.simpleName, "Error adding document", e)
                }


            val toast = Toast.makeText(this.context, text, duration)
            toast.show()
        }

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }
}