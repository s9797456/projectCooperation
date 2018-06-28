jQuery(document).ready(function() {
	$('#logout').click(function () {
		    	location.href=_ctxPath+"/user/quit.do";
		});
		
	$('#submitChange').click(function(){
			$.ajax({
				url:_ctxPath+"/user/editPWD.do",
				type:"post",
				dataType:"json",
				data:$('#editPassForm').serialize(),
				success:function(data){
					$('#myModal').modal('hide');
					for(var i=1;i<$('#editPassForm input').length;i++){
						$('form input').eq(i).val("");
					}
					alert(data.msg);
					location.href="quit.do";
				},
				error:function(xhr,status,error){
					
				 }
				});
		});
		
});
