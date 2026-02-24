package fi.haagahelia.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import fi.haagahelia.course.domain.DepartmentRepository;
import fi.haagahelia.course.domain.Student;
import fi.haagahelia.course.domain.StudentRepository;

@Controller
public class StudentController {
	
	private final StudentRepository studentRepository; 
	private final DepartmentRepository departmentRepository; 

    // contructor injection
    public StudentController(StudentRepository studentRepository, 
        DepartmentRepository departmentRepository){
            this.studentRepository = studentRepository;
            this.departmentRepository = departmentRepository; 
    }
	
	// Show all students
    @GetMapping("/studentlist")
    public String studentList(Model model) {	
        model.addAttribute("students", studentRepository.findAll());
        return "studentlist";
    }
  
    // Add new student
    @GetMapping("/add")
    public String addStudent(Model model){
    	model.addAttribute("student", new Student());
    	model.addAttribute("departments", departmentRepository.findAll());
        return "addstudent";
    }     
    
    // Save new student
    @PostMapping("/save")
    public String save(Student student){
        studentRepository.save(student);
        return "redirect:/studentlist";
    }    

    // Delete student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
    	studentRepository.deleteById(studentId);
        return "redirect:/studentlist";
    }     
}
