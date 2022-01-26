package com.app.carbohydratesgide.ui.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.carbohydratesgide.R
import com.app.carbohydratesgide.databinding.ItemCategoriesBinding
import com.bumptech.glide.Glide

class CategoriesAdapter (
    private var arr: ArrayList<CategoriesItem>,
    private val onClick: (id: Int) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
    }


    override fun getItemCount() = arr.size

    class ViewHolder(
        private val binding: ItemCategoriesBinding,
        private val onClick: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoriesItem) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(item.image)
                    .into(image)

                textName.text = item.name


                itemView.setOnClickListener {
                    onClick(item.id)
                }
            }
        }

    }
}