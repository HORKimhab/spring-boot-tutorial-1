package com.hkimhab.example;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Order {

    @Schema(example = "General")
    private String customerName;

    @Schema(example = "Freedom buger Beef burger")
    private String product;

    @Schema(example = "2")
    private int quantity;

    @Schema(example = "USD")
    private String currency;
}
