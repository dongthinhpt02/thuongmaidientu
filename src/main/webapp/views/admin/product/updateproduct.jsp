<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/views/admin/layout/header.jsp"%>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="/Nhom11/admin">Trang quản lý</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			<div class="input-group">
				<input class="form-control" type="text" placeholder="Search for..."
					aria-label="Search for..." aria-describedby="btnNavbarSearch" />
				<button class="btn btn-primary" id="btnNavbarSearch" type="button">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="#!"></a></li>
					<li><a class="dropdown-item" href="#!"></a></li>
					<li><hr class="dropdown-divider" /></li>
					<li><a class="dropdown-item" href="#!"></a></li>
				</ul></li>
			<c:choose>
				<c:when test="${ account != null }">
					<p class="badge bg-dark text-white ms-1 rounded-pill">Xin chào
						${ account.username }</p>
					<form action="/Nhom11/logout" method="post">
						<button class="badge bg-dark text-white ms-1 rounded-pill"
							type="submit">Đăng xuất</button>
					</form>

				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">

						<%@ include file="/views/admin/layout/left.jsp"%>



					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Logged in as:</div>
					Start Bootstrap
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4"></div>
				<h1 class="mt-4">Quản lý thương mại điện tử</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Quản lý</li>
				</ol>
				<div class="insert-form">
					<h1>Chỉnh sửa sản phẩm</h1>

					<form method="post">
						<label for="name">Tên sản phẩm</label> <input
							value="${product.name}" type="text" id="name" name="name"
							required><br> <br> <label for="unitPrice">Giá</label>
						<input value="${product.unitPrice}" type="number" id="unitPrice"
							name="unitPrice" step="0.01" required><br> <br>
						<label for="quantityLeft">Số lượng nhập</label> <input
							value="${product.quantityLeft}" type="number" id="quantityLeft"
							name="quantityLeft" required><br> <br> <label
							for="description">Mô tả</label><br>
						<textarea value="${product.description}" id="description"
							name="description" rows="4" cols="50"></textarea>
						<br> <br> <label for="image">Ảnh</label> <input
							value="${product.image}" type="text" id="image" name="image"><br>
						<br>
						<!-- Dropdown for Supplier -->
						<%-- <label for="supplierId">Nhà cung cấp</label> <select  id="supplierId"
							name="supplierId" required>
							<c:forEach items="${suppliers}" var="supplier">
								<option value="${supplier.id}">${supplier.name}</option>
							</c:forEach>
						</select><br>
						<br> --%>
						<label for="supplierId">Nhà cung cấp</label> <select
							id="supplierId" name="supplierId" required>
							<c:forEach items="${suppliers}" var="supplier">
								<c:choose>
									<c:when test="${supplier.id eq defaultSupplierId}">${supplier.name}
										<option value="${supplier.id}" selected>${supplier.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${supplier.id}">${supplier.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> <br>
						<br>

						<!-- Dropdown for Category -->
						<label for="categoryId">Danh mục</label> <select id="categoryId"
							name="categoryId" required>
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select><br> <br>
						<button type="submit" value="Submit">Thêm</button>
					</form>
				</div>
			</main>
			<%@ include file="/views/admin/layout/footer.jsp"%>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
		crossorigin="anonymous"></script>

</body>
</html>
