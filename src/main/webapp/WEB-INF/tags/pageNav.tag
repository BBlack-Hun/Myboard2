<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="startPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="endPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="totalPage" required="true" type="java.lang.Integer" %>

<ul class="pagination">
  	<li data-page=1>
  		<a href="index?page=1&perPageNum=10">&lt;&lt;</a>
  	</li>
	<c:if test="${startPage > 1 }">
	  	<li data-page=${startPage -1 }>
	  		<a href="index?page=${startPage - 1 }&perPageNum=10">&lt;</a>
	  	</li>
  	</c:if>
	<c:forEach begin="${startPage }" end="${endPage }" var="cnt">
  	<li ${(page == cnt)?"class=\"active\"":"" } 
  	 data-page=${cnt }>
  		<a href="index?page=${cnt }&perPageNum=10">${cnt}</a>
  	</li>
	</c:forEach>
	<c:if test="${endPage < totalPage }">
	  	<li data-page=${endPage + 1 }>
	  		<a href="index?page=${endPage + 1 }">&gt;</a>
	  	</li>
  	</c:if>
  	<li data-page=${totalPage }>
  		<a href="index?page=${totalPage }">&gt;&gt;</a>
  	</li>
</ul> 