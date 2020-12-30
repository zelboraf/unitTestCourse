package module2_vatservice.v2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class VatServiceTest {
    VatService vatService;
    VatProvider vatProvider;

    @BeforeEach
    void setUp() {
        vatProvider = Mockito.mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }

    @Test
    @DisplayName("Should calculate gross price for default VAT")
    void shouldCalculateGrossPriceForDefaultVat() throws IncorrectVatException {
        // GIVEN
        String type = "Toys";
        Product product = generateProduct(type);
        when(vatProvider.getDefaultVat()).thenReturn(0.23);

        // WHEN
        double result = vatService.getGrossPriceForDefaultVat(product);

        // THEN
        assertThat(result).isEqualTo(123.0);
        verify(vatProvider).getDefaultVat();
    }

    @Test
    @DisplayName("Should calculate gross price for custom VAT")
    void shouldCalculateGrossPriceForCustomVat() throws IncorrectVatException {
        // GIVEN
        String type = "Books";
        Product product = generateProduct(type);
        when(vatProvider.getVatForType(type)).thenReturn(0.08);

        // WHEN
        double result = vatService.getGrossPriceForCustomVat(product);

        // THEN
        assertThat(result).isEqualTo(108.0);
        verify(vatProvider).getVatForType(type);
    }

    @Test
    @DisplayName("Should throw exception when VAT is too high")
    void shouldThrowExceptionWhenVatIsTooHigh() {
        // GIVEN
        String type = "Food";
        Product product = generateProduct(type);
        String expectedMessage = "VAT can't be higher than 100%";
        when(vatProvider.getVatForType(type)).thenReturn(1.01);

        // WHEN
        Throwable throwable = catchThrowable(() -> {
            vatService.getGrossPriceForCustomVat(product);
        });

        // THEN
        assertThat(throwable)
                .isInstanceOf(IncorrectVatException.class)
                .hasMessage(expectedMessage);
        verify(vatProvider).getVatForType(type);

    }

    private Product generateProduct(String type) {
        UUID id = UUID.randomUUID();
        double netPrice = 100.0;
        return new Product(id, netPrice, type);
    }
}