package com.example.cz10000_001.mytestapp.aactivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowContentResolver;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.*;

/**
 * 共享数据存储的测试类
 * Created by cz10000_001 on 2018/2/26.
 */
public class AccountProviderTest extends AndroidTestCase {
    private ContentResolver mContentResolver;
    private ShadowContentResolver mShadowContentResolver;
    private AccountProvider mProvider;
    private String AUTHORITY = "com.example.cz10000_001.mytestapp.aactivity.AccountProvider";
    private Uri URI_PERSONAL_INFO = Uri.parse("content://" + AUTHORITY + "/" + AccountTable.TABLE_NAME);



    @Before
    public void setUp() {
        ShadowLog.stream = System.out;

        mProvider = new AccountProvider();
        mContentResolver = RuntimeEnvironment.application.getContentResolver();
        mShadowContentResolver = Shadows.shadowOf(mContentResolver);

        mProvider.onCreate();

    }

    @After
    public void tearDown() {
        AccountUtil.resetSingleton(AccountDBHelper.class, "mAccountDBHelper");
    }

    /**
     * 查询表测试
     * @throws Exception
     */
    @Test
    public void query() throws Exception {
        ContentValues contentValues1 = AccountUtil.getContentValues("1");
        ContentValues contentValues2 = AccountUtil.getContentValues("2");

        mShadowContentResolver.insert(URI_PERSONAL_INFO, contentValues1);
        mShadowContentResolver.insert(URI_PERSONAL_INFO, contentValues2);

        //查询所有数据
        Cursor cursor1 = mShadowContentResolver.query(URI_PERSONAL_INFO, null, null, null, null);
        assertEquals(cursor1.getCount(), 2);

        //查询id为2的数据
        Uri uri = ContentUris.withAppendedId(URI_PERSONAL_INFO, 2);
        Cursor cursor2 = mShadowContentResolver.query(uri, null, null, null, null);
        assertEquals(cursor2.getCount(), 1);
    }

    /**
     * 测试查询表中不存在的数据
     */
    @Test
    public void queryNoMatch() {
        Uri noMathchUri = Uri.parse("content://com.example.cz10000_001.mytestapp.aactivity.AccountProvider/tabel/");
        Cursor cursor = mShadowContentResolver.query(noMathchUri, null, null, null, null);
        assertNull(cursor);
    }

    /**
     * 插入数据测试
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        ContentValues contentValues1 = AccountUtil.getContentValues("1");
        mShadowContentResolver.insert(URI_PERSONAL_INFO, contentValues1);
        Cursor cursor = mShadowContentResolver.query(URI_PERSONAL_INFO, null, AccountTable.ACCOUNT_ID + "=?", new String[]{"1"}, null);
        assertEquals(cursor.getCount(), 1);
        cursor.close();
    }

    /**
     * 测试 删除表中数据
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        try {
            mShadowContentResolver.delete(URI_PERSONAL_INFO, null, null);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Delete not supported");
        }
    }

    /**
     * 测试更新表数据
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        ContentValues contentValues = AccountUtil.getContentValues("2");
        Uri uri = mShadowContentResolver.insert(URI_PERSONAL_INFO, contentValues);

        contentValues.put(AccountTable.ACCOUNT_NAME, "account_update");
        int update = mShadowContentResolver.update(uri, contentValues, null, null);
        assertEquals(update, 1);

        Cursor cursor = mShadowContentResolver.query(URI_PERSONAL_INFO, null, AccountTable.ACCOUNT_ID + "=?", new String[]{"2"}, null);
        cursor.moveToFirst();
        String accountName = cursor.getString(cursor.getColumnIndex(AccountTable.ACCOUNT_NAME));
        assertEquals(accountName, "account_update");
        cursor.close();
    }

}