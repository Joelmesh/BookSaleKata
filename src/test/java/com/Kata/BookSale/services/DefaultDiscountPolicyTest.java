package com.Kata.BookSale.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultDiscountPolicyTest {

    private final DiscountPolicy discountPolicy = new DefaultDiscountPolicy();

    @Test
    void returnsFullPriceForSingleBookSet() {
        assertEquals(1.0, discountPolicy.discountFactor(1));
    }

    @Test
    void returnsFivePercentDiscountForTwoBookSet() {
        assertEquals(0.95, discountPolicy.discountFactor(2));
    }

    @Test
    void returnsSeventyFivePercentFactorForFiveBookSet() {
        assertEquals(0.75, discountPolicy.discountFactor(5));
    }

    @Test
    void returnsFullPriceForUnknownSetSize() {
        assertEquals(1.0, discountPolicy.discountFactor(6));
    }
}
