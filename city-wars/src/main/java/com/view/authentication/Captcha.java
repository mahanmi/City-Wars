package com.view.authentication;

import java.util.Random;

public class Captcha {

        public static void main(String[] args) {
                Captcha captcha = new Captcha();
                System.out.println(captcha.generateCaptcha(12345));
        }

        private static final String[][] asciiDigits = {
                        {
                                        " ░▒▓████████▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓████████▓▒░ "

                        },
                        {
                                        "    ░▒▓█▓▒░ ",
                                        " ░▒▓████▓▒░ ",
                                        "    ░▒▓█▓▒░ ",
                                        "    ░▒▓█▓▒░ ",
                                        "    ░▒▓█▓▒░ ",
                                        "    ░▒▓█▓▒░ ",
                                        "    ░▒▓█▓▒░ "

                        },
                        {

                                        " ░▒▓███████▓▒░  ",
                                        "        ░▒▓█▓▒░ ",
                                        "        ░▒▓█▓▒░ ",
                                        "  ░▒▓██████▓▒░  ",
                                        " ░▒▓█▓▒░        ",
                                        " ░▒▓█▓▒░        ",
                                        " ░▒▓████████▓▒░ ",

                        },
                        {

                                        " ░▒▓███████▓▒░ ",
                                        "       ░▒▓█▓▒░ ",
                                        "       ░▒▓█▓▒░ ",
                                        "  ░▒▓██████▓▒░ ",
                                        "       ░▒▓█▓▒░ ",
                                        "       ░▒▓█▓▒░ ",
                                        " ░▒▓███████▓▒░ "
                        },
                        {

                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓████████▓▒░ ",
                                        "        ░▒▓█▓▒░ ",
                                        "        ░▒▓█▓▒░ ",
                                        "        ░▒▓█▓▒░ "

                        },
                        {

                                        " ░▒▓████████▓▒░ ",
                                        " ░▒▓█▓▒░        ",
                                        " ░▒▓█▓▒░        ",
                                        " ░▒▓███████▓▒░  ",
                                        "       ░▒▓█▓▒░  ",
                                        "       ░▒▓█▓▒░  ",
                                        " ░▒▓███████▓▒░  "

                        },
                        {

                                        " ░▒▓███████▓▒░  ",
                                        " ░▒▓█▓▒░        ",
                                        " ░▒▓█▓▒░        ",
                                        " ░▒▓███████▓▒░  ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        "  ░▒▓██████▓▒░  "

                        },
                        {

                                        " ░▒▓████████▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        "        ░▒▓█▓▒░ ",
                                        "       ░▒▓█▓▒░  ",
                                        "       ░▒▓█▓▒░  ",
                                        "      ░▒▓█▓▒░   ",
                                        "      ░▒▓█▓▒░   "

                        },
                        {

                                        "  ░▒▓██████▓▒░  ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        "  ░▒▓██████▓▒░  ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        "  ░▒▓██████▓▒░  "

                        },
                        {

                                        "  ░▒▓██████▓▒░  ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        " ░▒▓█▓▒░░▒▓█▓▒░ ",
                                        "  ░▒▓███████▓▒░ ",
                                        "        ░▒▓█▓▒░ ",
                                        "        ░▒▓█▓▒░ ",
                                        "  ░▒▓██████▓▒░  "

                        }
        };

        public int generateCaptcha() {
                Random random = new Random();
                int randomNumber = 10000 + random.nextInt(9999999 - 10000 + 1);

                String numberString = String.valueOf(randomNumber);

                for (int i = 0; i < 7; i++) {
                        for (char digit : numberString.toCharArray()) {
                                System.out.print(asciiDigits[Character.getNumericValue(digit)][i] + " ");
                        }
                        System.out.println();
                }

                return randomNumber;
        }

        public String generateCaptcha(int randomNumber) {
                String numberString = String.valueOf(randomNumber);

                String captchaString = "";

                for (int i = 0; i < 7; i++) {
                        for (char digit : numberString.toCharArray()) {
                                captchaString += asciiDigits[Character.getNumericValue(digit)][i] + " ";
                        }
                        captchaString += "\n";
                }

                return captchaString;
        }
}