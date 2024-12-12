Instruction Manual
Empty if you download this -Lewis


Dear all
 
Please take note the following check list:
 
Part B - Assignment Checklist
 
You MUST submit the following items: (create a word file to include the details for --> item 4 to 8, create a directory and place item 1 and 2 into the directory)
 
1. JAR file (this must be tested and must be functional/working)
2. Project file (whole project file of IntelliJ)
3. External text/binary/object files (provide file path of the files in the project and the actual files)
4. Provide the version of Scene builder
5. Provide the version of JavaFX SDK
6. Provide the version of Java JDK 
7. Provide any other information about files/third party libraries that need to run your application
8. Briefly explain (steps) how your application is deployed
9. Working environment - MUST be Windows, IntelliJ IDE
10. Application type - MUST be standalone desktop application
 
*Failure to comply with the above mentioned items will result in no marks awarded for Part B.

66	MA YIBO	0370913	10	10	0370913@sd.taylors.edu.my;
66	YONG ZHEN MING	0374974	10	10	0374974@sd.taylors.edu.my;
66	LIM JING XIANG	0360940	10	10	0360940@sd.taylors.edu.my;
66	LUO HAOMING	0370518	10	10	0370518@sd.taylors.edu.my;
66	EDWIN FRANCISCO DOSS	0338928	10	10	0338928@sd.taylors.edu.my;


To implement the core functionality for User Profiles and System Login & Authentication, followed by Fitness Goals, you can structure your classes and proceed in the following manner:

Core Functionality: User Profile, System Login & Authentication
1. Model Class
User.java:
Define attributes like id, username, password, email, age, gender, etc.
Ensure encapsulation with private fields and public getters/setters.
Add methods like toString() for serialization purposes.
2. Data Access Object (DAO)
TheBetterDtoMapper.java:

Define methods such as addUser(User user), getUserByUsername(String username), and validateLogin(String username, String password).
DSAImpObj.java:

Implement the TheBetterDtoMapper.
Use a binary file to store user profiles. Example methods:
Serialize and save the User list.
Deserialize and retrieve users.
3. Controller Class
UserController.java:
Include methods to call DAO methods for:
Adding users during registration.
Validating login credentials.
4. UI Form Class
UserControllerForm.java:
Develop JavaFX/Swing forms for:
Login screen (fields for username and password).
Registration screen (fields for username, password, email, age, etc.).
Use the initialize method to load users from the serialized file.
Fitness Goals
1. Model Class
FitnessGoal.java:
Define attributes like goalId, userId, goalType (e.g., weight loss, calorie target), goalValue, and startDate.
Add methods for encapsulation and goal-related functionality.
2. Data Access Object (DAO)
Extend TheBetterDtoMapper:

Add methods like addGoal(FitnessGoal goal), getGoalsByUserId(int userId), etc.
Update DSAImpObj.java:

Implement the above methods.
Serialize goals into a separate binary file.
3. Controller Class
FitnessGoalController.java:
Manage user fitness goals:
Add, update, and retrieve goals from the DAO.
4. UI Form Class
FitnessGoalForm.java:
Create forms for:
Inputting and updating goals.
Viewing current goals.
Suggested Sequence of Work
Start with the User Model:

Create the User class with necessary fields and methods.
Test the serialization and deserialization of users using the DAO.
Develop User Login & Registration UI:

Create the forms for login and registration.
Integrate them with the UserController for functionality.
Implement Fitness Goal Feature:

Create the FitnessGoal class.
Develop DAO methods for managing goals.
Design and test the fitness goal forms.
This modular approach ensures that the core user functionalities are built and tested before moving on to features like fitness goals.






