package org.app.manager.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonBackReference
    @JsonIgnore
    private LibBook libraryBook;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @JsonBackReference
    @JsonIgnore
    private LibMember libraryMember;

    @NotNull
    private LocalDateTime borrowDate;
    @NotNull
    private LocalDateTime returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate.plusDays(30).with(LocalTime.of(20,0));
    }
    public LibBook getLibraryBook() {
        return libraryBook;
    }

    public void setLibraryBook(LibBook libraryBook) {
        this.libraryBook = libraryBook;
    }

    public LibMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "id=" + id +
                ", bookId=" + (libraryBook != null ? libraryBook.getId() : null) +
                ", memberId=" + (libraryMember != null ? libraryMember.getId() : null) +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
