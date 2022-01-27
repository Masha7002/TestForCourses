package com.company;
import java.util.List;
public class PersonManager {
        private PersonDAO dao;

        public PersonManager() {
            dao = PersonDAOFactory.getPersonDAO();
        }
        // Добавление контакта - возвращает ID добавленного контакта
        public Long addPerson(Person person) {
            return dao.addPerson(person);
        }
        // Редактирование контакта
        public void updatePerson(Person person) {
            dao.updatePerson(person);
        }

        // Удаление контакта по его ID
        public void deletePerson(Long ID) {
            dao.deletePerson(ID);
        }

        // Получение одного контакта
        public Person getPerson(Long ID) {
            return dao.getPerson(ID);
        }

        // Получение списка контактов
        public List<Person> findPeople() {
            return dao.findPeople();
        }
    }
