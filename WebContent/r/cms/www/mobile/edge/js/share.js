"use strict";

function init(nn) {
	//var nickname = location.search.match(new RegExp("[\?\&]nickName=([^\&]+)", "i"))[1];
	var nickname = urlRequest("nickname");
        var channel = urlRequest("channel");
	if (nn === 1) {
		var imgurl = urlRequest("imgid");
		$(".edge-missionOneTxt1").show();
		$(".imgHold").show();
		$(".imgHold img").attr('src','http://www.changanfordclub.com'+imgurl);
	}
	if (nn === 2) {
		var imgurl = urlRequest("imgid");
		$(".edge-missionTwoTxt1").show();
		$("edge-recording").show();
		$("#shareVoice").attr('src',,'http://www.changanfordclub.com/attached'+imgurl);///media/wechat/E527EObBrPpB4-SbFfOc8cri-PMZOgmCGwrjOkA22NKggJgT-BpEudJNAkaAXBND.mp3
	}
	if (nn === 3) {
		var imgurl = urlRequest("imgid");
		$(".missionThree3txt").show();
		$(".imgHold").show();
		$(".imgHold img").attr('src','http://www.changanfordclub.com/attached/image/wechat/'+imgurl);
	}
	if (nn === 4) {
		var imgurl = urlRequest("imgid");
		$(".mission4txt").show();
		$(".imgHold").show();
		$(".imgHold img").attr('src','http://www.changanfordclub.com/attached/image/wechat/'+imgurl);
	}
	
}

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

$(function () {
	var pp = urlRequest("p");
	init(pp);
	 window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg';
         window.share.link = window.location.href;
         window.share.title= '锐界Title';
         window.share.desc = '锐界Desc！';
         shareConfig();
});

$(".wantChann").click(function() {
	window.location.href = "http://www.changanfordclub.com/infocollection/potentialcustomer/edge/index.jsp";
})

$(".edge-recording").click(function(){
	$('#shareVoice')[0].play();
})
