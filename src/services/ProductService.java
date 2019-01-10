package services;

import models.Product;
import models.enums.Position;

import java.sql.*;
import java.util.Properties;

public class ProductService {
    private static ProductService productService;

    public Product getProduct(long id) {
        Product product = null;
        try {
            Connection connection = SConnection.getConnection();

            PreparedStatement query = connection.prepareStatement("SELECT * FROM product where id in (" + id + ")");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getInt("sizeX"),
                        resultSet.getInt("sizeY"),
                        resultSet.getInt("sizeZ")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
        if (product.equals(null))
            System.out.println("There is no any product with id  = " + id);
        else
            System.out.println("Product selected from database: " + product);
        return product;
    }

    public static Product rotateH(Product p) {
        int temp = p.getSizeX();
        p.setSizeX(p.getSizeY());
        p.setSizeY(temp);
        return p;
    }

    public static Product changePosition(Product product, Position position) {

        switch (position) {
            case POSITION_1:
                return product;
            case POSITION_2:
                int temp = product.getSizeX();
                product.setSizeX(product.getSizeY());
                product.setSizeY(temp);
                return product;
            case POSITION_3:
                temp = product.getSizeZ();
                product.setSizeZ(product.getSizeY());
                product.setSizeY(temp);
                return product;
            case POSITION_4:
                temp = product.getSizeZ();
                product.setSizeZ(product.getSizeY());
                product.setSizeY(temp);
                temp = product.getSizeX();
                product.setSizeX(product.getSizeY());
                product.setSizeY(temp);
                return product;
            case POSITION_5:
                temp = product.getSizeZ();
                product.setSizeZ(product.getSizeX());
                product.setSizeX(temp);
                temp = product.getSizeX();
                product.setSizeX(product.getSizeY());
                product.setSizeY(temp);
                return product;
            case POSITION_6:
                temp = product.getSizeX();
                product.setSizeZ(product.getSizeX());
                product.setSizeZ(temp);
                return product;
            default:
                return product;
        }

    }

    public static ProductService getProductService() {
        if (productService == null)
            productService = new ProductService();
        return productService;
    }


}
