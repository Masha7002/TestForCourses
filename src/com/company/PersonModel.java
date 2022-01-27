package com.company;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PersonModel extends AbstractTableModel {
    // Список загловков для колонок в таблице
    private static final String[] headers = {"ID", "Имя", "Фамилия", "ДР", "Телефон"};

    // Здесь мы храним список контактов, которые будем отображать в таблице
    private final List<Person> people;

    public PersonModel(List<Person> people) {
        this.people = people;
    }

    @Override
    // Получить количество строк в таблице - у нас это размер коллекции
    public int getRowCount() {
        return people.size();
    }

    @Override
    // Получить количество столбцов - их у нас стольк же, сколько полей
    // у класса Person - всего пять
    public int getColumnCount() {
        return 5;
    }

    @Override
    // Вернуть загловок колонки - мы его берем из массива headers
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    // Получить объект для отображения в конкретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    public Object getValueAt(int row, int col) {
        Person person = people.get(row);
        // В зависимости от номера колонки возвращаем то или иное поле контакта
        switch (col) {
            case 0:
                return person.getID().toString();
            case 1:
                return person.getFirstName();
            case 2:
                return person.getLastName();
            case 3:
                return person.getBirthDate();
            default:
                return person.getPhone();
        }
    }
}
