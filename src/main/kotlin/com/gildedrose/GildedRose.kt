package com.gildedrose

const val AGED_BRIE = "Aged Brie"
const val CONCERT_BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
const val SULFURAS = "Sulfuras, Hand of Ragnaros"

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (isRisingGoods(i)) {
                if (couldSold(i) && isSellingGoods(i)) {
                    decreaseQuality(i)
                }
            } else {
                if (items[i].quality < 50) {
                    riseQuality(i)

                    if (items[i].name == CONCERT_BACKSTAGE_PASS) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                riseQuality(i)
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                riseQuality(i)
                            }
                        }
                    }
                }
            }

            if (isSellingGoods(i)) {
                items[i].sellIn -= 1
            }

            if (items[i].sellIn < 0) {
                if (isAgedBrie(i)) {
                    if (isConcertBackstagePass(i)) {
                        if (couldSold(i) && isSellingGoods(i)) {
                            decreaseQuality(i)
                        }
                    } else {
                        items[i].quality -= items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        riseQuality(i)
                    }
                }
            }
        }
    }

    private fun riseQuality(i: Int) {
        items[i].quality += 1
    }

    private fun decreaseQuality(i: Int) {
        items[i].quality -= 1
    }

    private fun couldSold(i: Int) = items[i].quality > 0

    private fun isSellingGoods(i: Int) = items[i].name != SULFURAS

    private fun isRisingGoods(i: Int) = isAgedBrie(i) && isConcertBackstagePass(i)

    private fun isConcertBackstagePass(i: Int) = items[i].name != CONCERT_BACKSTAGE_PASS

    private fun isAgedBrie(i: Int) = items[i].name != AGED_BRIE

}

