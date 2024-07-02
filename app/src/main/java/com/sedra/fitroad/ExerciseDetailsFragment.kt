package com.sedra.fitroad

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.sedra.fitroad.databinding.FragmentExerciseDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseDetailsFragment : Fragment() {

    var binding: FragmentExerciseDetailsBinding? = null
    val player by lazy { ExoPlayer.Builder(requireContext()).build() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_details, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Bind the player to the view.
        binding?.playerView?.player = player
        binding?.textView12?.text = arguments?.getString("name")
        val videoUri = Uri.parse(arguments?.getString("video"))
        val mediaItem = MediaItem.fromUri(videoUri)
// Set the media item to be played.
        player.setMediaItem(mediaItem)
// Prepare the player.
        player.prepare()
// Start the playback.
        player.play()


    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}