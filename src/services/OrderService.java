package services;

import models.OrderLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderService orderService;


    public List<OrderLine> getOrders() {
        List<OrderLine> orders = new ArrayList<>();
        try {
            Connection connection = SConnection.getConnection();

            PreparedStatement query = connection.prepareStatement("SELECT * FROM orderline");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                orders.add(new OrderLine(
                                resultSet.getInt("id"),
                                resultSet.getInt("numberOfProducts"),
                                resultSet.getInt("productId")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (orders.isEmpty())
            System.out.println("Some error, no orders loaded ");
        else
            System.out.println("Orders loaded");
        return orders;
    }


    public static OrderService getOrderService() {
        if (orderService == null)
            orderService = new OrderService();
        return orderService;
    }
}
