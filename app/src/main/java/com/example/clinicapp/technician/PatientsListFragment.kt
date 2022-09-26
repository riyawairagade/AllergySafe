package com.example.clinicapp.technician

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.R
import com.example.clinicapp.databinding.FragmentPatientsListBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PatientsListFragment : Fragment() {

    private var _binding: FragmentPatientsListBinding? = null
    private val patientsAdapter = PatientAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPatientsListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        db.collection("Patients")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(javaClass.simpleName, "Listen failed.", e)
                    return@addSnapshotListener
                }
                val data = mutableListOf<Patient>()
                for (doc in value!!) { //we have verified that there is no error so value will not be null
                    if(doc.data["Test recommended"] == true){
                        data.add(Patient(doc.get("Name").toString(), doc.get("Phone").toString()))
                    }
                }
                patientsAdapter.patientsData = data
                println("data: $data")
            }

//        val data = listOf(Patient("1. P1 | 25F"), Patient("2. P2 | 40M"), Patient("3. P5 | 18M"))

        binding.recyclerView2.adapter = patientsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}