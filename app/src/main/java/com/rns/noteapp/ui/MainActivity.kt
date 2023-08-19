package com.rns.noteapp.ui

import com.rns.noteapp.R
import com.rns.noteapp.databinding.ActivityMainBinding
import com.rns.noteapp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val viewID = R.layout.activity_main
}