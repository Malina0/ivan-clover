package com.app.carbohydratesgide.back

import android.util.Base64

object B64Decoder {
    fun decode(s: String): String{
        return String(Base64.decode(s, Base64.NO_WRAP))
    }
}