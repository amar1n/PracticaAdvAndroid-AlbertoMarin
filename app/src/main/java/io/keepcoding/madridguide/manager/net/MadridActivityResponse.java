package io.keepcoding.madridguide.manager.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MadridActivityResponse {
    @SerializedName("result")
    List<MadridActivityEntity> result;
}
