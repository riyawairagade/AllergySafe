package com.example.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clinicapp.allergen.FruitsFragmentDirections
import com.example.clinicapp.databinding.FragmentOptionsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OptionsFragment : Fragment() {

    private var _binding: FragmentOptionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args : OptionsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pname = args.name
        val phone = args.phone

        binding.meatFishimageButton.setOnClickListener {
            findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToMeatFragment(pname, phone))
        }

        binding.inhalantsimageButton.setOnClickListener {
            findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToInhalantsFragment(pname, phone))
        }

        binding.vegetablesimageButton.setOnClickListener {
            findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToVegetablesFragment(pname, phone))
        }

        binding.fruitsimageButton.setOnClickListener {
            findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToFruitsFragment(pname, phone))
        }

        binding.otherfoodsimageButton.setOnClickListener {
            findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToOtherFoodsFragment(pname, phone))
        }

        binding.nextPatient.setOnClickListener {
//            val fruitsData = args.fruitsData
            val action = OptionsFragmentDirections.actionOptionsFragmentToPatientsListFragment()
            view.findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}