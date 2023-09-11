package com.example.holidate.features.holidays

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.holidate.databinding.HolidayItemBinding
import com.example.holidate.model.Holiday
import com.example.holidate.utils.parseDate
import com.example.holidate.views.FavoriteBtn

class HolidayAdapter(private val listener: HolidayAdapterListener) :
    ListAdapter<Holiday, RecyclerView.ViewHolder>(DiffCallback()) {

    interface HolidayAdapterListener {
        fun onHolidayClick(holiday: Holiday)
        fun onAddToFavoriteClick(holiday: Holiday)
        fun onRemoveFromFavoriteClick(holiday: Holiday)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            HolidayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holiday = getItem(position)
        (holder as ViewHolder).bind(holiday, listener)
    }

    class ViewHolder(private val binding: HolidayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holiday: Holiday, listener: HolidayAdapterListener) {
            binding.apply {
                holidayItemName.text = holiday.name
                holidayItemDate.text = holiday.date.parseDate()
                holidayItemFavoriteBtn.setIsFavorite(holiday.isFavorite)

                root.setOnClickListener {
                    listener.onHolidayClick(holiday)
                }

                holidayItemFavoriteBtn.setClickListener(object : FavoriteBtn.FavoriteBtnListener {
                    override fun onAddToFavoriteClick() {
                        holiday.isFavorite = true
                        listener.onAddToFavoriteClick(holiday)
                    }

                    override fun onRemoveFromFavoriteClick() {
                        holiday.isFavorite = false
                        listener.onRemoveFromFavoriteClick(holiday)
                    }

                })
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Holiday>() {

        override fun areItemsTheSame(oldItem: Holiday, newItem: Holiday): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Holiday, newItem: Holiday): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

