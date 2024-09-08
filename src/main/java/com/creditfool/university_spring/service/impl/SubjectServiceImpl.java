package com.creditfool.university_spring.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.creditfool.university_spring.dto.SubjectDto;
import com.creditfool.university_spring.entity.Subject;
import com.creditfool.university_spring.exception.DataAlreadyExistException;
import com.creditfool.university_spring.exception.DataNotFoundException;
import com.creditfool.university_spring.repository.SubjectRepository;
import com.creditfool.university_spring.service.SubjectService;
import com.creditfool.university_spring.util.UUIDValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;

    private final DataNotFoundException subjectNotFoundException = new DataNotFoundException("Subject not found");

    @Override
    public Subject createSubject(SubjectDto subjectDto) {
        validateSubjectData(subjectDto);
        Subject subject = dtoToEntity(subjectDto);
        return repository.save(subject);
    }

    @Override
    public void deleteSubject(String id) {
        Subject subject = getSubjectById(id, false);
        subject.setIsActive(false);
        repository.save(subject);
    }

    @Override
    public void deleteSubject(String id, boolean isHardDelete) {
        if (isHardDelete) {
            Subject subject = getSubjectById(id, true);
            repository.delete(subject);

        } else {
            deleteSubject(id);
        }
    }

    @Override
    public List<Subject> getAllSubject() {
        return repository.findAll();
    }

    @Override
    public List<Subject> getAllSubject(boolean isActive) {
        return repository.findByIsActive(isActive);
    }

    @Override
    public Subject getSubjectById(String id, boolean getNotActive) {
        if (!UUIDValidator.isValid(id)) {
            throw subjectNotFoundException;
        }
        Optional<Subject> subject = repository.findById(UUID.fromString(id));
        if (!subject.isPresent()) {
            throw subjectNotFoundException;
        }
        if (!subject.get().getIsActive().booleanValue() && !getNotActive) {
            throw subjectNotFoundException;
        }
        return subject.get();
    }

    @Override
    public Subject getSubjectByName(String name) {
        Optional<Subject> subject = repository.findByName(name);
        if (!subject.isPresent()) {
            throw subjectNotFoundException;
        }
        return subject.get();
    }

    @Override
    public Subject updateSubject(String id, SubjectDto updatedSubject) {
        Subject subject = getSubjectById(id, false);
        validateSubjectData(updatedSubject);
        if (updatedSubject.subjectName() != null) {
            subject.setSubjectName(updatedSubject.subjectName());
        }
        return repository.save(subject);
    }

    private void validateSubjectData(SubjectDto subjectDto) {
        Optional<Subject> subject = repository.findByName(subjectDto.subjectName());
        if (subject.isPresent() && !subject.get().getId().equals(subjectDto.id())) {
            throw new DataAlreadyExistException("Subject with same name already exist");
        }

    }

    private Subject dtoToEntity(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectDto.subjectName());
        return subject;
    }

}
