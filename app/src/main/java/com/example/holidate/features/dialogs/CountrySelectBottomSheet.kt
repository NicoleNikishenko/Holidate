package com.example.holidate.features.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.holidate.R
import com.example.holidate.databinding.BottomSheetCountrySelectBinding
import com.example.holidate.features.holidays.HolidayAdapter
import com.example.holidate.features.holidays.HolidaysFragmentDirections
import com.example.holidate.features.holidays.HolidaysViewModel
import com.example.holidate.model.Country
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountrySelectBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_country_select) {

    private var binding: BottomSheetCountrySelectBinding? = null
    private val viewModel by viewModel<CountrySelectViewModel>()
    private var countryAdapter: CountryAdapter? = null

    companion object {
        fun newInstance(): CountrySelectBottomSheet {
            return CountrySelectBottomSheet()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BottomSheetCountrySelectBinding.bind(view)

        initUI()
        initObservers()
    }

    private fun initUI() {
        binding?.apply {
            countryAdapter = CountryAdapter(object: CountryAdapter.CountryAdapterListener{
                override fun onCountrySelect(country: Country) {
                    viewModel.updateCurrentCountry(country)
                    dismiss()
                }

            })
            countrySelectList.adapter = countryAdapter
        }
    }

    private fun initObservers() {
       viewModel.availableCountries.observe(viewLifecycleOwner){ countries->
           countries?.let{
               countryAdapter?.submitList(it)
           }
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        countryAdapter = null
    }


}