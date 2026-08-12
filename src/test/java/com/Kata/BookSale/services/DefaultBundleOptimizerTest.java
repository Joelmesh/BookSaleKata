package com.Kata.BookSale.services;

import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultBundleOptimizerTest {

    private final BundleOptimizer optimizer = new DefaultBundleOptimizer();

    @Test
    void doesNotChangeNonOptimizableSetCounts() {
        assertEquals(Map.of(2, 1L, 4, 1L), optimizer.optimize(Map.of(2, 1L, 4, 1L)));
    }

    @Test
    void optimizesFiveAndThreeSetsIntoTwoFourSets() {
        assertEquals(Map.of(5, 0L, 3, 0L, 4, 2L), optimizer.optimize(Map.of(5, 1L, 3, 1L)));
    }

    @Test
    void preservesOtherSetCountsWhenOptimizing() {
        assertEquals(Map.of(5, 0L, 3, 0L, 4, 2L, 2, 1L), optimizer.optimize(Map.of(5, 1L, 3, 1L, 2, 1L)));
    }
}
