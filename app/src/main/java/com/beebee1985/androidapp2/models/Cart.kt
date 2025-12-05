package com.beebee1985.androidapp2.models

/**
 * Simple in-memory cart singleton for demo purposes.
 */
object Cart {
    private val items = mutableMapOf<MenuItem, Int>()

    fun add(item: MenuItem, qty: Int) {
        if (qty <= 0) return
        items[item] = (items[item] ?: 0) + qty
    }

    fun list(): Map<MenuItem, Int> = items.toMap()

    fun clear() = items.clear()

    fun total(): Double = items.entries.sumOf { it.key.price * it.value }
}
