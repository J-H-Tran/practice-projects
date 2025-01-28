package org.app.manager.library.service.impl;

import org.app.manager.library.model.Member;
import org.app.manager.library.repository.MemberRepository;
import org.app.manager.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public List<Member> findAllMembers() {
        return memberRepo.findAll();
    }

    @Override
    public Member findMemberById(int id) {
        return memberRepo.findById(id).orElseThrow(() ->
            new NoSuchElementException("Member not found with id: " + id));
    }

    @Transactional
    @Override
    public Member saveMember(Member member) {
        return memberRepo.save(member);
    }

    @Transactional
    @Override
    public void deleteMemberById(int id) {
        if (!memberRepo.existsById(id)) {
            throw new NoSuchElementException("Member not found with id: " + id);
        }
        memberRepo.deleteById(id);
    }
}
