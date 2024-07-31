package business.hub.cartservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum keeping {

    PRODUCT_SHOP("Магазин"),
    PRODUCT_WAREHOUSE("Склад");

    private final String keeping;

}
