package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import ru.skillbranch.devintensive.R

// JvmOverloads создает несколько конструкторов для параметров в главном конструкторе
class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defaultAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defaultAttr) {
    companion object {
        private const val DEFAULT_ASPECT_RATIO = 1.78f
    }

    private var aspectRatio = DEFAULT_ASPECT_RATIO

    init {
        if (attrs != null) {
            Log.d("M_AspectRatioImageView","init block")
            // считываем атрибуты
            val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
            //получаем значение
            aspectRatio = a.getFloat(
                R.styleable.AspectRatioImageView_aspectRatio,
                DEFAULT_ASPECT_RATIO
            )
            a.recycle() // высвободить ресурсы
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeight = (measuredWidth / aspectRatio).toInt()
        setMeasuredDimension(measuredWidth, newHeight)
    }
}