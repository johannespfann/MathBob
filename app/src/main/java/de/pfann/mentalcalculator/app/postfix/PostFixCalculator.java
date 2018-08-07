package de.pfann.mentalcalculator.app.postfix;

import android.util.Log;

import java.util.Queue;
import java.util.Stack;

import de.pfann.mentalcalculator.app.postfixinfixelemente.DividendOperator;
import de.pfann.mentalcalculator.app.postfixinfixelemente.Element;
import de.pfann.mentalcalculator.app.postfixinfixelemente.MinusOperator;
import de.pfann.mentalcalculator.app.postfixinfixelemente.MultiplierOperator;
import de.pfann.mentalcalculator.app.postfixinfixelemente.Operant;
import de.pfann.mentalcalculator.app.postfixinfixelemente.Operator;
import de.pfann.mentalcalculator.app.postfixinfixelemente.PlusOperator;

/**
 * Created by jopfann on 21.08.14.
 */
public class PostFixCalculator{

    private Queue<Element> postfixQueue;

    private Stack<Double> result;

    private String logTag;

    public Queue<Element> getPostfixQueue() {
        return postfixQueue;
    }

    public Stack<Double> getResult() {
        return result;
    }

    public PostFixCalculator() {
        result = new Stack<Double>();
        logTag = PostFixCalculator.class.toString();
    }

    public double calculatePostfixTask(Queue<Element> aTask) {
        Queue<Element> copyTask;
        postfixQueue = aTask;
        Log.i(logTag, "Anazahl postfix: " + getPostfixQueue().size());
        Log.i(logTag, "Anazahl ResultStack  : " + result.size());
        do {
            Element element = postfixQueue.remove();
            Log.i(logTag, "Next Token is: " + element.getToken());
            if (element instanceof Operator) {
                handleOperator(element);
            } else if (element instanceof Operant) {
                handleOperant(element);
            }
        } while (!postfixQueue.isEmpty());
        return result.pop();
    }

    private void handleOperator(final Element aElement){
        double first = result.pop();
        double second = result.pop();
        if(aElement instanceof PlusOperator){
            result.push(second + first);
        }
        if(aElement instanceof MinusOperator){
            result.push(second - first);
        }
        if(aElement instanceof MultiplierOperator){
            result.push(second * first);
        }
        if(aElement instanceof DividendOperator){
            result.push(second / first);
        }
    }

    private void handleOperant(final Element aElement){
        result.push(Double.parseDouble(aElement.getToken()));
    }
}
