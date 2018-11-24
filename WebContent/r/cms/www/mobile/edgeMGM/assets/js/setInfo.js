$(function () {
	block = false;
	window.agreeNotice = true;
	window.agreeNotice2 = true;
	window.tabOwner = true;
	var chann = urlRequest("channel");
	window.channel = chann.split("#")[0];

	initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", "");
	initSelectData("buyingDealerProvince2","","buyingDealerCity2","", "carBuyingDealer2", "");

	$("#chk01").click(function(){
		if (window.agreeNotice) {
			window.agreeNotice = false;
			$("#chk01").attr('src','/r/cms/www/mobile/edgeMGM/assets/i/checkbox.png');
		} else {
			window.agreeNotice = true;
			$("#chk01").attr('src','/r/cms/www/mobile/edgeMGM/assets/i/checked.png');
		}
	})

	
	
	$('.formsubmitBtn').on('touchstart',function(){
		var mobile = $('.inputMobi').val();
		var name = $('.inputName').val();
		var mobileSugg = $('.inputsuggMobi').val();
		var nameSugg = $('.inputsuggName').val();
		var owerCar = $(".ownerCar").find("option:selected").text();
		var prov = $(".province").find("option:selected").text();
		var city = $(".City").find("option:selected").text();
		var dealer = $(".dealer").find("option:selected").text();
		var buytime = $(".dealTime").find("option:selected").text();
		
		console.log(city);
		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}
		if(mobileSugg == '' || !(/^1[34578]\d{9}/.test(mobileSugg)) ){
			alert('请输入被推荐人正确的手机号！')
			return false;
		}
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if(nameSugg==''){
			alert('请输入您的姓名！')
			return false;
		}
		if (city === "请选择" ||prov === "请选择" ) {
			alert("请选择省市");
			return false;
		}
		if (owerCar === "请选择") {
			alert("请选择你的车型");
			return false;
		}
		if(block == false){
			block = true
			saveinfor(name,mobile,nameSugg,mobileSugg,owerCar,prov,city,dealer,buytime,window.channel)
		}
	})

	$(".formsubmitBtn2").on("touchstart",function(){
		
		var mobile = $('.inputMobi2').val();
		var name = $('.inputName2').val();
		var prov = $(".province2").find("option:selected").text();
		var city = $(".City2").find("option:selected").text();
		var dealer = $(".dealer2").find("option:selected").text();
		var buytime = $(".dealTime2").find("option:selected").text();
		var mycar = $(".myCar").find("option:selected").text();
		var myYear = $(".myYear").find("option:selected").text();
		
		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if (city === "请选择" ||prov === "请选择" ) {
			alert("请选择省市");
			return false;
		}
		if (dealer === "请选择") {
			alert("请选择经销商");
			return false;
		}
		if (mycar === "请选择") {
			alert("请选择你现有的车");
			return false;
		}
		if (buytime === "请选择") {
			alert("请选择预计购买时间");
			return false;
		}
		if (myYear === "请选择") {
			alert("请选择现有车型购车时间");
			return false;
		}
		if(block == false){
			block = true
			saveinfor2(name,mobile,prov,city,dealer,buytime,mycar,myYear,window.channel)
		}
	
	})

	
})

var urlHost = "http://www.changanfordclub.com/";



function initSelectData(provinceSelectId,defaultProvince,citySelectId,defaultCity,dealerSelectId,defaultDealer){
	loadProvince(provinceSelectId,defaultProvince,citySelectId,defaultCity,dealerSelectId,defaultDealer);
	$("#"+provinceSelectId).change(function(){
		$("#"+citySelectId).html("");
		$("#"+dealerSelectId).html("");
		var provinceId=$(this).find("option:selected").attr("data");
		loadCity(citySelectId,'',provinceId,'','');
	});
	$("#"+citySelectId).change(function(){
		$("#"+dealerSelectId).html("");
		var cityId=$(this).find("option:selected").attr("data");
		loadDealer(dealerSelectId,'',cityId);
	});
	
}




