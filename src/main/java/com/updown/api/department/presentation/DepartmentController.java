package com.updown.api.department.presentation;

import com.updown.api.department.presentation.dto.request.DepartmentSaveRequest;
import com.updown.api.department.presentation.dto.request.DepartmentUpdateRequest;
import com.updown.api.department.presentation.dto.response.DepartmentFindResponse;
import com.updown.api.department.presentation.mapstruct.mapper.DepartmentEntityMapper;
import com.updown.api.department.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // @ResponseBody 알아서 사용하게 해주려고 사용
@RequiredArgsConstructor // @RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentFindResponse createDepartment(@RequestBody @Valid DepartmentSaveRequest departmentSaveRequest) {
        return DepartmentEntityMapper.INSTANCE.departmentEntityToDepartmentFindResponseDTO(
            departmentService.createDepartment(departmentSaveRequest)
        );
    }

    @GetMapping
    public List<DepartmentFindResponse> findAccounts() {
        return DepartmentEntityMapper.INSTANCE.departmentEntityToDepartmentFindResponseDTO(
                departmentService.findAll()
        );
    }

    @GetMapping("/{id}")
    public DepartmentFindResponse findDepartmentById(@PathVariable Long id) {
        return DepartmentEntityMapper.INSTANCE.departmentEntityToDepartmentFindResponseDTO(
                departmentService.findById(id)
        );
    }

    @PutMapping("/{id}")
    public DepartmentFindResponse updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentUpdateRequest departmentUpdateRequest) {
        return DepartmentEntityMapper.INSTANCE.departmentEntityToDepartmentFindResponseDTO(
                departmentService.updateDepartment(id, departmentUpdateRequest)
        );
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

}
