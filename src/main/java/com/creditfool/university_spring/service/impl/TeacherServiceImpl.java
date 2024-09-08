package com.creditfool.university_spring.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.TeacherDto;
import com.creditfool.university_spring.entity.Teacher;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.repository.TeacherRepository;
import com.creditfool.university_spring.service.TeacherService;
import com.creditfool.university_spring.util.UUIDValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;

    private final DataNotFoundException teacherNotFoundException = new DataNotFoundException("Teacher not found");

    @Override
    public Teacher createTeacher(TeacherDto teacherDto) {
        validateTeacherData(teacherDto);
        Teacher teacher = dtoToEntity(teacherDto);
        return repository.save(teacher);
    }

    @Override
    public void deleteTeacher(String id) {
        Teacher teacher = getTeacherById(id);
        teacher.setIsActive(false);
        repository.save(teacher);
    }

    @Override
    public void deleteTeacher(String id, boolean isHardDelete) {
        if (isHardDelete) {
            Teacher teacher = getTeacherById(id);
            repository.delete(teacher);

        } else {
            deleteTeacher(id);
        }

    }

    @Override
    public List<Teacher> getAllTeacher() {
        return repository.findAll();
    }

    @Override
    public List<Teacher> getAllTeacher(boolean isActive) {
        return repository.findByIsActive(isActive);
    }

    @Override
    public Teacher getTeacherById(String id) {
        if (!UUIDValidator.isValid(id)) {
            throw teacherNotFoundException;
        }
        Optional<Teacher> teacher = repository.findById(UUID.fromString(id));
        if (!teacher.isPresent()) {
            throw teacherNotFoundException;
        }
        return teacher.get();
    }

    @Override
    public Teacher getTeacherByNip(String nip) {
        Optional<Teacher> teacher = repository.findByNip(nip);
        if (!teacher.isPresent()) {
            throw teacherNotFoundException;
        }
        return teacher.get();
    }

    @Override
    public Teacher updateTeacher(String id, TeacherDto updatedTeacher) {
        Teacher teacher = getTeacherById(id);
        validateTeacherData(updatedTeacher);
        if (updatedTeacher.firstName() != null) {
            teacher.setFirstName(updatedTeacher.firstName());
        }
        if (updatedTeacher.lastName() != null) {
            teacher.setLastName(updatedTeacher.lastName());
        }
        if (updatedTeacher.nip() != null) {
            teacher.setNip(updatedTeacher.nip());
        }
        if (updatedTeacher.address() != null) {
            teacher.setAddress(updatedTeacher.address());
        }
        if (updatedTeacher.phone() != null) {
            teacher.setPhone(updatedTeacher.phone());
        }
        if (updatedTeacher.email() != null) {
            teacher.setEmail(updatedTeacher.email());
        }
        repository.save(teacher);
        return teacher;
    }

    private void validateTeacherData(TeacherDto teacherDto) {
        List<Teacher> activeTeacher = getAllTeacher(true);
        for (Teacher teacher : activeTeacher) {
            if (teacher.getNip().equals(teacherDto.nip())) {
                throw new DataAlreadyExistException("Teacher with same NIP already exist");
            }
            if (teacher.getEmail().equals(teacherDto.email())) {
                throw new DataAlreadyExistException("Teacher with same Email already exist");
            }
            if (teacher.getPhone().equals(teacherDto.phone())) {
                throw new DataAlreadyExistException("Teacher with same Phone Number already exist");
            }
        }
    }

    private Teacher dtoToEntity(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName());
        teacher.setLastName(teacherDto.lastName());
        teacher.setNip(teacherDto.nip());
        teacher.setAddress(teacherDto.address());
        teacher.setEmail(teacherDto.email());
        teacher.setPhone(teacherDto.phone());
        return teacher;
    }
}
