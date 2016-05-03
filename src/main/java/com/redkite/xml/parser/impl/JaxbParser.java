package com.redkite.xml.parser.impl;

import com.redkite.xml.model.SubjectsHolder;
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
    public SubjectsHolder getObject(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SubjectsHolder.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object result = unmarshaller.unmarshal(xmlFile);
        return (SubjectsHolder) result;
    }
}
