package de.pfann.mentalcalculator.app.tasks;

import android.util.Log;

import java.util.Queue;

import de.pfann.mentalcalculator.app.activities.MainActivity;
import de.pfann.mentalcalculator.app.postfix.PostFixCalculator;
import de.pfann.mentalcalculator.app.postfixinfixelemente.Element;

/**
 * Created by jopfann on 24.08.14.
 */
public class TaskCalculator {

    private PostFixCalculator calculator;

    public TaskCalculator(){
         calculator = new PostFixCalculator();
    }

    public boolean isResultCorrect(final double aResultOfHuman,final Queue<Element> aTask){
        Log.i(MainActivity.APP_NAME,"isResultCorrect: aTask.size():" + aTask.size());
        return (aResultOfHuman == calculator.calculatePostfixTask(aTask));
    }

    public double calculateTask(Queue<Element> aTask){
        return calculator.calculatePostfixTask(aTask);
    }
}
