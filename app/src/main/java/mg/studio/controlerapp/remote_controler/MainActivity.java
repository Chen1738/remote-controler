package mg.studio.controlerapp.remote_controler;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import irsupported.DeviceName;

public class MainActivity extends FragmentActivity implements FragmentInfo.OnFragmentInteractionListener {
    static final int NUM_ITEMS = 2;

    MyAdapter mAdapter;

    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        // Watch for button clicks.
        Button button = findViewById(R.id.goto_first);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(0);
            }
        });
        button = findViewById(R.id.goto_last);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(NUM_ITEMS - 1);
            }
        });
    }


    //For the FragmentInfo Java
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1) return FragmentInfo.newInstance("Test", "Test2");
            return ArrayListFragment.newInstance(position);
            /*
              v = inflater.inflate(R.layout.fragment_fragment_info, container, false);
             View tv = v.findViewById(R.id.text);
             ((TextView) tv).setText("Fragment #" + mNum);
             */
        }
    }

    public static class ArrayListFragment extends ListFragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v;

            v = inflater.inflate(R.layout.fragment_pager_list, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView) tv).setText("Device list" + mNum);

            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            final String[] arrayDemo = DeviceName.devicelist;


            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.item_list, arrayDemo));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            //super.onListItemClick(l,v,position,id);
            //Log.i("FragmentList", "Item clicked: " + id);
            final String[] arrayDemo = DeviceName.devicelist;
            Toast.makeText(getActivity(), "you select "+arrayDemo[position], Toast.LENGTH_SHORT).show();
        }
    }


}
