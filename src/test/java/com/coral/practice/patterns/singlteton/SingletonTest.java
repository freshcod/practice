package com.coral.practice.patterns.singlteton;

import com.PracticeApplication;
import com.coral.practice.patterns.singleton.HungrySingleton;
import com.coral.practice.patterns.singleton.LazySingleton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by qiuhai on 2016/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PracticeApplication.class)
@WebAppConfiguration
public class SingletonTest {
    @Test
    public void testLazySingleton() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class clazz = LazySingleton.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazySingleton lazySingleton1 = LazySingleton.getINSTANCE();
        LazySingleton lazySingleton2 = LazySingleton.getINSTANCE();
        System.out.println(lazySingleton1==lazySingleton2);
        LazySingleton lazySingleton3 = (LazySingleton)constructor.newInstance();
        System.out.println(lazySingleton1==lazySingleton3);
    }

    @Test
    public void testHungrySingleton() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor constructor = HungrySingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        HungrySingleton hungrySingleton3 = (HungrySingleton) constructor.newInstance();
        HungrySingleton hungrySingleton1 = HungrySingleton.getINSTANCE();
        HungrySingleton hungrySingleton2 = HungrySingleton.getINSTANCE();
        System.out.println(hungrySingleton1==hungrySingleton2);

        System.out.println(hungrySingleton1 == hungrySingleton3);
    }
}
