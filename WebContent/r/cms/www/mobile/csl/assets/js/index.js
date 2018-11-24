var game;
var video;
var selectedClub = "shenhua";
var selectedClubCNStr = "绿地申花";
var ticket = "colored_shenhua";
var carTurnSpeed = 100;
var targetDelay = 900;
var targetSpeed = 500;
var clubArray = ['shenhua','hengda','fuli','suning','luneng','guoan','shanggang','lvcheng','yongcang','taida','jianye','huaxia','hongyun','yanbian','yatai','lifan'];
var clubOrgi = ['shenhua','hengda','fuli','suning','luneng','guoan','shanggang','lvcheng','yongcang','taida','jianye','huaxia','hongyun','yanbian','yatai','lifan'];
var leftTimes = 3;
var timeSecond = 60;
var block = false;
var openId = Math.random();
var video = document.querySelector('#myvideo');
var client= (function (){
		var u = navigator.userAgent;
		return {
			ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), 
			android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, 
			weixin: u.indexOf('MicroMessenger') > -1
		}
	})();
//$(document).ready(function(){
//  function stopScrolling(touchEvent) {
//      touchEvent.preventDefault();
//  }
//  document.addEventListener( 'touchstart' , stopScrolling , false );
//  document.addEventListener( 'touchmove' , stopScrolling , false );
//});

$(function(){
		getleftTimes().done(function(result){
		var playtimes = result.message;
		leftTimes = 3 - playtimes;
		//alert(leftTimes);
		if(leftTimes<0) {
			leftTimes = 0;
		}
		$(".leftTimesTxt").html("0" + leftTimes)
	})
	$(document).on('touchmove.prevent', function(e) {
        e.preventDefault();
    });
	//alert(bb);
	var _w = $(window).width();
	var _h = $(window).height();
	var windowRatio = _w / _h;
	//var isIphone = navigator.userAgent.toLowerCase().indexOf('iphone') >= 0;
		video.load();
		//$('#video').get(0).play();
		video.addEventListener('canplaythrough',function(){
	    })

	    video.addEventListener('ended',myHandler);
	setTimeout(function(){
		$('#masker').fadeOut();
		//$("#myvideo").width(640).height(1030);
		//video.play();
		if (client.android) {
			video.play();
		} else {
			loadingMediaTimer = setInterval(function (){
				// document.title = video.readyState; 
				if ( video.readyState >= 1 ){ 
					video.play();
					clearInterval(loadingMediaTimer);
				}
			},10)
		}
	},2500);
	
	setTimeout(function(){
		playMusic();
	},9000);
	$('#masker').on('touchend',function(){
		$('#masker').fadeOut();
			video.play();
	})
    function myHandler(e) {
    	video.pause();
    	video = null;
    	$('.video-wrapper').remove();
    	//alert("视频放完了")
    	$(".logo").fadeIn();
        if(!e) { e = window.event; }
        console.log("Video Finished");
        //$(".video-wrapper").fadeOut(1000,function(){$('#myvideo').remove();});
        $(".selectTeam").fadeIn(200,function(){
       	var selectClub = new Swiper ('.selectTeamWrap', {
    	pagination : '.swiper-pagination',
	     	width:490,
	      	height:490,
		}) 
		TweenMax.to($(".selectTeam"), 0.6, {opacity:"1",ease:Power2.easeInOut});
		$("#gameStage").show();
        });

		
       
    };
    

	game = new Phaser.Game(_w,_h,Phaser.auto,"gameStage");
	game.transparent = true;
	game.state.add('Boot',boot);
	game.state.add('Preload',preload);
	game.state.add('playGame',playGame);
	game.state.add('GameSuccess',GameSuccess);
	game.state.add('GameSuccessGift',GameSuccessGift);
	game.state.add('GameOverScreen',GameOverScreen);
	game.state.start('Boot');
	$(".selectTeamWrap ul li").on('click',function(event){
		$(".icoShenhua").attr("src","assets/i/ico_shenhua.png");
		$(".icoHengda").attr("src","assets/i/ico_hengda.png");
		$(".icoFuli").attr("src","assets/i/ico_fuli.png");
		$(".icoSuning").attr("src","assets/i/ico_suning.png");
		$(".icoLuneng").attr("src","assets/i/ico_lunneg.png");
		$(".icoGuoan").attr("src","assets/i/ico_guoan.png");
		$(".icoShanggang").attr("src","assets/i/ico_shanggang.png");
		$(".icolvcheng").attr("src","assets/i/ico_lvcheng.png");
		$(".icoyongcang").attr("src","assets/i/ico_yongcang.png");
		$(".icotaida").attr("src","assets/i/ico_taida.png");
		$(".icojianye").attr("src","assets/i/ico_jianye.png");
		$(".icohuaxia").attr("src","assets/i/ico_huaxia.png");
		$(".icohongyun").attr("src","assets/i/ico_hongyun.png");
		$(".icoyanbian").attr("src","assets/i/ico_yanbian.png");
		$(".icoyatai").attr("src","assets/i/ico_yatai.png");
		$(".icolifan").attr("src","assets/i/ico_lifan.png");
		var a = $(event.target).data("orgi");
		selectedClub = $(event.target).data("club");
		//console.log(a);
		$(event.target.firstChild).attr("src","assets/i/" + a + "_on.png");
	})
	$(".selectConfirm").on("touchstart",function(){
		$(".selectTeam").fadeOut();
		
		
			game.state.start('Preload');
			initClubName(selectedClub);
			var clubPosi = jQuery.inArray(selectedClub,clubArray);
			clubArray.splice(clubPosi,1);
			console.log(selectedClubCNStr);
			$(".score").fadeIn();
		
		//	game.state.add('Preload',preload);
		//	game.state.add('TitleScreen',titleScreen);
		//	game.state.add("HowToPlay",howToPlay);
		//	game.state.add('PlayGame',playgame);
		//	game.state.add('GameOverScreen',GameOverScreen);
		//	game.state.start('Boot');
	})
});

