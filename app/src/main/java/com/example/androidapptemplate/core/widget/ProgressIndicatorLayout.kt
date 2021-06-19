package com.example.androidapptemplate.core.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.androidapptemplate.R


internal class ProgressIndicatorLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.view_progress_indicator, this)
        // 初期値は非表示
        hide()
        // android:clickable="true" にすることで裏側のコンテンツに触れさせない
        isClickable = true

        val typedValue = TypedValue()
        val isFoundedAttribute =
            context.theme.resolveAttribute(R.attr.scrimBackground, typedValue, true)
        if (isFoundedAttribute) {
            // foreground = AppCompatResources.getDrawable(context, typedValue.resourceId)
            setBackgroundResource(typedValue.resourceId)
        }
    }

    fun show() {
        this.isVisible = true
    }

    fun hide() {
        this.isVisible = false
    }
}