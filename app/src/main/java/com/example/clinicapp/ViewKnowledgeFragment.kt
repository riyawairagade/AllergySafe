package com.example.clinicapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicapp.databinding.FragmentViewKnowledgeBinding
import com.example.clinicapp.knowledge.Knowledge
import com.example.clinicapp.knowledge.ViewKnowledgeAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ViewKnowledgeFragment : Fragment() {

    private var _binding: FragmentViewKnowledgeBinding? = null
    private val viewKnowledgeAdapter = ViewKnowledgeAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "View Knowledge"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewKnowledgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        val data = mutableListOf<Knowledge>()
        db.collection("Users").whereEqualTo("Type", "Expert").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var n :String = document.get("Name").toString()
                    Log.d(TAG, "${document.id} => ${document.data}")
                    db.collection("Users").document(document.id).collection("Knowledge").get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                Log.d(TAG, "${document.id} => ${document.data}")
                                data.add(Knowledge(n, document.get("text").toString()))
                                println("data $data")
                            }

                        }
                        .addOnFailureListener { exception ->
                            Log.d(TAG, "Error getting documents: ", exception)
                        }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


//        db.collection("Users")
//            .addSnapshotListener { value, e ->
//                if (e != null) {
//                    Log.w(javaClass.simpleName, "Listen failed.", e)
//                    return@addSnapshotListener
//                }
//                val data = mutableListOf<Knowledge>()
//                for (doc in value!!) { //we have verified that there is no error so value will not be null
//                    if (doc.data["Type"].toString() == "Expert") {
//                        db.collection("Users").document(doc.id).collection("Knowledge")
//                            .addSnapshotListener { value, e ->
//                                if (e != null) {
//                                    Log.w(javaClass.simpleName, "Listen failed.", e)
//                                    return@addSnapshotListener
//                                }
//                                for (doc in value!!) { //we have verified that there is no error so value will not be null
//                                    data.add(Knowledge(doc.get("text").toString()))
//                                }
//                            }
//                    }
//                }
//                viewKnowledgeAdapter.data = data
//            }
        binding.recyclerView4.adapter = viewKnowledgeAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}