package com.example.clinicapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicapp.databinding.FragmentUpdatePreferenceBinding
import com.example.clinicapp.knowledge.Knowledge
import com.example.clinicapp.knowledge.UpdatePreferenceAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdatePreferenceFragment : Fragment() {
    private var _binding: FragmentUpdatePreferenceBinding? = null
    private val updatePreferenceAdapter = UpdatePreferenceAdapter()

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
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        db.collection("Users")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(javaClass.simpleName, "Listen failed.", e)
                    return@addSnapshotListener
                }
                val data = mutableListOf<Knowledge>()
                for (doc in value!!) { //we have verified that there is no error so value will not be null
                    if (doc.data["Type"] == "Expert") {
                        db.collection("Users").document(doc.id).collection("Knowledge")
                            .addSnapshotListener { value, e ->
                                if (e != null) {
                                    Log.w(javaClass.simpleName, "Listen failed.", e)
                                    return@addSnapshotListener
                                }
                                for (doc in value!!) { //we have verified that there is no error so value will not be null
                                    data.add(Knowledge("", doc.get("Text").toString()))
                                }
                            }
                    }
                }
                updatePreferenceAdapter.data = data
            }
        binding.recyclerView3.adapter = updatePreferenceAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}