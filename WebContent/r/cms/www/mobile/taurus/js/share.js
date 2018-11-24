"use strict";


var orientLayer = document.getElementById("orientLayer");

function checkDirect() {
    if (document.documentElement.clientHeight >= document.documentElement.clientWidth) {
        return "portrait";
    } else {
        return "landscape";
    }
}

function orientNotice() {
    var orient = checkDirect();
    if (orient == "portrait") {
        orientLayer.style.display = "block";
    } else {
        orientLayer.style.display = "none";
    }
}
function init() {

    orientNotice();
    window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", function () {
        setTimeout(orientNotice, 200);
    });
	var imgurl = window.location.search.split('&')[1].split('imgid=')[1]
	$('.sharecontent .sharecard ').attr('src','http://www.changanfordclub.com/attached/image/wechat/'+imgurl)
}

$(function () {
	$('#orientLayer img').css('height',$(window).height())
	init();
	 window.share.imgUrl = 'http://www.changanfordclub.com/r/cms/www/mobile/taurus/images/shareimg.png';
         window.share.link = window.location.href;
         window.share.title= '|敬挚友|定制您的新春祝福';
         window.share.desc = '新春之际，为您的挚友定制专属明信片，分享您的新年祝福';
         shareConfig();
});