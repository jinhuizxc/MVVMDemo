package com.example.jinhui.mvvmdemo;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.jinhui.mvvmdemo.adapter.NewsAdapter;
import com.example.jinhui.mvvmdemo.databinding.ActivityMainBinding;
import com.example.jinhui.mvvmdemo.helper.DialogHelper;
import com.example.jinhui.mvvmdemo.utils.ToastUtils;
import com.example.jinhui.mvvmdemo.view.INewsView;
import com.example.jinhui.mvvmdemo.viewmodel.NewsVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import static com.example.jinhui.mvvmdemo.constant.MainConstant.LoadData.FIRST_LOAD;

/**
 * 尝试MVVM
 *
 * 作者： 周旭 on 2017年10月18日 0018.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 *
 */
public class MainActivity extends AppCompatActivity implements INewsView, XRecyclerView.LoadingListener {

    private Context mContext;
    private ActivityMainBinding binding;
    private NewsAdapter newsAdapter; //新闻列表的适配器

    private NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mContext = this;
        initRecyclerView();
        newsVM = new NewsVM(this, newsAdapter);

    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        binding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        binding.newsRv.setAdapter(newsAdapter);

    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == FIRST_LOAD) {
            DialogHelper.getInstance().show(mContext, "加载中...");
        }
    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
        ToastUtils.show(mContext, message);
    }

    @Override
    public void onRefresh() {
        // 下拉刷新
        newsVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        newsVM.loadMoreData();
    }
}
