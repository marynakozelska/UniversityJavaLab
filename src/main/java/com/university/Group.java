package com.university;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.*;

public class Group {
    private String name;
    private List<Student> students;

    public Group(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    //    Методи які дозволяють додати студента до групи
    public boolean addStudent(Student student) throws InstanceAlreadyExistsException {
        if (isInList(student)) {
            throw new InstanceAlreadyExistsException(); // message, gradebooknum
        }
        return students.add(student);
    }

    //    Перевіряти чи нема студентів з такою заліковкою.
    private boolean isInList(Student student) { // call existsByGradeBookNumber
        return existsByGradeBookNumber(student.getGradeBookNumber());
    }

    //    Видаляти студентів
    public boolean deleteStudent(Student student) throws InstanceNotFoundException {
        if (!isInList(student)) {
            throw new InstanceNotFoundException();
        }
        return students.remove(student);
    }

    //    Сортувати по середньому балу (зросс. і спадному порядку)
    public List<Student> sortByGPAAscending() {
        return students
                .stream()
                .sorted(Comparator.comparing(Student::getGPA))
                .toList();
    }

    public List<Student> sortByGPADescending() {
        return students
                .stream()
                .sorted(Comparator.comparing(Student::getGPA).reversed())
                .toList();
    }

    //    Сортувати по алфавіту (по прізвищу, імені).
    public List<Student> sortBySurname() {
        return students
                .stream()
                .sorted(Comparator.comparing(Student::getSurname).thenComparing(Student::getName))
                .toList();
    }

    //    Пошук по заліковці чи є, імені чи прізвищу.
    public boolean existsByGradeBookNumber(int gradeBookNumber) {
        Student firstStudent = students
                .stream()
                .filter(student -> student.getGradeBookNumber() == gradeBookNumber)
                .findFirst()
                .orElse(null);
        return firstStudent != null;
    }

    public Student findByGradeBookNumber(int gradeBookNumber) throws InstanceNotFoundException {
        return students
                .stream()
                .filter(student -> student.getGradeBookNumber() == gradeBookNumber)
                .findFirst()
                .orElseThrow(InstanceNotFoundException::new);
    }

    public List<Student> findBySurname(String surname) {
        return students
                .stream()
                .filter(student -> Objects.equals(student.getSurname(), surname))
                .toList();
    }

    public List<Student> findBySurnameAndName(String surname, String name) {
        return students
                .stream()
                .filter(student -> Objects.equals(student.getSurname(), surname) &&
                        Objects.equals(student.getName(), name))
                .toList();
    }
}
