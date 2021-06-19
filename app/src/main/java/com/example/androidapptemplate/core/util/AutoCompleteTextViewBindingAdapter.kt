package com.example.androidapptemplate.core.util

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import com.example.androidapptemplate.R

object AutoCompleteTextViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("adapterValues")
    fun <T> AutoCompleteTextView.setAdapterValues(objects: List<T>?) {
        if (objects.isNullOrEmpty()) return
        val adapter: ArrayAdapter<T> = ArrayAdapter(context, R.layout.dropdown_menu, objects)
        setAdapter(adapter)
    }

    /**
     * 先頭の値に空データを持つスピナーの設定.
     * @param objects スピナーアイテム [String]
     */
    @JvmStatic
    @BindingAdapter("adapterWithEmptyValues")
    fun <T> AutoCompleteTextView.setAdapterWithEmptyValues(objects: List<String>?) {
        if (objects.isNullOrEmpty()) return
        val values = mutableListOf<String>()
        values.add("")
        values.addAll(objects)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(context, R.layout.dropdown_menu, values)
        setAdapter(adapter)
    }
}