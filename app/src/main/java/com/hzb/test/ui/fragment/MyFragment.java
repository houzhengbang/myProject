package com.hzb.test.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hzb.test.R;
import com.hzb.test.ui.activity.MainActivity;
import com.hzb.test.ui.activity.home.HomeActivity;


/**
 * FileName: HomeFragment
 * Author: houzhengbang
 * Date: 2020-05-28 20:03
 * Description:
 */
public class MyFragment extends Fragment {

    private MainActivity mContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout, null);
//        mContext = (HomeActivity) getActivity();

//        LinearLayout viewById = view.findViewById(R.id.my);
//        SystemBarUtils.setPadding(mContext, viewById);

        return view;


    }
}
