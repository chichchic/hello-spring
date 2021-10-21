# 스프링과 스프링 부트의 차이

## Spring Boot
쉽게 만들고, 단독적으로 만들 수 있으며, 상용화 수준까지 단순하게 만들수 있고
스프링 기반 어플리케이션을 만들어준다.

내장 웹서버(tomcat)가 있기 때문에 서버 구동시간이 매우 단순해지며, config에서 jetty등으로 바꿀 수 있다.

security, data JPA 등의 다른 스프링 프레임워크 요소를 쉽게 사용할 수 있음.
**dependency**

스프링은 가 너무 길고, 버전 정보까지 전부 직접 설정해야하나

스프링 부트는 보다 짧게 관리가 가능하며 버전 관리를 권장 버전으로 자동 설정해준다.

**configuration**

스프링 부트는 application.properties(application.yml)만 설정해주면 됨

# 라이브러리 살펴보기

maven, gradle은 라이브러리간의 의존성을 관리해준다.

**tomcat embe**

과거에는 WAS를 직접 서버에 설치 한 후 자바 코드를 밀어넣는 방식 (웹서버와 개발 코드가 분리)

지금은 embeded되어있는 상태로 build한 후 올리면 완료된다.(내장 서블릿 컨테이너 덕분)

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
1. 컨트롤러: 웹 MVC의 컨트롤러 역할. 외부 요청을 받는다.
2. 서비스: 핵심 비지니스 로직 구현. method 이름을 보다 비지니스에 가깝게 설정.
3. 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리. method 이름을 보다 데이터 관리에 가깝게 설정.
4. 도메인: 비지니스 도메인 객체. 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장되고 관리됨

만약 아직 데이터 저장소가 정해지지 않았을 경우 repository를 interface로 만들어 구현 클래스를 변경할 수 있도록 설계한다.

# 테스트 코드 작성
given when then 으로 나누어서 작성하면 긴 테스트 코드일 경우 보다 명확히 보인다.
(상황에 따라 추가 변형)

# 서비스 빈과 의존관계
@Controller 어노테이션이 명시되어 있을 경우 스프링 컨테이너가 컨트롤러를 생성해서 관리한다.
@Autowired 어노테이션이 명시되어 있을 경우 Controller와 자동으로 연결시켜준다.

컨테이너에서 스프링 빈을 등록할 때 기본으로 싱글톤으로 등록된다.(유일하게 하나만 등록)
따라서 같은 스프링 빈이면 모두 같은 인스턴스이다. (싱글톤이 아니게 설정도 가능)

## 등록 방법
1. 컴포넌트 스캔과 자동 의존관계 설정

@Component 어노테이션이 있으면 스프링 빈으로 자동 등록된다.(@Controller도 @Component의 일종)

단, @SpringBootApplication가 명시된 package의 하위 package에서만 동작한다.

2. 자바 코드로 직접 스프링 빈 등록하

@Configuration 어노테이션을 가진 클래스를 만든 후 @Bean 어노테이션을 통해 각각 등록해준다.


정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
그리고 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야하면 설정을 통해 스프링 빈으로 등록한다.(직접 등록의 장점)

@Autowired를 통한 DI는 스프링이 관리하는 객체에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.

### 번외) DI
1. 생성자 주입: 생성자를 통해서 들어오는 형태. 가장 좋은 방법(의존 관계가 실행중에 동적으로 변하는 경우가 없으므로)
2. 필드 주입: 필드 자체에 @Autowired(좋은 방법 x)
3. setter 주입: setter에 @Autowired. 단점: 누군가가 member service를 호출할때 public하게 되야하는데 이때 노출되므로 문제가 발생할 수 있다.

**JDBC**
application과 database를 연결할 때 사용하는 기술
스프링 JdbcTemplate을 통해 보다 편리하게 사용할 수 있다.
**JPA**
등록 수정 삭제등의 쿼리를 객체없이 관리할 수 있다.
스프링에서 JPA를 간편하게 사용할 수 있도록하는 '스프링 데이터 JPA'도 있다. 