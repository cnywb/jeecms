(function($) {
  'use strict';
  $('#fullpage').fullpage({
        //Navigation
        menu: '#menu',
        lockAnchors: false,
        anchors:['firstPage', 'secondPage', 'thirdPage'],
        navigation: false,
        navigationPosition: 'right',
        navigationTooltips: ['firstSlide', 'secondSlide'],
        showActiveTooltip: false,
        slidesNavigation: true,
        slidesNavPosition: 'bottom',
  			
  			afterRender: function(){
  				$(".edge-firstTxt1").addClass('fadeInDown');
  				$(".edge-firstTxt2").delay(1000).addClass('slideInLeft');
  				$(".edge-firstTxt3").delay(1800).addClass('slideInRight');
  			},
  			afterLoad: function(anchorLink, index){
  				if(index == 1) {
  						$(".edge-firstTxt2").show();
		  				$(".edge-firstTxt2").delay(1000).toggleClass('slideInLeft');
		  				$(".edge-firstTxt3").show();
		  				$(".edge-firstTxt3").delay(1800).toggleClass('slideInRight');
		  				$(".edge-secondTxt3").show();
		  				$(".edge-secondTxt3").delay(1800).toggleClass('slideInRight');
  				}
  				if(index == 2) {
  						$(".edge-thirdTxt2").show();
  						$(".edge-thirdTxt2").toggleClass('fadeIn');
  						$(".edge-tracking").show();
  						$(".edge-tracking").delay(500).toggleClass('fadeIn');
  						$(".edge-thirdTxt3").show();
		  				$(".edge-thirdTxt3").delay(1000).toggleClass('fadeIn');
		  				$(".edge-thirdTxt4").show();
		  				$(".edge-thirdTxt4").delay(1800).toggleClass('fadeIn');
		  				$(".edge-thirdTxt5").show();
		  				$(".edge-thirdTxt5").delay(2600).toggleClass('fadeIn');
  				}
  			},
  			onLeave: function(index, direction){
  				if(index == 1) {
  						console.log("离开第一证");
  						$(".edge-firstTxt1").toggleClass('slideInDown');
		  				$(".edge-firstTxt2").toggleClass('slideInLeft');
		  				$(".edge-firstTxt3").toggleClass('fadeInDown');
  						$(".edge-firstTxt1").fadeOut();
		  				$(".edge-firstTxt2").fadeOut();
		  				$(".edge-firstTxt3").fadeOut();
  				}
  				if(index == 2) {
  						$(".edge-secondTxt1").toggleClass('slideInDown');
		  				$(".edge-secondTxt2").toggleClass('slideInLeft');
		  				$(".edge-secondTxt3").toggleClass('fadeInDown');
  						$(".edge-secondTxt1").fadeOut();
		  				$(".edge-secondTxt2").fadeOut();
		  				$(".edge-secondTxt3").fadeOut();
  				}
  				if(index == 3) {
  						$(".edge-thirdTxt2").toggleClass('fadeIn');
		  				$(".edge-thirdTxt3").toggleClass('fadeIn');
		  				$(".edge-thirdTxt4").toggleClass('fadeIn');
		  				$(".edge-thirdTxt5").toggleClass('fadeIn');
  						$(".edge-thirdTxt2").fadeOut();
		  				$(".edge-thirdTxt3").fadeOut();
		  				$(".edge-thirdTxt4").fadeOut();
		  				$(".edge-thirdTxt5").fadeOut();
  				}
  			},
  });
        
$(".edge-thirdTxt2").click(function() {
		window.location.href = "http://www.changanfordclub.com/r/cms/www/mobile/edge/mission1.html";
})
 
$(".edge-thirdTxt3").click(function() {
		window.location.href = "http://www.changanfordclub.com/r/cms/www/mobile/edge/mission2.html";
})
  
$(".edge-thirdTxt4").click(function() {
		window.location.href = "http://www.changanfordclub.com/r/cms/www/mobile/edge/mission3.html";
})

$(".edge-thirdTxt5").click(function() {
		window.location.href = "http://www.changanfordclub.com/r/cms/www/mobile/edge/mission4.html";
})

  
  var voice = {
    localId: '',
    serverId: ''
  };
  
  // 4 音频接口
  // 4.2 开始录音
  document.querySelector('#startRecord').onclick = function () {
    wx.startRecord({
      cancel: function () {
        alert('用户拒绝授权录音');
      }
    });
  };
  // 4.3 停止录音
  document.querySelector('#stopRecord').onclick = function () {
    wx.stopRecord({
      success: function (res) {
        voice.localId = res.localId;
      },
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });
  };
  // 4.4 监听录音自动停止
  wx.onVoiceRecordEnd({
    complete: function (res) {
      voice.localId = res.localId;
      alert('录音时间已超过一分钟');
    }
  });
  // 4.5 播放音频
  document.querySelector('#playVoice').onclick = function () {
    if (voice.localId == '') {
      alert('请先使用 startRecord 接口录制一段声音');
      return;
    }
    wx.playVoice({
      localId: voice.localId
    });
  };

  // 4.7 停止播放音频
  document.querySelector('#stopVoice').onclick = function () {
    wx.stopVoice({
      localId: voice.localId
    });
  };
  // 4.8 监听录音播放停止
  wx.onVoicePlayEnd({
    complete: function (res) {
      alert('录音（' + res.localId + '）播放结束');
    }
  });
  // 4.8 上传语音
  document.querySelector('#uploadVoice').onclick = function () {
  	alert(voice.localId);
    if (voice.localId == '') {
      alert('请先使用 startRecord 接口录制一段声音');
      return;
    }
    wx.uploadVoice({
      localId: voice.localId,
      success: function (res) {
        alert('上传语音成功，serverId 为' + res.serverId);
        voice.serverId = res.serverId;
        $('.texttt').html(res.serverId);
      }
    });
  };
  // 4.9 下载语音
  document.querySelector('#downloadVoice').onclick = function () {
    if (voice.serverId == '') {
      alert('请先使用 uploadVoice 上传声音');
      return;
    }
    wx.downloadVoice({
      serverId: voice.serverId,
      success: function (res) {
        alert('下载语音成功，localId 为' + res.localId);
        voice.localId = res.localId;
      }
    });
  };

	document.querySelector('#uploadPic').onclick = function () {
   	 wx.chooseImage({
    success: function (res) {
        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
        alert("aa");
    }
});
    };

  
})(jQuery);
