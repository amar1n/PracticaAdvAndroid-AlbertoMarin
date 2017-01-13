package io.keepcoding.madridguide.model.mappers;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import io.keepcoding.madridguide.manager.net.MadridActivityEntity;
import io.keepcoding.madridguide.model.MadridActivity;

public class MadridActivityEntityMadridActivityMapper {
    public List<MadridActivity> map(List<MadridActivityEntity> madridActivityEntities) {
        List<MadridActivity> result = new LinkedList<>();

        for (MadridActivityEntity entity: madridActivityEntities) {
            MadridActivity madridActivity = new MadridActivity(entity.getId(), entity.getName());
            // detect current lang
            if (Locale.getDefault().getLanguage().startsWith("es")) {
                madridActivity.setDescription(entity.getDescriptionEs());
            } else {
                madridActivity.setDescription(entity.getDescriptionEn());
            }
            madridActivity.setLogoImgUrl(entity.getLogoImg());
            madridActivity.setLatitude(entity.getLatitude());
            madridActivity.setLongitude(entity.getLongitude());

            // ...

            result.add(madridActivity);
        }

        return result;
    }
}
