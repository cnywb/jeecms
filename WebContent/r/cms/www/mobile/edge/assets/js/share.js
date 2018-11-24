function init(nn) {
	//var nickname = location.search.match(new RegExp("[\?\&]nickName=([^\&]+)", "i"))[1];
	var nickname = urlRequest("nickname");
        var channel = urlRequest("channel");
	if (nn === '1') {
		var imgurl = urlRequest("imgid");
		$(".edge-missionOneTxt1").show();
$("#userphoto").show();
  var canvas = document.getElementById("userphoto");  
  var context = canvas.getContext("2d"); 
  var sourceImg = new Image();
var imgSrc = 'http://www.changanfordclub.com/attached/'+imgurl;
  sourceImg.src = imgSrc;
    var imageUtil = new imgCanvas();
    sourceImg.onload = function() {
      imageUtil.drawScaleImageInCenter(context,sourceImg);
    }
	}
	if (nn === '2') {
		var imgurl = urlRequest("imgid");
		$(".edge-missionTwoTxt1").show();
		$(".edge-recording").show();
		$("#shareVoice").attr('src','http://www.changanfordclub.com/attached'+imgurl);///media/wechat/E527EObBrPpB4-SbFfOc8cri-PMZOgmCGwrjOkA22NKggJgT-BpEudJNAkaAXBND.mp3
	}
	if (nn === '3') {
		var imgurl = urlRequest("imgid");
		$(".missionThree3txt").show();
		$(".imgHold").show();
		$(".imgHold img").attr('src','http://www.changanfordclub.com/attached/image/wechat/'+imgurl);
	}
	if (nn === '4') {
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
	 window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg?1210';
         window.share.link = window.location.href;
         window.share.title= '锐意任务来袭';
         window.share.desc = '探寻神秘的黄金线路，走进男神女神预约通道';
         shareConfig();
});

$(".wantChann").click(function() {
	window.location.href = "http://www.changanfordclub.com/infocollection/potentialcustomer/edge/index.jspx?channel="+ channel;
})

$(".edge-recording").click(function(){
	$('#shareVoice')[0].play();
})