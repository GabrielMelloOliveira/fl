package com.filelibrary.gabrielmello.test;

/**
 *
 * @author Gabriel Mello de Oliveira
 */
public class User {
    
    private long id;
    private String name;
    private String email;
    private int age;

    public User() {}
    
    public User(long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    public User(String id, String name, String email, String age) {
        this.id = Long.valueOf(id);
        this.name = name;
        this.email = email;
        this.age = Integer.valueOf(age);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Nome: " + this.name + ", Email: " + this.email + ", Idade: " + this.age + " anos.";
    }
    
}
