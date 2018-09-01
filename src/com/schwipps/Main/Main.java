package com.schwipps.Main;

import com.schwipps.dsf.TypeEquipmentDescription;

import java.io.File;


public class Main {


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Unmarshaller unmarshaller = new Unmarshaller();
        TypeEquipmentDescription equipment =  unmarshaller.unmarshal(new File("C:\\Users\\Chris\\Desktop\\QC11_Testing\\QC11_Testing\\Release\\CF_EquipmentDefinition.xml"));


        System.out.println("success");
    }

}