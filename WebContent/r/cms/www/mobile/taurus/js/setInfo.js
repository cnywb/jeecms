$(function () {
	id = 1;
	gender = '男';
	block = false;
	
	var Request = new Object();  
	Request = GetRequest(); 
	var channelStr= Request["channel"];  
	console.log(channelStr);
	
	function GetRequest() {  
    var url = location.search; //获取url中"?"符后的字串   
    var theRequest = new Object();  
    if (url.indexOf("?") != -1) {  
        var str = url.substr(1);  
        strs = str.split("&");  
        for (var i = 0; i < strs.length; i++) {  
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
        }  
    }  
    return theRequest;  
}  
	

		
	$('.rule').on('touchstart',function(){
		$('.popup').hide()
		$('.ruletxt').fadeIn()
	})   
	
	$('.apply').on('touchstart',function(){
		$('.setinfo').fadeIn()
	}) 
	$('.icon').on('touchstart',function(e){
		$('.icon').removeClass('on')
		$(this).addClass('on')
		this.gender = $(this).attr('data-id')
				
	})
		
	$('.restart').on('touchstart',function(e){
	window.location.href='http://www.changanfordwechat.com/fordwechat/control?state=1301' + channelStr;
	console.log(channelStr);
	})
			
	
	$('.setinfo .commit').on('touchstart',function(){
		var mobile = $('.mobile').val();
		var name = $('.name').val();
		var prov = $("#provinceDDL").find("option:selected").text();
		var city = $("#cityDDL").find("option:selected").text();
		console.log(city);
		if(mobile != '' && !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if (city === "") {
			alert("请选择省市");
			return false;
		}
		if(block == false){
			block = true
			saveinfor(name,mobile,self.gender,prov,city,channelStr)
		}
	})
	
	$('.popup .close,.success a,.share').on('touchstart',function(){
		$('.popup').fadeOut()
	})	

	
})
function saveinfor(name,mobile,gender,province,city,channel){

	$.ajax({  
		url: '/infocollection/potentialcustomer/taurus/add.jspx',
		data:{
			'infoList[0].customerProvince':province,
			'infoList[0].intentionalCarModel':'金牛座',
			'infoList[0].customerCity':city,
            'infoList[0].channel':channel,
			'infoList[0].customerName':name,
			'infoList[0].customerMobilePhoneNo':mobile,
			'infoList[0].customerGender':gender,
			'infoList[0].activityName':'金牛座为卓越而造H5'
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
