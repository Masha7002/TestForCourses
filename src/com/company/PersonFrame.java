package com.company;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class PersonFrame extends JFrame implements ActionListener {
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private final PersonManager personManager = new PersonManager();
    private final JTable personTable = new JTable();

    // В конструкторе мы создаем нужные элементы
    public PersonFrame() {
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        personTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Используем layout manager
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);

        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // усанавливаем у него layout
        btnPanel.setLayout(gridbag);
        // Создаем кнопки
        btnPanel.add(createButton(gridbag, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gridbag, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gridbag, gbc, "Редактировать", EDIT));
        btnPanel.add(createButton(gridbag, gbc, "Удалить", DELETE));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(personTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загружаем контакты
        loadPerson();
    }

    // Метод создает кнопку с заданными характеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будет проверяться в обработчике и мы будем знать, какую
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
            // Перегрузка данных
            case LOAD:
                loadPerson();
                break;
            // Добавление записи
            case ADD:
                addPerson();
                break;
            // Исправление записи
            case EDIT:
                editPerson();
                break;
            // Удаление записи
            case DELETE:
                deletePerson();
                break;
        }
    }

    // Загрузить список контактов
    private void loadPerson() {
        // Обращаемся к классу для загрузки списка контактов
        List<Person> people = personManager.findPeople();
        // Создаем модель, которой передаем полученный список
        PersonModel pm = new PersonModel(people);
        // Передаем нашу модель таблице - и она может ее отображать
        personTable.setModel(pm);
    }

    // Добавление контакта
    private void addPerson() {
        // Создаем диалог для ввода данных
        PersonDialog ecd = new PersonDialog();
        // Обрабатываем закрытие диалога
        savePerson(ecd);
    }

    // Редактирование контакта
    private void editPerson() {
        // Получаем выделеннуб строку
        int sr = personTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(personTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные контакта по его ID
            Person cnt = personManager.getPerson(id);
            // Создаем диалог для ввода данных и передаем туда контакт
            PersonDialog ecd = new PersonDialog(cnt);
            // Обрабатываем закрытие диалога
            savePerson(ecd);
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Выделите нужную вам строку для редактирования");
        }
    }

    // Удаление контакта
    private void deletePerson() {
        // Получаем выделеннуб строку
        int sr = personTable.getSelectedRow();
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(personTable.getModel().getValueAt(sr, 0).toString());
            // Удаляем контакт
            personManager.deletePerson(id);
            // перегружаем список контактов
            loadPerson();
        } else {
            JOptionPane.showMessageDialog(this, "Выделите нужную вам строку для удаления");
        }
    }

    // Общий метод для добавления и изменения контакта
    private void savePerson(PersonDialog ecd) {
        // Если мы нажали кнопку SAVE
        if (ecd.isSave()) {
            // Получаем контакт из диалогового окна
            Person cnt = ecd.getPerson();
            if (cnt.getID() != null) {
                // Если ID у контакта есть, то мы его обновляем
                personManager.updatePerson(cnt);
            } else {
                // Если у контакта нет ID - значит он новый и мы его добавляем
                personManager.addPerson(cnt);
            }
            loadPerson();
        }
    }
}
