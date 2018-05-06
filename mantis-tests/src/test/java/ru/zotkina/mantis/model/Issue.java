package ru.zotkina.mantis.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.sql.SmallIntTypeDescriptor;

import javax.persistence.*;

@Entity
@Table(name="mantis_bug_table")
public class Issue {
    @Id
    @Column (name="id")
    private int id;
    @Expose
    @Column(name="summary")
    private String summary;

    @Transient
    private String description;


    //@OneToOne(fetch = FetchType.EAGER)
    //@JoinTable(name="mantis_project_table", joinColumns = @JoinColumn(name="id"),
      //      inverseJoinColumns =@JoinColumn(name="project_id") )
    @Transient
    private Project project;

    @Expose
    @Column(name = "status")
    //@Type(type = "org.hibernate.type.SmallIntTypeDescriptor")
    private short status;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Issue withStatus(short status) {
        this.status = status;
        return this;
    }
}
