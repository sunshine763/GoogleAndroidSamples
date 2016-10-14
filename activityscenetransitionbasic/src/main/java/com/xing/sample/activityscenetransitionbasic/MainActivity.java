package com.xing.sample.activityscenetransitionbasic;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.grid);
        mGridView.setOnItemClickListener(this);
        mGridView.setAdapter(new GridAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item= (Item) parent.getItemAtPosition(position);
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra(Constants.EXTRA_PARAM_ID,item.getId());
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair<View, String>(view.findViewById(R.id.imageview_header), Constants.VIEW_NAME_HEADER_IMAGE),
                new Pair<View, String>(view.findViewById(R.id.textview_name), Constants.VIEW_NAME_HEADER_TITLE)
        );
        ActivityCompat.startActivity(this,intent,activityOptionsCompat.toBundle());

    }

    class GridAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return Item.ITEMS.length;
        }

        @Override
        public Item getItem(int position) {
            return Item.ITEMS[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=getLayoutInflater().inflate(R.layout.grid_item,parent,false);
            }

            Item item = getItem(position);
            ImageView imageview_header = (ImageView) convertView.findViewById(R.id.imageview_header);
            Picasso.with(imageview_header.getContext()).load(item.getThumbnailUrl()).into(imageview_header);

            TextView textview_name = (TextView) convertView.findViewById(R.id.textview_name);
            textview_name.setText(item.getName());
            return convertView;
        }
    }
}
