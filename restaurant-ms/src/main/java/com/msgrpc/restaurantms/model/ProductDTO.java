package com.msgrpc.restaurantms.model;

import com.msgrpc.restaurantms.ProductResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stock;

    public ProductDTO(ProductResponse productResponse) {
        this.name = productResponse.getName();
        this.description = productResponse.getDescription();
        this.price = productResponse.getPrice();
        this.stock = productResponse.getStock();
    }
}
