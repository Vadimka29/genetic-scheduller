package com.redkite.services.impl.xml;

import com.redkite.services.SubjectService;
import com.redkite.xml.model.Subject;
import com.redkite.xml.model.SubjectItem;
import com.redkite.xml.model.SubjectsHolder;
import com.redkite.xml.parser.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Vadym on 03.05.2016.
 */
@Service
public class XmlSubjectService implements SubjectService {
    private XMLParser xmlParser;
    private SubjectsHolder subjectsHolder;

    @Autowired
    public XmlSubjectService(XMLParser parser) throws JAXBException {
        this.xmlParser = parser;
        String pathToXmlDataFile = getClass().getClassLoader().getResource("testdata/test-data.xml").getPath();
        subjectsHolder = xmlParser.getObject(new File(pathToXmlDataFile));
    }


    @Override
    public List<SubjectItem> getAllItemsForSubject(String subjectName) {
        Assert.notNull(subjectName, "subjectName can't be null");
        List<Subject> resultList = subjectsHolder.getSubject().stream()
                .filter(subj -> subjectName.equals(subj.getName()))
                .collect(Collectors.toList());
        if(resultList != null && !resultList.isEmpty()){
            return resultList.get(0).getSubjectItem();
        }
        return null;
    }
}
