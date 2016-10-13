package com.xing.sample.actionbarcompat_shareactionprovider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xing.sample.actionbarcompat_shareactionprovider.content.ContentItem;

import java.util.ArrayList;

/**
 * 这个sample展示了如何使用{@link ShareActionProvider}去分享信息
 * 注：图片可能会分享失败是因为am.openFd(assetName)这个API版本比较高，需要接收分享信息的APP做相应的处理
 */
public class MainActivity extends ActionBarActivity {

    private ArrayList<ContentItem> mItems=getSampleContent();

    private ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem shareItem = menu.findItem(R.id.menu_share);
        mShareActionProvider= (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        int currentViewPagerItem = ((ViewPager) findViewById(R.id.viewpager)).getCurrentItem();
        setShareIntent(currentViewPagerItem);
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareIntent(int position){
        if(mShareActionProvider!=null){
            ContentItem item = mItems.get(position);
            Intent shareIntent = item.getShareIntent(this);
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    PagerAdapter mPagerAdapter=new PagerAdapter() {
        LayoutInflater mLayoutInflater;
        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(mLayoutInflater==null){
                mLayoutInflater=LayoutInflater.from(MainActivity.this);
            }

            ContentItem item = mItems.get(position);
            switch (item.contentType){
                case ContentItem.CONTENT_TYPE_TEXT:
                    TextView tv = (TextView) mLayoutInflater.inflate(R.layout.item_text,container, false);
                    tv.setText(item.contentResourceId);
                    container.addView(tv);
                    return tv;
                case ContentItem.CONTENT_TYPE_IMAGE:
                    ImageView iv = (ImageView) mLayoutInflater.inflate(R.layout.item_image, container, false);
                    iv.setImageURI(item.getContentUri());
                    container.addView(iv);
                    return iv;
            }
            return null;
        }
    };

    ViewPager.OnPageChangeListener mOnPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setShareIntent(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    ArrayList<ContentItem> getSampleContent(){
        ArrayList<ContentItem> items=new ArrayList<>();

        items.add(new ContentItem(ContentItem.CONTENT_TYPE_IMAGE, "photo_1.jpg"));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_TEXT, R.string.quote_1));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_TEXT, R.string.quote_2));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_IMAGE, "photo_2.jpg"));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_TEXT, R.string.quote_3));
        items.add(new ContentItem(ContentItem.CONTENT_TYPE_IMAGE, "photo_3.jpg"));

        return items;
    }
}
