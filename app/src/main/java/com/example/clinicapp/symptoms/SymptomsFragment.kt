package com.example.clinicapp.symptoms

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.databinding.FragmentSymptomsBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SymptomsFragment : Fragment() {

    private lateinit var binding: FragmentSymptomsBinding
    private val symptomsAdapter = SymptomsAdapter()
    private val args : SymptomsFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Patient Signs and Symptoms"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSymptomsBinding.inflate(inflater)
        binding.recyclerViewSymptoms.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.name
        val phone = args.phone
        symptomsAdapter.name = name
        symptomsAdapter.phone = phone

        val data = mutableListOf(Symptom("RunningNose"), Symptom("Sneezing"), Symptom("Cough"), Symptom("WheezeBlocks"), Symptom("Headache"), Symptom("Itching"), Symptom("Swelling"), Symptom("RedRashes"), Symptom("FHistory"))
        symptomsAdapter.symptomsData = data
        binding.recyclerViewSymptoms.adapter = symptomsAdapter

        binding.floatingActionButton.setOnClickListener{
            val symptomsText = binding.others.editText?.text.toString()

            data.add(Symptom(symptomsText))
            symptomsAdapter.symptomsData = data
            binding.recyclerViewSymptoms.adapter = symptomsAdapter
        }

        binding.floatingActionButton10.setOnClickListener {
            val test = hashMapOf("Test recommended" to true)
            val db = Firebase.firestore
            val text = "Test recommended"

            db.collection("Patients").document(name)
                .set(test, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d(javaClass.simpleName, "DocumentSnapshot")
                    val toast = Toast.makeText(this.context, text, Toast.LENGTH_LONG)
                    toast.show()
                }
                .addOnFailureListener { e ->
                    Log.w(javaClass.simpleName, "Error adding document", e)
                }
        }

        binding.home.setOnClickListener{
            findNavController().navigate(SymptomsFragmentDirections.actionSymptomsFragmentToDoctorFragment())
        }

        binding.existingp.setOnClickListener{
            findNavController().navigate(SymptomsFragmentDirections.actionSymptomsFragmentToDoctorPatientsListFragment())
        }

        binding.newp.setOnClickListener{
            findNavController().navigate(SymptomsFragmentDirections.actionSymptomsFragmentToDetailsFragment())
        }
    }
}