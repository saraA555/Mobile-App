package com.sedra.fitroad.view.uploadinbodytwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sedra.fitroad.R
import com.sedra.fitroad.databinding.FragmentInBodyResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InBodyResultTwoFragment : Fragment() {

    var binding: FragmentInBodyResultBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_in_body_result, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            include.progressBar.progress = 7
            include.textView5.text = "STEP 7 OF 7"
            include.imageView4.setOnClickListener {
                findNavController().popBackStack()
            }

            textView8.text =
                "Last inBody Date : 04/05/2015\n" + "TBW : 27.2\n" + "Protien : 7.1\n" + "Mineral : 2.74\n" + "Body Fat Mass : 22.1\n" + "Weight : 59.1\n" + "BMI : 24.0\n" + "Percent Body Fat (%) : 37.5\n" + "InBody Score : 66\n" + "Weight Control : -6.2 kg\n" + "Impedance : 0"
            button.setOnClickListener {
                findNavController().navigate(R.id.action_inBodyResultTwoFragment_to_moreFragment)
            }
        }
    }


}