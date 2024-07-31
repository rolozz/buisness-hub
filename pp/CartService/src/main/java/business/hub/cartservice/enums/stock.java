package business.hub.cartservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum stock {

    PRODUCT_STOCK0(1),
    PRODUCT_STOCK5(0.95),
    PRODUCT_STOCK10(0.9),
    PRODUCT_STOCK15(0.85),
    PRODUCT_STOCK20(0.8),
    PRODUCT_STOCK25(0.75);

    private final double stock;
}
