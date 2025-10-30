package com.updown.api.department.adapter.in.web;

import com.updown.api.department.application.port.in.CreateDepartmentInPort;
import com.updown.api.department.application.port.in.GetDepartmentInPort;
import com.updown.api.department.application.port.in.UpdateDepartmentInPort;
import com.updown.api.department.application.port.in.DeleteDepartmentInPort;
import com.updown.api.department.application.port.in.dto.CreateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.UpdateDepartmentCommand;
import com.updown.api.department.application.port.in.dto.DepartmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 부서 웹 컨트롤러
 */
@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    
    private final CreateDepartmentInPort createDepartmentInPort;
    private final GetDepartmentInPort getDepartmentInPort;
    private final UpdateDepartmentInPort updateDepartmentInPort;
    private final DeleteDepartmentInPort deleteDepartmentInPort;
    
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody @Valid CreateDepartmentCommand command) {
        DepartmentResponse response = createDepartmentInPort.createDepartment(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable Long departmentId) {
        DepartmentResponse response = getDepartmentInPort.getDepartment(departmentId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody @Valid UpdateDepartmentCommand command) {
        DepartmentResponse response = updateDepartmentInPort.updateDepartment(command);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        deleteDepartmentInPort.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }
}
