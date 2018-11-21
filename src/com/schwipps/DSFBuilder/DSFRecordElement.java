package com.schwipps.DSFBuilder;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class DSFRecordElement {
    private LinkedList<String> recordElementNames;
    private String variable;

    public DSFRecordElement(String variable, LinkedList<String> recordElementNames){
        setRecordElementNames(recordElementNames);
        setVariable(variable);
    }

    public LinkedList<String> getRecordElementNames() {
        return recordElementNames;
    }

    public void setRecordElementNames(LinkedList<String> recordElementNames) {
        this.recordElementNames = recordElementNames;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }
}

