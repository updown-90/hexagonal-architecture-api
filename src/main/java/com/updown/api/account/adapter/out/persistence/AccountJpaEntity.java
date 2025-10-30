package com.updown.api.account.adapter.out.persistence;

import com.updown.api.department.adapter.out.persistence.DepartmentJpaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 계정 JPA 엔티티
 */
@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String loginId;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String accountName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;
    
    @Version
    private Long version;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentJpaEntity department;
    
    @Builder
    public AccountJpaEntity(Long id, String loginId, String password, String accountName, 
                           AccountStatus accountStatus, Long version, DepartmentJpaEntity department) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.accountName = accountName;
        this.accountStatus = accountStatus;
        this.version = version;
        this.department = department;
    }
}
