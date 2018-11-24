(function($) {
  var voice = {
    localId: '',
    serverId: ''
  };
  var uploadblock = false;
  var channel = urlRequest("channel");
  
  $(".edge-missionTwoRecordBtn").click(function(){
		 $(".edge-missionTwoTxt1").fadeOut();
		 $(".edge-record").fadeOut();
		 $(".edge-missionTwoRecordBtn").fadeOut();
		 $(".edge-recording").fadeIn();
		 $(".edge-missionNotice").fadeIn();
		 $(".edge-shareBtn").fadeIn();
		 $(".edge-yyBtn").fadeIn(500,function(){
			 		$(".edge-recordNotice").show();
			 		$(".edge-recording").addClass("edge-recordingAni");
			 		wx.startRecord();
		 });
  });

		$(".edge-recording").click(function(){
				    wx.stopRecord({
				      success: function (res) {
				        voice.localId = res.localId;
				        $(".edge-recordNotice").html("");
				        $(".edge-recordNotice").html("录音完毕，请试听或者分享到朋友圈");
				        $(".edge-recording").removeClass("edge-recordingAni");
				        bindPlayEvent();
				      },
				      fail: function (res) {
				        alert(JSON.stringify(res));
				      }
				    });
		})
	
	function bindPlayEvent() {
		$(".edge-recording").click(function(){
				if (voice.localId == '') {
				      alert('请先使用 startRecord 接口录制一段声音');
				      return;
				    }
				    wx.playVoice({
				      localId: voice.localId
				    });
		})
	}
  	$(".share").click(function(){
  		$(".share").fadeOut();
  	})
	//发送到服务器
  	$('.edge-shareBtn').click(function(){
				if(uploadblock) return false;
			 	uploadblock = true;
         $('.ploading').show();
         $(".loadTxt").empty().html("正在上传服务器,请稍候...");          
					wx.uploadVoice({
	    		localId: voice.localId, // 需要上传的音频的本地ID，由stopRecord接口获得
	    		isShowProgressTips: 1,// 默认为1，显示进度提示
	        success: function (res) {
	        var serverId = res.serverId; // 返回音频的服务器端ID
	        uploadVoice(serverId)
    }
});
		})
  
	function uploadVoice(serverId) {
			 $.ajax({
            url: ' http://www.changanfordclub.com/infocollection/potentialcustomer/edge/getMp3AudioUrl.jspx?mediaId=' + serverId,
            type: "GET",
            dataType: "text",
            async: false,
            data: {},
            success: function (response) {
                   $('.share').fadeIn(500);
                 	 $('.ploading').fadeOut()
                   uploadblock = false;
		   			window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg?1210';
                    window.share.link = 'http://' + window.location.host + '/r/cms/www/mobile/edge/potential_customer_info.html?p=2&channel=' + channel + '&imgid='+response+''
                    window.share.title= '锐意任务来袭';
                    window.share.desc = '探寻神秘的黄金线路，走进男神女神预约通道';
                    shareConfig();
			}
		})
}
	function ajaxuploadVoice(voiceid,channel){
     
	 $.ajax({
            url: ' http://www.changanfordclub.com/infocollection/potentialcustomer/edge/getMp3AudioUrl.jspx?mediaId=' + voiceid,
            type: "GET",
            dataType: "text",
            async: false,
            data: {},
            success: function (response) {
                   $('.share').fadeIn(500);
                 	 $('.ploading').fadeOut()
                   uploadblock = false;
		   window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg';
                    window.share.link = 'http://' + window.location.host + '/r/cms/www/mobile/edge/potential_customer_info.html?p=2&channel=' + channel + '&imgid='+response+''
                    window.share.title= '锐意任务来袭';
                    window.share.desc = '探寻神秘的黄金线路，走进男神女神预约通道';
                    shareConfig();
			}
		})
	};
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
	
	
})(jQuery);



	