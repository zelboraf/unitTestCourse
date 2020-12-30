package module2_vatservice.v2;

import java.util.UUID;

class Product {
    UUID id;
    double netPrice;
    String type;

    public Product(UUID id, double netPrice, String type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public String getType() {
        return type;
    }
}
