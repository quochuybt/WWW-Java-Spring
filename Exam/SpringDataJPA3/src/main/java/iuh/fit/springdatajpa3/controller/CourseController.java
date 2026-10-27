package iuh.fit.springdatajpa3.controller;

import iuh.fit.springdatajpa3.entity.Course;
import iuh.fit.springdatajpa3.service.CourseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/page")
    public Page<Course> getPage(@RequestParam(defaultValue = "0")int number, @RequestParam(defaultValue = "10")int size) {
        return courseService.findAll(number,size);
    }

    @GetMapping("/price")
    public List<Course> findCourseByPriceLessThan(@RequestParam double max) {
        return courseService.findCourseByPriceLessThan(max);
    }

    @GetMapping("/search")
    public List<Course> findCourseByTitleContainingIgnoreCase(@RequestParam String title) {
        return courseService.findCourseByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/find")
    public List<Course> findCourseByInstructorFullName(@RequestParam String name) {
        return courseService.findCourseByInstructorFullName(name);
    };

    @GetMapping("/duration")
    public Integer getTotalDuration(@RequestParam String email) {
        return courseService.getTotalDuration(email);
    };

    @GetMapping("/top2")
    public List<Course> findTop2CoursePrice() {
        return courseService.findTop2CoursePrice();
    };
}
