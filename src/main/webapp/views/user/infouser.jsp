<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/views/layout/header.jsp"%>
<title>Simple Shopping Cart</title>
<link rel="stylesheet"
	href="http://localhost:8080/Nhom11/views/user/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</head>
<body>
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">
			<div class="col-md-3 border-right">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<span class="text-black-50"></span><span> </span>
				</div>
			</div>
			<div class="col-md-5 border-right">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-right">Profile Settings</h4>
					</div>
					<form method="post">
						<div class="row mt-2">
							<div class="col-md-6">
								<label class="labels">Id</label><input id="id" name="id"
									value="${account.id}" type="
								text"
									class="form-control" disabled>
							</div>
							<div class="col-md-6">
								<label class="labels">username</label><input id="username"
									name="username" value="${account.username}" type=" text"
									class="form-control" disabled>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12">
								<label class="labels">Email</label><input id="email"
									name="email" type="text" class="form-control" disabled
									value="${account.email}">
							</div>
							<div class="col-md-12">
								<label class="labels">Họ và tên</label><input id="fullName"
									name="fullName" type="text" class="form-control"
									value="${account.fullName}">
							</div>
							<div class="col-md-12">
								<label class="labels">Hình ảnh</label><input id="image"
									name="image" type="text" class="form-control"
									value="${account.image}">
							</div>
							<div class="col-md-12">
								<label class="labels">Số điện thoại</label><input
									id="phoneNumber" name="phoneNumber" type="text"
									class="form-control" value="${account.phoneNumber}">
							</div>
							<div class="col-md-12">
								<label class="labels">Mật khẩu</label><input id="password"
									name="password" type="password" class="form-control"
									value="${account.password}">
							</div>

						</div>
						<div>

							<div class="mt-5 text-center">
								<button class="btn btn-primary profile-button" type="submit">Save
									Profile</button>
							</div>
					</form>

					<div class="mt-5 text-center">
						<a class="btn btn-primary profile-button" href="/Nhom11/home">Back
							to home</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>