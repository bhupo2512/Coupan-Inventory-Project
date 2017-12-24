<!doctype html>
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

		<div class="loginFormContainer row">
			<div class="col-sm-6 mx-auto">
				<form class="loginForm" method="post" action="login.do">
					<div class="row">
						<div class="col-sm-12">
							<h2 class="loginHeader">Coupon Vending Machine</h2>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="username" class="username">Username</label>
							<input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
							<!-- <label for="username" class="error">Validation Error</label> -->
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="password" class="password">Password</label>
							<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
							<!-- <label for="username" class="error">Validation Error</label> -->

						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" class="btn btn-lg btn-primary btn-block" type="submit" value="Login" />
						</div>
					</div>
					<!-- <div class="row">
						<div class="col-sm-12">
							<a href="Registration.html">New User? Click here to register.</a>
						</div>
					</div> -->
				</form>
			</div>
		</div>
    </div> <!-- /container -->
  </body>
</html>
