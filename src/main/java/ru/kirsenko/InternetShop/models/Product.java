package ru.kirsenko.InternetShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_table")


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_discription")
    private String discription;
    @Column(name = "product_price")
    private float price;
    @Column(name = "product_type")
    private Integer type;

    public Product(Long id, String name, String discription, float price, Integer type) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.price = price;
        this.type = type;
    }

    public Product() {
    }

    public int getId() {
        return Math.toIntExact(this.id);
    }

    public String getName() {
        return this.name;
    }

    public String getDiscription() {
        return this.discription;
    }

    public float getPrice() {
        return this.price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$discription = this.getDiscription();
        final Object other$discription = other.getDiscription();
        if (this$discription == null ? other$discription != null : !this$discription.equals(other$discription))
            return false;
        if (Float.compare(this.getPrice(), other.getPrice()) != 0) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $discription = this.getDiscription();
        result = result * PRIME + ($discription == null ? 43 : $discription.hashCode());
        result = result * PRIME + Float.floatToIntBits(this.getPrice());
        return result;
    }

    public String toString() {
        return "Product(id=" + this.getId() + ", name=" + this.getName() + ", discription=" + this.getDiscription() + ", price=" + this.getPrice() + ")";
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