var boot = function(game) {};
boot.prototype = {
	preload: function() {
		//game.load.image('loading','assets/i/loading.png');
		game.load.image("scoreBoard","assets/i/score.png");
		game.load.image("gameBg","assets/i/gameBg.jpg");
		game.load.image("bridge","assets/i/bridge.png");
		game.load.image("carShadow","assets/i/carShadow.png");
		//game.load.image("carTips","assets/i/carTips.png");
		//game.load.image("startBtn","assets/i/btn_startGame.png");
		//game.load.image("fakeClub","assets/i/gameTip.png");
	},
	create: function(){
		game.scale.pageAlignHorizontally = true;
		game.scale.pageAlignVertically = true;
		game.scale.scaleMode = Phaser.ScaleManager.SHOW_ALL;
			var bg = game.add.tileSprite(0,0,game.width,game.height,'gameBg');
			var bridge = game.add.sprite(game.width/2,game.height/2,'bridge');
			bridge.anchor.set(0.5);
//			var fakeClub = game.add.sprite(game.width/2,game.height/2 + 150,'fakeClub');
//			fakeClub.anchor.set(0.5);
			var scoreBoard =  game.add.sprite(30,30,"scoreBoard");
			bg.autoScroll(0,250);
	}
}

var preload = function(game) {};
preload.prototype = {
	preload:function() {
			var bg = game.add.tileSprite(0,0,game.width,game.height,'gameBg');
			var bridge = game.add.sprite(game.width/2,game.height/2,'bridge');
			bridge.anchor.set(0.5);
//			var fakeClub = game.add.sprite(game.width/2,game.height/2 + 150,'fakeClub');
//			fakeClub.anchor.set(0.5);
			var scoreBoard =  game.add.sprite(30,30,"scoreBoard");
			bg.autoScroll(0,250);
//			var gameTipcarShadow =  game.add.sprite(game.width/2,game.height/2+100,"carShadow");
//			gameTipcarShadow.anchor.set(0.5);
//			var gameTipcar =  game.add.sprite(game.width/2 +15,game.height/2+240,"carTips");
//			gameTipcar.anchor.set(0.5);
//			var loadingBar = game.add.sprite(game.width/2,game.height - 5,"loading");
//			loadingBar.anchor.setTo(0.5);
			game.load.image("gift","assets/i/colored_ford.png");
			game.load.image("colored_fuli","assets/i/colored_fuli.png");
			game.load.image("colored_guoan","assets/i/colored_guoan.png");
			game.load.image("colored_hengda","assets/i/colored_hengda.png");
			game.load.image("colored_hongyun","assets/i/colored_hongyun.png");
			game.load.image("colored_huaxia","assets/i/colored_huaxia.png");
			game.load.image("colored_jianye","assets/i/colored_jianye.png");
			game.load.image("colored_lifan","assets/i/colored_lifan.png");
			game.load.image("colored_luneng","assets/i/colored_luneng.png");
			game.load.image("colored_lvcheng","assets/i/colored_lvcheng.png");
			game.load.image("colored_shanggang","assets/i/colored_shanggang.png");
			game.load.image("colored_shenhua","assets/i/colored_shenhua.png");
			game.load.image("colored_suning","assets/i/colored_suning.png");
			game.load.image("colored_taida","assets/i/colored_taida.png");
			game.load.image("colored_yanbian","assets/i/colored_yanbian.png");
			game.load.image("colored_yatai","assets/i/colored_yatai.png");
			game.load.image("colored_yongcang","assets/i/colored_yongcang.png");
			game.load.image("gray_fuli","assets/i/gray_fuli.png");
			game.load.image("gray_guoan","assets/i/gray_guoan.png");
			game.load.image("gray_hengda","assets/i/gray_hengda.png");
			game.load.image("gray_hongyun","assets/i/gray_hongyun.png");
			game.load.image("gray_huaxia","assets/i/gray_huaxia.png");
			game.load.image("gray_jianye","assets/i/gray_jianye.png");
			game.load.image("gray_lifan","assets/i/gray_lifan.png");
			game.load.image("gray_luneng","assets/i/gray_luneng.png");
			game.load.image("gray_lvcheng","assets/i/gray_lvcheng.png");
			game.load.image("gray_shanggang","assets/i/gray_shanggang.png");
			game.load.image("gray_shenhua","assets/i/gray_shenhua.png");
			game.load.image("gray_suning","assets/i/gray_suning.png");
			game.load.image("gray_taida","assets/i/gray_taida.png");
			game.load.image("gray_yanbian","assets/i/gray_yanbian.png");
			game.load.image("gray_yatai","assets/i/gray_yatai.png");
			game.load.image("gray_yongcang","assets/i/gray_yongcang.png");
			game.load.image("car","assets/i/car.png");
			game.load.image("carLight","assets/i/carLight.png");
			game.load.image("partcital","assets/i/parcital.png");
			game.load.audio("bgmusic",["assets/sound/bgmusic.mp3"]);
			game.load.audio("successMusic",["assets/sound/success.mp3"]);
			game.load.audio("losemusic",["assets/sound/lose.mp3"]);
			game.load.onFileComplete.add(this.fileComplete, this);
			$(".loader").fadeIn();
			
			
//			if (game.load.hasLoaded) {
//				gameTipcar.destroy();
//			}
	},
	fileComplete: function(progress, cacheKey, success, totalLoaded, totalFiles){
		console.log("File Complete: " + progress + "% - " + totalLoaded + " out of " + totalFiles);
		var tweenOn = true;
		if (tweenOn) {
			if (progress > 0 && progress < 10) {
				//tweenOn = false;
				$(".djsCopy span").html("10");
			} else if (progress > 10 && progress < 20) {
				$(".djsCopy span").html("09");
			} else if (progress > 20 && progress < 30) {
				$(".djsCopy span").html("08");
			}else if (progress > 30 && progress < 40) {
				$(".djsCopy span").html("07");
			} else if (progress > 40 && progress < 50) {
				$(".djsCopy span").html("06");
			}else if (progress > 50 && progress < 60) {
				$(".djsCopy span").html("05");
			} else if (progress > 60 && progress <70) {
				$(".djsCopy span").html("04");
			} else if (progress > 70 && progress < 80) {
				$(".djsCopy span").html("03");
			} else if (progress > 80 && progress < 90) {
				$(".djsCopy span").html("02");
			} else if (progress > 90 && progress < 100) {
				$(".djsCopy span").html("01");
			}
		}
	},
	create: function() {
		$(".djsCopy").fadeOut();
		$(".daojishi").fadeOut();
		$(".go").fadeIn(1000,function(){
			//TweenMax.to($(".go"), 0.6, {opacity:"1",ease:Power2.easeInOut});
			TweenMax.to($(".go"), 0.7, {css:{scale:'6',opacity:'0'}, ease:Quad.easeInOut,onComplete:function(){
				$(".loader").fadeOut();
				leftTimes = leftTimes -1;
				if (leftTimes < 0) {
					game.state.start("GameOverScreen");
					$(".leftTimes span").html("00");
					$(".Gameover").fadeIn();
				} else {
					$(".leftTimesTxt").html("0" + leftTimes);
					addTimes();
					game.state.start("playGame");
				}
			}});
		});
		//game.state.start("TitleScreen");	
//		var startBtn =  game.add.button(game.width/2,game.height/2+340,"startBtn",this.startGame);
//			startBtn.anchor.set(0.5);
		$(".loader").show()
	},
	startGame: function() {
		//$(".gameTips").fadeOut();
		leftTimes = leftTimes -1;
		if (leftTimes < 0) {
			game.state.start("GameOverScreen");
			$(".leftTimes span").html("00");
			$(".Gameover").fadeIn();
		} else {
			$(".leftTimesTxt").html("0" + leftTimes)
			game.state.start("playGame");
		}
	}
}

