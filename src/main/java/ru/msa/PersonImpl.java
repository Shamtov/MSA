package ru.msa;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class PersonImpl {

    private PersonImpl() {
    }

    static String getRandomName(Person p) {
        return p.getName();
    }

    static String getRandomSurname(Person p) {
        return p.getSurname();
    }

    static String getPatronymics(Person p) {
        return p.getPatronymics();
    }

    static String getRandomFullName(Person p) {
        return p.getFullName();
    }

    public static Person getRandomGender() throws IOException {
        int random = ThreadLocalRandom.current().nextInt(0, 2);
        switch (random) {
            case 0:
                return Man.getInstance();
            case 1:
                return Woman.getInstance();
            default:
                throw new IllegalStateException("Unexpected value: " + random);
        }
    }
}
