package com.example.edtech;

import com.example.edtech.model.Enrollment;
import com.example.edtech.repository.EnrollmentRepository;
import com.example.edtech.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    void findByStudentAndCourse_ExistingEnrollment_ReturnsEnrollment() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentRepository.findByCourseIdAndStudentId(1L, 1L)).thenReturn(Optional.of(enrollment));

        Optional<Enrollment> result = enrollmentService.findByCourseIdAndStudentId(1L, 1L);

        assertTrue(result.isPresent());
    }

    @Test
    void deleteById_ExistingId_DeletesSuccessfully() {
        doNothing().when(enrollmentRepository).deleteById(1L);

        assertDoesNotThrow(() -> enrollmentService.deleteById(1L));
        verify(enrollmentRepository, times(1)).deleteById(1L);
    }
}