package to.msn.wings.navigationtoolbar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeforeFragment extends Fragment {


    public BeforeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_before, container, false);
        View view = inflater.inflate(R.layout.fragment_before, container, false);
        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.afterFragment);
                    }
                }
        );
        return view;
    }
}
