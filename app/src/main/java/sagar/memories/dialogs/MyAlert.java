package sagar.memories.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;

import sagar.memories.R;
import sagar.memories.utils.Globals;

/**
 * Created by Sagar on 26-03-2016.
 */
public class MyAlert extends DialogFragment {

    Callback callback;
    String personName;

    @Override
    public void onAttach(Activity activity) {
        callback  = (Callback) activity;
        super.onAttach(activity);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password, null);
        final AppCompatEditText et = (AppCompatEditText) view.findViewById(R.id.etServerIp);
        builder.setView(view)
                .setPositiveButton("Unlock", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String text = et.getText().toString().toLowerCase();
                        switch (personName){
                            case Globals.KEY_ADI: if(text.equals(Globals.PASS_ADI)) callback.onTextEntered(); break;
                            case Globals.KEY_DIPAN: if(text.equals(Globals.PASS_DIPAN)) callback.onTextEntered(); break;
                            case Globals.KEY_KRISHNA: if(text.equals(Globals.PASS_KRISHNA)) callback.onTextEntered(); break;
                            case Globals.KEY_SUVI: if(text.equals(Globals.PASS_SUVI)) callback.onTextEntered(); break;
                            default: callback.invalidPass();
                        }
                    }
                });

        return builder.create();
    }

    @Override
    public void setArguments(Bundle args) {
        personName = args.getString(Globals.KEY_PERSON_NAME);
        super.setArguments(args);
    }

    public interface Callback{
        void onTextEntered();
        void invalidPass();
    }
}
