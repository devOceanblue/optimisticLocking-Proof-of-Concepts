# Proof of Concepts: Optimistic Locking with Spring Data JPA, @Query, and QueryDSL

## Overview

This repository showcases a collection of Proof of Concepts (PoCs) demonstrating the use of optimistic locking in Spring
Data JPA, enriched with Java Persistence Query Language (@Query) and QueryDSL. It's designed as an educational resource
for developers to understand and implement optimistic locking strategies in Spring-based applications, ensuring data
integrity and consistency in environments with concurrent data access.
An optimistic lock is usually checked by including a restriction in a SQL update or delete statement. If the database
reports that zero rows were updated, it is inferred that another transaction has already updated or deleted the row, and
the failure of the optimistic lock is reported via an OptimisticLockingFailureException.

## Objectives

- **Examine Optimistic Locking in Spring Data JPA**: Provide practical examples and tests demonstrating the behavior of
  optimistic locking within the context of Spring Data JPA.
- **Highlight Limitations with @Query and QueryDSL**: The main feature of optimistic locking is to prevent data
  conflicts in concurrent access scenarios by using a version field to track changes. In standard repository methods
  that deal with entities, this version field is automatically included in SQL queries. However, with custom queries
  written using @Query or QueryDSL, this automatic inclusion is absent. Consequently, the optimistic locking mechanism
  is not engaged, and the application will not throw OptimisticLockingFailureException if a concurrent modification
  occurs.
- **Educational Resource for Developers**: Serve as a comprehensive guide for developers to understand the nuances and
  specific scenarios where optimistic locking is effectively managed by Spring Data JPA.

## Getting Started

Clone the repository: `git clone https://github.com/devOceanblue/optimisticLocking-Proof-of-Concepts.git`

```shell
docker-compose build --no-cache
```

now you are all set.

## Conclusion

Optimistic locking is a powerful tool for ensuring data integrity and consistency in environments with concurrent data
access. It's important to understand the nuances and limitations of optimistic locking, especially when using @Query and
QueryDSL.
The effective use of optimistic locking in Spring Data JPA, powered by Hibernate, is evident when observing the
generated SQL queries. The automatic inclusion of the version column in update and delete queries for methods that deal
with entity parameters underscores the mechanism's role in managing concurrent data access. However, its absence(version
column) in
batch operations and custom queries highlights the need for awareness and careful handling of these cases to maintain
data integrity in applications dealing with concurrent transactions.

## License

This project is licensed under the [MIT License](LICENSE). For more details, see the LICENSE file in this repository.

## References

- Hibernate
  Documentation: [Optimistic Locking](https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#locking-optimistic)
- Hibernate JavaDoc: https://docs.jboss.org/hibernate/orm/6.4/javadocs/org/hibernate/annotations/OptimisticLocking.html
- Spring Data
  JavaDoc: https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html 