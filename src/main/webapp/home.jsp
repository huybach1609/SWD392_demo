<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<!DOCTYPE html>
<html>
<head>
  <title>Hệ thống thông tin giải quyết thủ tục hành chính Bộ Giáo Dục và Đào Tạo (MOET)</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div>
  <%@include file="./view/Shared/navbar.jsp" %>
  <div>
    <label>UC-8</label>
    <a href="${pageContext.request.contextPath}/hoidap/list">hoi dap kien nghi</a>
  </div>
  <div>
    <label>UC-2</label>
    <a href="${pageContext.request.contextPath}/account/register">Dang ky</a>
  </div>
</div>
</body>
</html>
