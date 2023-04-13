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

## JSTL

JSP Standard tag library

````
jsp에서 빈번하게 사용하는 반복문 조건문을 처리하는 동작을 모아 표준으로 만든 태그 라이브러리
	종류					기능								접두어(prefix)
	core				변수 선언 조건문 반복문 url 처리		     c
	formatting			숫자, 날짜, 시간 포멧 지정				    fmt
	xml					xml parsing						           x
	function			컬렉션, 문자열 처리					        fn
	sql					데이터베이스 연결 및 쿼리 실행			     sql
    ```
````

## CORE

-   Catch : 자바에서 try-catch 구문과 동일한 기능(에러가 발생할 수 있는 부분에 있어서 에러 메시지를 출력하는 코드)

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<%
		int num1=200;
	%>
	<c:catch var="eMsg">
		<%
			int res = num1/0;
		%>
	</c:catch>
	예외: ${eMsg }<br/>
	<c:set var="num2" value="400"/>
	<c:catch var="eMsg">
		${"일"+num2 }
	</c:catch>
	예외:${eMsg }
</body>
```

-   Choose : 여러 조건중에 해당하는 조건에 맞는 처리를 할 수 있다. when 태그를 사용하여 현재의 조건에 부합하는지 여부를 확인하고, 최종적으로는 otherwise를 통해서 Default 처리를 할 수도 있다. 자바에에서는 CASE 와 같은 동작을 한다고 생각하면 이해하기 쉬울 것 같다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<c:set var="num" value="99"/>

	<h4>choose</h4>
	<c:choose>
		<c:when test="${num mod 2 eq 0 }">
			${num }는 짝수입니다.
		</c:when>
		<c:otherwise>
			${num }는 홀수입니다.
		</c:otherwise>
	</c:choose>
</body>
```

-   ForEach : 자바프로그램의 for 문에 해당하는 기능을 제공하는 커스텀액션이다. 즉 이 액션을 이용하면 특정 HTML코드를 일정 횟수만큼 반복해서 출력할 수 있다.
    -   향상된 for문도 있다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<h4>일반 for문</h4>
	<c:forEach var="i" begin="1" end="3" step="1">
		<p>반복${i}</p>
	</c:forEach>

	<h4>반복문 속성</h4>
	<table border="1">
	<c:forEach begin="3" end="7" var="i" varStatus="loop">
		<tr>
			<td>count:${loop.count }</td>		<!-- 반복순서 -->
			<td>index:${loop.index }</td>		<!-- ? -->
			<td>current:${loop.current }</td>	<!-- 현재값 -->
			<td>first:${loop.first }</td>		<!-- 처음인지 여부  -->
			<td>last:${loop.last }</td>			<!-- 마지막인지 여부 -->
			<td>i:${i }</td>
		</tr>
	</c:forEach>
	</table>
	<h4>enhanced(향상된) for문</h4>
	<%
		String[] rainbow={"빨","주","노","초","파","남","보"};
	%>
	<c:forEach items="<%=rainbow %>" var="color">
		<span>${color }</span>
	</c:forEach>
	<%
		String[] rainbow2={"red","orange","yellow","green","blue","navy","purple"};
	%>
		<h4>반복문 속성</h4>
	<table border="1">
	<c:forEach items="<%=rainbow2 %>" var="color" varStatus="loop">
		<tr>
			<td>count:${loop.count }</td>		<!-- 반복순서 -->
			<td>index:${loop.index }</td>		<!-- 인덱스 -->
			<td>current:${loop.current }</td>	<!-- 인덱스의 실제 요소 -->
			<td>first:${loop.first }</td>		<!-- 처음인지 여부  -->
			<td>last:${loop.last }</td>			<!-- 마지막인지 여부 -->
			<td style="color:${color};">color:${color}</td>
		</tr>
	</c:forEach>
</body>
```

-   ForTokens : 구분 기호를 이용하여 구분기호를 제외한 문자열들을 출력하고, 구분 기호를 제외하고 forEach 루프와 비슷하다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<%
		String rainbow ="red,orange,yellow,green,blue,navy,purple";
	%>
	<c:forTokens items="<%=rainbow %>" delims="," var="color">
		<span style="color:${color }">${color }</span>
	</c:forTokens>
</body>
```

-   If : 조건문.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<c:set var="number" value="100"/>
	<c:set var="string" value="JSP"/>

	<c:if test="${number mod 2 eq 0 }" var="result" scope="request">
		${number }는 짝수입니다.<br>
	</c:if>

		result:${result }<br>

	<c:if test="100">
		EL이 아닌 정수를 지정하면 false
	</c:if>

	<c:if test="${string eq 'Java' }" var="result2">
		문자열은 java입니다.<br>
	</c:if>

	<c:if test="${not result2 }">
		문자열이 java가 아닙니다.<br>
	</c:if>

	<c:if test="tRuE" var="result3">
		대소문자 구분없이 "tRuE"인 경우 true<br>
	</c:if>
		result:${result3 }<br>

	<c:if test=" ${ true } " var="result4">
		EL 양쪽에 공백이 존재하면 false <br>
	</c:if>
		result:${result4}<br>

	<c:if test="${string eq 'Java' }">
		문자열은 java입니다.<br>
	</c:if>
	<c:if test="${string eq 'JSP' }" >
		문자열은 JSP입니다.<br>
	</c:if>
</body>
```

