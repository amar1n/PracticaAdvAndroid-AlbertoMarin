package io.keepcoding.madridguide.model.mappers;

import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.manager.net.ShopEntity;
import io.keepcoding.madridguide.model.Shop;

public class ShopEntityShopMapper {
    public List<Shop> map(List<ShopEntity> shopEntities) {
        List<Shop> result = new LinkedList<>();

        for (ShopEntity entity: shopEntities) {
            Shop shop = new Shop(entity.getId(), entity.getName());
            // detect current lang
            shop.setDescription(entity.getDescriptionEs());
            shop.setLogoImgUrl(entity.getLogoImg());

            // ...

            result.add(shop);
        }

        return result;
    }
}
