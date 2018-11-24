 
var windowHeight = $(window).height();  

$(function () {

 
	block = false; 
	window.agreeNotice = true; 
	window.tabOwner = true;
	var chann = urlRequest("channel");
	window.channel = chann.split("#")[0]; 

	initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", ""); 
	loadData();
	    
    $(".ruleTxt").on("click", function(){         
		$("#ruleOverlay").fadeOut();
	})

	$(".reasonRadio>div>label").click(function(){ 
		if ($(this).hasClass('active')) { 
			window.agreeNotice = false; 
			$(this).removeClass('active');  
		}else{ 
			window.agreeNotice = true; 
			$(this).addClass('active');  
		}  
	}) 
 
	$(".succWindow").on("touchstart",function(){
		$("#shareOverlay").fadeIn();
		$("#succOverlay").fadeOut();
	})
	
	$("#shareOverlay").on("touchend",function(){
		$("#shareOverlay").fadeOut();
	})
	
	 

	$(".submit").on("click",function(){
		var name = $('.inputName').val(); 
		var nameid = $('.inputName').data('myid');  

		var sex = $('.sexRadio input[name="sex"]:checked').data('myid');  

		var mobile = $('.inputMobi').val(); 
		var mobileid = $('.inputMobi').data('myid'); 

		var prov = $(".province").find("option:selected").text(); 

		var city = $(".city").find("option:selected").text();
		var cityid = $(".city").data('myid');

		var age = $(".age").find("option:selected").data('myid');

		var salery = $(".salery").find("option:selected").data('myid');
		var driver = $('.driverRadio input[name="driver"]:checked ').data('myid'); 

		var mycar = $(".styleRadio input[name='styCar']:checked").data('myid');  //感兴趣的车型
		var buytime = $(".timeRadio input[name='time']:checked").data('myid'); //计划何时再购车

		var mouny = $(".myMoney").find("option:selected").data('myid'); //再购车预算  
		var brandid = $(".brandRadio input[name='brand']:checked").data('myid');//考虑的购车品牌，其他 
		var brand = '';

		var reason = $(".reasonRadio input[type='checkbox']:checked");//购买因素 ？多选
		var reasonList = [];
		for(var i=0;i<reason.length;i++){
			reasonList.push($(reason[i]).data('myid'));
		} 

		var arv4s = $(".arv4sRadio input[name='arv']:checked").data('myid');//到访过4s店 
		var info = $(".infoRadio input[name='info']:checked").data('myid');//接受更多资讯
 		   
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if(sex=='' || sex == undefined){
			alert('请选择您的性别！')
			return false;
		}   
 
		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}  
		if (city == "请选择" ||prov == "请选择" ) {
			alert("请选择省市！");
			return false;
		}
		if (age == "请选择"|| age == undefined) {
			alert("请选择您的年龄！");
			return false;
		}		
		if (salery == "请选择" || salery == undefined) {
			alert("请选择您的收入！");
			return false;
		}
		if (driver == "" || driver == undefined) {
			alert("请选择您是否拥有驾照！");
			return false;
		}  
		if (mycar == "" || mycar == undefined) {
			alert("请选择感兴趣的车型！");
			return false;
		}
		if (buytime == "" || buytime == undefined) {
			alert("请选择计划何时再购车");
			return false;
		}

		if (mouny == "请选择" || mouny == undefined) {
			alert("请选择购车预算");
			return false;
		}
		if (brandid == "" || brandid == undefined) {
			alert("请选择考虑的购车品牌"); 
			return false;
		}
		if(brandid == '213'){
			brand = $('.inputOther').val();
			if(brand == ""){
				alert("请输入其他品牌");
				return false;
			}
		}

		if (reasonList.length == 0 || reasonList == undefined) {//多选
			alert("请选择购买的关键因素");
			return false;
		}
		if (arv4s == "" || arv4s == undefined) {
			alert("请选择是否到访过4S店");
			return false;
		}
		if (info == "" || info == undefined) {
			alert("请选择是否原因接受更多的品牌资讯");
			return false;
		}

		if(block == false){
			block = true
                        city=$("#buyingDealerProvince").find("option:selected").text()+city;
			saveinfor(nameid,name,sex,mobile,mobileid,city,cityid,age,salery,driver,mycar,buytime,mouny,brandid,brand,reasonList,arv4s,info)
		}
	
	});

	
})

