package ru.msa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public interface Person {

    String getName();

    String getSurname();

    String getPatronymics();

    static List<String> getList(String path) {
        return new BufferedReader(new InputStreamReader(Person.class.getClassLoader().getResourceAsStream(path), StandardCharsets.UTF_8))
                .lines().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    static long getRandomNum(long maxNum) {
        return ThreadLocalRandom.current().nextLong(0L, maxNum);
    }

    default String getFullName() {
        return String.format("%s %s %s", getSurname(), getName(), getPatronymics());
    }
}
