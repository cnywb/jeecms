$(function(){
	var myscore = urlRequest("score");
	$(".loading1").addClass("loading1_in");
	//alert(myscore);
setTimeout(function(){

	if (myscore == 1) {   
		loadimages(['assets/i/resaul1_head.png','assets/i/r1_txt.png','assets/i/resault1_superMan.png','assets/i/resault_bg.jpg','assets/i/resault_foot.png','assets/i/share_bg.jpg','assets/i/share_man.png','assets/i/loadingMan2.png']);
	} else if (myscore == 2) { 
		loadimages(['assets/i/resaul2_head.png','assets/i/resault2_txt.png','assets/i/resault2_superMan.png','assets/i/resault_bg.jpg','assets/i/resault_foot.png','assets/i/share_bg.jpg','assets/i/share_man.png']);
	} else if (myscore == 3) { 
		loadimages(['assets/i/resaul3_head.png','assets/i/resault3_txt.png','assets/i/resault3_superMan.png','assets/i/resault_bg.jpg','assets/i/resault_foot.png','assets/i/share_bg.jpg','assets/i/share_man.png']);
	} else if (myscore == 4){ 
		loadimages(['assets/i/resaul4_head.png','assets/i/resault4_txt.png','assets/i/resault4_superMan.png','assets/i/resault_bg.jpg','assets/i/resault_foot.png','assets/i/share_bg.jpg','assets/i/share_man.png']);
	} else {
		loadimages(['assets/i/resaul5_head.png','assets/i/resault5_txt.png','assets/i/resault5_superMan.png','assets/i/resault_bg.jpg','assets/i/resault_foot.png','assets/i/share_bg.jpg','assets/i/share_man.png']);
	
	}
},2000)
	
	function loadimages(arr){
			var self=this;
			var newimages=[], loadedimages=0
			var arr=(typeof arr!="object")? [arr] : arr
			function imageloadpost(){
				loadedimages++;
		
				if (loadedimages==arr.length){
					setTimeout(function(){
						$(".loading1").addClass("loading1_out");
					},500)
					setTimeout(function(){
						landing();
						console.log("加载完毕");
					},500)
				}
				var loadcent = loadedimages/arr.length*100;
				$(".loading-txt").empty().html("LOADING " + Math.round(loadcent) + "%")
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
		
//		$("#danmup").danmuplayer({
//			  src:"assets/i/Goddess.mp4",       //视频源
//			  width:610,            //视频宽度
//			  height:388            //视频高度
//			});
		
		
		
		$(".resault_btnBg").fadeIn();
		if (myscore == 1) {
			$(".resaultOne").fadeIn();
			$(".loading_wrap").hide();
			setTimeout(function(){
				$(".r1_head").show().addClass("animated fadeIn");
			},800)
			
			setTimeout(function(){
				$(".r1_txt").show().addClass("animated fadeInDown");
				$(".r1_man").show().addClass("animated fadeInLeftBig");
			},1200);
		} ;
		if (myscore == 2){
			$(".resaultTwo").fadeIn();
			$(".loading_wrap").hide();
			setTimeout(function(){
				$(".r2_head").show().addClass("animated fadeIn");
			},800)
			setTimeout(function(){
				$(".r2_txt").show().addClass("animated fadeInDown");
				$(".r2_man").show().addClass("animated fadeInLeftBig");
			},1200);
		}
		if (myscore == 3) {
			$(".resaultThree").fadeIn();
			$(".loading_wrap").hide();
			setTimeout(function(){
				$(".r3_head").show().addClass("animated fadeIn");
			},800)
			setTimeout(function(){
				$(".r3_txt").show().addClass("animated fadeInDown");
				$(".r3_man").show().addClass("animated fadeInLeftBig");
			},1200);
		}
		if (myscore == 4) {
			$(".resaultFour").fadeIn();
			$(".loading_wrap").hide();
			setTimeout(function(){
				$(".r4_head").show().addClass("animated fadeIn");
			},800)
			setTimeout(function(){
				$(".r4_txt").show().addClass("animated fadeInDown");
				$(".r4_man").show().addClass("animated fadeInLeftBig");
			},1200);
		}
		if (myscore == 5) {
			$(".resaultFive").fadeIn();
			$(".loading_wrap").hide();
			setTimeout(function(){
				$(".r5_head").show().addClass("animated fadeIn");
			},800)
			setTimeout(function(){
				$(".r5_txt").show().addClass("animated fadeInDown");
				$(".r5_man").show().addClass("animated fadeInLeftBig");
			},1200);
		}
	}
	$(".btn_share").on('touchstart',function() {
		$(".shareTips").fadeIn();
	})
	
	$(".shareTips").click(function(){
		$(".shareTips").fadeToggle();
	})
	$(".btn_tryagain").click(function(){
		window.location.href = "http://www.changanfordclub.com/infocollection/potentialcustomer/yiboGoddess/index.jspx?channel=1";
	})
	$(".btn_gift").click(function(){
			$(".videoPlay").fadeIn();
			$(".shareTips").hide();

	})
	$(".closeBtn").on('touchstart',function(){
		jwplayer("danmup").stop();
		$('.videoPlay').fadeOut();
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
