(function ($) {
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();
	var currPage = "0";
//	$('#bgmusic')[0].play();
	window.detecOrient = true;
	
//window.addEventListener('orientationchange', newcss);
	
	
	
	
	
//	$(window).resize(function(){
//		newcss();
//	});
	loadimg();
//	playMusic();
	
	
//	$('.music').click(function() {
//				var audio = document.getElementById('bgmusic');
//				if(audio!==null){             
//					//检测播放是否已暂停.audio.paused 在播放器播放时返回false.<span style="font-family: Arial, Helvetica, sans-serif;">在播放器暂停时返回true</span>  
//
//					if(!audio.paused)  
//					{                 
//						audio.pause();// 这个就是暂停//audio.play();// 这个就是播放  
//						$('.music img').attr('src','/r/cms/www/mobile/taurusmgm/assets/i/audioOff.png')
//					}else{
//						audio.play();
//						$('.music img').attr('src','/r/cms/www/mobile/taurusmgm/assets/i/audio.png')
//					}  
//				}  
//		});

	
	
	
var mySwiper = new Swiper ('.swiper-container', {
	    direction: 'horizontal',
	    noSwiping:false,
	    width : 1280,
	    height : 1044,
	    loop: false,
	    touchRatio : 0.1,
	    allowSwipeToNext : false,
	    resistanceRatio : 0.3,
	    onInit: function(swiper){
	    	    currPage = "0";
      			$(".p1btn1").on('touchstart', function(){
//				$('#scene1').parallax({
//								  calibrateX: false,
//								  calibrateY: false,
//								  invertX: false,
//								  invertY: true,
//								  limitX: 130,
//								  limitY: 0,
//								  scalarX: 10,
//								  scalarY: 0,
//								  frictionX: 0.4,
//								  frictionY: 0.2,
//								  originX: 0,
//								  originY: 0
//							});
				   	$(".p1btn1").fadeOut();
				   	$(".p1copy2").fadeOut();
				   	$(".movePhone").fadeIn();
				    $(".movePhoneCopy").fadeIn();
				    mySwiper.unlockSwipeToNext();
				   // window.addEventListener("deviceorientation",orientationHandler, false);
				   })
      			$(".p1btn2").on("touchstart", function(){
      				$("#ruleOverlay").fadeIn();
      			})
      			$(".ruleTxt").on("touchstart", function(){
      				$("#ruleOverlay").fadeOut();
      			})
    },
	    onSlideChangeEnd: function(swiper) {
	    	if (mySwiper.activeIndex == 0) {
//	    		window.addEventListener("deviceorientation",orientationHandler, false);
	    	}
	    	if (mySwiper.activeIndex == 1) {
	    		mySwiper.lockSwipeToNext();
	    		mySwiper.lockSwipeToPrev();
				currPage = "1";
	    		setTimeout(function(){
	    			$(".p2light").fadeOut(1600);
	    			$(".overGray").fadeOut(1500,function(){
	    				
	    				$(".grayBg").fadeOut(1500,function(){$(".p2copy1").fadeIn(2000,function(){
//						   $('#scene2').parallax({
//										  calibrateX: true,
//										  calibrateY: false,
//										  invertX: false,
//										  invertY: true,
//										  limitX: 130,
//										  limitY: 0,
//										  scalarX: 10,
//										  scalarY: 0,
//										  frictionX: 0.4,
//										  frictionY: 0.2,
//										  originX: 0,
//										  originY: 0
//									});
				    		mySwiper.unlockSwipeToNext();
				    		mySwiper.unlockSwipeToPrev();
				    		//window.addEventListener("deviceorientation",orientationHandler, false);
				    	
	    				})});
	    				
	    			});
	    		},500)
	    		
	    	}
	    	if (mySwiper.activeIndex == 2) {
	    		currPage = "2";
	    		mySwiper.lockSwipeToNext();
	    		mySwiper.lockSwipeToPrev();
	    		$(".page3bg").addClass("page3bgAni");
	    		setTimeout(function(){
	    					$(".p3copy1").fadeIn(2000);
//	    					$('#scene3').parallax({
//								  calibrateX: true,
//								  calibrateY: false,
//								  invertX: false,
//								  invertY: true,
//								  limitX: 130,
//								  limitY: 0,
//								  scalarX: 20,
//								  scalarY: 0,
//								  frictionX: 0.4,
//								  frictionY: 0.2,
//								  originX: 0,
//								  originY: 0
//							});
				    		mySwiper.unlockSwipeToNext();
				    		mySwiper.unlockSwipeToPrev();
				    		//window.addEventListener("deviceorientation",orientationHandler, false);
	    		},500)
	    	}
	    	if (mySwiper.activeIndex == 3) {
	    		currPage = "3";
	    		$(".logo1").show();
	    		mySwiper.lockSwipeToNext();
	    		mySwiper.lockSwipeToPrev();
	    		$(".cardrive").addClass("cardriveIn");
	    		setTimeout(function(){
	    			$(".frontwheel").hide();
	    			$(".backWheel").hide();
	    		},2700)
	    		setTimeout(function(){
	    			$(".p4copy1").fadeIn(2000,function(){
//					$('#scene4').parallax({
//								  calibrateX: true,
//								  calibrateY: false,
//								  invertX: false,
//								  invertY: true,
//								  limitX: 130,
//								  limitY: 0,
//								  scalarX: 20,
//								  scalarY: 0,
//								  frictionX: 0.4,
//								  frictionY: 0.2,
//								  originX: 0,
//								  originY: 0
//							});
			    		mySwiper.unlockSwipeToNext();
			    		mySwiper.unlockSwipeToPrev();
			    		//window.addEventListener("deviceorientation",orientationHandler, false);
	    			})
	    		},3000)
	    	}
	    	if (mySwiper.activeIndex == 4)	{
	    		currPage = "4";
	    		$(".logo1").hide();
	    		$(".giftPic,.giftPic2,.notice").on("touchstart",function(){
	    			$("#ruleOverlay").fadeIn();
	    		})
	    	}
	    }
  })  	

//function orientationHandler(event){
//	var posi = 0;
//	switch (currPage) {
//	case "0" :
//	var str = $(".page1Bg").css("transform");
//	strs=str.split(",");
//	posi = parseInt(strs[4]);
//	if (posi<-110) {
//		mySwiper.slideTo(1);
//		//window.removeEventListener("deviceorientation",orientationHandler, false);
//	}
//	//$(".test360").html("").html(currPage + "||" + posi);
//	break;
//	case "1" :
//	var str = $(".page2Holder").css("transform");
//	strs=str.split(",");
//	posi = parseInt(strs[4]);
//	if (posi< -110) {
//		window.removeEventListener("deviceorientation",orientationHandler, false);
//		mySwiper.slideTo(2);
//	} else if (posi>110) {
//		window.removeEventListener("deviceorientation",orientationHandler, false);
//		mySwiper.slideTo(0);
//		
//	}
//	//$(".test360").html("").html(currPage + "||" + posi);
//	break;
//	case "2" :
//	var str = $(".page3bg").css("transform");
//	strs=str.split(",");
//	posi = parseInt(strs[4]);
//	if (posi<-110) {
//		window.removeEventListener("deviceorientation",orientationHandler, false);
//		mySwiper.slideTo(3);
//		
//	} else if (posi>110) {
//		window.removeEventListener("deviceorientation",orientationHandler, false);
//		mySwiper.slideTo(1);
//		
//	}
//	//$(".test360").html("").html(currPage + "||" + posi);
//	break;
//	case "3" :
//	var str = $(".driveHolder").css("transform");
//	strs=str.split(",");
//	posi = parseInt(strs[4]);
//	if (posi<-110) {
//		window.removeEventListener("deviceorientation",orientationHandler, false);
//		mySwiper.slideTo(4);
//	} else if (posi>110) {
//		window.removeEventListener("deviceorientation",orientationHandler, false);
//		mySwiper.slideTo(2);
//	}
//	//$(".test360").html("").html(currPage + "||" + posi);
//	break;
//	case "4" :
//	posi = 0;
//	window.removeEventListener("deviceorientation",orientationHandler, false);
//	//$(".test360").html("").html(currPage + "||" + posi);
//	break;
//	}
//}


})(jQuery);

