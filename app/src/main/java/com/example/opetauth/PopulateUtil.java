package com.example.opetauth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class PopulateUtil {

    static List<Person> loadPersons() {
        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.name = "TomTom";
        person.child = 1;
        person.salary = 2400.75;
        person.active = false;
        person.pets = Arrays.asList("Jojo", "Rex");
        person.birthday = new GregorianCalendar(1990, Calendar.AUGUST, 17)
                .getTime();
        persons.add(person);

        person = new Person();
        person.name = "Cargun";
        person.child = 2;
        person.salary = 2500.75;
        person.active = false;
        person.pets = Arrays.asList("Chica");
        person.birthday = new GregorianCalendar(1980, Calendar.AUGUST, 17)
                .getTime();
        persons.add(person);

        person = new Person();
        person.name = "Chica";
        person.child = 2;
        person.salary = 3500.75;
        person.active = true;
        person.pets = Arrays.asList("Pop");
        person.birthday = new GregorianCalendar(1985, Calendar.AUGUST, 17)
                .getTime();
        persons.add(person);

        return persons;
    };
}
