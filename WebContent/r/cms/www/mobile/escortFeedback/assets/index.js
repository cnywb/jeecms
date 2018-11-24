$(function () {
		var urlHost = "http://www.changanfordclub.com/";
var channel = urlRequest("channel");
channel = channel.split("#")[0];
		block = false;
		initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", "");
		orient();
		
		$('.commit').on('touchstart',function(){
			var mobile = $('.mobile').val();
			var name = $('.name').val();
			var prov = $("#buyingDealerProvince").find("option:selected").text();
			var city = $("#buyingDealerCity").find("option:selected").text();
			var dealer = $("#carBuyingDealer").find("option:selected").text();
			console.log(city);
			if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
				alert('请输入正确的手机号！')
				return false;
			}
			if(name=== ""){
				alert('请输入您的姓名！')
				return false;
			}
			if (city === "") {
				alert("请选择省市");
				return false;
			}
			if (dealer === "") {
				alert("请选择经销商");
				return false;
			}
			if(block == false){
				block = true
				saveinfor(name,mobile,prov,city,dealer,channel)
			}
		})

})

/**
 * 初始化三级联动下拉菜单
 */

function initSelectData(provinceSelectId,defaultProvince,citySelectId,defaultCity,dealerSelectId,defaultDealer){
	loadProvince(provinceSelectId,defaultProvince,citySelectId,defaultCity,dealerSelectId,defaultDealer);
	$("#"+provinceSelectId).change(function(){
		$("#"+citySelectId).html("");
		$("#"+dealerSelectId).html("");
		var provinceId=$(this).find("option:selected").attr("data");
		console.log(provinceId);
		loadCity(citySelectId,'',provinceId,'','');
	});
	$("#"+citySelectId).change(function(){
		$("#"+dealerSelectId).prev().html("");
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
			$("#"+selectId).append('<option value="" data="" >省份</option>');
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
	console.log(submitData);
	$.ajax( {
		url :urlHost + 'dealer/findAllCityByProvinceId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" >城市</option>');
			for(var i=0;i<result.length;i++){
				var t=result[i];
				if(defaultValue==t.name){
					$("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
					loadDealer(dealerSelectId, defaultDealer, t.id);
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
 * 加载经销商
 */
function loadDealer(selectId,defaultValue,cityId){
	if(cityId==""){
		return ;
	}
	var submitData={"cityId":cityId};
	console.log("cityId" + cityId);
	$.ajax( {
		url :urlHost + 'dealer/findAllDealerByCityId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" >首选经销商</option>');
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

function saveinfor(name,mobile,province,city,dealer,channel){

	$.ajax({  
		url: urlHost + 'infocollection/potentialcustomer/taurus/add.jspx',
		data:{
			'infoList[0].customerProvince':province,
			'infoList[0].intentionalCarModel':'福睿斯',
			'infoList[0].customerCity':city,
            'infoList[0].channel':channel,
			'infoList[0].customerName':name,
			'infoList[0].customerMobilePhoneNo':mobile,
			'infoList[0].activityName':'福睿斯-新年车主证言',
			'infoList[0].intentionalDealer':dealer,
		},
		type: 'post',
		cache: false,
		async: false,
		dataType: 'json',
		success: function(value) {
			block = false;
			if(value.status == '1'){
alert('感谢你提交信息');
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
	



function orient() {
   // alert('gete');
    if (window.orientation == 0 || window.orientation == 180) {
        $(".Htips").hide();
        orientation = 'portrait';
        return false;
    }
    else if (window.orientation == 90 || window.orientation == -90) {
        $(".Htips").show();;
        orientation = 'landscape';

        return false;
    }
}

/* 在用户变化屏幕显示方向的时候调用*/
$(window).bind( 'orientationchange', function(e){
    orient();
});