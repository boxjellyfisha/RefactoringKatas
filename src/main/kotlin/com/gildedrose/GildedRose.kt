package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val CONCERT_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != AGED_BRIE && items[i].name != CONCERT_BACKSTAGE_PASS) {
                if (items[i].quality > 0 && items[i].name != SULFURAS) {
                    items[i].quality -= 1
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality += 1

                    if (items[i].name == CONCERT_BACKSTAGE_PASS) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality += 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality += 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != SULFURAS) {
                items[i].sellIn -= 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != AGED_BRIE) {
                    if (items[i].name != CONCERT_BACKSTAGE_PASS) {
                        if (items[i].quality > 0 && items[i].name != SULFURAS) {
                            items[i].quality -= 1
                        }
                    } else {
                        items[i].quality -= items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality += 1
                    }
                }
            }
        }
    }
}

