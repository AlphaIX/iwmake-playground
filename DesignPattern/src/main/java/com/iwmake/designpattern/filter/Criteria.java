package com.iwmake.designpattern.filter;

import java.util.List;

/**
 * 为标准（Criteria）创建接口，根据不同的标准过滤用户
 * @author Dylan
 * @since 2020-11-07
 */
public interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
