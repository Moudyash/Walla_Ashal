package com.moudy.alshafie.Ui.Fragments.Levels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.moudy.alshafie.Data.Beginner
import com.moudy.alshafie.Data.DataSets.WordsDataset
import com.moudy.alshafie.R
import com.moudy.alshafie.adapter.Beginneradapter
import com.moudy.alshafie.adapter.Conversationadapter
import com.moudy.alshafie.databinding.FragmentExpertBinding
import com.moudy.alshafie.databinding.FragmentNormalBinding


class ExpertFragment : Fragment() {
    private lateinit var binding: FragmentExpertBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpertBinding.inflate(inflater, container, false)



        val rvitems = mutableListOf<Beginner>()

        for (i in WordsDataset.ExpertAR.indices) {
            val item = WordsDataset.ExpertEN[i]
            val item2 = WordsDataset.ExpertAR[i]
            val item3 = WordsDataset.ExpertAR[i]
            rvitems.add(Beginner(item3,item2,item))
        }

        val data = ArrayList<Beginner>()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.expertrv.layoutManager = layoutManager

        val adapter = Beginneradapter(data)

        binding.expertrv.adapter = Conversationadapter(rvitems)







        return binding.root

    }

}


