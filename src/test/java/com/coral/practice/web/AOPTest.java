package com.coral.practice.web;

import com.PracticeApplication;
import com.coral.practice.web.service.PeopleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by qiuhai on 2016/8/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PracticeApplication.class)
@WebAppConfiguration
public class AOPTest {

    @Autowired
    private PeopleService peopleService;

    @Test
    public void testAOP(){
        peopleService.sleep();
    }

}
