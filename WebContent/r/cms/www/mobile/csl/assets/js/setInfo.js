$(function () {
	block = false;
	window.agreeNotice = true;
	window.agreeNotice2 = true;
	window.tabOwner = true;
	var chann = urlRequest("channel");
	window.channel = chann.split("#")[0];


	initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", "");


	$(".preOrderForm .btn_submit").on("touchstart",function(){
		doTrack("/CSL/TD Page/Click")
		var mobile = $('.preOrderForm .inputMobi').val();
		var name = $('.preOrderForm .inputName').val();
		var prov = $(".preOrderForm .province").find("option:selected").text();
		var city = $(".preOrderForm .city").find("option:selected").text();
		var dealer = $(".preOrderForm .dealer").find("option:selected").text();
		var buyCar = $(".buyCar").find("option:selected").text();
		var team = selectedClubCNStr;
		var type = giftTpye;

		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if (city === "市") {
			alert("请选择省市");
			return false;
		}
		if (dealer === "请选择经销商") {
			alert("请选择经销商");
			return false;
		}
		if (buyCar === "试驾车型") {
			alert("请选择试驾车型");
			return false;
		}
		if(block == false){

			block = true
			saveinfor(name,mobile,prov,city,dealer,buyCar,window.channel,team,type)
		}

	})

$(".btn_share").on("touchstart",function(){
doTrack("/CSL/Friend Page/Click")
	$(".submitOk").fadeOut();
	$(".shareLay").fadeIn();
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
			$("#"+selectId).append('<option value="" data="" >请选择</option>');
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
			$("#"+selectId).append('<option value="" data="" >请选择</option>');
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
			$("#"+selectId).append('<option value="" data="" >请选择</option>');
			for(var i=0;i<result.length;i++){
				var t=result[i];
				if(defaultValue==t.name){
                                         if(t.name!="AM001"){
                                         	$("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');

                                         }
				}else{
                                        if(t.name!="AM001"){
					$("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				        }
                                 }
			}
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
	});
}





function saveinfor(name,mobile,prov,city,dealer,owerCar,channel,team,type){
var openId = urlRequest("openId");
	$.ajax({
		url: 'http://www.changanfordclub.com/infocollection/potentialcustomer/add.jspx',
		data:{
			'infoList[0].carOwnerName':name,
                        'infoList[0].customerName':name,
			'infoList[0].carOwnerMobilePhoneNo':mobile,
                        'infoList[0].customerMobilePhoneNo':mobile,
			'infoList[0].carModel':owerCar,
			'infoList[0].customerProvince':prov,
			'infoList[0].customerCity':city,
			'infoList[0].budget':team,
			'infoList[0].fileUrl':type,
			'infoList[0].intentionalDealer':dealer,
			'infoList[0].intentionalCarModel':'全车系',
			'infoList[0].activityName':'中超抢票活动',
'infoList[0].wechatId':openId ,
			'infoList[0].channel':channel
		},
		type: 'post',
		cache: false,
		async: false,
		dataType: 'json',
		success: function(value) {
			block = false;
			if(value.status == '1'){
				//alert('提交成功');
doPageview("/CSL/Friend Page")
				$('.preOrderForm').fadeOut();
				$(".submitOk").fadeIn();
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
