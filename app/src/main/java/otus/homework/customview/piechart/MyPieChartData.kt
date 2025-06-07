package otus.homework.customview.piechart

import androidx.annotation.ColorRes
import androidx.annotation.FloatRange

data class MyPieChartData(
    val category: String,
    @ColorRes val color: Int,
    @FloatRange(from = 0.0, to = 360.0) val size: Float
)