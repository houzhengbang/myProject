package com.hzb.test.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hzb.test.R;
import com.hzb.myUniversalUtils.base.BaseFragment;



public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_layout, container, false);

        view.findViewById(R.id.fr_head_back).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                //通过FragmentManager管理器获取被标记的fragment
                Fragment fragment1 = manager.findFragmentByTag("s");
                if (fragment1 != null) {
                    //isMarket = true;//这行忽略
                    //开始事务 通过remove清除指定的fragment，并提交
                    manager.beginTransaction().remove(fragment1).commit();
            }}
        });

        TextView header_title = view.findViewById(R.id.fr_head_title);
        header_title.setText("任务列表");


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initAdapter();
//        getList(page, true);
    }


    public void getList(int mPager, boolean isRefresh) {
//        refreshLayout.setEnableLoadMore(false);
//        TaskBody req = new TaskBody();
//        req.clazzId = PadSession.getClazz().clazzId;
////        req.clazzId = "29477322293035008";
//        RetroAdapter.createService(HomeApiService.class)
//                .getTaskList(req)
//                .subscribeOn(Schedulers.io())
//                .map(ModelBase::getData)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(it -> {
//                    if (isRefresh) {
//                        refreshLayout.finishRefresh();
//                        mAdapter.setNewInstance(it);
////                        mAdapter.setEnableLoadMore(true);
////                        if (it.size() <= 0) {
////                            mAdapter.setEmptyView(notDataView);
////                        }
//                    } else {
//                        refreshLayout.finishLoadMore();
//                        if (it != null && !it.isEmpty()) {
//                            mAdapter.addData(it);
////                            mAdapter.loadMoreComplete();
//                        } else {
////                            mAdapter.loadMoreEnd(false);
//                        }
//                    }
//                    page++;
//                }, throwable -> {
//                    throwable.printStackTrace();
//                });
    }

    private void initAdapter() {
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mAdapter = new TaskAdapter();
//        mRecyclerView.setAdapter(mAdapter);
//        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                getList(page, false);
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                page = 0;
//                getList(page, true);
//            }
//        });
//        mAdapter.addChildClickViewIds(R.id.watch_tv, R.id.continue_tv);
//        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
//            if (view.getId() == R.id.watch_tv) {
//
//
//            } else if (view.getId() == R.id.continue_tv) {
//
//
//            }
//        });

    }


}
