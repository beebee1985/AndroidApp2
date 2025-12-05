package com.beebee1985.androidapp2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.beebee1985.androidapp2.models.Cart
import com.beebee1985.androidapp2.models.MenuItem

/**
 * Shows details for a menu item and allows adding quantity to cart.
 */
class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val name = intent.getStringExtra("name") ?: "Item"
        val price = intent.getDoubleExtra("price", 0.0)
        val id = intent.getIntExtra("id", 0)

        val tvName: TextView = findViewById(R.id.detailName)
        val tvPrice: TextView = findViewById(R.id.detailPrice)
        val tvQty: TextView = findViewById(R.id.detailQty)

        tvName.text = name
        tvPrice.text = "$${"%.2f".format(price)}"
        var qty = 1
        tvQty.text = qty.toString()

        findViewById<Button>(R.id.btnMinus).setOnClickListener {
            if (qty > 1) {
                qty -= 1
                tvQty.text = qty.toString()
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener {
            qty += 1
            tvQty.text = qty.toString()
        }

        findViewById<Button>(R.id.btnAddCart).setOnClickListener {
            val item = MenuItem(id, name, price)
            Cart.add(item, qty)
            finish()
        }
    }
}
