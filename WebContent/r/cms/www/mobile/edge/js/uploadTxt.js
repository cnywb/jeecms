(function($) {
  'use strict';
  var uploadblock = false;
  
  var canvas = document.getElementById("txtCanvas");  
  var context = canvas.getContext("2d");
  var paperImg = new Image();
  paperImg.src = "/r/cms/www/mobile/edge/assets/i/inputBg.png";
  paperImg.onload = function() {
  	context.drawImage(paperImg,0,0);
  }

 	 $('.textareaInput').on('touchstart',function(e){
				$(this).val('');
			}) 
  $(".inputXY").click(function(){
		        $(".missionThree3txt").fadeOut();
		        $(".edge-map").fadeOut();
		        $(".btnTrack").fadeOut();
		        $("#txtCanvas").show();
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
				//var bg = $(this).parent().find('.slidebg')[0]
				//var userimg = $(this).parent().find('.userImg')[0]
			//	var nikname = $(this).parent().find('.nickname').html()
				var copy = $(this).parent().find('.textareaInput').val()
       //var cid = $(this).attr('data-id')             
				setcanvas(copy);
		})
  
function setcanvas(copy){
	  context.font="24px/38px 黑体"; 
    context.fillStyle = "#000000";
    
		for(var i = 1; getTrueLength(copy) > 0; i++){
			var tl = cutString(copy, 12);
			context.fillText(copy.substr(0, tl).replace(/^\s+|\s+$/, ""), 17, i * 38 + 39);
			copy = copy.substr(tl);
		}

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
                   cardblock = false;
		   							window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg';
                    window.share.link = 'http://' + window.location.host + '/r/cms/www/mobile/edge/potentialCustomerInfo.html?p=4&imgid='+response+''
                    window.share.title= '挑战我的不可能标题';
                    window.share.desc = '合影文字内容待提供';
                    shareConfig();
			}
				
		})
		
	};
  
})(jQuery);
