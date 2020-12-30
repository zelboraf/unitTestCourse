package module2_vatservice.v2;

import org.apache.log4j.Logger;

class VatService {
    VatProvider vatProvider;
    final static Logger logger = Logger.getLogger(VatService.class);

    public VatService(VatProvider vatProvider) {
        this.vatProvider = vatProvider;
    }

    public double getGrossPriceForDefaultVat(Product product) throws IncorrectVatException {
        logger.info("Get price for default VAT");
        return calculateGrossPrice(product, vatProvider.getDefaultVat());
    }

    public double getGrossPriceForCustomVat(Product product) throws IncorrectVatException {
        logger.info("Get price for custom VAT");
        return calculateGrossPrice(product, vatProvider.getVatForType(product.getType()));
    }

    private double calculateGrossPrice(Product product, double vatValue) throws IncorrectVatException {
        double netPrice = product.getNetPrice();
        logger.info(String.format("Calculate gross price for netPrice: %.2f, vatValue: %.2f, type: %s",
                netPrice, vatValue, product.getType()));
        if(vatValue > 1){
            String errorMessage = "VAT can't be higher than 100%";
            logger.error(errorMessage);
            throw new IncorrectVatException(errorMessage);
        }
        return netPrice * (1 + vatValue);
    }
}
