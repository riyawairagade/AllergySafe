package com.allergysafe.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.allergysafe.clinicapp.databinding.FragmentUpdatePreferenceBinding
import com.allergysafe.clinicapp.knowledge.Knowledge
import com.allergysafe.clinicapp.knowledge.UpdatePreferenceAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdatePreferenceFragment : Fragment() {
    private var _binding: FragmentUpdatePreferenceBinding? = null
    private val updatePreferenceAdapter = UpdatePreferenceAdapter()
    private val args : UpdatePreferenceFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Update Preference"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdatePreferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updatePreferenceAdapter.name = args.name
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        db.collection("Users").document(args.name).collection("Knowledge")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                val ndata = mutableListOf<Knowledge>()
                for (doc in value!!) {
                    doc.getString("text")?.let { it ->
                        ndata.add(Knowledge(args.name, it))
                    }
                }
                updatePreferenceAdapter.data = ndata
            }
        binding.recyclerView3.adapter = updatePreferenceAdapter

        binding.floatingActionButton2.setOnClickListener{
            val pref = binding.editTextTextPersonName.editText?.text.toString()
            val newData = hashMapOf("text" to pref)
            db.collection("Users").document(args.name).collection("Knowledge").add(newData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}