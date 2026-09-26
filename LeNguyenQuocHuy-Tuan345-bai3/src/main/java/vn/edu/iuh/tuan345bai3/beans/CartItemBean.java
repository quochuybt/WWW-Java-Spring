package vn.edu.iuh.tuan345bai3.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemBean implements Serializable {
    private Product product;
    private int quantity;

    public CartItemBean(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
