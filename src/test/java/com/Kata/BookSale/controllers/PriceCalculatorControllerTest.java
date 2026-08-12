package com.Kata.BookSale.controllers;

import com.Kata.BookSale.models.BasketRequest;
import com.Kata.BookSale.services.PriceCalculator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceCalculatorController.class)
@AutoConfigureJsonTesters
public class PriceCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private PriceCalculator priceCalculator;

    @Test
    void returnsPriceResponseForValidBasket() throws Exception {
        given(priceCalculator.calculatePrice(anyList())).willReturn(95.0);

        BasketRequest request = new BasketRequest();
        request.setBookIds(List.of(0, 1));

        mockMvc.perform(post("/api/v1/checkout/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(95.0))
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void returnsBadRequestWhenBookIdsMissing() throws Exception {
        BasketRequest request = new BasketRequest();

        mockMvc.perform(post("/api/v1/checkout/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("The request body or bookIds array cannot be null."));
    }
}
