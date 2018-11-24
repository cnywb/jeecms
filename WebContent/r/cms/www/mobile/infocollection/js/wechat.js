$(document).ready(function () {

    window.share = {
        id: "2",
        imgUrl: 'http://'+window.location.host+'/r/cms/www/mobile/infocollection/images/logo.jpg',
        link: 'http://'+window.location.host+'/infocollection/potentialcustomer/index.jspx?channel=testchannel',
        title: "长安福特荐友团壕礼相送，炙热来袭！",
        desc: "地球上有一群“荐人”，他们毕生的乐趣就是把一切好东西推荐给别人。",
    }

    shareConfig();
});

function shareConfig() {
    var thisurl = window.location.href;
     $.getJSON("http://www.changanfordwechat.com/getinfo?state=4&url="+thisurl, function (data) {
    
          wxshare(data);
        });


    function wxshare(response) {

        wx.config({
            debug: false,
            appId: response.appId,
            timestamp: response.timestamp,
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

            function wxcheck() {
                wx.checkJsApi({
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
                desc: window.share.desc,
                success: function () {
       ga('send', {
	  'hitType': 'event',          // Required.
	  'eventCategory': "mgm",   // Required.
	  'eventAction': 'page',      // Required.
	  'eventLabel': ShareTimelines'',
	  'eventValue': 1
	});
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });


            wx.onMenuShareAppMessage({
                title: window.share.title, // 分享标题
                link: window.share.link, // 分享链接
                imgUrl: window.share.imgUrl,
                desc: window.share.desc,
                trigger: function (res) {
                    //	alert('用户点击分享到朋友圈');
                },
                success: function (res) {
        ga('send', {
	  'hitType': 'event',          // Required.
	  'eventCategory': "mgm",   // Required.
	  'eventAction': 'page',      // Required.
	  'eventLabel': ShareFriend'',
	  'eventValue': 1
	});
                },
                cancel: function (res) {
                    //	alert('已取消');
                },
                fail: function (res) {
                    //	alert(JSON.stringify(res));
                }
            });

            wx.onMenuShareQQ({
                title: window.share.title, // 分享标题
                desc: window.share.desc, // 分享描述
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
                title: window.share.title, // 分享标题
                desc: window.share.desc, // 分享描述
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