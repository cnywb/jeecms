$(document).ready(function () {
//	var Request = new Object();  
//	Request = GetRequest(); 
//	var channelStr= Request["channel"];  
//	var channelwechat = 'http://www.changanfordwechat.com/fordwechat/control?state=' + channelStr;
//	console.log(channelwechat);
	
	
//	function GetRequest() {  
//  var url = location.search; //获取url中"?"符后的字串   
//  var theRequest = new Object();  
//  if (url.indexOf("?") != -1) {  
//      var str = url.substr(1);  
//      strs = str.split("&");  
//      for (var i = 0; i < strs.length; i++) {  
//          theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
//      }  
//  }  
//  return theRequest;  
//}  

	window.share = {
		imgUrl : 'http://www.changanfordclub.com/r/cms/www/mobile/yiboBoss/images/shareimg.jpg',
		link : 'http://www.changanfordclub.com/infocollection/potentialcustomer/yiboBoss/index.jspx?channel=1',
		title : "小搏支招，TA靠【学会独立，努力进取】征服老板，完美！",
		desc : "想知道你搞定老板的独门秘籍？戳这里，戳这里，戳这里！",
		}
		shareConfig();
	});
	
	function shareConfig(){
		var thisurl = window.location.href;
		//alert(encodeURI(thisurl));
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
				'chooseImage',
				'previewImage',
				'uploadImage',
				'downloadImage',
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
							'onMenuShareWeibo'
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