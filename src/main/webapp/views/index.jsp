<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/views/layout/header.jsp"%>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="/Nhom11/home">Cửa hàng thương mại
				điện tử</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:forEach var="e" items="">
								<li><a class="dropdown-item"
									href="/Nhom11/home?categoryid="></a></li>
							</c:forEach>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:forEach var="e" items="">
								<li><a class="dropdown-item" href=""></a></li>
							</c:forEach>
						</ul></li>
				</ul>

				<c:choose>

					<c:when test="${ account != null }">
						<a class="btn btn-outline-dark text-decoration-none"
							href="/Nhom11/cart"> <i class="bi-cart-fill me-1"></i> Cart
						</a>
						<p class="badge bg-dark text-white ms-1 rounded-pill">Xin chào
							${ account.username }</p>
						<a class="btn btn-outline-dark text-decoration-none"
							href="/Nhom11/updateuser">Thay đổi thông tin
						</a>
						<form method="post">
							<button class="badge bg-dark text-white ms-1 rounded-pill"
								type="submit">Quản lý đơn hàng</button>
						</form>
						<form action="logout" method="post">
							<button class="btn btn-outline-dark" type="submit">Đăng
								xuất</button>
						</form>

					</c:when>
					<c:otherwise>
						<button class="btn btn-outline-dark">
							<a href="login">Đăng nhập</a>
						</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
	<!-- Header-->
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">Cửa hàng thương mại điện tử</h1>
				<p class="lead fw-normal text-white-50 mb-0">Chuyên bán các sản
					phẩm Laptop và Mobile</p>
			</div>
		</div>
	</header>
	<!-- Section-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row">
				<form>
					<label>Danh muc:</label> <select name="categoryId">
						<option value="">Tất cả</option>
						<c:forEach var="e" items="${categories}">
							<option value=${ e.id }>${e.name}</option>
						</c:forEach>
					</select> <label>Nhà cung cap:</label> <select name="supplierId">
						<option value="">Tất cả</option>
						<c:forEach var="e" items="${suppliers}">
							<option value=${ e.id }>${e.name}</option>
						</c:forEach>
					</select>
					<button type="submit">Tim kiem</button>
				</form>
			</div>
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach items="${products}" var="i">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top"
								src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${i.name}</h5>
									<!-- Product price-->
									${i.getIntUnitPrice()} VND
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto"
										href="/Nhom11/products/detail?id=${i.id.toString() }">Xem
										chi tiết</a> <br> <br>
									<form action="/Nhom11/cart?productId=${i.id.toString() }"
										method="post">
										<button class="btn btn-outline-dark mt-auto" type="submit">Thêm
											vào giỏ hàng</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<%@ include file="/views/layout/footer.jsp"%>
		<!-- Bootstrap core JS-->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
