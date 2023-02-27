package com.example.android2lresson2dz.ui.fragments.notapp.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android2lresson2dz.App
import com.example.android2lresson2dz.R
import com.example.android2lresson2dz.databinding.FragmentDetailNotBinding
import com.example.android2lresson2dz.models.NoteModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailNotFragment : Fragment() {

    private lateinit var binding: FragmentDetailNotBinding
    private var backgroundColor = "#191818"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNotBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
        setupListener()
    }

    private fun setupListener() = with(binding) {

        arrowLeft.setOnClickListener {
            findNavController().navigate(R.id.action_detailNotFragment_to_noteAppFragment)
        }
        btn1.setOnClickListener {
            backgroundColor = "#191818"
            imgDots1.isVisible = true
            imgDots2.isVisible = false
            imgDots3.isVisible = false
        }
        btn2.setOnClickListener {
            backgroundColor = "#EBE4C9"
            imgDots1.isVisible = false
            imgDots2.isVisible = true
            imgDots3.isVisible = false

        }
        btn3.setOnClickListener {
            backgroundColor = "#571818"
            imgDots1.isVisible = false
            imgDots2.isVisible = false
            imgDots3.isVisible = true
        }
    }

    @SuppressLint("WeekBasedYear")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendData() = with(binding) {
        val current = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("dd MMMM")
        val formatted = current.format(format)
        tvData.text = formatted

        btnReady.setOnClickListener {
            val data = tvData.text.toString()

            if (etTitle.text.isNotEmpty() && etDescription.text.isNotEmpty()) {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                val time = tcTimee.text.toString()
                App.appDatabase?.noteDao()
                    ?.insert(NoteModel(title, description, backgroundColor, time, data))
                findNavController().navigateUp()
            }
        }
    }
}