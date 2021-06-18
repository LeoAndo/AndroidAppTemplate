package com.example.androidapptemplate.features.core

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import com.example.androidapptemplate.R

object AutoCompleteTextViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("adapterValues")
    fun <T> setAdapterValues(view: AutoCompleteTextView, objects: List<T>?) {
        if (objects.isNullOrEmpty()) return

        val adapter: ArrayAdapter<T> = ArrayAdapter(view.context, R.layout.dropdown_menu, objects)
        view.setAdapter(adapter)
    }

    /**
     * 先頭の値に空データを持つスピナーの設定.
     *
     * @param view [AutoCompleteTextView]
     * @param objects スピナーアイテム [String]
     */
    @JvmStatic
    @BindingAdapter("adapterWithEmptyValues")
    fun <T> setAdapterWithEmptyValues(view: AutoCompleteTextView, objects: List<String>?) {
        if (objects.isNullOrEmpty()) return
        val values = mutableListOf<String>()
        values.add("")
        values.addAll(objects)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(view.context, R.layout.dropdown_menu, values)
        view.setAdapter(adapter)
    }
}