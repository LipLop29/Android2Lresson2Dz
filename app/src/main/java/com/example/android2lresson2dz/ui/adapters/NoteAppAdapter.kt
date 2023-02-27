package com.example.android2lresson2dz.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2lresson2dz.databinding.ItemNoteBinding
import com.example.android2lresson2dz.models.NoteModel

class NoteAppAdapter(val onItemLongClick : (noteModel : NoteModel) -> Unit) :
    RecyclerView.Adapter<NoteAppAdapter.NoteViewHolder>() {

    private var list: List<NoteModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(noteModel: NoteModel) {
            binding.itemNoteTitle.text = noteModel.title
            binding.itemNoteDescription.text = noteModel.description
            binding.tvTimeItem.text = noteModel.time
            binding.tvDataItem.text = noteModel.data
            binding.cvItem.setCardBackgroundColor(Color.parseColor(noteModel.color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnLongClickListener {
            onItemLongClick(list[position])
            true
        }
    }

    override fun getItemCount(): Int = list.size
}