package id.co.edtslib.slidingchipsview

import android.view.LayoutInflater
import android.view.ViewGroup
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapter
import id.co.edtslib.slidingchipsview.databinding.AdapterChipBinding

class ChipAdapter<T>(private val textColor: Int, private val textPadding: Float,
                     private val chipBackgroundColor: Int, private val strokeColor: Int,
                     private val textStyle: Int): BaseRecyclerViewAdapter<AdapterChipBinding, T>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> AdapterChipBinding
        get() = AdapterChipBinding::inflate

    override fun createHolder() = ChipHolder<T>(binding, textColor, textPadding,
        chipBackgroundColor, strokeColor, textStyle)

}