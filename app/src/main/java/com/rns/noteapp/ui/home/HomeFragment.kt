package com.rns.noteapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rns.noteapp.R
import com.rns.noteapp.databinding.FragmentHomeBinding
import com.rns.noteapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = HomeAdapter(emptyList(), viewModel)

        binding.floatingActionButton.setOnClickListener { navigateToAddNote() }

        lifecycleScope.launchWhenCreated {
            viewModel.navigateToEditNote.collect { event ->
                if (event != null) {
                    event.getContentIfNotHandled()?.let {
                        navigateEditNote(it)
                    }
                }
            }
        }
    }

    private fun navigateToAddNote() {
        val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
        findNavController().navigate(action)
    }

    private fun navigateEditNote(id: Long) {
        val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(id)
        findNavController().navigate(action)
    }
}