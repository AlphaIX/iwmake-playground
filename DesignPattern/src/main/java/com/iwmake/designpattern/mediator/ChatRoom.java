package com.iwmake.designpattern.mediator;

import java.util.Date;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
    }
}
