package com.redkite.xml.parser.impl;

import com.redkite.xml.parser.XMLParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Vadym on 03.05.2016.
 */
public class JaxbParser implements XMLParser {

    @Override
    public Object getObject(File xmlFile, Class cl) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(cl);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(xmlFile);
        return object;
    }
}
