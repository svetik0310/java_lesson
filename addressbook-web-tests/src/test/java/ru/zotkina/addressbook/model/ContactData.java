package ru.zotkina.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


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

    public ContactData withGroups(Set<GroupData> groups)
                                   {
        this.groups = groups;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns =@JoinColumn(name="group_id") )
    private Set<GroupData> groups = new HashSet<GroupData>();

    public Groups getGroups() {
        return new Groups(groups);
    }

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
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (home != null ? !home.equals(that.home) : that.home != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (work != null ? !work.equals(that.work) : that.work != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
        return homepage != null ? homepage.equals(that.homepage) : that.homepage == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
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


    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", idcontact=" + idcontact +
                '}';
    }

}
