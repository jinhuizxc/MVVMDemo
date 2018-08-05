package com.example.jinhui.mvvmdemo.model;

import com.example.jinhui.mvvmdemo.base.BaseLoadListener;
import com.example.jinhui.mvvmdemo.bean.SimpleNewsBean;

public interface INewsModel {

    /**
     * 获取新闻数据
     *
     * @param page 页数
     * @param loadListener
     */
    void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener);

}
