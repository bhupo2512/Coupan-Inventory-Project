$('document').ready(function(){
	$('select').change(function(){
		if ($('#couponProvider').val()=="null" && $('#couponCategroy').val()=="null" && $('#couponProduct').val()=="null" )
			$('input[type="submit"]').prop('disabled',true);
		else
			$('input[type="submit"]').prop('disabled',false);
	});
	
});