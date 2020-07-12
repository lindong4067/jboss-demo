package com.example.modules.export.d;

import com.example.modules.export.b.Baker;
import com.example.modules.export.c.Cast;
import com.examples.modules.export.a.Afirm;

public class Direct implements DirectMBean{
    @Override
    public void direct() throws ClassNotFoundException {
        System.out.println("com.example.modules.export.d.Direct.direct() start");
        new Afirm().afirm();
        new Baker().baker();
        new Cast().cast();
        System.out.println("com.example.modules.export.d.Direct.direct() stop");
    }
}
