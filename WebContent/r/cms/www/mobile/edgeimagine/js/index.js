var urlHost = "/"; 
var openid = urlRequest("openId"); 
console.log("wechatId: " + openid);

(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-69201345-15', 'auto');
  ga('send', 'pageview');


ga('set','campaignName','edgeimagine');
ga('set','campaignSource',openid);
ga('set','campaignMedium','weixin');

                            function doTrack(name) {
			    console.log('track', name)
			    name = name.toUpperCase();
			    ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "edgeimagine", // Required.
			        'eventAction': 'click', // Required.
			        'eventLabel': "BUT_" + name,
			        'eventValue': 1
			    });
			};
			function doPageview(name) {
			    console.log('doPageview', name);
			    ga('send', 'pageview', {
			        'page': name,
			        'title': "edgeimagine",
			    });
			};



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

var openId = urlRequest("openId");
var chann = urlRequest("channel");
window.channel = chann.split("#")[0];
var state =  urlRequest("state");
window.state = state.split("#")[0];
$(".swipe-handler .btnimg").hide();

$(function() {
	 
	function initwh() {
		var iw = $(window).width();
		var ih = $(window).height();
		$(".swiper-container").css("width", iw);
		$(".swiper-container").css("height", ih);
	}
	var sure = true;

	var loadingScale, t;
	var imgLoad = function() {
		var loaded = 0;
		var me = this;
		var temp = '';
		if ($('body img').length === 0 || navigator.userAgent.indexOf('MSIE 9') != -1) {
			show_page();
		} else {
			$('body img').imagesLoaded().progress(function(instance, image) {
				loaded++;
				//if(loadingScale < (loaded / instance.images.length)){
				loadingScale = loaded / instance.images.length;
				t = parseInt(loadingScale * 10);
				console.log('LOADING' + parseInt(loadingScale * 100) + '%'); 

				$('.rgroud').html(temp);
				if (loaded == instance.images.length) {
					show_page(me);
				}
				//}
			});
		}
		// work script
		function show_page(me) {
			setTimeout(function() {
                                
				$('.initpage').show().addClass("animated fadeIn"); 
				$('.swipe-handler').show();
				landing();
                                if($.cookie('state') == "bk"){
                                    mySwiper.slideTo(5, 0, false); 
                                    $(".swipe-handler .btnimg").hide();
                                    closefn();
                                    $.removeCookie('state'); 
                                }else{
                                     setTimeout(function(){$(".swipe-handler .btnimg").show();  },1000) 
                                }

			}, 1000);
		}
	}
	var mySwiper;
	var landing = function() {
		mySwiper = new Swiper('.swiper-container', {
			direction: 'vertical',  
			autoplayStopOnLast:true,
			touchRatio: 0.1,
			speed: 500,
			touchRatio: 1,
			mousewheelControl: false, 
			resistanceRatio: 0.3,
			onInit: function(swiper) {
				currPage = 0; 
                                    doPageview("/Edge Lego/Landing Page"); 
			},
			onSlideChangeStart: function(swiper) {    
				currPage = mySwiper.activeIndex;  
				if (currPage == 1) {
                                    doPageview("/Edge Lego/Ease Family");
				} else if (currPage == 2) {
                                    doPageview("/Edge Lego/Ease People");  
				} else if (currPage == 3) {  
                                    doPageview("/Edge Lego/Ease Travel");
				} else if (currPage == 4) {
                                    doPageview("/Edge Lego/Ease Business");
                                      mySwiper.unlockSwipeToNext(); 
                                      $(".swipe-handler .btnimg").show(); 
				} else if (currPage == 5) {
				      //closefn();
                                      //doPageview("/Edge Lego/Challenge");
                                      //mySwiper.lockSwipeToNext();
                                      $(".swipe-handler .btnimg").hide(); 
                                      $(".page6 .info").show();

				} else if (currPage == 6) {  
                                    doPageview("/Edge Lego/Test Drive");
				} else if (currPage == 7) { 
 
				} 
				console.log('onSlideChangeStart = ' + currPage  );
			} 
		}) 
	}


	initwh();

	//加载资源
	imgLoad();

	initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", "");
	initSelectData("buyingDealerProvince2","","buyingDealerCity2","", "carBuyingDealer2", "");

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
				$("#"+selectId).append('<option value="" data="" selected>请选择省份</option>');
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
	          alert("网络或数据异常，操作失败！经销商");
	       }
		});
	}

 

	//暂停使用滑动
	function closefn() {   
		mySwiper.lockSwipes();
		console.log('-----------暂停使用滑动------------ ='   );  
	}

	//开始使用滑动
	function openfn() {  
		console.log('-----------开始使用滑动------------'  );   
		mySwiper.unlockSwipes(); 
	}


	/**
	 **提交信息
	 **/

	function sendInfo(name, mobile,prov,city,cstyle,dealer) {
		$.ajax({
			url: 'add.jspx',
			data:{ 
				'infoList[0].carOwnerName':name,  
				'infoList[0].carOwnerMobilePhoneNo':mobile, 
				'infoList[0].customerName':name,
				'infoList[0].customerMobilePhoneNo':mobile,  
				'infoList[0].customerProvince':prov,
				'infoList[0].customerCity':city,
				'infoList[0].intentionalDealer':dealer, 
                                'infoList[0].carModel': cstyle,
                                'infoList[0].wechatId': openId,
				'infoList[0].activityName':'ruijie-legao',
				'infoList[0].channel':channel

			},
			type: 'post',
			cache: false,
			async: false,
			dataType: 'json',
			success: function(value) {
				block = false;
				if (value.status == 1) {
					$(".success").removeClass("dn");
                                        doPageview("/Edge Lego/Successful Submission");
				} else {
					alert(value.message);
					//alert("请勿重复提交");
				}


			},
			error: function() {
				alert('网络繁忙,请稍后重试');
				block = false;
			}
		})
	} 

	// 活动详情
	$(".page5 .rule").on('touchstart', function() { 
                doPageview("/Edge Lego/Rules");

		$('.popup').show().addClass("animated fadeIn");
	})

	$('.swipe-handler .btnruler').on('touchstart', function() { 
                doPageview("/Edge Lego/Rules");
		$('.popup').show().addClass("animated fadeIn");
	})


	$('.popup .cls').on('touchstart', function() {
		$('.popup').hide().removeClass("fadeIn");
	}) 

	$(".page5 .sj").on('touchstart', function() {  
                 ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "edgeimagine", // Required.
			        'eventAction': 'click', // Required.
			        'eventLabel': "/Edge Lego Event/Challenge/Go Test Drive",
			        'eventValue': 1
			    });
		openfn();
		mySwiper.slideNext();
		closefn();
                setTimeout(function(){$(".page6 .info").show();},500);
                

	});


	$('.success .cls').on('touchstart', function() {
		$('.success').hide().addClass("dn");
                //self.location = 'index.jspx';
	}) 
	// 我要挑战
	$(".page5 .tz").on('touchstart', function() { 
                 ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "edgeimagine", // Required.
			        'eventAction': 'click', // Required.
			        'eventLabel': "/Edge Lego Event/Challenge/Go Challenge",
			        'eventValue': 1
			    });
                $.cookie('state', 'bk'); 
		self.location = 'http://club.autohome.com.cn/bbs/forum-c-3615-1.html';


	});

	/**
	 ** submit info
	 **/ 
	$(".page6 .tj").on('touchstart', function() { 
                 ga('send', {
			       'hitType': 'event', // Required.
			        'eventCategory': "edgeimagine", // Required.
			        'eventAction': 'click', // Required.
			        'eventLabel': "/Edge Lego Event/Test Drive/Submission",
			        'eventValue': 1
			    }); 
		if (sure) {
			var name = $('.name').val();
			var mobile = $('.tel').val();
			var prov = $(".province").find("option:selected").text();
			var city = $(".city").find("option:selected").text();
			var dealer = $(".dealer").find("option:selected").text();
                        var cstyle = $(".cstyle").find("option:selected").text();
 
			if (name == '') {
				alert('请输入您的姓名！');
				$(".name").focus();
				return false;
			}
			if (mobile == '' || !(/^1[34578]\d{9}/.test(mobile))) {
				alert('请输入正确的手机号！')
				$(".tel").focus();
				return false;
			} 

			if (city === "请选择" ||prov === "请选择" ) {
			        alert("请选择省市");
				return false;
			}
			if (cstyle === "请选择车型") {
				alert("请选择车型");
				return false;
			}
 
			sure = false;
			sendInfo(name, mobile,prov,city,cstyle,dealer);

		} else {
			alert("您已提交过信息，感谢您的参与!");
		}
	})  
})