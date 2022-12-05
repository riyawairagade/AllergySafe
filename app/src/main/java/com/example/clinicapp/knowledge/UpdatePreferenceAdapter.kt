package com.example.clinicapp.knowledge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.databinding.ItemRowUpdateKnowledgeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdatePreferenceAdapter : RecyclerView.Adapter<UpdatePreferenceAdapter.ViewHolder>(){

    var data = mutableListOf<Knowledge>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var name = String()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdatePreferenceAdapter.ViewHolder {
        return ViewHolder(
            ItemRowUpdateKnowledgeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val know = data[position]

        with(holder.binding) {
            textView28.text = know.text

            imageView3.setOnClickListener{
                val db = Firebase.firestore
                db.collection("Users").document(name).collection("Knowledge").whereEqualTo("text", textView28.text.toString())
                    .addSnapshotListener { value, e ->
                        if (e != null) {
                            return@addSnapshotListener
                        }
                        for (doc in value!!) {
                            db.collection("Users").document(name).collection("Knowledge").document(doc.id)
                                .delete()
                        }
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemRowUpdateKnowledgeBinding) : RecyclerView.ViewHolder(binding.root)
}