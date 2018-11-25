package com.schwipps.DSFBuilder;

import com.schwipps.DSFBuilder.enums.DebugDataReadRequestCommand;

import java.util.LinkedList;

public class DSFRecordElement {
    private LinkedList<String> recordElementNames;
    private String variable;
    private DebugDataReadRequestCommand readRequestCommand;
    public DSFRecordElement(String variable, LinkedList<String> recordElementNames, DebugDataReadRequestCommand command){
        setRecordElementNames(recordElementNames);
        setVariable(variable);
        setReadRequestCommand(command);
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

    public boolean isPeriodic() {
        if(readRequestCommand.equals(DebugDataReadRequestCommand.READ_DATA_PERIODICALLY)){
            return  true;
        }
        else
        {
            return false;
        }
    }

    public DebugDataReadRequestCommand getReadRequestCommand() {
        return readRequestCommand;
    }

    public void setReadRequestCommand(DebugDataReadRequestCommand readRequestCommand) {
        this.readRequestCommand = readRequestCommand;
    }
}

