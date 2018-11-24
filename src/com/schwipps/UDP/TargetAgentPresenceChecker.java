package com.schwipps.UDP;

import com.schwipps.DSFBuilder.Builder;
import com.schwipps.DSFBuilder.enums.TargetAgentRequestCommand;

public class TargetAgentPresenceChecker implements Runnable {
    private UDPSenderTargetAgent udpSenderTargetAgent;
    boolean running;
    public TargetAgentPresenceChecker(UDPSenderTargetAgent udpSenderTargetAgent){
        this.udpSenderTargetAgent = udpSenderTargetAgent;
    }

    @Override
    public void run() {
        running = true;
        while(running){
            udpSenderTargetAgent.sendMessage(Builder.buildTargetAgentRequestMessage(0, 0, TargetAgentRequestCommand.PRESENCE_CHECK).getByte());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
