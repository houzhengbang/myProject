package com.hzb.test.ui.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.git.navmenu.NavMenuLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hzb.test.R;
import com.hzb.test.ui.fragment.MainFragment;
import com.hzb.test.ui.fragment.MyFragment;
import com.hzb.utils.base.Base2Activity;
import com.hzb.utils.util.T;

public class HomeActivity extends Base2Activity {


    private BottomNavigationView bottomNavigationView;
    private MainFragment work_fragment;
    private MyFragment message_fragment;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment

    NavMenuLayout mNavMenuLayout;
    private int[] iconResSelected = {R.drawable.home_bottom_tab_icon_message_highlight, R.drawable.home_bottom_tab_icon_work_highlight};
    private int[] iconRes = {R.drawable.home_bottom_tab_icon_message_normal, R.drawable.home_bottom_tab_icon_work_normal};
    private String[] textRes = {"首页", "个人中心"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        initFragment();


        mNavMenuLayout = (NavMenuLayout) findViewById(R.id.nav_layout);

        mNavMenuLayout.setIconRes(iconRes)//设置未选中图标
                .setIconResSelected(iconResSelected)//设置选中图标
                .setTextRes(textRes)//设置文字
                .setIconSize(60, 60)//设置图标大小
//                .setIconSize(0, 60,60)//设置指定位置的图标
//                .setTextSize(20)//设置文字大小
//                .setTextSize(0, 20)//指定位置的文字大小
//                .setTextColor(Color.GRAY)//未选中状态下文字颜色
//                .setTextColorSelected(Color.RED)//选中状态下文字颜色
//                .setTextColor(0, Color.YELLOW)//设置指定位置下文字颜色
//                .setTextColorSelected(0, Color.BLUE)//设置指定位置下选中状态文字颜色
//                .setMarginTop(PixelUtil.dpToPx(MainActivity.this, 5))//
//                .setMarginTop(1, PixelUtil.dpToPx(MainActivity.this, 10))
//                .setMsg(0, "99+")//设置显示消息
//                .setMsg(1, "NEW")//设置显示消息
//                .showRedPoint(0)//设置显示红点
//                .hideMsg(0)//隐藏消息
//                .hideMsg(1)//隐藏消息
//                .hideRedPoint(2)//隐藏红点
                .setSelected(0);//设置选中的位置

        //选中的点击事件
        mNavMenuLayout.setOnItemSelectedListener(new NavMenuLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {

                if (lastfragment != position) {
                    switchFragment(lastfragment, position);
                    lastfragment = position;
                }

            }
        });
        //已选中状态下的点击事件
        mNavMenuLayout.setOnItemReSelectedListener(new NavMenuLayout.OnItemReSelectedListener() {
            @Override
            public void onItemReSelected(int position) {

            }
        });
    }


    private void initFragment() {
        work_fragment = new MainFragment();
        message_fragment = new MyFragment();

        fragments = new Fragment[]{work_fragment, message_fragment};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, work_fragment).show(work_fragment).commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_message: {
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;
//                        StatusBarUtil.setStatusBarColor(MainActivity.this, Color.rgb(255, 210, 117));//设置状态栏的颜色

                    }
                    return true;
                }

                case R.id.action_work: {
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;
//                        StatusBarUtil.setStatusBarColor(MainActivity.this, Color.rgb(253, 110, 100));//设置状态栏的颜
                    }
                    return true;
                }
            }
            return false;
        }
    };


    //切换Fragment
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.frame_fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }


    private long lastTime;

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastTime < 2000) {
            System.exit(0);
            super.onBackPressed();
        } else {
            T.showShort("再按一次退出应用");
            lastTime = System.currentTimeMillis();
        }
    }


}