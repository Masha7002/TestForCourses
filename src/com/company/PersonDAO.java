package com.company;
import java.util.List;
public interface PersonDAO {
    // Добавление контакта - возвращает ID добавленного контакта
    public Long addPerson(Person person);
    // Редактирование контакта
    public void updatePerson(Person person);
    // Удаление контакта по его ID
    public void deletePerson(Long ID);
    // Получение контакта
    public Person getPerson(Long ID);
    // Получение списка контактов
    public List<Person> findPeople();
}
