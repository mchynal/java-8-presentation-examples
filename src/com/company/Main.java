package com.company;

import org.jooq.lambda.Seq;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    private static String napis;

    public static void main(String[] args) {

        //TODO Runnable
        Runnable printAbc = () -> System.out.println("abc");
        printAbc.run();

        //TODO Consumer
        Consumer<String> print = str -> System.out.println(str);
        print.accept("Cde");

        //TODO Predicate
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(2));
        System.out.println(isEven.test(3));

        //TODO Function
        Function<Integer, Integer> triple = x -> x * 3;
        System.out.println(triple.apply(5));

        //TODO BiFunction
        BiFunction<Integer, Integer, String> concat = (a, b) -> a.toString() + b.toString();
        System.out.println(concat.apply(4, 6));

        //TODO safeExecute
        safeExecute(() -> {
            System.out.println("czuje sie bezpiecznie :)");
            System.out.println(napis.length());
        });

        //TODO Seq 1, 2, 3, 4, 5, 6, 7, 8, 9
        List<Integer> list = Seq.of(1, 2, 3, 4, 5, 6, 7, 8, 9).toList();

        //TODO even and gt 3
        List<Integer> result = list.stream().filter(isEven).filter(x -> x > 3).collect(Collectors.toList());

        System.out.println(result);

        //TODO triple all
        List<Integer> list1 = result.stream().map(triple).collect(Collectors.toList());
        System.out.println(list1);


        //TODO list + 10
        System.out.println(
                list.stream().reduce(10, (accu, i) -> accu + i)
        );


        //TODO lazy + paralel
        Function<Integer, Integer> factor = v -> {
            System.out.println("...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return v * 100 + 5 + v;
        };

        System.out.println(
                //list.parallelStream().map(factor).collect(Collectors.toList())
        );

        //TODO group odd/even
        System.out.println(
                list.stream().collect(Collectors.groupingBy(p -> p % 2 == 0, Collectors.toList()))
        );

        //TODO jOOl group
        System.out.println(
                Seq.seq(list).groupBy(p -> p % 2 == 0 ? "even" : "odd")
        );

        System.out.println(
                Seq.seq(list).groupBy(p -> p % 2 == 0 ? "even" : "odd", Collectors.summarizingInt(Integer::intValue))
        );

        //TODO Optional
        if (napis != null) {
            System.out.println(
                    napis.length()
            );
        } else {
            System.out.println(0);
        }

        System.out.println(
                Optional.ofNullable(napis).orElse("").length()
        );

        //TODO first gt 10 | sout | ifPresent
        list.stream().filter(x -> x > 9).findFirst().ifPresent(System.out::println);


        //TODO java8 vs jOOλ vs javaslang
        //https://gist.github.com/mchynal/6dc2e571d6fb37862d9001355b0e29a8
    }

    //TODO safeExecute
    private static void safeExecute(Runnable block) {
        try {
            block.run();
        } catch (Exception e) {
            System.out.println("Obslugujemy wyjatek");
        }
    }


}
