package io.keepcoding.madridguide.manager.db;

public class DBConstants {
    public static final String TABLE_SHOP = "SHOP";
    public static final String TABLE_MADRIDACTIVITY = "MADRIDACTIVITY";

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

    public static final String KEY_MADRIDACTIVITY_ID = "_id";
    public static final String KEY_MADRIDACTIVITY_NAME = "NAME";
    public static final String KEY_MADRIDACTIVITY_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_MADRIDACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String KEY_MADRIDACTIVITY_ADDRESS = "ADDRESS";
    public static final String KEY_MADRIDACTIVITY_URL = "URL";
    public static final String KEY_MADRIDACTIVITY_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_MADRIDACTIVITY_LATITUDE = "latitude";
    public static final String KEY_MADRIDACTIVITY_LONGITUDE = "longitude";

    public static final String[] ALL_MADRIDACTIVITY_COLUMNS = {
            KEY_MADRIDACTIVITY_ID,
            KEY_MADRIDACTIVITY_NAME,
            KEY_MADRIDACTIVITY_IMAGE_URL,
            KEY_MADRIDACTIVITY_LOGO_IMAGE_URL,
            KEY_MADRIDACTIVITY_ADDRESS,
            KEY_MADRIDACTIVITY_URL,
            KEY_MADRIDACTIVITY_DESCRIPTION,
            KEY_MADRIDACTIVITY_LATITUDE,
            KEY_MADRIDACTIVITY_LONGITUDE
    };

    public static final String SQL_SCRIPT_CREATE_ACTIVITY_TABLE =
            "create table " + TABLE_MADRIDACTIVITY
                    + "( "
                    + KEY_MADRIDACTIVITY_ID + " integer primary key autoincrement, "
                    + KEY_MADRIDACTIVITY_NAME + " text not null,"
                    + KEY_MADRIDACTIVITY_IMAGE_URL + " text, "
                    + KEY_MADRIDACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_MADRIDACTIVITY_ADDRESS + " text,"
                    + KEY_MADRIDACTIVITY_URL + " text,"
                    + KEY_MADRIDACTIVITY_LATITUDE + " real,"
                    + KEY_MADRIDACTIVITY_LONGITUDE + " real, "
                    + KEY_MADRIDACTIVITY_DESCRIPTION + " text "
                    + ");";

    public static final String DROP_DATABASE_SCRIPTS = "";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_SHOP_TABLE,
            SQL_SCRIPT_CREATE_ACTIVITY_TABLE
    };
}
