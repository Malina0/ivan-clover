package com.app.carbohydratesgide.ui.categories.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.kirich1409.viewbindingdelegate.viewBinding
import com.app.carbohydratesgide.R
import com.app.carbohydratesgide.databinding.ActivityProductsBinding
import com.app.carbohydratesgide.ui.categories.adapter.CategoriesInf
import com.app.carbohydratesgide.ui.categories.products.adapter.ProductsAdapter
import com.app.carbohydratesgide.ui.categories.products.adapter.ProductsItem
import com.app.carbohydratesgide.ui.categories.products.details.DetailsActivity

class ProductsActivity : AppCompatActivity(R.layout.activity_products) {

    private val binding: ActivityProductsBinding by viewBinding()

    private var chooseArr = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val idCategories = intent.getIntExtra("idCategories", 0)

        val list = ArrayList<ProductsItem>()

        CategoriesInf.models[idCategories].product.forEachIndexed { index, productsItem ->
            val item = CategoriesInf.models[idCategories].product[index]
            list.add(
                ProductsItem(
                    item.name,
                    item.image,
                    item.proteins,
                    item.fats,
                    item.carbohydrates,
                    index
                )
            )
        }

        binding.recycler.adapter = ProductsAdapter(list, onClick)
        binding.buttonCalculate.setOnClickListener {
            Log.d("chooseArr", chooseArr.toString())
            Intent(this, DetailsActivity::class.java).apply {
                putExtra("idCategories", idCategories)
                putExtra("chooseArr", chooseArr)
            }.also {
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        binding.buttonCalculate.show()
        binding.buttonCalculate.hide()
    }
    private val onClick: (id: Int, isCheck: Boolean) -> Unit = { id, isCheck ->
        if (isCheck) {
            chooseArr.add(id)
        } else {
            chooseArr.remove(id)
        }
        if (chooseArr.isEmpty()) {
            binding.buttonCalculate.hide()
        } else {
            binding.buttonCalculate.show()
        }
    }
}