/**
 * 加载省
 */
function loadProvince(selectId,defaultValue,citySelectId,defaultCity,dealerSelectId,defaultDealer){
	var submitData={};
	$.ajax( {
		url :urlHost + 'dealer/findAllProvince.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" selected>请选择</option>');
			for(var i=0;i<result.length;i++){
				var t=result[i];
				if(defaultValue==t.name){
					$("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				    loadCity(citySelectId, defaultCity, t.id,dealerSelectId,defaultDealer);
				}else{
					$("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				}
			}
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
	});
}
/**
 * 加载城市
 */
function loadCity(selectId,defaultValue,provinceId,dealerSelectId,defaultDealer){
	if(provinceId==""){
		return;
	}
	var submitData={"provinceId":provinceId};
	$.ajax( {
		url :urlHost + 'dealer/findAllCityByProvinceId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" selected>请选择</option>');
			for(var i=0;i<result.length;i++){
				var t=result[i];
				if(defaultValue==t.name){
					$("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
					loadDealer(dealerSelectId, defaultDealer, t.id);
				}else{
					$("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				}
			}
			if (provinceId == "16") {
				$("#"+selectId).append("<option value='莱州市' data='158' >莱州市</option>");
			}
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
	});
}
/**
 * 加载经销商
 */
function loadDealer(selectId,defaultValue,cityId){
	if(cityId==""){
		return ;
	}
	var submitData={"cityId":cityId};
	$.ajax( {
		url :urlHost + 'dealer/findAllDealerByCityId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" selected>请选择</option>');
			for(var i=0;i<result.length;i++){
				var t=result[i];
				if(defaultValue==t.name){
					$("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				}else{
					$("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				}
			}
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
	});
}





function saveinfor(name,mobile,nameSugg,mobileSugg,owerCar,prov,city,dealer,buytime,channel){

	$.ajax({  
		url: 'http://www.changanfordclub.com/infocollection/potentialcustomer/add.jspx',
		data:{
			'infoList[0].carOwnerName':name,
			'infoList[0].carOwnerMobilePhoneNo':mobile,
			'infoList[0].carModel':owerCar,
			'infoList[0].customerName':nameSugg,
			'infoList[0].customerMobilePhoneNo':mobileSugg,
			'infoList[0].customerProvince':prov,
			'infoList[0].customerCity':city,
			'infoList[0].intentionalDealer':dealer,
			'infoList[0].intentionalBuyDateRange':buytime,
			'infoList[0].intentionalCarModel':'锐界',
			'infoList[0].activityName':'锐界新车主推荐活动',
			'infoList[0].channel':channel
		},
		type: 'post',
		cache: false,
		async: false,
		dataType: 'json',
		success: function(value) {
			block = false;
			if(value.status == '1'){
				alert('提交成功');
				
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

function saveinfor2(name,mobile,prov,city,dealer,buytime,mycar,myyear,channel){

	$.ajax({  
		url: 'http://www.changanfordclub.com/infocollection/potentialcustomer/add.jspx',
		data:{
			'infoList[0].customerName':name,
'infoList[0].carOwnerName':name,
			'infoList[0].customerMobilePhoneNo':mobile,
'infoList[0].carOwnerMobilePhoneNo':mobile,
			'infoList[0].carOwnerProvince':prov,
			'infoList[0].carOwnerCity':city,
			'infoList[0].carOwnerDealer':dealer,
			'infoList[0].intentionalBuyDateRange':buytime,
			'infoList[0].carModel':mycar,
			'infoList[0].carOwnerBuyTime':myyear,
			'infoList[0].intentionalCarModel':'锐界',
			'infoList[0].activityName':'新车主推荐活动',
			'infoList[0].channel':channel
		},
		type: 'post',
		cache: false,
		async: false,
		dataType: 'json',
		success: function(value) {
			block = false;
			if(value.status == '1'){
				$("#succOverlay").fadeIn();
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




function urlRequest(paras)  //获取url参数方法
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