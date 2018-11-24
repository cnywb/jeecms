var a,s;

searchBtn1 = document.querySelector('.info-pg .btnsearch'),
searchBtn2 = document.querySelector('.info-pg .btnvin'),
popmsgcls = document.querySelector('.popupmsg .msgbg'),
poptbcls = document.querySelector('.popuptable .cls'),
submitBtn = document.querySelector('.info-pg .btnsearchst');
/*延长质保*/


$(document).ready(function(){
	initcarModelList();
	$("#btn_introduce").click(function(){
		showContent();
	});
	
	$("#btn_start").click(function(){
		showIntroduce();
	});
	
	
	$("#btn_how_to_get_vin").on('click',function(){
		$('#content').hide();
	    var dheight = $(document).height();
	    $('.popup').css('height',dheight);
		$(".popup").fadeIn();
	});
	$("#backBtn").click(function(){
		$('#content').show();
		$(".popup").fadeOut();
	});
	//TOUCH点击 fastClick方法 Start
function fastClick(el, callback) {
	var handler = {
		handleEvent: function(ev) {
			switch (ev.type) {
				case 'touchstart':
				case 'mousedown':
					this.start(ev)
					break
				case 'touchend':
				case 'touchcancel':
				case 'mouseup':
					this.end(ev)
			}
		},
		start: function(ev) {
			var touch = ev.changedTouches ? ev.changedTouches[0] : ev
			this.clientX = touch.clientX
			this.clientY = touch.clientY
			this.touchTime = +(new Date)
		},
		end: function(ev) {
			if ((typeof this.touchTime != 'undefined') &&
					(+(new Date) - this.touchTime < 1000)) {
				var touch = ev.changedTouches ? ev.changedTouches[0] : ev
				if ((Math.abs(touch.clientX - this.clientX) < 10 &&
						Math.abs(touch.clientY - this.clientY) < 10)) {
					callback && callback(ev)
				}
				this.clientX = this.clientY = this.touchTime = undefined
			}
		}
	};

	var events = ('ontouchstart' in window) ? 
						['touchstart', 'touchend', 'touchcancel'] : 
						['mousedown', 'mouseup']
	events.forEach(function(ev) {
		el.addEventListener(ev, handler, false)
	});
}

//TOUCH点击 fastClick方法 End

	function initHeight(){
		a = document.documentElement.clientHeight,
		s = document.documentElement.clientWidth; 
		console.info('clientHeight:' + a  + '  clientWidth:' + s);
	};

	initHeight();

    fastClick(searchBtn1,queryPackageOrder);
    fastClick(submitBtn,queryPackage);

    fastClick(popmsgcls ,closemsg);
	fastClick(poptbcls,closetb);

	function closemsg(){
             $(".popupmsg").fadeOut();
	};

 function  closetb(){
       $(".popuptable").fadeOut();
}

	
	function collapse1(){
		
		$('#collapse-panel-1').collapse('toggle');
	}

	function collapse2(){
		$('#collapse-panel-2').collapse('toggle');
	}


	$('#collapse-panel-1').on('open.collapse.amui', function() {
	 	$('.btnsearch span').removeClass('am-icon-chevron-down');
	 	$('.btnsearch span').addClass('am-icon-chevron-up');
	 	
	 

	}).on('close.collapse.amui', function() {
	  	$('.btnsearch span').addClass('am-icon-chevron-down');
	 	$('.btnsearch span').removeClass('am-icon-chevron-up');
	});


	$('#collapse-panel-2').on('open.collapse.amui', function() {
	 	$('.btnvin span').removeClass('am-icon-chevron-down');
	 	$('.btnvin span').addClass('am-icon-chevron-up');

	}).on('close.collapse.amui', function() {
	  	$('.btnvin span').addClass('am-icon-chevron-down');
	 	$('.btnvin span').removeClass('am-icon-chevron-up');
	});

	

	
	function showHint(){
		$('#collapse-panel-2').show();
	}

});


function showContent(){
	$('#introduce').show();
	$('#content').hide();
}

function showIntroduce(){
	$('#introduce').hide();
	$('#content').show();
}

function queryPackageOrder(){
	var vin=$("#doc-vin").val();
	if(vin==""){
		 
                 $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('请输入VIN码！');
              return;
	 }

 if(validateVin(vin)==false){
            $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('VIN码格式不正确！');
              return;
}
	$.ajax( {
		url :'/maintenance/package/order/findAllForSSP.jspx',
		type : 'post',
		dataType:'json',
		data:{"vin":vin},
		success : function(result) {
		$('#collapse-panel-1').show();
	        if(result.length==0){
                        setCarModelSelect(vin);
	        	$("#collapse-panel-1").html("您尚未购买长安福特原厂保养套餐。点击“<a href=\"javascript:;\" onclick=\"showContent();\">什么是长安福特原厂保养套餐?</a>”查看长安福特原厂保养套餐介绍。");
	        	$("#collapse-panel-2").show();
	        }else{
	        	$("#collapse-panel-2").hide();
                         var message="";
                         for(var i=0;i<result.length;i++){
                           message=message+'您车架号为'+result[i].vin+'的'+result[i].model+'，已经在'+result[i].dealerName+'购买'+getProductName(result[i].product)+',合同有效期为'+result[i].contractStartDate+'至'+result[i].contractEndDate+'，共计可使用'+getUseQty(result[i].product)+'次。';
	        	   message=message+'<br>'
                         }
	        	$("#collapse-panel-1").html(message);
	        }
		},error:function(){
	      //alert('注意:网络异常！');
                 $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('注意:网络异常！');
	    }
	});
}

