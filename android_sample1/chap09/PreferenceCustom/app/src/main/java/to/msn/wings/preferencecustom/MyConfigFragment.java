package to.msn.wings.preferencecustom;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class MyConfigFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref);
    }
}

