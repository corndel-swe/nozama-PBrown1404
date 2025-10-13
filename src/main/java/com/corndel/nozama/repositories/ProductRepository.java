package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;

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

            while (rs.next()) {
                var name = rs.getString("name");
                var description = rs.getString("description");
                var price = rs.getFloat("price");
                var stockQuantity = rs.getInt("stockQuantity");
                var imageURL = rs.getString("imageURL");

                var product = new Product(id, name, description, price, stockQuantity, imageURL);
                return product;
            }

        }
        return null;
    }

}
