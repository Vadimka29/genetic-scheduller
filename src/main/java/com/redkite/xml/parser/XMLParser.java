package com.redkite.xml.parser;

import com.redkite.xml.model.SubjectsHolder;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by Vadym on 03.05.2016.
 */
public interface XMLParser {
    SubjectsHolder getObject(File xmlFile) throws JAXBException;
//    boolean saveObject(File xmlFile, Object obj) throws JAXBException;
}
