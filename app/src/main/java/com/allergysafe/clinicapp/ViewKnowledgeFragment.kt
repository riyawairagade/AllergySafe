package com.allergysafe.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.allergysafe.clinicapp.databinding.FragmentViewKnowledgeBinding
import com.allergysafe.clinicapp.knowledge.Knowledge
import com.allergysafe.clinicapp.knowledge.ViewKnowledgeAdapter
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
        db.collection("Users").whereEqualTo("Type", "Expert")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                val data = mutableListOf<Knowledge>()
                for (doc in value!!) { //we have verified that there is no error so value will not be null
                    val n :String = doc.get("Name").toString()
                    db.collection("Users").document(doc.id).collection("Knowledge")
                        .addSnapshotListener { result, er ->
                            if (er != null) {
                                return@addSnapshotListener
                            }
                            for (d in result!!) {
                                data.add(Knowledge(n, d.get("text").toString()))
                            }
                            viewKnowledgeAdapter.data = data
                        }
                    binding.recyclerView4.adapter = viewKnowledgeAdapter
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}