var playGame = function(game){};
playGame.prototype = {
	create: function() {
		console.log("playgame");
		var bg = game.add.tileSprite(0,0,game.width,game.height,'gameBg');
		var bridge = game.add.sprite(game.width/2,game.height/2,'bridge');
		bridge.anchor.set(0.5);
		var scoreBoard =  game.add.sprite(30,30,"scoreBoard");
		bg.autoScroll(0,250);
		
		this.bgMusic = game.add.audio("bgmusic");
		this.bgMusic.loopFull(1);
		game.successMusic = game.add.audio("successMusic");
		game.loseMusic = game.add.audio("losemusic");
		
		//添加生命值倒计时信息
		this.timer = game.time.events.loop(Phaser.Timer.SECOND, this.updateTime, this);
		
		
		//添加车子相关元素
		carShadow =  game.add.sprite(game.width/2,game.height/2+200,"carShadow");
		carShadow.anchor.set(0.5);
		//game.physics.enable(carShadow,Phaser.Physics.ARCADE);
		carLight =  game.add.sprite(game.width/2,game.height/2+360,"carLight");
		carLight.anchor.set(0.5);
		lightTween = game.add.tween(carLight).to({
			alpha:0.6
		},1500,"Linear",true,0,-1);
		lightTween.yoyo(true);
		var carShadowTween = game.add.tween(carShadow).to({
			width:280,height:280,alpha:0.6
		},400,Phaser.Easing.Circular.in,true,0,-1);
		carShadowTween.yoyo(true);
		car = game.add.sprite(game.width/2,game.height/2 + 200,"car");
		car.anchor.set(0.5);
		car.canMove = true;
		car.side = -1;
		car.smokeEmitter = game.add.emitter(car.x,car.y + car.height/2 + 2, 50)
		car.smokeEmitter.makeParticles("partcital");
		car.smokeEmitter.setXSpeed(-55,55);
		car.smokeEmitter.setYSpeed(30,350);
		car.smokeEmitter.setAlpha(0.7,0.1);
		car.smokeEmitter.start(false,500,50);
		game.physics.enable(car,Phaser.Physics.ARCADE);
		
		//控制车子代码
		
		
		
		//添加游戏对象组
		this.carGroup = game.add.group();
		this.clubGroup = game.add.group();
		this.ticketGroup = game.add.group();
		this.specialGroup = game.add.group();
		this.carGroup.add(car);
		
		
		
		
		//添加障碍物
		this.targetLoop = game.time.events.loop(targetDelay,function(){
			//console.log(targetPosi);
			var targetPosiRnd = game.rnd.between(1,6);
			switch (targetPosiRnd) {
				case 1:
				var t1 = this.getTargetType();
				var target = new Target(game,"channel1",t1[0]);
				game.add.existing(target);
				if (t1[1]=="gift") {this.specialGroup.add(target)} else if (t1[1]=="ticket") {this.ticketGroup.add(target)} else {this.clubGroup.add(target)};

				var t2 = this.getTargetType();
				var target2 = new Target(game,"channel2",t2[0]);
				game.add.existing(target2);
				if (t2[1]=="gift") {this.specialGroup.add(target2)} else if (t2[1]=="ticket") {this.ticketGroup.add(target2)} else {this.clubGroup.add(target2)};
				break
				
				case 2:
				var t1 = this.getTargetType();
				var target = new Target(game,"channel1",t1[0]);
				game.add.existing(target);
				if (t1[1]=="gift") {this.specialGroup.add(target)} else if (t1[1]=="ticket") {this.ticketGroup.add(target)} else {this.clubGroup.add(target)};

				var t2 = this.getTargetType();
				var target2 = new Target(game,"channel3",t2[0]);
				game.add.existing(target2);
				if (t2[1]=="gift") {this.specialGroup.add(target2)} else if (t2[1]=="ticket") {this.ticketGroup.add(target2)} else {this.clubGroup.add(target2)};
				break
				
				case 3:
				var t1 = this.getTargetType();
				var target = new Target(game,"channel2",t1[0]);
				game.add.existing(target);
				if (t1[1]=="gift") {this.specialGroup.add(target)} else if (t1[1]=="ticket") {this.ticketGroup.add(target)} else {this.clubGroup.add(target)};

				var t2 = this.getTargetType();
				var target2 = new Target(game,"channel2",t2[0]);
				game.add.existing(target2);
				if (t2[1]=="gift") {this.specialGroup.add(target2)} else if (t2[1]=="ticket") {this.ticketGroup.add(target2)} else {this.clubGroup.add(target2)};
				break
				
				case 4:
				var t1 = this.getTargetType();
				var target = new Target(game,"channel1",t1[0]);
				game.add.existing(target);
				if (t1[1]=="gift") {this.specialGroup.add(target)} else if (t1[1]=="ticket") {this.ticketGroup.add(target)} else {this.clubGroup.add(target)};
				break
				
				case 5:
				var t1 = this.getTargetType();
				var target = new Target(game,"channel2",t1[0]);
				game.add.existing(target);
				if (t1[1]=="gift") {this.specialGroup.add(target)} else if (t1[1]=="ticket") {this.ticketGroup.add(target)} else {this.clubGroup.add(target)};
				break
				
				case 6:
				var t1 = this.getTargetType();
				var target = new Target(game,"channel3",t1[0]);
				game.add.existing(target);
				if (t1[1]=="gift") {this.specialGroup.add(target)} else if (t1[1]=="ticket") {this.ticketGroup.add(target)} else {this.clubGroup.add(target)};
				break
			}
		},this);
		
		window.addEventListener("deviceorientation", this.handleOrientation, true);

		
	},
	updateTime: function() {
		if (timeSecond <= 0) {
		$(".Gameover").fadeIn(500,function(){
			//$(".leftTimes span").html("0" + leftTimes);

		});
		window.removeEventListener("deviceorientation", this.handleOrientation, true);
//		game.input.keyboard.removeKey(Phaser.Keyboard.Z);
//		game.input.keyboard.removeKey(Phaser.Keyboard.X);
		car.smokeEmitter.on = false;
//		cars[1].smokeEmitter.on = false;
		game.time.events.remove(this.targetLoop);
		game.tweens.removeAll();
//
		for (var i=0; i < this.clubGroup.length; i++) {
			this.clubGroup.getChildAt(i).body.velocity.y = 0;
			this.clubGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.ticketGroup.length; i++) {
			this.ticketGroup.getChildAt(i).body.velocity.y = 0;
			this.ticketGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.specialGroup.length; i++) {
			this.specialGroup.getChildAt(i).body.velocity.y = 0;
			this.specialGroup.getChildAt(i).body.velocity.x = 0;
		};
		club.destroy();
		car.body.velocity.y = 0;
		game.state.start("GameOverScreen");
		} else {
			timeSecond = timeSecond - 1;
			$(".timesTxt").html(timeSecond + "'");
		}
		
	},
	getTargetType: function() {
		var targetType = [];
		var targetTypeRnd = game.rnd.between(1,100);
			if (targetTypeRnd == 95 || targetTypeRnd == 96 ) {
				targetType[0] = "gift";
				targetType[1] = "gift";
			} else if (targetTypeRnd == 98) {
				targetType[0] = ticket;
				targetType[1] = "ticket";
				//console.log("将来了:" + targetTypeRnd + "//奖品是：" + targetType);
			} else {
				targetType[0] = "gray_" + clubArray[game.rnd.between(0,clubArray.length-1)];
				targetType[1] = "club";
			};
			return targetType;
	},
	update:function() {
		car.smokeEmitter.x = car.x;
		carShadow.x = car.x;
		carLight.x = car.x;
		//this.carLight.x = ;
//		cars[1].smokeEmitter.x = cars[1].x;
//
		game.physics.arcade.collide(this.carGroup,this.clubGroup,function(car,club) {
//			if(c.tint == t.tint) {
//				t.destroy();
//				this.hitSound.play();
//			} else {
				game.loseMusic.play();
				this.targetFail(club);
//			}
		},null,this);
		
		game.physics.arcade.collide(this.carGroup,this.ticketGroup,function(car,ticket) {
//			if(c.tint == t.tint) {
//				t.destroy();
//				this.hitSound.play();
//			} else {
				game.successMusic.play();
				this.ticketSuccess(ticket);
				
//			}
		},null,this);
		game.physics.arcade.collide(this.carGroup,this.specialGroup,function(car,gift) {
		game.successMusic.play();
		this.giftSuccess(gift);
		},null,this);
	},
	handleOrientation: function(e) {
	var gamma = e.gamma;
    var y = e.beta;
    if (car.x < 160) {
    	car.x = 160;
    	gamma = 0;
    } else if (car.x > 450) {
    	car.x = 450;
    	gamma = 0;
    }
    car.body.velocity.x = gamma*14;
    //car.velocity.y += y;
	},
	moveCar: function(e){
	if(car.canMove){
	car.canMove = false;
	var steerTween = game.add.tween(car).to({
		angle: 20
		}, carTurnSpeed / 2, Phaser.Easing.Linear.None, true);
	steerTween.onComplete.add(function(){
			var steerTween = game.add.tween(car).to({
			angle: 0
			}, carTurnSpeed / 2, Phaser.Easing.Linear.None, true);
			});
	steerTween.onComplete.add(function(){
		car.canMove = true;
	})
	}
	},
	showPreOrderForm: function() {
		$(".submitOk").show();
		$(".Gameover").show();
		$(".preOrderForm").fadein();
	},
	targetFail: function(club) {
		if (leftTimes<=0) {
			$(".Gameover .btn_again").hide();
			$(".Gameover .btn_preOrder").show();
		}
		$(".Gameover").fadeIn(500,function(){
			$(".leftTimes span").html("0" + leftTimes);
		});
		window.removeEventListener("deviceorientation", this.handleOrientation, true);
//		game.input.keyboard.removeKey(Phaser.Keyboard.Z);
//		game.input.keyboard.removeKey(Phaser.Keyboard.X);
		car.smokeEmitter.on = false;
//		cars[1].smokeEmitter.on = false;
		game.time.events.remove(this.targetLoop);
		game.tweens.removeAll();
//
		for (var i=0; i < this.clubGroup.length; i++) {
			this.clubGroup.getChildAt(i).body.velocity.y = 0;
			this.clubGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.ticketGroup.length; i++) {
			this.ticketGroup.getChildAt(i).body.velocity.y = 0;
			this.ticketGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.specialGroup.length; i++) {
			this.specialGroup.getChildAt(i).body.velocity.y = 0;
			this.specialGroup.getChildAt(i).body.velocity.x = 0;
		};
//		game.input.onDown.remove(this.moveCar,this);
		var explosionEmitter = game.add.emitter(club.x,club.y,200);
		explosionEmitter.makeParticles("partcital");
		explosionEmitter.gravity = 0;
		explosionEmitter.setAlpha(0.2,1);
		explosionEmitter.minParticleScale = 0.5;
		explosionEmitter.maxParticalScale=3;
		explosionEmitter.start(true,3000,null,500);
//		explosionEmitter.forEach(function(p) {
//			p.tint = t.tint;
//		});
		club.destroy();
		car.body.velocity.y = 0;
		//car.destroy();
//		this.bgMuisc.stop();
//		var explosionSound = game.add.audio("explosion");
//		explosionSound.play();
//		game.time.events.add(Phaser.Timer.SECOND*2,function(){
//			game.state.start("GameOverScreen");
//		},this);
		game.state.start("GameOverScreen");
},
	ticketSuccess:function(ticket) {
		$(".successCopy span").html(selectedClubCNStr);
		$(".successA").fadeIn();
		window.removeEventListener("deviceorientation", this.handleOrientation, true);
//		game.input.keyboard.removeKey(Phaser.Keyboard.Z);
//		game.input.keyboard.removeKey(Phaser.Keyboard.X);
		car.smokeEmitter.on = false;
//		cars[1].smokeEmitter.on = false;
		game.time.events.remove(this.targetLoop);
		game.tweens.removeAll();
//
		for (var i=0; i < this.clubGroup.length; i++) {
			this.clubGroup.getChildAt(i).body.velocity.y = 0;
			this.clubGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.clubGroup.length; i++) {
			this.clubGroup.getChildAt(i).body.velocity.y = 0;
			this.clubGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.ticketGroup.length; i++) {
			this.ticketGroup.getChildAt(i).body.velocity.y = 0;
			this.ticketGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.specialGroup.length; i++) {
			this.specialGroup.getChildAt(i).body.velocity.y = 0;
			this.specialGroup.getChildAt(i).body.velocity.x = 0;
		};
//		game.input.onDown.remove(this.moveCar,this);
//		var explosionEmitter = game.add.emitter(club.x,club.y,200);
//		explosionEmitter.makeParticles("partcital");
//		explosionEmitter.gravity = 0;
//		explosionEmitter.setAlpha(0.2,1);
//		explosionEmitter.minParticleScale = 0.5;
//		explosionEmitter.maxParticalScale=3;
//		explosionEmitter.start(true,3000,null,500);
////		explosionEmitter.forEach(function(p) {
////			p.tint = t.tint;
////		});
		ticket.destroy();
		car.body.velocity.y = 0;
		game.state.start("GameSuccess");
},
giftSuccess:function(gift) {
		$(".successCopy span").html(selectedClubCNStr);
		$(".successB").fadeIn();
		window.removeEventListener("deviceorientation", this.handleOrientation, true);
//		game.input.keyboard.removeKey(Phaser.Keyboard.Z);
//		game.input.keyboard.removeKey(Phaser.Keyboard.X);
		car.smokeEmitter.on = false;
//		cars[1].smokeEmitter.on = false;
		game.time.events.remove(this.targetLoop);
		game.tweens.removeAll();
//
		for (var i=0; i < this.clubGroup.length; i++) {
			this.clubGroup.getChildAt(i).body.velocity.y = 0;
			this.clubGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.clubGroup.length; i++) {
			this.clubGroup.getChildAt(i).body.velocity.y = 0;
			this.clubGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.ticketGroup.length; i++) {
			this.ticketGroup.getChildAt(i).body.velocity.y = 0;
			this.ticketGroup.getChildAt(i).body.velocity.x = 0;
		};
		for (var i=0; i < this.specialGroup.length; i++) {
			this.specialGroup.getChildAt(i).body.velocity.y = 0;
			this.specialGroup.getChildAt(i).body.velocity.x = 0;
		};
//		game.input.onDown.remove(this.moveCar,this);
//		var explosionEmitter = game.add.emitter(club.x,club.y,200);
//		explosionEmitter.makeParticles("partcital");
//		explosionEmitter.gravity = 0;
//		explosionEmitter.setAlpha(0.2,1);
//		explosionEmitter.minParticleScale = 0.5;
//		explosionEmitter.maxParticalScale=3;
//		explosionEmitter.start(true,3000,null,500);
////		explosionEmitter.forEach(function(p) {
////			p.tint = t.tint;
////		});
		gift.destroy();
		car.body.velocity.y = 0;
		game.state.start("GameSuccessGift");
}
}

