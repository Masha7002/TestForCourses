package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public final class PersonSimpleDAO implements PersonDAO {
    private final List<Person> people = new ArrayList<>();
/////////////////////////////////// вставить по умолчанию вывод именниников
//LocalDate today = LocalDate.now(); // Сегодняшняя дата
   // int day = today.getDayOfMonth();
   // int month = today.getMonthValue();
    //    if (month == )
            ////////////////////////
    @Override
    public Long addPerson(Person person) {
        Long id = generateID();
        person.setID(id);
        people.add(person);
        return id;
    }

    @Override
    public void updatePerson(Person person) {
        Person oldPerson = getPerson(person.getID());
        if(oldPerson != null) {
            oldPerson.setFirstName(person.getFirstName());
            oldPerson.setLastName(person.getLastName());
            oldPerson.setBirthDate(person.getBirthDate());
            oldPerson.setPhone(person.getPhone());
        }
    }

    @Override
    public void deletePerson(Long ID) {
        for(Iterator<Person> it = people.iterator(); it.hasNext();) {
            Person cnt = it.next();
            if(cnt.getID().equals(ID)) {
                it.remove();
            }
        }
    }

    @Override
    public Person getPerson(Long ID) {
        for(Person person : people) {
            if(person.getID().equals(ID)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public List<Person> findPeople() {
        return people;
    }

    private Long generateID() {
        Long ID = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(getPerson(ID) != null) {
            ID = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return ID;
    }
}
