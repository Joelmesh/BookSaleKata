package com.Kata.BookSale.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listBooksReturnsAvailableBookCatalog() throws Exception {
        mockMvc.perform(get("/api/v1/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    void calculateBasketPriceEndpointReturnsExpectedValue() throws Exception {
        mockMvc.perform(post("/api/v1/books/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[0,1]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(95.0));
    }
}
