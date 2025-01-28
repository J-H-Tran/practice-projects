package org.app.manager.library.util;

import org.app.manager.library.model.Member;
import org.app.manager.library.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
public class MemberCommands {

    private static final Logger logger = LoggerFactory.getLogger(MemberCommands.class);

    @Autowired
    private MemberService memberService;

    @ShellMethod("List all members")
    public List<Member> listMembers() {
        logger.info("Listing all members");
        return memberService.findAllMembers();
    }

    @ShellMethod("Find a member by ID")
    public Member findMember(@ShellOption Long id) {
        try {
            logger.info("Finding member with ID: {}", id);
            return memberService.findMemberById(id);
        } catch (NoSuchElementException e) {
            logger.error("Error: Member not found with ID: {}", id, e);
            return null;
        }
    }

    @ShellMethod("Add a new member")
    public Member addMember(
            @ShellOption String name,
            @ShellOption String email
    ) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            logger.error("Error: Name and email must not be empty");
            return null;
        }
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setMembershipDate(LocalDate.now());
        logger.info("Adding new member: {}", member.getName());
        return memberService.saveMember(member);
    }

    @ShellMethod("Delete a member by ID")
    public String deleteMember(@ShellOption Long id) {
        try {
            logger.info("Deleting member with ID: {}", id);
            Member member = memberService.findMemberById(id);
            memberService.deleteMemberById(id);
            return new StringBuilder()
                    .append("Member ")
                    .append(member.getName())
                    .append(" deleted successfully")
                    .toString();
        } catch (NoSuchElementException e) {
            logger.error("Error: Member not found with ID: {}", id, e);
            return "Error: Member not found with ID: " + id;
        }

    }
}
