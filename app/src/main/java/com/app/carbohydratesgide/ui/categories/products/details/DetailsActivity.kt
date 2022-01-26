package com.app.carbohydratesgide.ui.categories.products.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.kirich1409.viewbindingdelegate.viewBinding
import com.app.carbohydratesgide.R
import com.app.carbohydratesgide.databinding.ActivityDetailsBinding
import com.app.carbohydratesgide.ui.categories.adapter.CategoriesInf

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {

    private val binding: ActivityDetailsBinding by viewBinding()
    var proteins = 0
    var fats = 0
    var carbohydrates = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val proteinsNorm = 91
        val fatsNorm = 112
        val carbohydratesNorm = 415

        proteins = 0
        fats = 0
        carbohydrates = 0

        val idCategories = intent.getIntExtra("idCategories", 0)
        val chooseArr = intent.getIntegerArrayListExtra("chooseArr")


        CategoriesInf.models[idCategories].product.forEachIndexed { index, productsItem ->
            val item = CategoriesInf.models[idCategories].product[index]
            if (chooseArr != null) {
                Log.d("fddfsfdsdsff", "fddfdf")
                if (index in chooseArr) {
                    proteins += item.proteins
                    fats += item.fats
                    carbohydrates += item.carbohydrates
                }
            }
        }

        with(binding) {
            textCarbohydratesOwn.text = "Углеводы: $carbohydrates"
            textFatsOwn.text = "Жиры: $fats"
            textProteinsOwn.text = "Белки: $proteins"

            textCarbohydratesNorm.text = "Углеводы: $carbohydratesNorm"
            textFatsNorm.text = "Жиры: $fatsNorm"
            textProteinsNorm.text = "Белки: $proteinsNorm"

            textCarbohydratesNeed.text = "Углеводы: ${getNeedValue(carbohydrates, carbohydratesNorm)}"
            textFatsNeed.text = "Жиры: ${getNeedValue(fats, fatsNorm)}"
            textProteinsNeed.text = "Белки: ${getNeedValue(proteins, proteinsNorm)}"
        }
    }

    private fun getNeedValue(value: Int, valueUntil: Int): String {
        val newValue = valueUntil - value

        return if (newValue > 0) {
            newValue.toString()
        } else {
            "0"
        }
    }

    override fun onPause() {
        super.onPause()

        proteins = 0
        fats = 0
        carbohydrates = 0
    }
}