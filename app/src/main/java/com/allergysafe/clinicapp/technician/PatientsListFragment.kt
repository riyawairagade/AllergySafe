package com.allergysafe.clinicapp.technician

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.allergysafe.clinicapp.databinding.FragmentPatientsListBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PatientsListFragment : Fragment() {

    private var _binding: FragmentPatientsListBinding? = null
    private val patientsAdapter = PatientAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Technician"
    }

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
                    return@addSnapshotListener
                }
                val data = mutableListOf<Patient>()
                for (doc in value!!) { //we have verified that there is no error so value will not be null
                    if(doc.data["Test recommended"] == true){
                        data.add(Patient(doc.get("Name").toString(), doc.get("Phone").toString()))
                    }
                }
                patientsAdapter.patientsData = data
            }
        binding.recyclerView2.adapter = patientsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}