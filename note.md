# 스프링과 스프링 부트의 차이

## Spring Boot
쉽게 만들고, 단독적으로 만들 수 있으며, 상용화 수준까지 단순하게 만들수 있고
스프링 기반 어플리케이션을 만들어준다.

**dependency**

스프링은 가 너무 길고, 버전 정보까지 전부 직접 설정해야하나

스프링 부트는 보다 짧게 관리가 가능하며 버전 관리를 권장 버전으로 자동 설정해준다.

**configuration**

스프링 부트는 application.properties(application.yml)만 설정해주면 됨


# 라이브러리 살펴보기

maven, gradle은 라이브러리간의 의존성을 관리해준다.

**tomcat embe**

과거에는 WAS를 직접 서버에 설치 한 후 자바 코드를 밀어넣는 방식 (웹서버와 개발 코드가 분리)

지금은 embeded되어있는 상태로 build한 후 올리면 완료된다.

따라서 단순히 gradle르 build된 jar 파일만 서버에서 실행시키면 된다.

**logging**

로그로 출력해 기록을 남겨야 에러 발견등을 쉽게 할 수 있어서 중요하다.

slf4j: 인터페이스, logback: 어떻게 로그를 남길것인가 (검색해볼것)

**test**

junit: 최근들어 5version으로 넘어가기 시작함.

mockito: 목 라이브러리

assertj: 테스트 코드를 좀 더 편하게 작성해주는 도와주는 라이브러리

spring-test: 스프링 통합 테스트 지원

# controller와 static content

관련 controller를 찾을 수 있다면 viewResolver가 resouces의 template package에서 해당 파일명.html을 찾는다.

만약, 찾을 수 없다면 static폴더에서 정적 컨텐츠를 찾아 반환한다.

**@ResponseBody**

이때는 viewResolver가 아닌 HttpMessageConverter가 동작한다.

보통은 text로 반환(StringConverter가 동작) 

객체가 오면 json형식(defualt, 여러 포맷을 다 지원해줌)로 반환(JsonConverter가 동작)

클라이언트의 HTTP Accept header와 서버 컨트롤러 반환 타입 정보 둘을 조합해서 HttpMessageConverter가 선택된다.

# 일반적인 웹 어플리케이션의 계층 구조
1. 컨트롤러: 웹 MVC의 컨트롤러 역할
2. 서비스: 핵심 비지니스 로직 구현. method 이름을 보다 비지니스에 가깝게 설정.
3. 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리. method 이름을 보다 데이터 관리에 가깝게 설정.
4. 도메인: 비지니스 도메인 객체. 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장되고 관리됨

만약 아직 데이터 저장소가 정해지지 않았을 경우 repository를 interface로 만들어 구현 클래스를 변경할 수 있도록 설계한다.
  