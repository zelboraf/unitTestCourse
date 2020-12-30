package module2_vatservice.v1;

import java.util.UUID;

class Product {
    UUID id;
    double netPrice;

    public Product(UUID id, double netPrice) {
        this.id = id;
        this.netPrice = netPrice;
    }

    public double getNetPrice() {
        return netPrice;
    }
}
