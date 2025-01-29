# Library Management System

This is a Library Management System built with Spring Boot. It allows you to manage books, members, and borrow records.

## Features

- Manage books (add, list, find, delete, update)
- Manage members (add, list, find, delete, update)
- Manage borrow records (borrow, return, list, find, delete, update)
- Search books by title, author, or ISBN
- Generate reports for borrowed books

## Technologies Used

- Java
- Spring Boot
- Maven
- SLF4J for logging
- H2 Database for development
- PostgreSQL for production

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/jt-rakuten/library-management-system.git
   cd library-management-system
   ```

2. Build the project:
   ```sh
   mvn clean install
   ```

3. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Usage

### Commands

#### Books
- **List all books**:
  ```sh
  listBooks
  ```
- **Find a book by ID**:
  ```sh
  findBook --id <bookId>
  ```
- **Add a new book**:
  ```sh
  addBook --title <title> --author <author> --isbn <isbn> --publicationYear <year>
  ```
- **Update a book by ID**:
  ```sh
  updateBook --id <bookId> --title <title> --author <author> --isbn <isbn> --publicationYear <year>
  ```
- **Delete a book by ID**:
  ```sh
  deleteBook --id <bookId>
  ```

#### Members
- **List all members**:
  ```sh
  listMembers
  ```
- **Find a member by ID**:
  ```sh
  findMember --id <memberId>
  ```
- **Add a new member**:
  ```sh
  addMember --name <name> --email <email>
  ```
- **Update a member by ID**:
  ```sh
  updateMember --id <memberId> --name <name> --email <email>
  ```
- **Delete a member by ID**:
  ```sh
  deleteMember --id <memberId>
  ```

#### Borrow Records
- **List all borrow records**:
  ```sh
  listBorrowRecords
  ```
- **Find a borrow record by ID**:
  ```sh
  findBorrowRecord --id <recordId>
  ```
- **Borrow a book**:
  ```sh
  borrowBook --bookId <bookId> --memberId <memberId>
  ```
- **Return a book**:
  ```sh
  returnBook --id <recordId>
  ```
- **Update a borrow record by ID**:
  ```sh
  updateBorrowRecord --id <recordId> --bookId <bookId> --memberId <memberId> --borrowDate <date> --returnDate <date>
  ```
- **Delete a borrow record by ID**:
  ```sh
  deleteBorrowRecord --id <recordId>
  ```

## Logging

Logging is implemented using **SLF4J**. Logs are generated for various operations at different levels (info, error). This helps in monitoring and debugging the application efficiently.

## Exception Handling

Custom exceptions are used to handle specific error scenarios. Global exception handling is implemented using `@ControllerAdvice`, ensuring consistent error responses across the application.

---