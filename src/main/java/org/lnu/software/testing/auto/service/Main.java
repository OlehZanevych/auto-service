package org.lnu.software.testing.auto.service;

import java.util.Random;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        String messageTemplate = "(%1$d, 'Model %1$d', %2$d, %3$d, 'Model description %1$d'),";
        for (int i = 1; i <= 1000; ++i) {
            System.out.println(String.format(messageTemplate, i, i / 10 + 1, 2000 + random.nextInt(20)));
        }
    }
}
