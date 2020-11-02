package com.hzb.test.ui.activity.login;


/**
 * FileName: LoginModel
 * Author: houzhengbang
 * Date: 2020-05-27 10:17
 * Description:
 */

/**
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * <p>
 * userInfo P层传递过来的数据
 * callBack P层传递过来的回调对象----------说白了就是成功还是失败了,你总的告诉我一声,我好通知V层来处理[这里可以不用回调,在代码中使用EventBus或者传递一个Handler过来也可以,不建议这样操作]
 */

public class LoginModel implements LoginControl.ILoginModel {

}
