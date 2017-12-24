<!doctype html>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Coupon Vending Machine</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles -->
    <link href="css/style.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">

		<div class="registrationFormContainer row">
			<div class="col-sm-6 mx-auto">
				<form class="registrationForm" method="post" action="coupanselection.do">
					<div class="row">
						<div class="col-sm-12">
							<h2 class="loginHeader">Select Coupons</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="couponProvider" class="couponProvider">Select Coupon Provider</label>
							<select class="form-control" id="couponProvider" name="couponProvider">
								<option value=null>Select Provider</option>
								<c:forEach items="${coupanProviderList.linkedListArray}" var="item" begin = "0" end = "${coupanProviderList.count-1}">
								    <option value="${item}">${item}</option>
								</c:forEach>
							</select>

							<!-- <label for="username" class="error">Validation Error</label> -->
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="couponCategroy" class="couponCategroy">Select Category</label>
							<select class="form-control" id="couponCategroy" name ="couponCategroy">
								<option value=null>Select Category</option>
								<c:forEach items="${coupanCatagoryList.linkedListArray}" var="item" begin = "0" end = "${coupanCatagoryList.count-1}">
								    <option value="${item}">${item}</option>
								</c:forEach>
							</select>

							<!-- <label for="username" class="error">Validation Error</label> -->
						</div>
					</div><div class="row">
						<div class="col-sm-12">
							<label for="couponProduct" class="couponProduct">Select Product</label>
							<select class="form-control" id="couponProduct" name="couponProduct">
								<option value=null>Select Product</option>
								<c:forEach items="${coupanAccesoryNameList.linkedListArray}" var="item" begin = "0" end = "${coupanAccesoryNameList.count-1}">
								    <option value="${item}">${item}</option>
								</c:forEach>
							</select>

							<!-- <label for="username" class="error">Validation Error</label> -->
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" class="btn btn-lg btn-primary btn-block" disabled="true" type="submit" value="Next" />
						</div>
					</div>
					
				</form>
			</div>
		</div>
    </div> <!-- /container -->
  </body>
  <script src="js/jQuery.js"></script>
  <script src="js/Profile.js"></script>
</html>
