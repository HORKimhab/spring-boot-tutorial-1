package com.hkimhab.basic.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.hkimhab.basic.dto.StudentDto;
import com.hkimhab.basic.mapper.StudentMapper;
import com.hkimhab.basic.model.User;
import com.hkimhab.basic.repository.UserRepository;
import com.hkimhab.basic.response.ApiResponseCustomize;
import com.hkimhab.basic.response.StudentResDto;
import com.hkimhab.basic.service.StudentService;

public class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private UserRepository repository;

    @Mock
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {

        // Given
        StudentDto dto = new StudentDto(
                "H",
                "Kimhab",
                "hkimhab@gmail.com",
                22,
                "password12345",
                1);

        User student = new User(
                "H",
                "Kimhab",
                "hkimhab@gmail.com",
                22,
                "password12345");

        User savedStudent = new User(
                "H",
                "Kimhab",
                "hkimhab@gmail.com",
                22,
                "password12345");
        savedStudent.setId(1L);

        // Mock the calls
        when(mapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(mapper.toStudentResDto(savedStudent)).thenReturn(new StudentResDto(
                savedStudent.getFirstName(),
                savedStudent.getLastName(),
                savedStudent.getEmail(),
                savedStudent.getAge()));

        // When
        StudentResDto response = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.firstName(), response.firstName());
        assertEquals(dto.lastName(), response.lastName());
        assertEquals(dto.email(), response.email());
        assertEquals(dto.age(), response.age());

        verify(mapper, times(1)).toStudent(dto);
        verify(repository, times(1)).save(student);
        verify(mapper, times(1)).toStudentResDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {

        // Given
        List<User> students = new ArrayList<>();
        students.add(new User(
                "H",
                "Kimhab",
                "hkimhab@gmail.com",
                22,
                "password12345"));

        // Mock the calls
        when(repository.findAll()).thenReturn(students);
        // When
        ResponseEntity<?> response = studentService.getAllUsers();
        ApiResponseCustomize<?> responseBody = assertInstanceOf(ApiResponseCustomize.class, response.getBody());
        List<?> data = assertInstanceOf(List.class, responseBody.getData());

        // Then
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Users retrieved successfully", responseBody.getMessage());
        assertEquals(students.size(), data.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
        // Given
        Long studentId = 1L;
        User student = new User(
                "H",
                "Kimhab",
                "hkimhab@gmail.com",
                22,
                "password12345");

        // Mock the calls
        when(repository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(mapper.toStudentResDto(any(User.class))).thenReturn(new StudentResDto(
                "H",
                "Kimhab",
                "hkimhab@gmail.com",
                22));

        // When
        ResponseEntity<?> response = studentService.getUserById(studentId);
        ApiResponseCustomize<?> responseBody = assertInstanceOf(ApiResponseCustomize.class, response.getBody());
        User responseData = assertInstanceOf(User.class, responseBody.getData());

        // Then
        assertEquals(200, response.getStatusCode().value());
        assertEquals("User found", responseBody.getMessage());
        assertEquals(student.getFirstName(), responseData.getFirstName());
        assertEquals(student.getLastName(), responseData.getLastName());
        assertEquals(student.getEmail(), responseData.getEmail());
        assertEquals(student.getAge(), responseData.getAge());
        verify(repository, times(1)).findById(studentId);
    }

    /**
     * Verifies that searching by name returns an HTTP 200 response containing
     * the expected number of matching users.
     *
     * Test flow:
     * 1. Prepare a sample search term and a mocked repository result.
     * 2. Stub {@code repository.searchByName(studentName)} so the service does
     *    not hit the database during the test.
     * 3. Call {@code studentService.searchUesrName(studentName)}.
     * 4. Extract the response body and assert that:
     *    - the HTTP status is 200
     *    - the returned data is a list
     *    - the list size matches the mocked repository result
     * 5. Verify the repository method is called exactly once with the same name.
     */
    @Test
    public void should_find_student_by_name() {
        // Given
        String studentName = "nhh";
        List<User> students = new ArrayList<>();
        students.add(new User(
                "H",
                "Kimhab",
                "hkimhab@gmail.com",
                22,
                "password12345"));

        // Mock the calls
        when(repository.searchByName(studentName)).thenReturn(students);

        // When
        ResponseEntity<?> response = studentService.searchUesrName(studentName);

        // Extract the wrapper response body and the actual returned list.
        ApiResponseCustomize<?> responseBody = assertInstanceOf(ApiResponseCustomize.class, response.getBody());
        List<?> data = assertInstanceOf(List.class, responseBody.getData());

        // Then
        assertEquals(200, response.getStatusCode().value());
        assertEquals(students.size(), data.size());

        verify(repository, times(1)).searchByName(studentName);
    }

}
