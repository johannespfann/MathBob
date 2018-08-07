package de.pfann.mentalcalculator.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import de.pfann.mentalcalculator.app.R;
import de.pfann.mentalcalculator.app.activities.elements.GameSettings;
import de.pfann.mentalcalculator.app.activities.elements.LeavingGameDialog;
import de.pfann.mentalcalculator.app.tasks.Task;
import de.pfann.mentalcalculator.app.tasks.TaskCalculator;
import de.pfann.mentalcalculator.app.tasks.TaskManager;
import de.pfann.mentalcalculator.app.util.ResultCounter;

public class GameActivity extends Activity {

    private static final String TAG = MainActivity.APP_NAME;

    private TextView mCountdownTextView;
    private TextView mResultCounterTextView;
    private TextView mTaskDisplayTextView;
    private TextView mGamerResultTextView;

    private Task mCurrentTask;
    private TaskManager mTaskManager;
    private TaskCalculator mTaskCalulator;
    private ResultCounter mResultCounter;
    private int mCountdown;
    private CountDownTimer mCountdownTimer;

    private boolean isLeft = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setupGameSettings();
        setNextTask();
        setupGameElements();
        startCountDown();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
        isLeft = true;
        mCountdownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isLeft) {
            LeavingGameDialog dialog = new LeavingGameDialog(this,this);
            dialog.getDialog().show();
        }
    }

    private void setNextTask(){
        mCurrentTask = mTaskManager.generateNewTask();
    }

    private void setupGameSettings(){
        GameSettings gameSettings = GameSettings.getInstance();
        mCountdown = gameSettings.getCountDown();
        mTaskManager = new TaskManager(TAG,gameSettings);
    }

    private void setupGameElements(){
        mCountdownTextView = (TextView) findViewById(R.id.textview_countdown);
        mTaskDisplayTextView = (TextView) findViewById(R.id.textview_task);
        mResultCounterTextView = (TextView) findViewById(R.id.textview_result_counter);
        mGamerResultTextView = (TextView) findViewById(R.id.textview_result_input);
        mTaskCalulator = new TaskCalculator();
        mResultCounter = new ResultCounter();
        mTaskDisplayTextView.setText(mCurrentTask.toString());
    }

    private void verifyTask(){
        if(mGamerResultTextView.getText().length() <= 0){
            return;
        }
        double result = Double.parseDouble(mGamerResultTextView.getText().toString());
        if(mTaskCalulator.isResultCorrect(result,mCurrentTask.getTaskQueue())){
            mResultCounter.incrementRightTasks(mCurrentTask.getProfitPoints());
        }else{
            mResultCounter.incrementWrongTasks(mCurrentTask.getPenaltyPoints());
        }
        mResultCounterTextView.setText(mResultCounter.countResultAsString());
        mCurrentTask = mTaskManager.generateNewTask();
        mTaskDisplayTextView.setText(mCurrentTask.toString());
        mGamerResultTextView.setText("");
    }

    private void startCountDown(){
        mCountdownTimer = new CountDownTimer(mCountdown * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mCountdownTextView.setText(String.valueOf(millisUntilFinished / 1000));
                mCountdown--;
            }
            public void onFinish() {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.RESULT,mResultCounter.countResult());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        }.start();
    }

    public void onDigitClicked(final View aView) {
        switch (aView.getId()) {
            case R.id.button_return:
                int length = mGamerResultTextView.getText().length();
                if(length > 0) {
                    mGamerResultTextView.setText(mGamerResultTextView.getText().subSequence(0,
                            length - 1));
                }
                break;
            case R.id.button_right:
                verifyTask();
                break;
            case R.id.digit_button_0:
            case R.id.digit_button_1:
            case R.id.digit_button_2:
            case R.id.digit_button_3:
            case R.id.digit_button_4:
            case R.id.digit_button_5:
            case R.id.digit_button_6:
            case R.id.digit_button_7:
            case R.id.digit_button_8:
            case R.id.digit_button_9:
                if(mGamerResultTextView.getText().length() > 6){
                    break;
                }
                mGamerResultTextView.setText(mGamerResultTextView.getText() + ((Button) aView)
                        .getText()
                        .toString().trim());
                break;

        }
    }
}
