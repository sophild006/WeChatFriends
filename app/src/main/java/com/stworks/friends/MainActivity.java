package com.stworks.friends;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stworks.common.base.bean.UserInfo;
import com.stworks.common.base.ui.BaseMvpActivity;
import com.stworks.common.utils.ViewUtils;
import com.stworks.friends.config.ViewConfig;
import com.stworks.friends.adapter.FriendsAdapter;
import com.stworks.friends.bean.FriendsList;
import com.stworks.friends.friends.FriendContract;
import com.stworks.friends.friends.FriendPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wwq on 2018/7/4.
 */

public class MainActivity extends BaseMvpActivity<FriendContract.FriendPresenter> implements FriendContract.FriendView, SwipeRefreshLayout.OnRefreshListener {

    private int CURRENT_PAGE = 0;
    private int PAGE_SIZE = 5;
    private Unbinder binder;
    @BindView(R.id.swipeRefreshView)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    private FriendsAdapter mAdapter;
    /**
     * header 部分
     */
    private View headerView;
    private ImageView ivHeader, ivHeaderBg;
    private TextView tvUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    @Override
    public FriendContract.FriendPresenter createPresenter() {
        return new FriendPresenterImpl();
    }

    @Override
    protected int doGetLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        getPresenter().init(this);
        mAdapter.setPresenter(getPresenter());
        getPresenter().getData(PAGE_SIZE, CURRENT_PAGE);
        getPresenter().getUserInfo();
    }

    @Override
    protected void initView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        headerView = View.inflate(this, R.layout.friends_header, null);
        ivHeader = headerView.findViewById(R.id.iv_header);
        ivHeaderBg = headerView.findViewById(R.id.iv_header_bg);
        tvUserName = headerView.findViewById(R.id.tv_user_name);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FriendsAdapter(R.layout.friends_item);
        mAdapter.addHeaderView(headerView);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getData(PAGE_SIZE, CURRENT_PAGE);
                CURRENT_PAGE++;
            }
        }, recyclerView);
    }

    @OnClick({})
    public void onViewClicked(View view) {

        switch (view.getId()) {
//            case R.id.swipeRefreshView:
//                break;
        }
    }

    @Override
    public void doParise() {
        showToast(getString(R.string.common_prise_success));
    }

    @Override
    public void doComment() {
        showToast(getString(R.string.common_comment_success));
    }

    @Override
    public void showData(List<FriendsList> friendsLists) {
        swipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(friendsLists);
        CURRENT_PAGE++;
    }

    @Override
    public void showMoreData(List<FriendsList> friendsLists) {
        mAdapter.addData(friendsLists);
        mAdapter.loadMoreComplete();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadFriendsEnd() {
        mAdapter.loadMoreEnd();
    }

    @Override
    public void loadFriendsFailed() {
        mAdapter.loadMoreFail();

    }

    @Override
    public void showUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            ViewUtils.intoImageView(this, userInfo.getAvatar(), ivHeader);
            tvUserName.setText(ViewUtils.getContent(userInfo.getUsername()));
        }
        ViewUtils.intoImageView(this, "http://www.thoughtworks.com/imgs/hosted/header.jpg", ivHeaderBg);
    }

    @Override
    public void onRefresh() {
        CURRENT_PAGE = 0;
        swipeRefreshLayout.setRefreshing(true);
        getPresenter().getUserInfo();
        getPresenter().getData(PAGE_SIZE, CURRENT_PAGE);
    }

    @Override
    public void setViewStatus(int status) {
        super.setViewStatus(status);
        switch (status) {
            case ViewConfig.NORMAL_STATUS:
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                break;
            case ViewConfig.ERROR_STATUS:
                swipeRefreshLayout.setVisibility(View.GONE);
                break;
            case ViewConfig.EMPTY_STATUS:
                swipeRefreshLayout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
        getPresenter().onDestroy();
    }
}
