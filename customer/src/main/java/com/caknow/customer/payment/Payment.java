package com.caknow.customer.payment;

/**
 * Created by junu on 1/2/2017.
 */

public class Payment {

    public enum Type{
        AMEX, VISA, MC, DISC
    }

    private Type type;
    private int lastFour;
    private String exp;
    private String name;

    public Payment(Type type, int lastFour, String exp, String name){
        this.type = type;
        this.lastFour = lastFour;
        this.exp = exp;
        this.name = name;
    }

    public Type getType(){
        return this.type;
    }

    public int getLastFour(){
        return this.lastFour;
    }

    public String getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }
}
