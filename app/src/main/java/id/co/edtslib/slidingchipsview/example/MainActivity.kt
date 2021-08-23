package id.co.edtslib.slidingchipsview.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.co.edtslib.slidingchipsview.SlidingChipsDelegate
import id.co.edtslib.slidingchipsview.SlidingChipsView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf("Abah", "Hezbi", "Ade", "Robert", "Jovan", "Ucup")

        val chips = findViewById<SlidingChipsView<String>>(R.id.chips)
        chips.delegate = object : SlidingChipsDelegate<String> {
            override fun onSelected(item: String, position: Int) {
                Toast.makeText(this@MainActivity, item, Toast.LENGTH_SHORT).show()
            }

        }
        chips.items = list

    }
}