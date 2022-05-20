package id.co.edtslib.slidingchipsview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.widget.TextViewCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class SlidingChipsView<T> : HorizontalScrollView {
    var delegate: SlidingChipsDelegate<T>? = null

    private lateinit var chipGroup: ChipGroup
    private var textColor = R.color.chip_text_color
    private var chipBackgroundColor = R.color.chip_background_color
    private var textPadding = 0f
    private var textStyle = 0
    var autoScroll = false

    var items: MutableList<T> = mutableListOf()
        set(value) {
            field = value

            for ((i, item) in items.withIndex()) {

                val chip = Chip(context)
                chip.text = item.toString()

                if (textStyle > 0) {
                    TextViewCompat.setTextAppearance(chip, textStyle)
                }


                chip.tag = item
                chip.setTextColor(
                    ContextCompat.getColorStateList(
                        context,
                        textColor
                    )
                )
                chip.chipStartPadding = textPadding
                chip.chipEndPadding = textPadding

                chip.chipBackgroundColor = ContextCompat.getColorStateList(
                    context,
                            chipBackgroundColor
                )
                chip.setOnClickListener {
                    repeat (chipGroup.childCount) {idx ->
                        chipGroup[idx].isSelected = false
                    }
                    chip.isSelected = true

                    if (autoScroll) {
                        val left = it.left - scrollX
                        val space = resources.getDimensionPixelSize(R.dimen.dimen_16dp)

                        // if partial show on right
                        if (it.width + left + space > width) {
                            val diff = width - it.width - left - space
                            val scrollX = scrollX - diff
                            scrollTo(scrollX, 0)
                        }
                    }

                    delegate?.onSelected(item, i)
                }

                chip.isSelected = selectionIndex == i

                chipGroup.addView(chip)
            }
        }

    var selectionIndex = 0
        set(value) {
            field = value

            repeat(chipGroup.childCount) {
                chipGroup.getChildAt(it).isSelected = it == value
            }
        }

    constructor(context: Context?) : super(context) {
        init(null)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false

        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SlidingChipsView,
                0, 0
            )

            val startEndSpace = a.getDimension(R.styleable.SlidingChipsView_startEndSpace,
                resources.getDimensionPixelSize(R.dimen.dimen_16dp).toFloat())

            textColor = a.getResourceId(R.styleable.SlidingChipsView_textColor,
                R.color.chip_text_color)

            textPadding = a.getDimension(R.styleable.SlidingChipsView_textPadding,
                resources.getDimensionPixelSize(R.dimen.dimen_20dp).toFloat())

            chipBackgroundColor = a.getResourceId(R.styleable.SlidingChipsView_chipBackground,
                R.color.chip_background_color)

            textStyle = a.getResourceId(R.styleable.SlidingChipsView_textStyle, 0)

            chipGroup = ChipGroup(context)
            addView(chipGroup)

            chipGroup.isSingleLine = true
            chipGroup.isSingleSelection = true
            chipGroup.setPadding(startEndSpace.toInt(), 0, startEndSpace.toInt(), 0)

            val layoutParams = chipGroup.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

            chipGroup.layoutParams = layoutParams

            a.recycle()
        }
    }
}