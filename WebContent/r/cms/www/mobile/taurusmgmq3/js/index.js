$(function(){

	
function isTouchDevice() {
  //document.getElementById("version").innerHTML = navigator.appVersion;

   try {f
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
							setTimeout(function(){$(".page2 .p2t1").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".page2 .p2t2").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".page2 .arrowHandle").show().addClass("animated fadeIn")},1900); 
						})
			      } else if (swiper.activeIndex == 2) {
			      		$(".page3Bg").fadeIn(1000,function(){ 
							setTimeout(function(){$(".page3 .p3t1").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".page3 .p3t2").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".page3 .p3t3").show().addClass("animated fadeIn")},2400);
							setTimeout(function(){$(".page3 .p3t4").show().addClass("animated fadeIn")},3200);
							setTimeout(function(){$(".page3 .rulebtn").show().addClass("animated fadeIn")},4000);  
							setTimeout(function(){$(".page3 .arrowHandle").show().addClass("animated fadeInUp")},4800);
						})
			      } else if (swiper.activeIndex == 3) {
			      	$(".page4Bg").fadeIn(1000,function(){ 
							setTimeout(function(){$(".page4 .p4t1").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".page4 .p4t2").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".page4 .p4t3").show().addClass("animated fadeIn")},2400);
							setTimeout(function(){$(".page4 .p4t4").show().addClass("animated fadeIn")},3200); 
							setTimeout(function(){$(".page4 .arrowHandle").show().addClass("animated fadeInUp")},4000);
						})
			      } else if (swiper.activeIndex == 4) {
			      	//mySwiper.lockSwipeToNext();
			      	$(".page5Bg").fadeIn(1000,function(){ 
							setTimeout(function(){$(".page5 .split").show().addClass("animated fadeIn")},800);
							setTimeout(function(){$(".p5copy").show().addClass("animated fadeIn")},1600);
							setTimeout(function(){$(".btn").show().addClass("animated fadeIn")},1900);
						}) 
			      } 
			    },
			  }) 

			$(".page1 .btn").on("touchstart",function(){
	      		mySwiper.unlockSwipeToNext();
	      		mySwiper.slideNext();
	      	});
 
                        $(".page3 .rulebtn").on("touchstart",function(){
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
		setTimeout(function(){$(".page1 .p1t1").show().addClass("animated fadeIn")},800);
		setTimeout(function(){$(".page1 .p1t2").show().addClass("animated fadeIn")},1600);
		setTimeout(function(){$(".page1 .p1t3").show().addClass("animated fadeIn")},2400);
		setTimeout(function(){$(".page1 .btn").show().addClass("animated fadeInUp")},3000);
	})
}

			





