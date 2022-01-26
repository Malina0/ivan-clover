package com.app.carbohydratesgide.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.app.carbohydratesgide.R
import com.app.carbohydratesgide.databinding.ActivityBaseBinding
import com.app.carbohydratesgide.ui.categories.CategoriesFragment
import com.app.carbohydratesgide.ui.water.WaterFragment

class BaseActivity : AppCompatActivity(R.layout.activity_base) {

    private val binding: ActivityBaseBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, CategoriesFragment()).commit()
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.id_categories -> replaceFragment(CategoriesFragment())
                R.id.id_water -> replaceFragment(WaterFragment())
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}