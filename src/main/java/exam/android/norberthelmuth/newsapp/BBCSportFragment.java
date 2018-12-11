package exam.android.norberthelmuth.newsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BBCSportFragment extends Fragment {

    static BBCSportFragment instance;

    public static BBCSportFragment getInstance() {
        if (instance == null)
            instance = new BBCSportFragment();
        return instance;
    }

    public BBCSportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bbcsport, container, false);
    }

}
