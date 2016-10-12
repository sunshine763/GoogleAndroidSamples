package com.xing.sample.actionbarcompat_listpopupmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *  这个ListFragment展示了一个有模拟数据的list,每个view都可点击，
 *  被点击的时候弹出的PopupMenu可以删除item
 */
public class PopupListFragment extends ListFragment implements View.OnClickListener {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> items=new ArrayList<>();
        for(int i=0;i<Cheeses.CHEESES.length;i++){
            items.add(Cheeses.CHEESES[i]);
        }
        setListAdapter(new PopupAdapter(items));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) l.getItemAtPosition(position);
        Toast.makeText(getActivity(), "Item Clicked:"+item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(final View view) {
        //这里google给的注释说post一个runnable可以确保弹出的PopupMenu显示在正确的位置上，因为在PopupMenu
        //显示之前，view可能会改变位置
        //注：我查资料后发现view.post()方法只有当这个view是attached的时候才会执行run方法
        view.post(new Runnable() {
            @Override
            public void run() {
                showPoputMenu(view);
            }
        });
    }

    private void showPoputMenu(View v) {
        final PopupAdapter adapter = (PopupAdapter) getListAdapter();
        final String item = (String) v.getTag();
        PopupMenu popup = new PopupMenu(getActivity(), v);
        popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());
        //增加PopupMenu的点击事件
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_remove:
                        adapter.remove(item);
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }


    /**
     * 一个简单的array Adapter用于产生数据
     */
    class PopupAdapter extends ArrayAdapter<String>{
        public PopupAdapter(ArrayList<String> items) {
            super(getActivity(), R.layout.list_item,android.R.id.text1,items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            View popupButton = view.findViewById(R.id.button_popup);
            //设置popupButton的tag可以在item复用的时候取回popuButton
            popupButton.setTag(getItem(position));
            popupButton.setOnClickListener(PopupListFragment.this);
            return view;
        }
    }
}
