package de.pfann.mentalcalculator.app.postfixinfixelemente;

/**
 * Created by jopfann on 20.08.14.
 */
public class EmptyStackException extends Exception {

    public EmptyStackException(){
        super();
    }

    public EmptyStackException(final String aMessage){
        super(aMessage);
    }
}
