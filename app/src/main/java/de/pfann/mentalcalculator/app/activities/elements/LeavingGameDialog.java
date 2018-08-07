package de.pfann.mentalcalculator.app.activities.elements;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by johannespfann on 11.11.14.
 */
public class LeavingGameDialog {

    private Context mContext;
    private Activity mActivity;

    public LeavingGameDialog(final Context aContext,final Activity aActivity){
        mActivity = aActivity;
        mContext = aContext;
    }

    public AlertDialog getDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle("Beendet");

        alertDialogBuilder.setMessage("Spiel wurde beendet!");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int i) {
                mActivity.finish();
            }
        });
        return alertDialogBuilder.create();
    }
}
