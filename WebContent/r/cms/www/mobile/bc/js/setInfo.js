var urlHost = "/"; 
var windowHeight = $(window).height(); 
//dealer/findAllProvince.jspx http://www.changanfordclub.com/
//var citiAry =["TBD","安庆","安阳","蚌埠","包头","宝鸡","保定","滨州","沧州","昌吉","常德","常州","朝阳","承德","池州市","滁州","慈溪","大理","大连","大庆","丹东","丹阳","德阳","德州","东莞","东阳","东营","佛山","福州","抚顺","抚州","阜阳","赣州","高密","巩义","广州","贵阳","桂林","哈尔滨","海口","海宁","合肥","菏泽","鹤壁","衡阳","呼和浩特","湖州","怀化","淮安","淮南","惠州","吉安","吉林","济南","济宁","济源","佳木斯","嘉兴","江阴","焦作","金华","锦州","晋江","荆门","景德镇","靖江","九江","柯桥","昆明","莱州","兰州","乐山","丽江","丽水","连云港","聊城","临沂","浏阳","柳州","六安","娄底","洛阳","漯河","马鞍山","绵阳","牡丹江","南昌","南京","南宁","南通","南阳","宁波","宁乡","盘锦","平顶山","濮阳","齐齐哈尔","秦皇岛","青岛","青州","清远","衢州","曲靖","泉州","如东","如皋","三门峡","厦门","汕头","商丘","上饶","上虞","邵阳","绍兴","深圳","沈阳","嵊州","十堰","石家庄","寿光","松原","苏州","太原","泰兴","泰州","天津","铜陵","万州","威海","潍坊","温州","乌鲁木齐","无锡","芜湖","吴江","武汉","西安","西宁","锡林郭勒","湘潭","襄阳","萧山","新乡","新余","信阳","宿迁","宿州","徐州","许昌","烟台","扬州","宜昌","宜春","义乌","益阳","银川","营口","永康","永州","余姚","玉林","玉溪","岳阳","运城","枣庄","张家港","漳州","长春","长沙","长兴","长治","镇江","郑州","郑州新郑","中山","重庆","周口","珠海","株洲","诸城","诸暨","驻马店","淄博"];

