package teamf.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 5/02/13
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */

public class User implements Serializable {

    private int userID;

    private String username;

    private String password;

    private String email;

    private String telephone;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String street;

    private String number;

    private String zipcode;

    private String city;




    private Collection<Deelname> deelnames;

    private Collection<Trip> trips;

    public User() {

    }

    public User(String city, String zipcode, String number, String street, Date dateOfBirth, String lastName, String firstName, String telephone, String email, String password, String username)  {
        this.password =  password;
        this.city = city;
        this.zipcode = zipcode;
        this.number = number;
        this.street = street;
        this.dateOfBirth = dateOfBirth;
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephone = telephone;
        this.email = email;
        this.username = username;


    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Collection<Deelname> getDeelnames() {
        return deelnames;
    }

    public void setDeelnames(Collection<Deelname> deelnames) {
        this.deelnames = deelnames;
    }

    public Collection<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Collection<Trip> trips) {
        this.trips = trips;
    }

    public void setShowPosition(boolean showPosition) {
        //this.showPosition = showPosition;
    }

    public boolean isShowPosition() {

        return true;
    }

    public void setNotificationEmail(boolean notificationEmail) {
        //this.notificationEmail = notificationEmail;
    }

    public boolean isNotificationEmail() {

        return true;
    }



  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userID != user.userID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userID;
    } */
}
