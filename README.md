### 구성
- Java Spring boot
- Vuejs
- H2 (memory0

### 준비물
- Jdk11
- Node
- Npm
- Maven

### 실행
```bash
# Frontend dev server ON
git clone https://github.com/seolminsu90/cs-homework.git
cd cs-homework-frontend
npm install
npm run dev # 포트는 8080 입니다.

# Backend dev server ON
cd cs-homework-backend
mvn clean package
java -jar .\target\cs-question-api-0.0.1-SNAPSHOT.jar # 포트는 8090 입니다.
```

- 1. 고객은 별도의 인증 인가 과정이 없기에 사전에 Id를 입력해서 저장해놓고 문의 작성 및 내 문의 조회를 하도록 유도
- 2. 동일한 문의에 여러 상담사가 동시에 할당을 요청할 수 있어 할당/답변 테이블을 분리하여 한개 이외는 저장될 수 없도록 구성

### 확인
- http://localhost:8080/
