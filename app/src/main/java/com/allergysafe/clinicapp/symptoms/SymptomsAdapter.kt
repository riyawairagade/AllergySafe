package com.allergysafe.clinicapp.symptoms

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allergysafe.clinicapp.databinding.ItemRowSymptomsBinding
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
            val sym = mutableMapOf<String, String>()
            symptom.setOnClickListener{
                sym[symptom.text.toString()] = "yes"
                symptom.setBackgroundColor(Color.parseColor("#8BC34A"))

                db.collection("Patients").document(name).collection("Symptoms").document("Symptoms")
                    .set(sym as Map<String, Any>, SetOptions.merge())
                    .addOnSuccessListener {
                        symptom.setBackgroundColor(Color.parseColor("#8BC34A"))
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return symptomsData.size
    }

    inner class ViewHolder(val binding: ItemRowSymptomsBinding) : RecyclerView.ViewHolder(binding.root)
}