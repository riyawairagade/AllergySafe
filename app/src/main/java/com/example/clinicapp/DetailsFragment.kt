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
import com.example.clinicapp.databinding.FragmentDetailsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "New Patient"
    }

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
            val patient = hashMapOf("Name" to binding.name.editText?.text.toString(), "Age" to binding.age.editText?.text.toString(),"Phone" to binding.phone.editText?.text.toString(),"Email" to binding.email.editText?.text.toString(),"Doctor" to binding.doctor.editText?.text.toString(),"Address" to binding.address.editText?.text.toString(), "Test recommended" to false)
            if(binding.name.editText?.text.isNullOrBlank() || binding.age.editText?.text.isNullOrBlank() || binding.phone.editText?.text.isNullOrBlank() || binding.email.editText?.text.isNullOrBlank() || binding.doctor.editText?.text.isNullOrBlank() || binding.address.editText?.text.isNullOrBlank()){
                Toast.makeText(activity,"Please enter all the patient details",Toast.LENGTH_LONG).show()
            }
            else{
                db.collection("Patients").document(binding.name.editText?.text.toString())
                    .set(patient)
                    .addOnSuccessListener {
                        Log.d(javaClass.simpleName, "DocumentSnapshot")
                        findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToSymptomsFragment(binding.name.editText?.text.toString(), binding.phone.editText?.text.toString()))
                    }
                    .addOnFailureListener { e ->
                        Log.w(javaClass.simpleName, "Error adding document", e)
                        Toast.makeText(activity,"Inserting data failed",Toast.LENGTH_LONG).show()
                    }
            }
        }

        binding.home.setOnClickListener{
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToDoctorFragment())
        }

        binding.existingp.setOnClickListener{
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToDoctorPatientsListFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}