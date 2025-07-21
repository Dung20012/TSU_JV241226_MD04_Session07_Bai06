package com.data.session_07.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // ánh xạ class với 1 bảng trong DB
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "products") // tên bảng DB
public class Product {
    @Id // khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự tăng auto_increment
    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private Long categoryId;
}
