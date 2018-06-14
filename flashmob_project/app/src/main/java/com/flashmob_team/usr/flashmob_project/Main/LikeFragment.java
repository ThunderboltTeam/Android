package com.flashmob_team.usr.flashmob_project.Main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

public class LikeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerLikeAdapter likeAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecyclerLikeItem> likeList;

    public LikeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.likeRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.scrollToPosition(0);
        likeAdapter = new RecyclerLikeAdapter(likeList);
        mRecyclerView.setAdapter(likeAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset() {
        likeList = new ArrayList<>();
        likeList.add(new RecyclerLikeItem("모집합니다!!", "2018/6/12", 3, 5, "서울"));
        likeList.add(new RecyclerLikeItem("놀자요!", "2018/6/12", 10, 10, "강남"));
        likeList.add(new RecyclerLikeItem("모집합니다!!", "2018/6/12", 3, 5, "서울"));
        likeList.add(new RecyclerLikeItem("놀자요!", "2018/6/12", 10, 10, "강남"));
        likeList.add(new RecyclerLikeItem("모집합니다!!", "2018/6/12", 3, 5, "서울"));
        likeList.add(new RecyclerLikeItem("놀자요!", "2018/6/12", 10, 10, "강남"));
    }
}
