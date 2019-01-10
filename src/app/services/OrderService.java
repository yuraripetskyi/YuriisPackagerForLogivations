package app.services;

import app.models.OrderLine;

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
            System.out.println("There are no orders to execute");
        else
            System.out.println("Orders loaded");
        return orders;
    }

    public void removeOrder(OrderLine order){
        try {
            Connection connection = SConnection.getConnection();
            if(order!=null) {
                PreparedStatement update = connection.prepareStatement("DELETE FROM orderline WHERE id = " + order.getId());
                update.executeUpdate();
            }
            } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }


    public static OrderService getOrderService() {
        if (orderService == null)
            orderService = new OrderService();
        return orderService;
    }
}
