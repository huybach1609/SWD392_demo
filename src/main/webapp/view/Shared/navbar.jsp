<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: bachLH
  Date: 10/30/2024
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>

<style>
    .nav-hover:hover {
        background-color: #4DB8FF;
        color: #f8f9fa;
    }

    .active {
        background-color: #4DB8FF;
        color: #CCE6FF;
    }

    .link {
        text-decoration: none;
    }
</style>
<div class="row p-3" style="background-color: #035397">
    <div class="col-1">
    </div>
    <a href="<c:url value="/"/>" class="col-6 ">
        <img src="<c:url value="/image/dvclogo.png"/>" height="75px"/>
    </a>
    <div class="col">
    </div>
</div>


<c:set var="navIndex" value="${navIndex != null ? navIndex : 0}" scope="request"/>
<div class="row " style="font-size: 13px; padding-left:  10px ; background-color: #CCE6FF">
    <a class="col-auto py-2 nav-hover link ${0 == navIndex ? 'active' : ''} "
       href="<c:url value="/"/>"
    >DICH VU CONG TRUC TUYEN</a>
    <a class="col-auto py-2 nav-hover link ${1 == navIndex ? 'active' : ''} ">QUAN LY THONG TIN</a>
    <a class="col-auto py-2 nav-hover link ${2 == navIndex ? 'active' : ''} ">LICH SU GIAO DICH</a>
    <a class="col-auto py-2 nav-hover link ${3 == navIndex ? 'active' : ''} "
       href="${pageContext.request.contextPath}/hoidap/list"
    >HOI DAP KIEN NGHI</a>
</div>