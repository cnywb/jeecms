

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
	var u = navigator.userAgent, app = navigator.appVersion; //edit by Simon 20151021 判断是否安卓 显示不同提示
    var orient = checkDirect();
    if (orient === "landscape") {
        orientLayer.style.display = "none";
    } else if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
        orientLayer.style.display = "block";
    } else {
    	orientLayer.style.backgroundImage = "url(/r/cms/www/mobile/taurus/images/change_ios.jpg)";
    	orientLayer.style.display = "block";
    }
}
function init() {


    setTimeout(scrollTo, 0, 0, 0);

    orientNotice();
    window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", function () {
        setTimeout(orientNotice, 200);
    //    console.log($(window).width() + " " + $(window).height());
    });


  document.addEventListener('touchmove', preventDefault, false);

    document.body.addEventListener('touchmove', function (ev) {
        var target = ev.target;

        if (isScroller(target)) {
            ev.stopPropagation();
        }
    }, false);


    if (document.body) { setupEmbed(); }
    else { document.addEventListener("DOMContentLoaded", setupEmbed); }

    function setupEmbed() {
        if (window.top != window) {
            document.body.className += " embedded";
        }
    }

    var o = window.examples = {};
    o.showDistractor = function(id) {
        var div = id ? document.getElementById(id) : document.querySelector("div canvas").parentNode;
        div.className += " loading";
    };

    o.hideDistractor = function() {
        var div = document.querySelector(".loading");
        div.className = div.className.replace(/\bloading\b/);
    };

}

function preventDefault(ev) {
    ev.preventDefault();
}

function isScroller(el) {

    return el.classList.contains('scroller');
}



$(function () {
	
    init();
	
	 $(window).resize(function () {
		$('Canvas').width($(window).width());
		$('Canvas').height($(window).height());
	}).trigger('resize');

    canvas.init();

});
