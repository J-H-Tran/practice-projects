package org.app.manager.library.service.impl;

import org.app.manager.library.model.Member;
import org.app.manager.library.repository.MemberRepository;
import org.app.manager.library.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public List<Member> findAllMembers() {
        logger.info("Fetching all members from the database");
        return memberRepo.findAll();
    }

    @Override
    public Member findMemberById(int id) {
        logger.info("Fetching member with id: {}", id);
        return memberRepo.findById(id).orElseThrow(() -> {
            logger.error("Member not found with id: {}", id);
            return new NoSuchElementException("Member not found with id: " + id);
        });

    }

    @Transactional
    @Override
    public Member saveMember(Member member) {
        logger.info("Saving member to the database");
        return memberRepo.save(member);
    }

    @Transactional
    @Override
    public void deleteMemberById(int id) {
        if (!memberRepo.existsById(id)) {
            logger.error("Member not found with id: {}, Unable to delete", id);
            throw new NoSuchElementException("Member not found with id: " + id);
        }
        logger.info("Deleting member with id: {}", id);
        memberRepo.deleteById(id);
    }
}
