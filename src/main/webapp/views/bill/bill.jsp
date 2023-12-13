<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Bill</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<div class="container">
	<a class="navbar-brand" href="/Nhom11/home">Cửa hàng thương mại
		điện tử</a>
	<div class="row">
		<div class="col-xs-12">
			<div class="invoice-title">
				<h2>Danh sách hóa đơn của ${account.fullName}</h2>
			</div>
			<hr>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Order summary</strong>
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed">
							<thead>
								<tr>
									<td><strong>Id</strong></td>
									<td class="text-center"><strong>Số điện thoại</strong></td>
									<td class="text-right"><strong>Địa chỉ</strong></td>
									<td class="text-right"><strong>Chi tiết hóa đơn</strong></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${bills}" var="i">
									<!-- foreach ($order->lineItems as $line) or some such thing here -->
									<tr>
										<td>${i.id}</td>
										<td class="text-center">${i.phoneNumber}</td>
										<td class="text-right">${i.shippingAddress}</td>
										<td><div style="text-align: right;">
												<a href="/Nhom11/bills/detail?billId=${i.id.toString() }">Chi
													tiết hóa đơn</a>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>