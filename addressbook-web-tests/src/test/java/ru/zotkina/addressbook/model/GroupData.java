package ru.zotkina.addressbook.model;

public class GroupData {
    private final String groupname;
    private final String header;
    private final String footer;
    private final String idgroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (groupname != null ? !groupname.equals(groupData.groupname) : groupData.groupname != null) return false;
        return idgroup != null ? idgroup.equals(groupData.idgroup) : groupData.idgroup == null;
    }

    @Override
    public int hashCode() {
        int result = groupname != null ? groupname.hashCode() : 0;
        result = 31 * result + (idgroup != null ? idgroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "groupname='" + groupname + '\'' +
                ", idgroup='" + idgroup + '\'' +
                '}';
    }

    public String getIdgroup() {
        return idgroup;
    }

    public GroupData(String idgroup, String groupname, String header, String footer) {
        this.groupname = groupname;
        this.header = header;
        this.footer = footer;
        this.idgroup=idgroup;

    }


    public GroupData(String groupname, String header, String footer) {
        this.groupname = groupname;
        this.header = header;
        this.footer = footer;
        this.idgroup=null;

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
