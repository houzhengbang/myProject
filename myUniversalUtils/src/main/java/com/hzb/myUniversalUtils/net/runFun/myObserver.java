package com.hzb.myUniversalUtils.net.runFun;

/**
 * FileName: myObserver
 * Author: houzhengbang
 * Date: 2020/10/31 1:38 PM
 * Description:
 */
public class myObserver {

//    private void getRetrofit() {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
//                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
//                .writeTimeout(Constans.DEFAULT_TIME,TimeUnit.SECONDS)//设置写入超时时间
//                .addInterceptor(new LogInterceptor())//添加打印拦截器
//                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
//                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(client)
//                .baseUrl(Constans.BaseUrl)
//                //添加GSON解析：返回数据转换成GSON类型
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiUrl api = retrofit.create(ApiUrl.class);
//        Call<Bean> demo = api.getRetrofit();
//        demo.enqueue(new Callback<Bean>() {
//            @Override
//            public void onResponse(Call<Bean> call, Response<Bean> response) {
//                Log.e(TAG, "请求成功信息: "+response.body().toString());
//                tv_retrofit.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Bean> call, Throwable t) {
//                Log.e(TAG, "请求失败信息: " +t.getMessage());
//                tv_retrofit.setText(t.getMessage());
//            }
//        });
//
//    }
//
//    private void getDatas() {
//        RequestUtils.getDemoList(this, new MyObserver<List<Demo>>(this) {
//            @Override
//            public void onSuccess(List<Demo> result) {
//                for (Demo demo:result){
//                    Log.e(TAG, "onSuccess: "+demo.toString() );
//                }
//                tv_retrofit.setText(result.toString());
//            }
//            @Override
//            public void onFailure(Throwable e, String errorMsg) {
//                tv_retrofit.setText(errorMsg);
//            }
//        });
//    }
//
//    private void getData() {
//        myObserver = new MyObserver<Demo>(this) {
//            @Override
//            public void onSuccess(Demo result) {
//                tv_retrofit.setText(result.toString());
//            }
//            @Override
//            public void onFailure(Throwable e, String errorMsg) {
//                tv_retrofit.setText(errorMsg);
//            }
//        };
//        RequestUtils.getDemo(this,myObserver);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        myObserver.cancleRequest();
//    }
} 