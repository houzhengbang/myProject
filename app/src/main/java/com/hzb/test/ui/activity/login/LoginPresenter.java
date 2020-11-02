package com.hzb.test.ui.activity.login;

/**
 * FileName: LoginModel
 * Author: houzhengbang
 * Date: 2020-05-27 10:17
 * Description:
 */


/**
 * 登录的桥梁(View和Model的桥梁)-------P通过将V拿到的数据交给M来处理  P又将处理的结果回显到V
 * 说白点就是P中new出M和V 然后通过调用它们两个的方法来完成操作
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 */
public class LoginPresenter implements LoginControl.ILoginPresenter {
    private String TAG = "LoginPresenter";
    private final LoginControl.ILoginView iLoginView;
    private final LoginControl.ILoginModel loginModel;

    public LoginPresenter(LoginControl.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();
    }
}
