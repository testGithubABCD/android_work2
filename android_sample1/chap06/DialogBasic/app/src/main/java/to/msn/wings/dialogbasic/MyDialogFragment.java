package to.msn.wings.dialogbasic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Objects;

public class MyDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = Objects.requireNonNull(getArguments());
        String txtName = args.getString("txtName");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("ダイアログの基本")
        // return builder.setTitle(R.string.d_title)
            // .setMessage("こんにちは、世界！")
            .setMessage(String.format("こんにちは、%sさん！", txtName))
            .setIcon(R.drawable.wings)
            .create();
    }
}
