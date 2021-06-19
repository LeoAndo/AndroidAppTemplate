package com.example.androidapptemplate.features.webapi.trivia

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidapptemplate.R
import com.example.androidapptemplate.features.webapi.trivia.history.TriviaHistoryFragment
import com.example.androidapptemplate.features.webapi.trivia.random.TriviaRandomFragment
import com.example.androidapptemplate.features.webapi.trivia.select.TriviaSelectFragment

class TriviaPagerAdapter(fragment: Fragment, private val context: Context) :
    FragmentStateAdapter(fragment) {

    fun getPageTitle(position: Int): CharSequence = context.getString(
        when (TabType.values()[position]) {
            TabType.SELECT -> R.string.trivia_select
            TabType.RANDOM -> R.string.trivia_random
            TabType.HISTORY -> R.string.trivia_history
        }
    )

    enum class TabType { SELECT, RANDOM, HISTORY }

    override fun getItemCount(): Int = TabType.values().size

    override fun createFragment(position: Int): Fragment {
        return when (TabType.values()[position]) {
            TabType.SELECT -> TriviaSelectFragment()
            TabType.RANDOM -> TriviaRandomFragment()
            TabType.HISTORY -> TriviaHistoryFragment()
        }
    }
}