package otus.homework.customview.piechart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec.getSize
import androidx.annotation.Size
import kotlin.math.min

class MyPieChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val chartStrokeWidth = 100f

    private var items: List<MyPieChartData> = emptyList()
    private var painters: List<Paint> = emptyList()
    private var chartSize: Int = 0
    private var chartOval: RectF = RectF()


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val width = w - paddingLeft - paddingRight
        val height = h - paddingTop - paddingBottom
        chartSize = min(width, height)
        chartOval = buildChartOval(chartSize, chartStrokeWidth)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = getSize(widthMeasureSpec)
        val h = resolveSizeAndState(w, heightMeasureSpec, 0)
        val size = min(w, h)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        if (items.isEmpty() || chartSize == 0) return

        canvas.apply {
            var startAngle = 0f
            items.forEachIndexed { idx, item ->
                drawArc(chartOval, startAngle, item.size, false, painters[idx])
                startAngle += item.size
            }
        }

    }

    fun setItems(@Size(max = 10) items: List<MyPieChartData>) {
        this.items = items
        preparePainters(this.items)
    }

    private fun preparePainters(@Size(max = 10) items: List<MyPieChartData>) {
        painters = buildList {
            items.forEach { item ->
                add(
                    Paint().apply {
                        color = context.getColor(item.color)
                        strokeWidth = chartStrokeWidth
                        style = Paint.Style.STROKE
                    }
                )
            }
        }
    }

    private fun buildChartOval(size: Int, strokeWidth: Float): RectF {
        val halfStroke = strokeWidth / 2
        val l = paddingStart + halfStroke
        val r = paddingStart + size - halfStroke
        val t = paddingTop + halfStroke
        val b = paddingTop + size - halfStroke
        return RectF(l, t, r, b)
    }
}