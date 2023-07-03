# 🚀 질문과 답변 게시판 시스템

## 🌟 목차

- [🎉 프로젝트 소개](#-프로젝트-소개)
- [🛠 사용한 도구들](#-사용-기술)
- [🚗 설치 및 시작 방법](#-설치-및-실행-방법)
- [🔍 주요 기능들](#-기능-설명)
- [💭 프로젝트 후기](#-프로젝트-후기)
- [📄 기타 정보](#-기타-정보)

## 🎉 프로젝트 소개

👩‍💻 본 프로젝트는 사용자 간 질문과 답변을 공유하는 게시판입니다. 스프링과 타임리프를 기반으로 회원 관리, 질문/답변 CRUD 및 추천 기능 등을 제공합니다.

## 🛠 사용 도구

### 언어

- `Java 17`

### 개발 도구

- `IntelliJ IDEA`

### 데이터베이스

- `MySQL`

### 프레임워크

- `Spring MVC`
- `Spring Boot 3.1.4`

### 라이브러리

- `Thymeleaf`
- `Bootstrap`
- `simpleMDE`

## 🚗 설치 및 시작 방법

```plaintext
# 📦 레포지토리 클론
git clone https://github.com/yumXD/spring-board.git

# 📂 디렉토리로 이동
cd spring-board

# 📚 MySQL 환경설정
1. Mysql 설치
2. 설치된 MySQL에 접속하여 아래의 SQL 명령어를 실행합니다.

create database board default character set utf8 collate utf8_general_ci;
이를 통해 'board'라는 이름의 데이터베이스가 생성됩니다.

# 🚀 의존성 설치
build

##🔥 의존성 설치 중 오류 발생 시 대처 방안
setting → Enable annotation processing

Build, Exeution, Deployment → Build Tools → Build and run using → Intellij IDEA 변경
Build, Exeution, Deployment → Build Tools → Run tests using → Intellij IDEA 변경
Build, Exeution, Deployment → Build Tools → Gradle JVM → Java 17 이상 변경
Project Structure → SDK → Java 17 이상 변경

# 🎉 애플리케이션 시작!
Run BoardApplication
```

## 🔍 주요 기능들

📝 **회원가입 & 로그인/로그아웃**: 깔끔하게 회원정보를 관리해요!

🖌 **질문 & 답변 CRUD**: 마음껏 질문하고 답변하세요!

💬 **답변 앵커 스크롤**: 답변을 단 후 해당 답변 위치로 바로 이동해요!

💖 **추천 기능**: 유용한 질문과 답변에는 좋아요를 눌러주세요!

📝 **마크다운 에디터**: simpleMDE로 텍스트를 아름답게 꾸며 작성하세요!

🎨 **레이아웃 & 디자인**: 타임리프와 부트스트랩으로 깔끔하고 모던한 UI를 제공해요!

## 💭 프로젝트 후기

🎯 클론 코딩을 통해 시작한 이 프로젝트는 제 개발 능력을 한 단계 끌어올리는 중요한 계기가 되었습니다. 원본 코드와 기능을 참조하며 진행한 경험은 마치 프로 개발자의 작업을 직접 들여다보는 듯한 느낌이었습니다.

🚀 스프링, 타임리프, MySQL 등의 기술 스택과 깊은 친분을 맺을 수 있었고, 기존에 알고 있던 지식의 틀을 넘어서 실제 서비스에서는 어떻게 동작하는지 체감할 수 있었어요.

🔧 클론 코딩 과정에서도 물론 도전과제가 적지 않았습니다. 설정 오류나 의존성 문제를 직면했을 때, 그 문제를 원본과 비교하며 해결하는 과정은 매우 흥미로웠습니다.

🎨 디자인 측면에서도, 부트스트랩과 타임리프를 사용하여 사용자 친화적인 UI를 재현하려고 노력했고, 이 과정에서 디자인의 중요성을 다시금 깨달았습니다.

📝 마크다운 에디터인 simpleMDE의 도입은 사용자에게 좀 더 직관적이고 편리한 작성 환경을 제공하게 만들었습니다.

🤝 전체적으로, 클론 코딩을 통해 기존의 서비스를 재해석하고, 그 안에서 내 스타일과 아이디어를 더하는 작업은 매우 흥미로웠습니다. 이를 통해 새로운 관점에서 기술과 기능을 바라보게 되었고, 앞으로의 개발 과정에 큰 도움이 될 것 같아요!

## 📄 기타 정보

📘 이 레포지토리는 [E-book](https://wikidocs.net/book/7601)에서 배운 내용을 바탕으로 참고하여 만든 질문과 답변 게시판(클론 코딩)이에요!
<br>
📘 원본 깃허브 프로젝트는 [여기](https://github.com/pahkey/sbb3)에서 볼 수 있어요.
