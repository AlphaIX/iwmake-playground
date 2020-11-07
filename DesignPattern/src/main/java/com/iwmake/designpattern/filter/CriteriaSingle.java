package com.iwmake.designpattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePerson = new ArrayList<>();
        for (Person person : persons) {
            if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
                singlePerson.add(person);
            }
        }
        return singlePerson;
    }
}
