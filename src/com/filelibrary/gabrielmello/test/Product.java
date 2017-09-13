package com.filelibrary.gabrielmello.test;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class Product {
    private String nameProduct;
    private Double cost;
    private String type;

    public Product(String nameProduct, Double cost, String type) {
        this.nameProduct = nameProduct;
        this.cost = cost;
        this.type = type;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
