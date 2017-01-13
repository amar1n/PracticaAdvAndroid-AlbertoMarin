package io.keepcoding.madridguide.model.mappers;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import io.keepcoding.madridguide.manager.net.ShopEntity;
import io.keepcoding.madridguide.model.Shop;

public class ShopEntityShopMapper {
    public List<Shop> map(List<ShopEntity> shopEntities) {
        List<Shop> result = new LinkedList<>();

        for (ShopEntity entity: shopEntities) {
            Shop shop = new Shop(entity.getId(), entity.getName());
            // detect current lang
            if (Locale.getDefault().getLanguage().startsWith("es")) {
                shop.setDescription(entity.getDescriptionEs());
            } else {
                shop.setDescription(entity.getDescriptionEn());
            }
            shop.setLogoImgUrl(entity.getLogoImg());
            shop.setLatitude(entity.getLatitude());
            shop.setLongitude(entity.getLongitude());

            // ...

            result.add(shop);
        }

        return result;
    }
}
