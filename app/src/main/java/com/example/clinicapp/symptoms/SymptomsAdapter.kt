package com.example.clinicapp.symptoms

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.DetailsFragmentDirections
import com.example.clinicapp.databinding.ItemRowSymptomsBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SymptomsAdapter : RecyclerView.Adapter<SymptomsAdapter.ViewHolder>() {
    var symptomsData = listOf<Symptom>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var name = String()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var phone = String()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowSymptomsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val symptoms = symptomsData[position]

        with(holder.binding) {
            symptom.text = symptoms.name

            val db = Firebase.firestore
            val sym = mutableMapOf<String, Boolean>()
            symptom.setOnClickListener{
                sym[symptom.text.toString()] = true
                symptom.setBackgroundColor(Color.parseColor("#8BC34A"))

                db.collection("Patients").document(name).collection("Symptoms").document("Symptoms")
                    .set(sym as Map<String, Any>, SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d(javaClass.simpleName, "DocumentSnapshot")
                        symptom.setBackgroundColor(Color.parseColor("#8BC34A"))
                    }
                    .addOnFailureListener { e ->
                        Log.w(javaClass.simpleName, "Error adding document", e)
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return symptomsData.size
    }

    inner class ViewHolder(val binding: ItemRowSymptomsBinding) : RecyclerView.ViewHolder(binding.root)
}