package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val CONCERT_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { it.update() }
    }

    private fun Item.update() {
        when (name) {
            AGED_BRIE -> {
                updateQuality(1)
            }
            CONCERT_BACKSTAGE_PASS -> {
                if (quality < 50) {
                    updateQuality(1)

                    if (sellIn < 11) {
                        updateQuality(1)
                    }

                    if (sellIn < 6) {
                        updateQuality(1)
                    }
                }
            }
            SULFURAS -> {
                quality -= 0

            }
            else -> {
                if (quality > 0) quality -= 1
            }
        }

        if (name != SULFURAS) {
            sellIn -= 1
        }

        if (sellIn < 0) {
            if (name != AGED_BRIE) {
                if (name != CONCERT_BACKSTAGE_PASS) {
                    if (quality > 0 && name != SULFURAS) {
                        quality -= 1
                    }
                } else {
                    quality = 0
                }
            } else {
                updateQuality(1)
            }
        }
    }

    private fun Item.updateQuality(change: Int) {
        if (quality < 50) {
            quality += change
        }
    }
}

