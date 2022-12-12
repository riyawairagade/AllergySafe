package com.allergysafe.clinicapp.allergen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.allergysafe.clinicapp.databinding.FragmentInhalantsBinding

class InhalantsFragment : Fragment() {

    private lateinit var binding: FragmentInhalantsBinding
    private val allergenAdapter = AllergenAdapter()
    private val args : InhalantsFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Inhalants"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInhalantsBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf(Allergen("Aspergilus", "NR", "LR", "MR", "HR"), Allergen("Cockroach", "NR", "LR", "MR", "HR"), Allergen("Cottondust", "NR", "LR", "MR", "HR"), Allergen("Housedust", "NR", "LR", "MR", "HR"), Allergen("Parthenium", "NR", "LR", "MR", "HR"), Allergen("Pollen", "NR", "LR", "MR", "HR"))
        allergenAdapter.data = data
        binding.recyclerView.adapter = allergenAdapter

        val pname = args.name
        val phone = args.phone
        allergenAdapter.pname = pname
        allergenAdapter.phone = phone

        binding.floatingActionButton6.setOnClickListener{
            val action = InhalantsFragmentDirections.actionInhalantsFragmentToOptionsFragment(pname, phone)
            view.findNavController().navigate(action)
        }
    }
}