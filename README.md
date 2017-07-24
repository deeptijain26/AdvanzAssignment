# AdvanzAssignment
Questionnaire Assignment

Three database table namely ANSWERS, QUESTIONS and ANS_SUB_QUESTIONS model the entity objects and their relations.

To create the database run the script.sql in src/main/resources.

From the directory where the project is located
1. To build use 
mvn clean package

2. To run 
mvn spring-boot:run

3. Add questions page is hosted at http://localhost:8080/index.html. Save performs insertions and establishes the correct parent-child relationship between questions, answers and subquestions appropriately results of which can be verified through 4.

4. Rest APIs : http://localhost:8080/questions. GET pulls the list of questions in JSON format. POST to the same API saves the list of questions added through Add questions page.

5. Clicking on Cancel button in Add questions page takes the user to the questions tree display page.

