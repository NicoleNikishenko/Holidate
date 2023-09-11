package com.example.holidate.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout
import com.example.holidate.databinding.FavoriteBtnBinding


class FavoriteBtn @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding = FavoriteBtnBinding.inflate(LayoutInflater.from(context), this, true
    )

    private var isFavSelected = false
    private var listener: FavoriteBtnListener? = null

    interface FavoriteBtnListener {
        fun onAddToFavoriteClick()
        fun onRemoveFromFavoriteClick()
    }

    fun setIsFavorite(isFavorite: Boolean?) {
        isFavorite?.let {
            isFavSelected = isFavorite
            binding.favBtn.isSelected = isFavorite
        }

    }

    fun setClickListener(listener: FavoriteBtnListener) {
        this.listener = listener
        binding.favBtn.setOnClickListener {
            onClick()
        }
    }

    private fun onClick() {
        if (isFavSelected) {
            listener?.onRemoveFromFavoriteClick()
        } else {
            listener?.onAddToFavoriteClick()
        }
        animateView()
        updateUi()
    }

    private fun animateView() {
        val anim = ScaleAnimation(
            1f, 1.1f, // Start and end values for the X axis scaling
            1f, 1.1f, // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 0.5f
        ) // Pivot point of Y scaling
        anim.duration = 150
        anim.repeatMode = ScaleAnimation.REVERSE
        anim.interpolator = OvershootInterpolator()
        binding.favBtn.startAnimation(anim)
    }

    private fun updateUi() {
        isFavSelected = !isFavSelected
        binding.favBtn.isSelected = isFavSelected
    }
}