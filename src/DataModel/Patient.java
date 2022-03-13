package DataModel;

import javafx.beans.property.*;

import java.util.Date;

public class Patient extends Person {
    private SimpleIntegerProperty room = new SimpleIntegerProperty();
    private SimpleObjectProperty<java.sql.Date> admissionDate = new SimpleObjectProperty<>();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty gender = new SimpleStringProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleObjectProperty<java.sql.Date> age = new SimpleObjectProperty<>();

    public Patient(String firstName, String lastName, String phone, int id, String gender, String address,
                   java.sql.Date age, int room, java.sql.Date date) {
        //super(firstName, lastName, phone, id, gender, address, age);
        this.room.set(room);
        this.admissionDate.set(date);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phone.set(phone);
        this.id .set(id);
        this.gender.set(gender);
        this.address.set(address);
        this.age.set(age);

    }

    public Patient() {

    }

    public int getRoom() {
        return room.get();
    }

    public SimpleIntegerProperty roomProperty() {
        return room;
    }

    public void setRoom(int room) {
        this.room.set(room);
    }

    public Date getAdmissionDate() {
        return admissionDate.get();
    }

    public SimpleObjectProperty<java.sql.Date> admissionDateProperty() {
        return admissionDate;
    }

    public void setAdmissionDate(java.sql.Date admissionDate) {
        this.admissionDate.set(admissionDate);
    }

    @Override
    public String getFirstName() {
        return firstName.get();
    }

    @Override
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    @Override
    public String getLastName() {
        return lastName.get();
    }

    @Override
    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @Override
    public String getPhone() {
        return phone.get();
    }

    @Override
    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public int getId() {
        return id.get();
    }

    @Override
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String getGender() {
        return gender.get();
    }

    @Override
    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    @Override
    public String getAddress() {
        return address.get();
    }

    @Override
    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    @Override
    public java.sql.Date getAge() {
        return age.get();
    }

    @Override
    public SimpleObjectProperty<java.sql.Date> ageProperty() {
        return age;
    }

    public void setAge(java.sql.Date age) {
        this.age.set(age);
    }
}
