"use strict";


$(function () {
	var pp = urlRequest("p");
	init(pp);
	 window.share.imgUrl = '/r/cms/www/mobile/edgeimagine/images/share.jpg';
         window.share.link = window.location.href;
         window.share.title= '乐高挑战：创造想象锐世界';
         window.share.desc = '挑战乐高达人，拼搭锐界，一起探索美好锐世界！';
         shareConfig();
});


