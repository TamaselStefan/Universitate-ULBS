package lab9;

import java.util.*;
import java.util.stream.*;

public class p2 {
    public static void main(String[] args) {
        String text = "Acesta este un program scris in java pentru expresii lambda";

        List<String> cuvinte = Arrays.asList(text.split(" "));
        System.out.println("Lista cuvintelor: " + cuvinte);


        List<String> cuvinteLungi = cuvinte.stream()
                .filter(c -> c.length() >= 5)
                .collect(Collectors.toList());
        System.out.println("\na) Cuvinte cu lungime >= 5 (" + cuvinteLungi.size() + "): " + cuvinteLungi);


        List<String> sortata = cuvinteLungi.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("\nb) Lista sortata: " + sortata);


        Optional<String> cuP = cuvinte.stream()
                .filter(c -> c.startsWith("p"))
                .findFirst();
        System.out.println("\nc) Cuvant cu 'p': " + cuP.orElse("Nu exista"));
    }
}