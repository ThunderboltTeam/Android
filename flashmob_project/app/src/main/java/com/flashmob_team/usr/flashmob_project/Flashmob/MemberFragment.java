package com.flashmob_team.usr.flashmob_project.Flashmob;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flashmob_team.usr.flashmob_project.R;


@SuppressLint("ValidFragment")
public class MemberFragment extends Fragment {
    Context mContext;
    RelativeLayout childRL;

    public MemberFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        ImageView leaderIv = (ImageView) view.findViewById(R.id.leaderIv);
        leaderIv.setBackground(new ShapeDrawable(new OvalShape()));
        leaderIv.setClipToOutline(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView memberIv;
        TextView memberName;
        LinearLayout parentLL;

        RelativeLayout memberRelativeLayout = (RelativeLayout)getActivity().findViewById(R.id.memberRelativeLayout);

        int inputNumber = 8; //번개모임 참여한 사람의 수

        int div = 0, remainder = 0;
        if(inputNumber % 5 == 0) {
            div = inputNumber / 5;
        } else if(inputNumber % 5 != 0) {
            div = inputNumber / 5 + 1;
        }

        for(int j=1; j<=div; j++) {
            parentLL = new LinearLayout(getActivity());
            parentLL.setId(j * 100);
            RelativeLayout.LayoutParams parentLLParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            parentLLParams.setMargins(convertPixToDP(10), convertPixToDP(5), convertPixToDP(10), convertPixToDP(10));
            if(j == 1) {
                parentLLParams.addRule(RelativeLayout.BELOW, R.id.Members);
            } else {
                parentLLParams.addRule(RelativeLayout.BELOW, (j-1) * 100);
            }
            parentLL.setOrientation(LinearLayout.HORIZONTAL);
            parentLL.setLayoutParams(parentLLParams);

            if(j != div) {
                remainder = 5;
            } else {
                remainder = inputNumber % 5;
            }

            for(int i=1; i<=remainder; i++) {
                childRL = new RelativeLayout(getActivity());
                RelativeLayout.LayoutParams childRLParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                childRLParams.setMargins(20, 0, 20, 0);
                childRL.setLayoutParams(childRLParams);

                memberIv = new ImageView(getActivity());
                memberIv.setId(i);
                memberIv.setImageResource(R.drawable.leader); //이미지
                memberIv.setBackground(new ShapeDrawable(new OvalShape()));
                memberIv.setClipToOutline(true);
                RelativeLayout.LayoutParams memberIvParams = new RelativeLayout.LayoutParams(convertPixToDP(60), convertPixToDP(60));
                memberIvParams.setMargins(0, 10, 0, 0);
                memberIv.setLayoutParams(memberIvParams);

                childRL.addView(memberIv);

                memberName = new TextView(getActivity());
                memberName.setText("Member Name");
                memberName.setGravity(Gravity.CENTER);

                RelativeLayout.LayoutParams memberNameParams = new RelativeLayout.LayoutParams(convertPixToDP(60), ViewGroup.LayoutParams.WRAP_CONTENT);
                memberNameParams.setMargins(0, 10, 0, 5);
                memberNameParams.addRule(RelativeLayout.BELOW, memberIv.getId());
                memberName.setLayoutParams(memberNameParams);

                childRL.addView(memberName);

                parentLL.addView(childRL);
            }

            memberRelativeLayout.addView(parentLL);

        }

    }

    //dp 변환 함수
    public int convertPixToDP(int px) {
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
        return dp;
    }
}
