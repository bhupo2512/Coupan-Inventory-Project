$('document').ready(function(){
	$('#purchaseBtn').on('click',function(){
		var selectedItems = "";
		$('input[name="selectedList"]:checked').each(function(){
			console.log($(this).attr('id'));
			var id=$(this).attr('id');
			
			selectedItems+=$('#cpnProvider_'+id).next('.attrVal').text().trim()+" "+ 
			$('#cpnCategory_'+id).next('.attrVal').text().trim()+" "+
			$('#cpnProduct_'+id).next('.attrVal').text().trim()+" "+
			$('#cpnBrand_'+id).next('.attrVal').text().trim()+" "+
			$('#cpnDiscount_'+id).next('.attrVal').text().trim()+" "+
			$('#cpnPrice_'+id).next('.attrVal').text().trim()+" "+
			$('#cpnStatus_'+id).next('.attrVal').text().trim()+" ";
		
			/*obj = {
						coupanProvider:$('#cpnProvider_'+id).next('.attrVal').text().trim(), 
						coupanCatagory:$('#cpnCategory_'+id).next('.attrVal').text().trim(), 
						coupanAccesoryName:$('#cpnProduct_'+id).next('.attrVal').text().trim(),
						coupanName:$('#cpnBrand_'+id).next('.attrVal').text().trim(),
						discountRate:$('#cpnDiscount_'+id).next('.attrVal').text().trim(),
						finalPrize:$('#cpnPrice_'+id).next('.attrVal').text().trim(),
						expiryDate:$('#cpnExpiry_'+id).next('.attrVal').text().trim(),
						coupanStatus:$('#cpnStatus_'+id).next('.attrVal').text().trim(),
					};
			array.push(obj);*/
		});
		$('#hdnItems').val(selectedItems);
		$('#purchaseListForm').submit();
	});
	
	
});