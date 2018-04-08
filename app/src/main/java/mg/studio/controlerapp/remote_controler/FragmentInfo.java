package mg.studio.controlerapp.remote_controler;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.PlusOneButton;

//there should add IR  code library



/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link FragmentInfo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentInfo#newInstance} factory method to
 * create an instance of this fragment.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class FragmentInfo extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PlusOneButton mPlusOneButton;

    private OnFragmentInteractionListener mListener;

    //ADD by author
    String infoString= "Control";
    final public String TITLE = "Control";
    //get IRControle class
    private ConsumerIrManager IR;
    //adjust whether IR function
    boolean IRBack;

    private View view;
    private ImageView modeimage,speedimage,directionimage;
    //init air-conditioner data
    //private AirData airdata = new AirData(0,25,0,0,0);

    //init array,the elements are sources of images
    private int modeimages[] = new int[]{R.drawable.heating,R.drawable.refrigeration,R.drawable.blowing,R.drawable.chushi};
    private int speedimages[] = new int[]{R.drawable.high,R.drawable.mid,R.drawable.low};
    private int directionimages[] = new int[]{R.drawable.hori,R.drawable.verti};
    //deifne a number for cycle
    private int count = 0;


    public FragmentInfo() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInfo newInstance(String param1, String param2) {
        FragmentInfo fragment = new FragmentInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_info, container, false);

        Button on = (Button) view.findViewById(R.id.on);
        Button off = (Button) view.findViewById(R.id.off);

        TextView textView =view.findViewById(R.id.fragmenttext);
        textView.setText(infoString);

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onActivityCreated();
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onResume();
            }
        });


        //Find the +1 button
//        mPlusOneButton =  view.findViewById(R.id.plus_one_button);

        return view;
    }


    public void onActivityCreated() {//Bundle savedInstanceState

        //super.onActivityCreated(savedInstanceState);


        Button add = (Button) getActivity().findViewById(R.id.add);
        Button subtract = (Button) getActivity().findViewById(R.id.subtract);

        final ImageView imageView = getActivity().findViewById(R.id.image);
        final ImageView imageView1 = getActivity().findViewById(R.id.speed);
        final ImageView imageView2 = getActivity().findViewById(R.id.direction);

        //add.setBackground(R.id.);
        imageView.setImageResource(modeimages[0]);
        imageView1.setImageResource(speedimages[0]);
        imageView2.setImageResource(directionimages[0]);


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setImageResource(modeimages[++count%modeimages.length]);
                    //mode event
                }
            });

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView1.setImageResource(speedimages[++count%speedimages.length]);
                    //speed event
                }
            });

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView2.setImageResource(directionimages[++count%directionimages.length]);
                    //direction event
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView temperature = getActivity().findViewById(R.id.temperature);
                    int number = Integer.parseInt(temperature.getText().toString());

                    if(number < 33){
                        number+=1;
                    }
                    else {
                        Toast.makeText(getActivity(), "temperature is too high!", Toast.LENGTH_SHORT).show();}
                    temperature.setText(Integer.toString(number));
                }
            });

            subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView temperature = getActivity().findViewById(R.id.temperature);
                    int number = Integer.parseInt(temperature.getText().toString());

                    if(number > 15){
                        number-=1;
                    }
                    else {
                        Toast.makeText(getActivity(), "temperature is too low!", Toast.LENGTH_SHORT).show();}
                    temperature.setText(Integer.toString(number));
                }
            });

    }

    private void InitEvent(){
        //get ConsumerIrManager instance
        IR = (ConsumerIrManager)getActivity().getSystemService(Context.CONSUMER_IR_SERVICE);

        //perform check when sdk version>4.4
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            IRBack = IR.hasIrEmitter();
            if(!IRBack) Toast.makeText(getActivity(), "sorry,there is not IR device", Toast.LENGTH_SHORT).show();
            else Toast.makeText(getActivity(), "IR device is ready ", Toast.LENGTH_SHORT).show();
        }
    }

    /*send IR sign
    * @param carrierFrenquency  the frequency of IR transmit
     * @param pattern  the alternative time(/us) of on/off
     * */
    private void sendIrMsg(int carrierFrenquency,int[] pattern){
        IR.transmit(carrierFrenquency,pattern);

        Toast.makeText(getActivity(),"send successfully",Toast.LENGTH_SHORT).show();
        String content = null;
        for(int i = 0;i<pattern.length;i++){
            content += String.valueOf(pattern[i])+",";
        }
        Log.e("remote messages",content);
        Log.e("remote messages","the length is "+pattern.length);
    }

    @Override
    public void onResume() {
        super.onResume();
        //onStop();

        // Refresh the state of the +1 button each time the activity receives focus.
//        mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
