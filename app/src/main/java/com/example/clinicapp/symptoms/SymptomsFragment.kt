package com.example.clinicapp.symptoms

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.R
import com.example.clinicapp.allergen.InhalantsFragmentArgs
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

        val data = mutableListOf(Symptom("Running nose"), Symptom("Sneezing"), Symptom("Cough"), Symptom("Wheezing/Blocks"), Symptom("Headache"), Symptom("Itching"), Symptom("Swelling/Flushing"), Symptom("Red rashes"), Symptom("Family History"))
        symptomsAdapter.symptomsData = data
        binding.recyclerViewSymptoms.adapter = symptomsAdapter

        binding.floatingActionButton.setOnClickListener{
            val symptomsText = binding.others.text.toString()

            data.add(Symptom(symptomsText))
            symptomsAdapter.symptomsData = data
            binding.recyclerViewSymptoms.adapter = symptomsAdapter
        }

        binding.imageButton2.setOnClickListener {
            findNavController().navigate(R.id.action_symptomsFragment_to_reportFragment)
        }

        binding.imageButton.setOnClickListener {
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
    }
}