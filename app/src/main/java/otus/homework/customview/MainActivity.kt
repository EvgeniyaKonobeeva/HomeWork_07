package otus.homework.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import otus.homework.customview.piechart.MyPieChart
import otus.homework.customview.piechart.MyPieChartData
import otus.homework.operators.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chart = findViewById<MyPieChart>(R.id.my_pie_chart)
        chart.setItems(
            listOf(
                MyPieChartData(
                    category = "Products",
                    color = R.color.teal_200,
                    size = 135f
                ),
                MyPieChartData(
                    category = "Clothes",
                    color = R.color.black,
                    size = 45f
                ),
                MyPieChartData(
                    category = "Renta",
                    color = R.color.purple_200,
                    size = 70f
                ),
                MyPieChartData(
                    category = "Bla",
                    color = R.color.purple_700,
                    size = 110f
                ),
            )
        )
    }
}