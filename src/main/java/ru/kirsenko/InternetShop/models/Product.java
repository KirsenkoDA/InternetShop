package ru.kirsenko.InternetShop.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_table")


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 255, message = "Имя должно содержать от 2 до 255 символов")
    private String name;
    @Column(name = "product_discription")
    @NotEmpty(message = "Описание не должно быть пустым")
    @Size(min = 2, max = 1000, message = "Имя должно содержать от 2 до 30 символов")
    private String discription;
    @Column(name = "product_price")
    @Min(value=0, message = "Значение цены может быть только положительным")
    private float price;

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductGroup productGroup;
    //Доабвление связи к модели image
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")//(mappedBy)Товар связанный с фотографией будет записан в foreign key  в таблице images
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    @PrePersist//Метод инициализации бина
    private void init()
    {
        dateOfCreated = LocalDateTime.now();
    }
    public void addImageToProduct(Image image)//Метод добавления foreign key в таблицы
    {
        image.setProduct(this);
        images.add(image);
    }
    //Возвращает true если существует изображение с указанным индексом
    public boolean existImage(int index)
    {
        return images.size() >= index + 1;
    }
    public void deleteImageFromProduct(Long imageId)
    {
        images.remove(imageId);
    }
    public void updateImageFromProduct(Image image, int index)
    {
        image.setProduct(this);
        images.set(index, image);
    }
    public Product(Long id, String name, String discription, float price) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.price = price;
    }

    public Product() {
    }

    public Long getId() {
        return this.id;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getPreviewImageId() {
        return previewImageId;
    }

    public void setPreviewImageId(Long previewImageId) {
        this.previewImageId = previewImageId;
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

//    public Long hashCode() {
//        final int PRIME = 59;
//        Long result = 1;
//        result = result * PRIME + this.getId();
//        final Object $name = this.getName();
//        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
//        final Object $discription = this.getDiscription();
//        result = result * PRIME + ($discription == null ? 43 : $discription.hashCode());
//        result = result * PRIME + Float.floatToIntBits(this.getPrice());
//        return result;
//    }

    public String toString() {
        return "Product(id=" + this.getId() + ", name=" + this.getName() + ", discription=" + this.getDiscription() + ", price=" + this.getPrice() + ")";
    }
}
