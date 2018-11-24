$(function(){
	var myscore = urlRequest("score");
	//alert(myscore);

	if (myscore == 0 || myscore == 4) {   //resault 3
		loadimages(['/r/cms/www/mobile/yiboBoss/images/homeBg.jpg','/r/cms/www/mobile/yiboBoss/images/home_txt.png','/r/cms/www/mobile/yiboBoss/images/resault3_men.png','/r/cms/www/mobile/yiboBoss/images/resault3_kou.png','/r/cms/www/mobile/yiboBoss/images/resault3.png','/r/cms/www/mobile/yiboBoss/images/resault3_Tip.png','/r/cms/www/mobile/yiboBoss/images/car3.png']);
	window.share.imgUrl = 'http://yiche.changanfordwechat.com/r/cms/www/mobile/yiboBoss/images/shareimg.jpg';
            window.share.link = 'http://www.changanfordclub.com/infocollection/potentialcustomer/yiboBoss/index.jspx?channel=1'
            window.share.title= '小搏支招，TA靠【加满勇气，敢于突破】征服老板，完美！';
            window.share.desc = '想知道你搞定老板的独门秘籍？戳这里，戳这里，戳这里！';
            shareConfig();
	} else if (myscore == 1 || myscore == 5) { //resault 4
				loadimages(['/r/cms/www/mobile/yiboBoss/images/homeBg.jpg','/r/cms/www/mobile/yiboBoss/images/home_txt.png','/r/cms/www/mobile/yiboBoss/images/resault4_girl.png','/r/cms/www/mobile/yiboBoss/images/resault4.png','/r/cms/www/mobile/yiboBoss/images/resault4_tip.png','/r/cms/www/mobile/yiboBoss/images/resault4_txt.png','/r/cms/www/mobile/yiboBoss/images/car3.png']);
			window.share.imgUrl = 'http://yiche.changanfordwechat.com/r/cms/www/mobile/yiboBoss/images/shareimg.jpg';
            window.share.link = 'http://www.changanfordclub.com/infocollection/potentialcustomer/yiboBoss/index.jspx?channel=1'
            window.share.title= '小搏支招，TA靠【学会独立，努力进取】征服老板，完美！';
            window.share.desc = '想知道你搞定老板的独门秘籍？戳这里，戳这里，戳这里！';
            shareConfig();
	} else if (myscore == 2 || myscore == 6) { //resault 2
			loadimages(['/r/cms/www/mobile/yiboBoss/images/homeBg.jpg','/r/cms/www/mobile/yiboBoss/images/home_txt.png','/r/cms/www/mobile/yiboBoss/images/resault2.png','/r/cms/www/mobile/yiboBoss/images/resault2_man1.png','/r/cms/www/mobile/yiboBoss/images/resault2_man2.png','/r/cms/www/mobile/yiboBoss/images/resault2_txt.png','/r/cms/www/mobile/yiboBoss/images/resault2_tip.png','/r/cms/www/mobile/yiboBoss/images/resault2_pia.png','/r/cms/www/mobile/yiboBoss/images/car4.png','/r/cms/www/mobile/yiboBoss/images/car4_shadow.png']);
			window.share.imgUrl = 'http://yiche.changanfordwechat.com/r/cms/www/mobile/yiboBoss/images/shareimg.jpg';
            window.share.link = 'http://www.changanfordclub.com/infocollection/potentialcustomer/yiboBoss/index.jspx?channel=1'
            window.share.title= '小搏支招，TA靠【联合同事，提升动力】征服老板，完美！';
            window.share.desc = '想知道你搞定老板的独门秘籍？戳这里，戳这里，戳这里！！';
            shareConfig();
	} else { //resault1
				loadimages(['/r/cms/www/mobile/yiboBoss/images/homeBg.jpg','/r/cms/www/mobile/yiboBoss/images/home_txt.png','/r/cms/www/mobile/yiboBoss/images/resault1.png','/r/cms/www/mobile/yiboBoss/images/resault1_man.png','/r/cms/www/mobile/yiboBoss/images/resault1_light.png','/r/cms/www/mobile/yiboBoss/images/resault1_txt.png','/r/cms/www/mobile/yiboBoss/images/resault1_tip.png','/r/cms/www/mobile/yiboBoss/images/resault_lighton.png','/r/cms/www/mobile/yiboBoss/images/car3.png','/r/cms/www/mobile/yiboBoss/images/car3Shadow.png.png']);
			window.share.imgUrl = 'http://yiche.changanfordwechat.com/r/cms/www/mobile/yiboBoss/images/shareimg.jpg';
            window.share.link = 'http://www.changanfordclub.com/infocollection/potentialcustomer/yiboBoss/index.jspx?channel=1'
            window.share.title= '小搏支招，TA靠【想得更多，做得更好】征服老板，完美！';
            window.share.desc = '想知道你搞定老板的独门秘籍？戳这里，戳这里，戳这里！';
            shareConfig();
	}
	
	function loadimages(arr){
			var self=this;
			var newimages=[], loadedimages=0
			var arr=(typeof arr!="object")? [arr] : arr
			function imageloadpost(){
				loadedimages++;
		
				if (loadedimages==arr.length){
					
					setTimeout(function(){
						landing();
						console.log("加载完毕");
					},500)
				}
				var loadcent = loadedimages/arr.length*100;
				$(".loading_info").empty().html("正在分析你的信息 " + Math.round(loadcent) + "%")
			}
			
			for (var i=0; i<arr.length; i++){
				newimages[i]=new Image()
				newimages[i].src= arr[i]
				newimages[i].onload=function(){
					imageloadpost()
				}
				newimages[i].onerror=function(){
					imageloadpost()
				}
			}
	}
	
	function landing() {
		if (myscore == 0 || myscore == 4) {
			$(".Resault3").show();
			$(".loading_wrap").hide();
			$(".Resault3_title").addClass("Resault3_title_show");
			$(".Resault3_man").addClass("Resault3_man_show");
			$(".Resault3_txt").addClass("Resault3_txt_show");
			$(".Resault3_tip").addClass("Resault3_tip_show");
			$(".Resault1_carShadow").addClass("Resault1_carShadow_show");
			$(".Resault1_car").addClass("Resault1_car_show");
			setTimeout(function(){
				$(".Resault3_kou").addClass("Resault3_kou_show");
				$(".resault_arrow").addClass("Resault3_kou_show");
			},1200);
		} ;
		if (myscore == 1 || myscore == 5){
			$(".Resault4").show();
			$(".loading_wrap").hide();
			$(".Resault1_carShadow").addClass("Resault1_carShadow_show");
			$(".Resault1_car").addClass("Resault1_car_show");
			$(".Resault4_title").addClass("Resault4_title_show");
			$(".Resault4_girl").addClass("Resault4_girl_show");
			$(".Resault4_txt").addClass("Resault4_txt_show");
			$(".Resault4_tip").addClass("Resault4_tip_show");
			setTimeout(function(){
				$(".resault_arrow").addClass("Resault3_kou_show");
			},1200);
		}
		if (myscore == 2 || myscore == 6) {
			$(".Resault2").show();
			$(".loading_wrap").hide();
			$(".Resault2_carShadow").addClass("Resault2_carShadow_show");
			$(".Resault2_car").addClass("Resault2_car_show");
			$(".Resault2_title").addClass("Resault2_title_show");
			$(".Resault2_man1").addClass("Resault2_man1_show");
			$(".Resault2_man2").addClass("Resault2_man2_show");
			$(".Resault2_txt").addClass("Resault2_txt_show");
			$(".Resault2_tip").addClass("Resault2_tip_show");
			setTimeout(function(){
				$(".resault_arrow").addClass("Resault3_kou_show");
				$(".Resault2_pia").addClass("Resault2_pia_show");
			},800);
		}
		if (myscore == 3 || myscore == 7) {
			$(".Resault1").show();
			$(".loading_wrap").hide();
			$(".Resault1_carShadow").addClass("Resault1_carShadow_show");
			$(".Resault1_car").addClass("Resault1_car_show");
			$(".Resault1_title").addClass("Resault1_title_show");
			$(".Resault1_man").addClass("Resault1_man_show");
			$(".Resault1_txt").addClass("Resault1_txt_show");
			$(".Resault1_tip").addClass("Resault1_tip_show");
			setTimeout(function(){
				$(".resault_arrow").addClass("Resault3_kou_show");
				$(".Resault1_light").addClass("Resault1_light_show");
			},800);
		}
	
	}
	$(".btn_shareTimeline").click(function() {
		$(".shareTips").fadeIn();
	})
	
	$(".shareTips").click(function(){
		$(".shareTips").fadeToggle();
	})
	$(".btn_restart").click(function(){
		window.location.href = "http://yiche.changanfordwechat.com/infocollection/potentialcustomer/yiboBoss/index.jspx?channel=1";
	})
	$(".btn_viewVideo").click(function(){
		if (myscore == 3 || myscore == 7) {
			$(".Resault1").hide();
			$(".videoPlay").fadeIn();
		} else if (myscore == 2 || myscore == 6) {
			$(".Resault2").hide();
			$(".videoPlay").fadeIn();
		} else if (myscore == 1 || myscore == 5) {
			$(".Resault4").hide();
			$(".videoPlay").fadeIn();
		} else {
			$(".Resault3").hide();
			$(".videoPlay").fadeIn();
		}
	})

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

})
