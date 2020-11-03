package com.hzb.utils.utilTool.screenAdapter.generator;


import com.hzb.utils.utilTool.screenAdapter.constants.DimenTypes;
import com.hzb.utils.utilTool.screenAdapter.utils.MakeUtils;

import java.io.File;


public class DimenGenerator {

    /**
     * 设计稿尺寸(将自己设计师的设计稿的宽度填入)
     */
    private static final int DESIGN_WIDTH = 375;

    /**
     * 设计稿的高度  （将自己设计师的设计稿的高度填入）
     */
    private static final int DESIGN_HEIGHT = 667;

    public static void main(String[] args) {
        int smallest = DESIGN_WIDTH>DESIGN_HEIGHT? DESIGN_HEIGHT:DESIGN_WIDTH;  //     求得最小宽度
        DimenTypes[] values = DimenTypes.values();
        for (DimenTypes value : values) {
            File file = new File("");
            MakeUtils.makeAll(smallest, value, file.getAbsolutePath());
        }
    }


    //                DisplayMetrics dm = new DisplayMetrics();
    //                getWindowManager().getDefaultDisplay().getMetrics(dm);
    //                int width = Math.min(dm.widthPixels,dm.heightPixels);
    //                tv1.setText("dpi : "+dm.densityDpi +"   smallest width pixels : "+width);
    //                tv2.setText("计算出来的smallestWidth : "+width/(dm.densityDpi/160.0) +"dp");
    //                tv3.setText("实际使用的smallestWidth :  "+getResources().getString(R.string.base_dpi));
    //                tv4.setText("当前手机： "+SystemUtil.getDeviceBrand()+"  "+SystemUtil.getSystemModel()+ " \n"+"当前系统： "+SystemUtil.getSystemVersion()+ " ");
    //                LinearLayout.LayoutParams p= (LinearLayout.LayoutParams) view.getLayoutParams();
    //                p.width = getResources().getDimensionPixelSize(R.dimen.qb_px_375);
    //                view.setLayoutParams(p);



//    新版AndroidStudio无法运行main方法
// TODO: 2020/10/31  在.idea下的gradle.xml文件下，<GradleProjectSettings>节点下加入<option name="delegatedBuild" value="false" />，重新build项目即可
}
