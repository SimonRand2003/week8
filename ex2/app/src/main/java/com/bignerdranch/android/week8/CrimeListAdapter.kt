package com.bignerdranch.android.week8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.week8.databinding.ListItemPoliceBinding
import com.bignerdranch.android.week8.databinding.ListItemCrimeBinding

import android.text.format.DateFormat

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title

        // Format the date using android.text.format.DateFormat
        val formattedDate = DateFormat.format("MMMM dd, yyyy", crime.date)

        // Set the formatted date to the crimeDate TextView
        binding.crimeDate.text = formattedDate.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}

class PoliceCrimeHolder(private val binding: ListItemPoliceBinding) : RecyclerView.ViewHolder(binding.root) {
    // Implement the logic to bind data for more serious crimes
    // For example:
    fun bind(crime: Crime) {
        binding.policeTitle.text= crime.title
        binding.policeDate.text = crime.date.toString()

    }
}

class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Define view types
    private val VIEW_TYPE_NORMAL = 1
    private val VIEW_TYPE_POLICE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
            VIEW_TYPE_POLICE -> {
                // Create a different ViewHolder for more serious crimes
                // Use a layout with a streamlined interface containing a button to contact the police
                // You need to define the layout and create a corresponding ViewHolder class
                // For example:
                val binding = ListItemPoliceBinding.inflate(inflater, parent, false)
                PoliceCrimeHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_NORMAL -> {
                val crimeHolder = holder as CrimeHolder
                crimeHolder.bind(crimes[position])
            }
            VIEW_TYPE_POLICE -> {
                val policeCrimeHolder = holder as PoliceCrimeHolder
                policeCrimeHolder.bind(crimes[position])
            }
        }
    }


    override fun getItemCount(): Int = crimes.size

    override fun getItemViewType(position: Int): Int {
        // Return the view type based on the requiresPolice property
        return if (crimes[position].requiresPolice) VIEW_TYPE_POLICE else VIEW_TYPE_NORMAL
    }
}


