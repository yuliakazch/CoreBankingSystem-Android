package com.gmail.yuliakazachok.corebanking.libraries.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

fun Fragment.closeKeyboard(binding: ViewBinding) {
    val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.hideSoftInputFromWindow(binding.root.applicationWindowToken, 0)
}

fun Fragment.openKeyboard() {
    val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}