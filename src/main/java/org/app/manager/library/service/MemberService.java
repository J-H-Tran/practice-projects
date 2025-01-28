package org.app.manager.library.service;

import org.app.manager.library.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAllMembers();
    Member findMemberById(Long id);
    Member saveMember(Member member);
    void deleteMemberById(Long id);
}
