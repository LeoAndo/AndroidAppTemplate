package com.example.androidapptemplate.core.app

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import com.example.androidapptemplate.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// https://material.io/components/sheets-bottom
internal open class AppBottomSheetDialogFragment : BottomSheetDialogFragment() {
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.ThemeOverlay_AndroidAppTemplate_BottomSheetDialog
        )
    }
}