function saveinfor(nameid,name,sex,mobile,mobileid,city,cityid,age,salery,driver,mycar,buytime,mouny,brandid,brand,reasonList,arv4s,info){  
 console.log(nameid,name,sex,mobile,mobileid,city,cityid,age,salery,driver,mycar,buytime,mouny,brandid,brand,reasonList,arv4s,info) 
var mydata = {
	       
			"surveyCode": "DREAM_WORKS",
			"unitAnswerCode":'DREAM_WORKS_A3',
			"unitAnswerValue":mobile,
			"answerSheetlist[0].answer.id": nameid,
			"answerSheetlist[0].answerMemo":name,

			"answerSheetlist[1].answer.id":sex,
			"answerSheetlist[1].answerMemo":"",

			"answerSheetlist[2].answer.id":mobileid,
			"answerSheetlist[2].answerMemo":mobile,

			"answerSheetlist[3].answer.id":cityid,
			"answerSheetlist[3].answerMemo":city,

			"answerSheetlist[4].answer.id":age,
			"answerSheetlist[4].answerMemo":"",

			"answerSheetlist[5].answer.id":salery,
			"answerSheetlist[5].answerMemo":"",

			"answerSheetlist[6].answer.id":driver,
			"answerSheetlist[6].answerMemo":"",

			"answerSheetlist[7].answer.id":mycar,
			"answerSheetlist[7].answerMemo":"",

			"answerSheetlist[8].answer.id":buytime,
			"answerSheetlist[8].answerMemo":"",

			"answerSheetlist[9].answer.id":mouny,
			"answerSheetlist[9].answerMemo":"",

			"answerSheetlist[10].answer.id":brandid,
			"answerSheetlist[10].answerMemo":brand,
 
			"answerSheetlist[11].answer.id":arv4s,
			"answerSheetlist[11].answerMemo":"",
 
			"answerSheetlist[12].answer.id":info,
			"answerSheetlist[12].answerMemo":"" 
		};
for(var i=0;i<reasonList.length;i++){
	mydata['answerSheetlist['+ (13+i) +'].answer.id'] = reasonList[i];
} 

	$.ajax({  
		url: '../../../survey/addAnswerSheetForAnonymous.jspx',
		data:mydata,
		type: 'post',
		cache: false,
		async: false,
		dataType: 'json',
		success: function(value) {
			block = false;
			if(value.status == '5'){ 
                $('.ruleTxt').css('bottom',windowHeight/2-110);
				$(".popup").fadeIn();
			}else if(value.status=='4'){
				alert('该手机号已经参与过本此调研，请勿重复提交！');
           	}else {
				alert(value.message);
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
	});

	
}



//初始化数据
function initControls(){ 
	$('.inputName').empty(); 
	$('.age').empty();
	$('.salery').empty(); 
	$('.myMoney').empty();
	
	// 读取 var myid= $('.inputName').data('data-myid'); console.log(myid); 
}


function bondData(obj,data){
	obj.append('<option  value="" data="" selected>请选择</option>');
	//console.log(data); 
	for(var i=0;i<data.length;i++){
		obj.append('<option value="'+data[i].name+'" data-myid="'+data[i].id+'" >'+data[i].name+'</option>');
		//console.log(data[i].name); 
	}
}

function bondList(obj,data,nm,tp){
	for(var i=0;i<obj.length;i++){
		$(obj[i]).html('<input type="'+ tp +'" name="'+ nm +'" data-myid="'+ data[i].id +'" value="'+ data[i].name +'" class="radio"><label for="f" class="radio">'+ data[i].name +'</label><br>');
		$(obj[i]).data('data-myid',data[i].id); 

		// console.log(data[i].id + nm);// + data[i].name + data[i].id
	}
}


/**
 * 加载经销商
 */
function loadData( ){ 
	var submitData={"surveyId":"5"}; 
	$.ajax( {
		url :'../../../survey/findAllQuestionBySurveyId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
			initControls();   
			$('.inputName').data('myid',result[0].answerList[0].id); 

			$('.sexRadio').data('data-myid',result[1].answerList[0].id);  
			bondList($('.stysx'),result[1].answerList,'sex','radio');


			$('.inputMobi').data('myid',result[2].answerList[0].id); 

			$('.city').data('myid',result[3].answerList[0].id); 
			
 
			$('.age').data('data-myid',result[4].answerList[0].id); 
			bondData($('.age'),result[4].answerList);

			$('.salery').data('data-myid',result[5].answerList[0].id); 
			bondData($('.salery'),result[5].answerList);

			$('.driverRadio').data('data-myid',result[6].answerList[0].id); 
			bondList($('.stydr'),result[6].answerList,'driver','radio');

			$('.styleRadio').data('data-myid',result[7].answerList[0].id); 
			bondList($('.stylb'),result[7].answerList,'styCar','radio');

			$('.timeRadio').data('data-myid',result[8].answerList[0].id); 
			bondList($('.stytm'),result[8].answerList,'time','radio');

			$('.myMoney').data('data-myid',result[9].answerList[0].id); 
			bondData($('.myMoney'),result[9].answerList); 
 
			$('.brandRadio').data('data-myid',result[10].answerList[0].id); 
			bondList($('.stybd'),result[10].answerList,'brand','radio');

 			$('.reasonRadio').data('data-myid',result[11].answerList[0].id); 
			bondList($('.styrs'),result[11].answerList,'brand','checkbox');

			$('.arv4sRadio').data('data-myid',result[12].answerList[0].id);
			bondList($('.sty4s'),result[12].answerList,'arv','radio');

			$('.infoRadio').data('myid',result[13].answerList[0].id);
			bondList($('.styif'),result[13].answerList,'info','radio');


			var myid= $('.inputName').data('data-myid'); console.log(myid); 
			var myid2= $('.sexRadio').data('data-myid'); console.log(myid2); 
			var myid3= $('.inputMobi').data('data-myid'); console.log(myid3); 
			var myid4= $('.city').data('data-myid'); console.log(myid4); 
			var myid5= $('.age').data('data-myid'); console.log(myid5); 
			var myid6= $('.salery').data('data-myid'); console.log(myid6); 
			var myid7= $('.driverRadio').data('data-myid'); console.log(myid7); 
			var myid8= $('.styleRadio').data('data-myid'); console.log(myid8); 
			var myid13= $('.styleRadio').data('data-myid'); console.log(myid13 + '4s'); 
  
			for(var i=0;i<result.length;i++){
				var t=result[i];

				// if(defaultValue==t.name){
					//$("#"+selectId).append('<option   value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				// }else{
					//$("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
				// }
			}
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
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
					$("#"+selectId).append('<option selected  value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
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
var county_sh=["黄浦区","卢湾区","徐汇区","长宁区","静安区","普陀区","闸北区","虹口区","杨浦区","闵行区","宝山区","嘉定区","浦东新区","金山区","松江区","青浦区","南汇区","奉贤区","崇明县","城桥镇"];
var county_bj=["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷区","密云县","延庆县","延庆镇"];
var county_cq=["渝中区","大渡口区","江北区","沙坪坝区","九龙坡区","南岸区","北碚区","万盛区","双桥区","渝北区","巴南区","万州区","涪陵区","黔江区","长寿区","合川市","永川区市","江津市","南川市","綦江县","潼南县","铜梁县","大足县","荣昌县","璧山县","垫江县","武隆县","丰都县","城口县","梁平县","开县","巫溪县","巫山县","奉节县","云阳县","忠县","石柱土家族自治县","彭水苗族土家族自治县","酉阳土家族苗族自治县","秀山土家族苗族自治县"];
var county_tj=["和平区","河东区","河西区","南开区","河北区","红桥区","塘沽区","汉沽区","大港区","东丽区","西青区","津南区","北辰区","武清区","宝坻区","蓟县","宁河县","芦台镇","静海县","静海镇"];
function loadCounty(selectId,county){
	$("#"+selectId).empty();
	for(var i=0;i<county.length;i++){
		var t=county[i];
      $("#"+selectId).append('<option value="'+t+'" data="'+t+'" >'+t+'</option>');
	};
}
/**
 * 加载城市
 */
function loadCity(selectId,defaultValue,provinceId,dealerSelectId,defaultDealer){
	if(provinceId==""){
		return;
	}
	if(provinceId=="1"){
	    loadCounty(selectId,county_bj);
		return ;
	}
	if(provinceId=="2"){
		loadCounty(selectId,county_tj);
		return ;
	}
	if(provinceId=="3"){
		loadCounty(selectId,county_sh);
		return ;
	}
	if(provinceId=="4"){
		loadCounty(selectId,county_cq);
		return ;
	}
	var submitData={"provinceId":provinceId};
	$.ajax( {
		url :'../../../dealer/findAllCityByProvinceId.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) { 
			$("#"+selectId).empty();
			$("#"+selectId).append('<option value="" data="" selected>请选择</option>');
			for(var i=0;i<result.length;i++){
				var t=result[i];
				if(defaultValue==t.name){
					$("#"+selectId).append('<option selected value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
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


 
function urlRequest(paras)  //获取url参数方法
	{ 
		var url = location.href; 
		var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
		var paraObj = {};
		for (var i=0; j=paraString[i]; i++){ 
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
		} 
		var returnValue = paraObj[paras.toLowerCase()]; 
		if(typeof(returnValue)=="undefined"){ 
		return ""; 
		}else{ 
		return returnValue; 
	} 
}