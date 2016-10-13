package com.xing.sample.actionbarcompat_listpopupmenu;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 这个例子展示了用PopupMenu建立的list,list的每个item都有下拉菜单
 *
 * 代码在{@link PopupListFragment}
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
