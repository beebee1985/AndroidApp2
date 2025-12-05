package com.beebee1985.androidapp2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beebee1985.androidapp2.models.MenuItem

/**
 * Main screen showing the Tim Hortons menu.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler: RecyclerView = findViewById(R.id.recyclerMenu)
        recycler.layoutManager = LinearLayoutManager(this)

        val sample = listOf(
            MenuItem(1, "Original Blend Coffee", 2.49, "Freshly brewed coffee"),
            MenuItem(2, "Iced Cappuccino", 3.99, "Creamy iced capp"),
            MenuItem(3, "Bagel", 1.99, "Toasted bagel with spread"),
            MenuItem(4, "Donut", 1.29, "Sweet classic donut")
        )

        recycler.adapter = MenuAdapter(sample) { item ->
            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra("id", item.id)
            intent.putExtra("name", item.name)
            intent.putExtra("price", item.price)
            startActivity(intent)
        }

        findViewById<View>(R.id.fabCart).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}