$(function () {
	block = false; 
	window.agreeNotice = true;
	window.agreeNotice2 = true;
	window.tabOwner = true;
	var chann = urlRequest("channel");
	window.channel = chann.split("#")[0];

$(".guakai").on("touchstart",function(){
	$(".guakai").hide()
	
})

	// 初始化城市
    //for(var i=0;i<citiAry.length;i++){ 
    	//$(".newCity").append('<option value="'+citiAry[i]+'" data="'+citiAry[i]+'" >'+citiAry[i]+'</option>');
    //}
	initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", "");
	initSelectData("buyingDealerProvince2","","buyingDealerCity2","", "carBuyingDealer2", "");
	 
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

        $(".ruleTxt").on("touchstart", function(){
                                
      				$("#ruleOverlay").fadeOut();
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
	

	$(".submit").on("touchstart",function(){
		
		var mobile = $('.inputMobi').val();
		var name = $('.inputName').val();

		var mycity //= $('.inputCity').val(); //单独导入的城市
		var prov = $(".province").find("option:selected").text();
		var city = $(".city").find("option:selected").text();
		var dealer = $(".dealer").find("option:selected").text();
		var buytime = $(".dealTime2").find("option:selected").text(); //计划何时再购车
		var mouny = $(".myMoney").find("option:selected").text(); //再购车预算 
		var mycar = $(".myCar").find("option:selected").text();
		var myYear = $(".myYear").find("option:selected").text();
		var sex = $('.sexRadio input[name="sex"]:checked ').val(); 

		var temp = $("label.radioed");
		var moreCar = []; 
		for(var i in temp){
			temp[i].innerText != undefined ? moreCar.push(temp[i].innerText):null;
		}  

		
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		} 

		// if (mycity === "请选择") {
		// 	alert("请选择城市");
		// 	return false;
		// } 
		if (mycar === "请选择") {
			alert("请选择感兴趣的车型");
			return false;
		}
		if (buytime === "请选择") {
			alert("请选择计划何时再购车");
			return false;
		}

		if (mouny === "请选择") {
			alert("请选择购车预算");
			return false;
		}
		if (city === "请选择" ||prov === "请选择" ) {
			alert("请选择省市");
			return false;
		}
                 if (city === "" ||prov === "" ) {
			alert("请选择省市");
			return false;
		}
		if (dealer === "请选择") {
			alert("请选择经销商");
			return false;
		}
		
		if(block == false){

			block = true;
			saveinfor(name,sex,mobile,mycity,mouny,prov,city,dealer,buytime,mycar,window.channel,moreCar);

		}
	
	})

	
})



function saveinfor(name,sex,mobile,mycity,mouny,prov,city,dealer,buytime,mycar,channel,moreCar){

	$.ajax({  
		url: 'add.jspx',
		data:{
			'infoList[0].carOwnerName':name,
			'infoList[0]. customerGender':sex, 
			'infoList[0].carOwnerMobilePhoneNo':mobile,
			'infoList[0].carModel': mycar,
			'infoList[0].customerName':name,
			'infoList[0].customerMobilePhoneNo':mobile,
			//'infoList[0].customerCity':mycity,
			'infoList[0].budget':mouny, 
			'infoList[0].customerProvince':prov,
			'infoList[0].customerCity':city,
			'infoList[0].intentionalDealer':dealer,
			'infoList[0].intentionalBuyDateRange':buytime,
			'infoList[0].intentionalCarModel': moreCar.toString(), //预购车型
 
			'infoList[0].activityName':'BC',
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
                $('.ruleTxt').css('bottom',windowHeight/2-110);
				doLuckyDraw(city,mobile);
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
		url : '../../../dealer/findAllProvince.jspx',
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
		url : '../../../dealer/findAllCityByProvinceId.jspx',
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
		url :'../../../dealer/findAllDealerByCityId.jspx',
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



function doLuckyDraw(city,mobilePhoneNo) {

var city_py={"TBD":"tbd",
"杭州市":"BC_HANGZHOU",
"大连市":"Dalian",
"东莞市":"Dongguan",
"福州市":"Fuzhou",
"贵阳市":"guiyang",
"哈尔滨":"Harbin ",
"合肥市":"hefei",
"呼和浩特市":"Hohehot",
"济南市":"jinan",
"昆明市":"kunming",
"兰州市":"Lanzhou ",
"南昌市":"Nanchang",
"南京市":"Nanjing",
"南宁市":"Nanning",
"南通市":"nantong",
"宁波市":"Ningbo",
"厦门市":"xiamen",
"深圳市":"Shenzhen",
"苏州市":"suzhou",
"太原市":"Taiyuan",
"天津市":"tianjan",
"潍坊市":"huaifang",
"温州市":"wenzhou",
"乌鲁木齐市":"Urumqi",
"武汉市":"wuhan",
"西安市":"xian",
"西宁市":"Xining",
"徐州市":"xuzhou",
"烟台市":"yantai",
"义乌市":"yuwu",
"银川市":"Yinchuan",
"长沙市":"Changsha",
"郑州市":"zhenzhou",
"重庆市":"chongqing"};


      var ccc = "BC_"+city_py[city];
      var code=ccc.toUpperCase();
      $.ajax({
             url:'../../../ocms/toolkit/luckydraw/doDrawForPotentialCustomerUserJSONP.htm',
             dataType:"jsonp",
             data:{"mobilePhoneNo":mobilePhoneNo,"code":code},
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
			        ratio: .6
			    }, function() {
			        //$(".notice").fadeIn();
			        this.clearCover();
			    });
			    $(".luckyDraw").show();
              }
             }
        });
}


