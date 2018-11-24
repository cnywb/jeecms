var canvas = (function () {
	var num = 0;
	var maxlen;
	var controlAnim=false;
	var controlEnd=false;
    var startX=null;
	var cardblock = false;
	
	var Request = new Object();  
	Request = GetRequest(); 
	var channelStr= Request["channel"];  
	

     
    return {
        init: function () {
			swipe();
			$('.open a').on('touchstart',function(){
				var id = $(this).attr('data-id') 
				for(var i=1;i<=2;i++){
				    $('.slidewrap>div:nth-child('+i+') .slidebg').attr('src','/r/cms/www/mobile/taurus/images/card/c'+i+'_'+id+'.jpg?20162');
				}
				$('#fdjmusic')[0].play()
				 $('#fdjmusic')[0].volume = 1;
				 $('#apply').removeClass('apply2');
				$('.page').delay(1500).fadeOut()
				$('.getimages').delay(2000).fadeIn(1000)
				//console.log('111',manifest)
			})
		    $('textarea').on('touchstart',function(e){
				//$(this).val('');
				$('textarea').removeClass('lightIn2');
			})
			$('.done').on('touchstart',function(){
				if(cardblock) return false;
			 	cardblock = true;
                $('.ploading').show();
$(".loadTxt").empty().html("正在生成图片并上传服务器请稍候");
				var bg = $(this).parent().find('.slidebg')[0]
				var userimg = $(this).parent().find('.userImg')[0]
				var nikname = $(this).parent().find('.nickname').html()
				var copy = $(this).parent().find('textarea').val()
                var cid = $(this).attr('data-id')             
				setcanvas(bg,userimg,nikname,copy,cid,channelStr)	
				
			})
			
        }
    };
    
    function GetRequest() {  
    var url = location.search; //获取url中"?"符后的字串   
    var theRequest = new Object();  
    if (url.indexOf("?") != -1) {  
        var str = url.substr(1);  
        strs = str.split("&");  
        for (var i = 0; i < strs.length; i++) {  
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
        }  
    }  
    return theRequest;  
}  
	
	function swipe(){
		maxlen = $('.slidewrap>div').length;
		$('.button a').on('touchstart',function(e){
				var btn = $(this).index();
				if(btn == 0){
					if(num==0){
						num=0
					}else if(num==1){$('.button .lbtn').hide(); num-- } 
					else  {
						$('.button .lbtn').show()
						num--
					}
					$('.button .rbtn').show()
				}
				if(btn == 1){
					if(num>=Number(maxlen-1)){
						num=Number(maxlen-1)
					}else if(num>=Number(maxlen-2)){$('.button .rbtn').hide();num++}else{
						$('.button .rbtn').show()
						num++
					}
					$('.button .lbtn').show()
				}
				anim(num)
			})
			
			$('.slidewrap').on('touchstart', function(e) {
				$(window).on('touchmove.prevent', function(e) {
					e.preventDefault();
				});
				
				if (controlAnim == false) {
					controlAnim = true;
					startX = e.originalEvent.touches[0].pageX;
				} else{return}

			});
			
			$('.slidewrap').on('touchend', function(e) {
				$(window).off('touchmove.prevent')
				var endX = e.originalEvent.changedTouches[0].pageX;
				
				if (endX - startX > 10) {
					if(num==2){$('.button .lbtn').hide()}
					if(num==0){
						num=0
					}else if(num==1){$('.button .lbtn').hide(); num-- } 
					else  {
						$('.button .lbtn').show()
						num--
					}
					$('.button .rbtn').show()
				}
				
				if (endX - startX < -10) {
					
					if(num>=Number(maxlen-1)){
						num=Number(maxlen-1)
					}else if(num>=Number(maxlen-2)){$('.button .rbtn').hide();num++}else{
						$('.button .rbtn').show()
						num++
					}
					$('.button .lbtn').show()
				}
				
				controlAnim = false;
				anim(num)
			});

	};
	
	function anim(snum){
		$('.slidewrap').stop().animate({
			left:-1136*snum
		},300)
	};
	
	function setcanvas(bg,userimg,nikname,copy,cid,channelStr){
		var mycanvas=document.getElementById('mycanvas');
		var context=mycanvas.getContext('2d');
			context.font="22px/38px 黑体";     
			if(cid == '1'){
				context.fillStyle = "#ffffff";
			}else{
				context.fillStyle = "#58595b";
			}
			
			context.drawImage(bg,0,0);
			context.drawImage(userimg,1014,19,80,80);
			context.fillText(nikname,980,514);
		for(var i = 1; getTrueLength(copy) > 0; i++){
			var tl = cutString(copy, 30);
			context.fillText(copy.substr(0, tl), 770, i * 40 + 344);
//context.fillText(copy.substr(0, tl).replace(/^\s+|\s+$/, ""), 770, i * 40 + 344);
			copy = copy.substr(tl);
		}
		var image = mycanvas.toDataURL("image/jpeg",0.3).replace("data:image/jpeg;base64,", "");

	      setTimeout(function(){
			ajaxuploadPic(image,channelStr)
		},2000)
	};
	function ajaxuploadPic(image,channelStr){
     console.log("channel:" + channelStr);
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
		   window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/taurus/images/shareimg.png';
                    window.share.link = 'http://' + window.location.host + '/infocollection/potentialcustomer/taurus/potentialCustomerInfo.jspx?channel='+channelStr+'&imgid='+response+''
                    window.share.title= '|敬挚友|定制您的新春祝福';
                    window.share.desc = '新春之际，为您的挚友定制专属明信片，分享您的新年祝福 ';
                    shareConfig();
			}	
		})	
	};
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

})();