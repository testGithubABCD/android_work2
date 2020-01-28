package to.msn.wings.navigationbasic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import java.util.Random;

public class BeforeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_before, container, false);
        view.findViewById(R.id.btnNext).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Navigation.findNavController(v).navigate(R.id.afterFragment);
                    /*
                    Bundle bundle = new Bundle();
                    bundle.putInt("num", (new Random()).nextInt(100));
                    Navigation.findNavController(v).navigate(R.id.afterFragment, bundle);
                    */
                    BeforeFragmentDirections.ActionBeforeFragmentToAfterFragment action =
                        BeforeFragmentDirections.actionBeforeFragmentToAfterFragment();
                    action.setNum((new Random()).nextInt());
                    Navigation.findNavController(v).navigate(action);
                }
            }
        );
        return view;
    }
}
