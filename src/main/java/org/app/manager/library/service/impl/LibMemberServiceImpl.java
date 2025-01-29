package org.app.manager.library.service.impl;

import org.app.manager.library.model.LibMember;
import org.app.manager.library.repository.LibMemberRepository;
import org.app.manager.library.service.LibMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibMemberServiceImpl implements LibMemberService {

    @Autowired
    private LibMemberRepository libMemberRepository;

    public boolean isMembershipValid(Long memberId) {
        Optional<LibMember> member = libMemberRepository.findById(memberId);

        if (member.isPresent()) {
            LocalDate membershipDate = member.get().getMembershipDate();
            return membershipDate.isAfter(LocalDate.now().minusYears(1));
        }
        return false;
    }

    @Override
    public void addMember(LibMember member) {
        libMemberRepository.save(member);
    }

    @Override
    public Optional<LibMember> getMemberById(Long id) {
        return libMemberRepository.findById(id);
    }

    @Override
    public List<LibMember> getAllMembers() {
        return libMemberRepository.findAll();
    }

    @Override
    public void removeMember(Long id) {
        libMemberRepository.deleteById(id);
    }
}
