package com.example.caesarappgithub.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caesarappgithub.Itemgithub.Model.FollowViewModel
import com.example.caesarappgithub.showLoading
import com.example.caesarappgithub.databinding.FragmentFollowerBinding
import com.example.caesarappgithub.ui.adapter.FollowListAdapter

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getString("username")
        val viewModel = ViewModelProvider(requireActivity())[FollowViewModel::class.java]


        binding.progressBar3.showLoading(true)


        viewModel.getFollower(args!!)
        viewModel.dataFollower.observe(viewLifecycleOwner) { followerList ->


            binding.progressBar3.showLoading(false)
            binding.rvFollower.adapter = FollowListAdapter(followerList)
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}