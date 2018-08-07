package de.pfann.mentalcalculator.app.activities.elements;

import java.util.ArrayList;
import java.util.List;

import de.pfann.mentalcalculator.app.tasks.TaskFactory;

/**
 * Created by johannespfann on 21.10.14.
 */
public class GameSettings {
    private static GameSettings instance = null;

    private int mCountDown;
    private List<TaskFactory> taskFactoryList;

    private GameSettings(){
        taskFactoryList = new ArrayList<TaskFactory>();
        mCountDown = 120;
    }

    public static GameSettings getInstance(){
        if(instance == null){
            instance = new GameSettings();
        }
        return instance;
    }

    public void addNewFactory(final TaskFactory taskFactory){
        taskFactoryList.add(taskFactory);
    }

    public List<TaskFactory> getTaskFactoryList(){
        return taskFactoryList;
    }

    public void setCountDown(final int aCountDown){
        mCountDown = aCountDown;
    }

    public int getCountDown(){
        return mCountDown;
    }
}
