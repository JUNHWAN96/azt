package com.example.azt.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class) // 엔티티를 DB에 적용하기전 콜백 now를 안해줘도 시간 생성
@MappedSuperclass //공통 매핑 정보를 사용하기 위해서 선언
public class AuditingFields {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // 파라미터를 받아서 세팅 할 때, 파싱에 대한 룰 적용
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
