package com.stockbit.app.utils

import android.annotation.SuppressLint
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.app.R


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun RecyclerView.addItemDecoration(){
    addItemDecoration(
        DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
    )
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.setupPasswordToggle(){
    setOnTouchListener { _, event ->
        val DRAWABLE_RIGHT = 2
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= (right - compoundDrawables[DRAWABLE_RIGHT].bounds.width()) - 50) {
                // your action here
                if (transformationMethod != null) {
                    transformationMethod = null
                    requestFocus()
                    text?.length?.let { this.setSelection(it) }
                    setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        ContextCompat.getDrawable(this.context, R.drawable.ic_outline_visibility),
                        null
                    )
                } else {
                    transformationMethod = PasswordTransformationMethod()
                    requestFocus()
                    text?.length?.let { setSelection(it) }
                    setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        ContextCompat.getDrawable(this.context, R.drawable.ic_outline_visibility_off),
                        null
                    )
                }
                return@setOnTouchListener true
            }
        }
        return@setOnTouchListener false
    }
}
