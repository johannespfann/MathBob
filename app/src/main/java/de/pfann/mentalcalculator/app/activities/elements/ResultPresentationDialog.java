package de.pfann.mentalcalculator.app.activities.elements;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by johannespfann on 12.11.14.
 */
public class ResultPresentationDialog {

    private Context mContext;
    private int mResult;

    public ResultPresentationDialog(final Context aContext,final int aResult){
        mContext = aContext;
        mResult = aResult;
    }

    public AlertDialog getDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle("Ergebnis:");
        String message = "";
        if(mResult > 1 || mResult == 0){
            message = " Punkte!";
        }
        if(mResult == 1){
            message = " Punkt!";
        }
        alertDialogBuilder.setMessage(mResult + message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int i) {
                dialogInterface.cancel();
            }
        });
        return alertDialogBuilder.create();
    }
}
