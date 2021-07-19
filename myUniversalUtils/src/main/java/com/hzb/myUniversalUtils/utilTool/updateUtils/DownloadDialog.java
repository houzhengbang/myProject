package com.hzb.myUniversalUtils.utilTool.updateUtils;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.hzb.myUniversalUtils.R;


/**
 * FileName: DownloadDialog
 * Author: houzhengbang
 * Date: 2020/11/2 3:57 PM
 * Description:
 */
public class DownloadDialog extends AlertDialog {

    private ProgressBar mProgressBar;
    private TextView mTvPercentage;
    private TextView mTvSize;

    public DownloadDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_download);
//        setCancelable(false);

//        setCancelable(true);
//        按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView(){
        mProgressBar = findViewById(R.id.progressBar);
        mTvPercentage = findViewById(R.id.tv_percentage);
        mTvSize = findViewById(R.id.tv_size);
    }

    public ProgressBar getmProgressBar(){
        return mProgressBar;
    }

    public TextView getmTvPercentage(){
        return mTvPercentage;
    }

    public TextView getmTvSize(){
        return mTvSize;
    }
}