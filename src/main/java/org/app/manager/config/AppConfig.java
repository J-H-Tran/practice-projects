package org.app.manager.config;

import org.app.manager.database.Database;
import org.app.manager.datasource.PersonDataSource;
import org.app.manager.repository.PersonRepository;
import org.app.manager.service.PersonService;

/**
 * The AppConfig class is responsible for configuring and providing instances of various components
 * in the application. This class follows the principles of Inversion of Control (IoC) and Dependency
 * Injection (DI) to promote loose coupling, improve testability, enhance maintainability, and promote
 * reusability of components.
 */
public class AppConfig {

    /**
     * Creates and returns a new instance of the Database class.
     *
     * @return a new Database instance
     */
    public Database database() {
        return new Database();
    }

    /**
     * Creates and returns a new instance of the PersonDataSource class.
     *
     * @return a new PersonDataSource instance
     */
    public PersonDataSource personDataSource() {
        return new PersonDataSource();
    }

    /**
     * Creates and returns a new instance of the PersonRepository class, with dependencies
     * on Database and PersonDataSource.
     *
     * @return a new PersonRepository instance
     */
    public PersonRepository personRepository() {
        return new PersonRepository(database(), personDataSource());
    }

    /**
     * Creates and returns a new instance of the PersonService class, with a dependency
     * on PersonRepository.
     *
     * @return a new PersonService instance
     */
    public PersonService personService() {
        return new PersonService(personRepository());
    }
}
