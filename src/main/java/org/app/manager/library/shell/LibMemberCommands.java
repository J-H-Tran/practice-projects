package org.app.manager.library.shell;

import org.app.manager.library.model.LibMember;
import org.app.manager.library.service.LibMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class LibMemberCommands {

    @Autowired
    private LibMemberService libMemberService;

    @ShellMethod(key = "add-member")
    public String addMember(
            @ShellOption String name,
            @ShellOption String email
    ) {
        LibMember member = new LibMember();
        member.setFullName(name);
        member.setEmail(email);
        member.setMembershipDate(LocalDateTime.now());
        libMemberService.addMember(member);

        return "Member added: " + member;
    }

    @ShellMethod(key = "get-member-id")
    public String getMemberById(
            @ShellOption Long id
    ) {
        Optional<LibMember> member = libMemberService.getMemberById(id);

        return member.map(value -> "Member found: " + value).orElse("Member not found");
    }

    @ShellMethod(key = "list-members")
    public List<LibMember> listMembers() {
        return libMemberService.getAllMembers();
    }

    @ShellMethod(key = "remove-member")
    public String removeMember(
            @ShellOption Long id
    ) {
        if (libMemberService.getMemberById(id).isPresent()) {
            libMemberService.removeMember(id);
            return "Member removed with ID: " + id;
        } else {
            return "Member not found with ID: " + id;
        }
    }

    @ShellMethod(key = "is-membership-valid")
    public String isMembershipValid(
            @ShellOption Long memberId
    ) {
        boolean isValid = libMemberService.isMembershipValid(memberId);
        return isValid ? "Membership is valid" : "Membership is not valid";
    }
}