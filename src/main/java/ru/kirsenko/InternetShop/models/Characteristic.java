package ru.kirsenko.InternetShop.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Table(name="characteristic_table")
public class Characteristic {
    //Справочник характеристик
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    Long id;
    @Column(name="name")
    String name;

    public List<ProductCharacteristic> getProductCharacteristics() {
        return productCharacteristics;
    }

    public void setProductCharacteristics(List<ProductCharacteristic> productCharacteristics) {
        this.productCharacteristics = productCharacteristics;
    }

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="characteristic_id")//имя поля в таблице productGroupCharacteristic
//    private List<ProductGroupCharacteristic> productGroupCharacteristic = new ArrayList<>();
    @ManyToMany
    @JoinTable (name="product_group_characteristic",
            joinColumns=@JoinColumn (name="characteristic_id"),
        inverseJoinColumns=@JoinColumn(name="product_group_id"))
    private List<ProductGroup> productGroups;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "characteristic")
    private List<ProductCharacteristic> productCharacteristics = new ArrayList<>();
}
