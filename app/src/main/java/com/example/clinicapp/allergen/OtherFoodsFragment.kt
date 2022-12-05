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
import com.example.clinicapp.databinding.FragmentOtherFoodsBinding


class OtherFoodsFragment : Fragment() {
    private lateinit var binding: FragmentOtherFoodsBinding
    private val allergenAdapter = AllergenAdapter()
    private val args : OtherFoodsFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Other Foods"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOtherFoodsBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf(Allergen("Curd", "NR", "LR", "MR", "HR"), Allergen("Milk", "NR", "LR", "MR", "HR"), Allergen("Tea", "NR", "LR", "MR", "HR"))
        allergenAdapter.data = data
        binding.recyclerView.adapter = allergenAdapter

        binding.floatingActionButton3.setOnClickListener{
            val allergenText = binding.otherAllergens.editText?.text.toString()
            data.add(Allergen(allergenText, "NR", "LR", "MR", "HR"))
            allergenAdapter.data = data
            binding.recyclerView.adapter = allergenAdapter
        }

        val pname = args.name
        val phone = args.phone
        allergenAdapter.pname = pname
        allergenAdapter.phone = phone

        binding.floatingActionButton9.setOnClickListener{
            val action = OtherFoodsFragmentDirections.actionOtherFoodsFragmentToOptionsFragment(pname, phone)
            view.findNavController().navigate(action)
        }
    }
}