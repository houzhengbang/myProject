package com.hzb.myUniversalUtils.utilTool.screenAdapter.generator;


import com.hzb.myUniversalUtils.utilTool.screenAdapter.constants.DimenTypes;
import com.hzb.myUniversalUtils.utilTool.screenAdapter.utils.MakeUtils;

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



    //七、常见问题汇总
    //
    //7.1 为什么宽度适配了，高度有时候没有完全适配？
    //
    //因为各种屏幕高宽比并不是固定的，有 16:9、4:3，还有全面屏的 19.5:9 等等，如果强行将宽高都适配那只会导致布局变形。
    //
    //例如一个控件的宽高为 360dp 和 640dp，如果将它显示在宽高为 360dp 和 640dp 的设备上是正常铺满整个屏幕的，但是显示在宽高为 360dp 和 780dp 的设备上高度则不能铺满，如果你让高度铺满，而宽度又保持不变，那就会出现变形的情况。所以这也就是为什么目前市面上的屏幕适配方案只能以宽或高一个维度去适配，另一个方向用滑动或权重的方式去适配的原因。
    //
    //那你为什么说高度也能适配呢？
    //这里说的高度也能适配指的是在不同分辨率和密度的手机上能达到等比缩放的适配，其他屏幕适配方案也是一样的。
    //
    //7.2 如何同时适配横竖屏？
    //
    //方案一：（不推荐）
    //计算出设备宽度和高度的 dp 值，然后生成对应的宽高 dimens.xml 文件。然后去掉所有 values-swXXXdp 目录上的 s，即改为 values-wXXXdp。这样设备不管横竖屏都能找到对应的 values-wXXXdp 目录下的 dimens.xml 文件了。 虽然也能达到一定程度的适配，但是这样会增加很多 dimens.xml 文件，而且使用竖屏的设计图显示出来的效果也不够好。
    //
    //方案二：（推荐）
    //因为横屏时宽高变化太大，想要横屏时也能完全适配，那就只能让设计师出一套横屏的设计图，然后单独写一套横屏的布局文件。这时候还需要在 res 文件夹下创建 layout-land 文件夹用来放横屏的布局文件，默认的 layout 文件夹放竖屏的布局文件。
    //
    //注意：smallestWidth 限定符适配的效果是让不同分辨率和密度的设备上能达到以设计图等比缩放的适配，如果设备与设计图相差太大时并不能达到很好的适配效果，需要单独出图，其他屏幕适配方案也是一样的。
    //
    //7.3 如何适配平板、TV？
    //
    //同横屏道理一样，平板、TV 与手机的宽高差距太大，想要平板、TV 也能完全适配，那就只能让设计师出一套平板、TV 的设计图，然后单独写一套平板、TV 的布局文件。
    //
    //如果 同时适配手机与平板 或者 同时适配手机与 TV，还需要在 res 文件夹下创建 layout-sw533dp 文件夹用来放平板或 TV 的布局文件，默认的 layout 文件夹放手机的布局文件。
    //注意：这里用的是 533dp 的原因是目前手机最大的宽度为 480dp，所以大于 480dp 的就认为是平板或 TV。
    //
    //如果 同时适配手机、平板与 TV，这种情况平板与 TV 的设计图可以只出一套，因为平板与 TV 的样式比较类似，而且平板与 TV 的最小宽度值可能会出现一样的情况，这时候适配看成上面那种方式即可。
    //
    //注意：再说一遍，smallestWidth 限定符适配的效果是让不同分辨率和密度的设备上能达到以设计图等比缩放的适配，如果设备与设计图相差太大时并不能达到很好的适配效果，需要单独出图，其他屏幕适配方案也是一样的。


}



