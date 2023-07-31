package com.example.assignment_ks_re.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.assignment_ks_re.data.model.Document
import com.example.assignment_ks_re.databinding.ItemBookPreviewBinding

class BookSearchAdapter : ListAdapter<Document, BookSearchViewHolder>(BookDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        return BookSearchViewHolder(
            ItemBookPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        val book = currentList[position]
        holder.bind(book)
    }

    companion object {
        private val BookDiffCallback = object : DiffUtil.ItemCallback<Document>() {
            override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
                return oldItem.isbn == newItem.isbn
            }

            override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
                return oldItem == newItem
            }
        }
    }
}