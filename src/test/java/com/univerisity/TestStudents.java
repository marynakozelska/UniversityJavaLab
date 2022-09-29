package com.univerisity;

import com.university.Group;
import com.university.Student;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class TestStudents {
    @Test
    @DisplayName("Exists by grade book")
    public void test1() throws InstanceAlreadyExistsException {
        Student student1 = new Student(12, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Group group = new Group("101A");
        group.addStudent(student1);
        assertTrue(group.existsByGradeBookNumber(12));
    }

    @Test
    @DisplayName("List size")
    public void test2() {
        Student student1 = new Student(22, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Student student2 = new Student(22, "Jane", "Doe", LocalDate.of(2002, 1, 12), 5);
        Group group = new Group("101A");
        try {
            group.addStudent(student1);
            group.addStudent(student2); // has same grade book number
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(1, group.getStudents().size());
    }

    @Test
    @DisplayName("Deleting student")
    public void test3() throws InstanceAlreadyExistsException, InstanceNotFoundException {
        Student student1 = new Student(22, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Student student2 = new Student(15, "Jane", "Doe", LocalDate.of(2001, 5, 6), 3.8);
        Group group = new Group("101A");
        group.addStudent(student1);
        group.addStudent(student2);
        group.deleteStudent(student1);
        assertFalse(group.existsByGradeBookNumber(22));
    }

    @Test
    @DisplayName("Find by grade book")
    public void test4() throws InstanceAlreadyExistsException, InstanceNotFoundException {
        Student student1 = new Student(22, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Student student2 = new Student(15, "Jane", "Doe", LocalDate.of(2001, 5, 6), 3.8);
        Group group = new Group("101A");
        group.addStudent(student1);
        group.addStudent(student2);
        assertSame(group.findByGradeBookNumber(15), student2);
    }

    @Test
    @DisplayName("Find by surname")
    public void test5() throws InstanceAlreadyExistsException {
        Student student1 = new Student(22, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Student student2 = new Student(15, "Jane", "Doe", LocalDate.of(2001, 5, 6), 3.8);
        Group group = new Group("101A");
        group.addStudent(student1);
        group.addStudent(student2);
        assertSame(group.findBySurname("Doe").stream().findFirst().get(), student2);
    }

    @Test
    @DisplayName("Find by surname and name")
    public void test6() throws InstanceAlreadyExistsException {
        Student student1 = new Student(22, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Student student2 = new Student(15, "Jane", "Dou", LocalDate.of(2001, 9, 6), 3.8);
        Student student3 = new Student(12, "Nick", "Lucas", LocalDate.of(2003, 5, 6), 3.8);
        Student student4 = new Student(11, "Nick", "Dou", LocalDate.of(2002, 12, 6), 3.8);
        Group group = new Group("201A");
        group.addStudent(student1);
        group.addStudent(student2);
        group.addStudent(student3);
        group.addStudent(student4);
        assertSame(group.findBySurnameAndName("Dou", "Nick").stream().findFirst().get(), student4);
    }

    @Test
    @DisplayName("Not found")
    public void test7() {
        Student student1 = new Student(22, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Group group = new Group("201A");
        assertThrows(InstanceNotFoundException.class, () -> {
            group.deleteStudent(student1);
        });
    }

    @Test
    @DisplayName("Find by surname and name")
    public void test8() {
        Student student1 = new Student(22, "John", "Dou", LocalDate.of(2000, 1, 12), 4.5);
        Group group = new Group("201A");
        assertTrue(group.findBySurname("Lucas").isEmpty());
        assertTrue(group.findBySurnameAndName("Lucas", "John").isEmpty());
    }
}
