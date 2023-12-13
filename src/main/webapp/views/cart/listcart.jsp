<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/views/layout/header.jsp"%>
<title>Simple Shopping Cart</title>
<link rel="stylesheet"
	href="http://localhost:8080/Nhom11/views/cart/style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<div class="cart_section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-10 offset-lg-1">
					<div class="cart_container">
						<div class="cart_title">Giỏ hàng</div>
						<div class="cart_items">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Ảnh</th>
										<th scope="col">Tên</th>
										<th scope="col">Số lượng</th>



									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${cartArray}">
										<tr class="cart_item">
											<td class="cart_item_name">${item.getKey().image}</td>
											<td class="cart_item_name">${item.getKey().name}</td>
											<td class="cart_item_quantity">${item.getValue()}</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div>
							<div class="order_total">
								<div class="order_total_content text-md-right">
									<div class="order_total_title">Tổng hóa đơn</div>
									<div class="order_total_amount">${totalCost }</div>
								</div>
							</div>
							<br> <br>
							<form action="/Nhom11/order" method="post">
								<label for="address">Địa chỉ :</label> <input name="address"
									type="text" id="address" placeholder="Nhập địa chỉ"
									class="address_input"> <br> <br> <label
									for="phonenumber">Số điện thoại :</label> <input
									name="phonenumber" type="text" id="address"
									placeholder="Nhập số điện thoại" class="address_input">
								<div class="cart_buttons">
									<form action="/Nhom11/cart/delete" method="post">
										<button type="submit" class="button cart_button_clear">Xóa giỏ hàng</button>
									</form>
									<a href="/Nhom11/home" class="button cart_button_clear">Tiếp
										tục mua sắm</a>
									<button type="submit" class="button cart_button_checkout">Thanh
										toán</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/http://localhost:8080/Nhom11/views/cart/script.js"></script>
</body>
</html>
