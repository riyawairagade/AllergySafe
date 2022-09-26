package com.example.clinicapp.technician

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapp.R
import com.example.clinicapp.databinding.ItemRowPatientBinding

class PatientAdapter : RecyclerView.Adapter<PatientAdapter.ViewHolder>() {
    var patientsData = mutableListOf<Patient>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowPatientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patients = patientsData[position]

        with(holder.binding) {
            patientText.text = patients.pname
        }

        holder.binding.yesimageButton.setOnClickListener{
            findNavController(holder.itemView).navigate(PatientsListFragmentDirections.actionPatientsListFragmentToOptionsFragment(patients.pname, patients.phone))
        }
    }

    override fun getItemCount(): Int {
        return patientsData.size
    }

    inner class ViewHolder(val binding: ItemRowPatientBinding) : RecyclerView.ViewHolder(binding.root)
}