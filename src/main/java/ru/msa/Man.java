package ru.msa;

import java.io.IOException;
import java.util.List;

public class Man implements Person {
    private final static String MANS_NAMES_PATH = "src/main/resources/MansNames.txt";
    private final static String MANS_SURNAMES_PATH = "src/main/resources/MansSurnames.txt";
    private final static String MANS_PATRONYMICS_PATH = "src/main/resources/MansPatronymics.txt";
    private static Man instance;
    private static List<String> names;
    private static List<String> surnames;
    private static List<String> patronymics;

    private Man() {
    }

    public static Man getInstance() throws IOException {
        if (instance == null) {
            instance.init();
            instance = new Man();
        }
        return instance;
    }

    private static void init() throws IOException {
        names = Person.getList(MANS_NAMES_PATH);
        surnames = Person.getList(MANS_SURNAMES_PATH);
        patronymics = Person.getList(MANS_PATRONYMICS_PATH);
        System.out.println("Man init");
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
