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
import com.example.clinicapp.databinding.FragmentFruitsBinding

class FruitsFragment : Fragment() {

    private lateinit var binding: FragmentFruitsBinding
    private val allergenAdapter = AllergenAdapter()
    private val args : FruitsFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Fruits"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFruitsBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf(Allergen("Mango", "NR", "LR", "MR", "HR"), Allergen("Banana 1", "NR", "LR", "MR", "HR"), Allergen("Banana 2", "NR", "LR", "MR", "HR"), Allergen("Pineapple", "NR", "LR", "MR", "HR"))
        allergenAdapter.data = data
        binding.recyclerView.adapter = allergenAdapter

        val pname = args.name
        allergenAdapter.pname = pname
        val phone = args.phone
        allergenAdapter.phone = phone

        binding.floatingActionButton5.setOnClickListener{
            val action = FruitsFragmentDirections.actionFruitsFragmentToOptionsFragment(pname, phone)
            view.findNavController().navigate(action)
        }
    }
}