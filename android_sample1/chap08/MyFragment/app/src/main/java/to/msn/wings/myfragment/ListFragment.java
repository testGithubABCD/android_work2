package to.msn.wings.myfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

public class ListFragment extends Fragment {
    private boolean isTwoPane = false;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = Objects.requireNonNull(getActivity());
        if(activity.findViewById(R.id.detailsFrame) != null) {
            isTwoPane = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Activity activity = Objects.requireNonNull(getActivity());
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                activity, android.R.layout.simple_list_item_1,
                ListDataSource.getAllNames());
        ListView list = view.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*
            // タブレットだけの場合
            @Override
            public void onItemClick(
                AdapterView<?> parent, View view, int i, long id) {
                FragmentManager manager = Objects.requireNonNull(getFragmentManager());
                DetailsFragment fragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name",
                    (String) parent.getItemAtPosition(i));
                fragment.setArguments(bundle);
                manager.beginTransaction()
                    .replace(R.id.detailsFrame, fragment)
                    .commit();
            }
            */


            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int i, long id) {
                FragmentManager manager = Objects.requireNonNull(getFragmentManager());
                DetailsFragment fragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", (String) parent.getItemAtPosition(i));
                if (isTwoPane) {
                    fragment.setArguments(bundle);
                    manager.beginTransaction()
                            .replace(R.id.detailsFrame, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
