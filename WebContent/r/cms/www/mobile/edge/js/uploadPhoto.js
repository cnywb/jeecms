(function($) {
  'use strict';
  var photo = {
    localId: '',
    serverId: ''
  };
  var uploadblock = false;
  
  var canvas = document.getElementById("userphoto");  
  var context = canvas.getContext("2d"); 
  
  function drawphoto(img) {
  	var sourceImg = new Image();
  	sourceImg.src = img;
  	var imageUtil = new imgCanvas();
    sourceImg.onload = function() {
    	imageUtil.drawScaleImageInCenter(context,sourceImg);
    }
  }
  $(".edge-missionOneUploadBtn").click(function(){
  		wx.chooseImage({
		    count: 1, // 默认9
		    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		    success: function (res) {
		        photo.localId = res.localIds; 
		        //alert(photo.localId)// 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		        $(".edge-missionOneTxt1").fadeOut();
		        $(".edge-takePic").fadeOut();
		        $(".edge-missionOneUploadBtn").fadeOut();
		        $("#userphoto").show();
		        $(".edge-missionNotice").fadeIn();
		        $(".edge-shareBtn").fadeIn();
		        $(".edge-yyBtn").fadeIn();
		        //$(".testt").attr('src',photo.localId);
		        var photoStr = photo.localId.toString();
		        drawphoto(photoStr);
		    }
		});
  })
  	$(".share").click(function(){
  		$(".share").fadeOut();
  	})
  //发送到服务器
  	$('.edge-shareBtn').click(function(){
				if(uploadblock) return false;
			 	uploadblock = true;
         $('.ploading').show();
         alert("sss");
         $(".loadTxt").empty().html("正在生成图片并上传服务器,请稍候...");
				//var bg = $(this).parent().find('.slidebg')[0]
				//var userimg = $(this).parent().find('.userImg')[0]
			//	var nikname = $(this).parent().find('.nickname').html()
			//	var copy = $(this).parent().find('textarea').val()
       //var cid = $(this).attr('data-id')             
				setcanvas();
		})
  
function setcanvas(){
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
                    window.share.link = 'http://' + window.location.host + '/infocollection/potentialcustomer/edge/potentialCustomerInfo.jspx?p=1&imgid='+response+''
                    window.share.title= '挑战我的不可能标题';
                    window.share.desc = '合影文字内容待提供';
                    shareConfig();
			}
				
		})
		
	};
  
})(jQuery);
