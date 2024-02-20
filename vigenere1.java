package org.example;

import java.util.Scanner;

public class vigenere1 {
    private static final String abecele = "ABCDEFGHIJKLMNOPQRSTUVWXYZĄČĘĖĮŠŲŪŽ0123456789";

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pasirinkite:\n1. Užšifravimas\n2. Dešifravimas");
        int choice = scanner.nextInt();

        System.out.println("Pasirinkite šifravimo/dešifravimo būdą:\n1. Pagal abėcėlę\n2. Pagal ASCII lentelę");
        int metodas = scanner.nextInt();
        System.out.println("Įveskite tekstą:");
        String tekstas = scanner.next().toUpperCase();
        System.out.println("Įveskite raktą:");
        String raktas = scanner.next().toUpperCase();

        String result = "";
        if (choice == 1) {
            result = (metodas == 1) ? uzsifruotiabc(tekstas, raktas) : uzsifruotiAscii(tekstas, raktas);
        } else if (choice == 2) {
            result = (metodas == 1) ? desifruotiabc(tekstas, raktas) : desifruotiAscii(tekstas, raktas);
        } else {
            System.out.println("Negalimas pasirinkimas!");
            System.exit(0);
        }

        System.out.println((choice == 1 ? "Užšifruotas" : "Dešifruotas") + " tekstas: " + result);
        scanner.close();
    }

    private static String uzsifruotiabc(String tekstas, String raktas) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            int charIndex = abecele.indexOf(tekstas.charAt(i));
            int keyIndex = abecele.indexOf(raktas.charAt(i % raktas.length()));
            int newIndex = (charIndex + keyIndex) % abecele.length();
            encrypted.append(abecele.charAt(newIndex));
        }
        return encrypted.toString();
    }

    private static String desifruotiabc(String tekstas, String raktas) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            int charIndex = abecele.indexOf(tekstas.charAt(i));
            int keyIndex = abecele.indexOf(raktas.charAt(i % raktas.length()));
            int newIndex = (charIndex - keyIndex + abecele.length()) % abecele.length();
            decrypted.append(abecele.charAt(newIndex));
        }
        return decrypted.toString();
    }

    private static String uzsifruotiAscii(String tekstas, String raktas) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            int newChar = (tekstas.charAt(i) + raktas.charAt(i % raktas.length())) % 256;
            encrypted.append((char) newChar);
        }
        return encrypted.toString();
    }

    private static String desifruotiAscii(String tekstas, String raktas) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < tekstas.length(); i++) {
            int newChar = (tekstas.charAt(i) - raktas.charAt(i % raktas.length()) + 256) % 256;
            decrypted.append((char) newChar);
        }
        return decrypted.toString();
    }
}