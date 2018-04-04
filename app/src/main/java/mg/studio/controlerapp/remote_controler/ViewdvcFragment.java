package mg.studio.controlerapp.remote_controler;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ViewdvcFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    @Bind(R.id.devicelist) ListView devicelist;
    @Bind(R.id.freshdevice) SwipeRefreshLayout mSwipeRefreshLayout;

    private List<String> stringList;
    private ArrayAdapter devicelistAdapt;

    public ViewdvcFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_viewdvc, container, false);

        ButterKnife.bind(this, view);
        initData();
        return view;
    }


    private void initData() {
        stringList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {

            stringList.add("device " + String.valueOf(i));
        }
        devicelistAdapt = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stringList);
        devicelist.setAdapter(devicelistAdapt);

        devicelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), stringList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //init downpull components' color
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        SystemClock.sleep(2000);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        Toast.makeText(getActivity(), "fresh successfully", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }.execute();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);//解绑
    }
}
