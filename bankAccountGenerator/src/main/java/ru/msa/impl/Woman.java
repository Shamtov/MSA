package ru.msa.impl;

import org.springframework.stereotype.Component;
import ru.msa.models.Person;

import java.util.List;

@Component
public class Woman implements Person {
    private final static String WOMANS_NAMES_PATH = "WomansNames.txt";
    private final static String WOMANS_SURNAMES_PATH = "WomansSurnames.txt";
    private final static String WOMANS_PATRONYMICS_PATH = "WomansPatronymics.txt";

    private static List<String> names;
    private static List<String> surnames;
    private static List<String> patronymics;

    private Woman() {
    }

    {
        names = Person.getList(WOMANS_NAMES_PATH);
        surnames = Person.getList(WOMANS_SURNAMES_PATH);
        patronymics = Person.getList(WOMANS_PATRONYMICS_PATH);
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