var GameSuccess = function(game) {};
GameSuccess.prototype = {
	create: function() {
		var bg = game.add.tileSprite(0,0,game.width,game.height,'gameBg');
		var bridge = game.add.sprite(game.width/2,game.height/2,'bridge');
		bridge.anchor.set(0.5);
		var scoreBoard =  game.add.sprite(30,30,"scoreBoard");
		bg.autoScroll(0,250);
	}
}

var GameSuccessGift = function(game) {};
GameSuccessGift.prototype = {
	create: function() {
		var bg = game.add.tileSprite(0,0,game.width,game.height,'gameBg');
		var bridge = game.add.sprite(game.width/2,game.height/2,'bridge');
		bridge.anchor.set(0.5);
		var scoreBoard =  game.add.sprite(30,30,"scoreBoard");
		bg.autoScroll(0,250);
	}
}

var GameOverScreen = function(game) {};
GameOverScreen.prototype = {
	create: function() {
		var bg = game.add.tileSprite(0,0,game.width,game.height,'gameBg');
		var bridge = game.add.sprite(game.width/2,game.height/2,'bridge');
		bridge.anchor.set(0.5);
		var scoreBoard =  game.add.sprite(30,30,"scoreBoard");
		bg.autoScroll(0,250);
	}
}

Target = function (game,lane,type) {
	if (lane == "channel1") {
		offX = -25;
		start = 240;
	} else if (lane == "channel2") {
		offX = 0;
		start = 320;
	} else {
		offX = 25;
		start = 400;
	}
	
	
	Phaser.Sprite.call(this,game,start,-30,type);
	game.physics.enable(this,Phaser.Physics.ARCADE);
	this.anchor.set(0.5);
	this.body.velocity.y = targetSpeed;
	this.body.velocity.x = offX;
}

