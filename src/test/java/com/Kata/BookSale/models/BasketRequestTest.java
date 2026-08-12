package com.Kata.BookSale.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BasketRequestTest {

    @Test
    void gettersAndSettersShouldWork() {
        BasketRequest request = new BasketRequest();
        assertNull(request.getBookIds());

        List<Integer> bookIds = List.of(0, 1, 2);
        request.setBookIds(bookIds);

        assertEquals(bookIds, request.getBookIds());
    }
}
