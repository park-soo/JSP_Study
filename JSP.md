## JSP(아래의 내용들 복습하여 채워넣기.)

## JSP(Java Server Pages)란

-   HTML 코드에 Java 코드를 넣어 동적웹페이지를 생성하는 웹 어플리케이션 도구이다.

-   JSP가 실행되면 자바 Servlet으로 변환되며 웹 어플리케이션 서버에서 동작되면서 필요한 기능을 수행하고 그렇게 생성된 데이터를 웹페이지와 함께 클라이언트로 응답한다.

### JSP 와 서블릿

-   JSP 와 서블릿의 차이점은 결과적으로 하는일은 동일하지만
-   JSP 는 HTML 내부에 JAVA 소스코드가 들어감으로 인해 HTML 코드를 작성하기 간편하다는 장점이있으며
-   서블릿은 자바코드내에 HTML 코드가 있어서 읽고 쓰기가 굉장히 불편하기 때문에 작업의 효율성이 떨어진다.

지시어

### page

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Person" %>
```

▶ language : 스크립트 언어의 유형을 정해주는 속성입니다. 기본 설정 값으로는 java입니다.

▶ contentType : MIME 형식을 지정해주고 캐릭터셋을 설정해줍니다.

▶ pageEncoding : contentType과 동일한 기능을 하지만 "해당 JSP 파일을 UTF-8로 인코딩 하겠다"를 뜻하며 contentType에 사용된 charset은 "클라이언트(웹브라우저)가 받아볼 페이지가 UTF-8 형식이다"를 뜻합니다.

이외에도 여러가지 지시어 속성이 있으며, 몇가지 더 살펴보도록하겠습니다.

▶ import : JSP 파일 내에서 외부 자바 패키지나 클래스 불러올 때 사용합니다.

### include

-   해당 JSP 파일 내에 다른 HTML파일이나 JSP 페이지를 삽입할 수 있게 도와주는 기능을 하는 지시어 입니다.

```jsp
<%@ include file="삽입할 파일 이름" %>

```

내장 객체(TOMCAT)

-   request: 클라이언트의 HTTP요청 정보를 저장한 객체(HTTP 헤더 정보, 파라미터 등)
-   response :HTTP요청에 대한 응답 정보를 저장한 객체
-   out : 응답 페이지 전송을 위한 출력 스트림 객체
-   application : 응답페이지 전송을 위한 출력 스트림 객체
-   exception : 예외 처리를 위한 객체
-   session : 클라이언트의 세션 정보를 저장한 객체
-   pageContext : 페이지 실행에 필요한 컨텍스트 정보를 저장한 객체
-   page : 해당 페이지 서블릿 객체(인스턴스)
-   config : 해당 페이지의 서블릿 설정 정보(초기화 정보)를 저장한 객체

## request.getRequestDispatcher

-   request 보내고, forward뒤에 경로 에 request와 response를 둘다 떠넘긴다.
-   request받은걸로 response를 보낸다.
-   dispatcher가 계속 있으면 계속 계속 전달 전달 전달 된다.

## response.sendRedirect

-   request response 끝났기때문에 새 연결이 시작

## JDBC(JavaDataBaseConnectivity)

## execute.Query

1. 수행결과로 ResultSet 객체의 값을 반환합니다.
2. SELECT 구문을 수행할 때 사용되는 함수입니다.

```java
pstmt = con.prepareStatement("SELECT ID, NAME FROM SAMPLE_TABLE");
ResultSet rs = pstmt.executeQuery();

while (rs.next())
{
    System.out.println("ID = " + rs.getInt(1) + ", NAME = " + rs.getString(2));
}

ID = 100, NAME = Jerry
```

## execute.Update

1. 수행결과로 Int 타입의 값을 반환합니다.
2. SELECT 구문을 제외한 다른 구문을 수행할 때 사용되는 함수입니다.

```java
pstmt = con.prepareStatement("UPDATE SAMPLE_TABLE SET NAME=? WHERE ID = ?");
pstmt.setString(1, "Park");
pstmt.setInt(2, 100);
int ret = pstmt.executeUpdate();
System.out.println("Return : " + ret );

Return : 2
```

## Action Tag

JSP 표준태그<br>
XML 표준문법(반드시 종료 태그. 태그 사이 주석X)<br>
표현식(<%= %>)

```jsp
<jsp:include> : 외부 파일을 현재 파일에 포함
지시어 <%@ include file="path" %>
액션태그 지시어
표현식 가능 불가능
결과만 포함 하나의 페이지로 포함
변수 별개 하나의 페이지 변수
page 공유X 공유O


<jsp:forward> : 다른 페이지로 요청(request)를 넘김<br>
request.getRequestDispatcher().forward(request, response)<br>
<jsp:useBean>,<jsp:setProperty>,<jsp:getProperty><br>
: 자바빈즈 생성 값 할당 불러오기<br>
<jsp:useBean id="구분자" class="대상클래스" scope="기본값 page/저장될 영역"/><br>
<jsp:setProperty name="자바빈즈 id" property="속성명(멤버변수명)" value="저장값"/><br>
<jsp:getProperty name="자바빈즈 id" property="속성명(멤버변수명)"/><br>
<jsp:param> : 다른 페이지로 매개변수 전달<br>
<jsp:include> <jsp:forward> 함께 사용<br>
화면이동<br>
<jsp:forward>
requestDispatcher().forward()<br>
javascript location.href() , location.replace<br>
response.sendRedirect<br>

<meta http-equiv=refresh content="시간; url=목적지"><br>
```

## EL

EL<br>
Expression language 표현 언어

<%= %>

변수나 메서드의 값을 출력할 때 사용하는 스크립트 언어<br>
4가지 영역(page, request, session, appliction) 속성 값<br>
내장 객체의 속성을 사용<br>
산술, 비교, 논리 연산 호출 사용<br>
자바에 정의된 메서드 호출 가능<br>

${속성}<br>
액션태그나 JSTL 혼용 가능<br>
jsp 스크립트 요소(선언부, 표현식, 스크립틀릿) 혼용 불가
