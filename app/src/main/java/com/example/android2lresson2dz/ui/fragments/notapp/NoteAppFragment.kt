package com.example.android2lresson2dz.ui.fragments.notapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android2lresson2dz.App
import com.example.android2lresson2dz.R
import com.example.android2lresson2dz.databinding.FragmentNoteAppBinding
import com.example.android2lresson2dz.models.NoteModel
import com.example.android2lresson2dz.ui.adapters.NoteAppAdapter
import com.example.android2lresson2dz.utils.PreferenceHelper

class NoteAppFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppBinding
    private val noteAppAdapter = NoteAppAdapter(this::onItemLongClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setList()
        setupListener()
    }

    private fun setupListener() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteAppFragment_to_detailNotFragment)
        }
        imgGrid.setOnClickListener {
            recycler.layoutManager = GridLayoutManager(requireContext(),2)
            imgGrid.isVisible = false
            imgLiner.isVisible = true
        }
        imgLiner.setOnClickListener {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            imgGrid.isVisible = true
            imgLiner.isVisible = false
        }
        if (PreferenceHelper.onBoard) {
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initialize() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAppAdapter
        }
    }

    private fun setList() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { list ->
            noteAppAdapter.setList(list)
        }
    }

    private fun onItemLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить")
            setPositiveButton("Да") { _, _ ->
                App.appDatabase?.noteDao()?.delete(noteModel)
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }
}