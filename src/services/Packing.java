package services;

import models.Case;
import models.Product;
import models.enums.Position;

import java.util.Set;

public class Packing {

    private static Packing packing;
    private int count;

    /*
     * Method choose best case to pack products into
     */
    public Case pack(Product product, Set<Case> cases, int number) {


        Case theCase = null;
        /*
         * trying all cases
         * cases are sorted by size, so first case that can contain our products is the best
         */
        for (Case cas : cases
        ) {
            count = 0;

            int counting = counting(cas, product, number); // count of products u can put in this case
            /*
             * if we can put all our products in case, return this case
             * if not, try other case
             */
            if (counting >= number) {
                theCase = cas;
                System.out.println("=================================================================================");
                System.out.println("the case is with id: " + cas.getId() + " was filled with " + number + " of product " + product );
                System.out.println("==================================================================================");
                break;
            }
        }
        return theCase;
    }


    private int counting(Case cas, Product product, int num) {
        Position[] positions = Position.values();
        int addictionalNum = 0; //have to use two counters
        int addCounter = 0;

        /*
         * try various ways to place first products
         */
        for (Position pos : positions
        ) {
            product = ProductService.changePosition(product, pos);

            /*
             * how many products we can put into case in this position
             */

            int xDif = cas.getSizeX() / product.getSizeX();
            int yDif = cas.getSizeY() / product.getSizeY();
            int zDif = (cas.getSizeZ() / product.getSizeZ());

            int temp = xDif * yDif * zDif;
            count = temp;
            /*
             *in some position it can be more or less,
             *  we chose that one where we can put more products
             */
            if (addictionalNum < temp) {
                addictionalNum = temp;
            }

            /*
             * trying to put more products in free space
             */

            //i understand that next lines do my algorithm much more slow, but i did not find better way to fill free space after first product placement
            //here we cut our fre space in different types, and start our counting method one more time, the case where we can put more products will be chosen

            if (product.getSizeX() < cas.getSizeY() % product.getSizeY() ||
                    product.getSizeX() < cas.getSizeZ() % product.getSizeZ()) {
                int aSpace = counting(new Case(cas.getId(), cas.getSizeX(), cas.getSizeY() % product.getSizeY(), cas.getSizeZ()), product, num - count);
                int bSpace = counting(new Case(cas.getId(), cas.getSizeX(), cas.getSizeY() - cas.getSizeY() % product.getSizeY(), cas.getSizeZ()%product.getSizeZ()), product, num - count - aSpace);

                if (aSpace + bSpace > addCounter) {
                    addCounter = aSpace + bSpace;
                }
            }
            if (product.getSizeY() < cas.getSizeX() % product.getSizeX() ||
                    product.getSizeY() < cas.getSizeZ() % product.getSizeZ()) {
                int aSpace = counting(new Case(cas.getId(), cas.getSizeX()%product.getSizeX(), cas.getSizeY(), cas.getSizeZ()), product, num - count);
                int bSpace = counting(new Case(cas.getId(), cas.getSizeX() - cas.getSizeX()%product.getSizeX(), cas.getSizeY(), cas.getSizeZ()%product.getSizeZ()), product, num - count - aSpace);

                if (aSpace + bSpace > addCounter) {
                    addCounter = aSpace + bSpace;
                }
            }
            if (product.getSizeZ() < cas.getSizeY() % product.getSizeY() ||
                    product.getSizeZ() < cas.getSizeX() % product.getSizeX()) {
                int aSpace = counting(new Case(cas.getId(), cas.getSizeX()%product.getSizeX(), cas.getSizeY(), cas.getSizeZ()), product, num - count);
                int bSpace = counting(new Case(cas.getId(), cas.getSizeX()-cas.getSizeX()%product.getSizeX(), cas.getSizeY() % product.getSizeY(), cas.getSizeZ()), product, num - count - aSpace);

                if (aSpace + bSpace > addCounter) {
                    addCounter = aSpace + bSpace;
                }
            }


        }
        return addictionalNum + addCounter;
    }

    public static Packing getPacking() {
        if (packing == null)
            packing = new Packing();
        return packing;
    }
}
