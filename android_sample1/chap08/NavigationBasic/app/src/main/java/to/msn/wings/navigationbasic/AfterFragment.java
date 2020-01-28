package to.msn.wings.navigationbasic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AfterFragment extends Fragment {


    public AfterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_after, container, false);
        TextView txt = v.findViewById(R.id.txtValue);
        //txt.setText("乱数："  + getArguments().getInt("num"));
        Bundle args = Objects.requireNonNull(getArguments());
        txt.setText("乱数：" +
            AfterFragmentArgs.fromBundle(args).getNum());
        return v;
    }
}