-   Import : 동적 페이지 호출 include랑 비슷

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<c:set var="reqVar" value="park" scope="request"/>
	<c:import url="./include/OtherPage.jsp" var="contents">
		<c:param name="param1" value="JSP"/>
		<c:param name="param2" value="JAVA"/>
	</c:import>
	<h4>다른 문서 삽입하기</h4>
	${contents}
	<c:import url="./include/OtherPage.jsp">
		<c:param name="param1" value="ultra"/>
		<c:param name="param2" value="super"/>
	</c:import>
	<iframe src="./include/OuterPage.jsp" style="width:100%; height:600px;"></iframe>
</body>
```

-   Out : 자바에서의 System.out.println 메서드, JSP와 비슷한 역할을 가지고 있습니다. 어떤 값을 입력받던지 간에 콘솔이 아닌 화면에 문자열로 바꾸어서 보여주는 역할입니다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<c:set var="iTag">
		i 태그는 <i>기울임</i>을 표현합니다.
	</c:set>
	<h4>기본 사용</h4>
	<c:out value="${iTag }"/>
	<br>
	${iTag }

	<h4>escapeXml 속성</h4>
	<c:out value="${iTag }" escapeXml="false"/>
	<br>

	<c:out value="${param.name }" default="이름 없음"/>
	<c:out value="" default="빈 문자열도 값으로 처리"/>

	</body>
```

-   Redirect : response.sendRedirect()와 같이 url 지정해 특정 페이지로 리다이렉트를 시켜주는 기능을 합니다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<c:set var="reqVar" value="park" scope="request"/>
	<c:redirect url="./include/OtherPage.jsp">
		<c:param name="param1" value="orange"/>
		<c:param name="param2" value="lemon"/>
	</c:redirect>
	</body>
```

-   Remove : Scope(저장영역)에 값을 삭제할 수 있도록 해줍니다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 변수 선언 -->
<c:set var="scopeVar" value="Page value" scope="page"/>			<!-- scope default값은 page이기때문에 생략해도 가능. -->
<c:set var="scopeVar" value="Request value" scope="request"/>
<c:set var="scopeVar" value="Session value" scope="session"/>
<c:set var="scopeVar" value="Application value" scope="application"/>
<body>
	<ul>
		<li>scopeVar : ${scopeVar }</li>
		<li>requestScope.scopeVar : ${requestScope.scopeVar }</li>
		<li>sessionScope.scopeVar : ${sessionScope.scopeVar }</li>
		<li>applicationScope.scopeVar : ${applicationScope.scopeVar }</li>
	</ul>
		<c:remove var="scopeVar" scope="session"/>
	<ul>
		<li>scopeVar : ${scopeVar }</li>
		<li>requestScope.scopeVar : ${requestScope.scopeVar }</li>
		<li>sessionScope.scopeVar : ${sessionScope.scopeVar }</li>
		<li>applicationScope.scopeVar : ${applicationScope.scopeVar }</li>
	</ul>

	<c:remove var="scopeVar"/>
	<ul>
		<li>scopeVar : ${scopeVar }</li>
		<li>requestScope.scopeVar : ${requestScope.scopeVar }</li>
		<li>sessionScope.scopeVar : ${sessionScope.scopeVar }</li>
		<li>applicationScope.scopeVar : ${applicationScope.scopeVar }</li>
	</ul>
	</body>
```

-   Set : Scope(저장영역)에 값을 추가할 수 있도록 해줍니다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- 변수 선언 -->
	<c:set var="aVar" value="200"/>
	<c:set var="elVar" value="${aVar mod 7 }"/>
	<c:set var="expVar" value="<%= new Date() %>"/>
	<c:set var="tagVar">태그내 설정</c:set>

	<h3>변수 출력</h3>
	${pageScope.aVar }<br>${elVar }<br>${expVar }<br>${tagVar }<br>

	<h4>자바빈즈 생성자 사용</h4>
	<c:set var="personVar1" value='<%=new Person("지수", 22) %>' scope="request"/>
	<ul>
		<li>이름: ${requestScope.personVar1.name }</li>
		<li>나이: ${personVar1.age}</li>
	</ul>

	<h4>자바빈즈 target, property 사용</h4>
	<c:set var="personVar2" value="<%=new Person() %>" scope="request"/>
	<c:set target="${personVar2 }" property="name" value="정약용" />
	<c:set target="${personVar2 }" property="age" value="60"/>
	<ul>
		<li>이름: ${requestScope.personVar2.name }</li>
		<li>나이: ${personVar2.age}</li>
	</ul>

	<h4>List</h4>
	<%
		ArrayList<Person> pList = new ArrayList<>();
		pList.add(new Person("제임스",55));
		pList.add(new Person("william",60));
	%>
	<c:set var="personList" value="<%=pList %>" scope="request"/>
	<ul>
		<li>이름: ${requestScope.personList[0].name }</li>
		<li>나이: ${personList[0].age}</li>
	</ul>

	<h4>Map</h4>
	<%
		Map<String, Person> pMap = new HashMap<>();
		pMap.put("personArgs1", new Person("superman",65));
		pMap.put("personArgs2", new Person("batman",55));
	%>
	<c:set var="personMap" value="<%=pMap %>" scope="request"/>
	<ul>
		<li>이름: ${requestScope.personMap.personArgs2.name }</li>
		<li>나이: ${personMap.personArgs2.age}</li>
	</ul>
	</body>
