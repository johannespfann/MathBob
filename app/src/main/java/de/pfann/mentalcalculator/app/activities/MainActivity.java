package de.pfann.mentalcalculator.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import de.pfann.mentalcalculator.app.R;
import de.pfann.mentalcalculator.app.activities.elements.GameSettings;
import de.pfann.mentalcalculator.app.activities.elements.ResultPresentationDialog;
import de.pfann.mentalcalculator.app.setttings.SettingsPreferenceActivity;
import de.pfann.mentalcalculator.app.tasks.SimpleMinusTask;
import de.pfann.mentalcalculator.app.tasks.SimpleMultiplicationTask;
import de.pfann.mentalcalculator.app.tasks.SimplePlusTask;
import de.pfann.mentalcalculator.app.tasks.SimpleTimesTableTask;


public class MainActivity extends Activity {

    protected static final int SUB_ACTIVITY_REQUEST_CODE_SINGLEPLAYER = 100;
    protected static final int SUB_ACTIVITY_REQUEST_CODE_MULTIPLAYER = 101;
    public static final String APP_NAME = "mental.calculator";
    public static final String RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(APP_NAME,"Start MentalCalculator");
    }

    public void startSinglePlayerGame(final View aView) {
        GameSettings gameSettings = buildGameSettings();
        Intent intent = new Intent(this,GameActivity.class);
        startActivityForResult(intent,SUB_ACTIVITY_REQUEST_CODE_SINGLEPLAYER);
    }

    public void startMultiPlayerGame(final View aView){
        Log.i(APP_NAME, "Start MultiplayerGame");
        GameSettings gameSettings = GameSettings.getInstance();
        gameSettings.setCountDown(120);
        gameSettings.addNewFactory(new SimpleMinusTask());
        gameSettings.addNewFactory(new SimpleMultiplicationTask());
        gameSettings.addNewFactory(new SimplePlusTask());
        gameSettings.addNewFactory(new SimpleTimesTableTask());
        Intent intent = new Intent(this, GameActivity.class);
        startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE_MULTIPLAYER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(APP_NAME, "onActivityResult");
        Log.i(APP_NAME, "RequestCode: " + requestCode);
        Log.i(APP_NAME, "ResultCode : " + resultCode);
        if(resultCode == Activity.RESULT_OK) {
            ResultPresentationDialog dialog = new ResultPresentationDialog(this,
                    data.getIntExtra(MainActivity.RESULT,0));
            Log.i(APP_NAME, "IntentExtra: " + data.getIntExtra(MainActivity.RESULT, -1));

            if (requestCode == SUB_ACTIVITY_REQUEST_CODE_SINGLEPLAYER) {
                dialog.getDialog().show();
                Log.i(APP_NAME, "was singleplayer");
            }
            if (requestCode == SUB_ACTIVITY_REQUEST_CODE_MULTIPLAYER) {
                dialog.getDialog().show();
                Log.i(APP_NAME, "was multiplayer");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingsPreferenceActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private GameSettings buildGameSettings(){
        GameSettings gameSettings = GameSettings.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        gameSettings.setCountDown(Integer.parseInt(preferences.getString(getString(R.string
                        .pref_key_countdownList),"60")));
        if(preferences.getBoolean(getString(R.string.pref_key_simplePlusTask),true)){
            gameSettings.addNewFactory(new SimplePlusTask());
        }
        if(preferences.getBoolean(getString(R.string.pref_key_simpleMinusTask),true)){
            gameSettings.addNewFactory(new SimpleMinusTask());
        }
        if(preferences.getBoolean(getString(R.string.pref_key_simpleMultiplicationTask),true)){
            gameSettings.addNewFactory(new SimpleMultiplicationTask());
        }
        if(preferences.getBoolean(getString(R.string.pref_key_simpleTimeTableTask),true)){
            gameSettings.addNewFactory(new SimpleTimesTableTask());
        }
        return gameSettings;
    }

    public void onClickImage(final View aView){
        Log.i(APP_NAME,"Image Clicked");
        drawText();
    }

    private void drawText(){
        ImageView imageView = (ImageView) findViewById(R.id.imageview_test_key);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.logo3);

        Bitmap.Config config = bm.getConfig();
        int width = bm.getWidth();
        int height = bm.getHeight();

        Bitmap newImage = Bitmap.createBitmap(width, height, config);

        Canvas canvas = new Canvas(newImage);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(20);
        canvas.drawText("Hallo",0,25,paint);
        imageView.setImageBitmap(newImage);
    }
}
