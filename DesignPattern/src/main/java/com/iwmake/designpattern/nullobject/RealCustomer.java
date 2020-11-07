package com.iwmake.designpattern.nullobject;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class RealCustomer extends AbstractCustomer {

    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public boolean isNil() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
