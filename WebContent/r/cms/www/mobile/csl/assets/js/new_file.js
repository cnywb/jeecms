<script>
	var isIphone = navigator.userAgent.toLowerCase().indexOf('iphone') >= 0;
	// var sound = document.createElement('audio');
	var ld=vd=0;
	
	document.addEventListener('touchmove',function(e){
		e.preventDefault();
	})
	// sound.src = 'media/sound.mp3';
	playOver();	
	if (isIphone) {
	    var canvasVideo = new CanvasVideoPlayer({
			videoSelector: '.js-video',
			canvasSelector: '.js-canvas',
			hideVideo: true,
			audio: true,
		});
	}else {
		vd = 1;
	    $('canvas').hide();
	    $('.video').remove();
	    $('.video-responsive').append('<video src="media/final.mp4" id="video" width="0" height="0" x-webkit-airplay="true" webkit-playsinline="true" preload="auto" style="background:transparent;"></video>')

		$('#video').get(0).load();
		// $('#video').get(0).play();
		$('#video').get(0).addEventListener('canplaythrough',function(){
			
	    })

	    $('#video').on('ended',function(){
	    	showBtn();
	    	$('#video').remove();
	    })

	}
	
	function playOver(){
		$('#load1').transition({'opacity':1,delay:1000},500)
		$('#load2').transition({'opacity':1,delay:2000},500)
		$('#load3').transition({'opacity':1,delay:2000},500)
		$('#load4').transition({'opacity':1,delay:3000},function(){
			ld=1
			if(vd==1){
				$('#load5').transition({'opacity':1,delay:2000},function(){
					$('#loading').hide();
				})
			}
		})		
	}
	// function playOver(){
	// 	// $('#btn').show();
	// }
	function showBtn(){
		$('#link').show();
	}

	$('#masker').hammer().on('panup',function(e){
		//上滑
		var y = e.gesture.distance;
		// console.log(y)
		if(y>180){
			y = 180;
		}
		$('#masker>img').css({y:-y});
	})
	$('#masker').on('touchend',function(){
		$('#masker>img').transition({y:0},300,function(){
			$('#masker').fadeOut();
			
		});
		if(canvasVideo){
			canvasVideo.play();
		}else{
			$('#video').width(640).height(1040).get(0).play();
		}
	})

	$('#link a').on('touchend',function(){
		var href = $(this).data('href');
		location.href = href;
	})
	// $('#loading').on('touchend',function(){
	// 	location.reload(true);
	// })

	document.addEventListener('touchmove',function(e){
		e.preventDefault();
	})
</script>