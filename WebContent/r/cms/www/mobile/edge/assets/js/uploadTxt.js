(function($) {
 
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
	function getTrueLength(str){
		var len = str.length, truelen = 0;
		for(var x = 0; x < len; x++){
			if(str.charCodeAt(x) > 128){
				truelen += 2;
			}else{
				truelen += 1;
			}
		}
		return truelen;
	};
	
	function cutString(str, leng){
		var len = str.length, tlen = len, nlen = 0;
		for(var x = 0; x < len; x++){
			if(str.charCodeAt(x) > 128){
				if(nlen + 2 < leng){
					nlen += 2;
				}else{
					tlen = x;
					break;
				}
			}else{
				if(nlen + 1 < leng){
					nlen += 1;
				}else{
					tlen = x;
					break;
				}
			}
		}
		return tlen;
	}
  
function setcanvas(copy){
	  context.font="24px/38px 黑体"; 
    context.fillStyle = "#000000";
    
		for(var i = 1; getTrueLength(copy) > 0; i++){
			var tl = cutString(copy, 24);
			context.fillText(copy.substr(0, tl).replace(/^\s+|\s+$/, ""), 17, i * 38 + 21);
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
		   window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg?1210';
                    window.share.link = 'http://' + window.location.host + '/r/cms/www/mobile/edge/potential_customer_info.html?p=4&imgid='+response+''
                    window.share.title= '锐意任务来袭';
                    window.share.desc = '探寻神秘的黄金线路，走进男神女神预约通道';
                    shareConfig();
			}
				
		})
		
	};
  
})(jQuery);
