package ru.msa.impl;

import org.springframework.stereotype.Component;
import ru.msa.entity.Person;

import java.util.List;

@Component
public class Man implements Person {
    private final static String MANS_NAMES_PATH = "MansNames.txt";
    private final static String MANS_SURNAMES_PATH = "MansSurnames.txt";
    private final static String MANS_PATRONYMICS_PATH = "MansPatronymics.txt";
    private static List<String> names;
    private static List<String> surnames;
    private static List<String> patronymics;

    public Man() {
    }

    {
        names = Person.getList(MANS_NAMES_PATH);
        surnames = Person.getList(MANS_SURNAMES_PATH);
        patronymics = Person.getList(MANS_PATRONYMICS_PATH);
    }

    @Override
    public String getName() {
        return names.get(Math.toIntExact(Person.getRandomNum(names.size())));
    }

    @Override
    public String getSurname() {
        return surnames.get(Math.toIntExact(Person.getRandomNum(surnames.size())));
    }

    @Override
    public String getPatronymics() {
        return patronymics.get(Math.toIntExact(Person.getRandomNum(patronymics.size())));
    }
}
