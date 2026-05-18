package lab9;

import java.util.*;
import java.util.stream.*;

public class p1 {
    public static void main(String[] args) {
        List<Integer> numere = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numere.add((Integer)(5 + (int)(Math.random() * 21)));
        }
        System.out.println("Lista initiala: " + numere);

        int suma = numere.stream().mapToInt(Integer::intValue).sum();
        System.out.println("\na) Suma: " + suma);

        Optional<Integer> max = numere.stream().max(Comparator.naturalOrder());
        Optional<Integer> min = numere.stream().min(Comparator.naturalOrder());
        System.out.println("\nb) Max: " + max.get());
        System.out.println("   Min: " + min.get());


        List<Integer> filtrata = numere.stream()
                .filter(n -> n >= 10 && n <= 20)
                .collect(Collectors.toList());
        System.out.println("\nc) Lista filtrata [10..20]: " + filtrata);


        List<Double> listaDouble = numere.stream()
                .map(n -> Double.valueOf(n))
                .collect(Collectors.toList());
        System.out.println("\nd) Lista ca Double: " + listaDouble);


        boolean contine12 = numere.stream()
                .anyMatch(n -> n == 12);
        System.out.println("\ne) Contine valoarea 12: " + contine12);
    }
}