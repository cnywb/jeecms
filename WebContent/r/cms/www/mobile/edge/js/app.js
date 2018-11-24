(function($) {
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
              $(".edge-firstTxt1").show();
              $(".edge-firstTxt1").toggleClass('fadeInDown');
              $(".edge-firstTxt2").show();
              $(".edge-firstTxt2").delay(1000).toggleClass('slideInLeft');
              $(".edge-firstTxt3").show();
              $(".edge-firstTxt3").delay(1800).toggleClass('slideInRight');
          }
          if(index == 2) {
              $(".edge-secondTxt1").show();
              $(".edge-secondTxt1").toggleClass('fadeInDown');
              $(".edge-secondTxt2").show();
              $(".edge-secondTxt2").delay(1000).toggleClass('slideInLeft');
              $(".edge-secondTxt3").show();
              $(".edge-secondTxt3").delay(1800).toggleClass('slideInRight');
          }
          if(index == 3) {
              $(".edge-thirdTxt2").show();
              $(".edge-thirdTxt2").toggleClass('fadeIn');
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

var nickname = urlRequest("nickname");
var imgurl = urlRequest("imgid");
var channel = urlRequest("channel");
        
$(".edge-thirdTxt2").click(function() {
    window.location.href = "/r/cms/www/mobile/edge/mission1.html?nickname="+ nickname + "&imgid=" + imgurl + "&channel=" + channel + "";
})
 
$(".edge-thirdTxt3").click(function() {
    window.location.href = "/r/cms/www/mobile/edge/mission2.html?nickname="+ nickname + "&imgid=" + imgurl + "&channel=" + channel + "";
})
  
$(".edge-thirdTxt4").click(function() {
    window.location.href = "/r/cms/www/mobile/edge/mission3.html?nickname="+ nickname + "&imgid=" + imgurl + "&channel=" + channel + "";
})

$(".edge-thirdTxt5").click(function() {
    window.location.href = "/r/cms/www/mobile/edge/mission4.html?nickname="+ nickname + "&imgid=" + imgurl + "&channel=" + channel + "";
})


  
})(jQuery);
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