package com.example.holidate.features.holidays

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.holidate.R
import com.example.holidate.databinding.FragmentHolidaysBinding
import com.example.holidate.model.Holiday
import com.example.holidate.room.createHolidayObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteHolidaysFragment : Fragment(R.layout.fragment_holidays) {

    private val viewModel by viewModel<HolidaysViewModel>()

    private var binding: FragmentHolidaysBinding? = null
    private var holidayAdapter: HolidayAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHolidaysBinding.bind(view)

        initUI()
        initObservers()
    }

    private fun initUI() {
        binding?.apply {
            holidaysFragFavoriteButton.isVisible = false
            holidaysFragEmptyLayout.text = "No favorite holidays yet...\n Come on, click on some hearts!"

            holidayAdapter = HolidayAdapter(getHolidayAdapterListener())
            holidaysFragHolidaysList.adapter = holidayAdapter

        }
    }

    private fun getHolidayAdapterListener(): HolidayAdapter.HolidayAdapterListener {
        return object : HolidayAdapter.HolidayAdapterListener {
            override fun onHolidayClick(holiday: Holiday) {
                val action =
                    HolidaysFragmentDirections.actionHolidaysFragmentToSingleHolidayFragment(holiday)
                findNavController().navigate(action)
            }

            override fun onAddToFavoriteClick(holiday: Holiday) {
                viewModel.addHolidayToFavorite(holiday)
            }

            override fun onRemoveFromFavoriteClick(holiday: Holiday) {
                viewModel.removeFromFavorite(holiday)
            }
        }
    }


    private fun initObservers() {
        viewModel.countryData.observe(viewLifecycleOwner){ countryData ->
            viewModel.getFavoriteHolidayList(countryData.countryCode).observe(viewLifecycleOwner){ holidays->
                binding?.apply {
                    holidaysFragEmptyLayout.isVisible = holidays.isNullOrEmpty()
                    holidaysFragHolidaysList.isVisible = holidays.isNotEmpty()
                }
                holidayAdapter?.submitList(holidays.map { holiday -> holiday.createHolidayObject() })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        holidayAdapter = null
    }
}