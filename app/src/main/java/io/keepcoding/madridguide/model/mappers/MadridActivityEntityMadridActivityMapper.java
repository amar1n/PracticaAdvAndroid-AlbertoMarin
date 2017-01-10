package io.keepcoding.madridguide.model.mappers;

import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.manager.net.MadridActivityEntity;
import io.keepcoding.madridguide.model.MadridActivity;

public class MadridActivityEntityMadridActivityMapper {
    public List<MadridActivity> map(List<MadridActivityEntity> madridActivityEntities) {
        List<MadridActivity> result = new LinkedList<>();

        for (MadridActivityEntity entity: madridActivityEntities) {
            MadridActivity madridActivity = new MadridActivity(entity.getId(), entity.getName());
            // detect current lang
            madridActivity.setDescription(entity.getDescriptionEs());
            madridActivity.setLogoImgUrl(entity.getLogoImg());

            // ...

            result.add(madridActivity);
        }

        return result;
    }
}
