$(function () {
	id = 1;
	gender = '男';
	block = false; 
	
	$(".edge-yyBtn").click(function() {
		$('.popup').fadeIn()
	})
	
	$('.popup .close').on('touchstart',function(){
		$('.popup').fadeOut()
	})
	$('.success a').on('touchstart',function(){
		$('.success').fadeOut()
	})
	
//	$('.restart').on('touchstart',function(e){
//	window.location.href='http://www.changanfordwechat.com/fordwechat/control?state=1301' + channelStr;
//	console.log(channelStr);
//	})
//			
	$('.setinfo .commit').on('touchstart',function(){
		var mobile = $('.mobile').val();
		var name = $('.name').val();
		//var prov = $("#provinceDDL").find("option:selected").text();
		//var city = $("#cityDDL").find("option:selected").text();
		//console.log(city);
		if(mobile != '' && !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
//		if (city === "") {
//			alert("请选择省市");
//			return false;
//		}
		if(block == false){
			block = true
			saveinfor(name,mobile)
		}
	})

})
function saveinfor(name,mobile){
	var channelStr = urlRequest("channel");

	$.ajax({  
		url: 'http://www.changanfordclub.com/infocollection/potentialcustomer/edge/add.jspx',
		data:{
			'infoList[0].intentionalCarModel':'锐界',
			'infoList[0].customerName':name,
			'infoList[0].customerMobilePhoneNo':mobile,
			'infoList[0].activityName':'福特锐界 | 挑战不可能',
			'infoList[0].channel':channelStr
		},
		type: 'post',
		cache: false,
		async: false,
		dataType: 'json',
		success: function(value) {
			block = false;
			if(value.status == '1'){
				$('.popup').hide();
				$('.success').fadeIn();
			}else{
				alert(value.message)
			}
		},
		error :function() {
			alert('system error');
			block = false;
		}					
	})
}		

	function urlRequest(paras)
	{ 
	var url = location.href; 
	var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
	var paraObj = {} 
	for (i=0; j=paraString[i]; i++){ 
	paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
	} 
	var returnValue = paraObj[paras.toLowerCase()]; 
	if(typeof(returnValue)=="undefined"){ 
	return ""; 
	}else{ 
	return returnValue; 
	} 
	}