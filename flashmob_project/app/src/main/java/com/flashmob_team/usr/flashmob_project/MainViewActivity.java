package com.flashmob_team.usr.flashmob_project;


import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;


import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainViewActivity extends FragmentActivity {

    private HomeLogFragment homeLogFragment;
    private LikeFragment likeFragment;
    private ChatFragment chatFragment;
    private SearchFragment searchFragment;
    private UserFragment userFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        homeLogFragment = new HomeLogFragment();
        likeFragment = new LikeFragment();
        chatFragment = new ChatFragment();
        searchFragment = new SearchFragment();
        userFragment = new UserFragment();

        initFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if(tabId == R.id.tab_home){
                    transaction.replace(R.id.contentContainer, homeLogFragment).commit();
                }
                else if(tabId == R.id.tab_like) {
                    transaction.replace(R.id.contentContainer, likeFragment).commit();
                }
                else if(tabId == R.id.tab_chat) {
                    transaction.replace(R.id.contentContainer, chatFragment).commit();
                }
                else if(tabId == R.id.tab_search) {
                    transaction.replace(R.id.contentContainer, searchFragment).commit();
                }
                else if(tabId == R.id.tab_user) {
                    transaction.replace(R.id.contentContainer, userFragment).commit();
                }

            }

        });
    }
        //app 실행시 보여지는 Fragment

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, homeLogFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}

