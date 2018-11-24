(function($) {
  var photo = {
    localId: '',
    serverId: ''
  };
  var uploadblock = false;
  var channel = urlRequest("channel");
  
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
//alert(photo.localId)
        if(uploadblock) return false;
        uploadblock = true;
         $('.ploading').show();
         $(".loadTxt").empty().html("正在生成图片并上传服务器,请稍候...");
            
      wx.uploadImage({
        localId: photo.localId[0], // 需要上传的图片的本地ID，由chooseImage接口获得
        isShowProgressTips: 1,// 默认为1，显示进度提示
        success: function (res) {
            photo.serverId = res.serverId; // 返回图片的服务器端ID
//alert("serverId:"+ res.serverId);
            uploadImage(photo.serverId);
        }
    });
    })

  function uploadImage(image){
   $.ajax({
            url: 'http://www.changanfordclub.com/infocollection/potentialcustomer/edge/getImageUrl.jspx?mediaId=' + image,
            type: "GET",
            dataType: "text",
            async: false,
            data: {},
            success: function (response) {
                   $('.share').fadeIn(500);
                 $('.ploading').fadeOut()
                   uploadblock = false;
                    window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/edge/assets/i/shareImg.jpg?1210';
                    window.share.link = 'http://' + window.location.host + '/r/cms/www/mobile/edge/potential_customer_info.html?p=1&channel=' + channel + '&imgid='+response+''
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