package com.coral.practice.patterns.singlteton;

import com.coral.practice.utils.JsonUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by qiuhai on 2016/7/11.
 */
public class Test {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Test test = new Test();
//        Student student = (Student)test.getPerson();
//        System.out.println(student);
//
        List<Person> persons = test.getPersons();
        System.out.println(JsonUtils.list2json(persons));
        Map<String,Person> testMap = new HashMap<>();
        testMap.put("stu",persons.get(0));
        testMap.get("stu").setName("test11");
        System.out.println(JsonUtils.list2json(persons));

        Student student = new Student();
        student.setName("test");
        student.setAge(23);
        student.setMark(654);

        Student t = student;
        t.setName("zsls");
        System.out.println(student);

        List<String> test1 = Arrays.asList("a","b","c","d");
        System.out.println(test1);
        test1.forEach(str->{System.out.println(str);});

        System.out.println(7>>1);

        System.out.println(1|2);
    }


    public Person getPerson() throws InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        person.setName("zs");
        person.setAge(23);
        System.out.println(person);
        Student student = new Student();
        BeanUtils.copyProperties(student,person);
        student.setMark(654);
        System.out.println(student);
        return student;
    }

    public List<Person> getPersons(){
        List<Person> persons = new ArrayList<Person>();
        for(int i=0;i<4;i++){
            Student student = new Student();
            student.setName("test"+i);
            student.setAge(23+i);
            student.setMark(654+i);
            persons.add(student);
        }
        return persons;
    }
}
