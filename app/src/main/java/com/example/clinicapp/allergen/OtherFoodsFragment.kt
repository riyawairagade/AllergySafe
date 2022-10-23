package com.example.clinicapp.allergen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicapp.allergen.Allergen
import com.example.clinicapp.allergen.AllergenAdapter
import com.example.clinicapp.databinding.FragmentOtherFoodsBinding


class OtherFoodsFragment : Fragment() {
    private lateinit var binding: FragmentOtherFoodsBinding
    private val allergenAdapter = AllergenAdapter()
    private val args : OtherFoodsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOtherFoodsBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf(Allergen("Curd", "NR", "R1", "R2", "R3"), Allergen("Milk", "NR", "R1", "R2", "R3"), Allergen("Tea", "NR", "R1", "R2", "R3"))
        allergenAdapter.data = data
        binding.recyclerView.adapter = allergenAdapter

        binding.floatingActionButton3.setOnClickListener{
            val allergenText = binding.otherAllergens.text.toString()
            data.add(Allergen(allergenText, "NR", "LR", "MR", "HR"))
            allergenAdapter.data = data
            binding.recyclerView.adapter = allergenAdapter
        }

//        var fruitsData = args.fruitsData
        val pname = args.name
        val phone = args.phone
        allergenAdapter.pname = pname
        allergenAdapter.phone = phone

        var inhalantsData = allergenAdapter.allergenList

        binding.floatingActionButton9.setOnClickListener{
            var inhalantsDataString = inhalantsData.toString()
            inhalantsDataString = inhalantsDataString.substring(1, inhalantsDataString.length -1)
//            fruitsData = fruitsData.plus(", ").plus(inhalantsDataString)
            val action = OtherFoodsFragmentDirections.actionOtherFoodsFragmentToOptionsFragment(pname, phone)
            view.findNavController().navigate(action)
        }
    }
}