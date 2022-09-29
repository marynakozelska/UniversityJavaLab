package com.university;

import java.time.LocalDate;

public class Student {
    private int gradeBookNumber;
    private String name;
    private String surname;
    private LocalDate birthday;
    private double GPA;

    public Student(int gradeBookNumber, String name, String surname, LocalDate birthday, double GPA) {
        this.gradeBookNumber = gradeBookNumber;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.GPA = GPA;
    }

    public int getGradeBookNumber() {
        return gradeBookNumber;
    }

    public void setGradeBookNumber(int gradeBookNumber) {
        this.gradeBookNumber = gradeBookNumber;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student: " + name + " " + surname + "\nGPA: " + GPA +
                "\nGrade book number: " + gradeBookNumber + "\nBirthday: " + birthday.toString() + "\n";
    }
}
