package com.example.clinicapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.clinicapp.databinding.FragmentReportBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.io.InputStream


class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val args : ReportFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportBinding.inflate(inflater, container, false)

        val name = args.name
        val phone = args.phone
        val db = Firebase.firestore
        val testData : HashMap<String, String> = hashMapOf()

        db.collection("Patients").document(name)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.w(javaClass.simpleName, "DocumentSnapshot data: ${document.data}")
                    if(document.data?.get("Test recommended") == true){
                        binding.textView30.text = "True"
                        binding.textView20.visibility = View.VISIBLE
                    }
                    else{
                        binding.textView30.text = "False"
                    }

                    if(document.data?.get("Test completed") == true){
                        binding.textView32.visibility = View.VISIBLE
                        binding.textView32.text = "True"
                    }
                    else{
                        binding.textView32.visibility = View.VISIBLE
                        binding.textView32.text = "Under Process"
                    }
                } else {
                    Log.w(javaClass.simpleName, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(javaClass.simpleName, "Listen failed.", exception)
            }


        val docRef = db.collection("Patients").document(name).collection("Symptoms").document("Allergens")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.w(javaClass.simpleName, "DocumentSnapshot data: ${document.data}")
                    println()
                    document.data?.forEach{ entry ->
                        testData[entry.key.toString()] = entry.value.toString()
                    }
                } else {
                    Log.w(javaClass.simpleName, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(javaClass.simpleName, "Listen failed.", exception)
            }

        val items : MutableList<String> = mutableListOf()

        val docRef2 = db.collection("Patients").document(name).collection("Symptoms").document("Symptoms")
        docRef2.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.w(javaClass.simpleName, "DocumentSnapshot data: ${document.data}")
                    document.data?.forEach{ entry ->
                        testData[entry.key.toString()] = entry.value.toString()
                    }

                    testData.forEach{ entry ->
                        val a : String = entry.key.plus(" ").plus(entry.value).lowercase()
                        items.add(a)
                    }
                    var displayData : String = ""
                    var n : Float = 0.0F
                    var ro : Float = 0.0F
                    var o : Float = 0.0F
                    var uo : Float = 0.0F
                    var r : Float = 0.0F
                    var ru : Float = 0.0F
                    var u : Float = 0.0F
                    items.forEach { entry ->
                        val myInputStream : InputStream = resources.openRawResource(R.raw.allergydata)
                        val bufferedReader = myInputStream.bufferedReader()
                        var line = bufferedReader.readLine()
                        while(line != null){
                            val row2 : List<String> = line.split(",")
                            if(row2[0].lowercase() == entry){
                                displayData = displayData + row2.toString() + "\n"
                                n += row2[2].toFloat()
                                ro += row2[3].toFloat()
                                o += row2[3].toFloat()
                                uo += row2[4].toFloat()
                                r += row2[5].toFloat()
                                ru += row2[6].toFloat()
                                u += row2[7].toFloat()
                            }
                            line = bufferedReader.readLine()
                        }
                    }

                    var outputList = listOf("N: $n","RO: $ro","O: $o","UO: $uo","R: $r","RU: $ru","U: $u")
                    binding.textView33.text = outputList.toString()

//                    var pieChart = PieChart(context)
                    binding.piechart.addPieSlice(
                        PieModel(
                            "N", n,
                            Color.parseColor("#E91E63")
                        )
                    )
                    binding.piechart.addPieSlice(
                        PieModel(
                            "RO", ro,
                            Color.parseColor("#9C27B0")
                        )
                    )
                    binding.piechart.addPieSlice(
                        PieModel(
                            "O", o,
                            Color.parseColor("#3F51B5")
                        )
                    )
                    binding.piechart.addPieSlice(
                        PieModel(
                            "UO", uo,
                            Color.parseColor("#03A9F4")
                        )
                    )
                    binding.piechart.addPieSlice(
                        PieModel(
                            "R", r,
                            Color.parseColor("#4CAF50")
                        )
                    )
                    binding.piechart.addPieSlice(
                        PieModel(
                            "RU", ru,
                            Color.parseColor("#FFEB3B")
                        )
                    )
                    binding.piechart.addPieSlice(
                        PieModel(
                            "U", u,
                            Color.parseColor("#FF9800")
                        )
                    )
                    // To animate the pie chart
                    binding.piechart.startAnimation()

                } else {
                    Log.w(javaClass.simpleName, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(javaClass.simpleName, "Listen failed.", exception)
            }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}