package iuh.fit.springdatajpa3.repository;

import iuh.fit.springdatajpa3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findCourseByPriceLessThan(double max);

    List<Course> findCourseByTitleContainingIgnoreCase(String title);

    @Query("select c from Course c where c.instructor.fullName = :name")
    List<Course> findCourseByInstructorFullName(@Param("name") String name);

    @Query("select sum(c.duration_hours) from Course c where c.instructor.email = :email")
    Integer getTotalDuration(@Param("email") String email);

    @Query(value = "SELECT * FROM courses ORDER BY price DESC LIMIT 2", nativeQuery = true)
    List<Course> findTop2CoursePrice();
}
