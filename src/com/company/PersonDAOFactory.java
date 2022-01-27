package com.company;

public class PersonDAOFactory {
    public static PersonDAO getPersonDAO() {

        return new PersonSimpleDAO();
    }
}
