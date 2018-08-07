package de.pfann.mentalcalculator.app.tasks;

import java.util.LinkedList;
import java.util.List;

import de.pfann.mentalcalculator.app.activities.elements.GameSettings;
import de.pfann.mentalcalculator.app.util.NumberGenerator;

/**
 * Created by jopfann on 24.08.14.
 */
public class TaskManager {
    private String logTag;
    private List<TaskFactory> taskFactories;

    public TaskManager(final String aLogTag, final GameSettings gameSettings){
        logTag = aLogTag;
        taskFactories = new LinkedList<TaskFactory>();
        taskFactories = gameSettings.getTaskFactoryList();
    }

    public Task generateNewTask(){
        int randomFactory = NumberGenerator.getRandomInteger(0,taskFactories.size());
        TaskFactory taskFactory = taskFactories.get(randomFactory);
        return taskFactory.getNewTask();
    }
}

