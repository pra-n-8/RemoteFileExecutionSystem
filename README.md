# IT_Elites_Distributed_System



## Intro

This project helps to run .java (Java) and .py (Python) files remotly on a server. Users just have to upload the file using the frontend and wthe output of the file will be displayed as a response.

## Flow of the project
Our frontend is in Angular 14. The user is greeted by the system and asked which of the available file formats is he intrested in. Once he clicks on the intrested format, a GET call is made to backend which maintains an array of servers for different formats and fetches the entire list of services available to execute the file. The user is then asked to choose from the list of services he would like to connect to. On clicking connect he uploads the file and clicks on the submit button. This triggers the REST call and the file reaches the services the user has selected. Upon receiving the file the service executes the file and provied the output of the file as a response to the POST request. The client then shows the output in the output window to the user.
- 

## Running the project
For UI
1. Please install node.js, no specific version required.
2. Navigate to UI folder then execute the command in cmd - 
   npm i
   npm install -g @angular/cli
   ng serve
3. Install chrome extension - https://chrome.google.com/webstore/detail/allow-cors-access-control/lhobafahddgcelffkeicbaginigeejlf?hl=en
4. Navigate to DS folder and open 3 terminals and execute following 3 commands in 3 individual terminals
   mvn spring-boot:run -pl server
   mvn spring-boot:run -pl python
   mvn spring-boot:run -pl java
5. Navigate to JavaService/src/main/java/services/FilesStorageServiceImpl and change line 18 path to your
   machine's upload folder from the repo. Make sure you have double backslashes.
6. Navigate to JavaService/src/main/java/ucd/distributed/JavaService.java and change line 42 path to your
   machine's upload folder from the repo. Make sure you have double backslashes.
7. Navigate to PythonService/src/main/java/distributed/PythonService.java and change line 46 path to your
   machine's upload folder from the repo. Make sure you have double backslashes.
8. Navigate to PythonService/src/main/java/services/FilesStorageServiceImpl and change line 21 path to your
   machine's upload folder from the repo. Make sure you have double backslashes.
9. Open http://localhost:4200/ in your browser and use the system

## Link to the report
https://gitlab.com/comp30220/2022/it_elites_distributed_system/-/blob/master/Report.pdf
- 

## Link to the video
https://gitlab.com/comp30220/2022/it_elites_distributed_system/-/blob/main/Remote%20Execution%20System%20-%20IT%20Elites.mp4
