package com.xing.sample.actionbarcompat_shareactionprovider.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 这个类封装了content item,以不同的方式引用内容，assert URI 或者resourceID
 */
public class ContentItem {
    //image content type
    public static final int CONTENT_TYPE_IMAGE = 0;
    //text/string content type
    public static final int CONTENT_TYPE_TEXT = 1;

    public final int contentType;
    public final int contentResourceId;
    public final String contentAssetFilePath;

    public ContentItem(int type,int resourceId){
        this.contentType=type;
        this.contentResourceId=resourceId;
        this.contentAssetFilePath=null;
    }

    public ContentItem(int type,String assetFilePath){
        this.contentType=type;
        this.contentAssetFilePath=assetFilePath;
        contentResourceId=0;
    }

    public Uri getContentUri(){
        if(!TextUtils.isEmpty(contentAssetFilePath)){
            //返回asset文件的Uri
            return Uri.parse("content://"+AssetProvider.CONTENT_URI+"/"+contentAssetFilePath);
        }else {
            return null;
        }
    }

    /**
     * @return 要分享的Intent
     */
    public Intent getShareIntent(Context context){
        Intent intent=new Intent(Intent.ACTION_SEND);
        switch (contentType){
            case CONTENT_TYPE_IMAGE:
                intent.setType("image/jpg");
                intent.putExtra(Intent.EXTRA_STREAM,getContentUri());
                break;
            case CONTENT_TYPE_TEXT:
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,context.getString(contentResourceId));
                break;
        }
        return intent;
    }
}
