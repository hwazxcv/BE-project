package org.jwt.repositories;

import org.jwt.entities.Member;
import org.jwt.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

    Optional<Member> findByEmail(String email);

    default boolean exists(String email) {
        return exists(QMember.member.email.eq(email));
    }
}
