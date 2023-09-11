package com.example.holidate.features.holidays

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.holidate.R
import com.example.holidate.databinding.FragmentHolidaysBinding
import com.example.holidate.model.Holiday
import com.example.holidate.utils.parseDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class HolidaysFragment : Fragment(R.layout.fragment_holidays) {

    private val viewModel by viewModel<HolidaysViewModel>()

    private var binding: FragmentHolidaysBinding? = null
    private var holidayAdapter: HolidayAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHolidaysBinding.bind(view)


        initUI()
        initObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        binding?.apply {
            holidaysFragEmptyLayout.isVisible = false
            holidaysFragEmptyLayout.text = "Oh no! There aren't any holidays in your country\n Try choosing from our countries list"
            holidaysFragFavoriteButton.setOnClickListener {
                val action =
                    HolidaysFragmentDirections.actionHolidaysFragmentToFavoriteHolidaysFragment()
                findNavController().navigate(action)
            }

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
        viewModel.countryData.observe(viewLifecycleOwner){
            viewModel.getHolidayList(it.countryCode)
        }
        viewModel.holidaysList.observe(viewLifecycleOwner) { holidays->
            holidays?.let{
                holidayAdapter?.submitList(holidays)
                binding?.apply {
                    holidaysFragEmptyLayout.isVisible = holidays.isEmpty()
                    holidaysFragHolidaysList.isVisible = holidays.isNotEmpty()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        holidayAdapter = null
        viewModel.clearData()
    }
}