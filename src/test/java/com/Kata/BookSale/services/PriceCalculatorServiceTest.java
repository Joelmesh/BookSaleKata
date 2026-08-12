package com.Kata.BookSale.services;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorServiceTest {

    private PriceCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PriceCalculatorService();
    }

    @Test
    void calculatesZeroForNullBasket() {
        assertEquals(0.0, calculator.calculatePrice(null));
    }

    @Test
    void calculatesZeroForEmptyBasket() {
        assertEquals(0.0, calculator.calculatePrice(Collections.emptyList()));
    }

    @Test
    void calculatesSingleBookPrice() {
        assertEquals(50.0, calculator.calculatePrice(List.of(0)));
    }

    @Test
    void calculatesMultipleSameBookPrice() {
        assertEquals(150.0, calculator.calculatePrice(List.of(1, 1, 1)));
    }

    @Test
    void appliesDiscountForTwoDifferentBooks() {
        assertEquals(95.0, calculator.calculatePrice(List.of(0, 1)));
    }

    @Test
    void appliesMaximumDiscountForFiveDifferentBooks() {
        assertEquals(187.5, calculator.calculatePrice(List.of(0, 1, 2, 3, 4)));
    }

    @Test
    void optimizesFivePlusThreeToTwoFours() {
        assertEquals(320.0, calculator.calculatePrice(List.of(0, 0, 1, 1, 2, 2, 3, 4)));
    }

    @Test
    void calculatesLargeBasketCorrectly() {
        assertEquals(640.0, calculator.calculatePrice(List.of(
            0, 0, 0, 0,
            1, 1, 1, 1,
            2, 2, 2, 2,
            3, 3,
            4, 4
        )));
    }
}
