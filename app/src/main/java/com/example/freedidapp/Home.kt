package com.example.freedidapp

import android.media.session.MediaController
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.freedidapp.databinding.FragmentHomeBinding


class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root


      /*  val mediaController = android.widget.MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        val rawVideoUri =
            Uri.parse("android.resource://${requireContext().packageName}/${R.raw.sister}")
        binding.videoView.setVideoURI(rawVideoUri)
        binding.videoView.start()
*/
        return view
    }


}