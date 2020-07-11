package com.examples.modules.export.a;

import com.example.modules.export.b.Baker;
import com.example.modules.export.c.Cast;

public class Afirm {
    @SuppressWarnings({"rawtypes" })
    public void afirm() throws ClassNotFoundException {
        System.out.println("    ->  A");

        //depend on B
        new Baker().baker();
        Class clas = this.getClass().getClassLoader().loadClass("com.example.modules.export.c.Cast");
        System.out.println("A load C " + clas + " success");

        new Cast().cast();
        System.out.println("A invoke C success");
        System.out.println(this.getClass().getClassLoader());
        System.out.println("A   ->  \n");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new Afirm().afirm();
    }
}
