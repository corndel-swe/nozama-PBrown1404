package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static List<Product> findAll() throws SQLException {
        var query = "SELECT id, name, description, price, stockQuantity, imageURL FROM PRODUCTS";
        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query)) {

            var products = new ArrayList<Product>();
            while (rs.next()) {
                var id = rs.getInt("id");
                var name = rs.getString("name");
                var description = rs.getString("description");
                var price = rs.getFloat("price");
                var stockQuantity = rs.getInt("stockQuantity");
                var imageURL = rs.getString("imageURL");

                products.add(new Product(id, name, description, price, stockQuantity, imageURL));
            }

            return products;
        }
    }

    public static Product findById(int id) throws SQLException {
        var query = "SELECT name, description, price, stockQuantity, imageURL FROM PRODUCTS WHERE id = " + id;
        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query)) {

            var name = rs.getString("name");
            var description = rs.getString("description");
            var price = rs.getFloat("price");
            var stockQuantity = rs.getInt("stockQuantity");
            var imageURL = rs.getString("imageURL");

            return new Product(id, name, description, price, stockQuantity, imageURL);

        }
    }

    public Product createNewProduct(Integer id, String name, String description, Float price, Integer stockQuantity, String imageURL) throws SQLException {
        String query = String.format("INSERT INTO PRODUCTS VALUES(%d, %2s, %3s, %4s, %5d, %6s)",id,name,description,price,stockQuantity,imageURL);
        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query)) {
            return null;
        }

    }

    public static ArrayList<Product> filterByCategory(String categoryId) throws SQLException{
        try ( Connection con = DB.getConnection();
              var query = con.prepareStatement("SELECT PRODUCTS.id,PRODUCTS.name,PRODUCTS.description,PRODUCTS.price,PRODUCTS.stockQuantity,PRODUCTS.imageURL FROM PRODUCTS INNER JOIN PRODUCT_CATEGORIES ON PRODUCTS.id = PRODUCT_CATEGORIES.productId INNER JOIN CATEGORIES ON CATEGORIES.id = PRODUCT_CATEGORIES.categoryId WHERE CATEGORIES.id = ?"))

        {
            query.setString(1,categoryId);

            ResultSet rs = query.executeQuery();
            var products = new ArrayList<Product>();
            while (rs.next()) {
                var id = rs.getInt("id");
                var name = rs.getString("name");
                var description = rs.getString("description");
                var price = rs.getFloat("price");
                var stockQuantity = rs.getInt("stockQuantity");
                var imageURL = rs.getString("imageURL");

                products.add(new Product(id, name, description, price, stockQuantity, imageURL));
            }
            return products;
        }
    }

}
