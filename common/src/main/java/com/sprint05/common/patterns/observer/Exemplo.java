package com.sprint05.common.patterns.observer;

import reactor.core.publisher.Flux;

public class Exemplo {
    public static Flux<String> getBrands() {

    final Flux<String> brands = Flux.just("Under Armour", "Asics", "Nike", "Adidas", "Mizuno");

    brands.sort()
        .subscribe(System.out::println);//all sorted items

        brands.skip(1)
         .groupBy(b ->b.charAt(0))
            .flatMap(Flux::collectSortedList)
            .subscribe(System.out::println); //items grouped by first char

        return brands;

    }

    public static void main(String[] args) {

        System.out.println(Exemplo.getBrands());
    }
}
