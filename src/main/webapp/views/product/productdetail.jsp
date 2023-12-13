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
						<form method="post">
							<button class="badge bg-dark text-white ms-1 rounded-pill"
								type="submit">Thay đổi thông tin</button>
						</form>
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
	<!-- Product section-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="row gx-4 gx-lg-5 align-items-center">
				<div class="col-md-6">
					<img class="card-img-top mb-5 mb-md-0"
						src="https://dummyimage.com/600x700/dee2e6/6c757d.jpg" alt="..." />
				</div>
				<div class="col-md-6">
					<h1 class="display-5 fw-bolder">${product.name }</h1>
					<div class="fs-5 mb-5">

						<span>${product.getIntUnitPrice()} VND</span>
					</div>
					<p class="lead">${product.description}</p>
					<div class="d-flex">
						<form action="/Nhom11/cart?productId=${product.id.toString() }"
							method="post">
							<button class="btn btn-outline-dark mt-auto" type="submit">Thêm
								vào giỏ hàng</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Related items section-->
	<!-- Footer-->
	<!-- Footer-->
	<%@ include file="/views/layout/footer.jsp"%>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
