package module2_vatservice;

class VatService {
    double vatValue;

    public VatService() {
        this.vatValue = 0.23;
    }

    public double getGrossPriceForDefaultVat(Product product) throws IncorrectVatException {
        return getGrossPriceForCustomVat(product.getNetPrice(), vatValue);
    }

    public double getGrossPriceForCustomVat(double netPrice, double vatValue) throws IncorrectVatException {
        if(vatValue > 1){
            throw new IncorrectVatException("VAT can't be higher than 100%");
        }
        return netPrice * (1 + vatValue);
    }
}
