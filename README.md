# AdvanzAssignment
Questionnaire Assignment

To create the database run the script.sql in src/main/resources.

From the directory where the project is located
1. To build use 
mvn clean package

2. To run 
mvn spring-boot:run

3. Add questions page is hosted at http://localhost:8080/index.html. Save performs insertions and establishes the correct parent-child relationship between questions, answers and subquestions appropriately results of which can be verified through 4.

4. Rest APIs : http://localhost:8080/questions. GET pulls the list of questions in JSON format. POST to the same API saves the list of questions added through Add questions page.

Limitations/TODOs:
1. Implement Cancel button functionality .
2. Consume the the questions list obtained from the REST api so that it can be displayed in tree format.
