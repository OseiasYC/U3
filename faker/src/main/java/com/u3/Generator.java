package com.u3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.DeflaterOutputStream;

import com.github.javafaker.Faker;

public class Generator {

    public static List<String> generateRMs(Integer size) {
        List<String> randomRms = new ArrayList<>();

        for (Integer i = 1; i < size + 1; i++) {
            String formattedNumber = String.format("2000%05d", i);
            randomRms.add(formattedNumber);
        }

        return randomRms;
    }

    public static Map<String, Integer[]> generateCourses(Integer courseSize, Integer subjectsSizeByCourse) {
        Map<String, Integer[]> courses = new HashMap<>();
        Random random = new Random();
        int count = 0;
        int randomNumber;

        for (char c1 = 'A'; c1 <= 'Z' && count < courseSize; c1++) {
            for (char c2 = 'A'; c2 <= 'Z' && count < courseSize; c2++) {
                for (char c3 = 'A'; c3 <= 'Z' && count < courseSize; c3++) {
                    String key = "" + c1 + c2 + c3;
                    Integer[] value = new Integer[subjectsSizeByCourse];

                    for (int i = 0; i < subjectsSizeByCourse; i++) {
                        // do {
                        //     randomNumber = random.nextInt(100);
                        // } while (randomNumber == 0);
                        value[i] = i;
                    }

                    courses.put(key, value);
                    count++;
                }
            }
        }

        return courses;

    }
    
    public static byte[] generateRandomFile() throws IOException {
        Faker faker = new Faker();
        List<String> paragraphs = faker.lorem().paragraphs(5);
        String fileContent = String.join("\n", paragraphs);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream);
        deflaterOutputStream.write(fileContent.getBytes());
        deflaterOutputStream.close();
        return outputStream.toByteArray();
    }

    public static <K, V> void printMaps(Map<K, V[]> input) {
        for (Map.Entry<K, V[]> entry : input.entrySet()) {
            System.out.print(entry.getKey() + ": ");

            V[] values = entry.getValue();
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i]);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
