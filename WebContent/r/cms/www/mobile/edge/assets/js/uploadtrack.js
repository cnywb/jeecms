(function($) {
 
  var photo = {
    localId: '',
    serverId: ''
  };
  var uploadblock = false;
  
  var canvas = document.getElementById("mapofChina");  
  var context = canvas.getContext("2d"); 
  
  var maoCanvas = document.getElementById("mao");
  var maoCentext = maoCanvas.getContext("2d");
  
  var bgimg = new Image();
  bgimg.src = "/r/cms/www/mobile/edge/assets/i/Chinamap.png?1207";
  var maoImg = new Image();
  maoImg.src = "/r/cms/www/mobile/edge/assets/i/mao.png?1207";

  
  bgimg.onload = function() {
  		context.drawImage(bgimg,0,0)
  }
  maoImg.onload = function() {
  	maoCentext.drawImage(maoImg,270,250)
  }
  
  $("#mao").on("touchstart",function(e){
  	var _touch = e.originalEvent.targetTouches[0]
  	var X = _touch.clientX;
  	var Y = _touch.clientY;
  	X = Math.round(X);
  	Y = Math.round(Y);
  	var xx =  X - $("#mao").offset().left;
  	var yy =  Y - $("#mao").offset().top;
  	console.log(xx);
  	console.log(yy);
  	//maoCentext.clearRect(0,0,304,297);
  	maoCentext.drawImage(maoImg,xx,yy)
  })
  
  
  $(".btnTrack").click(function(){
		        $(".missionThree3txt").fadeOut();
		        $(".edge-map").fadeOut();
		        $(".btnTrack").fadeOut();
		        $(".chinaMap").show();
		        $(".edge-missionNotice").fadeIn();
		        $(".edge-shareBtn").fadeIn();
		        $(".edge-yyBtn").fadeIn();
  })
  	$(".share").click(function(){
  		$(".share").fadeOut();
  	})
  //发送到服务器
  	$('.edge-shareBtn').click(function(){
				if(uploadblock) return false;
			 	uploadblock = true;
         $('.ploading').show();
         $(".loadTxt").empty().html("正在生成图片并上传服务器,请稍候...");
           
				setcanvas();
		})
  
function setcanvas(){
		context.drawImage(maoCanvas,0,0)
		var imagebit = canvas.toDataURL("image/png",0.5).replace("data:image/png;base64,", "");
	      setTimeout(function(){
				ajaxuploadPic(imagebit)
		},1000)
	};
	function ajaxuploadPic(image){
     
	 $.ajax({
            url: 'http://www.changanfordclub.com/infocollection/potentialcustomer/edge/saveBase64Image.jspx',
            type: "POST",
            dataType: "text",
            async: false,
            data: {base64data:image},
            success: function (response) {
                   $('.share').fadeIn(500);
                 $('.ploading').fadeOut()
                   uploadblock = false;
		   window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg?1210';
                    window.share.link = 'http://' + window.location.host + '/r/cms/www/mobile/edge/potential_customer_info.html?p=3&imgid='+response+''
                    window.share.title= '锐意任务来袭';
                    window.share.desc = '探寻神秘的黄金线路，走进男神女神预约通道';
                    shareConfig();
			}
				
		})
		
	};
  
})(jQuery);
