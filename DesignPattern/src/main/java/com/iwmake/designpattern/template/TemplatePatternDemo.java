package com.iwmake.designpattern.template;

/**
 * 模板模式
 * @author Dylan
 * @since 2020-11-07
 */
public class TemplatePatternDemo {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();

        System.out.println();

        game = new Football();
        game.play();
    }
}
