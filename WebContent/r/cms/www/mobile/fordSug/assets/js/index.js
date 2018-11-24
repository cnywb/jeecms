$(function(){
//alert("213123123");
// window.onload = isTouchDevice;
//      $(document).on('touchmove.prevent', function(e) {
//      e.preventDefault();
//  });

//for (var i=0;i<121;i++) {
//				$(".imgGroup").append("<img src='/r/cms/www/mobile/fordSug/assets/i/h1/" + i + ".png'/>");
	//			$(".imgGroup").append("<img src='/r/cms/www/mobile/fordSug/assets/i/h2/" + i + ".png'/>");
	//			$(".imgGroup").append("<img src='/r/cms/www/mobile/fordSug/assets/i/h3/" + i + ".png'/>");
	//			$(".imgGroup").append("<img src='/r/cms/www/mobile/fordSug/assets/i/h4/" + i + ".png'/>");
	//		}

//function isTouchDevice() {
////document.getElementById("version").innerHTML = navigator.appVersion;
//
// try {
//    //document.createEvent("TouchEvent");
//    console.log("支持TouchEvent事件！");
//
//     //bindEvent(); //绑定事件
//      }
//  catch (e) {
//    console.log("不支持TouchEvent事件！" + e.message);
//    }
//    }

//Loading动画
//var loaders ={
//
//		width: 100,
//		height: 100,
//
//		stepsPerFrame: 1,
//		trailLength: 1,
//		pointDistance: .025,
//
//		strokeColor: '#05E2FF',
//
//		fps: 20,
//
//		setup: function() {
//			this._.lineWidth = 2;
//		},
//		step: function(point, index) {
//
//			var cx = this.padding + 50,
//				cy = this.padding + 50,
//				_ = this._,
//				angle = (Math.PI/180) * (point.progress * 360);
//
//			this._.globalAlpha = Math.max(.5, this.alpha);
//
//			_.beginPath();
//			_.moveTo(point.x, point.y);
//			_.lineTo(
//				(Math.cos(angle) * 35) + cx,
//				(Math.sin(angle) * 35) + cy
//			);
//			_.closePath();
//			_.stroke();
//
//			_.beginPath();
//			_.moveTo(
//				(Math.cos(-angle) * 32) + cx,
//				(Math.sin(-angle) * 32) + cy
//			);
//			_.lineTo(
//				(Math.cos(-angle) * 27) + cx,
//				(Math.sin(-angle) * 27) + cy
//			);
//			_.closePath();
//			_.stroke();
//
//		},
//		path: [
//			['arc', 50, 50, 40, 0, 360]
//		]
//};
//
//	var container = document.getElementById('loadingCavas');
//
//	var loadAni = new Sonic(loaders);
//
//	container.appendChild(loadAni.canvas);
//	loadAni.play();

//加载资源
	imgLoad();

	$(".oneRule, .tccopy,.tccopy2").on("touchstart",function(){
		$(".ruleLay").fadeIn()
	})
	$(".ruleDetail").on("touchstart",function(){
		$(".ruleLay").fadeOut()
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
               $('.landing').fadeIn();

				var iw = $(window).width();
				var ih = $(window).height();
				//alert(ih);
				$(".mainPage").css("height",ih);
				landing();

            }, 100);
        }
}

var landing = function() {
	$(".mainPage").fadeIn();

var mySwiper = new Swiper('.mainPage',{
	onInit: function(swiper){
$(".nextArrow").on("touchstart",function(){
	mySwiper.slideNext();
})
      //frame1.play();
   },
   onSlideChangeEnd: function(swiper){

   	console.log(mySwiper.activeIndex);
      if(mySwiper.activeIndex == "1"){
      //	frame2.play();
      }
      if(mySwiper.activeIndex == "2"){
      //	frame3.play();
      }
      if(mySwiper.activeIndex == "3"){
      //	frame4.play();
      }
      if(mySwiper.activeIndex == "0"){
      //	frame1.play();
      }
if(mySwiper.activeIndex == "4"){
      	$(".ruleLay").fadeIn();
      }
    }
	})
}

   var arrowOne = document.getElementById('arrowAnimate1');
		var aframe1 = new frameAnimate(arrowOne , {
			width: 200,
			height: 100,
			fps:6,
			frame: 6,
			loop: true
		}, function() {
			console.log("==stop==");
		});

aframe1.play();

   var arrowTwo = document.getElementById('arrowAnimate2');
		var aframe2 = new frameAnimate(arrowTwo , {
			width: 200,
			height: 100,
			fps:6,
			frame: 6,
			loop: true
		}, function() {
			console.log("==stop==");
		});

aframe2.play();

   var arrowThree = document.getElementById('arrowAnimate3');
		var aframe3 = new frameAnimate(arrowThree , {
			width: 200,
			height: 100,
			fps:6,
			frame: 6,
			loop: true
		}, function() {
			console.log("==stop==");
		});

aframe3.play();

   var arrowFour = document.getElementById('arrowAnimate4');
		var aframe4 = new frameAnimate(arrowFour , {
			width: 200,
			height: 100,
			fps:6,
			frame: 6,
			loop: true
		}, function() {
			console.log("==stop==");
		});

aframe4.play();


  //  var canvasOne = document.getElementById('frameAnimate');
	// 	var frame1 = new frameAnimate(canvasOne, {
	// 		width: 640,
	// 		height: 897,
	// 		fps:30,
	// 		frame: 120,
	// 		loop: false
	// 	}, function() {
	// 		console.log("==stop==");
	// 	});
	 // 
	 // 
  //  var canvasTwo = document.getElementById('frameAnimate2');
	// 	var frame2 = new frameAnimate(canvasTwo, {
	// 		width: 640,
	// 		height: 897,
	// 		fps:24,
	// 		frame: 120,
	// 		loop: false
	// 	}, function() {
	// 		console.log("==stop==");
	// 	});
  //  var canvasThree = document.getElementById('frameAnimate3');
	// 	var frame3 = new frameAnimate(canvasThree, {
	// 		width: 640,
	// 		height: 897,
	// 		fps:12,
	// 		frame: 120,
	// 		loop: false
	// 	}, function() {
	// 		console.log("==stop==");
	// 	});
	 // 
  //  var canvasFour = document.getElementById('frameAnimate4');
	// 	var frame4 = new frameAnimate(canvasFour, {
	// 		width: 640,
	// 		height: 897,
	// 		fps:40,
	// 		frame: 120,
	// 		loop: false
	// 	}, function() {
	// 		console.log("==stop==");
	// 	});
