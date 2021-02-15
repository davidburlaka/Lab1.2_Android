package com.company;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.time.LocalDateTime;
public class Main {

    public static int randomValue( int maxValue) {
        return ThreadLocalRandom.current().nextInt(0, maxValue + 1);
    }

    public static void splitString(ArrayList<String> names, ArrayList<String> groups, String input) {
        String[] splitNames = input.split("; ");
        for (String oneName: splitNames ) {
            names.add(oneName.split(" - ")[0]);
            groups.add(oneName.split(" - ")[1]);
        }

    }

    public static void main(String[] args) {
       // Частина 1

        // Дано рядок у форматі "Student1 - Group1; Student2 - Group2; ..."
        String studentsStr = "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82";

        // Завдання 1
        // Заповніть словник, де:
        // - ключ – назва групи
        // - значення – відсортований масив студентів, які відносяться до відповідної групи
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> groups = new ArrayList<>();

        // Ваш код починається тут
        splitString(names, groups, studentsStr);
        HashMap<String, String> studentsGroups = new HashMap<String, String>();
        for (int i =0; i < names.size(); i++) {
            studentsGroups.put(names.get(i), groups.get(i));
        }

        // Ваш код закінчується тут

        System.out.println("Завдання 1");
        System.out.println(studentsGroups);
        System.out.println();

        // Дано масив з максимально можливими оцінками

        List<Integer> points = Arrays.asList(12, 12, 12, 12, 12, 12, 12, 16);

        // Завдання 2
        // Заповніть словник, де:
        // - ключ – назва групи
        // - значення – словник, де:
        //   - ключ – студент, який відносяться до відповідної групи
        //   - значення – масив з оцінками студента (заповніть масив випадковими значеннями, використовуючи функцію `randomValue(maxValue: Int) -> Int`)



        HashMap<String, HashMap<String, ArrayList<Integer>>> studentPoints = new HashMap<String, HashMap<String, ArrayList<Integer>>>();

        // Ваш код починається тут


        for (Map.Entry<String, String> groupEntry: studentsGroups.entrySet()) {
            if (!studentPoints.containsKey(groupEntry.getValue())) {
                HashMap<String, ArrayList<Integer>> valueMap = new HashMap<String, ArrayList<Integer>>();
                for (Map.Entry<String, String> entry: studentsGroups.entrySet()) {
                    if (entry.getValue().equals(groupEntry.getValue())){
                        ArrayList<Integer> tempPoints = new ArrayList<>();
                        for (int point: points) {
                            tempPoints.add(randomValue(point));
                        }
                        valueMap.put(entry.getKey(), tempPoints);
                    }
                }
                studentPoints.put(groupEntry.getValue(), valueMap);
            }
        }

        // Ваш код закінчується тут

        System.out.println("Завдання 2");
        System.out.println(studentPoints);
        System.out.println();

        // Завдання 3
        // Заповніть словник, де:
        // - ключ – назва групи
        // - значення – словник, де:
        //   - ключ – студент, який відносяться до відповідної групи
        //   - значення – сума оцінок студента


        HashMap<String, HashMap<String, Integer>> sumPoints = new HashMap<String, HashMap<String, Integer>>();
        // Ваш код починається тут

        for (Map.Entry<String, HashMap<String, ArrayList<Integer>>> group: studentPoints.entrySet()) {
            HashMap<String, Integer> students = new HashMap<String, Integer>();
            for (Map.Entry<String, ArrayList<Integer>> student: group.getValue().entrySet()) {
                Integer sum = 0;
                for (Integer point: student.getValue()) {
                    sum += point;
                }
                students.put(student.getKey(), sum);
            }
            sumPoints.put(group.getKey(), students);
        }

        // Ваш код закінчується тут

        System.out.println("Завдання 3");
        System.out.println(sumPoints);
        System.out.println();

        // Завдання 4
        // Заповніть словник, де:
        // - ключ – назва групи
        // - значення – середня оцінка всіх студентів групи


        HashMap<String, Integer> groupAvg = new HashMap<String, Integer>();
        // Ваш код починається тут

        for (Map.Entry<String, HashMap<String, Integer>> group: sumPoints.entrySet()) {
            Integer sum = 0;
            Integer amount = 0;
            for (Map.Entry<String, Integer> student: group.getValue().entrySet()) {
                sum += student.getValue();
                amount++;
            }
            groupAvg.put(group.getKey(), sum/amount);
        }

        // Ваш код закінчується тут

        System.out.println("Завдання 4");
        System.out.println(groupAvg);
        System.out.println();

        // Завдання 5
        // Заповніть словник, де:
        // - ключ – назва групи
        // - значення – масив студентів, які мають >= 60 балів

        HashMap<String, ArrayList<String>> passedPerGroup = new HashMap<String, ArrayList<String>>();
        // Ваш код починається тут

        for (Map.Entry<String, HashMap<String, Integer>> group: sumPoints.entrySet()) {
            ArrayList<String> passed = new ArrayList<>();
            for (Map.Entry<String, Integer> student: group.getValue().entrySet()) {
                if (student.getValue() >= 60)
                    passed.add(student.getKey());
            }
            passedPerGroup.put(group.getKey(), passed);
        }

        // Ваш код закінчується тут

        System.out.println("Завдання 5");
        System.out.println(passedPerGroup);


        // Приклад виведення. Ваш результат буде відрізнятися від прикладу через використання функції random для заповнення масиву оцінок та через інші вхідні дані.
//
//Завдання 1
//["ІВ-73": ["Гончар Юрій", "Давиденко Костянтин", "Капінус Артем", "Науменко Павло", "Чередніченко Владислав"], "ІВ-72": ["Бортнік Василь", "Киба Олег", "Овчарова Юстіна", "Тимко Андрій"], "ІВ-71": ["Андрющенко Данило", "Гуменюк Олександр", "Корнійчук Ольга", "Музика Олександр", "Трудов Антон", "Феофанов Іван"]]
//
//Завдання 2
//["ІВ-73": ["Давиденко Костянтин": [5, 8, 9, 12, 11, 12, 0, 0, 14], "Капінус Артем": [5, 8, 12, 12, 0, 12, 12, 12, 11], "Науменко Павло": [4, 8, 0, 12, 12, 11, 12, 12, 15], "Чередніченко Владислав": [5, 8, 12, 12, 11, 12, 12, 12, 15], "Гончар Юрій": [5, 6, 0, 12, 0, 11, 12, 11, 14]], "ІВ-71": ["Корнійчук Ольга": [0, 0, 12, 9, 11, 11, 9, 12, 15], "Музика Олександр": [5, 8, 12, 0, 11, 12, 0, 9, 15], "Гуменюк Олександр": [5, 8, 12, 9, 12, 12, 11, 12, 15], "Трудов Антон": [5, 0, 0, 11, 11, 0, 12, 12, 15], "Андрющенко Данило": [5, 6, 0, 12, 12, 12, 0, 9, 15], "Феофанов Іван": [5, 8, 12, 9, 12, 9, 11, 12, 14]], "ІВ-72": ["Киба Олег": [5, 8, 12, 12, 11, 12, 0, 0, 11], "Овчарова Юстіна": [5, 8, 12, 0, 11, 12, 12, 12, 15], "Бортнік Василь": [4, 8, 12, 12, 0, 12, 9, 12, 15], "Тимко Андрій": [0, 8, 11, 0, 12, 12, 9, 12, 15]]]
//
//Завдання 3
//["ІВ-72": ["Бортнік Василь": 84, "Тимко Андрій": 79, "Овчарова Юстіна": 87, "Киба Олег": 71], "ІВ-73": ["Капінус Артем": 84, "Науменко Павло": 86, "Чередніченко Владислав": 99, "Гончар Юрій": 71, "Давиденко Костянтин": 71], "ІВ-71": ["Корнійчук Ольга": 79, "Трудов Антон": 66, "Андрющенко Данило": 71, "Гуменюк Олександр": 96, "Феофанов Іван": 92, "Музика Олександр": 72]]
//
//Завдання 4
//["ІВ-71": 79.333336, "ІВ-72": 80.25, "ІВ-73": 82.2]
//
//Завдання 5
//["ІВ-72": ["Бортнік Василь", "Киба Олег", "Овчарова Юстіна", "Тимко Андрій"], "ІВ-73": ["Давиденко Костянтин", "Капінус Артем", "Чередніченко Владислав", "Гончар Юрій", "Науменко Павло"], "ІВ-71": ["Музика Олександр", "Трудов Антон", "Гуменюк Олександр", "Феофанов Іван", "Андрющенко Данило", "Корнійчук Ольга"]]
    }
}
