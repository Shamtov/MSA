package ru.msa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public interface Person {

    String getName();

    String getSurname();

    String getPatronymics();

    static List<String> getList(String path) throws IOException {
        return Files.lines(Paths.get(path)).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }

    static long getRandomNum(long maxNum) {
        return ThreadLocalRandom.current().nextLong(0L, maxNum);
    }

    default String getFullName() {
        return String.format("%s %s %s", getSurname(), getName(), getPatronymics());
    }
}
