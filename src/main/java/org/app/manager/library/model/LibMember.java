package org.app.manager.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class LibMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String email;
    @NotNull
    private LocalDateTime membershipDate;

    @OneToMany(
            mappedBy = "libraryMember",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<BorrowRecord> borrowRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDateTime membershipDate) {
        this.membershipDate = membershipDate;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", membershipDate=" + membershipDate +
                ", borrowRecords=" + borrowRecords +
                '}';
    }
}
