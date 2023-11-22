package org.jwt.entities;

//공통 속석화

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract  class Base {
    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime createAt; //등록 일자
    @LastModifiedDate
    @Column(insertable=false)
    private LocalDateTime modifedAt; // 수정 일자
}
