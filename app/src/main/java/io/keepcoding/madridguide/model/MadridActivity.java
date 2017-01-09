package io.keepcoding.madridguide.model;

import java.io.Serializable;

public class MadridActivity implements Serializable {
    private long id;
    private String name;
    private String imageUrl;
    private String logoImgUrl;
    private String address;
    private String url;
    private String description;
    private float latitude;
    private float longitude;

    public MadridActivity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private MadridActivity() {
    }

    public long getId() {
        return id;
    }

    public MadridActivity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MadridActivity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MadridActivity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public MadridActivity setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MadridActivity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MadridActivity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MadridActivity setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public MadridActivity setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public MadridActivity setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }
}
