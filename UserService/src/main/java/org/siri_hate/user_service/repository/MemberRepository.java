package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByUsername(String username);
    Member findMemberByEmail(String email);
    List<Member> findMemberByUsernameStartingWithIgnoreCase(String username);
    List<Member> findMembersByProfileHiddenFlagIsFalse();
}