function loadimg() {	
        var loaded = 0;
        var me = this;
        if ($('body img').length === 0 || navigator.userAgent.indexOf('MSIE 9') != -1) {
            show_page();
        } else {
            $('body img').imagesLoaded().progress(function (instance, image) {
                loaded++;
                var loadingScale = loaded / instance.images.length;
                $('.ploading').text(parseInt(loadingScale * 100) + '%');
                //$('#J-loading-pross').width(parseInt(loadingScale * 100) + '%')

                if (loaded == instance.images.length) {
                    show_page(me);
                }
            });
        }
        // work script
        function show_page(me) {
            setTimeout(function () {
                $('.ploading').fadeOut();
                //me.initSwiper(me);
            }, 100);
        }
}
    //function initSwiper(me) {}
    

//  function playMusic() {
//      this.audioElement = new Audio('/r/cms/www/mobile/taurusmgm/assets/i/sync.mp3');
//      this.audioElement.controls = false;
//      this.audioElement.loop = true;
//      this.audioElement.autoplay = true;
//      this.audioElement.load();
//      this.audioElement.play();
//  }


//function newcss(){
////   windowWidth=$(window).width();
////   windowHeight=$(window).height();
////	 if(windowWidth>windowHeight){
////		 $(".VerticalScreenTip").show();
////	}else{
////		$(".VerticalScreenTip").hide();
////	}
//if ( window.orientation == 180 || window.orientation==0 ) {
//      alert("竖屏");
//  }
//  if( window.orientation == 90 || window.orientation == -90 ) {
//      alert("横屏");
//  }
//}
