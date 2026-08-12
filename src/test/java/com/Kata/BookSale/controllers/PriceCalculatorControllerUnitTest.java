package com.Kata.BookSale.controllers;

import com.Kata.BookSale.models.BasketRequest;
import com.Kata.BookSale.services.PriceCalculatorService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PriceCalculatorControllerUnitTest {

    @Test
    void shouldReturnFiveBooks() {
        PriceCalculatorController controller = new PriceCalculatorController(new PriceCalculatorService());

        var response = controller.listBooks();
        var books = response.getBody();

        assertNotNull(books);
        assertEquals(5, books.size());
        assertTrue(books.get(0).toString().contains("Clean Code"));
    }

    @Test
    void shouldAllowPriceCalculationThroughController() {
        PriceCalculatorController controller = new PriceCalculatorController(new PriceCalculatorService());

        BasketRequest request = new BasketRequest();
        request.setBookIds(List.of(0, 1));

        var response = controller.calculateBasketPrice(request);

        assertNotNull(response);
        assertEquals(95.0, response.getBody().get("totalPrice"));
    }
}
