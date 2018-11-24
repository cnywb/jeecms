$(function(){
	
	window.urlSet = '/r/cms/www/mobile/yiboGoddess/assets/i/'
	$(".loading1").addClass("loading1_in");
	
	setTimeout(function(){
		loadimages([window.urlSet+'home_bg.jpg',window.urlSet+'home_car.png',window.urlSet+'home_girl.png',window.urlSet+'home_t1.png',window.urlSet+'home_t2.png',
		window.urlSet+'home_t3.png',window.urlSet+'home_t4.png',window.urlSet+'home_title.png',
		window.urlSet+'question_bg.jpg',window.urlSet+'question_txt1.png',window.urlSet+'question_txt2.png',window.urlSet+'question_txt3.png',window.urlSet+'share_bg.jpg',window.urlSet+'music.mp3']);
	},1000);
	
	
	function loadimages(arr){
			var self=this;
			var newimages=[], loadedimages=0
			var arr=(typeof arr!="object")? [arr] : arr
			function imageloadpost(){
				loadedimages++;
		
				if (loadedimages==arr.length){
					$('.music').fadeIn(500);
					setTimeout(function(){
						$(".loading1").addClass("loading1_out");
					},500)
					setTimeout(function(){
						landing();
						console.log("加载完毕");
					},1000)
				}
				var loadcent = loadedimages/arr.length*100;
				$(".loading-txt").empty().html("LOADING " + Math.round(loadcent) + "%")
			}
			
			for (var i=0; i<arr.length; i++){
				newimages[i]=new Image()
				newimages[i].src= arr[i]
				newimages[i].onload=function(){
					imageloadpost()
				}
				newimages[i].onerror=function(){
					imageloadpost()
				}
			}
	}
	function landing() {
		$('#bgmusic')[0].play();
		$(".loading_wrap").fadeOut();
		$(".home_wrap").fadeIn(500,function(){
			$(".home_car").show().addClass("animated zoomIn");
			setTimeout(function(){
				$(".home_Leg").show().addClass("animated fadeInDown");
			},1200);
			setTimeout(function(){
				$(".home_copy5").show().addClass("animated fadeIn");
			},2000)
			setTimeout(function(){
				$(".home_copy1").show().addClass("animated fadeInLeft");
			},2500)
			setTimeout(function(){
				$(".home_copy2").show().addClass("animated fadeInRight");
			},3000)
			setTimeout(function(){
				$(".home_copy3").show().addClass("animated fadeInLeft");
			},3500)
			setTimeout(function(){
				$(".home_copy4").show().addClass("animated fadeInRight");
				$(".home_enter").show().addClass("animated fadeIn infinite").on("click",function(){
					question();
				});
			},4000)
			
		});
		$('.music').click(function() {
				var audio = document.getElementById('bgmusic');
				if(audio!==null){             
					//检测播放是否已暂停.audio.paused 在播放器播放时返回false.<span style="font-family: Arial, Helvetica, sans-serif;">在播放器暂停时返回true</span>  

					if(!audio.paused)  
					{                 
						audio.pause();// 这个就是暂停//audio.play();// 这个就是播放  
						$('.music img').attr('src','/r/cms/www/mobile/yiboGoddess/assets/i/audioOff.png')
					}else{
						audio.play();
						$('.music img').attr('src','/r/cms/www/mobile/yiboGoddess/assets/i/audio.png')
					}  
				}  
		});
	}
	function question() {
		$('.home_wrap').fadeOut();
		$(".resault_btnBg").fadeIn(500,function(){
			
			$('.question_wrap').fadeIn();
			$(".question_txt1").show();
			$(".question_txt1").addClass("animated fadeInDownBig");
			setTimeout(function(){
				$(".question_txt2").show();
				$(".question_txt2").addClass("animated fadeInDownBig");
			},1000)
			setTimeout(function(){
				$(".question_txt3").show();
				$(".question_txt3").addClass("animated fadeInDownBig");
				$(".dot1,.dot2,.dot3,.dot4,.dot5").show().addClass("animated zoomIn");
				$(".question_man").show().addClass("animated fadeInUpBig");
				//$(".btn_man").animate({"bottom":"108px","left":"525px"});
				$(".dot1").on('touchstart', function(){
					$(".question_man").animate({"top":"180px","left":"100px"},300).animate({"top":"980px","left":"56px","opacity":"0"},700,function(){
						$(".btn_man").show().css({"bottom":"54px","left":"56px"});
						setTimeout(function(){
							window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=1'
						},1700)
					});
						
					
//					setTimeout(function(){
//					$(".question_man").animate({"bottom":"54px","left":"56px"},300)	
//					$(".btn_man").animate({"bottom":"54px","left":"56px"},500,function(){window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=1'});	
//					},500)	
				});
				$(".dot2").on('touchstart', function(){
					$(".question_man").animate({"top":"180px","left":"100px"},300).animate({"top":"980px","left":"197px","opacity":"0"},700,function(){
						$(".btn_man").show().css({"bottom":"71px","left":"197px"});
						setTimeout(function(){
							window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=2'
						},1700)
					});
//					setTimeout(function(){
//					$(".btn_man").animate({"bottom":"71px","left":"197px"},500,function(){window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=2'});	
//					},500)	
				});
				$(".dot3").on('touchstart', function(){
					$(".question_man").animate({"top":"180px","left":"100px"},300).animate({"top":"980px","left":"308px","opacity":"0"},700,function(){
						$(".btn_man").show().css({"bottom":"86px","left":"308px"});
						setTimeout(function(){
							window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=3'
						},1700)
					});
					
					
					
//					setTimeout(function(){
//					$(".btn_man").animate({"bottom":"86px","left":"308px"},500,function(){window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=3'});
//					},500)	
				});
				$(".dot4").on('touchstart', function(){
					$(".question_man").animate({"top":"180px","left":"100px"},300).animate({"top":"980px","left":"429px","opacity":"0"},700,function(){
						$(".btn_man").show().css({"bottom":"98px","left":"429px"});
						setTimeout(function(){
							window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=5'
						},1700)
					});
					
					
//					setTimeout(function(){
//					$(".btn_man").animate({"bottom":"98px","left":"429px"},500,function(){window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=4'});	
//					},500)
				});
				$(".dot5").on('touchstart', function(){
						$(".question_man").animate({"top":"180px","left":"100px"},300).animate({"top":"980px","left":"525px","opacity":"0"},700,function(){
						$(".btn_man").show().css({"bottom":"108px","left":"525px"});
						setTimeout(function(){
							window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=4'
						},1700)
					});				
					
					
//					setTimeout(function(){
//						$(".btn_man").animate({"bottom":"108px","left":"525px"},500,function(){window.location.href = 'http://www.changanfordclub.com/r/cms/www/mobile/yiboGoddess/resault.html?score=5'});	
//					},500)
					
				});
			},2000)
		});
	}
})
