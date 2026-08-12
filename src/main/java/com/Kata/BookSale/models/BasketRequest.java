package com.Kata.BookSale.models;

import java.util.List;

public class BasketRequest {
private List<Integer> bookIds;

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }
}
