# Full Stack Todo App

A professional Todo App built with **React.js**, **Tailwind CSS**, **Spring Boot**, and **MySQL**. Includes JWT authentication, secure REST APIs.


## Features

- User registration and login with JWT authentication
- Add, update, delete todos
- Mark todos as completed
- Only logged-in users can manage their todos
- UI with Tailwind CSS
- Secure handling of credentials (backend `.properties`, frontend `.env`)


## Tech Stack

| Layer | Technology |
|------------ |-------------------------------|
| Frontend | React.js, Tailwind CSS |
| Backend | Spring Boot, Spring Security |
| Database | MySQL |
| Authentication | JWT (JSON Web Token) |
| Build Tools | Maven (Backend), Node.js/NPM (Frontend) |


## Project Structure

fullstack-todo-app/
│
├── backend/
│ ├── src/main/java/... # Controllers, Services, Models, Repositories
│ ├── src/main/resources/
│ │ └── application.properties.example # Example config
│ ├── target/ # Compiled files (ignored in Git)
│ └── pom.xml
│
├── frontend/
│ ├── src/
│ │ ├── pages/ # Pages (Login, Register, TodoList)
│ │ └── api/ # API calls
│ ├── public/
│ ├── build/ # Production build (ignored in Git)
│ ├── package.json
│ ├── tailwind.config.js
│ └── .env.example
│
└── README.md


## Installation / Setup

### Backend
1. Clone repo: `git clone https://github.com/yasminMurthuja/fullstack-todo-app.git`
2. Go to backend: `cd fullstack-todo-app/backend`
3. Create `application.properties` from example:
`cp src/main/resources/application.properties.example src/main/resources/application.properties`
4. Update MySQL credentials in `application.properties`
5. Build and run:
6.Backend runs at: `http://localhost:8080`

### Frontend
1. Go to frontend: `cd ../frontend`
2. Install dependencies: `npm install`
3. Create `.env` from example: `cp .env.example .env`
4. Update API URL: `REACT_APP_API_URL=http://localhost:8080`
5. Run frontend: `npm start`
6. Frontend runs at: `http://localhost:3000`


## Author
Yamin Begum
GitHub: https://github.com/yasminMurthuja
Email: yasmin@gmail.com
