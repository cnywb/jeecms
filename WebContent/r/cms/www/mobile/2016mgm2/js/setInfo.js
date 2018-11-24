var openid = urlRequest("openId");
var medium = urlRequest("utm_source");
if(medium != "")
  openid = openid + ":" + medium;
console.log("wechatId: " + openid);

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-69201345-2', 'auto');
  ga('send', 'pageview');
ga('set','campaignName','2016mgm2');
ga('set','campaignSource',openid);
ga('set','campaignMedium',medium);

 
window.alert = function(name){
 var iframe = document.createElement("IFRAME");
iframe.style.display="none";
iframe.setAttribute("src", 'data:text/plain,');
document.documentElement.appendChild(iframe);
window.frames[0].window.alert(name);
iframe.parentNode.removeChild(iframe);
}

function doTrack(name) {
			    console.log('track', name)
			    name = name.toUpperCase();
			    ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "2016mgm2", // Required.
			        'eventAction': 'click', // Required.
			        'eventLabel': "BUT_" + name,
			        'eventValue': 1
			    });
			};
			function doPageview(name) {
			    console.log('doPageview', name);
			    ga('send', 'pageview', {
			        'page': name,
			        'title': "2016mgm2",
			    });
			};

function toDate(str){
    var sd=str.split("-");
    return new Date(sd[0],sd[1]-1,sd[2]);
   }

