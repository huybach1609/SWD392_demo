<%--
  Created by IntelliJ IDEA.
  User: bachLH
  Date: 10/29/2024
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ & Suggestions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .nav-tabs .nav-link.active {
            color: #ff9900;
            font-weight: bold;
        }
    </style>
</head>
<body>
<%@include file="../Shared/navbar.jsp" %>
<div class="container mt-4">
    <!-- Tab Navigation -->
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="#">Hỏi đáp kiến nghị</a>
        </li>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="#">Câu hỏi thường gặp</a>--%>
<%--        </li>--%>
    </ul>

    <!-- Question Form Section -->
    <div class="mt-3">

        <h5>Đặt câu hỏi</h5>
        <div class="row">
            <c:if test="${!isStaff}">
            <div class="col-md-8">

                <form method="post" action="<%=request.getContextPath()%>/hoidap/list">
                    <div class="mb-3">
                        <input type="text" name="title" class="form-control" placeholder="Nhập tiêu đề câu hỏi, kiến nghị" required>
                    </div>
                    <div class="mb-3">
                        <textarea name="content" class="form-control" rows="3" placeholder="Nhập nội dung câu hỏi, kiến nghị" required></textarea>
                    </div>
                    <div class="mb-3">
                        <select name="departmentId" class="form-select" required>
                            <option selected>--Chọn đơn vị nhận--</option>
                            <c:forEach items="${departments}" var="de">
                                <option value="${de.id}">${de.departmentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-send-fill"></i> Gửi câu hỏi
                    </button>
                </form>
            </div>
            </c:if>
            <!-- Statistics Section -->
            <div class="col-md-4">
                <div class="border p-3">
                    <h6>Thống kê</h6>
                    <p>Số câu hỏi đã trả lời: <b>${num1}</b></p>
                    <p>Số câu hỏi trả lời chậm: <b>${num2}</b></p>
                </div>
            </div>
        </div>
    </div>

    <!-- Questions List Section -->
    <div class="mt-4">

        <h5>Danh sách hỏi đáp, kiến nghị</h5>
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead class="table-light">
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Câu hỏi</th>
                    <th scope="col">Người hỏi</th>
                    <th scope="col">Ngày trả lời</th>
                    <th scope="col">Đơn vị trả lời</th>
                    <c:if test="${isStaff}">
                        <th scope="col">Action</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:set var="dateFormat" value="dd/MM/yyyy" />
                <c:forEach items="${questions}" var="q">
                    <tr>
                        <td>${q.id}</td>
                        <td>
                            <a class="link-offset-2 link-underline link-underline-opacity-0"
                               href="<c:url value='/hoidap/details?questionId=${q.id}&action=1'/>">
                                    ${q.title}
                            </a>
                        </td>
                        <td>${q.sender.firstName} ${q.sender.lastName}</td>
                        <td>
                            <fmt:formatDate value="${q.askDate}" pattern="${dateFormat}" var="formattedStartDate" />
                            ${formattedStartDate}
                        </td>

                        <td>${q.department.departmentName}</td>
                        <c:if test="${isStaff}">
                            <td>
                                <a class="link-offset-2 link-underline link-underline-opacity-0"
                                   href="<c:url value='/hoidap/details?questionId=${q.id}&action=4'/>">
                                    trả lời
                                </a>
                            </td>
                  user </c:if>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Pagination -->
        <div class="d-flex justify-content-between align-items-center">
            <div>
                Hiển thị từ 1 đến 4 trong tổng số 4 kết quả
            </div>
            <nav>
                <ul class="pagination">
                    <li class="page-item disabled"><a class="page-link" href="#">«</a></li>
                    <li class="page-item active"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">»</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<!-- Bootstrap JS and Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