Target.prototype = Object.create(Phaser.Sprite.prototype);
Target.prototype.constructor = Target;

Target.prototype.update = function() {
//	if (this.y > game.height - this.height/2 && this.mustPickup) {
//		this.missed.dispatch(this);
//	}
	if (this.y > game.height + this.height/2) {
		this.destroy();
	}
}
 

function initClubName(nn) {
	ticket = "colored_" + selectedClub;
	switch (nn) {
		case "shenhua":
		selectedClubCNStr = "绿地申花";
		$(".successAForm .inputCity").html("<option selected='selected'>上海福银</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>浦东新区川桥路200号</option>")
		break;
		
		case "hengda":
		selectedClubCNStr = "广州恒大";
		$(".successAForm .inputCity").html("<option selected='selected'>华驰福威</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>天河区广汕公路上元岗广州汽车市场自编A2</option>")
		break;
		
		case "fuli":
		selectedClubCNStr = "广州富力";
		$(".successAForm .inputCity").html("<option selected='selected'>华驰福威</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>天河区广汕公路上元岗广州汽车市场自编A2</option>");
		break;
		
		case "suning":
		selectedClubCNStr = "江苏苏宁";
		$(".successAForm .inputCity").html("<option selected='selected'>南京福联</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>南京市大明路286号</option>");
		break;
		
		case "luneng":
		selectedClubCNStr = "山东鲁能";
		$(".successAForm .inputCity").html("<option selected='selected'>山东顺骋</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>济南市历下区工业南路83号 </option>");
		break;
		
		case "guoan":
		selectedClubCNStr = "北京国安";
		$(".successAForm .inputCity").html("<option selected='selected'>北京长福新港</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>北京市朝阳区太阳宫中路甲3号</option>");
		break;
		
		case "shanggang":
		selectedClubCNStr = "上海上港";
		$(".successAForm .inputCity").html("<option selected='selected'>上海协通</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>上海市闸北区共和新路3200号</option>");
		break;
		case "lvcheng":
		selectedClubCNStr = "杭州绿城";
		$(".successAForm .inputCity").html("<option selected='selected'>浙江万国</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>杭州市沈半路192号</option>");
		break;
		case "yongcang":
		selectedClubCNStr = "石家庄永昌";
		$(".successAForm .inputCity").html("<option selected='selected'>河北天和福昌</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>石家庄市南二环与体育大街交口东行100米</option>");
		break;
		case "taida":
		selectedClubCNStr = "天津泰达";
		$(".successAForm .inputCity").html("<option selected='selected'>天津柯兰德</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>天津市河东区卫国道236号</option>");
		break;
		case "huaxia":
		selectedClubCNStr = "河北华夏";
		$(".successAForm .inputCity").html("<option selected='selected'>秦皇岛金时</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>秦皇岛经济技术开发区新管委西行3公里路北</option>");
		break;
		case "hongyun":
		selectedClubCNStr = "辽宁宏运";
		$(".successAForm .inputCity").html("<option selected='selected'>辽宁盛世安特</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>辽宁省沈阳市铁西区北二东路18-1号</option>");
		break;
		case "yanbian":
		selectedClubCNStr = "延边富德";
		$(".successAForm .inputCity").html("<option selected='selected'>延吉冀东</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>吉林省延吉市开发区长白山东路3798-1号</option>");
		break;
		case "yatai":
		selectedClubCNStr = "长春亚泰";
		$(".successAForm .inputCity").html("<option selected='selected'>吉林神驰</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>吉林省长春市硅谷大街3500号</option>");
		break;
		case "lifan":
		selectedClubCNStr = "重庆力帆";
		$(".successAForm .inputCity").html("<option selected='selected'>重庆安福</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>重庆市渝北区龙溪红锦大道460号</option>");
		break;
		case "jianye":
		selectedClubCNStr = "河南建业";
		$(".successAForm .inputCity").html("<option selected='selected'>河南天道</option>");
		$(".successAForm .inputDealer").html("<option selected='selected'>河南省郑州市郑花路96号</option>");
		break;
	}
}


