package com.app.carbohydratesgide.back

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class SharedPrefs(context: Context) {
    private var myPrefs: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        myPrefs = context.getSharedPreferences("file", Context.MODE_PRIVATE)
        editor = myPrefs?.edit()
    }

    fun read(): String? {
        return myPrefs!!.getString("key", "def")
    }

    fun write(value: String) {
        editor!!.putString("key", value)
        editor!!.apply()
    }
}