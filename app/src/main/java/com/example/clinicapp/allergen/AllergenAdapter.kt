package com.example.clinicapp.allergen

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.databinding.ItemRowAllergenBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AllergenAdapter : RecyclerView.Adapter<AllergenAdapter.ViewHolder>(){

    var allergenList = linkedMapOf<String, String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var data = listOf<Allergen>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var phone = String()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var pname = String()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowAllergenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val allergen = data[position]

        val db = Firebase.firestore

        with(holder.binding) {
            a.text = allergen.name
            nr.text = allergen.nr
            r1.text = allergen.r1
            r2.text = allergen.r2
            r3.text = allergen.r3
            a.setOnClickListener{
                radioButtonGroup.visibility = View.VISIBLE
            }
            nr.setOnClickListener{
                a.setTextColor(Color.parseColor("#5Af158"))
                allergenList.put(allergen.name, allergen.nr)
                val data = hashMapOf(allergen.name to "nr")

                db.collection("Patients").document(pname).collection("Symptoms").document("Allergens")
                    .set(data, SetOptions.merge())

                val test = hashMapOf("Test completed" to true)
                db.collection("Patients").document(pname)
                    .set(test, SetOptions.merge())

            }
            r1.setOnClickListener{
                a.setTextColor(Color.parseColor("#FFFF00"))
                allergenList.put(allergen.name, allergen.r1)
                val data = hashMapOf(allergen.name to "lr")

                db.collection("Patients").document(pname).collection("Symptoms").document("Allergens")
                    .set(data, SetOptions.merge())

                val test = hashMapOf("Test completed" to true)
                db.collection("Patients").document(pname)
                    .set(test, SetOptions.merge())
            }
            r2.setOnClickListener{
                a.setTextColor(Color.parseColor("#FF9800"))
                allergenList.put(allergen.name, allergen.r2)
                val data = hashMapOf(allergen.name to "mr")

                db.collection("Patients").document(pname).collection("Symptoms").document("Allergens")
                    .set(data, SetOptions.merge())

                val test = hashMapOf("Test completed" to true)
                db.collection("Patients").document(pname)
                    .set(test, SetOptions.merge())
            }
            r3.setOnClickListener{
                a.setTextColor(Color.parseColor("#FF0000"))
                allergenList.put(allergen.name, allergen.r3)
                val data = hashMapOf(allergen.name to "hr")

                db.collection("Patients").document(pname).collection("Symptoms").document("Allergens")
                    .set(data, SetOptions.merge())

                val test = hashMapOf("Test completed" to true)
                db.collection("Patients").document(pname)
                    .set(test, SetOptions.merge())
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemRowAllergenBinding) : RecyclerView.ViewHolder(binding.root)
}
