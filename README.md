# IT_Elites_Distributed_System



## Intro

This project helps to run .java (Java) and .py (Python) files remotly on a server. Users just have to upload the file using the frontend and wthe output of the file will be displayed as a response.

## Flow of the project
Our frontend is in Angular 14. The user is greeted by the system and asked which of the available file formats is he intrested in. He then uploads the file and clicks on the submit button. This triggers the REST call and reaches the backend. Initially the backend simply relays the message to broker.The broker maintains an array of servers ready to execute files for different formats. Depending upon the type specified in the request the broker then sends the message to the respective services to execute the given file. Broker chooses the services which are first in the array. Once a service interacts with the broker, it removes it from the array and puts it back into the array when it receives a response from it. The respone is of the kind of string and is passed on by the backend to the frontend to be displayed to the user.
- 

## Running the project
For UI
1. Please install node.js, no specific version required.
2. Navigate to UI folder then execute the command in cmd - 
npm i
npm install -g @angular/cli
ng serve
3. Install chrome extension - https://chrome.google.com/webstore/detail/allow-cors-access-control/lhobafahddgcelffkeicbaginigeejlf?hl=en
4. Turn it on and then use the system.


## Link to the report

- 

## Link to the video
