package com.app.carbohydratesgide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.app.carbohydratesgide.databinding.ActivityMainBinding
import com.app.carbohydratesgide.ui.BaseActivity
import com.app.carbohydratesgide.utils.openAct
import com.app.carbohydratesgide.utils.toast

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.buttonStart.setOnClickListener {

            openAct(BaseActivity())
        }
    }
}