package de.pfann.mentalcalculator.app.postfixinfixelemente;

/**
 * Created by jopfann on 21.08.14.
 */
public class Operant implements Element {

    private String token;


    public Operant(final double aValue){
        token = String.valueOf(aValue);
    }
    @Override
    public String getToken() {
        return token;
    }
}
