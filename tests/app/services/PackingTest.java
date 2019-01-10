package app.services;

import app.models.Case;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PackingTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Packing.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Test
    public void pack() {
        ProductService productService = ProductService.getProductService();
        CaseService caseService = CaseService.getCaseService();
        OrderService orderService = OrderService.getOrderService();
        Packing packing = Packing.getPacking();
        Case pack = packing.pack(productService.getProduct(1), caseService.getCases(),1);
        assertNull(pack);
    }


}
