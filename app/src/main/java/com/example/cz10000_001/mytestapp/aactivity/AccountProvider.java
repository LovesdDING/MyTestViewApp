package com.example.cz10000_001.mytestapp.aactivity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 数据的存储
 * Created by cz10000_001 on 2018/2/26.
 */

public class AccountProvider extends ContentProvider {

    private static final String TAG = "AccountProvider";

    public static final int MATCH_CODE_ITEM = 1;
    public static final int MATCH_CODE_ITEM_ID = 2;
    public static final String AUTHORITIE = "com.example.cz10000_001.mytestapp.aactivity.AccountProvider";  //作者信息 该类的path 自己看着改

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITIE, AccountTable.TABLE_NAME, MATCH_CODE_ITEM);
        MATCHER.addURI(AUTHORITIE, AccountTable.TABLE_NAME + "/#", MATCH_CODE_ITEM_ID);
    }


    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (MATCHER.match(uri)) {
            case MATCH_CODE_ITEM:
                return AccountDBHelper.getInstance().getWritableDatabase().query(AccountTable.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
            case MATCH_CODE_ITEM_ID:
                String id = uri.getPathSegments().get(1);
                return AccountDBHelper.getInstance().getWritableDatabase().query(AccountTable.TABLE_NAME,
                        projection, AccountTable.ACCOUNT_ID + "=?", new String[]{id}, null, null, sortOrder);
            default:
                Log.i(TAG, "didn't match anything");
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowid = AccountDBHelper.getInstance().getWritableDatabase().insert(AccountTable.TABLE_NAME,
                null, values);
        Uri insertUri = ContentUris.withAppendedId(uri, rowid);
        return insertUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Delete not supported");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return AccountDBHelper.getInstance().getWritableDatabase().update(AccountTable.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
