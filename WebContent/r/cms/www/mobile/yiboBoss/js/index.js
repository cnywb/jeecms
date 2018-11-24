$(function(){
	var score = 0;
	var currentQ = 1;
	//loading
//	var img_list={
//		"cover":{
//			"img_1":"homeBg.jpg",
//			"img_2":"home_txt.png",
//			"img_3":"homeCar.png",
//			"img_4":"Hometiger.png",
//			"img_5":"logo.png"
//		},
//		"q_1":{
//			"img_1":"questionBg.jpg",
//			"img_2":"q1.png"
//		},
//	};

	loadimages(['/r/cms/www/mobile/yiboBoss/images/homeBg.jpg','/r/cms/www/mobile/yiboBoss/images/zazaza.gif','/r/cms/www/mobile/yiboBoss/media/music.mp3','/r/cms/www/mobile/yiboBoss/media/tigerVoice.mp3','/r/cms/www/mobile/yiboBoss/images/home_txt.png','/r/cms/www/mobile/yiboBoss/images/A1L.png','/r/cms/www/mobile/yiboBoss/images/a1l_bg.png','/r/cms/www/mobile/yiboBoss/images/a1l_man.png','/r/cms/www/mobile/yiboBoss/images/a1R.png','/r/cms/www/mobile/yiboBoss/images/a1r_bg.png','/r/cms/www/mobile/yiboBoss/images/a1r_man.png','/r/cms/www/mobile/yiboBoss/images/a2l.png','/r/cms/www/mobile/yiboBoss/images/a2l_bg.png','/r/cms/www/mobile/yiboBoss/images/a2l_coffee.png','/r/cms/www/mobile/yiboBoss/images/a2r_bg.png','/r/cms/www/mobile/yiboBoss/images/a2r_Tea.png','/r/cms/www/mobile/yiboBoss/images/a2r_Txt.png','/r/cms/www/mobile/yiboBoss/images/a3l_bg.png','/r/cms/www/mobile/yiboBoss/images/a3l_hand.png','/r/cms/www/mobile/yiboBoss/images/a3l_txt.png','/r/cms/www/mobile/yiboBoss/images/a3r_bg.png','/r/cms/www/mobile/yiboBoss/images/a3r_hand.png'
	,'/r/cms/www/mobile/yiboBoss/images/a3r_txt.png','/r/cms/www/mobile/yiboBoss/images/a4l.png','/r/cms/www/mobile/yiboBoss/images/a4r.png','/r/cms/www/mobile/yiboBoss/images/a5l.png','/r/cms/www/mobile/yiboBoss/images/a5r.png','/r/cms/www/mobile/yiboBoss/images/a7l_zheng.png','/r/cms/www/mobile/yiboBoss/images/a7r_fan.png','/r/cms/www/mobile/yiboBoss/images/arrow.png','/r/cms/www/mobile/yiboBoss/images/homeCar.png','/r/cms/www/mobile/yiboBoss/images/homeEnterBtn.png','/r/cms/www/mobile/yiboBoss/images/Hometiger.png','/r/cms/www/mobile/yiboBoss/images/logo.png','/r/cms/www/mobile/yiboBoss/images/q1.png','/r/cms/www/mobile/yiboBoss/images/q2.png','/r/cms/www/mobile/yiboBoss/images/q3.png','/r/cms/www/mobile/yiboBoss/images/q4.png','/r/cms/www/mobile/yiboBoss/images/q5.png','/r/cms/www/mobile/yiboBoss/images/q6.png','/r/cms/www/mobile/yiboBoss/images/q7.png','/r/cms/www/mobile/yiboBoss/images/questionBg.jpg','/r/cms/www/mobile/yiboBoss/images/questionCar.png']);
	
	function loadimages(arr){
			var self=this;
			var newimages=[], loadedimages=0
			var arr=(typeof arr!="object")? [arr] : arr
			function imageloadpost(){
				loadedimages++;
		
				if (loadedimages==arr.length){
					setTimeout(function(){
						$('.music').fadeIn(500);
					},1000)
					setTimeout(function(){
						landing();
						console.log("加载完毕");
					},500)
				}
				var loadcent = loadedimages/arr.length*100;
				$(".loading_info").empty().html("LOADING " + Math.round(loadcent) + "%")
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
                audio = document.getElementById('bgmusic');
		//$('#bgmusic')[0].play();
		$(".loading_wrap").hide();
		$(".firstStage").show();
		$(".homeCar").addClass("homeCar_in");
		$(".homeTxt").addClass("homeTxt_in");
		$(".homeTiger").addClass("homeTiger_in");
		$(".homeStartBtn").addClass("homeStartBtn_in");
		setTimeout(function(){
			$(".homeAni").append("<img src='/r/cms/www/mobile/yiboBoss/images/zazaza.gif' class='tigeranimated'>")
		},2500);
		setTimeout(function(){
			$('#tigerVoice')[0].play();	
		},2500);
		setTimeout(function(){
			$('#bgmusic')[0].play();
			$(".homeAni").hide();
		},3500)
	}

	
	function jumppage(qq) {
		if (qq == 1) {
			console.log(qq);
			currentQ = qq +1;
			$(".firstStage").hide();
			$(".homeCar").removeClass("homeCar_in");
			$(".homeTxt").removeClass("homeTxt_in");
			$(".homeTiger").removeClass("homeTiger_in");
			$(".homeStartBtn").removeClass("homeStartBtn_in");
			$(".question1").show();
			$(".q1Txt").addClass("q1Txt_show");
			$(".question1>.qaCar").addClass("qaCar_show");
			$(".a1l_bg").addClass("a1l_bg_show");
			$(".a1l_man").addClass("a1l_man_show");
			$(".a1l_txt").addClass("a1l_txt_show");
			$(".a1r_bg").addClass("a1r_bg_show");
			$(".a1r_man").addClass("a1r_man_show");
			$(".a1r_txt").addClass("a1r_txt_show");
		}
		if (qq == 2) {
			console.log(qq);
			currentQ = qq +1;
			$(".question1").hide();
			$(".q1Txt").removeClass("q1Txt_show");
			$(".question1>.qaCar").removeClass("qaCar_show");
			$(".a1l_bg").removeClass("a1l_bg_show");
			$(".a1l_man").removeClass("a1l_man_show");
			$(".a1l_txt").removeClass("a1l_txt_show");
			$(".a1r_bg").removeClass("a1r_bg_show");
			$(".a1r_man").removeClass("a1r_man_show");
			$(".a1r_txt").removeClass("a1r_txt_show");
			$(".question2").show();
			$(".q2Txt").addClass("q2txt_show");
			$(".question2>.qaCar").addClass("qaCar_show");
			$(".a2l_bg").addClass("a2l_bg_show");
			$(".a2l_coffee").addClass("a2l_coffee_show");
			$(".a2l_txt").addClass("a2l_txt_show");
			$(".a2r_bg").addClass("a2r_bg_show");
			$(".a2r_tea").addClass("a2r_tea_show");
			$(".a2r_txt").addClass("a2r_txt_show");
		}
		if (qq == 3) {
			console.log(qq);
			currentQ = qq +1;
			$(".question2").hide();
			$(".q2Txt").removeClass("q2txt_show");
			$(".question2>.qaCar").removeClass("qaCar_show");
			$(".a2l_bg").removeClass("a2l_bg_show");
			$(".a2l_coffee").removeClass("a2l_coffee_show");
			$(".a2l_txt").removeClass("a2l_txt_show");
			$(".a2r_bg").removeClass("a2r_bg_show");
			$(".a2r_tea").removeClass("a2r_tea_show");
			$(".a2r_txt").removeClass("a2r_txt_show");
			$(".question3").show();
			$(".q3Txt").addClass("q3txt_show");
			$(".question3>.qaCar").addClass("qaCar_show");
			$(".a3l_bg").addClass("a3l_bg_show");
			$(".a3l_hand").addClass("a3l_hand_show");
			$(".a3l_txt").addClass("a3l_txt_show");
			$(".a3r_bg").addClass("a3r_bg_show");
			$(".a3r_hand").addClass("a3r_hand_show");
			$(".a3r_txt").addClass("a3r_txt_show");
		}
		if (qq == 4) {
			console.log(qq);
			currentQ = qq +1;
			$(".question3").hide();
			$(".q3Txt").removeClass("q3txt_show");
			$(".question3>.qaCar").removeClass("qaCar_show");
			$(".a3l_bg").removeClass("a3l_bg_show");
			$(".a3l_hand").removeClass("a3l_hand_show");
			$(".a3l_txt").removeClass("a3l_txt_show");
			$(".a3r_bg").removeClass("a3r_bg_show");
			$(".a3r_hand").removeClass("a3r_hand_show");
			$(".a3r_txt").removeClass("a3r_txt_show");
			$(".question4").show();
			$(".q4Txt").addClass("q4Txt_show");
			$(".question4>.qaCar").addClass("qaCar_show");
			$(".question4>.a4l").addClass("a4l_show");
			$(".question4>.a4r").addClass("a4r_show");
		}
		if (qq == 5) {
			console.log(qq);
			currentQ = qq +1;
			$(".question4").hide();
			$(".q4Txt").removeClass("q4Txt_show");
			$(".question4>.qaCar").removeClass("qaCar_show");
			$(".question4>.a4l").removeClass("a4l_show");
			$(".question4>.a4r").removeClass("a4r_show");
			$(".question5").show();
			$(".q5Txt").addClass("q5Txt_show");
			$(".question5>.qaCar").addClass("qaCar_show");
			$(".a5l").addClass("a5l_show");
			$(".a5r").addClass("a5r_show");
		}
		if (qq == 6) {
			console.log(qq);
			currentQ = qq +1;
			$(".question5").hide();
			$(".q5Txt").removeClass("q5Txt_show");
			$(".question5>.qaCar").removeClass("qaCar_show");
			$(".a5l").removeClass("a5l_show");
			$(".a5r").removeClass("a5r_show");
			$(".question6").show();
			$(".q6Txt").addClass("q6Txt_show");
			$(".question6 .qaCar").addClass("qaCar_show");
			$(".question6 .a4l").addClass("a4l_show");
			$(".question6 .a4r").addClass("a4r_show");
		}
		if (qq == 7) {
			console.log(qq);
			$(".question5").hide();
			$(".q6Txt").removeClass("q6Txt_show");
			$(".question6>.qaCar").removeClass("qaCar_show");
			$(".question6>.a4l").removeClass("a4l_show");
			$(".question6>.a4r").removeClass("a4r_show");
			$(".question7").show();
			$(".q7Txt").addClass("q7Txt_show");
			$(".question7>.qaCar").addClass("qaCar_show");
			$(".question7>.a7l_zheng").addClass("a7l_zheng_show");
			$(".question7>.a7r_fan").addClass("a7r_fan_show");
			$(".viewResault").addClass("viewResault_show");
		}
	}
//	function goPage(pageNo) {
//		if (pageNo == 1) {
//			$(".firstStage").hide();
//			currentQ = pageNo + 1;
//		}
//	}
	

	$(".homeStartBtn,.a1r_txt,.a1r_man,.a2r_tea,.a2r_txt,.a3r_hand,.a3r_txt,.a4r,.a5r").click(function() {
		jumppage(currentQ);
	})
	$(".a1l_txt, .a1l_man,.a2l_coffee, .a2l_txt, .a3l_hand, .a3l_txt, .a5l, .a4l").click(function(){
		score = score + 1;
		jumppage(currentQ);
	})
	$(".a7l_zheng").click(function(){
		score = score + 1;
		console.log("总分是:" + score);
		$(".homeStartBtn,.a1r_txt,.a1r_man,.a2r_tea,.a2r_txt,.a3r_hand,.a3r_txt,.a4r,.a5r,.a4r,.a7l_zheng").unbind();
		$(".a1l_txt, .a1l_man,.a2l_coffee,.a2l_txt,.a3l_hand,.a3l_txt,.a4l,.a5l,.a4l,.a7r_fan").unbind();
		$(".viewResault").addClass("viewResaultAni");
		window.location.href="/r/cms/www/mobile/yiboBoss/resault.html?score="+score;
	})
	$(".a7r_fan").click(function() {
		console.log("总分是:" + score);
		$(".homeStartBtn,.a1r_txt,.a1r_man,.a2r_tea,.a2r_txt,.a3r_hand,.a3r_txt,.a4r,.a5r,.a4r,.a7l_zheng").unbind();
		$(".a1l_txt, .a1l_man,.a2l_coffee,.a2l_txt,.a3l_hand,.a3l_txt,.a4l,.a5l,.a4l,.a7r_fan").unbind();
		$(".viewResault").addClass("viewResaultAni");
		window.location.href="/r/cms/www/mobile/yiboBoss/resault.html?score="+score;
	})
	
	$(".viewResault").click(function() {
		//alert("your score is" + score);
		//window.location.href="resault.html?score="+score;
	})

		$('.music').click(function() {
				var audio = document.getElementById('bgmusic');
				if(audio!==null){             
					//检测播放是否已暂停.audio.paused 在播放器播放时返回false.<span style="font-family: Arial, Helvetica, sans-serif;">在播放器暂停时返回true</span>  

					if(!audio.paused)  
					{                 
						audio.pause();// 这个就是暂停//audio.play();// 这个就是播放  
						$('.music img').attr('src','/r/cms/www/mobile/yiboBoss/images/audioOff.png')
					}else{
						audio.play();
						$('.music img').attr('src','/r/cms/www/mobile/yiboBoss/images/audio.png')
					}  
				}  
		});
	
	
	


})
