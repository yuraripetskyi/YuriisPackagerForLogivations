package app.models;

public class OrderLine {
    private long id;
    private int numberOfProducts;
    private int productId;

    public OrderLine(long id, int numberOfProducts, int productId) {
        this.id = id;
        this.numberOfProducts = numberOfProducts;
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", numberOfProducts=" + numberOfProducts +
                ", productId=" + productId +
                '}';
    }
}
