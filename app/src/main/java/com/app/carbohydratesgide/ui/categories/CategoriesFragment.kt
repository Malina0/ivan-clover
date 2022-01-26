package com.app.carbohydratesgide.ui.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.app.carbohydratesgide.R
import com.app.carbohydratesgide.databinding.ActivityCategoriesBinding
import com.app.carbohydratesgide.ui.categories.adapter.CategoriesAdapter
import com.app.carbohydratesgide.ui.categories.adapter.CategoriesInf
import com.app.carbohydratesgide.ui.categories.adapter.CategoriesItem
import com.app.carbohydratesgide.ui.categories.products.ProductsActivity

class CategoriesFragment : Fragment(R.layout.activity_categories) {

    private val binding: ActivityCategoriesBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<CategoriesItem>()

        CategoriesInf.models.forEach {
            list.add(it)
        }

        binding.recycler.adapter = CategoriesAdapter(list, onClick)

    }

    private val onClick: (id: Int) -> Unit = {
        Intent(requireContext(), ProductsActivity::class.java).apply {
            putExtra("idCategories", it)
        }.also {
            startActivity(it)
        }
    }
}