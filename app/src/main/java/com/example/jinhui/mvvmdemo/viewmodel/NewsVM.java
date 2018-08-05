package com.example.jinhui.mvvmdemo.viewmodel;

import com.example.jinhui.mvvmdemo.MainActivity;
import com.example.jinhui.mvvmdemo.adapter.NewsAdapter;
import com.example.jinhui.mvvmdemo.base.BaseLoadListener;
import com.example.jinhui.mvvmdemo.bean.SimpleNewsBean;
import com.example.jinhui.mvvmdemo.constant.MainConstant;
import com.example.jinhui.mvvmdemo.model.INewsModel;
import com.example.jinhui.mvvmdemo.model.NewsModelImpl;
import com.example.jinhui.mvvmdemo.view.INewsView;

import java.util.List;

public class NewsVM implements BaseLoadListener<SimpleNewsBean> {

    private INewsModel mNewsModel;
    private INewsView mNewsView;
    private NewsAdapter mAdapter;
    private int loadType; //加载数据的类型
    private int currentPage = 1; //当前页数


    public NewsVM(INewsView mNewsView, NewsAdapter mAdapter) {
        this.mNewsView = mNewsView;
        this.mAdapter = mAdapter;
        mNewsModel = new NewsModelImpl();
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currentPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currentPage = 1;
        mNewsModel.loadNewsData(currentPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currentPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currentPage > 1) {
            //加载失败需要回到加载之前的页数
            currentPage--;
        }
        mNewsView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mNewsView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mNewsView.loadComplete();
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currentPage++;
        mNewsModel.loadNewsData(currentPage, this);
    }
}
