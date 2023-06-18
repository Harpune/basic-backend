package com.example.sandbox.fixture;

import com.example.sandbox.model.Persona;
import com.example.sandbox.util.DateUtil;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class PersonaFixture {

    private static final Faker faker = new Faker(new Locale("de-DE"));

    public static Persona create() {
        Persona persona = new Persona();

        persona.setId(RandomUtils.nextLong());
        persona.setEmail(faker.internet().emailAddress());
        persona.setFirstname(faker.name().firstName());
        persona.setLastname(faker.name().lastName());
        persona.setPassword(faker.gameOfThrones().city());
        persona.setDayOfBirth(DateUtil.toLocalDate(faker.date().birthday()));

        return persona;
    }

    public static List<Persona> createList() {
        return createList(5);
    }

    public static List<Persona> createList(int size) {
        return IntStream.range(0, size).mapToObj(i -> create()).toList();
    }
}
