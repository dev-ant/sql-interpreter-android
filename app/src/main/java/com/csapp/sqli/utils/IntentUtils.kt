package com.csapp.sqli.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

object IntentUtils {
    fun navigateTo(
        context: Context, destinationActivity: Class<*>, extras: Bundle? = null, flag: Int
    ) {
        val intent = Intent(context, destinationActivity)
        extras?.let { intent.putExtras(it) }
        intent.addFlags(flag)
        context.startActivity(intent)
    }
}