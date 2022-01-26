package com.app.carbohydratesgide.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun Context.openAct(activity: Activity) {
    startActivity(Intent(this, activity::class.java))
}