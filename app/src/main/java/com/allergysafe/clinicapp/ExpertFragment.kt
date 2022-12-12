package com.allergysafe.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.allergysafe.clinicapp.databinding.FragmentExpertBinding


class ExpertFragment : Fragment() {

    private var _binding: FragmentExpertBinding? = null
    private val args : ExpertFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Expert"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentExpertBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            findNavController().navigate(ExpertFragmentDirections.actionExpertFragmentToViewKnowledgeFragment())
        }

        binding.button2.setOnClickListener {
            findNavController().navigate(ExpertFragmentDirections.actionExpertFragmentToUpdatePreferenceFragment(args.phone))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}