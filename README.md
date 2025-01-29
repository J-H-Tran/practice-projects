# Library Management System

This is a Library Management System built with Spring Boot. It allows you to manage books, members, and borrow records.

## Features

- Manage books (add, list, find, delete)
- Manage members (add, list, find, delete)
- Manage borrow records (borrow, return, list, find, delete)

## Technologies Used

- Java
- Spring Boot
- Maven
- SLF4J for logging

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
- **Find a libBook by ID**:
  ```sh
  findBook --id <bookId>
  ```
- **Add a new libBook**:
  ```sh
  addBook --title <title> --author <author> --isbn <isbn> --publicationYear <year>
  ```
- **Delete a libBook by ID**:
  ```sh
  deleteBook --id <bookId>
  ```

#### Members
- **List all members**:
  ```sh
  listMembers
  ```
- **Find a libMember by ID**:
  ```sh
  findMember --id <memberId>
  ```
- **Add a new libMember**:
  ```sh
  addMember --name <name> --email <email>
  ```
- **Delete a libMember by ID**:
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
- **Borrow a libBook**:
  ```sh
  borrowBook --bookId <bookId> --memberId <memberId>
  ```
- **Return a libBook**:
  ```sh
  returnBook --id <recordId>
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