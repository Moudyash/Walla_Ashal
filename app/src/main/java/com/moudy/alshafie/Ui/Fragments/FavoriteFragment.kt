package com.moudy.alshafie.Ui.Fragments

import android.R

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moudy.alshafie.adapter.FavoriteAdapter
import com.moudy.alshafie.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
 private lateinit var binding:FragmentFavoriteBinding
    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
        favoriteRecyclerView=binding.favoriterv
        //favoriteAdapter = FavoriteAdapter()
        favoriteRecyclerView.adapter = favoriteAdapter
    }

  }





