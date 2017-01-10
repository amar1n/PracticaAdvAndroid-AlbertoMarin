package io.keepcoding.madridguide.manager.db.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import io.keepcoding.madridguide.manager.db.MadridActivityDAO;
import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.model.Shop;

public class MadridGuideProvider extends ContentProvider {
    public static final String MADRIDGUIDE_PROVIDER = "io.keepcoding.madridguide.provider";

    public static final Uri SHOPS_URI = Uri.parse("content://" + MADRIDGUIDE_PROVIDER + "/shops");
    public static final Uri MADRIDACTIVITIES_URI = Uri.parse("content://" + MADRIDGUIDE_PROVIDER + "/madridactivities");

    // Create the constants used to differentiate between the different URI requests.
    private static final int ALL_SHOPS = 1;
    private static final int SINGLE_SHOP = 2;
    private static final int ALL_MADRIDACTIVITIES = 3;
    private static final int SINGLE_MADRIDACTIVITY = 4;

    private static final UriMatcher uriMatcher;

    // Populate the UriMatcher object, where a URI ending in ‘elements’ will correspond to a request for all items, and ‘elements/[rowID]’ represents a single row.
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // content://io.keepcoding.madridguide.provider/shops
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops", ALL_SHOPS);
        // content://io.keepcoding.madridguide.provider/shops/363
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "shops/#", SINGLE_SHOP);
        // content://io.keepcoding.madridguide.provider/madridactivities
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "madridactivities", ALL_MADRIDACTIVITIES);
        // content://io.keepcoding.madridguide.provider/madridactivities/363
        uriMatcher.addURI(MADRIDGUIDE_PROVIDER, "madridactivities/#", SINGLE_MADRIDACTIVITY);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP: {
                String rowID = uri.getPathSegments().get(1);
                ShopDAO dao = new ShopDAO(getContext());
                cursor = dao.queryCursor(Long.parseLong(rowID));
                break;
            }
            case ALL_SHOPS: {
                ShopDAO dao = new ShopDAO(getContext());
                cursor = dao.queryCursor();
                break;
            }
            case SINGLE_MADRIDACTIVITY: {
                String rowID = uri.getPathSegments().get(1);
                MadridActivityDAO dao = new MadridActivityDAO(getContext());
                cursor = dao.queryCursor(Long.parseLong(rowID));
                break;
            }
            case ALL_MADRIDACTIVITIES: {
                MadridActivityDAO dao = new MadridActivityDAO(getContext());
                cursor = dao.queryCursor();
                break;
            }

            default:
                break;
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String type = null;

        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP:
                type = "vnd.android.cursor.item/vnd.io.keepcoding.madridguide.provider";
                break;
            case ALL_SHOPS:
                type = "vnd.android.cursor.dir/vnd.io.keepcoding.madridguide.provider";
                break;
            case SINGLE_MADRIDACTIVITY:
                type = "vnd.android.cursor.item/vnd.io.keepcoding.madridguide.provider";
                break;
            case ALL_MADRIDACTIVITIES:
                type = "vnd.android.cursor.dir/vnd.io.keepcoding.madridguide.provider";
                break;
            default:
                break;
        }

        return type;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        // content://io.keepcoding.madridguide.provider/shops
        // content://io.keepcoding.madridguide.provider/madridactivities

        Long id = null;
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP: {
                break;
            }
            case ALL_SHOPS: {
                ShopDAO dao = new ShopDAO(getContext());
                Shop shop = ShopDAO.getShopFromContentValues(contentValues);
                id = dao.insert(shop);
                break;
            }
            case SINGLE_MADRIDACTIVITY: {
                break;
            }
            case ALL_MADRIDACTIVITIES: {
                MadridActivityDAO dao = new MadridActivityDAO(getContext());
                MadridActivity madridActivity = MadridActivityDAO.getMadridActivityFromContentValues(contentValues);
                id = dao.insert(madridActivity);
                break;
            }
            default:
                break;
        }

        if (id == null || id == -1) {
            return null;
        }

        // content://io.keepcoding.madridguide.provider/shops/5353
        // content://io.keepcoding.madridguide.provider/madridactivities/5353

        // Construct and return the URI of the newly inserted row.
        Uri insertedUri = null;
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP: {
                break;
            }
            case ALL_SHOPS: {
                insertedUri = ContentUris.withAppendedId(SHOPS_URI, id);
                break;
            }
            case SINGLE_MADRIDACTIVITY: {
                break;
            }
            case ALL_MADRIDACTIVITIES: {
                insertedUri = ContentUris.withAppendedId(MADRIDACTIVITIES_URI, id);
                break;
            }
            default:
                break;
        }

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(insertedUri, null);

        return insertedUri;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereSelection) {
        // content://io.keepcoding.madridguide.provider/shops/72
        // content://io.keepcoding.madridguide.provider/madridactivities/57

        int deleteCount = 0;

        // If this is a row URI, limit the deletion to the specified row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_SHOP: {
                String rowID = uri.getPathSegments().get(1);
                ShopDAO dao = new ShopDAO(getContext());
                deleteCount = dao.delete(Long.parseLong(rowID));
                break;
            }
            case ALL_SHOPS: {
                ShopDAO dao = new ShopDAO(getContext());
                deleteCount = dao.deleteAll();
                break;
            }
            case SINGLE_MADRIDACTIVITY: {
                String rowID = uri.getPathSegments().get(1);
                MadridActivityDAO dao = new MadridActivityDAO(getContext());
                deleteCount = dao.delete(Long.parseLong(rowID));
                break;
            }
            case ALL_MADRIDACTIVITIES: {
                MadridActivityDAO dao = new MadridActivityDAO(getContext());
                deleteCount = dao.deleteAll();
                break;
            }
            default:
                break;
        }

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the number of deleted items.
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}


