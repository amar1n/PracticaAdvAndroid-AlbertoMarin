package io.keepcoding.madridguide.manager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;

import static io.keepcoding.madridguide.manager.db.DBConstants.ALL_MADRIDACTIVITY_COLUMNS;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_ADDRESS;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_DESCRIPTION;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_ID;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_IMAGE_URL;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_LATITUDE;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_LOGO_IMAGE_URL;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_LONGITUDE;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_NAME;
import static io.keepcoding.madridguide.manager.db.DBConstants.KEY_MADRIDACTIVITY_URL;
import static io.keepcoding.madridguide.manager.db.DBConstants.TABLE_MADRIDACTIVITY;

public class MadridActivityDAO implements DAOPersistable<MadridActivity> {
    private WeakReference<Context> context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public MadridActivityDAO(Context context, DBHelper dbHelper) {
        this.context = new WeakReference<>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();
    }

    public MadridActivityDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }

    /**
     * Insert a MadridActivity in DB
     * @param activity shouldn't be null
     * @return 0 is activity is null, id if insert is OK, INVALID_ID if insert fails
     */
    @Override
    public long insert(@NonNull MadridActivity activity) {
        if (activity == null) {
            return 0;
        }
        // insert

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try { // Null Column Hack
            id = db.insert(TABLE_MADRIDACTIVITY, KEY_MADRIDACTIVITY_NAME, this.getContentValues(activity));
            activity.setId(id);
            db.setTransactionSuccessful();  // COMMIT
        } finally {
            db.endTransaction();
        }

        return id;
    }

    public static @NonNull ContentValues getContentValues(final @NonNull MadridActivity madridActivity) {
        final ContentValues contentValues = new ContentValues();

        if (madridActivity == null) {
            return contentValues;
        }

        contentValues.put(KEY_MADRIDACTIVITY_NAME, madridActivity.getName());
        contentValues.put(KEY_MADRIDACTIVITY_ADDRESS, madridActivity.getAddress());
        contentValues.put(KEY_MADRIDACTIVITY_DESCRIPTION, madridActivity.getDescription());
        contentValues.put(KEY_MADRIDACTIVITY_IMAGE_URL, madridActivity.getImageUrl());
        contentValues.put(KEY_MADRIDACTIVITY_LOGO_IMAGE_URL, madridActivity.getLogoImgUrl());
        contentValues.put(KEY_MADRIDACTIVITY_LATITUDE, madridActivity.getLatitude());
        contentValues.put(KEY_MADRIDACTIVITY_LONGITUDE, madridActivity.getLongitude());
        contentValues.put(KEY_MADRIDACTIVITY_URL, madridActivity.getUrl());

        return contentValues;
    }

    public static @NonNull
    MadridActivity getMadridActivityFromContentValues(final @NonNull ContentValues contentValues) {
        final MadridActivity madridActivity = new MadridActivity(1, "");

        //activity.setId(contentValues.getAsInteger(KEY_SHOP_ID));
        madridActivity.setName(contentValues.getAsString(KEY_MADRIDACTIVITY_NAME));
        madridActivity.setAddress(contentValues.getAsString(KEY_MADRIDACTIVITY_ADDRESS));
        madridActivity.setDescription(contentValues.getAsString(KEY_MADRIDACTIVITY_DESCRIPTION));
        madridActivity.setImageUrl(contentValues.getAsString(KEY_MADRIDACTIVITY_IMAGE_URL));
        madridActivity.setLogoImgUrl(contentValues.getAsString(KEY_MADRIDACTIVITY_LOGO_IMAGE_URL));
        madridActivity.setUrl(contentValues.getAsString(KEY_MADRIDACTIVITY_URL));
        madridActivity.setLatitude(contentValues.getAsFloat(KEY_MADRIDACTIVITY_LATITUDE));
        madridActivity.setLongitude(contentValues.getAsFloat(KEY_MADRIDACTIVITY_LONGITUDE));

        return madridActivity;
    }

    @Override
    public void update(long id, @NonNull MadridActivity data) {

    }

    @Override
    public int delete(long id) {
        return db.delete(TABLE_MADRIDACTIVITY, KEY_MADRIDACTIVITY_ID + " = " + id, null);  // 1st way
        // db.delete(TABLE_SHOP, KEY_SHOP_ID + " = ?", new String[]{ "" + id });  // 2nd way
        //db.delete(TABLE_SHOP, KEY_SHOP_ID + " = ? AND " + KEY_SHOP_NAME + "= ?" ,
        //        new String[]{ "" + id, "pepito" });  // 2nd way

    }

    @Override
    public int deleteAll() {
        return db.delete(TABLE_MADRIDACTIVITY, null, null);
    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        Cursor c = db.query(TABLE_MADRIDACTIVITY, ALL_MADRIDACTIVITY_COLUMNS, null, null, null, null, KEY_MADRIDACTIVITY_ID);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    @Override
    public @Nullable
    MadridActivity query(final long id) {
        Cursor c = db.query(TABLE_MADRIDACTIVITY, ALL_MADRIDACTIVITY_COLUMNS, KEY_MADRIDACTIVITY_ID + " = " + id, null, null, null, KEY_MADRIDACTIVITY_ID);

        if (c != null && c.getCount() == 1) {
            c.moveToFirst();
        } else {
            return null;
        }

        MadridActivity madridActivity = getMadridActivity(c);

        return madridActivity;
    }

    @NonNull
    public static MadridActivity getMadridActivity(Cursor c) {
        long identifier = c.getLong(c.getColumnIndex(KEY_MADRIDACTIVITY_ID));
        String name = c.getString(c.getColumnIndex(KEY_MADRIDACTIVITY_NAME));
        MadridActivity activity = new MadridActivity(identifier, name);

        activity.setAddress(c.getString(c.getColumnIndex(KEY_MADRIDACTIVITY_ADDRESS)));
        activity.setDescription(c.getString(c.getColumnIndex(KEY_MADRIDACTIVITY_DESCRIPTION)));
        activity.setImageUrl(c.getString(c.getColumnIndex(KEY_MADRIDACTIVITY_IMAGE_URL)));
        activity.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_MADRIDACTIVITY_LOGO_IMAGE_URL)));
        activity.setLatitude(c.getFloat(c.getColumnIndex(KEY_MADRIDACTIVITY_LATITUDE)));
        activity.setLongitude(c.getFloat(c.getColumnIndex(KEY_MADRIDACTIVITY_LONGITUDE)));
        activity.setUrl(c.getString(c.getColumnIndex(KEY_MADRIDACTIVITY_URL)));
        return activity;
    }

    @Nullable
    @Override
    public List<MadridActivity> query() {
        Cursor c = queryCursor();

        if (c == null || !c.moveToFirst()) {
            return null;
        }

        List<MadridActivity> madridActivities = new LinkedList<>();

        c.moveToFirst();
        do {
            MadridActivity madridActivity = getMadridActivity(c);
            madridActivities.add(madridActivity);
        } while (c.moveToNext());

        return madridActivities;
    }

    public Cursor queryCursor(long id) {
        Cursor c = db.query(TABLE_MADRIDACTIVITY, ALL_MADRIDACTIVITY_COLUMNS, "ID = " + id, null, null, null, KEY_MADRIDACTIVITY_ID);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }

    @NonNull
    public static MadridActivities getMadridActivities(Cursor data) {
        List<MadridActivity> madridActivityList = new LinkedList<>();

        while (data.moveToNext()) {
            MadridActivity madridActivity = MadridActivityDAO.getMadridActivity(data);
            madridActivityList.add(madridActivity);
        }

        return MadridActivities.build(madridActivityList);
    }
}
