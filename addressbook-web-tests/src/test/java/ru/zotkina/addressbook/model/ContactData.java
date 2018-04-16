package ru.zotkina.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;


@Entity
@Table(name="addressbook")
public class ContactData {
    @Expose
    @Column(name="firstname")
    private  String firstname;
    @Expose
    @Column(name="middlename")
    private String middlename;
    @Expose
    @Column(name="lastname")
    private  String lastname;
    @Expose
    @Column(name="nickname")
    private  String nickname;
    @Expose
    @Column(name="title")
    private  String title;
    @Expose
    @Column(name="company")
    private  String company;
    @Expose
    @Type(type ="text")
    @Column(name="address")
    private  String address;
    @Expose
    @Column(name="home")
    @Type(type ="text")
    private  String home;
    @Expose
    @Column(name="mobile")
    @Type(type ="text")
    private  String mobile;
    @Expose
    @Column(name="work")
    @Type(type ="text")
    private  String work;
    @Expose
    @Column(name="fax")
    @Type(type ="text")
    private  String fax;
    @Expose
    @Column(name="email")
    @Type(type ="text")
    private  String email;
    @Expose
    @Column(name="email2")
    @Type(type ="text")
    private  String email2;
    @Expose
    @Column(name="email3")
    @Type(type ="text")
    private  String email3;
    @Expose
    @Column(name="homepage")
    @Type(type ="text")
    private  String homepage;
    @Id
    @Column(name="id")
    private int  idcontact=Integer.MAX_VALUE;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String allAddress;
    @Expose
    @Column(name="photo")
    @Type(type ="text")
    private  String photo;
    @Expose
    @Transient
    private String group;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (idcontact != that.idcontact) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + idcontact;
        return result;
    }

    public ContactData withIdcontact(int idcontact) {
        this.idcontact = idcontact;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withFax(String fax) {

        this.fax = fax;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAllAddress() {
        return allAddress;
    }

    public ContactData withAllAddress(String allAddress) {
        this.allAddress = allAddress;
        return this;
    }

    public ContactData withWork(String work) {

        this.work = work;
        return this;
    }

    public ContactData withHome(String home) {

        this.home = home;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withFirstname(String firstname) {

        this.firstname = firstname;
        return this;
    }




    public int getIdcontact() {
        return idcontact;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getGroup(){return group;}


    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", idcontact=" + idcontact +
                '}';
    }

}
