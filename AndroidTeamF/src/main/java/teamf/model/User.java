package teamf.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jeroen
 * Date: 5/02/13
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */


@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements  Serializable{
   //@JsonProperty
    private int userID;
    //@JsonProperty
    private String username;
    //@JsonProperty
    private String password;
    //@JsonProperty
    private String email;
    //@JsonProperty
    private String telephone;
   // @JsonProperty
    private String firstName;
   // @JsonProperty
    private String lastName;
   // @JsonProperty
    private Date dateOfBirth;
    //@JsonProperty
    private String street;
   // @JsonProperty
    private String number;
    //@JsonProperty
    private String zipcode;
   // @JsonProperty
    private String city;
   // @JsonProperty
    private boolean showPosition;
   // @JsonProperty
    private boolean notificationEmail;
   // @JsonProperty

    private Blob profielFoto;
  //  @JsonProperty
    private Collection<Deelname> deelnames;
   // @JsonProperty

    private Collection<Trip> trips;
   // @JsonProperty
    private Collection<Chat> chats;




    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> g =new ArrayList<GrantedAuthority>();
        g.add(new SimpleGrantedAuthority("ROLE_USER"));
        return g;
    }



    public Collection<Chat> getChats() {
        return chats;
    }

    public void setChats(Collection<Chat> chats) {
        this.chats = chats;
    }

    public User() {

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
        this.showPosition = showPosition;
    }

    public boolean isShowPosition() {

        return showPosition;
    }

    public void setNotificationEmail(boolean notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public boolean isNotificationEmail() {
        return notificationEmail;
    }

    public Blob getProfielFoto() {
        return profielFoto;
    }

    public void setProfielFoto(Blob profielFoto) {
        this.profielFoto = profielFoto;
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
