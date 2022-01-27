package com.company;

public class Person {
    public Long ID;
    public String FirstName;
    public String LastName;
    public String BirthDate;
    public String Phone;

    public Person () {
    }
    public Person (String FirstName, String LastName, String BirthDate, String Phone){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthDate = BirthDate;
        this.Phone = Phone;
    }
    public Person (Long ID, String FirstName, String LastName, String BirthDate, String Phone) {
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthDate = BirthDate;
        this.Phone = Phone;
    }
    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public String getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(String email) {
        this.BirthDate = BirthDate;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    @Override
    public String toString() {
        return "Person{" + "ID=" + ID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", BirthDate=" + BirthDate + ", Phone=" + Phone + '}';
    }
}