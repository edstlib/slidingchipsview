package id.co.edtslib.slidingchipsview

import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder
import id.co.edtslib.slidingchipsview.databinding.AdapterChipBinding

class ChipHolder<T>(private val binding: AdapterChipBinding, textColor: Int,
                    textPadding: Float, chipBackgroundColor: Int, strokeColor: Int, textStyle: Int):
    BaseViewHolder<T>(binding) {
    init {
        binding.chip.setTextColor(
            ContextCompat.getColorStateList(
                itemView.context,
                textColor
            )
        )

        binding.chip.chipStartPadding = textPadding
        binding.chip.chipEndPadding = textPadding

        binding.chip.chipBackgroundColor = ContextCompat.getColorStateList(
            itemView.context,
            chipBackgroundColor
        )

        if (strokeColor != 0) {
            binding.chip.chipStrokeColor = ContextCompat.getColorStateList(
                itemView.context,
                strokeColor
            )
            binding.chip.chipStrokeWidth = itemView.context.resources.getDimensionPixelSize(R.dimen.chip_dimen_1dp).toFloat()
        }

        if (textStyle > 0) {
            TextViewCompat.setTextAppearance(binding.chip, textStyle)
        }

    }
    override fun setData(
        list: MutableList<T>,
        position: Int,
        delegate: BaseRecyclerViewAdapterDelegate<T>?
    ) {
        val item = list[position]
        if (item is RecyclerData<*>) {
            binding.chip.text = item.data.toString()
            itemView.isSelected = item.selected

            binding.chip.setOnClickListener {
                delegate?.onClick(item, position, this)
            }

        }
    }
}