function getProductName(product){
	return product.substring(0,product.indexOf(" "));
}
var dic={"“五福贺新春”赠送特惠3次基础保养":3,
"高级保养套餐 赠送套餐 第一次购买":5,
"高级保养套餐 赠送套餐 买4赠1 24个月":5,
"高级保养套餐 赠送套餐 买7赠2 40个月":9,
"基础保养套餐 赠送套餐 第一次购买":5,
"基础保养套餐 赠送套餐 买4赠1 24个月":5,
"基础保养套餐 赠送套餐 买7赠2 40个月":9,
"金牛座老车主再购及推荐获赠：1次免费保养":1,
"新高级保养套餐 16个月3次 第二次购买":3,
"新高级保养套餐 16个月3次 第一次购买":3,
"新高级保养套餐 24个月5次 第二次购买":5,
"新高级保养套餐 24个月5次 第一次购买":5,
"新高级保养套餐 32个月7次 第二次购买":7,
"新高级保养套餐 32个月7次 第一次购买":7,
"新基础保养套餐 16个月3次 第二次购买":3,
"新基础保养套餐 16个月3次 第一次购买":3,
"新基础保养套餐 24个月5次 第二次购买":5,
"新基础保养套餐 24个月5次 第一次购买":5,
"新基础保养套餐 32个月7次 第二次购买":7,
"新基础保养套餐 32个月7次 第一次购买":7,
"翼虎 新福克斯召回活动获赠：5次免费保养":5,
"长安福特高级保养套餐 16个月3次":3,
"长安福特高级保养套餐 16个月3次 第二次购买":3,
"长安福特高级保养套餐 24个月5次":5,
"长安福特高级保养套餐 24个月5次 第二次购买":5,
"长安福特高级保养套餐 32个月7次":7,
"长安福特高级保养套餐 32个月7次 第二次购买":7,
"长安福特基础保养免费赠送":5,
"长安福特基础保养套餐 16个月3次":3,
"长安福特基础保养套餐 16个月3次 第二次购买":3,
"长安福特基础保养套餐 24个月5次":5,
"长安福特基础保养套餐 24个月5次 第二次购买":5,
"长安福特基础保养套餐 32个月7次":7,
"长安福特基础保养套餐 32个月7次 第二次购买":7,
"长安福特电话直销保养套餐 6个月":1,
"长安福特电话直销保养套餐 18个月":3};
function getUseQty(product){
      return dic[product];
}
function queryPackage(){
	var carModel=$("#carModel").val();
	$.ajax( {
		url :'/maintenance/package/find.jspx',
		type : 'post',
		dataType:'json',
		data:{"carModel":carModel,"packageType":"SSP"},
		success : function(result) {
			 $("#package_list").empty(); 
		   for(var i=0;i<result.length;i++){
			   $("#package_list").append('<tr><td>'+result[i].type+'</td><td>'+result[i].name+'</td><td>'+(result[i].price*1).toFixed(2)+'元</td></tr>');
		   }

                   $(".popuptable").fadeIn();
                   
		},error:function(){
	      //alert('注意:网络异常！');
                 $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('注意:网络异常！');
	    }
	});
}


var carModelList=[];
function initcarModelList(){
$("#carModel option").each(function(){
	carModelList.push($(this).val());
}
);
}
function setCarModelSelect(vin){
	var carModel=getModelFromVin(vin);
	if(carModel==""){
		setCarModelOptions(carModelList);
	}else{
		var temCarModelList=[];
		for(var i=0;i<carModelList.length;i++){
			var val=carModelList[i];
			if(val.indexOf(carModel)!=-1){
				 temCarModelList.push(val);
			 }
		 }
		setCarModelOptions(temCarModelList);
	}
}

function setCarModelOptions(carModelList){
	$("#carModel").empty();
	for(var i=0;i<carModelList.length;i++){
		var val=carModelList[i];
		$("#carModel").append('<option value="'+val+'">'+val+'</option>');
	}
}

function getModelFromVin(vin){
	vin=vin.toUpperCase();
	var vin5=vin.substring(4,5);
	var vin11=vin.substring(10,11);
        var vin8=vin.substring(7,8);
	
	if(vin5=="F"&&vin11=="N"){
		return "嘉年华";
	}
	if(vin5=="K"&&vin11=="F"){
		return "翼博";
	}
	if(vin5=="C"&&vin11=="F"){
		return "福克斯";
	}
	if(vin5=="C"&&vin11=="E"){
		return "新福克斯";
	}
	if(vin5=="F"&&(vin11=="E"||vin11=="S")){
		return "福睿斯";
	}
	if(vin5=="B"&&vin11=="F"){
		return "致胜";
	}
	if(vin5=="F"&&vin11=="H"){
		return "锐界";
	}
	if(vin5=="J"&&vin11=="E"){
		return "翼虎";
	}
	if(vin5=="F"&&vin11=="F"&&(vin8=="C"||vin8=="L")){
		return "新蒙迪欧";
	}
	
	if(vin5=="F"&&vin11=="F"&&vin8=="F"){
		return "麦柯斯";
	}
        if(vin5=="M"&&vin11=="H"){
		return "金牛座";
	}

	return "";
}

function validateVin(vin){
		if(vin.length!=17){
			return false;
		}
		var prefix=vin.substring(0,3).toUpperCase();
		if(prefix!="LVS"&&prefix!="LVR"){
			return false;
		}
		
		var endstr=vin.substring(11,17);
		var reg1=/^\d+$/;
		var reg2=/^[A-Za-z]+$/;
		if(!endstr.match(reg1)){
			return false;
		}
		var middlestr=vin.substring(10,11);
		if(!middlestr.match(reg2)){
			return false;
		}
		return true;
	}
