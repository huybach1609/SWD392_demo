<%--
  Created by IntelliJ IDEA.
  User: nhokk
  Date: 10/30/2024
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Dang ky tai khoan</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
      body {
        background-color: #f0f2f5;
        font-family: 'Segoe UI', Arial, sans-serif;
      }

      .card {
        border: none;
        border-radius: 15px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
      }

      .card-body {
        padding: 2rem;
      }

      .text-primary {
        color: #1a73e8 !important;
        font-weight: 600;
        margin-bottom: 2rem;
      }

      .form-label {
        font-weight: 500;
        color: #5f6368;
      }

      .form-control, .form-select {
        border-radius: 8px;
        border: 1px solid #dadce0;
        padding: 10px 15px;
        transition: all 0.3s ease;
      }

      .form-control:focus, .form-select:focus {
        border-color: #1a73e8;
        box-shadow: 0 0 0 2px rgba(26, 115, 232, 0.2);
      }

      .btn-primary {
        background-color: #1a73e8;
        border: none;
        padding: 12px 24px;
        border-radius: 8px;
        font-weight: 500;
        transition: all 0.3s ease;
      }

      .btn-primary:hover {
        background-color: #1557b0;
        transform: translateY(-1px);
      }

      /* Add icons to input fields */
      .input-group {
        position: relative;
      }

      .input-icon {
        position: absolute;
        right: 10px;
        top: 50%;
        transform: translateY(-50%);
        color: #5f6368;
        z-index: 10;
      }

      /* Required field asterisk styling */
      .text-danger {
        font-weight: bold;
      }

      /* Responsive padding */
      @media (max-width: 768px) {
        .card-body {
          padding: 1rem;
        }
      }

      /* Animation for form submission */
      @keyframes submit-pulse {
        0% { transform: scale(1); }
        50% { transform: scale(1.05); }
        100% { transform: scale(1); }
      }

      .btn-primary:active {
        animation: submit-pulse 0.3s ease;
      }
    </style>
  </head>
  <body class="bg-light">
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card">
            <div class="card-body">
              <div class="text-center mb-4">
                <h2 class="text-primary">ĐĂNG KÝ CÔNG DÂN SỐ</h2>
              </div>

              <form method="post" action="/SWD392_demo_war/account/register">
                <div class="row mb-3">
                  <div class="col-md-4">
                    <label class="form-label">Họ <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" name="firstName" required>
                  </div>
                  <div class="col-md-4">
                    <label class="form-label">Tên đệm</label>
                    <input type="text" class="form-control" name="middleName">
                  </div>
                  <div class="col-md-4">
                    <label class="form-label">Tên<span class="text-danger">*</span></label>
                    <input type="text" class="form-control" required name="lastName">
                  </div>
                </div>

                <div class="row mb-3">
                  <div class="col-md-3">
                    <label class="form-label">Số CCCD/CMT <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" required name="idCardNo">
                  </div>
                  <div class="col-md-3">
                    <label class="form-label">Dân tộc <span class="text-danger">*</span></label>
                    <select class="form-select" required name="tribe">
                      <option disabled>--Chọn--</option>
                      <option value="1" selected>Kinh</option>
                      <option value="2">Tày</option>
                      <option value="3">Thái</option>
                      <option value="4">Hoa</option>
                      <option value="5">Khơ-me</option>
                      <option value="6">Mường</option>
                      <option value="7">Nùng</option>
                      <option value="8">HMông</option>
                      <option value="9">Dao</option>
                      <option value="10">Gia-rai</option>
                      <option value="11">Ngái</option>
                      <option value="12">Ê-đê</option>
                      <option value="13">Ba na</option>
                      <option value="14">Xơ-đăng</option>
                      <option value="15">Sán Chay</option>
                      <option value="16">Cơ-ho</option>
                      <option value="17">Chăm</option>
                      <option value="18">Sán Dìu</option>
                      <option value="19">Hrê</option>
                      <option value="20">Mnông</option>
                      <option value="21">Ra-giai</option>
                      <option value="22">Xtiêng</option>
                      <option value="23">Bru-Vân Kiều</option>
                      <option value="24">Thổ</option>
                      <option value="25">Giáy</option>
                      <option value="26">Cơ-tu</option>
                      <option value="27">Gié Triêng</option>
                      <option value="28">Mạ</option>
                      <option value="29">Khơ mú</option>
                      <option value="30">Co</option>
                      <option value="31">Tà-ôi</option>
                      <option value="32">Chơ-ro</option>
                      <option value="33">Kháng</option>
                      <option value="34">Xinh-mun</option>
                      <option value="35">Hà Nhì</option>
                      <option value="36">Chu ru</option>
                      <option value="37">Lào</option>
                      <option value="38">La Chí</option>
                      <option value="39">La Ha</option>
                      <option value="40">Phù Lá</option>
                      <option value="41">La Hủ</option>
                      <option value="42">Lự</option>
                      <option value="43">Lô Lô</option>
                      <option value="44">Chứt</option>
                      <option value="45">Mảng</option>
                      <option value="46">Pà Thẻn</option>
                      <option value="47">Co Lao</option>
                      <option value="48">Cống</option>
                      <option value="49">Bố Y</option>
                      <option value="50">Si La</option>
                      <option value="51">Pu Péo</option>
                      <option value="52">Brâu</option>
                      <option value="53">Ơ Đu</option>
                      <option value="54">Rơ măm</option>
                    </select>
                  </div>
                  <div class="col-md-3">
                    <label class="form-label">Ngày sinh <span class="text-danger">*</span></label>
                    <input type="date" class="form-control" required name="dob">
                  </div>
                  <div class="col-md-3">
                    <label class="form-label">Giới tính <span class="text-danger">*</span></label>
                    <select class="form-select" required name="gender">
                      <option selected disabled>--Chọn--</option>
                      <option value="1" selected>Male</option>
                      <option value="2">Female</option>
                    </select>
                  </div>
                </div>

                <div class="mb-3">
                  <label class="form-label">Địa chỉ <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" required name="homeTown">
                </div>

                <div class="mb-3">
                  <label class="form-label">Email <span class="text-danger">*</span></label>
                  <input type="email" class="form-control" required name="email">
                </div>

                <div class="row mb-3">
                  <div class="col-md-6">
                    <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                    <input type="password" class="form-control" required name="password">
                  </div>
                  <div class="col-md-6">
                    <label class="form-label">Xác nhận mật khẩu <span class="text-danger">*</span></label>
                    <input type="password" class="form-control" required>
                  </div>
                </div>
                <c:if test="${param.registrationMessage != null}">
                  <div class="row mt-4">
                    <div class="col-12">
                      <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <div class="d-flex align-items-center justify-content-center">
                          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-circle-fill" viewBox="0 0 16 16">
                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8 4a.905.905 0 0 0-.9.995l.35 3.507a.552.552 0 0 0 1.1 0l.35-3.507A.905.905 0 0 0 8 4m.002 6a1 1 0 1 0 0 2 1 1 0 0 0 0-2"/>
                          </svg>
                          <span class="fs-5 fw-semibold">${param.registrationMessage}</span>
                        </div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                      </div>
                    </div>
                  </div>
                </c:if>
                <div class="text-center">
                  <button type="submit" class="btn btn-primary">Đăng ký tài khoản</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
