package com.moudy.alshafie.Ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.moudy.alshafie.adapter.LearningViewPagerAdapter
import com.moudy.alshafie.databinding.FragmentLearningBinding


class LearningFragment : Fragment() {

 private lateinit var binding:FragmentLearningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLearningBinding.inflate(inflater, container, false)
        binding.tabLayout.addTab(binding.tabLayout!!.newTab().setText("Beginner"))
        binding.tabLayout.addTab(binding.tabLayout!!.newTab().setText("Normal"))
        binding.tabLayout.addTab(binding.tabLayout!!.newTab().setText("Expert"))
        binding.tabLayout.addTab(binding.tabLayout!!.newTab().setText("Colors"))

        binding.tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LearningViewPagerAdapter(requireContext(), childFragmentManager, binding.tabLayout!!.tabCount)
        binding.viewpager!!.adapter = adapter
        binding.viewpager.setPageTransformer(false)  { page, position ->
            val ROTATION = -15f
                val width = page.width.toFloat()
                val height = page.height.toFloat()
                val rotation = ROTATION * position * -1.25f
                page.pivotX = width * 0.5f
                page.pivotY = height
                page.rotation = rotation
            }

        binding.viewpager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        binding.tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewpager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        return binding.root
    }


}