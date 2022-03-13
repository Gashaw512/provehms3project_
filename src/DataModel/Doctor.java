package DataModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Doctor  {
    private SimpleStringProperty qualification=new SimpleStringProperty();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty gender = new SimpleStringProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleObjectProperty<Date> age = new SimpleObjectProperty<>();



    public Doctor(int id,String firstName, String lastName, String phone,  String gender, String address, Date age,
                  String qualification) {
        /*super(firstName, lastName, phone, id, gender, address, age);*/
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phone.set(phone);
        this.id .set(id);
        this.gender.set(gender);
        this.address.set(address);
        this.age.set(age);
        this.qualification.set(qualification);
    }

    public Doctor() {

    }
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public Date getAge() {
        return age.get();
    }

    public SimpleObjectProperty<Date> ageProperty() {
        return age;
    }

    public void setAge(Date age) {
        this.age.set(age);
    }


    public String getQualification() {
        return qualification.get();
    }

    public StringProperty qualificationProperty() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification.set(qualification);
    }
}
