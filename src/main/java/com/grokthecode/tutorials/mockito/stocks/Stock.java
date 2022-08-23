package com.grokthecode.tutorials.mockito.stocks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stock {
    private String stockId;
    private String name;
    private int quantity;
}
