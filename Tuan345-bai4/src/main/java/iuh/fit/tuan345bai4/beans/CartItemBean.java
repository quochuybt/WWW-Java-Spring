package iuh.fit.tuan345bai4.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemBean implements Serializable {

    private Book book;
    private int quantity;

    public double getSubtotal() {
        return book.getPrice()*quantity;
    }
}