```

-   Url : url을 생성해주는 기능을 할 수 있습니다. 하지만 그냥 생성해주는 것이 아니라 contextPath를 자동으로 붙여주고 url을 생성하는 것이기 때문에 서버에 올려서 contextPath가 달라져도 url을 수정할 필요가 없어집니다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<h4>url로 링크</h4>
	<c:url var="url" value="/11JSTL/include/OtherPage.jsp">
		<c:param name="param1" value="수박" />
		<c:param name="param2" value="당근" />
	</c:url>
	<a href="${url}">other page</a>
	</body>
```

## Formatting

-   데이터 타입들을 자신이 원하는 종류로 변경할수 있다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<h4>날씨 포맷</h4>
	full:<fmt:formatDate value="${today }" type="date" dateStyle="full"/><br>
	short:<fmt:formatDate value="${today }" type="date" dateStyle="short"/><br>
	long:<fmt:formatDate value="${today }" type="date" dateStyle="long"/><br>
	default:<fmt:formatDate value="${today }" type="date" dateStyle="default"/><br>

	<h4>시간 포맷</h4>
	full:<fmt:formatDate value="${today }" type="time" timeStyle="full"/><br>
	short:<fmt:formatDate value="${today }" type="time" timeStyle="short"/><br>
	long:<fmt:formatDate value="${today }" type="time" timeStyle="long"/><br>
	default:<fmt:formatDate value="${today }" type="time" timeStyle="default"/><br>

	<h4>날짜 / 시간 표시</h4>
	<fmt:formatDate value="${today }" type="both" dateStyle="full" timeStyle="full"/><br>
	<fmt:formatDate value="${today }" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>
	</body>
```

## Function

-   문자열을 처리하는 함수를 제공합니다.

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<body>
	length:	${fn:length('123123')}<br/>
	toUpperCase: ${fn:toUpperCase('sss')}<br/>
	toLowerCase: ${fn:toLowerCase('SSS') }<br/>
	substring: ${fn:substring("check",1,3) }<br/>
	substringAfter: ${fn:substringAfter('applepie','pp') }<br/>
	substringBefore: ${fn:substringBefore('applepie','pp') }<br/>
	trim: ${fn:trim(' apple pie ')}<br/>
	replace: ${fn:replace('apple phone','apple','samsung')}<br/>
	indexOf: ${fn:indexOf('applepie','e') }<br/>
	startsWith: ${fn:startsWith('applepie','e') }<br/>
	endsWith: ${fn:endsWith('applpie','e') }<br/>
	contains: ${fn:contains('applEpiE','e') }<br/>
	containsIgnoreCase: ${fn:containsIgnoreCase('applEpiE','e') }<br/>
	<c:set var='list' value="${fn:split('a,p,p,l,e,p,i,e',',') }" />
	split:
	<c:forEach var="l" items="${list}" varStatus="stat" >
	${l}
	</c:forEach>
	<br/>
	join: ${fn:join(list,',')}<br/>
	escapeXml: ${fn:escapeXml('<strong>is delicious.</strong>') }<br/>
	</body>
```

## XML

-   JSP 중심의 XML 문서 작성 및 조작 방법을 제공한다

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<body>
	<c:import url="./include/FruitList.xml" var="fruitlist" charEncoding="UTF-8" />
	${fruitlist }
	<x:parse xml="${fruitlist }" var ="flist" /><br>
	<x:out select="$flist/fruitlist/fruit[1]/name"/><br>
	<x:out select="$flist/fruitlist/fruit[1]/from"/><br>
	<x:out select="$flist/fruitlist/fruit[1]/price"/><br>
	</body>
```

## Servlet

Servlet 작성 규칙

-   import javax.servlet
-   public
-   httpServlet 상속
-   doGet 또는 doPost 반드시 오버라이딩

1. web.xml servlet 설정
2. @ annotation 설정 - 컴파일러한테 알려주는 주석

## SMTP(Simple Mail Transfer Protocol)
