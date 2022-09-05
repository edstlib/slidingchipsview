package id.co.edtslib.slidingchipsview.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.co.edtslib.slidingchipsview.GroupChipsView
import id.co.edtslib.slidingchipsview.SlidingChipsDelegate
import id.co.edtslib.slidingchipsview.SlidingChipsView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf(Name("Abah"),
            Name("Hezbi"),
            Name("Ade"),
            Name("Robert"),
            Name("Jovan"),
            Name("Ucup"))

        val chips = findViewById<SlidingChipsView<Name>>(R.id.chips)
        chips.firstSelected = true
        chips.delegate = object : SlidingChipsDelegate<Name> {
            override fun onSelected(item: Name, position: Int) {
                Toast.makeText(this@MainActivity, item.toString(), Toast.LENGTH_SHORT).show()
            }

        }
        chips.items = list
        chips.selectedItem = Name("Ade")

    }
}