package mg.studio.controlerapp.remote_controler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager vp;
    //private ListFragment mFragmentList= new ListFragment();
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmAdapter;

    private ControlFragment fragmentcontrol;
    private ViewdvcFragment fragmentdvc;

    private TextView title, connect, device;

    String[] titles = new String[]{"Connection", "My device"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove tools column
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        initView();

        mFragmAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(2);//viewpager cache limit is 2
        vp.setAdapter(mFragmAdapter);
        vp.setCurrentItem(0);//init set viewPager choose the first one
        connect.setTextColor(Color.parseColor("#000000"));

        //ViewPager Listener
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //calls when page is selected
                title.setText(titles[position]);
                //changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //calls when state is changed,there are three state:0,1,2
                /*
                * arg0 == 1 means it is scrolling
                * arg0 == 2 means it has scrolled
                * arg0 == 0 means it does nothing*/
            }
        });
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        connect = (TextView) findViewById(R.id.connection);
        device = (TextView) findViewById(R.id.mydevice);

        connect.setOnClickListener(this);
        device.setOnClickListener(this);

        vp = (ViewPager) findViewById(R.id.viewpage1);
        fragmentcontrol = new ControlFragment();
        fragmentdvc = new ViewdvcFragment();
        //add data to mFragmentList
        mFragmentList.add(fragmentcontrol);
        mFragmentList.add(fragmentdvc);
    }
    /*
    private void initPosition() {
        DisplayMetrics disp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(disp);
    }
    */
    //change viewpager when click textview
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connection:
                vp.setCurrentItem(0, true);
                break;
            case R.id.mydevice:
                vp.setCurrentItem(1, true);
                break;
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /*
     *change the bottom navigation' color through ViewPager' scrolling 由ViewPager的滑动修改底部导航Text的颜色
     */
    private void changeTextColor(int position) {
        if (position == 0) {
            connect.setTextColor(Color.parseColor("#66CDAA"));
            device.setTextColor(Color.parseColor("#000000"));
        } else if (position == 1) {
            device.setTextColor(Color.parseColor("#66CDAA"));
            connect.setTextColor(Color.parseColor("#000000"));
        }
    }



}
