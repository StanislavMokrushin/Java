//package com.example.springsecurityapplication.models;
//
//
//import javax.persistence.*;
//
//@Entity
//public class Image {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String fileName;
//
////    связь много фото к одному продукту (многие к одному), fetch = FetchType.EAGER - означает что при загрузке фото надо подгрузить и продукт, optional = false - таблица не родительская
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    private Product product;
//
//    public Image(String fileName, Product product){
//        this.fileName = fileName;
//        this.product = product;
//    }
//
//    public Image() {
//
//    }
//
//
//    public void setProduct(Product product) {
//    }
//
//    public void setFileName(String resultFileName) {
//
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//}
package com.example.springsecurityapplication.models;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;
    //    связь много фото к одному продукту (многие к одному), fetch = FetchType.EAGER - означает что при загрузке фото надо подгрузить и продукт, optional = false - таблица не родительская
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
    private Product product;

    public Image(String fileName, Product product) {
        this.fileName = fileName;
        this.product = product;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}