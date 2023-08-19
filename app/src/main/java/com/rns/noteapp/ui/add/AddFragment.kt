package com.rns.noteapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rns.noteapp.R
import com.rns.noteapp.databinding.FragmentAddBinding
import com.rns.noteapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_add
    override val viewModel: AddViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goBack.setOnClickListener { findNavController().navigateUp() }

    }

    override fun onPause() {
        super.onPause()
        viewModel.run {
            currentNote.value?.run {
                if (this.title.isNotEmpty())
                    addNote(this)
            }
        }
    }

}