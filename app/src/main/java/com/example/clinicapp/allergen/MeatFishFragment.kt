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
import com.example.clinicapp.databinding.FragmentMeatFishBinding


class MeatFishFragment : Fragment() {
    private lateinit var binding: FragmentMeatFishBinding
    private val allergenAdapter = AllergenAdapter()
    private val args : MeatFishFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Meat and Fish"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMeatFishBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf(Allergen("Beef", "NR", "LR", "MR", "HR"), Allergen("Chicken", "NR", "LR", "MR", "HR"), Allergen("Crab", "NR", "LR", "MR", "HR"), Allergen("Mutton", "NR", "LR", "MR", "HR"), Allergen("Prawns", "NR", "LR", "MR", "HR"), Allergen("Shark", "NR", "LR", "MR", "HR"), Allergen("Egg", "NR", "LR", "MR", "HR"), Allergen("Fish 1", "NR", "LR", "MR", "HR"))
        allergenAdapter.data = data
        binding.recyclerView.adapter = allergenAdapter

        val pname = args.name
        val phone = args.phone
        allergenAdapter.pname = pname
        allergenAdapter.phone = phone

        binding.floatingActionButton7.setOnClickListener{
            val action = MeatFishFragmentDirections.actionMeatFragmentToOptionsFragment(pname, phone)
            view.findNavController().navigate(action)
        }
    }
}