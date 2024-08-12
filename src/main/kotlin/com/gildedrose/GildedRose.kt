package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val CONCERT_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"
const val CONJURESD = "Conjuresd"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items = items.map { it.update() }
    }

    private fun Item.update() = updateSellInDays().updateQuality()

    private fun Item.updateSellInDays(): Item {
        return copy(sellIn = sellIn - if (name == SULFURAS) 0 else 1)
    }

    private fun Item.updateQuality(): Item {
        val change = when (name) {
            AGED_BRIE -> if (sellIn < 0) 2 else 1
            CONCERT_BACKSTAGE_PASS -> when {
                sellIn < 0 -> -quality
                sellIn < 5 -> 3
                sellIn < 10 -> 2
                else -> 1
            }

            CONJURESD -> if (sellIn < 0) -4 else -2

            SULFURAS -> 0
            else -> if (sellIn < 0) -2 else -1
        }
        return copy(quality = if (change == 0)
            quality
        else {
            quality.plus(change).coerceIn(0, 50)
        })
    }
}

