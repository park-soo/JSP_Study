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

statement 정적
preparedStatement 동적

ActionTag

VO
DTO

DAO

Design pattern

model 1

model 2 (MVC model)
