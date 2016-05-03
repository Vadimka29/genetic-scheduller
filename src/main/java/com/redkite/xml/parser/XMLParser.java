package com.redkite.xml.parser;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by Vadym on 03.05.2016.
 */
public interface XMLParser {
    Object getObject(File xmlFile, Class cl) throws JAXBException;
//    boolean saveObject(File xmlFile, Object obj) throws JAXBException;
}
