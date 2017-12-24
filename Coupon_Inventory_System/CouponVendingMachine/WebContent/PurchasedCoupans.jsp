<!doctype html>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- <link rel="icon" href="../../../../favicon.ico"> -->

    <title>Coupon Vending Machine</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles -->
    <link href="css/style.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">
		<div class="purchasedCoupons">
			<h2>Purchased Coupons</h2>
			<table class="table table-striped">
			  <thead>
				<tr>
				  <th scope="col">#</th>
				  <th scope="col">Coupon Provider</th>
				  <th scope="col">Category</th>
				  <th scope="col">Product</th>
				  <th scope="col">Brand</th>
				  <th scope="col">Item Price</th>
				  <th scope="col">Discount Rate</th>
				  <th scope="col">Final Price</th>
				  <th scope="col">Expiry</th>
				</tr>
			  </thead>
			  <tbody>
			  
			  <c:forEach items="${purchasedList.linkedListArray}" var="item" begin = "0" end = "${purchasedList.count-1}" varStatus="i">
								    <tr>
				  <th scope="row">${i.count+1}</th>
				  <td>${item.coupanProvider}</td>
				  <td>${item.coupanCatagory}</td>
				  <td>${item.coupanAccesoryName}</td>
				  <td>${item.coupanName}</td>
				  <td>${item.itemPrize}</td>
				  <td>${item.discountRate}</td>
				  <td>${item.finalPrize}</td>
				  <td>${item.expiryDate}</td>
				</tr>
								</c:forEach>
			  
				
				
			  </tbody>
			</table>
			<div class="buttonRow row justify-content-sm-center">
				<div class="col-sm-4">
					<a href="done.do" class="btn btn-primary btn-block">CheckOut</a>
				</div>
				</div>
		</div>
    </div> <!-- /container -->
  </body>
  <script src="js/jQuery.js" type="javascript"></script>
  <script src="js/bootstrap.min.js" type="javascript"></script>
</html>
