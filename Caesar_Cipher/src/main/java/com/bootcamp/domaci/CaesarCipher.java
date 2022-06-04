package com.bootcamp.domaci;

import java.util.Locale;
import java.util.Scanner;

public class CaesarCipher {

    public static char nonAlphabetChar(char interpunctionSign) {
        switch (interpunctionSign) {

            case '.':
                return '.';
            case ',':
                return ',';
            case '?':
                return '?';
            case '!':
                return '!';
            case ':':
                return ':';
            case ';':
                return ';';
            case '-':
                return '-';
            case '"':
                return '"';
            case '(':
                return '(';
            case ')':
                return ')';
            case '—':
                return '—';
            case '‘':
                return '‘';
            case ' ':
                return ' ';
            case '@':
                return '@';
            case '\'':
                return '\'';

            default:
                return '\0';
        }

    }

    public static char returnEncodedLetter(char letter, int position) {
        String stringLetter = "" + letter;

        if (position > 26) {
            position = position % 26;
        }

        if (Character.isDigit(letter)) {
            return letter;
        }
        if (!Character.isAlphabetic(letter)) {
            return nonAlphabetChar(letter);
        }
        if ((letter - position) < 65) {
            return (char) ((letter - position) + 26);
        }

        return (char) (letter - position);
    }

    public static char returnDecodedLetter(char encodedLetter, int position) {
        String stringLetter = "" + encodedLetter;
        if (position > 26) {
            position = position % 26;
        }

        if (Character.isDigit(encodedLetter)) {
            return encodedLetter;
        }
        if (!Character.isAlphabetic(encodedLetter)) {
            return nonAlphabetChar(encodedLetter);
        }

        if ((encodedLetter + position) > 90) {
            return (char) ((encodedLetter + position) - 26);
        }

        return (char) ((encodedLetter + position));
    }

    public static String encodeText(String plainText, int leftShift) {
        plainText = plainText.toUpperCase(Locale.ENGLISH);
        String encodedText = "";
        char[] plainTextCharArr = plainText.toCharArray();
        for (int i = 0; i < plainTextCharArr.length; i++) {
            encodedText += returnEncodedLetter(plainTextCharArr[i], leftShift);
        }
        return encodedText;
    }

    public static String decodeText(String encodedText, int leftShift) {
        encodedText = encodedText.toUpperCase(Locale.ENGLISH);
        String decodedText = "";
        char[] encodedTextArray = encodedText.toCharArray();
        for (int i = 0; i < encodedTextArray.length; i++) {
            decodedText += returnDecodedLetter(encodedTextArray[i], leftShift);
        }
        return decodedText;
    }

    public static String textValidator(Scanner sc) {
        String text = "";
        do {
            System.out.println("Your text can have: numbers, English-Alphabet-letters and punctuation marks.");
            System.out.println("Enter your text:");
            text = sc.nextLine();


        } while (!text.matches("[0-9a-zA-Z]+[0-9a-zA-Z ,.@!?:\\-;\"'—()‘]*"));
        return text;
    }

    public static int optionsValidator(Scanner sc) {
        int option = -1;

        do {
            System.out.println("Press '1' to encode your text or '2' to decode it:");
            try {

                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Your input was not a number!");
            }
        } while (option != 1 && option != 2);
        return option;
    }

    public static int leftShiftValidator(Scanner sc) {
        int position = -1;

        do {
            System.out.println("Enter by how much positions you would like to rotate the letters: [must be >= 0]");
            try {

                position = Integer.parseInt(sc.nextLine());
                if (position < 0) {
                    System.out.println("Your number must be positive!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Your input was not a number!");
            }
        } while (position < 0);
        return position;
    }

    public static void userInterface() {
        Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);
        System.out.println("Welcome to Caesar Cipher tool!");
        System.out.println("This cipher will only use left shift letter rotation.");
        System.out.println("FYI: Your text will be capitalised.");

        String text = textValidator(input);
        int option = optionsValidator(input);
        int leftShift = leftShiftValidator(input);


        if (option == 1) {
            System.out.println("Your encoded text is:\n" + encodeText(text, leftShift));
        } if(option == 2) {
            System.out.println("Your decoded text is:\n" + decodeText(text, leftShift));
        }

    }


    public static void main(String[] args) {

        //example
        String textToEncode = "If he had anything confidential to say, he wrote it in cipher, that is, by so changing the order of the letters of the alphabet, that not a word could be made out.";
        String encodedText = encodeText(textToEncode, 3);
        System.out.println(encodedText);
        System.out.println(decodeText(encodedText, 3));

        userInterface();

    }
}
