package ru.kirsenko.InternetShop.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="product_characteristic")
@Data
public class ProductCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Characteristic characteristic;

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductCharacteristicValue() {
        return productCharacteristicValue;
    }

    public void setProductCharacteristicValue(String productCharacteristicValue) {
        this.productCharacteristicValue = productCharacteristicValue;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    @Column(name="product_characteristic_value")
    private String productCharacteristicValue;
}
