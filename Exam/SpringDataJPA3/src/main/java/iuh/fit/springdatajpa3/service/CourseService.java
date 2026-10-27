package iuh.fit.springdatajpa3.service;

import iuh.fit.springdatajpa3.entity.Course;
import iuh.fit.springdatajpa3.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Page<Course> findAll(int number, int size){
        Pageable courses = PageRequest.of(number,size);
        return courseRepository.findAll(courses);
    }

    public List<Course> findCourseByPriceLessThan(double max) {
        return courseRepository.findCourseByPriceLessThan(max);
    };

    public List<Course> findCourseByTitleContainingIgnoreCase(String title) {
        return courseRepository.findCourseByTitleContainingIgnoreCase(title);
    };

    public List<Course> findCourseByInstructorFullName(String name) {
        return courseRepository.findCourseByInstructorFullName(name);
    };

    public Integer getTotalDuration(String email) {
        return courseRepository.getTotalDuration(email);
    };

    public List<Course> findTop2CoursePrice() {
        return courseRepository.findTop2CoursePrice();
    };
}
