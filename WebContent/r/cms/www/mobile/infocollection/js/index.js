(function ($) {
    Mgm = function() {
		this.init();
    };
    $.extend(Mgm.prototype, {
		
        init: function(data) {
     doPageview('page1_H5')
			this.loadWrap()
			this.initSection1();
            this.initSection2();
            this.initSection3();
            this.initSection4();
			this.initSection5();
                     
			var channel = window.location.search.split('?channel=')[1].split('&')[0]
			$('.section5 a').click(function(){

				window.location.href='http://'+window.location.host+'/infocollection/potentialcustomer/potentialCustomerInfo.jspx?channel='+channel+''
			})
        },
		

        loadWrap: function() {
            var self = this;
		
			 $('.wrap').fullpage({
                scrollingSpeed: 1000,
                easing: 'swing',
                normalScrollElements: '',
				afterRender: function() {
                    setTimeout(function() {
                        self.appearSection1();
                    }, 500);
                },
                afterLoad: function(anchorLink, index) {
                    switch(index) {
                        case 1: self.appearSection1(); break;
                        case 2: self.appearSection2(); break;
                        case 3: self.appearSection3(); break;
						case 4: self.appearSection4(); break;
						case 5: self.appearSection5(); break;
                    }
                },
                onLeave: function(index, nextIndex) {
                    setTimeout(function() {
                        switch(index) {
                            case 1: self.disappearSection1(); break;
                            case 2: self.disappearSection2(); break;
                            case 3: self.disappearSection3(); break;
							case 4: self.disappearSection4(); break;
							case 5: self.disappearSection5(); break;
					
                        }
                    }, 1000);
                }
            });
			
         
		
		
        },
		
  
	
	
        //---------------------------------section 1----------------------------------//
        initSection1: function() {
            this.resetSection1();
        },
        resetSection1: function() {
        },
        appearSection1: function() {
			
			$('.section1 img:nth-child(6)').delay(500).fadeIn().addClass('part1p')
			$('.section1 img:nth-child(7)').delay(1000).fadeIn().addClass('word')
			$('.section1 img:nth-child(5)').delay(1800).fadeIn(400)
			$('.section1 img:nth-child(4)').delay(2000).fadeIn(400)
			$('.section1 img:nth-child(3)').delay(2400).fadeIn(400)
			$('.section1 img:nth-child(2)').delay(2400).fadeIn(400)
			$('.section1 img:nth-child(1)').delay(2400).fadeIn(400)
		},
				
        disappearSection1: function() {
            this.resetSection1();
        },
		
		
    	//---------------------------------section 2----------------------------------//
        initSection2: function() {
            this.resetSection2();
			
        },
        resetSection2: function() {
			
        },
        appearSection2: function() {
			$('.section2 .p2content img:nth-child(3)').delay(500).fadeIn().addClass('word')
			$('.section2 .people').delay(1000).fadeIn().addClass('part1p')
			$('.section2 .people img:nth-child(2)').delay(1200).fadeIn().addClass('Lhandrotate')
			$('.section2 .people img:nth-child(3)').delay(1200).fadeIn().addClass('Rhandrotate')
			$('.section2 .people img:nth-child(4)').delay(1200).fadeIn().addClass('light')
			$('.section2 .p2content img:nth-child(2)').delay(1500).fadeIn(400)
			$('.section2 .p2content img:nth-child(1)').delay(1700).fadeIn(400)
			$('.section2 .cycle').delay(2000).fadeIn(400)
        },
        disappearSection2: function() {
           
            this.resetSection2();
        },


        //---------------------------------section 3----------------------------------//
        initSection3: function() {
            this.resetSection3();
        },
        resetSection3: function() {
        },
        appearSection3: function() {
           	$('.section3 .man').delay(500).fadeIn().addClass('part1p')
			$('.section3 .man img:nth-child(2)').delay(500).fadeIn().addClass('light')
			$('.section3 img:nth-child(6)').delay(1000).fadeIn().addClass('word')
			$('.section3 img:nth-child(5)').delay(1500).fadeIn(400)
			$('.section3 img:nth-child(4)').delay(1700).fadeIn(400)
			$('.section3 img:nth-child(3)').delay(1900).fadeIn(400)
			$('.section3 img:nth-child(2)').delay(1900).fadeIn(400)
			$('.section3 img:nth-child(1)').delay(1900).fadeIn(400)
        },
        disappearSection3: function() {
         
            this.resetSection3();
        },
		
		 //---------------------------------section 4----------------------------------//
        initSection4: function() {
            this.resetSection4();
        },
        resetSection4: function() {
        },
        appearSection4: function() {
			$('.section4 img:nth-child(5)').delay(500).fadeIn().addClass('word')
			$('.section4 img:nth-child(6)').delay(1000).fadeIn().addClass('part1p p4car')
			$('.section4 img:nth-child(4)').delay(1500).fadeIn(400)
			$('.section4 img:nth-child(3)').delay(1700).fadeIn(400)
			$('.section4 img:nth-child(2)').delay(1900).fadeIn(400)
			$('.section4 img:nth-child(1)').delay(1900).fadeIn(400)
        },
        disappearSection4: function() {
         
            this.resetSection4();
        },
		
		 //---------------------------------section 5----------------------------------//
        initSection5: function() {
            this.resetSection5();
        },
        resetSection5: function() {
        },
        appearSection5: function() {
			$('.section5 .p5bottom img:nth-child(3)').delay(500).fadeIn().addClass('part1p')
			$('.section5 .p5bottom img:nth-child(4)').delay(1000).fadeIn().addClass('word')
			$('.section5 .p5bottom img:nth-child(2)').delay(1500).fadeIn(400)
			$('.section5 .p5bottom img:nth-child(1)').delay(1700).fadeIn(400)
			$('.section5 .p5top').delay(1900).fadeIn(400)
			$('.section5 .p5top img:nth-child(2)').delay(2000).fadeIn().addClass('p5car')
			$('.section5 a').delay(2100).fadeIn(400)
        },
        disappearSection5: function() {
         
            this.resetSection5();
        },
		
	
    });
})(jQuery);



$(function() {

  var mgm = new Mgm();

});


function isChinese(str) {
    var str = str.replace(/(^\s*)|(\s*$)/g, '');
    if(!(/^[\u4E00-\uFA29]*$/.test(str) && (!/^[\uE7C7-\uE7F3]*$/.test(str)))) {
        return false;
    }
    return true;
}
function isMobile(str) {
    if(/^1[358]\d{9}/.test(str)) {
        return true;
    }
    return false;
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return decodeURI(r[2]); return null;
}

function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
		$('.shareTips').show();
		$('.shareTips .sharewx').show();
		doPageview('Levis_501CT_weixin_Share')
    }else{
		$('.shareTips').show();
		$('.sharebtn_wb').show();
		$('.shareTips .sharewb').show()
		doPageview('Levis_501CT_weibo_Share')
    }
}

function doTrack(name) {
	console.log('track',name)
	ga('send', {
	  'hitType': 'event',          // Required.
	  'eventCategory': "",   // Required.
	  'eventAction': 'click',      // Required.
	  'eventLabel': name,
	  'eventValue': 1
	});
};
function doPageview(name) {
	console.log('doPageview',name)
	ga('send', {
	  'hitType': 'event',          // Required.
	  'eventCategory': "mgm",   // Required.
	  'eventAction': 'page',      // Required.
	  'eventLabel': name,
	  'eventValue': 1
	});
};
