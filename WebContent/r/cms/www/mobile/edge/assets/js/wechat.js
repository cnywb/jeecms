$(document).ready(function () {
	var projectURL = 'http://www.changanfordclub.com';
	var channelStr= urlRequest["channel"];  
	var channelwechat = projectURL + '/infocollection/potentialcustomer/edge/index.jspx?channel=' + channelStr;
	//console.log(channelwechat);
	
	
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

	window.share = {
		imgUrl : 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg?1210',
		link : channelwechat,
		title : "锐意任务来袭",
		desc : "探寻神秘的黄金线路，走进男神女神预约通道",
		}
		shareConfig();
	});
	
	function shareConfig(){
		var thisurl = window.location.href;
		//alert(encodeURI(thisurl));
		//$.getJSON("http://changford.agenda-sh.com/api/auth/wxShare?url="+ thisurl, function (data)
		$.getJSON("http://www.changanfordwechat.com/getinfo?state=4&url="+ thisurl, function (data) {
        
             wxshare(data)
			//alert(data)
                 });
		
		function wxshare(response) {
			wx.config({
			  debug: false,
			  appId: response.appId,
			  timestamp:response.timestamp,
			  nonceStr: response.nonceStr,
			  signature: response.signature,
			  jsApiList: [
				'checkJsApi',    
				'onMenuShareTimeline',    
				'onMenuShareAppMessage',    
				'onMenuShareQQ',    
				'onMenuShareWeibo',      
				'hideAllNonBaseMenuItem',    
				'showAllNonBaseMenuItem',    
				'startRecord',
				'stopRecord',    
				'onRecordEnd',    
				'playVoice',    
				'pauseVoice',    
				'stopVoice',    
				'uploadVoice',    
				'downloadVoice',    
				'chooseImage',    
				'previewImage',    
				'uploadImage',    
				'downloadImage',    
				'getNetworkType',    
				'openLocation',    
				'getLocation',    
				'hideOptionMenu',    
				'showOptionMenu',    
				'closeWindow',    
				'scanQRCode',    
			  ]
		  })
		  
		   wx.ready(function () {
			wxcheck();
			
			function wxcheck(){
					wx.checkJsApi({
						  jsApiList: [
							'checkJsApi',    
							'onMenuShareTimeline',    
							'onMenuShareAppMessage',    
							'onMenuShareQQ',    
							'onMenuShareWeibo',      
							'hideAllNonBaseMenuItem',    
							'showAllNonBaseMenuItem',    
							'startRecord',    
							'stopRecord',    
							'onRecordEnd',    
							'playVoice',    
							'pauseVoice',    
							'stopVoice',    
							'uploadVoice',    
							'downloadVoice',    
							'chooseImage',    
							'previewImage',    
							'uploadImage',    
							'downloadImage',    
							'getNetworkType',    
							'openLocation',    
							'getLocation',    
							'hideOptionMenu',    
							'showOptionMenu',    
							'closeWindow',    
							'scanQRCode',    
						  ],
						  success: function (res) {
							//alert(JSON.stringify(res));
							
						  }
						});
				}
				wx.onMenuShareTimeline({
					title: window.share.desc, // 分享标题
					link: window.share.link, // 分享链接
					imgUrl: window.share.imgUrl,
					desc:  window.share.desc,
					success: function () { 
						

					},
					cancel: function () { 
						// 用户取消分享后执行的回调函数
					}
				});	
				
				
				wx.onMenuShareAppMessage({
					title: window.share.title, // 分享标题
					link: window.share.link, // 分享链接
					imgUrl: window.share.imgUrl,
					desc:  window.share.desc,
					trigger: function (res) {
					//	alert('用户点击分享到朋友圈');
					},
					success: function (res) {
					

					},
					cancel: function (res) {
					//	alert('已取消');
					},
					fail: function (res) {
					//	alert(JSON.stringify(res));
					}
				});	
				
				wx.onMenuShareQQ({
					title:  window.share.title, // 分享标题
					desc:  window.share.desc, // 分享描述
					link: window.share.link, // 分享链接
					imgUrl: window.share.imgUrl, // 分享图标
					success: function () { 
					   // 用户确认分享后执行的回调函数
					},
					cancel: function () { 
					   // 用户取消分享后执行的回调函数
					}
				});
				
				wx.onMenuShareWeibo({
					title:  window.share.title, // 分享标题
					desc:  window.share.desc, // 分享描述
					link: window.share.link, // 分享链接
					imgUrl: window.share.imgUrl, // 分享图标
					success: function () { 
					// 用户确认分享后执行的回调函数
					},
					cancel: function () { 
					// 用户取消分享后执行的回调函数
					}
				});
				
				
			})
			
			
		}
	
	}