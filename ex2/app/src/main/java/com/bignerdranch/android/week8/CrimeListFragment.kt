package com.bignerdranch.android.week8

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.week8.databinding.FragmentCrimeListBinding

class CrimeListFragment : Fragment() {

    private val crimeListViewModel:CrimeListViewModel by viewModels()

    private var _binding: FragmentCrimeListBinding? = null

    private val binding
        get() = checkNotNull(_binding){
            "cannot cause binding is null"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(inflater,container,false)
        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        val crimes = crimeListViewModel.crimes
        val adapter = CrimeListAdapter(crimes)
        binding.crimeRecyclerView.adapter = adapter

        return binding.root
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding= null
    }

}