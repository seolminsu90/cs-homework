### 구성
- Java Spring boot
- Vuejs

### 준비물
- Jdk
- Node
- Npm
- Maven

### 구축
```bash
# Frontend dev server ON
git clone https://github.com/seolminsu90/cs-homework.git
cd cs-homework-frontend
npm install
npm run dev

# Backend dev server ON
cd cs-homework-backend
mvn clean package
java -jar .\target\cs-question-api-0.0.1-SNAPSHOT.jar
```

### 확인
- http://localhost:8080/
