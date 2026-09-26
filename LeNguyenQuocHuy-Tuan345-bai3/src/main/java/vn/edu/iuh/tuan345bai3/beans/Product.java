package vn.edu.iuh.tuan345bai3.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product implements Serializable {
    private int id;
    private String model;
    private double price;
    private int quantity;
    private String description;
    private String image;
}
