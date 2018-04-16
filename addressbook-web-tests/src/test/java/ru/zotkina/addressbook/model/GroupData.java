package ru.zotkina.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="group_list")
public class GroupData {
    @Expose
    @Column (name="group_name")
    private  String groupname;
    @Expose
    @Column (name="group_header")
    @Type(type ="text")
    private  String header;
    @Expose
    @Column (name="group_footer")
    @Type(type ="text")
    private  String footer;
    @Id
    @Column (name="group_id")
    private  int idgroup=Integer.MAX_VALUE;;

    @Override
    public String toString() {
        return "GroupData{" +
                "groupname='" + groupname + '\'' +
                ", idgroup=" + idgroup +
                '}';
    }

    public int getIdgroup() {
        return idgroup;
    }

    public GroupData withIdgroup(int idgroup) {
        this.idgroup = idgroup;
        return this;
    }

    public GroupData withGroupname(String groupname) {
        this.groupname = groupname;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {

        this.footer = footer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (idgroup != groupData.idgroup) return false;
        return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;
    }

    @Override
    public int hashCode() {
        int result = groupname != null ? groupname.hashCode() : 0;
        result = 31 * result + idgroup;
        return result;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

}
