package com.sedra.fitroad.view.more

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sedra.fitroad.R
import com.sedra.fitroad.databinding.FragmentMoreBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoreFragment : Fragment() {

    var binding: FragmentMoreBinding? = null
    @Inject
    lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            include.homeIcon.setOnClickListener {
                findNavController().popBackStack()
            }
            logout.setOnClickListener{
                preferences.edit().clear().apply()
                findNavController().navigate(R.id.action_moreFragment_to_startingFragment)
            }
            include.homeIcon.setOnClickListener{
                findNavController().navigate(R.id.action_moreFragment_to_homeFragment)
            }
            include.exercises.setOnClickListener{
                findNavController().navigate(R.id.action_moreFragment_to_blankFragment)
            }
            include.trainers.setOnClickListener{
                findNavController().navigate(R.id.action_moreFragment_to_trainersFragment)
            }
            textView13.setOnClickListener{
                findNavController().navigate(R.id.action_moreFragment_to_uploadInBodyFragment2)
            }
            textView16.setOnClickListener{
                findNavController().navigate(R.id.action_moreFragment_to_contactUsFragment)
            }
        }
    }


}