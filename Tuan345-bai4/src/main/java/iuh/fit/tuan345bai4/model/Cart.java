package iuh.fit.tuan345bai4.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(x -> x.getSubtotal()).sum();
    }
}
