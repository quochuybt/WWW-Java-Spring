package iuh.fit.tuan345bai4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {

    private Book book;
    private int quantity;

    public double getSubtotal() {
        return book.getPrice()*quantity;
    }
}
