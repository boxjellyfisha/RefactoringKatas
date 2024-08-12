package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * goto specification: https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/main/GildedRoseRequirements.md
 */
internal class GildedRoseTest {

    @Test
    fun foo() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    private lateinit var gildedRose: GildedRose

    private val Sulfuras = Item(SULFURAS, 0, 80)

    @BeforeEach
    fun startUp() {
        gildedRose = GildedRose(listOf())
    }



    // 每過一天，一般商品價值下降兩倍
    @Test
    fun `Given a Normal goods, When sell in 3 days, Then it's should sell every day`() {
        givenGoods(Item("Normal goods", 3, 40))
        pastDays(3)
        shouldBeSellIn(0)
    }
    @Test
    fun `Given a Normal goods, When sell in 3 days, Then it's Quality become less`() {
        givenGoods(Item("Normal goods", 0, 40))
        pastDays(3)
        qualityShouldBe(34) // every day minus 1 until sell by date has passed every day minus 2
    }

    // 一般商品： 0 <= Quality <= 50
    @Test
    fun `Given a Normal goods quality is 8, When sell in 10 days, Then it's Quality become zero`() {
        givenGoods(Item("Normal goods", 10, 8))
        pastDays(10)
        qualityShouldBe(0)  // every day minus 1 until sell by date has passed every day minus 2
    }

    @Test
    fun `Given a Aged brie quality is 50, When sell in 10 days, Then it's Quality become 50`() {
        givenGoods(Item(AGED_BRIE, 10, 50))
        pastDays(10)
        qualityShouldBe(50) // every day plus 1
    }

    //  Aged brie： Quality++
    @Test
    fun `Given a Aged brie quality is 10, When sell in 10 days, Then it's Quality become 20`() {
        givenGoods(Item(AGED_BRIE, 10, 10))
        pastDays(10)
        qualityShouldBe(20) // every day plus 1
    }

    @Test
    fun `Given a Aged brie quality is 10, When sell in 11 days, Then it's Quality become 20`() {
        givenGoods(Item(AGED_BRIE, 10, 10))
        pastDays(11)
        qualityShouldBe(22) // every day plus 1
    }

    // Backstage
    // sellIn days <= 10, 2++
    //             <= 5, 3++
    //             <0  , 0
    @Test
    fun `Given a Backstage pass quality is 40, When sell in 10 days, Then it's Quality become 50`() {
        givenGoods(Item(CONCERT_BACKSTAGE_PASS, 10, 40))
        pastDays(10)
        qualityShouldBe(50) // every day plus 2
    }

    @Test
    fun `Given a Backstage pass quality is 40, When sell in 11 days, Then it's Quality become 0`() {
        givenGoods(Item(CONCERT_BACKSTAGE_PASS, 10, 40))
        pastDays(11)
        qualityShouldBe(0) // every day plus 2
    }

    @Test
    fun `Given a Backstage pass quality is 10, When sell in 3 days, Then it's Quality become 16`() {
        givenGoods(Item(CONCERT_BACKSTAGE_PASS, 10, 10))
        pastDays(3)
        qualityShouldBe(16) // every day plus 2
    }

    @Test
    fun `Given a Backstage pass quality is 10, When sell in 8 days, Then it's Quality become 29`() {
        givenGoods(Item(CONCERT_BACKSTAGE_PASS, 10, 10))
        pastDays(8)
        qualityShouldBe(29) // every day plus 2
    }

    @Test
    fun `Given a Backstage pass quality is 10 and start with 11 days, When sell in 2 days, Then it's Quality become 29`() {
        givenGoods(Item(CONCERT_BACKSTAGE_PASS, 11, 10))
        pastDays(2)
        qualityShouldBe(13) // first day plus 1, and second day plus 2
    }

    // Sulfuras 是非賣品，他的價值恆等於 80
    @Test
    fun `Given a Sulfuras, When sell in 3 days, Then it never sold`() {
        givenGoods(Sulfuras)
        pastDays(3)
        shouldBeSellIn(0)
    }

    @Test
    fun `Given a Sulfuras, When sell in 3 days, Then the Quality should still be 80`() {
        givenGoods(Sulfuras)
        pastDays(3)
        qualityShouldBe(80)
    }

    // TODO: New feature Conjuresd --4
    @Test
    fun `Given a conjuresd quality is 10, When sell in 2 days, Then the Quality should be 2`() {
        givenGoods(Item(CONJURESD, 2, 10))
        pastDays(2)
        qualityShouldBe(6)
    }

    @Test
    fun `Given a conjuresd quality is 10, When sell in 4 days, Then the Quality should be 0`() {
        givenGoods(Item(CONJURESD, 2, 10))
        pastDays(4)
        qualityShouldBe(0)
    }

    private fun givenGoods(vararg item: Item) {
        gildedRose.items = item.asList()
    }

    private fun pastDays(day: Int) {
        for (today in 0..<day)
            gildedRose.updateQuality()
    }

    private fun shouldBeSellIn(days: Int) {
        assertEquals(days, gildedRose.items[0].sellIn)
    }

    private fun qualityShouldBe(value: Int) {
        assertEquals(value, gildedRose.items[0].quality)
    }
}


