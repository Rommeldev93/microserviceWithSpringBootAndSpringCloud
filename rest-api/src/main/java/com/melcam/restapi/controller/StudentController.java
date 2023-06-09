package com.melcam.restapi.controller;

import com.melcam.restapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1, "Rommel", "Melcam"
        );
        //return new ResponseEntity<>(student,HttpStatus.OK) ;
        //return  ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "rommel")
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Rommel", "Melcam"));
        students.add(new Student(2, "Yul", "Melcam"));
        students.add(new Student(3, "Zoe", "Melcam"));
        return ResponseEntity.ok(students);
    }

    //http://localhost:8080/students/1/rommel/melcam
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                                       @PathVariable("first-name") String firstName,
                                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/students/query?id=1&firstName=Rommel&lastName=Melcam
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                                          @RequestParam String firstName,
                                                          @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/students
    /*
    *
    {
        "id": 1,
        "firstName": "Rommel",
        "lastName": "Melcam"
    }
    * */
    @PostMapping("create")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //http://localhost:8080/students/1/update
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/students/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successsfully!");
    }
}
