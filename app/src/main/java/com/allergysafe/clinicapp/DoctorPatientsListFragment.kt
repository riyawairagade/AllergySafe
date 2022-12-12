package com.allergysafe.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.allergysafe.clinicapp.databinding.FragmentDoctorPatientsListBinding
import com.allergysafe.clinicapp.technician.Patient
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DoctorPatientsListFragment : Fragment() {
    private var _binding: FragmentDoctorPatientsListBinding? = null
    private val doctorPatientsListAdapter = DoctorPatientsListAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Existing Patients"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDoctorPatientsListBinding.inflate(inflater, container, false)
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
                        data.add(Patient(doc.get("Name").toString(), doc.get("Phone").toString()))
                }
                doctorPatientsListAdapter.patientsData = data
            }
        binding.recyclerView.adapter = doctorPatientsListAdapter

        binding.home.setOnClickListener{
            findNavController().navigate(DoctorPatientsListFragmentDirections.actionDoctorPatientsListFragmentToDoctorFragment())
        }

        binding.newp.setOnClickListener{
            findNavController().navigate(DoctorPatientsListFragmentDirections.actionDoctorPatientsListFragmentToDetailsFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}