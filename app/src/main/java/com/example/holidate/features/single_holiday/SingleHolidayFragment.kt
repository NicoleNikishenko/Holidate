package com.example.holidate.features.single_holiday

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.holidate.R
import com.example.holidate.databinding.FragmentSingleHolidayBinding
import com.example.holidate.model.Holiday
import com.example.holidate.utils.parseDate


class SingleHolidayFragment : Fragment(R.layout.fragment_single_holiday) {

    private var binding: FragmentSingleHolidayBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingleHolidayBinding.bind(view)
        val args: SingleHolidayFragmentArgs by navArgs()
        val holiday = args.holiday

        initUI(holiday)
    }

    private fun initUI(holiday:Holiday) {
        binding?.apply {
            holidayName.setText(holiday.name)
            holidayLocalName.setText(holiday.localName)
            holidayDate.setText(holiday.date.parseDate())
            holidayCountryCode.setText(holiday.countryCode)
            holidayLaunchYear.setText(if (holiday.launchYear != null) holiday.launchYear.toString() else "")
            holidayCountries.setText(holiday.counties?.joinToString(", "))
            holidayTypes.setText(holiday.types.joinToString(", "))
            holidayIsFixed.isChecked =  holiday.fixed
            holidayIsGlobal.isChecked =  holiday.global
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}