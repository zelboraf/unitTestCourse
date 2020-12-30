package module2_vatservice.v2;

class VatService {
    VatProvider vatProvider;


    public VatService(VatProvider vatProvider) {
        this.vatProvider = vatProvider;
    }

    public double getGrossPriceForDefaultVat(Product product) throws IncorrectVatException {
        return calculateGrossPrice(product.getNetPrice(), vatProvider.getDefaultVat());
    }

    public double getGrossPriceForCustomVat(Product product) throws IncorrectVatException {
        double vatValue = vatProvider.getVatForType(product.getType());
        return calculateGrossPrice(product.getNetPrice(), vatValue);
    }

    private double calculateGrossPrice(double netPrice, double vatValue) throws IncorrectVatException {
        if(vatValue > 1){
            throw new IncorrectVatException("VAT can't be higher than 100%");
        }
        return netPrice * (1 + vatValue);
    }
}
