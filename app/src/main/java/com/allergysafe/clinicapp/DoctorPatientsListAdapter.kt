package com.allergysafe.clinicapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.allergysafe.clinicapp.databinding.ItemRowPatientBinding
import com.allergysafe.clinicapp.technician.Patient

class DoctorPatientsListAdapter : RecyclerView.Adapter<DoctorPatientsListAdapter.ViewHolder>() {
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
            patientButton.text = patients.pname
            patientButton.setOnClickListener{
                Navigation.findNavController(holder.itemView).navigate(DoctorPatientsListFragmentDirections.actionDoctorPatientsListFragmentToReportFragment(patients.pname, patients.phone))
            }
        }
    }

    override fun getItemCount(): Int {
        return patientsData.size
    }

    inner class ViewHolder(val binding: ItemRowPatientBinding) : RecyclerView.ViewHolder(binding.root)
}