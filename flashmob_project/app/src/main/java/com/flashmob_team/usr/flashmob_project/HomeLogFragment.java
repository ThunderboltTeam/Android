package com.flashmob_team.usr.flashmob_project;


import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ResourceCursorAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;


/**
 * A simple {@link Fragment} subclass.
 */
//Fragment --> Fragment Activity
public class HomeLogFragment extends Fragment {

    public HomeLogFragment() {
        // Required empty public constructor
    }

    //dp변환 함수
    public int convertPixToDP(int px){
        int dp = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px , getResources().getDisplayMetrics());

        return dp;

    }
//    public TextView tag_makeText(String content){
//
//        TextView newText = new TextView(getActivity());
//        newText.setText(content);
//        newText.setTextColor(Color.BLACK);
////        newText.setTextSize(convertPixToDP(20));
//        newText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
//        newText.setGravity(Gravity.CENTER);
//
//        return newText;
//    }

//

    public TextView tag_makeText(String content, int size){

        TextView newText = new TextView(getActivity());
        newText.setText(content);
        newText.setTextColor(Color.BLACK);
//        newText.setTextSize(convertPixToDP(20));
        newText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        newText.setGravity(Gravity.CENTER);

        return newText;
    }

    public ImageView tag_Image(){
        ImageView newImgView = new ImageView(getActivity());
        newImgView.setImageBitmap(drawBigImage(R.drawable.hwai));
        newImgView.setScaleType(ImageView.ScaleType.CENTER);
        //newImgView.setImageResource(R.drawable.hwai);

        return newImgView;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //floatingActionButton android대신에 app:backgroundcolor해줘야 함.
        final FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.new_post);
        final ScrollView scrollView = getActivity().findViewById(R.id.scroll_view);

        final int[] previousScrollY = {scrollView.getScrollY()};

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                if (scrollView.getScrollY() > previousScrollY[0] && floatingActionButton.getVisibility() == View.VISIBLE){
                    floatingActionButton.hide();
                }
                else if (scrollView.getScrollY() < previousScrollY[0] && floatingActionButton.getVisibility() != View.VISIBLE){
                    floatingActionButton.show();
                }
                previousScrollY[0] = scrollView.getScrollY();
            }
        });


        //Layout 동적 생성
        LinearLayout LLlayout1 = getActivity().findViewById(R.id.scroll_layout);
        LinearLayout.LayoutParams BigVert_LLParam =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        LLlayout1.setOrientation(LinearLayout.VERTICAL);
        BigVert_LLParam.setMargins(50, 100, 50, 30);
//        BigVert_LLParam.setMargins(convertPixToDP(50), convertPixToDP(100),
//                convertPixToDP(0),convertPixToDP(30));

        TextView hot_list = tag_makeText("HOST LIST", 30);
        LLlayout1.addView(hot_list);
        hot_list.setLayoutParams(BigVert_LLParam);

        //ViewFlipper를 담고 있을 Linear Layout
        LinearLayout LLlayout1_2 = new LinearLayout(getActivity());
        LinearLayout.LayoutParams flipper_layout =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        LLlayout1.setOrientation(LinearLayout.VERTICAL);
        LLlayout1_2.setGravity(Gravity.CENTER);

        //ViewFlipper 비추, ViewPager로 다시
        // http://www.masterqna.com/android/67304/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%9B%B9%EB%B7%B0%EB%A1%9C-%EB%90%9C-viewflipper-%EC%97%90%EC%84%9C-%EC%A0%9C%EC%8A%A4%EC%B2%98-%EA%B8%B0%EB%8A%A5-%EB%84%A3%EA%B3%A0%EC%8B%B6%EC%9D%84%EB%95%8C
        final ViewFlipper flipper = new ViewFlipper(getActivity());
//        ViewFlipper.LayoutParams flipperParam = new ViewFlipper.LayoutParams(
//                ViewFlipper.LayoutParams.MATCH_PARENT, convertPixToDP(300)
//        );
        ViewFlipper.LayoutParams flipperParam = new ViewFlipper.LayoutParams(
                ViewFlipper.LayoutParams.MATCH_PARENT, convertPixToDP(200)
        );
        flipper.setForegroundGravity(Gravity.CENTER);
        flipperParam.setMargins(10,0,10,0);
        flipper.setAutoStart(true);
        flipper.setFlipInterval(3500);





        //hwai는 1920x1024, ekfmsrjs 1920x1080 확인
        int rsID[] = {R.drawable.fish, R.drawable.hwai, R.drawable.tree};

        for(int r=0; r<3; r++){
            ImageView slide_image = new ImageView(getActivity());
//            //1920x1024 이대로 넣으면 메모리 초과
//            slide_image.setImageResource(rsID[r]);

            slide_image.setImageBitmap(drawBigImage(rsID[r]));

            flipper.addView(slide_image);
            slide_image.setLayoutParams(flipperParam);
        }

        LLlayout1_2.addView(flipper);
        flipper.setLayoutParams(flipper_layout);
        LLlayout1.addView(LLlayout1_2);
//        LLlayout1_2.setLayoutParams(BigVert_LLParam);




        for(int i = 0; i<5; i++){

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
            HSV_Images_LLParam.setMargins(20,0,0,0);


            for(int j = 0; j < 6; j++){
                //HSV의 Layout안의 각 Image가 들어갈 곳
                LinearLayout LLlayout3 = new LinearLayout(getActivity());
                LinearLayout.LayoutParams txt_layout = new LinearLayout.LayoutParams(
                        convertPixToDP(180), LinearLayout.LayoutParams.WRAP_CONTENT);//400,200
                LinearLayout.LayoutParams Img_layout = new LinearLayout.LayoutParams(
                        convertPixToDP(180), convertPixToDP(100));//400,200

                LLlayout3.setOrientation(LinearLayout.VERTICAL);
                txt_layout.setMargins(0,5,  20,10);
                Img_layout.setMargins(0,10,20,10);;

                //ImageView 생성
                ImageView tag_Img = tag_Image();
                TextView content_txt = tag_makeText("노원구 스터디" + "\n" + "6시",15);



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

    protected Bitmap drawBigImage(int rsID){
        try{
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 8; // image 1/8로 줄여라
            options.inPurgeable = true;
            Bitmap src = BitmapFactory.decodeResource(getResources(), rsID, options);
            Bitmap resize = Bitmap.createScaledBitmap(src, options.outWidth, options.outHeight, true);

            return resize;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }


    //새로 모임을 만들었을 때
    public void Create_New_Meeting(){
        //this 대신 getContext();
//        LinearLayout LLlayout = new LinearLayout(getContext());
//        LinearLayout.LayoutParams LLParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LLlayout.setOrientation();
    }


}
