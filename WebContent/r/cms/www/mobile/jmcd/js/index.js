$(function() {
	var sh, step = 0,agree = true;
	var $startc1 = $('.init .c1');
	var cartime = 2.4,channel,$audio;
	channel = urlRequest("channel");
	window.channel = channel.split("#")[0]; 
        var openId = urlRequest("openId");
	//var el = document.querySelector(".start .visualbtn");


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

	var urlHost = "/";
	initSelectData("buyingDealerProvince1","","buyingDealerCity1","", "carBuyingDealer", ""); 

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
			$("#"+selectId).append('<option value="-1" data="" selected>*省份选择</option>');
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
			$("#"+selectId).append('<option value="-1" data="" selected>*城市选择</option>');
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
			$("#"+selectId).append('<option value="-1" data="" selected>*经销商选择</option>');
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
	// 开启游戏
	function addevent() {   
		//防止页面滚动
		$(document).on('touchmove.prevent', function(e) {      e.preventDefault();	}); 
		$('.p1').bind("touchend",gotoP2); 

		$('.p1 .content .inpu3 .nb').bind('input propertychange', invide1);
		$('.p1 .content .inpu4 .nb').bind('input propertychange', invide2);

		$('.p3 .get').bind('touchend',getticket);
		$('.p4 .submit').bind('touchstart',submitfn);

		$('.popup .pop').bind('touchstart',clspop);
	}

	function clspop(){
		$('.popup').fadeOut();
                $('.popup').addClass("dn");
	}


	//跳转到填写信息页面
	function getticket(){
doTrack("Win Tickets/Click")
		$('.p3').fadeOut(); 
        setTimeout(function(){
        	$('.p4').fadeIn();
			$('.p4').removeClass("dn");
doPageview("/Form Page");
        },500); 
	}

	//第一步分输入验证
	function invide1() { 
		if($('.p1 .content .inpu3 .nb').val().length == 8 ){ 
			if($('.p1 .content .inpu3 .nb').val() == "30325734"){
				// alert("我是实时监听哦,yes");
				$('.p1 .content .inpu3 .nb').blur();
				$('.p1 .content .lock').attr("src","/r/cms/www/mobile/jmcd/img/lock2.png");
				TweenMax.from($('.p1 .content .lock') ,1,{alpha:0,scaleX:1.5,scaleY:1.5});
				$('.p1 .content .btn3').unbind("touchend",replay1);
				setTimeout(success1(),2000); 
			}else{
				$('.p1 .content .inpu3 .tip3').attr("src","/r/cms/www/mobile/jmcd/img/p3_tip_error.png");
				$('.p1 .content .error').removeClass("dn");
				$('.p1 .content .lock').addClass("dn");
                                setTimeout(function(){reseterror('3');$('.p1 .content .inpu3 .nb').val("3032");},1000);
				// alert("我是实时监听哦,no!"); 
			}
		}else { 
			if($('.p1 .content .inpu3 .nb').val().length <8){
				reseterror('3');
			}

			if($('.p1 .content .inpu3 .nb').val().length <4){
				$('.p1 .content .inpu3 .nb').val("3032");
			}                      

		}
	}

	//第二步分输入验证
	function invide2() { 
		if($('.p1 .content .inpu4 .nb').val().length == 9 ){ 
			if($('.p1 .content .inpu4 .nb').val() == "104043669"){
				// alert("我是实时监听哦,yes2");
				$('.p1 .content .inpu4 .nb').blur();
				$('.p1 .content .lock').attr("src","/r/cms/www/mobile/jmcd/img/unlock.png");
				TweenMax.from($('.p1 .content .lock') ,1,{alpha:0,scaleX:1.5,scaleY:1.5});
				$('.p1 .content .btn3').unbind("touchend",replay2);

				$('.p1 .content .inpu4').fadeOut();
				$('.p1 .content .btn3').addClass("dn"); 
				$('.p1 .content .inpu4').addClass("dn"); 
				// 第二轮解码完成、
				setTimeout(complete2(),2000); 
			}else{
				$('.p1 .content .inpu4 .tip3').attr("src","/r/cms/www/mobile/jmcd/img/p3_tip_error.png");
				$('.p1 .content .error').removeClass("dn");
				$('.p1 .content .lock').addClass("dn");
                                setTimeout(function(){reseterror('4');$('.p1 .content .inpu4 .nb').val("10404");},1000);
				// alert("我是实时监听哦,no2!");
			}
		}else { 
			if($('.p1 .content .inpu4 .nb').val().length <9){
				reseterror('4');
			} 
			if($('.p1 .content .inpu4 .nb').val().length <5){
				$('.p1 .content .inpu4 .nb').val("10404");
			}
		}
	}

	//第二阶段完成 地图坐标移动
	function complete2(){
                doPageview("/Win Tickets");
		console.log();
		$('.p1').fadeOut();
		$('.p2').fadeIn();
		$('.p2 .bg2').removeClass("dn"); 
		$('.p2 .lt').addClass("dn"); 

		TweenMax.to($('.p2 .bg'),3.5,{scaleX:4,scaleY:4,alpha:0.8});
		TweenMax.to($('.p2 .bg'),0.5,{alpha:0,delay:3.5});

		TweenMax.to($('.p2 .location'),1,{y:200,onComplete:function(){
			TweenMax.to($('.p2 .location'),1,{x:100,y:-50,onComplete:function(){
				TweenMax.to($('.p2 .location'),1,{x:0,y:100,onComplete:function(){  
                                        TweenMax.to($('.p2 .bg2'),1.5,{y:-400}); 
					setTimeout(function(){   
							$('.p2 .bg').fadeOut();

							setTimeout( function(){
								$('.p2 .map').fadeIn(); 
							},2000);
							setTimeout(function(){
								$('.p2 .copy').fadeIn();
								$('.p2 .copy').addClass("animated flash");

								//跳转到第三页
								setTimeout(complete3,3000);
							},4400); 
					},800);
					 
				}});
			}});
		}}); 
	}

	function complete3(){
		$('.p2').fadeOut();
		$('.p3').fadeIn();
		TweenMax.to($('.p3 .car'),1,{y:-500,onComplete:function(){
			TweenMax.to($('.p3 .car'),0.8,{alpha:0});
		}});

		setTimeout(function(){
			$('.p3 .car3').removeClass("dn");
			$('.p3 .car3').fadeOut(); 
			TweenMax.to($('.p3 .car3'),2,{y:300,x:150,onComplete:function(){
				TweenMax.to($('.p3 .car3'),0.8,{alpha:0});
			}});
		},800);
		
		setTimeout(function(){
			$('.p3 .car6').removeClass("dn");
			$('.p3 .car6').fadeOut(); 
			TweenMax.to($('.p3 .car6'),2,{y:-200,x:150,onComplete:function(){
				TweenMax.to($('.p3 .car6'),0.8,{alpha:0});
			}});
		},1200);

		setTimeout(function(){
			$('.p3 .car5').removeClass("dn");
			$('.p3 .car5').fadeOut(); 
			TweenMax.to($('.p3 .car5'),2,{y:-200,x:-200,onComplete:function(){
				TweenMax.to($('.p3 .car5'),0.8,{alpha:0});

				$('.p3 .copy').removeClass("dn");
				$('.p3 .copy').fadeIn();


			}});
		 },2200); 


		setTimeout(function(){
			$('.p3 .car2').removeClass("dn");
			$('.p3 .car2').fadeOut(); 
			TweenMax.to($('.p3 .car2'),2,{y:150,x:-200,onComplete:function(){
				TweenMax.to($('.p3 .car2'),0.8,{alpha:0});
			}});
		},3200);
		
		setTimeout(function(){
			$('.p3 .car4').removeClass("dn");
			$('.p3 .car4').fadeOut(); 
			TweenMax.to($('.p3 .car4'),2,{y:100,x:300,onComplete:function(){
				TweenMax.to($('.p3 .car4'),0.8,{alpha:0});
				 
				$('.p3 .get').removeClass("dn");
				$('.p3 .get').fadeIn();


			}});
		},3300);

		setTimeout(function(){
			$('.p3 .car7').removeClass("dn");
			$('.p3 .car7').fadeOut(); 
			TweenMax.to($('.p3 .car7'),2,{y:-440,x:-100,onComplete:function(){
				TweenMax.to($('.p3 .car7'),0.8,{alpha:0});
			}});
		 },3700);


	}

	// 第一阶段完成
	function success1(){
		//再看一次2
doPageview("/East Longitude");
		$('.p1 .content .btn3').bind("touchend",replay2);

		$('.p1 .content .suc1').fadeIn();

		// 文本框验证
		$('.p1 .content .inpu3 .nb').unbind('input propertychange', invide1);
		 
		$('.p1 .content .inpu3').fadeOut();
		$('.p1 .content .btn3').addClass("dn"); 

		$('.p1 .content .inpu3').addClass("dn"); 
		$('.p1 .content .inpu4 .nb').val("10404");
		$('.p1 .content .inpu4 .nb').attr('maxlength',"9");

		TweenMax.to($('.p1 .content .car'),4,{rotation:0});
		TweenMax.to($('.p1 .content .numbers2 img'),0,{alpha:0});
 
		setTimeout(function(){$('.p1 .content .suc1').fadeOut(); },3500);   

		setTimeout(function(){ 
			TweenMax.to($('.p1 .content .car'),4,{rotation:360,onComplete:function(){
				$('.p1 .content .inpu4').fadeIn();
				$('.p1 .content .inpu4').addClass("animated flash"); 
				setTimeout(function(){
					$('.p1 .content .inpu4 .nb').focus();
					$('.p1 .content .btn3').removeClass("dn");
					$('.p1 .content .inpu4').removeClass("dn");  
				},1500);
			}});
			$('.p1 .content .numbers2').removeClass("dn"); 
			$('.p1 .content .car').removeClass("dn"); 
			$('.p1 .content .tip3').fadeIn(); 

			TweenMax.staggerTo($('.numbers2 .n'),0.2,{alpha:1},0.5); 
			TweenMax.staggerTo($('.numbers2 .n'),0.2,{alpha:0,delay:0.8,onComplete:function(){
				setTimeout(function(){$('.p1 .content .car').addClass("dn");},2200);
			}},.5);
		},5200); 
	}

	function reseterror(i){ 
		$('.p1 .content .inpu'+ i + ' .tip3').attr("src","/r/cms/www/mobile/jmcd/img/p3_tip.png");
		$('.p1 .content .error').addClass("dn");
		$('.p1 .content .lock').removeClass("dn");
	}

	function show_page(e) {
doPageview("/Landing Page");
		addevent();  
	}

	function replay2()
	{
doTrack("/East Longitude/Click");
doPageview("/East Longitude");
                $('.p1 .content .inpu4 .nb').blur(); 
		reseterror('4');
		$('.p1 .content .btn3').addClass("dn");
		$('.p1 .content .btn3').fadeOut();
		TweenMax.to($('.p1 .content .car'),0,{alpha:1,rotation:0});
		$('.p1 .content .inpu4').fadeOut();
		$('.p1 .content .inpu4').removeClass("animated flash");
		setTimeout(success1(),2000); 
	}

	function replay1()
	{  
doTrack("/North Latitude/Click");
                $('.p1 .content .inpu3 .nb').blur();
		reseterror('3');
		$('.p1 .content .btn3').addClass("dn");
		TweenMax.to($('.p1 .content .car'),0,{alpha:1,rotation:0});
		$('.p1 .content .inpu3').fadeOut();
		$('.p1 .content .inpu3').removeClass("animated flash");
		gotoP2();
	}
	function gotoP2(){ 
doTrack("/Landing Page/Click");
doPageview("/North Latitude");
                $('.p1 .content .lock').removeClass("animated flash infinite"); 
		//再看一次1
		$('.p1 .content .btn3').bind("touchend",replay1);
		$('.p1').unbind("touchend",gotoP2); 
		 
		$('.p1 .content .ltround').removeClass("active");
		$('.p1 .content .copy').fadeOut();
		$('.p1 .content .arrow').fadeOut();
		 
		$('.p1 .content .inpu3 .nb').val("3032");
 
		TweenMax.to($('.p1 .content .numbers img'),0,{alpha:0});
                TweenMax.to($('.p1 .content .car'),4,{rotation:0});

		$('.p1 .content .copy3').fadeIn(); 
		setTimeout(function(){$('.p1 .content .copy3').fadeOut(); },2000); 

		setTimeout(function(){ 
			TweenMax.to($('.p1 .content .car'),4,{rotation:360,onComplete:function(){
				$('.p1 .content .inpu3').fadeIn();
				$('.p1 .content .inpu3').addClass("animated flash"); 
				setTimeout(function(){
					$('.p1 .content .inpu3 .nb').focus();
					$('.p1 .content .btn3').removeClass("dn");

				},1500);
			}});
			$('.p1 .content .numbers').removeClass("dn"); 
			$('.p1 .content .car').removeClass("dn"); 
			$('.p1 .content .tip3').fadeIn(); 

			TweenMax.staggerTo($('.numbers .n'),0.2,{alpha:1},0.5); 
			TweenMax.staggerTo($('.numbers .n'),0.2,{alpha:0,delay:1.5,onComplete:function(){
				setTimeout(function(){$('.p1 .content .car').addClass("dn");},2200);
			}},.5);
		},3600);

	}

	// 即可推荐
	function submitfn() { 
doTrack("/Form Page/Click");
        var pattern=/^(1(([3589][0-9])|(47)|[8][01236789]))\d{8}$/; 
        var patternname=/^[\u4e00-\u9fa5]+$/i; 

        // 推荐人一
        var c = $(".name1").val();
        var d = $(".tel1").val();
        var e = $("#buyingDealerProvince1").val();
        var f = $("#buyingDealerCity1").val();
        var g = $(".dealer").val();
        var h = $(".cartype1").val();
        var i = $(".maybebuytime1").val();

        //必填姓名、手机
        if(c =="")
        {
            alert('请填写您的姓名！'); 
            return false;
        }
        if(!patternname.test(c.trim()))
        {
            alert('您的姓名必须是汉字，谢谢！'); 
            return false;
        } 
        if(d =="")
        {
            alert('请填写手机号码！'); 
            return false;
        }
        if(h =="-1")
        {
            alert('请选择意向车型！'); 
            return false;
        }  

        if(i =="-1")
        {
            alert('请选择意向购买时间！'); 
            return false;
        }   
        if(e =="-1")
        {
            alert('请选择省份！'); 
            return false;
        }  

        if(f =="-1")
        {
            alert('请选择城市！'); 
            return false;
        } 

        if(g =="-1")
        {
            alert('请选择经销商！'); 
            return false;
        } 

        if(!pattern.test(d.trim()))
        {
            alert('您的手机号码格式有误，请检查！');
            $(".tel").focus();
            return false;
        } 
        doTrack("/Form Page/Click");
    var mydata = {
            'infoList[0].carOwnerName':c,
            'infoList[0].carOwnerMobilePhoneNo':d, 
            'infoList[0].intentionalCarModel':h,
            'infoList[0].intentionalBuyDateRange':i, 
            'infoList[0].carOwnerProvince':e, 
            'infoList[0].carOwnerCity':f, 
            'infoList[0].carOwnerDealer':g, 
            'infoList[0].carModel':'8.9成都车展', 
            'infoList[0].intentionalCarModel':h,
            'infoList[0].activityName':'8.9成都车展', 
            'infoList[0].channel':channel,
            'infoList[0].wechatId': openId
           };  
     
    $.ajax({  
        url: '/infocollection/potentialcustomer/add.jspx',
        data:mydata,
        type: 'post',
        cache: false,
        async: false,
        dataType: 'json',
        success: function(value) {
            block = false;
            if(value.status == '1'){ 
doPageview("/Submitted Successfully");
                $('.popup').removeClass("dn");
				$('.popup').fadeIn();
            } else{
                alert(value.message); 
            }
        },
        error :function() { 
            block = false;
            alert("提交失败请重试，谢谢！");
        }                    
    })  
        return true;
    }   
   
	function clspop() {
      
		$('.popup').fadeOut();
	}

	// 加载动画
	var loadingScale, t;
	$(".loadingpage").fadeIn();
	var imgLoad = function() {
		var loaded = 0;
		var me = this;
		var temp = '';
		if ($('body img').length === 0 || navigator.userAgent.indexOf('MSIE 9') != -1) {
			show_page();
		} else {
			$('body img').imagesLoaded().progress(function(instance, image) {
				loaded++;
				loadingScale = loaded / instance.images.length;

				console.log(parseInt(loadingScale * 100) + "======");
				//$('.percent').html(parseInt(loadingScale * 100) + '%');

				if (loaded == instance.images.length) { 
					
					setTimeout(function(){   
						$('.p1').removeClass("dn"); 
						$('.init').fadeOut();     
								setTimeout(function(){
									$('.p1 .content .ltround').addClass("active");
									$('.p1 .content .arrow').addClass("active");
                                                                        $('.p1 .content .lock').addClass("animated flash infinite");
									$('.logo').removeClass("dn");
									$('.init').remove();
								},1000)
								
								show_page(me);  
					},3000);  
				}
			});
		}
	}

	$('.petalpointer').addClass("dn");
	imgLoad();

})