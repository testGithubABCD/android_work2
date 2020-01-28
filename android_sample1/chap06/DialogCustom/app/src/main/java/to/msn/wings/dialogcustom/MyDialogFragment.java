package to.msn.wings.dialogcustom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class MyDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = Objects.requireNonNull(getArguments());
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity())
            .inflate(R.layout.dialog_body, null);
        TextView txtMsg = layout.findViewById(R.id.txtMsg);
        txtMsg.setText(args.getString("txtName"));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("ダイアログの基本")
            .setView(layout)
            .setIcon(R.drawable.wings)
            .create();
    }
}
