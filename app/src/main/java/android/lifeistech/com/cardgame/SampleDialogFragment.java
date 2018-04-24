package android.lifeistech.com.cardgame;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class SampleDialogFragment extends DialogFragment {

    TextView nextPlayerText;
    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> logList;
    int logCount;

    static SampleDialogFragment newInstance(int num, ArrayList<String> logList, int logCount) {
        SampleDialogFragment f = new SampleDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        args.putStringArrayList("logList", logList);
        args.putInt("logCount", logCount);
        f.setArguments(args);

        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.view_dialog);

        int mNum = getArguments().getInt("num");
        logList = getArguments().getStringArrayList("logList");
        logCount = getArguments().getInt("logCount");

        listView = (ListView) dialog.findViewById(R.id.listView);
        nextPlayerText = (TextView) dialog.findViewById(R.id.nextPlayerText);
        button = (Button) dialog.findViewById(R.id.button);

        adapter = new ArrayAdapter(getActivity(), R.layout.log_dialog);
        for (int i = logList.size() - logCount; i < logList.size(); i++) {
            adapter.add(logList.get(i));
        }

        listView.setAdapter(adapter);

        nextPlayerText.setText("プレイヤー" + mNum + "のターンです");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // DialogFragment のレイアウトを縦横共に全域まで広げます
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 255, 255)));

        return dialog;
    }
}