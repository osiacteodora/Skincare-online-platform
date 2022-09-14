package com.example.demo.DataModels;
import javax.persistence.Entity;
import javax.persistence.Id;


public class User {

    private Long id_user;
    private String nume;
    private String prenume;
    private String type;
    private String email;
    private int budget;
    private String password;

    public User(){};

    public User(Long id_user, String nume, String prenume, String type, String email, int budget, String password) {
        this.id_user = id_user;
        this.nume = nume;
        this.prenume = prenume;
        this.type = type;
        this.email = email;
        this.budget = budget;
        this.password = password;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", budget=" + budget +
                ", password='" + password + '\'' +
                '}';
    }
}