function getleftTimes() {
         var url = 'http://yiche.changanfordwechat.com/ocms/toolkit/luckydraw/log/countByCurrentWeekJSONP.htm';
         var dtd = $.Deferred(); 
		 $.ajax({
		             url:url,
		             type: 'GET',
		             dataType:"jsonp",
		             //async: false,
		             //contentType: "application/json",
		             //jsonpCallback: 'jsonCallback',
		             data:{"wechatId":openId},
		             jsonp:"jsonpcallback",
		             success:function(data){
		         		dtd.resolve(data);
		             }
		       });         
        return dtd;   
// var submitData={};
//	    submitData["address"]="xxx";
//      $.ajax({
//           url:'http://www.changanfordclub.com/Media2Ford/commitDataJSONP.action',
//           type : 'post',
//           data:submitData,
//           dataType:"jsonp",
//           jsonp:"jsonpcallback",
//           success:function(data){
//              alert(data.status);
//           }
//      });
}

function addTimes() {
	     var url = 'http://yiche.changanfordwechat.com/ocms/toolkit/luckydraw/doDrawForWehcatUserJSONP.htm';
         var dtd = $.Deferred();
        $.ajax({
           type: 'GET',
            url: url,
            //async: false,
          //  jsonpCallback: 'jsonCallback',
            contentType: "application/json",
            dataType: 'jsonp',
            data:{"wechatId":openId,"code":selectedClub.toUpperCase()},
            jsonp:"jsonpcallback",
            success: function(result) {
                //dtd.resolve(result);
               console.log(result);
            },
            error: function(e) {
               console.log(e.message);
            }
        });
        return dtd;
}

