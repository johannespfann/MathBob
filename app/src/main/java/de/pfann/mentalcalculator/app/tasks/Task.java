package de.pfann.mentalcalculator.app.tasks;

import java.util.LinkedList;
import java.util.Queue;

import de.pfann.mentalcalculator.app.postfixinfixelemente.Element;

/**
 * Created by jopfann on 25.08.14.
 */
public class Task {

    private Queue<Element> taskQueue;

    private int profitPoints;

    private int penaltyPoints;

    private String displayString;

    public Task(final Queue<Element> aTaskQueue, final int aProfitPoints,final int aPenaltyPoints,final String stringToDisplay){
        taskQueue = new LinkedList<Element>(aTaskQueue);
        profitPoints = aProfitPoints;
        penaltyPoints = aPenaltyPoints;
        displayString = stringToDisplay;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public int getProfitPoints() {
        return profitPoints;
    }

    public Queue<Element> getTaskQueue() {
        return new LinkedList<Element>(taskQueue);
    }

    @Override
    public String toString(){
       return displayString;
    }
}
