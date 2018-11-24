var openid = urlRequest("openId"); 
console.log("wechatId: " + openid);

var urlHost = "/"; 
var windowHeight = $(window).height(); 
//dealer/findAllProvince.jspx http://www.changanfordclub.com/
//var citiAry =["TBD","安庆","安阳","蚌埠","包头","宝鸡","保定","滨州","沧州","昌吉","常德","常州","朝阳","承德","池州市","滁州","慈溪","大理","大连","大庆","丹东","丹阳","德阳","德州","东莞","东阳","东营","佛山","福州","抚顺","抚州","阜阳","赣州","高密","巩义","广州","贵阳","桂林","哈尔滨","海口","海宁","合肥","菏泽","鹤壁","衡阳","呼和浩特","湖州","怀化","淮安","淮南","惠州","吉安","吉林","济南","济宁","济源","佳木斯","嘉兴","江阴","焦作","金华","锦州","晋江","荆门","景德镇","靖江","九江","柯桥","昆明","莱州","兰州","乐山","丽江","丽水","连云港","聊城","临沂","浏阳","柳州","六安","娄底","洛阳","漯河","马鞍山","绵阳","牡丹江","南昌","南京","南宁","南通","南阳","宁波","宁乡","盘锦","平顶山","濮阳","齐齐哈尔","秦皇岛","青岛","青州","清远","衢州","曲靖","泉州","如东","如皋","三门峡","厦门","汕头","商丘","上饶","上虞","邵阳","绍兴","深圳","沈阳","嵊州","十堰","石家庄","寿光","松原","苏州","太原","泰兴","泰州","天津","铜陵","万州","威海","潍坊","温州","乌鲁木齐","无锡","芜湖","吴江","武汉","西安","西宁","锡林郭勒","湘潭","襄阳","萧山","新乡","新余","信阳","宿迁","宿州","徐州","许昌","烟台","扬州","宜昌","宜春","义乌","益阳","银川","营口","永康","永州","余姚","玉林","玉溪","岳阳","运城","枣庄","张家港","漳州","长春","长沙","长兴","长治","镇江","郑州","郑州新郑","中山","重庆","周口","珠海","株洲","诸城","诸暨","驻马店","淄博"];
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;
i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();
a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;
m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
ga('create', 'UA-69201345-2', 'auto');
ga('send', 'pageview');

ga('set','campaignName','新蒙迪欧皇家加勒比');
ga('set','campaignSource',openid);
ga('set','campaignMedium','weixin');

$(function () {
	block = false; 
	window.agreeNotice = true;
	window.agreeNotice2 = true;
	window.tabOwner = true;
	var chann = urlRequest("channel");
       
	window.channel = chann.split("#")[0];

	// 初始化城市
    //for(var i=0;i<citiAry.length;i++){ 
    	//$(".newCity").append('<option value="'+citiAry[i]+'" data="'+citiAry[i]+'" >'+citiAry[i]+'</option>');
    //}


	initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", ""); 
	 
	$(".owner").on("touchstart",function(){
		if (window.tabOwner == true) {
			console.log("nonething happen");
		} else {
			$(".owner>img").attr("src","/r/cms/www/mobile/taurusmgm/assets/i/owerACT.png?1212");
			$(".notowner>img").attr("src","/r/cms/www/mobile/taurusmgm/assets/i/notOwner.png?1212");
			$(".ownerForm").show();
			$(".newbieForm").hide();
			window.tabOwner = true;
		}
	})
	$(".notowner").on("touchstart",function(){
		if (window.tabOwner == false) {
			console.log("nonething happen");
		} else {
			$(".owner>img").attr("src","/r/cms/www/mobile/taurusmgm/assets/i/owner.png?1212");
			$(".notowner>img").attr("src","/r/cms/www/mobile/taurusmgm/assets/i/notOwnerACT.png?1212");
			$(".ownerForm").hide();
			$(".newbieForm").show();
			window.tabOwner = false;
		}
	})
 
	$(".styleRadio>div>label").click(function(){
		if (window.agreeNotice) {
			window.agreeNotice = false; 
			$(this).addClass('radioed');
		} else {
			window.agreeNotice = true;
			$(this).removeClass('radioed'); 
		}
	})

	$("#chk02").click(function(){
		if (window.agreeNotice2) {
			window.agreeNotice2 = false;
			$("#chk02").attr('src','/r/cms/www/mobile/taurusmgm/assets/i/checkbox.png');
		} else {
			window.agreeNotice2 = true;
			$("#chk02").attr('src','/r/cms/www/mobile/taurusmgm/assets/i/checked.png');
		}
	})
	
	$(".succWindow").on("touchstart",function(){
		$("#shareOverlay").fadeIn();
		$("#succOverlay").fadeOut();
	})
	
	$("#shareOverlay").on("touchend",function(){
		$("#shareOverlay").fadeOut();
	})
	
	 

	$("#btn_submit").on("click",function(){
		
		var mobile = $('.inputMobi').val();
		var name = $('.inputName').val(); 
		var prov = $(".province").find("option:selected").text();
		var city = $(".city").find("option:selected").text();
		var dealer = $(".dealer").find("option:selected").text();
		var beizhu =  $('.inputNotice').val();   
 
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
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
		  
		if(block == false){
			block = true
			saveinfor(name,mobile,prov,city,dealer,beizhu,window.channel)
		}
	
	})

	
})

function saveinfor(name,mobile,prov,city,dealer,beizhu,channel){
        var wechatId=urlRequest("openid");
	$.ajax({  
		url: urlHost +'infocollection/potentialcustomer/add.jspx',
		data:{
			'infoList[0].carOwnerName':name, 
			'infoList[0].carOwnerMobilePhoneNo':mobile,    
			'infoList[0].customerProvince':prov,
			'infoList[0].customerCity':city,
			'infoList[0].carOwnerDealer':dealer,
			'infoList[0].budget':'备注:'+beizhu,
			'infoList[0].activityName':'newmondeo',
			'infoList[0].carModel': '新蒙迪欧',
                        'infoList[0].wechatId': wechatId,
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
		error :function(e) {
			console.log(e);
			alert('system error');
			block = false;
		}					
	})
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
          alert("网络或数据异常，操作失败！加载省");
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