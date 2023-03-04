package com.moudy.alshafie.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.moudy.alshafie.Ui.Fragments.Levels.BeginnerFragment
import com.moudy.alshafie.Ui.Fragments.Levels.ExpertFragment
import com.moudy.alshafie.Ui.Fragments.Levels.NormalFragment

class LearningViewPagerAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return BeginnerFragment()
            }
            1 -> {
                return NormalFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return ExpertFragment()
            }
            else -> return BeginnerFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}