package com.university;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InstanceAlreadyExistsException, InstanceNotFoundException {
        Student student1 = new Student(12, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Student student2 = new Student(15, "Jane", "Doe", LocalDate.of(2001, 5, 6), 3.8);
        Student student3 = new Student(32, "Alex", "Jones", LocalDate.of(2000, 6, 29), 5);
        Student student4 = new Student(34, "Nick", "Lucas", LocalDate.of(2000, 12, 17), 4.2);
        Student student5 = new Student(36, "Joe", "Lucas", LocalDate.of(2000, 12, 17), 5);
        Student student6 = new Student(36, "Kevin", "Lucas", LocalDate.of(2000, 12, 17), 3);

        Group group = new Group("101A");

        group.addStudent(student1);
        group.addStudent(student2);
        group.addStudent(student3);
        try {
            group.addStudent(student3);
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        }
        group.addStudent(student4);
        group.addStudent(student5);
        try {
            group.addStudent(student6);
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        }

        System.out.println("--------------- Not sorted list ---------------");
        List<Student> studentList = group.getStudents();
        studentList.forEach(System.out::println);

        System.out.println("--------------- Sorted by GPA list ASC ---------------");
        List<Student> sortedStudentsASC = group.sortByGPAAscending();
        sortedStudentsASC.forEach(System.out::println);

        System.out.println("--------------- Sorted by GPA list DESC ---------------");
        List<Student> sortedStudentsDESC = group.sortByGPADescending();
        sortedStudentsDESC.forEach(System.out::println);

        System.out.println("--------------- Sorted by Surname list ---------------");
        List<Student> sortedStudentsByName = group.sortBySurname();
        sortedStudentsByName.forEach(System.out::println);

        System.out.println("--------------- Find by grade book ---------------");
        System.out.println(group.findByGradeBookNumber(34).toString());

        System.out.println("--------------- Find by surname ---------------");
        System.out.println(group.findBySurname("Lucas").toString());

        System.out.println("--------------- Find by surname and name ---------------");
        System.out.println(group.findBySurnameAndName("Jones", "Alex").toString());
    }
}
