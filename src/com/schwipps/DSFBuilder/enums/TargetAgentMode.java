package com.schwipps.DSFBuilder.enums;
    public enum TargetAgentMode {
        DISCONNECTED(0),
        CONNECTED(1),
        INVALID(2);

        private final int i;

        TargetAgentMode(int i){ this.i = i;}
        public int getValue(){return i;}
    }