package io.keepcoding.madridguide.manager.db;

public class DBConstants {
    public static final String TABLE_SHOP = "SHOP";
    public static final String TABLE_ACTIVITY = "ACTIVITY";

    // Table field constants
    public static final String KEY_SHOP_ID = "_id";
    public static final String KEY_SHOP_NAME = "NAME";
    public static final String KEY_SHOP_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String KEY_SHOP_ADDRESS = "ADDRESS";
    public static final String KEY_SHOP_URL = "URL";
    public static final String KEY_SHOP_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_SHOP_LATITUDE = "latitude";
    public static final String KEY_SHOP_LONGITUDE = "longitude";

    public static final String KEY_ACTIVITY_ID = "_id";
    public static final String KEY_ACTIVITY_NAME = "NAME";
    public static final String KEY_ACTIVITY_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_ACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String KEY_ACTIVITY_ADDRESS = "ADDRESS";
    public static final String KEY_ACTIVITY_URL = "URL";
    public static final String KEY_ACTIVITY_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_ACTIVITY_LATITUDE = "latitude";
    public static final String KEY_ACTIVITY_LONGITUDE = "longitude";

    public static final String[] ALL_SHOP_COLUMNS = {
            KEY_SHOP_ID,
            KEY_SHOP_NAME,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LOGO_IMAGE_URL,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_URL,
            KEY_SHOP_DESCRIPTION,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE
    };

    public static final String[] ALL_ACTIVITY_COLUMNS = {
            KEY_ACTIVITY_ID,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_LOGO_IMAGE_URL,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_DESCRIPTION,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE
    };

    public static final String SQL_SCRIPT_CREATE_SHOP_TABLE =
            "create table " + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_DESCRIPTION + " text "
                    + ");";

    public static final String SQL_SCRIPT_CREATE_ACTIVITY_TABLE =
            "create table " + TABLE_ACTIVITY
                    + "( "
                    + KEY_ACTIVITY_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_NAME + " text not null,"
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text,"
                    + KEY_ACTIVITY_URL + " text,"
                    + KEY_ACTIVITY_LATITUDE + " real,"
                    + KEY_ACTIVITY_LONGITUDE + " real, "
                    + KEY_ACTIVITY_DESCRIPTION + " text "
                    + ");";

    public static final String DROP_DATABASE_SCRIPTS = "";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_SHOP_TABLE,
            SQL_SCRIPT_CREATE_ACTIVITY_TABLE
    };
}
