package com.examples.modules.export.a;

import com.example.modules.export.b.Baker;

public class Afirm {
    @SuppressWarnings({"rawtypes" })
    public void afirm() throws ClassNotFoundException {
        System.out.println("    ->  A");

        //depend on B
        new Baker().baker();
        Class clas = this.getClass().getClassLoader().loadClass("org.jboss.modules.export.c.Cast");
//		Class.forName("org.jboss.modules.export.c.Cast").newInstance().getClass();

        System.out.println("A load C " + clas + " success");
        System.out.println(this.getClass().getClassLoader());
        System.out.println("A   ->  \n");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new Afirm().afirm();
    }
}