function addInfo(name,mobile,city,dealer) {
	var url = 'http://yiche.changanfordwechat.com/ocms/baseframework/security/user/addOrUpdateCarOwnerUserJSONP.htm';
        var dtd = $.Deferred(); 
        $.ajax({
           type: 'GET',
            url: url,
            //async: false,
          //  jsonpCallback: 'jsonCallback',
            contentType: "application/json",
            dataType: 'jsonp',
            data:{
            	"wechatId":openId,
            	"realName":name,
            	"mobilePhoneNo":mobile,
            	"company":dealer,
            	"nickname":selectedClubCNStr
            		},
            jsonp:"jsonpcallback",
            success: function(result) {
                //dtd.resolve(result);
                $(".successAForm").fadeOut();
                $(".submitOk").fadeIn();
               console.log(result);
            },
            error: function(e) {
               console.log(e.message);
            }
        });
        return dtd;
}

function addInfo2(name,mobile,address) {
	var url = 'http://yiche.changanfordwechat.com/ocms/baseframework/security/user/addOrUpdateCarOwnerUserJSONP.htm';
        var dtd = $.Deferred(); 
        $.ajax({
           type: 'GET',
            url: url,
            //async: false,
          //  jsonpCallback: 'jsonCallback',
            contentType: "application/json",
            dataType: 'jsonp',
            data:{
            	"wechatId":openId,
            	"realName":name,
            	"mobilePhoneNo":mobile,
            	"address":address,
            	"nickname":selectedClubCNStr
            		},
            jsonp:"jsonpcallback",
            success: function(result) {
                //dtd.resolve(result);
                block = false;
                $(".successBForm").fadeOut();
                $(".submitOk").fadeIn();
               console.log(result);
            },
            error: function(e) {
               console.log(e.message);
            }
        });
        return dtd;
}

