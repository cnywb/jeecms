//获取url参数方法
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

var openid = urlRequest("openId"); 
console.log("wechatId: " + openid);

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-69201345-22', 'auto'); 
  ga('set','campaignSource',openid);

	function doTrack(name) {
	    console.log('track', name)
	    name = name.toUpperCase();
	    ga('send', {
	       'hitType': 'event', // Required.
	        'eventCategory': "Link", // Required.
	        'eventAction': 'Click', // Required.
	        'eventLabel': "BUT_" + name,
	        'eventValue': 1
	    });
	} 
	;
	function doPageview(name) {
	    console.log('doPageview', name);
	    ga('send', 'pageview', {
	        'page': name,
	        'title': "UA",
	    });
	};

	doPageview("/Edge MGM-WeChat/Landing Page");


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
			$("#chk01").attr('src','/r/cms/www/mobile/edgeMGM/img/checkbox.png');
		} else {
			window.agreeNotice = true;
			$("#chk01").attr('src','/r/cms/www/mobile/edgeMGM/img/checked.png');
		}
	})

function saveinfor(dd){ 
    var jsonStr=JSON.stringify(dd);
    //alert(jsonStr);
    //return;

    $.ajax( {
        url :'../../../ocms/toolkit/infocollection/data/addJSONP.htm',
        type : 'post',
        dataType:'jsonp',
        jsonp:"jsonpcallback",
        data:{"jsonStr":jsonStr},
        success : function(result) { 
            //成功提交
                    if(result.status == "11"){ 
                        alert("数据提交成功，谢谢！");
                    } 
                    else{alert(result.message);}
        },error:function(XMLHttpRequest, textStatus, errorThrown){
               alert(textStatus);
                }
    }); 
}	
	
	$('.formsubmitBtn').on('touchstart',function(){
                doTrack("/Edge MGM-WeChat/Submission");
                var dataStr = "";
		var submitData={"campaignCode": "000006",
						"channelCode":chann };
		var dataListAll =[]; 

		var mobile = $('.inputMobi').val();
		var name = $('.inputName').val();
		var mobileSugg = $('.inputsuggMobi').val();
		var nameSugg = $('.inputsuggName').val();
		var owerCar = $(".ownerCar").find("option:selected").text();
		var prov = $(".province").find("option:selected").text();
		var city = $(".City").find("option:selected").text();
		var dealer = $(".dealer").find("option:selected").text();
		var buytime = $(".dealTime").find("option:selected").text();
		
		console.log(buytime );


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
		if (dealer === "请选择") {
			alert("请选择经销商");
			return false;
		}
                if (buytime == "请选择" || buytime == "") {
			alert("请选择预计购车时间");
			return false;
		}
		if(block == false){
		    block = true 
                    var object = new Object();
		    object["buyerName"]=name;
		    object["buyerMobilePhoneNo"]=mobile; 
		    object["potentialBuyerName"]=nameSugg;
                    object["potentialBuyTime"]=buytime; 
		    object["potentialBuyerMobilePhoneNo"]=mobileSugg;
		    object["potentialBuyerProvince"]=prov;
		    object["potentialBuyerCity"]=city; 
                    object["potentialBuyerDealer"]=dealer;
                    object["potentialBuyProductModel"]=owerCar;
                    object["buyerGender"]="UNKNOWN";
                    object["potentialBuyerGender"]="UNKNOWN";  
		    object["wechatId"]=openid;    
		    dataListAll.push(object); 
                        
		}
                submitData["dataList"] = dataListAll; 
                ga('set','campaignContent',name+"-"+mobile); 
                console.log(submitData);
                saveinfor(submitData); 
	}) 
	
})

var urlHost = "/";



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
			if(value.status == '1' || value.status == 1){
				alert('提交成功');
				
			}else{
				alert(value.message)
			}
		},
		error :function(e) {
			alert('system error');
			block = false;
		}					
	})
} 