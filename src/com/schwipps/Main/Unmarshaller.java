package com.schwipps.Main;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.schwipps.dsf.ObjectFactory;
import com.schwipps.dsf.TypeEquipmentDescription;


public class Unmarshaller {

    TypeEquipmentDescription unmarshal(File f) {
        try {
            JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
            javax.xml.bind.Unmarshaller jcUnmarshaller = jc.createUnmarshaller();
            Object object = jcUnmarshaller.unmarshal(f);

            if(object instanceof JAXBElement)  {
                JAXBElement<?> element = (JAXBElement<?>)jcUnmarshaller.unmarshal(f);
                if(element.getDeclaredType().equals(TypeEquipmentDescription.class)) {
                    //THIS IS TYPESAFE
                    @SuppressWarnings("unchecked")
                    TypeEquipmentDescription equipment = ((JAXBElement<TypeEquipmentDescription>)element).getValue() ;
                    return equipment;
                }
            }


            return null;
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
