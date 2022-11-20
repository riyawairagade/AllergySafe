package com.example.clinicapp.allergen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.allergen.Allergen
import com.example.clinicapp.allergen.AllergenAdapter
import com.example.clinicapp.databinding.FragmentVegetablesBinding


class VegetablesFragment : Fragment() {
    private lateinit var binding: FragmentVegetablesBinding
    private val allergenAdapter = AllergenAdapter()
    private val args : VegetablesFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Vegetables"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVegetablesBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf(Allergen("Ginger", "NR", "LR", "MR", "HR"), Allergen("Brinjal", "NR", "LR", "MR", "HR"), Allergen("Gram", "NR", "LR", "MR", "HR"), Allergen("Greens", "NR", "LR", "MR", "HR"), Allergen("Kovaikai", "NR", "LR", "MR", "HR"), Allergen("Tomato", "NR", "LR", "MR", "HR"), Allergen("Dhal", "NR", "LR", "MR", "HR"), Allergen("Drumstick", "NR", "LR", "MR", "HR"))
        allergenAdapter.data = data
        binding.recyclerView.adapter = allergenAdapter

//        var fruitsData = args.fruitsData
        var inhalantsData = allergenAdapter.allergenList
        val pname = args.name
        val phone = args.phone
        allergenAdapter.pname = pname
        allergenAdapter.phone = phone

        binding.floatingActionButton8.setOnClickListener{
            var inhalantsDataString = inhalantsData.toString()
            inhalantsDataString = inhalantsDataString.substring(1, inhalantsDataString.length -1)
//            fruitsData = fruitsData.plus(", ").plus(inhalantsDataString)
            val action = VegetablesFragmentDirections.actionVegetablesFragmentToOptionsFragment(pname, phone)
            view.findNavController().navigate(action)
        }
    }
}