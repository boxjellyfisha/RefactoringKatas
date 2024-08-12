package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val CONCERT_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { it.update() }
    }

    private fun Item.update() {
        updateSellInDays()
        updateQuality()
    }

    private fun Item.updateSellInDays() {
        sellIn -= if (name == SULFURAS) 0 else 1
    }

    private fun Item.updateQuality() {
        updateQuality(
            when (name) {
                AGED_BRIE -> if (sellIn < 0) 2 else 1
                CONCERT_BACKSTAGE_PASS -> when {
                    sellIn < 0 -> -quality
                    sellIn < 5 -> 3
                    sellIn < 10 -> 2
                    else -> 1
                }

                SULFURAS -> 0
                else -> if (sellIn < 0) -2 else -1
            }
        )
    }

    private fun Item.updateQuality(change: Int) {
        if(change == 0) return
        quality = quality.plus(change).coerceIn(0, 50)
    }
}

