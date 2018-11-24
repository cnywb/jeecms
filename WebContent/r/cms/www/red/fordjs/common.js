 $.noConflict();
 jQuery(document).ready(function($) {  
    $("#tabs h1:first").next("div").show().end().addClass("current");
	$("#tabs h1").click(function(){		
	    $(this).parent().siblings().children("h1").removeClass("current")
		$(this).addClass("current").next("div").slideDown();
		$(this).parent().siblings().children("div").hide();
	})
	$("#popUpResult li.panes h2:first").css({'margin-top':2});
	$(".RSelectBtn b").click(function(){
		$(".RSelect").toggleClass("hide");
	})
	$(".RSelect dd").hover(function(){
		var $this=$(this);
		$this.toggleClass("hover");
	});	
	$(".RSelect dd").click(function(){
		var $this=$(this);
		var $index=$this.index();
		var $text=$this.text();
		$(".RSelect").addClass("hide");
		$(".RSelect dd").removeClass("hide");
		$this.addClass("hide");
		$(".RSelectBtn b").text($text);	
		$(".panes dl").addClass("hide").eq($index).removeClass("hide").find("h2:first").css({'margin-top':2});;
	})
	
	$("#winning a").click(function(){
		var $this=$(this);
		var $rel=$this.attr("rel");
		$('#popUpMask').removeClass('hide');
        $('#popUpWinning').removeClass('hide');
		$("#popUpWinning ul").addClass("hide");
		$("#"+$rel).removeClass("hide");
		$("#popUpWinning .tabs a").removeClass("current");
		$("#popUpWinning .tabs a."+$rel).addClass("current");
	});
	$(".popUpClose").click(function(){
		$('#popUpWinning').addClass('hide');
		$('#popUpMask').addClass('hide');
	})
	$("#bVin").click(function(){
		var $vinVal=$("#vin").val();
		if($vinVal==""){
			alert("请输入一个关键字");
			return false;
		}
	});
		
});

