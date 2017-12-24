$('document').ready(function(){
	$('input[type="submit"]').click(function(e){
		if ($('#confirmPassword').val()!=$('#password').val()){
			alert("Password Mismatch !");
			e.preventDefault();
		}
	});
	
});