/**
 * 
 */
package com.signify.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signify.bean.Student;
import com.signify.exception.ProfessorNotAssignedException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.service.ProfessorService;

/**
 * @author Training
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/professor")
public class ProfessorController {
	/** The professor service. */
	@Autowired
	private ProfessorService professorService;

	/**
	 * View enrolled students.
	 *
	 * @param data the data
	 * @return the list
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, value = "/enrolledstudents", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Student>> getEnrolledStudents(@RequestBody Map<String, String> data) {
		try {
			return new ResponseEntity<List<Student>>(professorService.getEnrolledStudents(data.get("professorId")),
					HttpStatus.OK);
		} catch (ProfessorNotAssignedException e) {
			return new ResponseEntity<List<Student>>(new ArrayList<Student>() , HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds the grades.
	 *
	 * @param data the data
	 * @return the response entity
	 */
	@RequestMapping(value = "/addgrade", method = RequestMethod.POST)
	public ResponseEntity<String> addGrades(@RequestBody Map<String, String> data) {
		String professorId = data.get("professorId");
		String studentId = data.get("studentId");
		String grade = data.get("grade");
		try {
			professorService.addGrade(professorId, studentId, grade);
			return new ResponseEntity<String>("Student Graded", HttpStatus.OK);
		} catch (StudentNotRegisteredException | ProfessorNotAssignedException e) {
			return new ResponseEntity<String>("Student not registered or professor not assigned",
					HttpStatus.BAD_REQUEST);
		}
	}
}
