package com.updown.api.department.application.service;

import com.updown.api.department.application.port.in.DeleteDepartmentInPort;
import com.updown.api.department.application.port.out.LoadDepartmentOutPort;
import com.updown.api.department.application.port.out.SaveDepartmentOutPort;
import com.updown.api.common.exception.CustomRuntimeException;
import com.updown.api.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 부서 삭제 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DeleteDepartmentService implements DeleteDepartmentInPort {
    
    private final LoadDepartmentOutPort loadDepartmentOutPort;
    private final SaveDepartmentOutPort saveDepartmentOutPort;
    
    @Override
    public void deleteDepartment(Long departmentId) {
        // 부서 존재 여부 확인
        loadDepartmentOutPort.loadDepartment(departmentId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionType.NOT_FOUND_DEPARTMENT));
        
        saveDepartmentOutPort.deleteDepartment(departmentId);
    }
}
