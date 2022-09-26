package com.example.clinicapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.clinicapp.allergen.FruitsFragmentDirections
import com.example.clinicapp.databinding.FragmentReportBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset


class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private val args : ReportFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportBinding.inflate(inflater, container, false)

//        var fruitsData = args.fruitsData
//
//
//        var displayData : String = ""
//        var s : String = ""
//        var n : Float = 0.0F
//        var ro : Float = 0.0F
//        var o : Float = 0.0F
//        var uo : Float = 0.0F
//        var r : Float = 0.0F
//        var ru : Float = 0.0F
//        var u : Float = 0.0F
//
//        val items : List<String> = fruitsData.split(",")
//
//        items.forEach { entry ->
//            val row : List<String> = entry.split("=")
//            s = row[1]
//            when {
//                row[1] == "NR" -> {
//                    s = row[0].trim().lowercase().plus(" NR")
//                }
//                row[1] == "LR" -> {
//                    s = row[0].trim().lowercase().plus(" LR")
//                }
//                row[1] == "MR" -> {
//                    s = row[0].trim().lowercase().plus(" MR")
//                }
//                row[1] == "HR" -> {
//                    s = row[0].trim().lowercase().plus(" HR")
//                }
//            }
//            val myInputStream : InputStream = resources.openRawResource(R.raw.allergydata)
//            val bufferedReader = myInputStream.bufferedReader()
//            var line = bufferedReader.readLine()
//            while(line != null){
//                val row2 : List<String> = line.split(",")
//                if(row2[0] == s){
//                    displayData = displayData + row2.toString() + "\n"
//                    n += row2[2].toFloat()
//                    ro += row2[3].toFloat()
//                    o += row2[3].toFloat()
//                    uo += row2[4].toFloat()
//                    r += row2[5].toFloat()
//                    ru += row2[6].toFloat()
//                    u += row2[7].toFloat()
//                }
//                line = bufferedReader.readLine()
//            }
//        }
//
//        var outputList = listOf(n, ro, o, uo, r, ru, u)
//        binding.textView8.text = outputList.toString()

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