$(function () {
	block = false;
	var chann = urlRequest("channel");
	window.channel = chann.split("#")[0];

	initSelectData("buyingDealerProvince1","","buyingDealerCity1","", "", "");
	var ppNum = 1;
	
	$(".addpp").on("touchstart",function(){
		ppNum = ppNum +1;
		$(".tField").append("<div class='person" + ppNum + " pers'>		<input type='text' class='inputBlock inputsuggName ab' id='name" + ppNum + "' maxlength='11'/><input type='text' class='inputBlock inputsuggMobi ab' id='mobi" + ppNum + "' maxlength='11'/><select class='selectArea City ab' id='buyingDealerCity" + ppNum + "'><option value='0' selected>请选择</option>								</select>								<select class='selectArea province ab' id='buyingDealerProvince" + ppNum + "'>									<option value='0' selected>请选择</option>								</select>								<select class='selectArea buyCar ab' id='buyCar" + ppNum + "'>									<option value='0' selected>请选择</option>									<option value='5'>锐界</option>									<option value='1'>福睿斯</option>									<option value='3'>经典福克斯</option>									<option value='4'>全新福克斯</option>									<option value='6'>新蒙迪欧</option>									<option value='8'>麦柯斯</option>									<option value='9'>翼搏</option>									<option value='10'>翼虎</option>									<option value='11'>金牛座</option>								</select><select class='selectArea dealTime ab' id='dealTime" + ppNum + "'><option value='0' selected>请选择</option><option value='5'>0-3个月内</option><option value='1'>4-6个月</option><option value='2'>7-12个月</option><option value='3'>1年以上</option></select><img src='/r/cms/www/mobile/fordSug/assets/i/delpp.png' class='delpp ab'/></div>");
		initSelectData("buyingDealerProvince"+ ppNum,"","buyingDealerCity"+ ppNum,"", "", "");
		
		$(".delpp").on("touchstart",function(event){
			ppNum = ppNum - 1;
			 $(this).parent().remove();
		})
	})
	
	$('.btnSuggest').on('touchstart',function(){
		//alert(ppNum);
		var dataStr = "";
		var submitData={};
		
		for (i=0;i<ppNum;i++) {
			q = i +1;
			var mobile = $('.owerField .inputMobi').val();
			var name = $('.owerField .inputName').val();
			var owerCar = $(".owerField .ownerCar").find("option:selected").text();
			var vin = $('.owerField .inputVin').val();
			var mobileSugg = $('.tField div:nth-child('+q+') .inputsuggMobi').val();
			var nameSugg = $('.tField div:nth-child('+q+') .inputsuggName').val();
			var prov = $('.tField div:nth-child('+q+') .province').find("option:selected").text();
			var city = $('.tField div:nth-child('+q+') .City').find("option:selected").text();
			var buycar = $('.tField div:nth-child('+q+') .buyCar').find("option:selected").text();
			var buytime = $('.tField div:nth-child('+q+') .dealTime').find("option:selected").text();
			
			if(name==''){
			alert('请输入您的姓名！')
			return false;
			}
			if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
				alert('请输入正确的手机号！')
				return false;
			}
			if (owerCar === "请选择") {
				alert("请选择所购车型");
				return false;
			}
			if (validateVin(vin) == false) {
				alert("请输入正确VIN码");
				return false;
			}
			if(mobileSugg == '' || !(/^1[34578]\d{9}/.test(mobileSugg)) ){
				alert('请输入被推荐人正确的手机号！')
				return false;
			}
			if(nameSugg==''){
				alert('请输入被推荐人的姓名！')
				return false;
			}
			if (city === "请选择") {
				alert("请选择省市");
				return false;
			}
			if (buycar === "请选择") {
				alert("请选择意向车型");
				return false;
			}
			if (buytime === "请选择") {
				alert("请选择购车时间");
				return false;
			}	
			submitData["infoList["+i+"].carOwnerName"]=name;
			submitData["infoList["+i+"].carOwnerMobilePhoneNo"]=mobile;
			submitData["infoList["+i+"].carModel"]=owerCar;
			submitData["infoList["+i+"].customerName"]=nameSugg;
			submitData["infoList["+i+"].customerMobilePhoneNo"]=mobileSugg;
			submitData["infoList["+i+"].customerProvince"]=prov;
			submitData["infoList["+i+"].customerCity"]=city;
                        submitData["infoList["+i+"].vin"]=vin;
			submitData["infoList["+i+"].intentionalBuyDateRange"]=buytime;
			submitData["infoList["+i+"].intentionalCarModel"]=buycar;
			submitData["infoList["+i+"].activityName"]="全车系2016车主推荐活动";
			submitData["infoList["+i+"].channel"]=channel;
			dataStr = dataStr + nameSugg +","+mobileSugg+	"," +city+ "<br>";		
					
					
//			dataStr = dataStr + 'infoList['+i+'].carOwnerName:' + name + 
//			',infoList['+i+'].carOwnerMobilePhoneNo:' + mobile +
//			',infoList['+i+'].carModel:' + owerCar + 
//			',infoList['+i+'].customerName:' + nameSugg + 
//			',infoList['+i+'].customerMobilePhoneNo:' + mobileSugg + 
//			',infoList['+i+'].customerProvince:' + prov + 
//			',infoList['+i+'].customerCity:' + city + 
//			',infoList['+i+'].vin:' + vin + 
//			',infoList['+i+'].intentionalBuyDateRange:' + buytime + 
//			',infoList['+i+'].intentionalCarModel:' + buycar + 
//			',infoList['+i+'].activityName:全车系新车主推荐活动' + 
//			',infoList['+i+'].channel:' + channel +','
		}
		
		$(".confirmCopy").html(dataStr);
		$(".confirmLay").fadeIn();
		$(".confirm").on("touchstart",function(){
				if(block == false){
				block = true;
				console.log(submitData);
				saveinfor(submitData);
			}
		})
		
//		
//		
		
		
//		console.log(city);

	})
})

