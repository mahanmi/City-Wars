package com.view;

import java.util.Random;

public class Captcha {

    private static final String[][] asciiDigits = {
            {
                    " .d888b. ",
                    " 88   8D ",
                    " 88   88 ",
                    " 88   88 ",
                    " 88   8D ",
                    "  Y888P  "
            },
            {
                    "   d88    ",
                    "  d888    ",
                    "    88    ",
                    "    88    ",
                    "    88    ",
                    "  888888  "
            },
            {
                    " .d8888b. ",
                    " 88    88 ",
                    "     .8P  ",
                    "   .8P'   ",
                    " .8P'     ",
                    " 88888888 "
            },
            {
                    "  d8888b  ",
                    " 88    88 ",
                    "     .8P  ",
                    "     '8P  ",
                    " 88    88 ",
                    "  Y8888P  "
            },
            {
                    "    d8 88 ",
                    "   d8  88 ",
                    "  d8   88 ",
                    " 88888888 ",
                    "      `88 ",
                    "      `88 "
            },
            {
                    " d8888888 ",
                    " 88       ",
                    " 88d888b. ",
                    "      `88 ",
                    " d8b. .88 ",
                    "  Y88888P "
            },
            {
                    " .d8888b ",
                    " d8P' 88 ",
                    " 88      ",
                    " 88d888b.",
                    " 88    8D",
                    "  Y88888 "
            },
            {
                    " 8888888b ",
                    "      d8P ",
                    "     d8P  ",
                    "    d8P   ",
                    "   d8P    ",
                    "  d8P     "
            },
            {
                    " .d888b.  ",
                    " dP   Yb  ",
                    "  Y888P'  ",
                    " d8P  Y8b ",
                    " 88    88 ",
                    "  Y8888P  "
            },
            {
                    " .d888b.  ",
                    " d8    8b ",
                    "  Y888888 ",
                    "       88 ",
                    " d8b  d8b ",
                    "  Y88888  "
            }
    };

    public int generateCaptcha() {
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(9999999 - 10000 + 1);

        String numberString = String.valueOf(randomNumber);

        for (int i = 0; i < 6; i++) { 
            for (char digit : numberString.toCharArray()) {
                System.out.print(asciiDigits[Character.getNumericValue(digit)][i] + " ");
            }
            System.out.println();
        }

        return randomNumber;
    }
}
