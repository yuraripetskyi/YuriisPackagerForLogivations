import models.Product;
import models.enums.Position;
import services.CaseService;
import services.OrderService;
import services.Packing;
import services.ProductService;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductService productService = ProductService.getProductService();
        CaseService caseService = CaseService.getCaseService();
        OrderService orderService = OrderService.getOrderService();
        Packing packing = Packing.getPacking();

        System.out.println(orderService.getOrders());
        System.out.println(caseService.getCases());
        System.out.println(productService.getProduct(6));
        Position[] values = Position.values();
        System.out.println(packing.pack(productService.getProduct(4), caseService.getCases(), 20));

    }


}
