$(function(){
	$("#backBtn, .popup").on('touchstart',function(){
		$(".popup").fadeOut();
	})
	$("#searchFAQ").on('touchstart',function(){
        var dheight = $(document).height();
        $('.popup').css('height',dheight);
		$(".popup").fadeIn();
	})
})
