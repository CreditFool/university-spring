package com.creditfool.university_spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.creditfool.university_spring.dto.response.StudentListResponse;
import com.creditfool.university_spring.entity.Student;

@SpringBootTest
class StudentRepositoryIntegrationTest {

        @Autowired
        StudentRepository repository;

        @BeforeEach
        void setup() {
                repository.deleteAll();

                Student student1 = Student.builder()
                                .firstName("Kokoro")
                                .lastName("Tsurumaki")
                                .address("Happy land")
                                .email("kokoro@mail.com")
                                .phone("12345678910")
                                .nim("1234567891")
                                .build();

                Student student2 = Student.builder()
                                .firstName("Kanon")
                                .lastName("Matsubara")
                                .address("Happy land")
                                .email("kanon@mail.com")
                                .phone("12345678911")
                                .nim("1234567892")
                                .build();

                Student student3 = Student.builder()
                                .firstName("Kaoru")
                                .lastName("Seta")
                                .address("Happy land")
                                .email("seta@mail.com")
                                .phone("12345678912")
                                .nim("1234567893")
                                .build();

                Student student4 = Student.builder()
                                .firstName("Hagumi")
                                .lastName("Kitagawa")
                                .address("Happy land")
                                .email("hagumi@mail.com")
                                .phone("12345678913")
                                .nim("1234567894")
                                .build();

                Student student5 = Student.builder()
                                .firstName("Misaki")
                                .lastName("Okusawa")
                                .address("Happy land")
                                .email("misaki@mail.com")
                                .phone("12345678914")
                                .nim("1234567895")
                                .build();

                Student student6 = Student.builder()
                                .firstName("Michelle")
                                .address("Happy land")
                                .email("misaki@mail.com")
                                .phone("12345678914")
                                .nim("1234567896")
                                .deletedAt(LocalDateTime.now())
                                .build();

                repository.saveAll(List.of(
                                student1, student2, student3, student4, student5, student6));
        }

        @Test
        void testSave() {
                assertEquals(6, repository.count());
        }

        @Test
        void testFindAllByDeletedAtIsNotNull() {
                List<Student> actuaList = repository.findAllByDeletedAtIsNotNull();
                assertEquals(1, actuaList.size());
        }

        @Test
        void testFindAllByDeletedAtIsNotNullWithPage() {
                Page<Student> pageList = repository.findAllByDeletedAtIsNotNull(PageRequest.of(0, 2));
                assertNotNull(pageList);
                assertEquals(1, pageList.getTotalPages());
                assertEquals(1, pageList.getNumberOfElements());
                assertEquals(1, pageList.getTotalElements());
        }

        @Test
        void testFindAllByDeletedAtIsNull() {
                List<Student> actuaList = repository.findAllByDeletedAtIsNull();
                assertEquals(5, actuaList.size());
        }

        @Test
        void testFindAllByDeletedAtIsNullWithPage() {
                Page<Student> pageList = repository.findAllByDeletedAtIsNull(PageRequest.of(0, 2));
                assertNotNull(pageList);
                assertEquals(3, pageList.getTotalPages());
                assertEquals(2, pageList.getNumberOfElements());
                assertEquals(5, pageList.getTotalElements());
        }

        @Test
        void testFindAllByEmailIgnoreCaseOrPhoneOrNimAndDeletedAtIsNull() {
                List<Student> actuaList = repository.findAllByEmailIgnoreCaseOrPhoneOrNimAndDeletedAtIsNull(
                                "kokoro@mail.com",
                                "12345678911",
                                "1234567893"

                );
                assertEquals(3, actuaList.size());

                actuaList = repository.findAllByEmailIgnoreCaseOrPhoneOrNimAndDeletedAtIsNull(
                                "kokoro@mail.com",
                                "12345678911",
                                "1234567896"

                );
                assertEquals(2, actuaList.size());
        }

        @Test
        void testFindByIdAndDeletedAtIsNull() {
                Student aya = Student.builder()
                                .firstName("Aya")
                                .lastName("Maruyama")
                                .address("Pasupare land")
                                .email("aya@mail.com")
                                .phone("12345678922")
                                .nim("2234567893")
                                .build();

                Student savedStudent = repository.save(aya);
                Optional<Student> foundedData = repository.findByIdAndDeletedAtIsNull(savedStudent.getId());

                assertTrue(foundedData.isPresent());
                assertEquals(savedStudent.getId(), foundedData.get().getId());
                assertEquals(aya.getFirstName(), foundedData.get().getFirstName());
        }

}
