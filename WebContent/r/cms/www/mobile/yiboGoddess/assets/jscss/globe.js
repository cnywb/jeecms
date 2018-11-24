document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false); //禁止拖动
(function($) {
	screenini();
	$(window).bind("resize", screenini);
})(jQuery);

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
	
function screenini() {
		var windowH = $(window).height();
	    var windowW = $(window).width();
	    if (windowH > windowW) {
	        $(".screenAlert").hide();
	       } else {
	        $(".screenAlert").show();
	       }
	}