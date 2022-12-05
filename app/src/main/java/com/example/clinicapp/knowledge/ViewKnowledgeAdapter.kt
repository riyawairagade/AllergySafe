package com.example.clinicapp.knowledge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.databinding.ItemRowViewKnowledgeBinding

class ViewKnowledgeAdapter : RecyclerView.Adapter<ViewKnowledgeAdapter.ViewHolder>(){

    var data = mutableListOf<Knowledge>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowViewKnowledgeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val know = data[position]

        with(holder.binding) {
            var t : String = know.name.plus(": ").plus(know.text)
            textView28.text = t
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemRowViewKnowledgeBinding) : RecyclerView.ViewHolder(binding.root)
}