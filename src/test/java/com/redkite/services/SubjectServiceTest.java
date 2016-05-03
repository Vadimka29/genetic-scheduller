package com.redkite.services;

import com.redkite.xml.model.SubjectItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadym on 03.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistence.xml"})
public class SubjectServiceTest {

    @Autowired
    private SubjectService taskService;

    @Test
    public void getAllItemsForSubject() throws Exception {
        List<SubjectItem> allItemsForSubject = taskService.getAllItemsForSubject("Decision Theory");
        assertNotNull(allItemsForSubject);
        allItemsForSubject.forEach(subjectItem -> {
            assertNotNull(subjectItem.getRetrieveDate());
            assertNotNull(subjectItem.getDeadline());
            assertNotNull(subjectItem.getDuration());
            assertTrue(subjectItem.getDuration() > 0);
            assertNotNull(subjectItem.getWorkType());
        });
    }
}