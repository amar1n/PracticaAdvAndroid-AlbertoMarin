package io.keepcoding.madridguide.manager.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopResponse {
    @SerializedName("result")
    List<ShopEntity> result;
}
