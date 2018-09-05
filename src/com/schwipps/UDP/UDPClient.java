package com.schwipps.UDP;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

    public void test() {
            try {
                DatagramSocket clientSocket = new DatagramSocket(4445);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        InetAddress adress = null;
        try {
            adress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        System.out.println(adress.toString());
    }
}
