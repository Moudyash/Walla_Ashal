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
import com.moudy.alshafie.databinding.FragmentNormalBinding


class NormalFragment : Fragment() {
    private lateinit var binding: FragmentNormalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNormalBinding.inflate(inflater, container, false)



        val rvitems = mutableListOf<Beginner>()

        for (i in WordsDataset.NormalAR_EN.indices) {
            val item = WordsDataset.NormalEN[i]
            val item2 = WordsDataset.NormalAR_EN[i]
            val item3 = WordsDataset.NormalAR[i]
            rvitems.add(Beginner(item3,item2,item))
        }

        val data = ArrayList<Beginner>()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.normalrv.layoutManager = layoutManager

        val adapter = Beginneradapter(data)

        binding.normalrv.adapter = Beginneradapter(rvitems)







        return binding.root

    }

}


