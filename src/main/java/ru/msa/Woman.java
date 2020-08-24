package ru.msa;

import java.io.IOException;
import java.util.List;

public class Woman implements Person {
    private final static String WOMANS_NAMES_PATH = "src/main/resources/WomansNames.txt";
    private final static String WOMANS_SURNAMES_PATH = "src/main/resources/WomansSurnames.txt";
    private final static String WOMANS_PATRONYMICS_PATH = "src/main/resources/WomansPatronymics.txt";

    private static Woman instance;
    private static List<String> names;
    private static List<String> surnames;
    private static List<String> patronymics;

    private Woman() {
    }

    public static Woman getInstance() throws IOException {
        if (instance == null) {
            instance.init();
            instance = new Woman();
        }
        return instance;
    }

    private static void init() throws IOException {
        names = Person.getList(WOMANS_NAMES_PATH);
        surnames = Person.getList(WOMANS_SURNAMES_PATH);
        patronymics = Person.getList(WOMANS_PATRONYMICS_PATH);
        System.out.println("Woman init");
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