$(function () {


     
      var d1=toDate("2016-12-31");
      var d2=new Date();
      
      if(d2.getTime()>d1.getTime()){
        alert("请注意本活动已下线！");
      }


	doPageview('/CAF WeChat/Owner Refer'); 
        //防止页面滚动
	$(document).on('touchmove.prevent', function(e) {      e.preventDefault();	}); 

    $(".guakai").on("touchstart",function(){
		$(".guakai").hide(); 
	})

	block = false;
	var chann = urlRequest("channel");
	window.channel = chann.split("#")[0];
	$(".rulebtn").on("touchstart",function(){ 
                   ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "Link", // Required.
			        'eventAction': 'Click', // Required.
			        'eventLabel': "/CAF WeChat Event/Owner Refer/Rules",
			        'eventValue': 1
			    }); 
		$(".ruleLay").fadeIn(); 
	});
	$(".ruleLay img").on("touchstart",function(){$(".ruleLay").fadeOut();});
	initSelectData("buyingDealerProvince1","","buyingDealerCity1","", "", "");
        // var canvasOne = document.getElementById('frameAnimate');
		// var frame1 = new frameAnimate(canvasOne, {
		// 	width: 234,
		// 	height: 81,
		// 	fps:24,
		// 	frame: 12,
		// 	loop: true
		// }, function() {
		// 	console.log("==stop==");
		// });
        // frame1.play();
	var ppNum = 1; 
        var btnstr= ""; 
        $(".addpp"+ String(ppNum)).bind("touchstart",addPP);

	function addPP(){  
		ppNum = ppNum +1;console.log(ppNum);
                btnstr= '<img src="/r/cms/www/mobile/2016mgm2/img/addmore.jpg" class="addpp addpp'+ ppNum +' ab">';
                if(ppNum == 2){
                     //释放滚动
                     $(".formWrap").on("touchstart", function() {
		 	$(document).off('touchmove.prevent');
		     }); 
		     $(".formWrap").on("touchend", function() {
		 	$(document).on('touchmove.prevent', function(e) {
		 		e.preventDefault();
		 	});
		     }) 
                     $(".tField").append('<div class="person'+ ppNum +' pers"><input type="text" class="inputBlock inputsuggName ab" id="name'+ ppNum +'" maxlength="11"/><input type="tel" class="inputBlock inputsuggMobi ab" id="mobi'+ ppNum +'" maxlength="11"/><select class="selectArea City ab" id="buyingDealerCity'+ ppNum +'"><option>所在城市</option></select><select class="selectArea province ab" id="buyingDealerProvince'+ ppNum +'"></select>'+ btnstr+'</div>');
                }else{
                     $(".tField").append('<div class="person'+ ppNum +' pers"><input type="text" class="inputBlock inputsuggName ab" id="name'+ ppNum +'" maxlength="11"/><input type="tel" class="inputBlock inputsuggMobi ab" id="mobi'+ ppNum +'" maxlength="11"/><select class="selectArea City ab" id="buyingDealerCity'+ ppNum +'"><option>所在城市</option></select><select class="selectArea province ab" id="buyingDealerProvince'+ ppNum +'"></select></div>');
                } 
		initSelectData("buyingDealerProvince"+ ppNum,"","buyingDealerCity"+ ppNum,"", "", "");

                $(".addpp"+ String(ppNum)).bind("touchstart",addPP);
		 

		$(".addpp"+ String(ppNum-1)).unbind("touchstart",addPP); 
		$(".addpp"+ String(ppNum-1)).fadeOut(); 
	} 
	
	$('.submit').on('touchstart',function(){ 
                ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "Link", // Required.
			        'eventAction': 'Click', // Required.
			        'eventLabel': "/CAF WeChat Event/Owner Refer/Refer",
			        'eventValue': 1
			    });  

  var d1=toDate("2016-12-31");
      var d2=new Date();
      
      if(d2.getTime()>d1.getTime()){
        alert("请注意本活动已下线！");  return;
      }

		var dataStr = "";
		var submitData={"campaignCode": "000001",
						"channelCode":chann };
		
		var dataListAll =[];
		
		for (i=0;i<ppNum;i++) {
			var object ={};

			q = i +1;
			var mobile = $('.owerField .inputMobi').val();
			var name = $('.owerField .inputName').val(); 
			var vin = $('.owerField .inputVin').val();
			var mobileSugg = $('.tField div:nth-child('+q+') .inputsuggMobi').val();
			var nameSugg = $('.tField div:nth-child('+q+') .inputsuggName').val();
			var prov = $('.tField div:nth-child('+q+') .province').find("option:selected").text();
			var city = $('.tField div:nth-child('+q+') .City').find("option:selected").text();  
			
			if(name==''){
			alert('请输入您的姓名！')
			return false;
			}
			if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
				alert('请输入您正确的手机号码！')
				return false;
			} 
			if (validateVin(vin) == false) {
				alert("请输入您正确的VIN码！");
				return false;
			} 
			if(nameSugg==''){
				alert('请输入被推荐人 '+ q.toString() +' 的姓名！')
				return false;
			}
			if(mobileSugg == '' || !(/^1[34578]\d{9}/.test(mobileSugg)) ){
				alert('请输入被推荐人 '+ String(q) +' 的正确手机号码！')
				return false;
			}
			if (city === "所在城市" || city === "") {
				alert('请选择被推荐人 '+ q.toString() +' 的正确所在省市！');
				return false;
			}  
			object["buyerName"]=name;
			object["buyerMobilePhoneNo"]=mobile; 
			object["potentialBuyerName"]=nameSugg;
			object["potentialBuyerMobilePhoneNo"]=mobileSugg;
			object["potentialBuyerProvince"]=prov;
			object["potentialBuyerCity"]=city;
            object["purchasedProductSerialNumber"]=vin;
            object["potentialBuyProductModel"]="全车系";
            object["buyerGender"]="UNKNOWN";
            object["potentialBuyerGender"]="UNKNOWN";  
			object["wechatId"]=openid;  
            //object["potentialBuyerDealerCode"]="";  
			// dataSt   
			dataListAll.push(object); 
            ga('set','campaignContent',name+"-"+mobile+"-"+vin);
		}
                
		submitData["dataList"] = dataListAll; 
		console.log(submitData);
		saveinfor(submitData,mobile);  

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
		url :'../../../dealer/findAllProvince.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" selected>所在省份</option>');
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
		url :'../../../dealer/findAllCityByProvinceId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" selected>所在城市</option>');
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
		url :'../../../dealer/findAllDealerByCityId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" selected>所在城市</option>');
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



function saveinfor(dd,mobile){ 
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
			alert("提交成功！"); 
			doLuckyDraw(mobile);   
		},error:function(XMLHttpRequest, textStatus, errorThrown){
	 	       alert(textStatus);
     }
	}); 
}	


function doLuckyDraw(mobilePhoneNo) { 
      $.ajax({
             url:'../../../ocms/toolkit/luckydraw/doDrawForCarOwnerUserJSONP.htm',
             dataType:"jsonp",
             data:{"mobilePhoneNo":mobilePhoneNo,"code":"2016MGM2"},
             jsonp:"jsonpcallback",
             success:function(data){
              if (data.status == '9') {
              	$(".lucky").show();
                $(".gongxiCode").html("序列号: " + data.resultId);
              	LuckyCard.case({
			        ratio: .4
			    }, function() {
			        $(".notice").fadeIn();
			        this.clearCover();
			    });
			    $(".luckyDraw").show();
              } else {
              	$(".unlucky").show();
              	LuckyCard.case({
			        ratio: .5
			    }, function() {
			        //$(".notice").fadeIn();
			        this.clearCover();
			    });
			    $(".luckyDraw").show();
              }
             }
        });
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


