# Course-Homeworks
Devmind full-stack course homeworks
*****************
C21 HOMEWORK
*****************
1. the app creates an H2 in memory database
2. sets ups the database schema of tables
3. sets up a connection with the "Ball don't lie" API and retrieves data about NBA players
4. maps that data from JSON objects to JAVA objects via the Jackson Object Mapper
5. using these JAVA objects sets up Hibernate entities - Team.java and Player.java
6. maps entities to corresponding tables and saves the data into the in memory database
7. sets up the repository, service and controller layers
8. sets up endpoints to implement GET, POST, PUT and DELETE methods
9. implements validation for entered data
10. implements some custom handlers for validation exceptions

Notes: 1. Further info about accessing the API: https://www.balldontlie.io/#players

*****************
C20 HOMEWORK
*****************

1. the app creates an H2 in memory database
2. sets up the database schema of tables an inserts values into those tables
3. the database tables keep track of students, classes, teachers and grades
4. the app sets up corresponding entities with their corresponding relationships
5. the app sets up repositories with custom query methods added
6. the service layer is omitted for the sake of simplicity
7. the app sets up controller with endpoints for accessing an modifying data
8. the data enered is validated and a custom exception handler is included in the "CatalogController"
9. the app implements a test for the "CatalogController" to test some of its method

Homework requirements:

1.  Create a class named "ProfesorController" to serve as REST Controller for the following endpoints:
    /profesor/{id} - GET method to return information about the teacher with the "id" ID.
    /profesor/materii/{id} - GET method to return information about the classes that the teacher with the "id" ID is teaching.
2. Create a class called "StudentController" to serve as REST Controller for the "/student/enroll?studentId={studentId}&materieId={materieId}" endpoint.
   A POST method for this endpoint will insert a correspponding entry in the "studenti_to_materii: table, to record that the sudent with the studentId ID is enrolled in the class with materieId.
3. Inside the "StudentController" class add a POST method for the "/student" endpoint with the following request body:
    {
        "nume": "Cristi",
        "prenume": "Popescu",
        "cnp": "12345",
        "adresa": {
            "strada": "Iuliu Maniu",
            "numar": "23",
            "localitate": "Oradea"
        }
    }
   The method should create a new student and save it into the database, together with the attached address.
4. Inside the "StudentController" class add a POST method for the "/student/{studentId}/adresa" endpoint with the following request body:
    {
        "strada": "Iuliu Maniu",
        "numar": "23",
        "localitate": "Oradea"
    }
   The method should update the address for the student with "studentId" (if it is found in the database) so that it contains the values sent through the request body.
5. Inside the "StudentController" class add a DELETE method for the "/student/{studentId}" endpoint.
   The method should delete the student with the "studentId" from the database, together with the associated entities (address, classes, etc.).
6. Create a class called "CatalogController" and an entity called "Nota" to include the class, the grade, the student and the date the grade was given.
   The "CatalogController" should implement endpoints for:
     a. adding a grade for a particular student and a particular class
     b. listing all grades for a particular student
     c. listing all grades for a particular student and a particular class
     d. listing all grades for a particular student inside a given time range

