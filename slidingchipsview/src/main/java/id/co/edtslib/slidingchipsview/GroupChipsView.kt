package id.co.edtslib.slidingchipsview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.widget.TextViewCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class GroupChipsView<T> : ChipGroup {
    var delegate: SlidingChipsDelegate<T>? = null

    private var textColor = R.color.chip_text_color
    private var strokeColor = 0
    private var chipBackgroundColor = R.color.chip_background_color
    private var textPadding = 0f
    private var textStyle = 0

    var items: MutableList<T> = mutableListOf()
        set(value) {
            field = value

            removeAllViews()
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
                if (strokeColor != 0) {
                    chip.chipStrokeColor = ContextCompat.getColorStateList(
                        context,
                        strokeColor
                    )
                    chip.chipStrokeWidth = context.resources.getDimensionPixelSize(R.dimen.chip_dimen_1dp).toFloat()
                }
                chip.setOnClickListener {
                    repeat (childCount) {idx ->
                        getChildAt(idx).isSelected = false
                    }
                    chip.isSelected = true

                    val left = it.left - scrollX
                    val space = resources.getDimensionPixelSize(R.dimen.chip_dimen_16dp)

                    // if partial show on right
                    if (it.width + left + space > width) {
                        val diff = width - it.width - left - space
                        val scrollX = scrollX - diff
                        scrollTo(scrollX, 0)
                    }

                    delegate?.onSelected(item, i)
                }

                chip.isSelected = selectionIndex == i

                addView(chip)
            }
        }

    var selectionIndex = 0
        set(value) {
            field = value

            repeat(childCount) {
                getChildAt(it).isSelected = it == value
            }
        }

    constructor(context: Context) : super(context) {
        init(null)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SlidingChipsView,
                0, 0
            )

            val startEndSpace = a.getDimension(R.styleable.SlidingChipsView_slideChipMargin,
                resources.getDimensionPixelSize(R.dimen.chip_dimen_16dp).toFloat())

            textColor = a.getResourceId(R.styleable.SlidingChipsView_slideChipTextColor,
                R.color.chip_text_color)

            strokeColor = a.getResourceId(R.styleable.SlidingChipsView_slideChipStrokeColor,
                0)

            textPadding = a.getDimension(R.styleable.SlidingChipsView_slideChipTextPadding,
                resources.getDimensionPixelSize(R.dimen.chip_dimen_20dp).toFloat())

            chipBackgroundColor = a.getResourceId(R.styleable.SlidingChipsView_slideChipBackground,
                R.color.chip_background_color)

            textStyle = a.getResourceId(R.styleable.SlidingChipsView_slideChipTextStyle, 0)


            //isSingleLine = true
            isSingleSelection = true


            setPadding(startEndSpace.toInt(), 0, startEndSpace.toInt(), 0)

            /*
            val layoutParams = chipGroup.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

            chipGroup.layoutParams = layoutParams*/

            a.recycle()
        }
    }
}