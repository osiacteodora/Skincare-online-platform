package com.example.demo.Repository;

import com.example.demo.DataModels.Product;
import com.example.demo.Repository.Connection.Connection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final java.sql.Connection context = Connection.getConnection();

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statementString = context.prepareStatement("select * from products");
            ResultSet result = statementString.executeQuery();
            while (result.next()) {
                products.add(new Product(
                        result.getInt("id"),
                        result.getString("type"),
                        result.getString("skintype"),
                        result.getInt("price"),
                        result.getString("name"),
                        result.getString("brand"),
                        result.getInt("stock")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }

    public Product addProduct(Product product) {
        try {
            PreparedStatement statementString = context.prepareStatement("insert into products values(?,?,?,?,?,?,?)");
            statementString.setString(1,Integer.toString(product.getId()));
            statementString.setString(2,product.getType());
            statementString.setString(3,product.getSkintype());
            statementString.setString(4,Integer.toString(product.getPrice()));
            statementString.setString(5,product.getName());
            statementString.setString(6,product.getBrand());
            statementString.setString(7,Integer.toString(product.getStock()));
            statementString.executeUpdate();
            return product;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Integer getAvailableProductId() {
        try {
            PreparedStatement statementString = context.prepareStatement("select id from products");
            ResultSet result = statementString.executeQuery();
            Integer idCurrent = 0;
            while (result.next()) {
                idCurrent = result.getInt("id");
            }
            return idCurrent + 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public Product UpdateProduct(Product product) {
        try {
            PreparedStatement statementString = context.prepareStatement("update  products set type=?,skintype=?, price=?, name=?, brand=?, stock=? where id=?");
            statementString.setString(7,Integer.toString(product.getId()));
            statementString.setString(1,product.getType());
            statementString.setString(2,product.getSkintype());
            statementString.setString(3,Integer.toString(product.getPrice()));
            statementString.setString(4,product.getName());
            statementString.setString(5,product.getBrand());
            statementString.setString(6,Integer.toString(product.getStock()));
            statementString.executeUpdate();
            return product;
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean DeleteProduct(int id) {
        try {
            PreparedStatement statementString = context.prepareStatement("delete from products where id=?");
            statementString.setString(1,Integer.toString(id));
            statementString.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
