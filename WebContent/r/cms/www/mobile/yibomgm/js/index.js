var openid = urlRequest("openId"); 
console.log("wechatId: " + openid);

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-69201345-11', 'auto');
  ga('send', 'pageview');

ga('set','campaignName','yibomgm2');
ga('set','campaignSource',openid);
ga('set','campaignMedium','weixin');

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


function doTrack(name) {
			    console.log('track', name)
			    name = name.toUpperCase();
			    ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "yibomgm2", // Required.
			        'eventAction': 'click', // Required.
			        'eventLabel': "BUT_" + name,
			        'eventValue': 1
			    });
			};
			function doPageview(name) {
			    console.log('doPageview', name);
			    ga('send', 'pageview', {
			        'page': name,
			        'title': "yibomgm2",
			    });
			};


var myScroll, pushpetal = false;
var openId;
var speed;
var canvasOne = document.getElementById('frameAnimate');
var frame1 = new frameAnimate(canvasOne, {
	width: 403,
	height: 570,
	fps: 8,
	frame: 6,
	loop: true
}, function() {
	console.log("==stop==");
});
frame1.play();
$(function() {
	var sh, step = 0,
		agree = true,
		$audio, $patalaudio;
	var $startc1 = $('.init .c1');
	var tween, tween1, tween2, cartime = 2.6,
		channel;
	channel = urlRequest("channel");
	window.channel = channel.split("#")[0];
	openId = urlRequest("openId");
	var el = document.querySelector(".start .visualbtn");

	var isIos;
	var isAndroid;

	function isIosOrAndroid() {
		var browser = {
			versions: function() {
				var u = navigator.userAgent,
					app = navigator.appVersion;
				return {
					trident: u.indexOf('Trident') > -1,
					presto: u.indexOf('Presto') > -1,
					webKit: u.indexOf('AppleWebKit') > -1,
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,
					mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/),
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
					iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1,
					iPad: u.indexOf('iPad') > -1,
					webApp: u.indexOf('Safari') == -1
				};
			}()
		}
		isIos = browser.versions.ios;
		isAndroid = browser.versions.android;
	}

	isIosOrAndroid();

	//判断屏幕高度
	var sHeight = document.body.clientHeight;

        //防止页面滚动
	$(document).on('touchmove.prevent', function(e) {      e.preventDefault();	}); 
        doPageview('/EcoSport MGM S2/Landing Page');

	function urlRequest(paras) //获取url参数方法
	{
		var url = location.href;
		var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
		var paraObj = {}
		for (i = 0; j = paraString[i]; i++) {
			paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
		}
		var returnValue = paraObj[paras.toLowerCase()];
		if (typeof(returnValue) == "undefined") {
			return "";
		} else {
			return returnValue;
		}
	}

	var urlHost = "/";
	initSelectData("buyingDealerProvince1","","buyingDealerCity1","", "", ""); 
	initSelectData("buyingDealerProvince2","","buyingDealerCity2","", "", "");
	initSelectData("buyingDealerProvince3","","buyingDealerCity3","", "", "");

	function initSelectData(provinceSelectId, defaultProvince, citySelectId, defaultCity, dealerSelectId, defaultDealer) {
		loadProvince(provinceSelectId, defaultProvince, citySelectId, defaultCity, dealerSelectId, defaultDealer);
		$("#" + provinceSelectId).change(function() {
			$("#" + citySelectId).html("");
			$("#" + dealerSelectId).html("");
			var provinceId = $(this).find("option:selected").attr("data");
			loadCity(citySelectId, '', provinceId, '', '');
		});
		$("#" + citySelectId).change(function() {
			$("#" + dealerSelectId).html("");
			var cityId = $(this).find("option:selected").attr("data");
			//loadDealer(dealerSelectId, '', cityId);
		});
	}

	/**
	 * 加载省
	 */
	function loadProvince(selectId, defaultValue, citySelectId, defaultCity, dealerSelectId, defaultDealer) {
		var submitData = {};
		$.ajax({
			url: urlHost + 'dealer/findAllProvince.jspx',
			type: 'post',
			data: submitData,
			dataType: 'json',
			success: function(result) {
				$("#" + selectId).empty();
				$("#" + selectId).append('<option value="-1" data="-1" selected>请选择</option>');
				for (var i = 0; i < result.length; i++) {
					var t = result[i];
					if (defaultValue == t.name) {
						$("#" + selectId).append('<option selected="selected" value="' + t.name + '" data="' + t.id + '" >' + t.name + '</option>');
						loadCity(citySelectId, defaultCity, t.id, dealerSelectId, defaultDealer);
					} else {
						$("#" + selectId).append('<option value="' + t.name + '" data="' + t.id + '" >' + t.name + '</option>');
					}
				}
			},
			error: function() {
				alert("网络或数据异常，操作失败！");
			}
		});
	}
	/**
	 * 加载城市
	 */
	function loadCity(selectId, defaultValue, provinceId, dealerSelectId, defaultDealer) {
		if (provinceId == "") {
			return;
		}
		var submitData = {
			"provinceId": provinceId
		};
		$.ajax({
			url: urlHost + 'dealer/findAllCityByProvinceId.jspx',
			type: 'post',
			data: submitData,
			dataType: 'json',
			success: function(result) {
				$("#" + selectId).empty();
				$("#" + selectId).append('<option value="-1" data="-1" selected>请选择</option>');
				for (var i = 0; i < result.length; i++) {
					var t = result[i];
					if (defaultValue == t.name) {
						$("#" + selectId).append('<option selected="selected" value="' + t.name + '" data="' + t.id + '" >' + t.name + '</option>');
						loadDealer(dealerSelectId, defaultDealer, t.id);
					} else {
						$("#" + selectId).append('<option value="' + t.name + '" data="' + t.id + '" >' + t.name + '</option>');
					}
				}
				if (provinceId == "16") {
					$("#" + selectId).append("<option value='莱州市' data='158' >莱州市</option>");
				}
			},
			error: function() {
				alert("网络或数据异常，操作失败！");
			}
		});
	}

	// 开启游戏
	function addevent() {
		//添加音乐
		$audio = $('#chatAudio')[0];
		$patalaudio = $('#patalAudio')[0];

		$("#play").on("touchstart", function() {
			if ($audio.paused) {
				$("#play").removeClass('paused');
				$audio.play();
			} else {
				$audio.pause();
				$("#play").addClass('paused');
			}
		});
		$audio.play();
		frame1.stop();

		$('.init .btn').bind("click", function(){
                            ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "EcoSport MGM S2 Event", // Required.
			        'eventAction': 'Click', // Required.
			        'eventLabel': "/EcoSport MGM S2/Adventure Page/Let’s Go",
			        'eventValue': 1
			    });
                startbtn();});

		// 游戏结束
		$('.end .btngroup .invite').bind("touchstart", invite);
		$('.end .btngroup .replay').bind("touchstart", function(){
                            ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "EcoSport MGM S2 Event", // Required.
			        'eventAction': 'Click', // Required.
			        'eventLabel': "/EcoSport MGM S2/Game Page/Repeat",
			        'eventValue': 1
			    });
                startbtn();});

		//关闭窗口
		$('.success .notice').bind("touchstart", clssuccess);

		//关闭规则
		$('.rule .notice').bind("touchstart", clsrule);
		$('.submit .rulebtn').bind("touchstart", openrule);

		// 邀请更多好友
		$('.submit .addgroup .add1 .addbtn').bind("touchstart", invite1);
		$('.submit .addgroup .add2 .addbtn').bind("touchstart", invite2);

		// 同意条款
		$('.submit .agree').bind("touchstart", agreefn);

		//即可推荐
		$('.submit .submitbtn').bind("touchstart", submitfn);

		$("#wrapper").on("touchstart", function() {
			$(document).off('touchmove.prevent');
		});
		$("#wrapper").on("touchend", function() {
			$(document).on('touchmove.prevent', function(e) {
				e.preventDefault();
			});
		});
                
	}

	function show_page(e) {
                 
		addevent();
		sHeight = window.screen.height;
		if (isAndroid) {
			//$('#bottom').css("top",sHeight - 24);
                        //alert('isAndroid'+sHeight ); 
                           
		}
		if (sHeight < 1008) { 
			$('.init .t2').css("width","300px");
                        $('.init .t2').css("top","680px");
			$('.init .t2').css("margin-left","-150px");
                        $('.init .btn').css("top" + "700px"); 
		}

		// 初始化开始页面
		$('.init').removeClass("dn");
		$('.init').addClass("animated fadeIn");

		$('.init .t1').addClass("animated fadeInDown");
		$('.init .t1').removeClass("dn");

		setTimeout(function() {
			tween = TweenMax.from($startc1, 0.5, {
				y: "-=300",
				scale: 0,
				ease: Circ
			});

			// $('.init .c1').addClass("active"); 
			$('.init .c1').removeClass("dn");
		}, 1000);

		setTimeout(function() {
			tween1 = TweenMax.from($('.init .c2'), 0.3, {
				y: "-=10",
				scale: 0,
				ease: Circ
			});
			$('.init .c2').removeClass("dn");
		}, 1300);

		setTimeout(function() {
			$('.init .t2').addClass("animated fadeInUp");
			$('.init .t2').removeClass("dn");
			$('.init .btn').addClass("animated fadeInUp");
			$('.init .btn').removeClass("dn");
		}, 1800);

		setTimeout(function() {
			$('.init .btn').removeClass("fadeInUp");
			$('.init .btn').addClass("animated flash infinite");

		}, 2800);
	}

	// 即可推荐
	function submitfn() {
                   ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "EcoSport MGM S2 Event", // Required.
			        'eventAction': 'Click', // Required.
			        'eventLabel': "/EcoSport MGM S2/Invite Page/Submit",
			        'eventValue': 1
			    });
                var chann = urlRequest("channel");
	        window.channel = chann.split("#")[0];
                //++ 渠道68保存失败，暂时修改为15 2016/10/21 by Yunxi
                //window.channel == "68" ? window.channel="15":true;
                //alert("window.channel = " + window.channel);
                var submitData={"campaignCode": "000002",
						"channelCode":window.channel };
		
		var dataListAll =[];
                var object ={};

		var pattern = /^(1(([3589][0-9])|(47)|[8][01236789]))\d{8}$/;
		var a = $(".name").val();
		var b = $(".tel").val();

		// 推荐人一
		var c = $(".name1").val();
		var d = $(".tel1").val();
		var e = $("#buyingDealerProvince1").val();
		var f = $("#buyingDealerCity1").val();
		var g = $(".cartype1").val();
		var gg = $(".maybebuytime1").val();

		// 推荐人二
		var h = $(".name2").val();
		var i = $(".tel2").val();
		var j = $("#buyingDealerProvince2").val();
		var k = $("#buyingDealerCity2").val();
		var l = $(".cartype2").val();
		var m = $(".maybebuytime2").val();

		// 推荐人三
		var n = $(".name3").val();
		var o = $(".tel3").val();
		var p = $("#buyingDealerProvince3").val();
		var q = $("#buyingDealerCity3").val();
		var r = $(".cartype3").val();
		var s = $(".maybebuytime3").val();

		//必填姓名、手机
		if (a == "") {
			alert('请填写您的姓名！');
			$("#form-name").focus();
			return false;
		}

		if (b == "") {
			alert('请填写手机号码，谢谢！');
			$(".tel").focus();
			return false;
		}
		if (!pattern.test(b.trim())) {
			alert('您的手机号码格式有误，请检查！');
			$(".tel").focus();
			return false;
		}
		//推荐人一
		if (c == '') {
			$('.name1').focus();
			alert('推荐人一：请填写姓名，谢谢!');
			return;
		}
		if (d == "") {
			$('.tel1').focus();
			alert('推荐人一：请填写手机号码，谢谢!');
			return;
		}
		if (d != "") {
			if (!pattern.test(d.trim())) {
				alert('推荐人一：手机号码格式有误，请检查！');
				$(".tel1").focus();
				return false;
			}

			if (e == '-1') {
				alert('推荐人一：请选择省份，谢谢!');
				return;
			}
			if (f == '-1') {
				alert('推荐人一：请选择城市，谢谢!');
				return;
			}
			if (g == '-1') {
				alert('推荐人一：请选择购买车型，谢谢!');
				return;
			}
			if (gg == '-1') {
				alert('推荐人一：请选择购买时间，谢谢!');
				return;
			}
                        object["buyerName"]=a;
			object["buyerMobilePhoneNo"]=b; 
			object["potentialBuyerName"]=c;
			object["potentialBuyerMobilePhoneNo"]=d;
			object["potentialBuyerProvince"]=e;
			object["potentialBuyerCity"]=f; 
                        object["potentialBuyProductModel"]=g;
                        object["potentialBuyTime"]=gg;
                        object["buyerGender"]="UNKNOWN";
                        object["potentialBuyerGender"]="UNKNOWN";  
			object["wechatId"]=openid;  
                        dataListAll.push(object); 
		}

		// 推荐人二
		if (i != "") {
			if (!pattern.test(i.trim())) {
				alert('推荐人二：手机号码格式有误，请检查！');
				$(".tel2").focus();
				return false;
			}
			if (h == '') {
				alert('推荐人二：请填写姓名，谢谢!');
				return;
			}
			if (j == '-1') {
				alert('推荐人二：请选择省份，谢谢!');
				return;
			}
			if (k == '-1') {
				alert('推荐人二：请选择城市，谢谢!');
				return;
			}
			if (l == '-1') {
				alert('推荐人二：请选择购买车型，谢谢!');
				return;
			}
			if (m == '-1') {
				alert('推荐人二：请选择购买时间，谢谢!');
				return;
			}
                       object = {};
                       object["buyerName"]=a;
			object["buyerMobilePhoneNo"]=b; 
			object["potentialBuyerName"]=h;
			object["potentialBuyerMobilePhoneNo"]=i;
			object["potentialBuyerProvince"]=j;
			object["potentialBuyerCity"]=k; 
                        object["potentialBuyProductModel"]=l;
                        object["potentialBuyTime"]=m;
                        object["buyerGender"]="UNKNOWN";
                        object["potentialBuyerGender"]="UNKNOWN";  
			object["wechatId"]=openid;  
                        dataListAll.push(object); 
		}

		// 推荐人三
		if (o != "") {
			if (!pattern.test(o.trim())) {
				alert('推荐人三：手机号码格式有误，请检查！');
				$(".tel3").focus();
				return false;
			}
			if (n == '') {
				alert('推荐人三：请填写姓名，谢谢!');
				return;
			}
			if (p == '-1') {
				alert('推荐人三：请选择省份，谢谢!');
				return;
			}
			if (q == '-1') {
				alert('推荐人三：请选择城市，谢谢!');
				return;
			}
			if (r == '-1') {
				alert('推荐人三：请选择购买车型，谢谢!');
				return;
			}
			if (s == '-1') {
				alert('推荐人三：请选择购买时间，谢谢!');
				return;
			}
                       object = {};
                       object["buyerName"]=a;
			object["buyerMobilePhoneNo"]=b; 
			object["potentialBuyerName"]=n;
			object["potentialBuyerMobilePhoneNo"]=o;
			object["potentialBuyerProvince"]=p;
			object["potentialBuyerCity"]=q; 
                        object["potentialBuyProductModel"]=r;
                        object["potentialBuyTime"]=s;
                        object["buyerGender"]="UNKNOWN";
                        object["potentialBuyerGender"]="UNKNOWN";  
			object["wechatId"]=openid;  
                        dataListAll.push(object); 
		}
                submitData["dataList"] = dataListAll; 

                ga('set','campaignContent',a+"-"+b); 
                saveinfor(submitData); 
	} 

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
                        if(result.status == "11")
                        { $('.success').removeClass("dn");
		         $('.success').fadeIn(); }
                         else{
			   alert(result.message);
                         };
		},error:function(XMLHttpRequest, textStatus, errorThrown){
	 	       alert(textStatus);
     }
	}); 
}

	//邀请更多好友1
	function invite1() {
		setTimeout(function() {
			$('.submit .downup').removeClass("dn");
			$('.submit .downup').addClass("animated flash");
		}, 500);

		$('.submit .addgroup .add1 .addbtn').fadeOut();
		$('.submit .addgroup .add2').removeClass("dn");
		setTimeout(function() {
			$('.submit .downup').addClass("dn");
			$('.submit .downup').removeClass("animated flash")
		}, 1000);
	}

	//邀请更多好友2
	function invite2() {

		setTimeout(function() {
			$('.submit .downup').removeClass("dn");
			$('.submit .downup').addClass("animated flash");
		}, 500);

		$('.submit .addgroup .add2 .addbtn').fadeOut();
		$('.submit .addgroup .add3').removeClass("dn");
		setTimeout(function() {
			$('.submit .downup').addClass("dn");
			$('.submit .downup').removeClass("animated flash")
		}, 1000);
	}

	// agreefn
	function agreefn() {
		if (agree == true) {
			$('.submit .agree').attr("src", "/r/cms/www/mobile/yibomgm/img/p4_agree1.jpg");
			agree = false;
		} else {
			$('.submit .agree').attr("src", "/r/cms/www/mobile/yibomgm/img/p4_agree2.jpg");
			agree = true;
		}
	}

	// 关闭成功窗口
	function clssuccess() {
		$('.success').addClass("dn");
		$('.success').fadeOut();
	}

	// 关闭规则窗口
	function clsrule() {
		$('.rule').addClass("dn");
		$('.rule').fadeOut();
	}

	// 关闭规则窗口
	function openrule() {
		$('.rule').removeClass("dn");
		$('.rule').fadeIn();
	}
	//邀请好友填写表单
	function invite() { 
                ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "EcoSport MGM S2 Event", // Required.
			        'eventAction': 'Click', // Required.
			        'eventLabel': "/EcoSport MGM S2/Game Page/Share",
			        'eventValue': 1
			    });
                doPageview('/EcoSport MGM S2/Invite Page');

		$audio.pause();
		$("#play").addClass("dn");

		console.log("邀请好友填写表单");
		$('.rule').removeClass("dn");
		$('.rule').fadeIn();

		$('.end').addClass("dn");
		$('.submit').removeClass("dn");

                $('.end').remove();
                $('.start').remove();
	}

	// 跳转到游戏结束
	function gotogameend() { 
		$('.start .ss').removeClass('active');
		$('.start .ss2').removeClass('active');
		$('.start .lights img').removeClass('active');
		$('.start .car').removeClass('active'); 
		$('.start .road .r2').removeClass('active');
		$('.start .road .r3').removeClass('active');
		$('.start .score1').addClass('dn'); 
		$('.start .planets .p1').removeClass('active');
		$('.start .planets .p2').removeClass('active');
                $('.start .lights .l10').removeClass('active');
                $('.start .lights .l10').addClass('dn');

		$patalaudio.pause();  
		$('.end').removeClass("dn");

		frame1.pause();
		
	}

	function touch(event) {
		var event = event || window.event;
		var oInp = document.getElementById("inp");
		switch (event.type) {
			case "touchstart":
				startY = event.touches[0].clientY;
				stime = Date.parse(new Date());
				
				break;
			case "touchend":
                                if(moveY < startY){unloadtouch();$('.start .score1').removeClass('dn');}
				break;
			case "touchmove":
				event.preventDefault();
				moveY = event.touches[0].clientY;
				if (moveY > startY) {
					//alert('下划+moveY'+(moveY-startY));
				} else {
					distance = startY - moveY;
					//console.log('上划：'+distance); 
					sHeight = window.screen.height;
					speed = distance / sHeight;
					//alert('上划+moveY'+(startY-moveY));
				}
				break;
		}
	}

	// 开始滑动页面
	function unloadtouch() {
		document.removeEventListener('touchstart', touch, false);
		document.removeEventListener('touchmove', touch, false);
		document.removeEventListener('touchend', touch, false);
		$patalaudio.pause();
		$('.start .title').addClass('dn');
		$('.start .tip').addClass('dn');
		$('.start .score1').removeClass('dn');
		var total = speed*3800;
		var steps = total/40;
		var i = 0.0 ;

		var int= setInterval(changecourse,steps*2);

		function changecourse(){
			console.log('step:'+step+' i:'+i+ ' total:' + total +' current:' + parseInt(i/total) + "M");
			$('.start .score1').html(parseInt(total*i)+ "M");
			$('.end .btngroup .score .score2').html(parseInt(total*i)+ "M");
			i+=0.0125;
		}

		// step1
		frame1.play();
		$patalaudio.play();
         
                $('.start .planets .p1').addClass('active');
		$('.start .planets .p2').addClass('active');
		$('.start .ss').addClass('active');
		$('.start .ss2').addClass('active');
		$('.start .lights .l1').addClass('active');
		$('.start .lights .l3').addClass('active');
		$('.start .lights .l5').addClass('active');
		$('.start .lights .l10').addClass('dn');

		$('.start .car').addClass('active'); 
		$('.start .road .r2').addClass('active');
		$('.start .road .r3').addClass('active'); 
		
		console.log("step1");

		//step2 
		setTimeout(function() { 
			$('.start .stars img').addClass('active');
			$('.start .lights .l2').addClass('active');
			$('.start .lights .l4').addClass('active');
			$('.start .lights .l6').addClass('active');
			$('.start .lights img').addClass('active'); 
                        $('.start .lights .l10').addClass('active');
                        $('.start .lights .l10').removeClass('dn');
			console.log("step2");
		}, total/2);

		// step3
		setTimeout(function() {
			$('.start .planets').removeClass("dn");
			
			$('.start .lights .l7').addClass('active');
			$('.start .lights .l8').addClass('active');
			$('.start .lights .l9').addClass('active');
			$('.start .lights .l10').addClass('active');
			$('.start .lights .l11').addClass('active'); 
			console.log("step3");
		}, total);

		setTimeout(function(){
			gotogameend();
			clearInterval(int);  
		},total*1.5);  
	}

	function loadtouch() {
		var startY = ''; // 触摸开始时的纵坐标
		var moveY = ''; // 触摸移动中的纵坐标
		var distance = 0;
		var stime = 0;
		// var endY = '';     // 触摸结束的纵坐标

		document.addEventListener('touchstart', touch, false);
		document.addEventListener('touchmove', touch, false);
		document.addEventListener('touchend', touch, false);

	}

	//跳转到第二页
	function startbtn() { 
                
                doPageview('/EcoSport MGM S2/Game Page');
		step = 0, cartime = 2.6;
		$('.init').addClass("fadeOut");
		$('.end').addClass("dn");

		$('.start').removeClass("dn");
		$('.start').addClass("animated fadeIn"); 

		$('.start .score1').addClass('dn'); 
		$('.start .score1').html("0M");
		$('.start .title').removeClass('dn');
		$('.start .tip').removeClass('dn');   
		$('.start .planets .p2').removeClass('active');

		$('.start .title').addClass("animated fadeIn");
		$('.start .title').removeClass("dn");

		setTimeout(function(){
			loadtouch(); 
		},500); 
	}

	function submit() {
		//提交资料 
		$.ajax({
			url: 'toolkit/survey/answer/sheet/add.htm',
			data: "",
			type: 'post',
			cache: false,
			async: false,
			dataType: 'json',
			success: function(value) {
				block = false;
				if (value.status == '1') {

					var scores = value.totalScore;
					$.each(resultList, function(i, val) {
						if (resultList[i].score == scores) {
							$(".presult .result .title").val(resultList[i].title);
							$(".presult .result .content").val(resultList[i].content);
						}
					})

					alert('提交成功' + "totalScore = " + totalScore);
					console.log("提交成功:totalScore = " + totalScore);
				} else {
					alert(value.message)
				}
			},
			error: function(e) {
				console.log(e);
				alert('system error');
				block = false;
			}
		})
	}

	//弹出活动规则
	function popfn() {
		$('.poprule').fadeIn();
	}

	function clspop() {
		$('.poprule').fadeOut();
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
				$('.percent').html(parseInt(loadingScale * 100) + '%');

				if (loaded == instance.images.length) {

					$('.percent').html('完成');
					$('.loading').addClass("animated fadeOut ");
					$('.loading.page').remove();
					show_page(me);
                                        doPageview('/EcoSport MGM S2/Adventure Page');
				}
			});
		}
	}
	imgLoad();

})