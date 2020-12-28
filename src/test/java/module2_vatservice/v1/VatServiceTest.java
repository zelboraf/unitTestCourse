package module2_vatservice.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class VatServiceTest {
    VatService vatService;

    @Test
    @DisplayName("Should calculate gross price for default VAT")
    void shouldCalculateGrossPriceForDefaultVat() throws IncorrectVatException {
        // GIVEN
        Product product = generateProduct();

        // WHEN
        double result = vatService.getGrossPriceForDefaultVat(product);

        // THEN
        // JUnit5
        assertEquals(123.0, result);
        // AssertJ
        assertThat(result).isEqualTo(123.0);
    }

    @Test
    @DisplayName("Should calculate gross price for custom VAT")
    void shouldCalculateGrossPriceForCustomVat() throws IncorrectVatException {
        // GIVEN
        Product product = generateProduct();
        double vatValue = 0.08;

        // WHEN
        double result = vatService.getGrossPriceForCustomVat(product.getNetPrice(), vatValue);

        // THEN
        // JUnit5
        assertEquals(108.0, result);
        // AssertJ
        assertThat(result).isEqualTo(108.0);
    }

    @Test
    @DisplayName("Should throw exception when VAT is too high")
    void shouldThrowExceptionWhenVatIsTooHigh() {
        // GIVEN
        Product product = generateProduct();
        double vatValue = 1.01;
        String expectedMessage = "VAT can't be higher than 100%";

        //// JUnit5
        // WHEN
        IncorrectVatException exception = assertThrows(IncorrectVatException.class, () -> {
            vatService.getGrossPriceForCustomVat(product.getNetPrice(), vatValue);
        });

        // THEN
        assertTrue(exception.getMessage().contains(expectedMessage));

        //// AssertJ
        // WHEN
        Throwable throwable = catchThrowable(() -> {
            vatService.getGrossPriceForCustomVat(product.getNetPrice(), vatValue);
        });

        // THEN
        assertThat(throwable)
                .isInstanceOf(IncorrectVatException.class)
                .hasMessage(expectedMessage);
    }

    @BeforeEach
    void setUpVatService() {
        vatService = new VatService();
    }

    private Product generateProduct() {
        double netPrice = 100.0;
        String id = UUID.randomUUID().toString();
        return new Product(id, netPrice);
    }
}