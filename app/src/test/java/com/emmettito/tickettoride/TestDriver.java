package com.emmettito.tickettoride;

import com.emmettito.tickettoride.GameTests.GameProxyTest;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;


public class TestDriver {

    public static void main(String[] args) {

        JUnitCore jUnitCore = new JUnitCore();
        jUnitCore.addListener(new TextListener(System.out));

        System.out.println("Testing Game Proxy\n");
        jUnitCore.run(GameProxyTest.class);

    }

}