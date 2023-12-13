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
						<li class="breadcrumb-item active">Quản lý</li>
					</ol>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> Danh sách nhà cung cấp
						</div>
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>Id danh mục</th>
										<th>Tên danh mục</th>
										<th>Ảnh</th>
										<th>Trạng thái</th>
										<th>Chỉnh sửa</th>
										<th>Khóa</th>
										
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Id danh mục</th>
										<th>Tên danh mục</th>
										<th>Ảnh</th>
										<th>Trạng thái</th>
										<th>Chỉnh sửa</th>
										<th>Khóa</th>
									</tr>
								</tfoot>

								<tbody>
									<c:forEach items="${Categories}" var="i">
										<tr>
											<td>${i.id}</td>
											<td>${i.name}</td>
											<td>${i.image}</td>
											<td>${i.status}</td>
											<td><a href="/Nhom11/admin/categories/update?id=${ i.id.toString() }">Chỉnh sửa</a></td>
											<td><c:choose>
													<c:when test="${i.status == 1}">
														<form action="/Nhom11/admin/categories/delete?id=${ i.id.toString() }" method="post"> <button type="submit">Delete</button> </form>
													</c:when>
													<c:when test="${i.status == 0}">
														<form action="/Nhom11/admin/categories/restore?id=${ i.id.toString() }" method="post"> <button type="submit">Restore</button> </form>
													</c:when>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>

							</table>
						</div>
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
