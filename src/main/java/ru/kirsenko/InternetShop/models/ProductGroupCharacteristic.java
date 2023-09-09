package ru.kirsenko.InternetShop.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="product_group_characteristic")
@Data
public class ProductGroupCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="group_id")
    private Long groupId;
    @Column(name="characteristic_id")
    private Long characteristicId;
    @Column(name="product_id")
    private Long productId;
    @Column(name="product_characteristic_value")
    private String productCharacteristicValue;
}
