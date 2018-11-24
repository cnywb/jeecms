(function ($) {
    Mgm = function() {
		this.init();
    };
var lv = 1;
    $.extend(Mgm.prototype, {
		
        init: function(data) {
			this.id = 1;
			this.gender = '男';
			this.block = false;
                        this.startY = '';
                        this.num = 0;
                        this.colornum = 0;

	                 this.controlAnim=false;
	                 this.controlEnd=false;
                        this.chooseBlock = false;
			$('.ploading').show()
				this.loadimages(['/r/cms/www/mobile/taurus/music/bg.mp3?t=212','/r/cms/www/mobile/taurus/music/fdj.mp3?t=212','/r/cms/www/mobile/taurus/music/sync.mp3?t=212','/r/cms/www/mobile/taurus/images/fordlogo.png','/r/cms/www/mobile/taurus/images/door.jpg','/r/cms/www/mobile/taurus/images/change.jpg','/r/cms/www/mobile/taurus/images/part/0.jpg','/r/cms/www/mobile/taurus/images/part/1.png','/r/cms/www/mobile/taurus/images/part/2.png','/r/cms/www/mobile/taurus/images/part/3.png',
				'/r/cms/www/mobile/taurus/images/part/4.png','/r/cms/www/mobile/taurus/images/part/5.png','/r/cms/www/mobile/taurus/images/part/6.png','/r/cms/www/mobile/taurus/images/part/7.png','/r/cms/www/mobile/taurus/images/part/bg.jpg','/r/cms/www/mobile/taurus/images/part/line.png','/r/cms/www/mobile/taurus/images/part/line3.png',
				'/r/cms/www/mobile/taurus/images/part/line2.png','/r/cms/www/mobile/taurus/images/car/1.png','/r/cms/www/mobile/taurus/images/car/2.png','/r/cms/www/mobile/taurus/images/car/3.png','/r/cms/www/mobile/taurus/images/car/4.png','/r/cms/www/mobile/taurus/images/car/5.png','/r/cms/www/mobile/taurus/images/car/6.png','/r/cms/www/mobile/taurus/images/car/7.png',
				'/r/cms/www/mobile/taurus/images/car/8.png','/r/cms/www/mobile/taurus/images/car/icon_1.png','/r/cms/www/mobile/taurus/images/car/icon_2.png','/r/cms/www/mobile/taurus/images/car/icon_3.png','/r/cms/www/mobile/taurus/images/car/icon_4.png','/r/cms/www/mobile/taurus/images/car/icon_5.png','/r/cms/www/mobile/taurus/images/car/icon_6.png',
				'/r/cms/www/mobile/taurus/images/car/icon_7.png','/r/cms/www/mobile/taurus/images/car/icon_8.png','/r/cms/www/mobile/taurus/images/sharetips.png','/r/cms/www/mobile/taurus/images/icon/clock.png','/r/cms/www/mobile/taurus/images/icon/clockon.png','/r/cms/www/mobile/taurus/images/icon/icon_car1.png','/r/cms/www/mobile/taurus/images/icon/icon_car2.png',
				'/r/cms/www/mobile/taurus/images/icon/icon_car3.png','/r/cms/www/mobile/taurus/images/icon/icon_car4.png','/r/cms/www/mobile/taurus/images/icon/icon_car5.png','/r/cms/www/mobile/taurus/images/icon/icon_car6.png','/r/cms/www/mobile/taurus/images/icon/icon_car7.png','/r/cms/www/mobile/taurus/images/icon/icon_car8.png',
				'/r/cms/www/mobile/taurus/images/icon/icon_car1_on.png','/r/cms/www/mobile/taurus/images/icon/icon_car2_on.png','/r/cms/www/mobile/taurus/images/icon/icon_car3_on.png','/r/cms/www/mobile/taurus/images/icon/icon_car4_on.png','/r/cms/www/mobile/taurus/images/icon/icon_car5_on.png','/r/cms/www/mobile/taurus/images/icon/icon_car6_on.png',
				'/r/cms/www/mobile/taurus/images/icon/icon_car7_on.png','/r/cms/www/mobile/taurus/images/icon/icon_car8_on.png','/r/cms/www/mobile/taurus/images/icon/icondate.png','/r/cms/www/mobile/taurus/images/icon/shareicon.png','/r/cms/www/mobile/taurus/images/part/cart3on.png','/r/cms/www/mobile/taurus/images/card/c1_1.jpg','/r/cms/www/mobile/taurus/images/card/c1_2.jpg','/r/cms/www/mobile/taurus/images/card/c1_3.jpg','/r/cms/www/mobile/taurus/images/card/c1_4.jpg','/r/cms/www/mobile/taurus/images/card/c1_5.jpg','/r/cms/www/mobile/taurus/images/card/c1_6.jpg','/r/cms/www/mobile/taurus/images/card/c1_7.jpg','/r/cms/www/mobile/taurus/images/card/c1_8.jpg','/r/cms/www/mobile/taurus/images/card/c2_1.jpg','/r/cms/www/mobile/taurus/images/card/c2_2.jpg','/r/cms/www/mobile/taurus/images/card/c2_3.jpg','/r/cms/www/mobile/taurus/images/card/c2_4.jpg','/r/cms/www/mobile/taurus/images/card/c2_5.jpg','/r/cms/www/mobile/taurus/images/card/c2_6.jpg','/r/cms/www/mobile/taurus/images/card/c2_7.jpg','/r/cms/www/mobile/taurus/images/card/c2_8.jpg','/r/cms/www/mobile/taurus/images/car/icon_1on.png','/r/cms/www/mobile/taurus/images/car/icon_2on.png','/r/cms/www/mobile/taurus/images/car/icon_3on.png','/r/cms/www/mobile/taurus/images/car/icon_4on.png','/r/cms/www/mobile/taurus/images/car/icon_5on.png','/r/cms/www/mobile/taurus/images/car/icon_6on.png','/r/cms/www/mobile/taurus/images/car/icon_7on.png','/r/cms/www/mobile/taurus/images/car/icon_8on.png'])
		
        },
		
		loadimages : function(arr){
			var self=this;
			var newimages=[], loadedimages=0
			var arr=(typeof arr!="object")? [arr] : arr
			function imageloadpost(){
				loadedimages++;
		
				if (loadedimages==arr.length){
					
					setTimeout(function(){
						self.landing();
					},500)
				}
				var loadcent = loadedimages/arr.length*100;
				$(".loadTxt").empty().html("LOADING " + Math.round(loadcent) + "%")
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
		},
		landing:function(){
		
			var resault = location.search.match(new RegExp("[\?\&]nickName=([^\&]+)", "i"));
			if (resault != null) {
				var nickname = location.search.match(new RegExp("[\?\&]nickName=([^\&]+)", "i"))[1];
				var photo = location.search.match(new RegExp("[\?\&]userPhoto=([^\&]+)", "i"))[1];
				$('.userImg').attr('src','http://www.changanfordclub.com/common/image/scaling.htm?url='+decodeURI(photo).replace(/\\/g,'')+'&width=70&height=70');
				$('.nickname').empty().html(decodeURI(nickname))
			}
			
			  $('.ploading').hide();
			  $('.landing').show();
			  $('.page').fadeIn();
			  $('#bgmusic')[0].play();
			 var isHistory = false;
			 var lastVisit = 1;
		
			//  $('.getimages').fadeIn()
			  var self = this;
			 $('.landing a').on('touchstart',function(){

				  $('.landing').animate({
						top:-$(document).height()
					},1000,function(){
						$('.pagetips').fadeIn();
						$('.partlist li').addClass('on');
						//self.begin(self.id)
					})
			  });

			$('.pagetips').on('touchstart',function(){  //开始画面
				$('.pagetips').hide();
				$('.rightContentCover').hide();
				$('.partlist li').removeClass('on');
				setTimeout(function(){
					self.begin(self.id);
$('.apply').fadeIn();
					},500)
			})  
			  
			$('.partlist li').on('touchstart',function(e){
$('.partlist li').removeClass('partBtnBlur');
			if(self.chooseBlock) return false;
				self.chooseBlock = true;
				var ceid = $(this).index()+1;
				if(ceid > lastVisit){
					$(this).find('img').fadeIn().delay(500).fadeOut();
                    	self.chooseBlock = false;
					return false;
                                      
				} else if (ceid < lastVisit) {
               		//返回前面步骤
               		console.log('查看历史');
               		isHistory = true;
               		$('.partlist li').css('opacity','0.5');
					//$('.partlist li').removeClass('on')
					 $('.copy1,.copy2').hide();
                     $(this).css('opacity','1');
					 $(this).addClass('on');
					 self.chooseRight(ceid);

				} else {
					$('.partlist li').css('opacity','0.5')
					isHistory = false;
					lastVisit = lastVisit + 1;
					lv = lastVisit;
					//$('.partlist li').removeClass('on')
					$('.copy1,.copy2').hide();
                     $(this).css('opacity','1');
					 $(this).addClass('on');
					 self.chooseRight(ceid);
					 
				}
			})
				
			$('.partbtn a').on('touchstart',function(e){
				var  seid = $(this).index();
				var lenth = null;
				if(seid == 0){
					if(self.num >0){
						self.num--
					}else{
						self.num = 0;
					}
				}else{
					
					if(self.num >=2){
						self.num=2
					}else{
						self.num++
					}
					
				}
				$('.partlist').animate({
					top:-388*self.num
				})
			})  

                $('.partContet,.colorContent').on('touchstart', function(e) {
				$(window).on('touchmove.prevent', function(e) {
					e.preventDefault();
				});
				
				if (self.controlAnim == false) {
					self.controlAnim = true;
					self.startY = e.originalEvent.touches[0].pageY;
				} else{return}

			});
			
			$('.partContet').on('touchend', function(e) {
				$(window).off('touchmove.prevent')
				var endY = e.originalEvent.changedTouches[0].pageY;
				if (endY - self.startY > 20) {
					if(self.num==0){
						self.num=0
					}else{
						self.num--
					}
				}
				
				if (endY - self.startY < -20) {
					if(self.num>=2){
						self.num=2
					}else{
						self.num++
					}
				}
				
				self.controlAnim = false;
				$('.partlist').stop().animate({
					top:-388*self.num
				})
	
			}); 
			

                $('.colorContent').on('touchend', function(e) {
				$(window).off('touchmove.prevent')
				var endY = e.originalEvent.changedTouches[0].pageY;
				if (endY - self.startY > 10) {
					if(self.colornum==0){
						self.colornum=0
					}else{
						self.colornum--
					}
				}
				
				if (endY - self.startY < -10) {
					if(self.colornum>=1){
						self.colornum=1
					}else{
						self.colornum++
					}
				}
				
				self.controlAnim = false;
				$('.colorlist').stop().animate({
					top:-285*self.colornum
				})
	
			}); 
			
			
			$('.colorbtn a').on('touchstart',function(e){
				var  seid = $(this).index();
				var lenth = null;
				if(seid == 0){
					self.colornum = 0;
				}else{
					self.colornum = 1;
				}
				$('.colorlist').animate({
					top:-285*self.colornum
				})
			})  
			
			
			$('.colorlist li').on('touchstart',function(e){
				$('.colorlist li').removeClass('on')
				$(this).addClass('on')
				var  colorid = $(this).index();
				$('.carColor').fadeIn()
				$('.open a').attr('data-id',Number(colorid+1))
				$('.carColor').attr('src','/r/cms/www/mobile/taurus/images/car/'+Number(colorid+1)+'.png')
			})	  
		},
		
		begin:function(id,lastVisit){
			$('.fdj').attr("position: absolute;top: 150px;left: -4px;");
			$('.cs').attr("position: relative;top: -88px;left: -134px;z-index: 3;");
			$('.ppz').attr("top: 0;");
			$('.ppz').attr("opacity: 1;");
			$(".part4 section").attr("top: -280px;left: 393px;");
			$(".part5 section").attr("top: -280px;left: 393px;");
			$(".part6 section").css("left","627px");
			$(".part6 section").css("top","-305px");
			$(".part6 img").attr("left: 0;top: 0;");
			$(".light").css('top','0');
			$(".lighton").css('top','0');
			$(".ppz").removeClass('ppzscale');
			$(".part7 section").css("top","-280px");
			$(".part7 section").css("left","393px");
            $('.partlist li').removeClass('partBtnBlur');
            $('.copy2').hide();
			$('.rightContentCover').hide();

			console.log('接下来播放的动画ID是' + id)
			//$('.part'+id+' .copy1').delay(1000).fadeIn();
			
			$('.part'+id+' .copy2').fadeIn();
			
            $('.partlist li').css('opacity','0.5');
            var cidd = id - 1;
            $('.partlist li:eq('+cidd+')').css('opacity','1');
            $('.partlist li:eq('+cidd+')').addClass('on');
            $('.partlist li:eq('+cidd+')').addClass('partBtnBlur');
            
			this.chooseBlock = false

             if(id == 4){
				this.num = 1
				$('.partlist').stop().animate({
					top:-388*this.num
				},500)
			}
			if(id == 7){
				this.num = 2
				$('.partlist').stop().animate({
					top:-388*this.num
				},500)
			}
		
		},

		chooseRight:function(id){
			$('.rightContentCover').show();
			var self = this;
				self.id = id+1;
			if (id === 1) {
				//初始化
				$(".carLayer>img").hide();
				$(".carLayer>.p8").show();
				$(".fdj").show();
				//$('.copy2').hide();
				$('.part1 .copy2').show();
				$('.part'+id+' .copy1').fadeIn(1000);	
				$('.part'+id+' section').delay(1000).show().animate({top:70},1500,function(){
					$('.part1 .fdj').fadeOut(500);
					$('.carLayer .fdj_on').fadeIn(1000);
					$('.part1 section').delay(800).animate({top:-550},1500,function(){
						$('.part1 .copy1').delay(1000).fadeOut(500);
						$('.part1 .copy2').delay(1000).fadeOut(500,function(){
							self.begin(self.id);
							});
						})
					});
				}
			if (id === 2) {
				//先初始化
					$(".carLayer>img").hide();
					$(".carLayer>.p8").show();
					$(".carLayer>.fdj_on").show();
					$('.part2 .cs').css("top","88px");
					$('.part2 .cs').css("left","-154px");
					$('.part2 .cs').show();
					$('.carLayer .cs_on').hide();
					//$('.copy2').hide();
					$('.part2 .copy2').show();
					$('.part2 .copy1').fadeIn(1000);
					$('.part2 section').delay(1000).show().animate({top:55},1500,function(){
						$(".part2 .cs").fadeOut(500);
						$(".carLayer .cs_on").fadeIn(1000);
						$(".part2 section").delay(800).animate({top:-350},1500,function(){
							console.log('车身动画结束');
							$(".part2 .copy1").delay(100).fadeOut(500);
							$(".part2 .copy2").delay(100).fadeOut(500,function(){
								self.begin(self.id);
							});
						});
							
						})
				}
			if (id === 3) {
				//初始化
				$(".carLayer>img").hide();
				$(".carLayer>.p8").show();
				$(".carLayer>.cs_on").show();
				$('.part'+id+' .line').show();
				$('.part'+id+' .line').css("top","-30px");
				$('.part'+id+' .ppz').css("opacity","1");
				$('.part'+id+' .ppz').css("top","0");
				$(".part3 section").css("top","-259px");
				$(".part3 section").css("left","393px");
				$('carLayer .cs').show();
				$('.part3 .copy2').show();
				$('.part3 .copy1').fadeIn(1000);
				$('.part3 section ').delay(1000).show().animate({top:30},1000,function(){
						$('.part'+id+' .line').delay(500).animate({
							top:-280
						},500);
                   	    $('.part'+id+' .ppz').addClass('ppzscale');
						$('.part'+id+' .ppz').delay(500).animate({
							top:+100,
							opacity:0
						},1000,function(){
							$('.part'+id+' .tcshadow').fadeIn(1000);
							$('.part'+id+' .tcshadow').delay(1500).fadeOut(1000,function(){
								$(".part3 .copy1").delay(100).fadeOut(500);
								$(".part3 .copy2").delay(100).fadeOut(500,function(){
									self.begin(self.id);
								})
							});
						})
				})
			}
			
			if (id === 4) {
				//初始化
				$(".carLayer>img").hide();
				$(".carLayer>.p8").show();
				$(".carLayer>.cs_on").show();
				$('.part'+id+' .line').show();
				$('.part'+id+' .line').css("top","-30px");
				$('.part'+id+' .ppz').css("opacity","1");
				$('.part'+id+' .ppz').css("top","0");
				$(".part4 section").css("top","-280px");
				$(".part4 section").css("left","393px");
				//$('.copy2').hide();
				$('.part4 .copy2').show();
				$('.part4 .copy1').fadeIn(1000);
				$('.part4 section').delay(1000).show().animate({top:35},1000,function(){
						$('.part'+id+' .line ').delay(500).animate({
							top:-280
						},500);
                   	    $('.part'+id+' .ppz').addClass('ppzscale');
						$('.part'+id+' .ppz').delay(500).animate({
							top:100,
							opacity:0
						},1000,function(){
							$('.music').show();
							self.flow(0);
							$('#bgmusic')[0].volume = 0;
							$('#bgmusic')[0].pause();
							$('#sync')[0].play();
							$('#sync')[0].volume = 0.05;
							var audio = document.getElementById("sync");
							audio.addEventListener('canplaythrough', function(){this.volume=0.05;}, false);
							var audioInterval = setInterval(function()
							{
								var volume = audio.volume;
								if(!volume)
								{
									return;
								}
							
								if(volume >= 0.65)
								{
									return;
								}
							
								if(volume)
								{
									audio.volume += 0.05;
								}
							}, 400);

							$('.part'+id+' .music').delay(3000).fadeOut(1000,function(){
								$(".part4 .copy1").delay(100).fadeOut(500);
								$(".part4 .copy2").delay(100).fadeOut(500,function(){
									self.begin(self.id);
								})
							});
						})
				})
			}
			
			if (id === 5) {
				//初始化
				$(".carLayer>img").hide();
				$(".carLayer>.p8").show();
				$(".carLayer>.cs_on").show();
				$('.part'+id+' .line').show();
				$('.part'+id+' .line').css("top","-30px");
				$('.part'+id+' .ppz').css("opacity","1");
				$('.part'+id+' .ppz').css("top","0");
				$(".part5 section").css("top","-280px");
				$(".part5 section").css("left","393px");
				//	$('.copy2').hide();
				$('.part5 .copy2').show();
				$('.part5 .copy1').fadeIn(1000);
				$('.part5 section ').delay(1000).show().animate({top:35},1000,function(){
						$('.part'+id+' .line ').delay(500).animate({
							top:-280
						},500);
						$('.part'+id+' .shiftCircle').fadeIn(700,function(){
							$('.part'+id+' .shiftCircle').fadeOut(500)
						})
						
						$('.part'+id+' .l_one').delay(1200).fadeIn(500,function(){
							$('.part'+id+' .l_one').fadeOut(100,function(){
								$('.part'+id+' .l_two').fadeIn(500,function(){
									$('.part'+id+' .l_two').fadeOut(100,function(){
										$('.part'+id+' .l_three').fadeIn(500,function(){
											$('.part'+id+' .l_three').fadeOut(100,function(){
												$('.part'+id+' .l_four').fadeIn(500,function(){
													$('.part'+id+' .l_four').fadeOut(100,function(){
														$('.part'+id+' .ppz').addClass('ppzscale');
													});
												})
											})
										})
									})
								})
							})
						})
						$('.part'+id+' .ppz').delay(3500).animate({
							top:100,
							opacity:0
						},1000,function(){
								$(".part5 .copy1").delay(100).fadeOut(500);
								$(".part5 .copy2").delay(100).fadeOut(500,function(){
									self.begin(self.id);
								})
						})
				})	
			}
			if (id ===6) {
				$(".carLayer>img").hide();
				$(".carLayer>.p8").show();
				$(".carLayer>.cs_on").show();
				$('.part'+id+' .zy').show();
				$('.part'+id+' .zy').css("opacity","1");
				$('.part'+id+' .zy').css("top","120px");
				$('.part'+id+' .zy').css("left","5px");
				$(".part6 section").css("top","-305px");
				$(".part6 section").css("left","627px");
				$('.carLayer img.door').css("left","667px");
				$('.carLayer img.door').css("top","217px");
				$('.carLayer img.door').hide();
				$(".part5 section").css("left","393px");
				//$('.copy2').hide();
				$('.part6 .copy2').show();
				$('.part6 .copy1').fadeIn(1000);
				$('.part'+id+' section').delay(1000).show().animate({
						top:100
					},1000,function(){
						$('.part'+id+' .zy').animate({
									left:-70,opacity:.3
								},1000,function(){
									$('.zy').hide();
									$('.zy_on').fadeIn();
								})
								$('.door').delay(600).fadeIn().animate({
									left:600
								},500,function(){
								   $('.door').fadeOut();
								   $('.cart3on').fadeIn();
								})
								$('.part'+id+' section').delay(1000).animate({
									top:-305
								},800,function(){
									$(".part6 .copy1").delay(100).fadeOut(500);
									$(".part6 .copy2").delay(100).fadeOut(500,function(){
										self.begin(self.id);
									});
								})
					})
			}
			
			if (id===7) {
				$(".carLayer>img").hide();
				$(".carLayer>.p8").show();
				$(".carLayer>.cart3on").show();
				$(".part7 section").css("top","-650px");
				$(".part7 section").css("left","393px");
				$('.part'+id+' .line').show();
				$('.part'+id+' .line').css("top","-290px");
				$('.part'+id+' .ppz').css("opacity","1");
				$('.part'+id+' .ppz').css("top","90px");
				$('.part7 .copy2').show();
				$('.part7 .copy1').fadeIn(1000);
				$('.part'+id+' section ').css('left','250px');
				$('.part'+id+' section ').css('top','-150px');
				$('.part'+id+' section ').delay(1000).show().animate({
						top:220
					},1000,function(){
						$('.part'+id+' .line ').delay(500).animate({
							top:-820,
						},500)
                   	   // $('.part'+id+' .ppz').addClass('ppzscale')
						$('.part'+id+' .ppz').animate({
							opacity:0
						},1000,function(){
									$('.light').fadeIn()
									$('.lighton').fadeIn().addClass('lightIn')
									$('.lighton').delay(2000).removeClass('lightIn').fadeOut(300,function(){
										$(".part7 .copy1").delay(100).fadeOut(500);
										$(".part7 .copy2").delay(100).fadeOut(500,function(){
											self.begin(self.id)
										});
									})
						})
					})
			}
			
			if(id===8){
					$('.part8 .copy1').fadeIn();
					$('.part8 .copy2').show();
					$('.partbtn,.partlist').hide();
					$('.rightContent').addClass('selectcolor')
					$(".apply").fadeIn();
					$('.colorbtn,.colorContent').fadeIn();
					$('.open').fadeIn();
					$('.rightContentCover').hide();
			}
			
			
				//$('.part'+id+' .copy1').delay(1000).fadeIn();
				//$('.part'+id+' .copy2').delay(2000).fadeIn();	
				//$('.part'+id+' p').delay(800).fadeOut()

				
//				if(id==1 || id==2 || id==6){
//					$('.part'+id+' section').show().animate({
//						top:0
//					},1500,function(){
//						switch(id){
//							case 1:
//								$('.part1 .fdj').fadeOut(500)
//								$('.part1 .fdj_on').fadeIn(1000)
//								$('.part'+id+' section').delay(500).animate({
//									top:-550
//								},1000,function(){
//									console.log(self.id);
//									self.begin(self.id)
//								})
//							break;
//							case 2:
//								$('.part2 .cs').fadeOut(500)
//								$('.part2 .cs_on').fadeIn(1000)
//								$('.part'+id+' section').delay(500).animate({
//									top:-350
//								},1000,function(){
//									self.begin(self.id)
//								})
//
//							break;
//							case 6:
//								$('.part'+id+' .zy').animate({
//									left:-44
//								},500,function(){
//									$('.zy').fadeOut()
//									$('.zy_on').fadeIn()
//								})
//								$('.door').delay(1000).fadeIn().animate({
//									left:600
//								},500,function(){
//								   $('.door').fadeOut();
//                                 $('.zy_on').attr('src','images/part/cart3on.png');
//                       
//								})
//								$('.part'+id+' section').delay(500).animate({
//									top:-350
//								},1500,function(){
//									self.begin(self.id)
//								})
//							break;
//							
//						}
//
//					})
//				}
				
//				if(id==3 || id==4 || id==5){
//					$('.part'+id+' section ').show().animate({
//						top:-35
//					},1000,function(){
//						
//						$('.part'+id+' .line ').delay(500).animate({
//							top:-280
//						},500)
//                 	        $('.part'+id+' .ppz').addClass('ppzscale')
//						$('.part'+id+' .ppz').delay(500).animate({
//							top:50,
//							opacity:0
//						},1000,function(){
//							switch(id){
//								case 3:
//								//	$('.part'+id+' .tcshadow').fadeIn();
//								//	$('.part'+id+' .tcshadow').delay(1000).fadeOut();
//									setTimeout(function(){
//										self.begin(self.id)
//									},500)
//								break;
//								case 4:
//									$('.music').fadeIn()
//										self.flow(0);
//									  $('#sync')[0].play();
//									  $('#sync')[0].volume = 1;
//           
//									$('.music').delay(4000).fadeOut()
//									setTimeout(function(){
//										self.begin(self.id)
//									},4500)
//								break;
//								case 5:
//									$('.part'+id+' .shake').fadeIn();
//									$('.part'+id+' .shake').delay(1000).fadeOut();
//									setTimeout(function(){
//										self.begin(self.id)
//									},2500)
//								break;
//								case 7:
//									$('.light').fadeIn()
//									$('.lighton').fadeIn().addClass('lightIn')
//									$('.lighton').delay(2000).removeClass('lightIn').fadeOut()
//									setTimeout(function(){
//										self.begin(self.id)
//									},3000)
//								break;
//								
//							}
//							
//						})
//					})
//					
//				}
				
//				if(id == 7) {
//					$('.part'+id+' section ').css('left','220px');
//					$('.part'+id+' section ').css('top','-150px');
//					$('.part'+id+' section ').show().animate({
//						top:-35
//					},1000,function(){
//						
//						$('.part'+id+' .line ').delay(500).animate({
//							top:-320,
//						},500)
//                 	                          $('.part'+id+' .ppz').addClass('ppzscale')
//						$('.part'+id+' .ppz').delay(500).animate({
//							top:220,
//							opacity:0
//						},1000,function(){
//							switch(id){
//								case 7:
//									$('.light').fadeIn()
//									$('.lighton').fadeIn().addClass('lightIn')
//									$('.lighton').delay(2000).removeClass('lightIn').fadeOut()
//									setTimeout(function(){
//										self.begin(self.id)
//									},3000)
//								break;
//								
//							}
//							
//						})
//					})
//					
//				}
//				
//				if(id==8){
//					$('.partbtn,.partlist').hide();
//					$('.rightContent').addClass('selectcolor')
//					$('.colorbtn,.colorContent').fadeIn();
//					$('.open').fadeIn()
//				}
		},
		
		flow:function(num){
			var that = this;
			num++;
			if(num<=3){
				//console.log(num)
				$('.music img:nth-child('+num+')').fadeIn();
				setTimeout(function(){
					that.flow(num);
				},500)
			}else{
				$('.music img').hide();
				num=0;
				that.flow(num);
			}
		},

    });
})(jQuery);

$(function() {

  var mgm = new Mgm();

});

