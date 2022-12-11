package com.example.springsecurityapplication.models;


import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "title", nullable = false, columnDefinition = "text", unique = true)
    @NotEmpty(message = "Наименование товара не может быть пустым")
    private String title;


    @Column(name = "description", nullable = false, columnDefinition = "text")
    @NotEmpty(message = "Описание товара не может быть пустым")
    private String description;

    @Column(name = "price", nullable = false)
    @Min(value = 1, message = "Цена товара не может быть отрицательной или нулевой")
    @NotNull(message = "Цена товара не может быть отрицательной или нулевой")
    private float price;


    @Column(name = "warehouse", nullable = false)
    @NotEmpty(message = "Поле склад должно быть заполнено")
    private String warehouse;


    @Column(name = "seller", nullable = false)
    @NotEmpty(message = "Поле продавец должно быть заполнено")
    private String seller;

//    один продукт ко многим фото (один ко многим), cascade = CascadeType.ALL - при удалении продукта удаляются все фото, fetch = FetchType.LAZY - если подружается товар то не надо грузить фото и их хранить, mappedBy = "product" - с каким полем связь
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
//    лист фото так как к одному товау их много
    private List<Image> imageList = new ArrayList<>();
// связб много товаров для 1 категории
    @ManyToOne(optional = false)
    private Category category;

//    многие ко многим для корзины
    @ManyToMany()
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personList;

//    связь для заказов
    @OneToMany(mappedBy = "product")
    private List<Order> orderList;
//    дата создания товара
    private LocalDateTime dateTimeOfCreated;

//    как только создается объект продукта нужно заполнить дату создания товара
    @PrePersist
    private void init(){
        dateTimeOfCreated = LocalDateTime.now();
    }
// добавляем фото в лист фотографий
    public void addImageProduct(Image image){
        image.setProduct(this);
        imageList.add(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(String title, String description, float price, String warehouse, String seller) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.warehouse = warehouse;
        this.seller = seller;
    }

    public Product() {
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public LocalDateTime getDateTimeOfCreated() {
        return dateTimeOfCreated;
    }

    public void setDateTimeOfCreated(LocalDateTime dateTimeOfCreated) {
        this.dateTimeOfCreated = dateTimeOfCreated;
    }
}
