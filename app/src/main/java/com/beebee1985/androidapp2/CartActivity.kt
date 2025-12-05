package com.beebee1985.androidapp2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beebee1985.androidapp2.models.Cart
import com.beebee1985.androidapp2.models.MenuItem

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val recycler: RecyclerView = findViewById(R.id.recyclerCart)
        recycler.layoutManager = LinearLayoutManager(this)

        val items = Cart.list().toList().map { (item, qty) -> Pair(item, qty) }

        recycler.adapter = object : RecyclerView.Adapter<CartViewHolder>() {
            override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): CartViewHolder {
                val v = android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
                return CartViewHolder(v)
            }

            override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
                val (item, qty) = items[position]
                holder.name.text = "${item.name} x$qty"
                holder.price.text = "$${"%.2f".format(item.price * qty)}"
            }

            override fun getItemCount(): Int = items.size
        }

        val tvTotal: TextView = findViewById(R.id.cartTotal)
        tvTotal.text = "Total: $${"%.2f".format(Cart.total())}"

        findViewById<Button>(R.id.btnPlace).setOnClickListener {
            // Simple placeholder behaviour for demo
            Cart.clear()
            finish()
        }
    }
}

class CartViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.itemName)
    val price: TextView = view.findViewById(R.id.itemPrice)
}
