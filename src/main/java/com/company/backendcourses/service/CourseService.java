package com.company.backendcourses.service;

import com.company.backendcourses.helpers.CarnetGenerator;
import com.company.backendcourses.persistence.crud.CourseCrudRepository;
import com.company.backendcourses.persistence.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseCrudRepository courseCrudRepository;

    public Optional<Course> getCourse(Integer id) {
        return courseCrudRepository.findById(id);
    }

    public List<Course> getCourses() {
        return (List<Course>) courseCrudRepository.findByStatus("1");
    }

    public Course createCourse(Course course)  {
        return courseCrudRepository.save(course);
    }

    public Optional<Course> updateCourse(Course course, Integer id) {
        return courseCrudRepository.findById(id).map(courseToUpdate -> {
            courseToUpdate.setName(course.getName());
            courseToUpdate.setSection(course.getSection());
            courseToUpdate.setStartDate(course.getStartDate());
            courseToUpdate.setFinalDate(course.getFinalDate());
            courseToUpdate.setStartTime(course.getStartTime());
            courseToUpdate.setEndTime(course.getEndTime());
            courseToUpdate.setIdUser(course.getIdUser());
            return courseCrudRepository.save(courseToUpdate);
        });
    }

    public boolean deleteCourse(@PathVariable Integer id) {
        return courseCrudRepository.findById(id).map(courseToUpdate -> {
            courseToUpdate.setStatus("0");
            courseCrudRepository.save(courseToUpdate);
            return true;
        }).orElse(false);
    }
}
