package com.hzb.test.ui.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.hzb.test.R;
import com.hzb.test.ui.activity.login.LoginActivity;
import com.hzb.utils.base.Base2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by houzhengbang
 * @description
 * @desc :引导页
 */
public class GuideActivity extends Base2Activity implements GuideControl.ISplashView, View.OnClickListener {


    private ViewPager mViewPager;
    //容器
    private List<View> mList = new ArrayList<>();
    private View view1, view2, view3, view4;
    //小圆点
    private ImageView point1, point2, point3, point4;
    //跳过
    private Button btn_back;
    private GuidePresenter guidePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            View decorView = window.getDecorView();
//            decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
//                @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
//                @Override
//                public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
//                    WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
//                    return defaultInsets.replaceSystemWindowInsets(
//                            defaultInsets.getSystemWindowInsetLeft(),
//                            0,
//                            defaultInsets.getSystemWindowInsetRight(),
//                            defaultInsets.getSystemWindowInsetBottom());
//                }
//            });
//            ViewCompat.requestApplyInsets(decorView);
//            //将状态栏设成透明，如不想透明可设置其他颜色
//            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
//        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏



//        boolean firstOpen = AppUtils.isFirstOpen();
//        if (!firstOpen) {
//            AppUtils.setFirstOpen(true);
//        } else {
//            if (!SharedPreferenceUtils.getIsLogin(this)) {
//                goNextactive(1);
//            } else {
//                goNextactive(2);
//            }
//        }
        guidePresenter = new GuidePresenter(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initViews();
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }


    //初始化View
    private void initViews() {

        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);
        point4 = (ImageView) findViewById(R.id.point4);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        //设置默认图片
        setPointImg(true, false, false, false);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        view1 = View.inflate(this, R.layout.guide_pager_one, null);
        view2 = View.inflate(this, R.layout.guide_pager_two, null);
        view3 = View.inflate(this, R.layout.guide_pager_three, null);
        view4 = View.inflate(this, R.layout.guide_pager_four, null);
        view4.findViewById(R.id.btn_start).setOnClickListener(this);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        mList.add(view4);

        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());

        //监听ViewPager滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //pager切换
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setPointImg(true, false, false, false);
                        btn_back.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImg(false, true, false, false);
                        btn_back.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setPointImg(false, false, true, false);
                        btn_back.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        setPointImg(false, false, false, true);
                        btn_back.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                goNextactive();

                break;
            case R.id.btn_back:
                goNextactive();
                break;

            default:
                break;
        }
    }

    /**
     * 跳转到主页面
     *
     * @param
     */
    private void goNextactive() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
//        if (1 == type) {
//        } else if (2 == type) {
//            intent.setClass(this, MainActivity.class);
//        }
        startActivity(intent);
        finish();// 销毁当前活动界面
    }

    @Override
    public Context getContent() {
        return this;
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mList.get(position));
            //super.destroyItem(container, position, object);
        }
    }

    //设置小圆点的选中效果
    private void setPointImg(boolean isCheck1, boolean isCheck2, boolean isCheck3, boolean isCheck4) {
        if (isCheck1) {
            point1.setBackgroundResource(R.drawable.guide_red);
        } else {
            point1.setBackgroundResource(R.drawable.guide_gray);
        }
        if (isCheck2) {
            point2.setBackgroundResource(R.drawable.guide_red);
        } else {
            point2.setBackgroundResource(R.drawable.guide_gray);
        }

        if (isCheck3) {
            point3.setBackgroundResource(R.drawable.guide_red);
        } else {
            point3.setBackgroundResource(R.drawable.guide_gray);
        }

        if (isCheck4) {
            point4.setBackgroundResource(R.drawable.guide_red);
        } else {
            point4.setBackgroundResource(R.drawable.guide_gray);
        }
    }
}
