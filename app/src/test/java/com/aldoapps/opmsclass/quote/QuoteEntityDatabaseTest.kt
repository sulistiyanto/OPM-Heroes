package com.aldoapps.opmsclass.com.aldoapps.opmsclass.quote

import com.aldoapps.opmsclass.quote.repository.QuoteDatabase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

/**
 * Created by aldo on 05/01/18.
 */
class QuoteEntityDatabaseTest {

    @Test
    fun getAllQuoteTest() {
        // Given
        // When
        val quotes = QuoteDatabase.getQuotes()
        // Then
        assertThat(quotes.size).isEqualTo(33)
    }

    @Test
    fun getQuotesByAuthorTest() {
        // Given
        // When
        val tatsumakiQuotes = QuoteDatabase.getQuotesByAuthor("Tatsumaki")

        // Then
        assertThat(tatsumakiQuotes).hasSize(1)
        assertThat(tatsumakiQuotes.first().hero.name).isEqualToIgnoringCase("TATSUMAKI")

        // When
        val kingQuotes = QuoteDatabase.getQuotesByAuthor("KING");
        // Then
        assertThat(kingQuotes).hasSize(2)
        assertThat(kingQuotes.first().quote).isEqualTo("Romance games are my oasis.")

        // When
        val saitamaQuotes = QuoteDatabase.getQuotesByAuthor("Saitama")
        assertThat(saitamaQuotes).hasSize(11)
        assertThat(saitamaQuotes.first().hero.name).isEqualToIgnoringCase("saiTAMA")
    }

    @ParameterizedTest
    @CsvSource(
            "Tatsumaki, 1",
            "King, 2",
            "Saitama, 11"
    )
    fun testQuoteSize(author: String, expected: Int) {
        val quote = QuoteDatabase.getQuotesByAuthor(author)
        assertThat(quote).hasSize(expected)
    }
}