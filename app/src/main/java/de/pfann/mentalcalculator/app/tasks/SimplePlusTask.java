package de.pfann.mentalcalculator.app.tasks;

import java.util.LinkedList;
import java.util.Queue;

import de.pfann.mentalcalculator.app.postfixinfixelemente.Element;
import de.pfann.mentalcalculator.app.postfixinfixelemente.Operant;
import de.pfann.mentalcalculator.app.postfixinfixelemente.PlusOperator;
import de.pfann.mentalcalculator.app.util.NumberGenerator;

/**
 * Created by jopfann on 24.08.14.
 */
public class SimplePlusTask implements TaskFactory {
    @Override
    public Task getNewTask() {
        // x y + -> x + y
        Queue<Element> task = new LinkedList<Element>();
        int first = NumberGenerator.getRandomInteger(0,50);
        int second = NumberGenerator.getRandomInteger(0,50);
        task.add(new Operant(first));
        task.add(new Operant(second));
        task.add(new PlusOperator());
        return new Task(task,1,2,buildString(first,second));
    }

    private String buildString(final int aFirst,final int aSecond){
        return aFirst + " + " + aSecond;
    }
}
