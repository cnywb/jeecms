(function ($) {
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();
	var currPage = "0";
	
	window.detecOrient = true;
	$(".p1btn2").on("touchstart",function(){
	mySwiper.slideNext();
	})

	loadimg();

	
	
	
var mySwiper = new Swiper ('.swiper-container', {
	    direction: 'horizontal',
	    noSwiping:false,
	    width : 640,
	    height : 1044,
	    loop: false,
	    touchRatio : 0.1,
	    allowSwipeToNext : true,
	    resistanceRatio : 0.3,
	    })




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
