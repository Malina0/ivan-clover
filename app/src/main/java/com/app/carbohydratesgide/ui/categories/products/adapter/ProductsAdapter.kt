package com.app.carbohydratesgide.ui.categories.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.carbohydratesgide.databinding.ItemCategoriesBinding
import com.app.carbohydratesgide.databinding.ItemProductBinding
import com.app.carbohydratesgide.ui.categories.adapter.CategoriesAdapter
import com.app.carbohydratesgide.ui.categories.adapter.CategoriesItem
import com.bumptech.glide.Glide

class ProductsAdapter (
    private var arr: ArrayList<ProductsItem>,
    private val onClick: (id: Int, isCheck: Boolean) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
    }


    override fun getItemCount() = arr.size

    class ViewHolder(
        private val binding: ItemProductBinding,
        private val onClick: (id: Int, isCheck: Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductsItem) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(item.image)
                    .into(image)

                textName.text = item.name
                textProteins.text = "Белки: ${item.proteins}"
                textFats.text = "Жиры: ${item.fats}"
                textCarbohydrates.text = "Углеводы: ${item.carbohydrates}"

                checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        onClick(item.id, true)
                    } else {
                        onClick(item.id, false)
                    }
                }

                itemView.setOnClickListener {
                    checkBox.isChecked = !checkBox.isChecked
                }
            }
        }

    }
}