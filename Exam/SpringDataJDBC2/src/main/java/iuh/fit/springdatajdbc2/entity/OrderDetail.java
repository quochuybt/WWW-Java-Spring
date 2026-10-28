package iuh.fit.springdatajdbc2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_details")
public class OrderDetail {
    private Long orderId;
    private Long productId;
    private int quantity;
    private double unitPrice;

}
