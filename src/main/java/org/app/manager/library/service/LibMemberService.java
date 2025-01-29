package org.app.manager.library.service;

import org.app.manager.library.model.LibMember;

import java.util.List;
import java.util.Optional;

public interface LibMemberService {
    void addMember(LibMember member);
    Optional<LibMember> getMemberById(Long id);
    List<LibMember> getAllMembers();
    void removeMember(Long id);
    boolean isMembershipValid(Long memberId);
}
