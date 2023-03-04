package com.moudy.alshafie.Ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.moudy.alshafie.Data.HomeRV
import com.moudy.alshafie.R
import com.moudy.alshafie.adapter.adapter
import com.moudy.alshafie.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val args = arguments
        val user = args?.getString("username")
        binding.tvusername.text = user
        val rvitems = listOf(
            HomeRV("voice assistant will help you to learn new words in English",R.drawable.ic_illustration),
            HomeRV("voice assistant will help you to learn new words in English",R.drawable.ic_illustration),
            HomeRV("voice assistant will help you to learn new words in English",R.drawable.ic_illustration)
        )

        val data = ArrayList<HomeRV>()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homerv.layoutManager = layoutManager

        val adapter = adapter(data)

        binding.homerv.adapter = adapter(rvitems)
        return binding.root

    }

}



