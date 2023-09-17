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
import com.example.caesarappgithub.databinding.FragmentFollowingBinding
import com.example.caesarappgithub.ui.adapter.FollowListAdapter


class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getString("username")

        val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)

        if(savedInstanceState == null){
            viewModel.getFollowing(args!!)
        }

        viewModel.dataFollowing.observe(requireActivity()){
            binding.rvFollowing.adapter = FollowListAdapter(it)
        }

        viewModel.isFollowingLoading.observe(requireActivity()){
            binding.progressBar4.showLoading(it)
        }

        val manager = LinearLayoutManager(requireActivity())
        binding.rvFollowing.layoutManager = manager
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
