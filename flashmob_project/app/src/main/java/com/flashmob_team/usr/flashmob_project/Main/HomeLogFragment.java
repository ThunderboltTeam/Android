package com.flashmob_team.usr.flashmob_project.Main;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flashmob_team.usr.flashmob_project.Flashmob.CreateFlashmob;
import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
//Fragment --> Fragment Activity
public class HomeLogFragment extends Fragment {

    private int dotscount;
    private ImageView[] dots;

    public HomeLogFragment() {
        // Required empty public constructor
    }

    //dp변환 함수
    public int convertPixToDP(int px) {
        int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());

        return dp;
    }


    public TextView tag_makeText(String content, int size) {

        TextView newText = new TextView(getActivity());
        newText.setText(content);
        newText.setTextColor(Color.BLACK);
//        newText.setTextSize(convertPixToDP(20));
        newText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        newText.setGravity(Gravity.CENTER);

        return newText;
    }

    public ImageView tag_Image(int image) {
        ImageView newImgView = new ImageView(getActivity());
        Glide.with(this).load(image).into(newImgView);
        newImgView.setScaleType(ImageView.ScaleType.CENTER);

        return newImgView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;

        //return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //floatingActionButton android대신에 app:backgroundcolor해줘야 함.
        final FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.new_btn);
        final ScrollView scrollView = getActivity().findViewById(R.id.scroll_view);

        final int[] previousScrollY = {scrollView.getScrollY()};

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                if (scrollView.getScrollY() > previousScrollY[0] && floatingActionButton.getVisibility() == View.VISIBLE) {
                    floatingActionButton.hide();
                } else if (scrollView.getScrollY() < previousScrollY[0] && floatingActionButton.getVisibility() != View.VISIBLE) {
                    floatingActionButton.show();
                }
                previousScrollY[0] = scrollView.getScrollY();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateFlashmob.class);
                startActivity(intent);
            }
        });

        ArrayList arrayList = new ArrayList();
        int rsID[] = {R.drawable.fish, R.drawable.hwai, R.drawable.tree};
        for (int r = 0; r < 3; r++) {
            arrayList.add(rsID[r]);
        }
//

        ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getLayoutInflater(), arrayList);
        viewPager.setAdapter(viewPagerAdapter);


        LinearLayout sliderDotspanel = getActivity().findViewById(R.id.slider_dots);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(
                    getActivity(), R.drawable.nonactive_dot
            ));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(convertPixToDP(3), 0, convertPixToDP(3), 0);
            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(
                getActivity(), R.drawable.active_dot
        ));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(
                            getActivity(), R.drawable.nonactive_dot
                    ));

                    dots[position].setImageDrawable(ContextCompat.getDrawable(
                            getActivity(), R.drawable.active_dot
                    ));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //Layout 동적 생성
        LinearLayout LLlayout1 = getActivity().findViewById(R.id.scroll_layout);
        LinearLayout.LayoutParams BigVert_LLParam =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        LLlayout1.setOrientation(LinearLayout.VERTICAL);
//        BigVert_LLParam.setMargins(50, 100, 50, 30);
        BigVert_LLParam.setMargins(convertPixToDP(10), convertPixToDP(10),
                convertPixToDP(10), convertPixToDP(0));


        //ViewFlipper를 담고 있을 Linear Layout
        LinearLayout LLlayout1_2 = new LinearLayout(getActivity());
        LinearLayout.LayoutParams flipper_layout =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        LLlayout1.setOrientation(LinearLayout.VERTICAL);
        LLlayout1_2.setGravity(Gravity.CENTER);


        for (int i = 0; i < 5; i++) {

            //text, ImageView(Scroll)들어가는 Layout에 text삽입
            TextView tag_Name = tag_makeText("#운동", 20);
            LLlayout1.addView(tag_Name);
            tag_Name.setLayoutParams(BigVert_LLParam); //Margin을 줌

            //HorizontalScrollView를 생성
            HorizontalScrollView HZS_View = new HorizontalScrollView(getActivity());
            HZS_View.setHorizontalScrollBarEnabled(false); //HorizonScrollBar 없애기


            //HZS_View안의 여러 ImagView 들어갈 곳 생성
            LinearLayout LLlayout2 = new LinearLayout(getActivity());
            LinearLayout.LayoutParams HSV_Images_LLParam = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LLlayout2.setOrientation(LinearLayout.HORIZONTAL);
            //HS_View Parameter
            HSV_Images_LLParam.setMargins(20, 0, 0, 0);


            for (int j = 0; j < 6; j++) {
                //HSV의 Layout안의 각 Image가 들어갈 곳
                LinearLayout LLlayout3 = new LinearLayout(getActivity());
                LinearLayout.LayoutParams txt_layout = new LinearLayout.LayoutParams(
                        convertPixToDP(180), LinearLayout.LayoutParams.WRAP_CONTENT);//400,200
                LinearLayout.LayoutParams Img_layout = new LinearLayout.LayoutParams(
                        convertPixToDP(180), convertPixToDP(100));//400,200

                LLlayout3.setOrientation(LinearLayout.VERTICAL);
                txt_layout.setMargins(0, 5, 20, 10);
                Img_layout.setMargins(0, 10, 20, 10);
                ;

                //ImageView 생성
                ImageView tag_Img = tag_Image(R.drawable.hwai);
                TextView content_txt = tag_makeText("노원구 스터디" + "\n" + "6시", 15);


                LLlayout3.addView(tag_Img); //LLlayout3에 tag_Img삽입
                LLlayout3.addView(content_txt); // image밑에 title
                tag_Img.setLayoutParams(Img_layout);//Margin, 크기 Img_layout따라
                content_txt.setLayoutParams(txt_layout);

                LLlayout2.addView(LLlayout3);
                LLlayout3.setLayoutParams(HSV_Images_LLParam);
            }
            HZS_View.addView(LLlayout2);
            LLlayout1.addView(HZS_View);

//            HZS_View.setLayoutParams(HSV_Hor_Param); //이건 안됨. 왜인지는 모르겠..


        }
    }


}
