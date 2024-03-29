package com.hzb.myUniversalUtils.utilTool.screenAdapter.constants;

/**
 * @description
 * @author Created by houzhengbang
 * @desc :sw限定符 屏幕适配
 */
public enum DimenTypes {
//   手机   使用  dp
    //适配Android 3.2以上   大部分手机的sw值集中在  300-460之间
    DP_sw__300(300),  // values-sw300
    DP_sw__310(310),
    DP_sw__320(320),
    DP_sw__330(330),
    DP_sw__340(340),
    DP_sw__350(350),
    DP_sw__360(360),
    DP_sw__370(370),
    DP_sw__380(380),
    DP_sw__390(390),
    DP_sw__400(400),
    DP_sw__410(410),
    DP_sw__420(420),
    DP_sw__430(430),
    DP_sw__440(440),
    DP_sw__450(450),
    DP_sw__460(460),
    DP_sw__470(470),
    DP_sw__480(480),
    DP_sw__490(490);
    // 想生成多少自己以此类推


//    320，360， 384 ，392，400，410，411，428，432，480，533，592，600，640，662，720，768，800，811，820，960，1024，1280，1365

//平板   设备常见的屏幕宽度值：
//	DP_sw__320(320),
//	DP_sw__480(480),
//	DP_sw__600(600),
//	DP_sw__720(720),

    /**
     * 屏幕最小宽度
     */
    private int swWidthDp;


    DimenTypes(int swWidthDp) {

        this.swWidthDp = swWidthDp;
    }

    public int getSwWidthDp() {
        return swWidthDp;
    }

    public void setSwWidthDp(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }

}
