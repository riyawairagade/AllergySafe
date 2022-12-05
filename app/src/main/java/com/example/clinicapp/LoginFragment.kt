package com.example.clinicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.databinding.FragmentLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Login"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val techPass = "tech"
        val docPass = "doc"
        val expPass = "exp"

        val db = Firebase.firestore

        binding.login.setOnClickListener {

            val docRef = db.collection("Users").document(binding.username.editText?.text.toString())
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        if(document.data?.get("Years").toString().toInt() == 0 && binding.password.editText?.text.toString() == techPass){
                            findNavController().navigate(R.id.action_loginFragment_to_patientsListFragment)
                        }
                        else if(document.data?.get("Years").toString().toInt() in 1..9 && binding.password.editText?.text.toString() == docPass){
                            findNavController().navigate(R.id.action_loginFragment_to_doctorFragment)
                        }
                        else if(document.data?.get("Years").toString().toInt() >= 10 && binding.password.editText?.text.toString() == expPass){
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToExpertFragment(binding.username.editText?.text.toString()))
                        }
                        else{
                            Toast.makeText(activity,"Incorrect Password", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        val toast = Toast.makeText(this.context, "Incorrect Username", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}