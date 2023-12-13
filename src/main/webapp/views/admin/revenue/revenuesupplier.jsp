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
				<div class="container-fluid px-4">
					<h1 class="mt-4">Quản lý thương mại điện tử</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item active">Thống kê doanh thu theo
							nhà cung cấp</li>
					</ol>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>Id nhà cung cấp</th>
									<th>Tên nhà cung cấp</th>
									<th>Tổng tiền</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Id nhà cung cấp</th>
									<th>Tên nhà cung cấp</th>
									<th>Tổng tiền</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach items="${suppliers}" var="supplier">
									<tr>
										<td>${supplier[0]}</td>
										<td>${supplier[1]}</td>
										<td><fmt:formatNumber value="${supplier[2]}" type="number" pattern="###" /> VNĐ</td>
									</tr>
								</c:forEach>
							</tbody>
							<tbody>

							</tbody>

						</table>
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