function getScore() {
         var url = 'http://shell.agenda-sh.com/api/site/getPrize';
         var dtd = $.Deferred(); 
        $.ajax({
           type: 'GET',
            url: url,
            async: false,
          //  jsonpCallback: 'jsonCallback',
            contentType: "application/json",
            dataType: 'jsonp',
            data:{gallery_id:gallery_id},
            success: function(result) {
                dtd.resolve(result);
               //console.log(result);
            }
        });
        return dtd;        
}

function addScore(score) {
         var url = 'http://shell.agenda-sh.com/api/site/score';
         var dtd = $.Deferred(); 
        $.ajax({
           type: 'GET',
            url: url,
            async: false,
          //  jsonpCallback: 'jsonCallback',
            contentType: "application/json",
            dataType: 'jsonp',
            data:{score:score},
            success: function(result) {
                dtd.resolve(result);
               //console.log(result);
            },
            error: function(e) {
               console.log(e.message);
            }
        });
        return dtd;        
}



	$(".btn_again ").on("touchstart",function(){
			timeSecond = 60;
			leftTimes = leftTimes -1;
			console.log(leftTimes);
			if (leftTimes < 0) {
				game.state.start("GameOverScreen");
				$(".leftTimes span").html("00");
				$(".Gameover .btn_again").hide();
				$(".Gameover .btn_preOrder").show();
				$(".Gameover").fadeIn();
				$(".Gameover .btn_preOrder").on("touchstart",this.showPreOrderForm);
			} else {
				$(".Gameover").fadeOut();
				$(".leftTimesTxt").html("0" + leftTimes)
				addTimes();
				game.state.start("playGame");
			}
			});
	$(".btn_playAgain").on("touchstart",function(){
			timeSecond = 60;
			leftTimes = leftTimes -1;
			console.log(leftTimes);
			if (leftTimes < 0) {
				alert("不好意思，本周游戏次数已经用完");
//				game.state.start("GameOverScreen");
//				$(".leftTimes span").html("00");
//				$(".Gameover .btn_again").hide();
//				$(".Gameover .btn_preOrder").show();
//				$(".Gameover").fadeIn();
//				$(".Gameover .btn_preOrder").on("touchstart",this.showPreOrderForm);
			} else {
				$(".successA").fadeOut();
				$(".successB").fadeOut();
				$(".leftTimesTxt").html("0" + leftTimes);
				addTimes;
				game.state.start("playGame");
			}
			})
	$(".successA .btn_setForm").on("touchstart",function(){
		$(".successA").fadeOut();
		$(".successAForm").fadeIn();
	})
	$(".successB .btn_setForm").on("touchstart",function(){
		$(".successB").fadeOut();
		$(".successBForm").fadeIn();
	})
//	$(".successAForm .btn_submit").on("touchstart",function(){
//		$(".successAForm").fadeOut();
//		$(".submitOk").fadeIn();
//	})
//	$(".successBForm .btn_submit").on("touchstart",function(){
//		$(".successBForm").fadeOut();
//		$(".submitOk").fadeIn();
//	})
	$('.btn_preOrder').on("touchstart",function(){
		$(".submitOk").fadeOut();
		$(".preOrderForm").fadeIn();
	});
//	$('.preOrderForm .btn_submit').on("touchstart",function(){
//		$(".preOrderForm").fadeOut();
//		$(".shareLay").fadeIn();
//	});
	$(".ruleBtn").on("touchstart",function(){
		$(".ruleLay").fadeIn();
	})
	$(".ruleBg").on("touchstart",function(){
		$(".ruleLay").fadeOut();
	})
	$(".successAForm .btn_submit").on("touchstart",function(){
		var mobile = $('.successAForm .inputMobi').val();
		var name = $('.successAForm .inputName').val();
		var city = $(".successAForm .inputCity").find("option:selected").text();
		var dealer = $(".successAForm .inputDealer").find("option:selected").text();
		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if(block == false){
			block = true;
			
			addInfo(name,mobile,city,dealer);
		}
	});
	$(".successBForm .btn_submit").on("touchstart",function(){
		var mobile = $('.successBForm .inputMobi').val();
		var name = $('.successBForm .inputName').val();
		var address = $(".successBForm .inputAdd").val();
		if(mobile == '' || !(/^1[34578]\d{9}/.test(mobile)) ){
			alert('请输入正确的手机号！')
			return false;
		}
		if(name==''){
			alert('请输入您的姓名！')
			return false;
		}
		if(address==''){
			alert('请输入您的收货地址！')
			return false;
		}
		if(block == false){
			block = true;
			addInfo2(name,mobile,address);
		}
	})
	
	
//	$(".go").on("touchstart",function(){
//		$(".loader").fadeOut();
//		leftTimes = leftTimes -1;
//		if (leftTimes < 0) {
//			game.state.start("GameOverScreen");
//			$(".leftTimes span").html("00");
//			$(".Gameover").fadeIn();
//		} else {
//			$(".leftTimesTxt").html("0" + leftTimes)
//			game.state.start("playGame");
//		}
//	});
	
	
	
	
	
function playMusic() {
        audioElement = new Audio('assets/sound/bgmusic.mp3');
        audioElement.controls = false;
        audioElement.loop = true;
        audioElement.autoplay = true;
        audioElement.load();
        audioElement.play();
    }
//var clubArray = ['shenhua','hengda','fuli','suning','luneng','guoan','shanggang','lvcheng','yongcang','taida','jianye','huaxia','hongyun','yanbian','yatai','lifan'];