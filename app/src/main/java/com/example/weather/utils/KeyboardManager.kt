package com.example.weather.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

class KeyboardManager {

    companion object {

        fun hideKeyboard(context: Context?) {
            if (context == null) return
            hideKeyboard(context as Activity)
        }

        fun hideKeyboard(activity: Activity?) {
            if (activity == null) return
            val inputManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            val currentFocusedView: View? = activity.currentFocus
            if (currentFocusedView != null) {
                inputManager.hideSoftInputFromWindow(currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        fun showKeyboard(context: Context?) {
            if (context == null) return
            showKeyboard(context as Activity)
        }

        fun showKeyboard(activity: Activity?) {
            if (activity == null) return
            (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }
}