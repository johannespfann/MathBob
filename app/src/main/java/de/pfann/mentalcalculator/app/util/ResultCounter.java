package de.pfann.mentalcalculator.app.util;

/**
 * Created by jopfann on 05.08.14.
 */
public class ResultCounter {

    private int wrongTasks;
    private int rightTasks;

    public ResultCounter(){
        wrongTasks = 0;
        rightTasks = 0;
    }

    public void incrementRightTasks(int points){
        rightTasks = rightTasks + points;
    }

    public void incrementWrongTasks(int points){
        wrongTasks = wrongTasks + points;
    }

    public int countResult(){
        return rightTasks - wrongTasks;
    }

    public String countResultAsString(){
        return String.valueOf(rightTasks - wrongTasks);
    }
}
