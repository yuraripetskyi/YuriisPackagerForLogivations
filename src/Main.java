import models.Case;
import models.OrderLine;
import services.CaseService;
import services.OrderService;
import services.Packing;
import services.ProductService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        executeOrderline();
    }


    /*
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        be careful this method remove every executed order from orderline table
     */
    private static void executeOrderline() {
        ProductService productService = ProductService.getProductService();
        CaseService caseService = CaseService.getCaseService();
        OrderService orderService = OrderService.getOrderService();
        Packing packing = Packing.getPacking();
        List<OrderLine> orders = orderService.getOrders();
        Set<OrderLine> finishedOrders = new HashSet<>();
        for (OrderLine order : orders
        ) {
            Case pack = packing.pack(
                    productService.getProduct(order.getProductId()),
                    caseService.getCases(),
                    order.getNumberOfProducts()
            );
            if(pack!=null){
                finishedOrders.add(order);
                orderService.removeOrder(order);
            }
        }

        System.out.println("Finished Orders: "  + finishedOrders);

    }
}
