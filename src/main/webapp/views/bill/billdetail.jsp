<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div class="row">
		<div class="col-xs-12">
			<div class="invoice-title">
				<h2>Chi tiết hóa đơn</h2>
			</div>
			<hr>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Chi tiết hóa đơn</strong>
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed">
							<thead>
								<tr>
									<td><strong>Id</strong></td>
									<td class="text-center"><strong>Tên sản phẩm</strong></td>
									<td class="text-center"><strong>Giá mua</strong></td>
									<td class="text-right"><strong>Số lượng</strong></td>
								</tr>
							</thead>
							<tbody>
								<!-- foreach ($order->lineItems as $line) or some such thing here -->
								<tr>
									<td>BS-200</td>
									<td class="text-center">$10.99</td>
									<td class="text-center">1</td>
									<td class="text-right">$10.99</td>
							</tbody>
						</table>
					</div>
				</div>
				<br> <br>

				
			</div>
			<div style="text-align: right;">
					<tr>
						<td class="left"><strong class="text-dark">Total</strong></td>
						<td class="right"><strong class="text-dark">$20,744,00</strong>
						</td>
					</tr>
				</div>
		</div>
	</div>
</div>
</div>
</body>
</html>
