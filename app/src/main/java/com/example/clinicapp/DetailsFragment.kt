package com.example.clinicapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.allergen.InhalantsFragmentDirections
import com.example.clinicapp.databinding.FragmentDetailsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        binding.floatingActionButton4.setOnClickListener {
            val patient = hashMapOf("Name" to binding.name.text.toString(), "Age" to binding.age.text.toString(),"Phone" to binding.phone.text.toString(),"Email" to binding.email.text.toString(),"Doctor" to binding.doctor.text.toString(),"Address" to binding.address.text.toString(), "Test recommended" to false)
            db.collection("Patients").document(binding.name.text.toString())
                .set(patient)
                .addOnSuccessListener {
                    Log.d(javaClass.simpleName, "DocumentSnapshot")
                    findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToSymptomsFragment(binding.name.text.toString(), binding.phone.text.toString()))
                }
                .addOnFailureListener { e ->
                    Log.w(javaClass.simpleName, "Error adding document", e)
                    Toast.makeText(activity,"Inserting data failed",Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}