var urlHost = "/";

//var urlHost = "http://yiche.changanfordwechat.com/";

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



function saveinfor(dd){
	

	$.ajax({  
		url: 'http://www.changanfordclub.com/infocollection/potentialcustomer/add.jspx',
		data:dd,
//			'infoList[0].carOwnerName':name,
//			'infoList[0].carOwnerMobilePhoneNo':mobile,
//			'infoList[0].carModel':owerCar,
//			'infoList[0].customerName':nameSugg,
//			'infoList[0].customerMobilePhoneNo':mobileSugg,
//			'infoList[0].customerProvince':prov,
//			'infoList[0].customerCity':city,
//			'infoList[0].vin':vin,
//			'infoList[0].intentionalBuyDateRange':buytime,
//			'infoList[0].intentionalCarModel':buycar,
//			'infoList[0].activityName':'全车系新车主推荐活动',
//			'infoList[0].channel':channel,	
		
		type: 'post',
		cache: false,
		async: false,
		dataType: 'json',
		success: function(value) {
			block = false;
			if(value.status == '1'){
				//alert('提交成功');
				$(".successLay").fadeIn();
				$(".confirmLay").fadeOut();
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


$(".success").on("touchstart",function(){
	$(".successLay").fadeOut();
})
$(".close").on("touchstart",function(){
	$(".confirmLay").fadeOut();
})


function validateVin(vin){
		if(vin.length!=17){
			return false;
		}
		var prefix=vin.substring(0,3).toUpperCase();
		if(prefix!="LVS"&&prefix!="LVR"){
			return false;
		}
		
		var endstr=vin.substring(11,17);
		var reg1=/^\d+$/;
		var reg2=/^[A-Za-z]+$/;
		if(!endstr.match(reg1)){
			return false;
		}
		var middlestr=vin.substring(10,11);
		if(!middlestr.match(reg2)){
			return false;
		}
		return true;
	}


