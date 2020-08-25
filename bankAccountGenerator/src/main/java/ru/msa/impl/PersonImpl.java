package ru.msa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.msa.models.Person;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class PersonImpl {

    public PersonImpl() {
    }
    @Autowired
    private Man man;
    @Autowired
    private Woman woman;

    public String getRandomName(Person p) {
        return p.getName();
    }

    public String getRandomSurname(Person p) {
        return p.getSurname();
    }

    public String getPatronymics(Person p) {
        return p.getPatronymics();
    }

    public String getRandomFullName(Person p) {
        return p.getFullName();
    }

    public Person getRandomGender() {
        int random = ThreadLocalRandom.current().nextInt(0, 2);
        switch (random) {
            case 0:
                return man;
            case 1:
                return woman;
            default:
                throw new IllegalStateException("Unexpected value: " + random);
        }
    }
}
