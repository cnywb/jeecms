$(function(){

	
function isTouchDevice() {
  //document.getElementById("version").innerHTML = navigator.appVersion;

   try {
      //document.createEvent("TouchEvent");
      console.log("支持TouchEvent事件！");

       //bindEvent(); //绑定事件
        }
    catch (e) {
      console.log("不支持TouchEvent事件！" + e.message);
      }
      }
	
//Loading动画
var loaders ={

		width: 100,
		height: 50,
		padding: 10,
		stepsPerFrame: 2,
		trailLength: 1,
		pointDistance: .03,
		strokeColor: '#FF7B24',
		
		step: 'fader',
		multiplier: 2,
		setup: function() {
			this._.lineWidth = 5;
		},
		path: [
		
			['arc', 10, 10, 10, -270, -90],
			['bezier', 10, 0, 40, 20, 20, 0, 30, 20],
			['arc', 40, 10, 10, 90, -90],
			['bezier', 40, 0, 10, 20, 30, 0, 20, 20]
		]
};

	var container = document.getElementById('loadingCavas');
	
	var loadAni = new Sonic(loaders);
	
	container.appendChild(loadAni.canvas);
	loadAni.play();
	
//加载资源
	imgLoad();
			var mySwiper = new Swiper ('.swiper-container', {
			    loop: false,
			    autoplay: false,
			    direction:'vertical',
			    onSlideChangeEnd: function(swiper){
			    	console.log(swiper.activeIndex);
			      if (swiper.activeIndex == 1) {
			      		$(".page2Bg").fadeIn(1000,function(){
							$(".p2title").show().addClass("animated slideInLeft");
							setTimeout(function(){$(".page2 .split").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".p2copy2").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".p2pic").show().addClass("animated fadeIn")},1900);
							setTimeout(function(){$(".p2copy").show().addClass("animated fadeIn")},2400);
							setTimeout(function(){$(".page2 .p2bling").show().addClass("animated fadeIn")},2800);
							setTimeout(function(){$(".page2 .arrowHandle").show().addClass("animated fadeInUp")},2800);
						})
			      } else if (swiper.activeIndex == 2) {
			      		$(".page3Bg").fadeIn(1000,function(){
							$(".p3title").show().addClass("animated fadeIn");
							setTimeout(function(){$(".page3 .split").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".p3copy").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".p3pic").show().addClass("animated fadeIn")},1900);
							setTimeout(function(){$(".p3copy2").show().addClass("animated fadeIn")},2400);
							setTimeout(function(){$(".page3 .p3bling").show().addClass("animated fadeIn")},2800);
							setTimeout(function(){$(".page3 .arrowHandle").show().addClass("animated fadeInUp")},2800);
						})
			      } else if (swiper.activeIndex == 3) {
			      	$(".page4Bg").fadeIn(1000,function(){
							$(".p4title").show().addClass("animated fadeIn");
							setTimeout(function(){$(".page4 .split").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".p4copy2").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".p4pic").show().addClass("animated fadeIn")},1900);
							setTimeout(function(){$(".p4copy").show().addClass("animated fadeIn")},2400);
							setTimeout(function(){$(".page4 .p4bling").show().addClass("animated fadeIn")},2800);
							setTimeout(function(){$(".page4 .arrowHandle").show().addClass("animated fadeInUp")},2800);
						})
			      } else if (swiper.activeIndex == 4) {
			      	//mySwiper.lockSwipeToNext();
			      	$(".page5Bg").fadeIn(1000,function(){
							$(".p5title").show().addClass("animated fadeIn");
							setTimeout(function(){$(".page5 .split").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".p5copy").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".btn").show().addClass("animated fadeIn")},1900);
						})
			      	$(".btn").on("touchstart",function(){
			      		mySwiper.unlockSwipeToNext();
			      		mySwiper.slideNext();
			      	})
			      } 
			    },
			  }) 
			  
			  $(".giftPic,.giftPic2,.notice").on("touchstart",function(){
	    			$("#ruleOverlay").fadeIn();
	    		})
			  $(".ruleTxt").on("touchstart", function(){
      				$("#ruleOverlay").fadeOut();
      			})
})

var imgLoad = function(){
        var loaded = 0;
        var me = this;
        if ($('body img').length === 0 || navigator.userAgent.indexOf('MSIE 9') != -1) {
            show_page();
        } else {
            $('body img').imagesLoaded().progress(function (instance, image) {
                loaded++;
                var loadingScale = loaded / instance.images.length;
                $('.loadingTxt').html(parseInt(loadingScale * 100) + '%');

                if (loaded == instance.images.length) {
                    show_page(me);
                }
            });
        }
        // work script
        function show_page(me) {
            setTimeout(function () {
                $('.loading').fadeOut();
                $('.wrap').fadeIn();
				landing();
				var iw = $(window).width();
				var ih = $(window).height(); 
				
            }, 100);
        }
}


function landing() {
	$(".page1Bg").fadeIn(1000,function(){
		$(".p1title").show().addClass("animated fadeIn");
		setTimeout(function(){$(".page1 .split").show().addClass("animated fadeIn")},800);
		setTimeout(function(){$(".p1copy1").show().addClass("animated fadeIn")},1600);
		setTimeout(function(){$(".xz").show().addClass("animated fadeIn")},2400);
		setTimeout(function(){$(".stars").show().addClass("animated fadeIn")},2800);
		setTimeout(function(){$(".page1 .arrowHandle").show().addClass("animated fadeInUp")},2800);
	})
}

			





