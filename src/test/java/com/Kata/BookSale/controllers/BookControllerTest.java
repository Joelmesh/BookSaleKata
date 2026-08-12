package com.Kata.BookSale.controllers;

import com.Kata.BookSale.services.PriceCalculatorService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookControllerTest {

    @Test
    void shouldReturnFiveBooks() {
        BookController controller = new BookController(new PriceCalculatorService());

        var response = controller.listBooks();
        var books = response.getBody();

        assertNotNull(books);
        assertEquals(5, books.size());
        assertTrue(books.get(0).toString().contains("Clean Code"));
    }

    @Test
    void shouldAllowPriceCalculationThroughBookController() {
        BookController controller = new BookController(new PriceCalculatorService());

        double price = controller.calculateBasketPrice(List.of(0, 1));

        assertEquals(95.0, price);
    }
}
