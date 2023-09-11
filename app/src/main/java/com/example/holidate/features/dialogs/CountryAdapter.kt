package com.example.holidate.features.dialogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.holidate.databinding.CountryItemBinding
import com.example.holidate.model.Country

class CountryAdapter(private val listener: CountryAdapterListener) :
    ListAdapter<Country, RecyclerView.ViewHolder>(DiffCallback()) {

    interface CountryAdapterListener {
        fun onCountrySelect(country: Country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country = getItem(position)
        (holder as ViewHolder).bind(country, listener)
    }

    class ViewHolder(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country, listener: CountryAdapterListener) {
            binding.apply {

                countryItemName.text = country.name

                root.setOnClickListener{
                    listener.onCountrySelect(country)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.countryCode == newItem.countryCode
        }
    }
}

