package iuh.fit.tuan345bai4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    private long id;
    private String title;
    private String author;
    private double price;
    private String imgbook;
}
