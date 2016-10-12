package com.xing.sample.actionbarcompat_listpopupmenu;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by lenovo on 2016/10/12.
 */
public class SampleTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mTestActivity;
    private PopupListFragment mTestFragment;

    public SampleTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Starts the activity under test using the default Intent with:
        // action = {@link Intent#ACTION_MAIN}
        // flags = {@link Intent#FLAG_ACTIVITY_NEW_TASK}
        // All other fields are null or empty.
        mTestActivity=getActivity();
        mTestFragment= (PopupListFragment) mTestActivity.getSupportFragmentManager().getFragments().get(0);
    }

    public void testPreconditions(){
        //给message增加一些你的断言，这些断言会在测试失败的时候显示
        assertNotNull("mTestActivity is null",mTestActivity);
        assertNotNull("mTestFragment is null",mTestFragment);
    }
}
