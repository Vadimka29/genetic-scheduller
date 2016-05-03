package com.redkite.services;

import com.redkite.xml.model.Subject;
import com.redkite.xml.model.SubjectItem;

import java.util.List;

/**
 * Created by Vadym on 03.05.2016.
 */
public interface SubjectService {
    List<SubjectItem> getAllItemsForSubject(String subjectName);
}
