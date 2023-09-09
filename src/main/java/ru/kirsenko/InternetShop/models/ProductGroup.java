package ru.kirsenko.InternetShop.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_group")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_group_id")
    private Long id;
    @Column(name = "product_group_name")
    private String name;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    //Доабвление связи к модели Product
    //Cascade.Merge - каскадное бновление
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productGroup")//(mappedBy)Группа связанная с товаром будет записана в foreign key  в таблице images
    private List<Product> product = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productGroupCharacteristics")
////    @JoinColumn(name="product_group_id")//имя поля в таблице productGroupCharacteristic
//    private List<ProductGroupCharacteristic> productGroupCharacteristics = new ArrayList<>();
    @ManyToMany
    @JoinTable (name="product_group_characteristic",
        joinColumns=@JoinColumn (name="product_group_id"),
        inverseJoinColumns=@JoinColumn(name="characteristic_id"))
    private List<Characteristic> characteristics;

    public ProductGroup() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductGroup)) return false;
        final ProductGroup other = (ProductGroup) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductGroup;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "ProductGroup(id=" + this.getId() + ", name=" + this.getName() + ")";
    }
    public boolean isSelected(Long selectedParam){
        if(selectedParam == id)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
