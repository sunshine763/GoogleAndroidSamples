package com.xing.sample.activityscenetransitionbasic;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Item mItem;
    private ImageView imageview_header;
    private TextView textview_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        mItem=Item.getItem(getIntent().getIntExtra(Constants.EXTRA_PARAM_ID,0));

        imageview_header = (ImageView) findViewById(R.id.imageview_header);
        textview_title = (TextView) findViewById(R.id.textview_title);

        ViewCompat.setTransitionName(imageview_header,Constants.VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(textview_title,Constants.VIEW_NAME_HEADER_TITLE);

        loadItem();
    }

    private void loadItem(){
        textview_title.setText(getString(R.string.image_header,mItem.getName(),mItem.getAuthor()));

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP && addTransitionListener()){
            loadThumbnail();
        }else {
            loadFullSizeImage();
        }
    }

    private boolean addTransitionListener() {
        Transition transition = getWindow().getSharedElementEnterTransition();
        if(transition!=null){
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    loadFullSizeImage();
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }

            });
            return true;
        }
        return false;
    }

    private void loadThumbnail(){
        Picasso.with(imageview_header.getContext())
                .load(mItem.getThumbnailUrl())
                .noFade()
                .into(imageview_header);
    }

    private void loadFullSizeImage(){
        Picasso.with(imageview_header.getContext())
                .load(mItem.getPhotoUrl())
                .noFade()
                .noPlaceholder()
                .into(imageview_header);
    }
}
