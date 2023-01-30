package com.updown.api.account.domain;

import com.updown.api.account.presentation.dto.AccountSaveRequestDTO;
import com.updown.api.account.presentation.dto.AccountUpdateRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
// @DynamicInsert 이 어노테이션을 적용하게 되면 Insert 쿼리를 날릴 때 null인 값은 제외하고 쿼리문이 만들어진다.
// @DynamicUpdate 이 어노테이션을 적용하게 되면 Update 쿼리를 날릴 때 null인 값은 제외하고 쿼리문이 만들어진다.
// @DynamicInsert, @DynamicUpdate 를 사용하게 되면 불필요한 DB 부하를 줄일 수 있고, default 값 대신에 null 값이 들어갈 일은 없을 것이다.
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  해당 값을 유니크하게 DB에서 관리할 수 있도록 해준다.
    //  처음 insert 할 때 동시성 제어도 가능하다.
    @Column(unique = true)
    private String loginId;

    @Column
    private String accountName;

    // 낙관적 잠금(Optimisstic Lock)은 현실적으로 데이터 갱신시 경합이 발생하지 않을 것이라고 낙관적으로 보고 잠금을 거는 기법입니다.
    // 예를 들어 회원정보에 대한 갱신은 보통 해당 회원에 의해서 이루어지므로 동시에 여러 요청이 발생할 가능성이 낮습니다.
    // 따라서 동시에 수정이 이루어진 경우를 감지해서 예외를 발생시켜도 실제로 예외가 발생할 가능성이 낮다고 낙관적으로 보는 것입니다.
    // 이는 엄밀한 의미에서 보면 잠금이라기 보다는 일종의 충돌감지(Conflict detection)에 가깝습니다.
    // JPA에서 낙관적 잠금을 사용하기 위해서는 @Version 어노테이션을 붙인 필드를 추가하면 간단하게 적용할 수 있습니다.
    //  1. A트랜잭션이 emp테이블의 emp_no = 1 값을 가져온다. 이때 version은 0이다.
    //  2. B트랜잭션이 emp테이블의 emp_no = 1 값을 가져온다. 이때 version은 0이다.
    //  3. A트랜잭션이 조회한 엔티티를 변경하여 커밋한다.
    //      > 커밋하기 전에 JPA에서는 version을 확인해 본다. 현재 엔티티의 version과 DB의 version 값이 같은지
    //      > 엔티티 버전와 DB 버전이 같다면 emp 테이블의 version 컬럼에 1로 업데이트 한다.
    //  4. B트랜잭션이 조회한 엔티티를 변경하여 커밋한다.
    //      > 커밋하기 전에 version을 확인한다. 어라~ 근데 내가 조회한 엔티티 version은 0인데 DB의 version은 1로 되어 있다.
    //      > 예외를 발생시킨다
    @Version
    private Long version;

    @Builder
    public AccountEntity(String loginId, String accountName) {
        this.loginId = loginId;
        this.accountName = accountName;
    }

    public static AccountEntity create(AccountSaveRequestDTO accountSaveRequestDTO) {
        return new AccountEntity(accountSaveRequestDTO.getLoginId(), accountSaveRequestDTO.getAccountName());
    }

    public void update(AccountUpdateRequestDTO accountUpdateRequestDTO) {
        this.accountName = accountUpdateRequestDTO.getChangeAccountName();
    }
}
