package com.rns.noteapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rns.noteapp.R
import com.rns.noteapp.databinding.FragmentEditBinding
import com.rns.noteapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : BaseFragment<FragmentEditBinding>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_edit
    override val viewModel: EditViewModel by activityViewModels()
    private val args: EditFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadNote(args.id)

        binding.goBack.setOnClickListener { findNavController().navigateUp() }

        lifecycleScope.launchWhenCreated {
            viewModel.deleteStatus.collect { event ->
                event.getContentIfNotHandled()?.let { findNavController().navigateUp()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.run {
            currentNote.value?.run {
                updateNote(this)
            }
        }
    }

}