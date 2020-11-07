package com.iwmake.designpattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
