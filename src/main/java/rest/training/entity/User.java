package rest.training.entity;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String surname;
    private List<Project> projects;

    public User() {
    }

    public User(Long id, String name, String surname, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
