package module2_vatservice.v2;

public interface VatProvider {

    double getDefaultVat();

    double getVatForType(String type);
}
