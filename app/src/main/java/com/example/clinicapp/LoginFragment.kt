package com.example.clinicapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.clinicapp.databinding.FragmentLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val techUser = "technician"
        val techPass = "tech"
        val docUser = "doctor"
        val docPass = "doc"
        val expUser = "expert"
        val expPass = "exp"

        val db = Firebase.firestore

        binding.login.setOnClickListener {

            val docRef = db.collection("Users").document(binding.username.text.toString())
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d(javaClass.simpleName, "DocumentSnapshot data: ${document.data}")
                        if(document.data?.get("Years").toString().toInt() == 0 && binding.password.text.toString() == techPass){
                            findNavController().navigate(R.id.action_loginFragment_to_patientsListFragment)
                        }
                        else if(document.data?.get("Years").toString().toInt() in 1..9 && binding.password.text.toString() == docPass){
                            findNavController().navigate(R.id.action_loginFragment_to_doctorFragment)
                        }
                        else if(document.data?.get("Years").toString().toInt() >= 10 && binding.password.text.toString() == expPass){
                            findNavController().navigate(R.id.action_loginFragment_to_expertFragment)
                        }
                        else{
                            Toast.makeText(activity,"Incorrect Password", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Log.d(javaClass.simpleName, "No such document")
                        val toast = Toast.makeText(this.context, "Incorrect Username", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(javaClass.simpleName, "get failed with ", exception)
                }


//            if(binding.username.text.toString() == techUser && binding.password.text.toString() == techPass){
//                findNavController().navigate(R.id.action_loginFragment_to_patientsListFragment)
//            }
//            else if(binding.username.text.toString() == docUser && binding.password.text.toString() == docPass){
//                findNavController().navigate(R.id.action_loginFragment_to_doctorFragment)
//            }
//            else if(binding.username.text.toString() == expUser && binding.password.text.toString() == expPass){
//                findNavController().navigate(R.id.action_loginFragment_to_expertFragment)
//            }
//            else{
//                Toast.makeText(activity,"Username or Password is incorrect", Toast.LENGTH_LONG).show()
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}