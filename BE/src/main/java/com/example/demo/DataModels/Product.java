package com.example.demo.DataModels;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


public class Product  {


    private int id;
    private String type;
    private String skintype;
    private int price;
    private String name;
    private String brand;
    private int stock;

    public Product(){};

    public Product(int id, String type, String skintype, int price, String name, String brand, int stock) {
        this.id = id;
        this.type = type;
        this.skintype = skintype;
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.stock = stock;
    }

    public Product(String type, String skintype, int price, String name, String brand, int stock) {
        this.type = type;
        this.skintype = skintype;
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkintype() {
        return skintype;
    }

    public void setSkintype(String skintype) {
        this.skintype = skintype;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", skintype='" + skintype + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", stock=" + stock +
                '}';
    }
}
