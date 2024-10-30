<%--
  Created by IntelliJ IDEA.
  User: bachLH
  Date: 10/29/2024
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQ Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .highlight {
            color: red;
            font-weight: bold;
        }

        .response-text {
            color: green;
        }

        .statistics-box {
            border: 1px solid #ccc;
            padding: 15px;
            border-radius: 5px;
            background-color: #f8f9fa;
        }

        .question-section, .answer-section {
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 5px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<%@include file="../Shared/navbar.jsp" %>
<div class="container mt-4">
    <!-- Page Title -->
    <div class="d-flex justify-content-between">
        <h4>CHI TIẾT HỎI ĐÁP, KIẾN NGHỊ</h4>
        <!-- Statistics Sidebar -->
        <div class="statistics-box">
            <h6>Thống kê</h6>
            <p>Số câu hỏi đã trả lời: <b>4</b></p>
            <p>Số câu hỏi trả lời chậm: <b>0</b></p>
        </div>
    </div>

    <!-- Question Detail -->
    <c:set var="dateFormat" value="dd/MM/yyyy HH:mm"/>
    <div class="question-section mt-4">
        <p><strong>Câu hỏi:</strong> <span class="highlight">${question.title}</span></p>
        <p><strong>Người hỏi:</strong> ${question.user.firstName} ${question.user.lastName}</p>
        <%--        <p><strong>Địa chỉ:</strong> Yên Thắng - Ý Yên - Nam Định, Xã Yên Thắng, Huyện Ý Yên, Tỉnh Nam Định</p>--%>
        <p><strong>Ngày hỏi:</strong>
            <fmt:formatDate value="${question.askDate}" pattern="${dateFormat}" var="formattedStartDate"/>
            ${formattedStartDate}
        </p>
        <p><strong>Nội dung câu hỏi</strong></p>
        <p>${question.content}</p>
    </div>

    <c:if test="${action == 4}">
        <div class="answer-section mt-3">
            <form method="post" action="<%=request.getContextPath()%>/hoidap/details">
                    <div class="card-body">
                        <h5 class="card-title">trả lời</h5>
                        <input name="quesId" type="hidden" class="form-control"
                               value="${question.id}">

                        <div class="my-3">
                            <input name="ansTitle" type="text" class="form-control" id="questionTitle"
                                   placeholder="Enter question title" value="${question.title}">
                        </div>

                        <div class="mb-3">
                            <select id="department" name="ansDepartment" class="form-select" required>
                                <option selected>--Chọn đơn vị trả lời--</option>
                                <c:forEach items="${departments}" var="de">
                                    <option value="${de.id}">${de.departmentName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <textarea name="ansContent" class="form-control" id="answerContent" rows="3"
                                      placeholder="Enter the content of the answer"></textarea>
                        </div>
                        <div class="row mx-1">
                            <!-- Submit Button -->
                            <button type="submit" class="col-auto btn btn-primary">Submit Answer</button>
                            <!-- Back Button -->
                            <a class="btn btn-outline-secondary col-auto mx-2"
                               href="<c:url value='/hoidap/list'/>"
                            >Huỷ</a>
                            </div>
                        </div>
            </form>
        </div>

    </c:if>

    <c:forEach items="${question.answerList}" var="ans" varStatus="status">
        <!-- Answer Detail -->
        <div class="answer-section mt-3">
            <p><strong>Câu trả lời số:</strong> ${status.index + 1}</p>
            <p><strong>Trả lời câu hỏi:</strong> <span class="highlight">${question.title}</span></p>
            <p><strong>Đơn vị trả lời:</strong> ${question.department.departmentName}</p>
            <p><strong>Ngày trả lời:</strong>
                <fmt:formatDate value="${ans.answerDate}" pattern="${dateFormat}" var="ansDate"/>
                    ${ansDate}
            </p>
            <p><strong>Nội dung câu trả lời</strong></p>
            <p class="response-text">${ans.content}</p>
        </div>
    </c:forEach>

    <!-- Back Button -->
    <div class="mt-3">
        <a class="btn btn-primary"
           href="<c:url value='/hoidap/list'/>"
        >Quay lại</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

