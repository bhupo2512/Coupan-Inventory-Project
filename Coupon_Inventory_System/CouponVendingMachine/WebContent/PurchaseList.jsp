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
		<div class="purchaseListContainer">
			<form class="purchaseListForm" id ="purchaseListForm" method="post" action="cart.do">
			<c:forEach items="${availableCoupanList.linkedListArray}" var="item" begin = "0" end = "${availableCoupanList.count-1}" varStatus="i">
			   	<div class="purchaseListRow row justify-content-sm-center">
					<div class="col-sm-1">
						<div class="form-check">
						  <label class="form-check-label">
							<input class="form-check-input position-static" type="checkbox" id="${i.count}" value="option1" name="selectedList">
						  </label>
						</div>
	
					</div>
					<div class="col-sm-11">
						<p><span class="couponAttr" id="cpnProvider_${i.count}">Coupon Provider:</span><span class="attrVal"> ${item.coupanProvider }</span><br/>
						<span class="couponAttr"  id="cpnCategory_${i.count}">Coupon Catagory:</span><span class="attrVal"> ${item.coupanCatagory }</span><br/>
						<span class="couponAttr"  id="cpnProduct_${i.count}">Coupon Product:</span><span class="attrVal"> ${item.coupanAccesoryName }</span><br/>
						<span class="couponAttr" id="cpnBrand_${i.count}">Coupon Brand:</span><span class="attrVal"> ${item.coupanName }</span><br/>
						<span class="couponAttr"  id="cpnDiscount_${i.count}">Coupon Discount Rate:</span><span class="attrVal"> ${item.discountRate }</span><br/>
						<span class="couponAttr"  id="cpnPrice_${i.count}">Coupon Final Price:</span><span class="attrVal"> ${item.finalPrize }</span><br/>
						<span class="couponAttr" id="cpnExpiry_${i.count}">Coupon Expiry Date:</span><span class="attrVal"> ${item.expiryDate }</span><br/>
						<span class="couponAttr" id="cpnStatus_${i.count}">Coupon Status:</span><span class="attrVal"> ${item.coupanStatus }</span><br/>
					</div>
				</div>
			   
			   
			</c:forEach>
			<input type="hidden" id="hdnItems" name="selectedList1" value="default"/>
		</form>
			
			
			
			
			<div class="buttonRow row justify-content-sm-center">
				<div class="col-sm-4">
					<a href="sortedCarrt.do?so=1" class="btn btn-outline-primary btn-xs btn-block">Provider</a>
				</div>
				<div class="col-sm-4">
					<a href="sortedCarrt.do?so=2" class="btn btn-outline-primary btn-xs btn-block">Product</a>
				</div>
				<div class="col-sm-4">
					<a href="sortedCarrt.do?so=3" class="btn btn-outline-primary btn-xs btn-block">Price</a>
				</div>
				</div>
				<div class="buttonRow row justify-content-sm-center">
				<div class="col-sm-4">
					<a href="sortedCarrt.do?so=4" class="btn btn-outline-primary btn-xs btn-block">Discount</a>
				</div>
				<div class="col-sm-4">
					<a href="sortedCarrt.do?so=5" class="btn btn-outline-primary btn-xs btn-block">Expiry</a>
				</div>
				<div class="col-sm-4">
					<a href="sortedCarrt.do?so=6" class="btn btn-outline-primary btn-xs btn-block">Status</a>
				</div>
			</div>
			<div class="buttonRow row justify-content-sm-center">
				<div class="col-sm-12">
					<button class="btn btn-primary btn-block" id="purchaseBtn">Purchase</button>
				</div>
			</div>

			</div>
		</div>
    </div> <!-- /container -->
  </body>
  <script src="js/jQuery.js"></script>
  <!-- <script src="js/bootstrap.min.js"></script> -->
   <script src="js/purchaseList.js"></script>
</html>
