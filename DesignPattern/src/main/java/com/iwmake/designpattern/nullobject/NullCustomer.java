package com.iwmake.designpattern.nullobject;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class NullCustomer extends AbstractCustomer {

    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }
}
