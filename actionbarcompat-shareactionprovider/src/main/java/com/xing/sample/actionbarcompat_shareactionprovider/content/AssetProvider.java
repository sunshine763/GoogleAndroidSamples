package com.xing.sample.actionbarcompat_shareactionprovider.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 可以从应用的assets文件夹读取文件的简单的ContentProvider
 * 主要功能在{@link #openAssetFile(android.net.Uri, String)}.
 */
public class AssetProvider extends ContentProvider{

    public static String CONTENT_URI ="com.xing.sample.actionbarcompat_shareactionprovider";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //不支持query请求
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        //不支持返回data type
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //不支持insert请求
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //不支持delete请求
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //不支持update请求
        return 0;
    }

    @Nullable
    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        //获得文件名
        String assetName = uri.getLastPathSegment();

        if(TextUtils.isEmpty(assetName)){
            throw new FileNotFoundException();
        }

        try {
            //返回文件的descriptor
            AssetManager am = getContext().getAssets();
            return am.openFd(assetName);
        } catch (IOException e) {
            e.printStackTrace();
            return super.openAssetFile(uri, mode);
        }
    }
}
