package com.xing.sample.actionbarcompat_basic;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 这个例子用展示如何用ActionBarCompat去创建一个basic Activity
 * 可以从xml添加menu，也可以从代码添加menu
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *
     * 使用这个方法实例化菜单，并将你的item添加上去
     * 如果你添加了item并且想将它显示出来则 return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //从resource中实例化菜单
        getMenuInflater().inflate(R.menu.main,menu);
        //用code添加item,使用ids.xml产生的id可以使得所有的menu的id不同
        MenuItem locationItem = menu.add(0, R.id.menu_location, 0, R.string.menu_location);
        locationItem.setIcon(R.drawable.ic_action_location);
        //使用MenuItemCompat调用item相关的方法
        MenuItemCompat.setShowAsAction(locationItem,MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    /**
     *
     * 当菜单的item被点击时调用此方法，你要处理返回时间时应该return true;
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_refresh:
                // Here we might start a background refresh task
                Toast.makeText(this, R.string.menu_refresh, Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_location:
                // Here we might call LocationManager.requestLocationUpdates()
                Toast.makeText(this, R.string.menu_location, Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                // Here we would open up our settings activity
                Toast.makeText(this, R.string.menu_settings, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
