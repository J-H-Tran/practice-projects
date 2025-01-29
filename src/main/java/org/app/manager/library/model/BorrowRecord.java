package org.app.manager.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private LibBook book;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private LibMember libMember;

    @NotNull
    private LocalDate borrowDate;
    @NotNull
    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public LibBook getBook() {
        return book;
    }

    public void setBook(LibBook book) {
        this.book = book;
    }

    public LibMember getLibMember() {
        return libMember;
    }

    public void setLibMember(LibMember libMember) {
        this.libMember = libMember;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "id=" + id +
                ", book=" + book +
                ", libMember=" + libMember +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
