# Object-Oriented Programming with Java

A Java coursework repository containing object-oriented exercises and a banking-management project with a Maven web application, servlet/JSP pages, and SQL setup scripts.

## Contents

- `banking management system/` — standalone Java classes and exception/account examples.
- `Banking Management System Project/` — Maven project with accounts, customers, DAO, servlet, JSP, and SQL scripts.
- Other experiment folders — additional class exercises.

## Run the Maven project

Use a compatible JDK and Maven installation from the project directory:

```bash
cd "Banking Management System Project"
mvn package
```

The project is a servlet/JSP application and requires a compatible servlet container and database configuration to run. Follow the SQL scripts in that folder and update connection settings for a local database. The checkout also tracks compiled `.class` files and Maven `target/` output; they are build artifacts, not source dependencies.

## Next steps

Document the supported JDK/container/database versions and add ignore rules for generated Java output. No repository-level `LICENSE` file was found, so reuse terms for project code are unspecified.
