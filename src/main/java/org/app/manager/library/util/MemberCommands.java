package org.app.manager.library.util;

import org.app.manager.library.model.Member;
import org.app.manager.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
public class MemberCommands {

    @Autowired
    private MemberService memberService;

    @ShellMethod("List all members")
    public List<Member> listMembers() {
        return memberService.findAllMembers();
    }

    @ShellMethod("Find a member by ID")
    public Member findMember(@ShellOption int id) {
        try {
            return memberService.findMemberById(id);
        } catch (NoSuchElementException e) {
            System.err.println("Error: Member not found with ID: " + id);
            return null;
        }
    }

    @ShellMethod("Add a new member")
    public Member addMember(
            @ShellOption String name,
            @ShellOption String email
    ) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            System.err.println("Error: Name and email must not be empty");
            return null;
        }
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setMembershipDate(LocalDate.now());
        return memberService.saveMember(member);
    }

    @ShellMethod("Delete a member by ID")
    public String deleteMember(@ShellOption int id) {
        try {
            Member member = memberService.findMemberById(id);
            memberService.deleteMemberById(id);
            return new StringBuilder()
                    .append("Member ")
                    .append(member.getName())
                    .append(" deleted successfully")
                    .toString();
        } catch (NoSuchElementException e) {
            return "Error: Member not found with ID: " + id;
        }

    }
}
