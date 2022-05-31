package com.sprint05.common.patterns.builder;

public class Exemplo {

    Pizza pizza = new Pizza.Builder(10)
            .queijo()
            .tomate()
            .bacon()
            .build();
}
