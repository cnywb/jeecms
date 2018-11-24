var a,s;
/*保养套餐*/
startBtn = document.querySelector('.inityc-pg .btninit'),
searchBtn1 = document.querySelector('.infoyc-pg .btnsearch'),
searchBtn2 = document.querySelector('.infoyc-pg .btnvin'),

popmsgcls = document.querySelector('.popupmsg .msgbg'),
submitBtn = document.querySelector('.infoyc-pg .searchbtn');

/*延长质保*/


$(document).ready(function(){
	$("#btn_introduce").click(function(){
		showContent();
	});
	$("#btn_start").click(function(){
		showIntroduce();
	});
	initBuyYear('year');
	initBuyYear('p_year');
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
						['mousedown', 'mouseup'];
	events.forEach(function(ev) {
		el.addEventListener(ev, handler, false);
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
 
	
	fastClick(popmsgcls ,closemsg);
	
	function closemsg(){
             $(".popupmsg").fadeOut();
	};
	
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
	
	
	
	
	


});


function showContent(){
	$('#introduce').show();
	$('#content').hide();
}

function showIntroduce(){
	$('#introduce').hide();
	$('#content').show();
}

var packageOrderList=[];
function queryPackageOrder(){
	var vin=$("#doc-vin").val();
	if(vin==""){
		 //alert('请输入VIN码！');
                 $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('请输入VIN码！');
                 $(".pupupft").html("");
                 return;
	}
if(validateVin(vin)==false){
            $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('VIN码格式不正确！');
  $(".pupupft").html("");
              return;
}

	$.ajax( {
		url :'/maintenance/package/order/findAllForEW.jspx',
		type : 'post',
		dataType:'json',
		data:{"vin":vin},
		success : function(result) {
			$('#collapse-panel-1').show();
	        if(result.length==0){
	        	$("#collapse-panel-1").html("您尚未购买长安福特原厂延长质保。点击<a href=\"javascript:;\" onclick=\"showContent();\">什么是长安福特原厂延长质保？</a>查看长安福特原厂延长质保详细介绍。");
	            $("#collapse-panel-2").hide(); 
	            $("#collapse-panel-3").show();
	        }else{
                        packageOrderList=getNeedPackageOrder(result);
                     
                       /* var message="";
                        for(var i=0;i<result.length;i++){
                          var checked='';
                          if(i==0){
                            checked='checked="true"';
                          }
                         message=message+'<input '+checked+'type="radio" name="product" value="'+result[i].id+'" id="raido_'+i+'"><label for="raido_'+i+'" >您车架号为'+result[i].vin+'的'+result[i].model+'，已经在'+result[i].dealerName+'购买'+result[i].product+'</label><br>';
                        }*/
                        
                        var message="";
                        for(var i=0;i<packageOrderList.length;i++){
                         message=message+'您车架号为'+packageOrderList[i].vin+'的'+packageOrderList[i].model+'，已经在'+packageOrderList[i].dealerName+'购买'+packageOrderList[i].product;
                        }   
                $("#collapse-panel-1").html(message);
	        	$("#collapse-panel-2").show(); 
	        	$('#collapse-panel-3').hide();
	        }
		},error:function(jqXHR, textStatus, errorThrown){
	         //alert('注意:请求异常！状态码'+textStatus);
                 $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('注意:请求异常！状态码'+textStatus);
                $(".pupupft").html("");
	    }
	});
}

function getNeedPackageOrder(packageOrderList){
	 var retVal=[];
	 for(var i=0;i<packageOrderList.length;i++){
        if(packageOrderList[i].fileName=='2015-55'){
        	retVal.push(packageOrderList[i]);
        }
     } 
	 if(retVal.length==0){
		 retVal.push(packageOrderList[0]);
	 }
	 return retVal;
}
function getPackageOrder(id){
   var retVal=null;
   for(var i=0;i<packageOrderList.length;i++){
     if(id==packageOrderList[i].id){
        retVal=packageOrderList[i];
     }
   }
   return retVal;
}

/**
 * 计算生效日期
 * @param buyMonths  购车月份 
 * @param hasOdometer 已行驶里程 
 * @param termMonths  延保月份 
 * @param termOdometer 延保里程
 * @param monthAverageOdometer 月平均行驶公里数
 */
function getEffectiveInfo(buyMonths,termMonths,termOdometer,hasOdometer,monthAverageOdometer,contractStartDate,contractEndDate){
	  $(".pupupft").html("");
	   if((parseInt(hasOdometer)!=hasOdometer)||parseInt(hasOdometer)<=0){
		    return "已行驶里程必须为大于0的整数!";
	    }
	    if((parseInt(monthAverageOdometer)!=monthAverageOdometer)||parseInt(monthAverageOdometer)<=0){
		   return "月平均行驶里程必须为大于0的整数!";
	    }
       $(".pupupft").html("此方案仅作参考，请咨询当地长安福特授权经销商。");
	hasOdometer=hasOdometer*1;
        monthAverageOdometer=monthAverageOdometer*1;
        if((48-buyMonths)<=0){
           return "根据您的驾驶习惯，您购买的延保将由于超过覆盖的公里数而失效";
        }
	if(termMonths==12&&termOdometer==100000){
		var result=hasOdometer+(48-buyMonths)*monthAverageOdometer;
                result=result*1;
		if(result>0&&result<100000){
			return "根据您的驾驶习惯，您购买的延保将在"+getContractEffetiveDate(contractStartDate,contractEndDate)+"生效";
		}else{
			return "根据您的驾驶习惯，您购买的延保将由于超过覆盖的公里数而失效";
		}
	}
	if(termMonths==24&&termOdometer==100000){
		var result=hasOdometer+(60-buyMonths)*monthAverageOdometer;
                result=result*1;
		if(result>0&&result<100000){
			return "根据您的驾驶习惯，您购买的延保将在"+getContractEffetiveDate(contractStartDate,contractEndDate)+"生效";
		}else{
			return "根据您的驾驶习惯，您购买的延保将由于超过覆盖的公里数而失效";
		}
	}
    if(termMonths==12&&termOdometer==20000){
    	       var result=hasOdometer+(48-buyMonths)*monthAverageOdometer;
                result=result*1;

		 if(result>0&&result<=120000){
			return "根据您的驾驶习惯，您购买的延保将在"+getContractEffetiveDate(contractStartDate,contractEndDate)+"生效";
		}else{
			return "根据您的驾驶习惯，您购买的延保将由于超过覆盖的公里数而失效";
		}
	}
    if(termMonths==24&&termOdometer==40000){
    	        var result=hasOdometer+(60-buyMonths)*monthAverageOdometer;
                result=result*1;
		if(result>0&&result<140000){
		return "根据您的驾驶习惯，您购买的延保将在"+getContractEffetiveDate(contractStartDate,contractEndDate)+"生效";
   }else{
 		return "根据您的驾驶习惯，您购买的延保将由于超过覆盖的公里数而失效";
   }
	}
}


function doGetEWPackagePromotion(){
	var hasOdometer=$("#p_termOdometer").val();
	$(".pupupft").html("");
	if((parseInt(hasOdometer)!=hasOdometer)||parseInt(hasOdometer)<=0){
	    	showAlert("已行驶里程必须为大于0的整数!");
	    	return;
	}
	var buyChance=getBuyChance();
     if(buyChance==undefined){
      $(".pupupft").html("此方案仅作参考，请咨询当地长安福特授权经销商。");
       showAlert("暂无合适的延保产品供您选择!");
	   return;
     }
	queryPackage(buyChance);
   	return;
}

function showAlert(message){
	  $(".popupmsg").fadeIn();
      $(".pupupmsgtxt").html(message);
}


function doGetEffectiveInfo(){
	var year=$("#year").val();
	 var month=$("#month").val();
	var buyMonths=getMonthAmount(year,month);
	var hasOdometer=$("#termOdometer").val();
	var monthAverageOdometer=$("#monthAverageOdometer").val();
     /*var oid=$('input[name="product"]:checked ').val();
     var packageOrder=getPackageOrder(oid);*/
    var packageOrder=packageOrderList[0];
	var message=getEffectiveInfo(buyMonths,packageOrder.termMonths,packageOrder.termOdometer,hasOdometer,monthAverageOdometer,packageOrder.contractStartDate,packageOrder.contractEndDate);
    $(".popupmsg").fadeIn();
    $(".pupupmsgtxt").html(message);
  
	return;
}

function initBuyYear(selectId){
	   var myDate = new Date();
	   var y=myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	   var offset=2012;
	   var yearDiffer=y*1-offset+1;
	   for(var i=0;i<yearDiffer;i++){
	    $("#"+selectId).append('<option value="'+(offset+i)+'">'+(offset+i)+'</li>');
	   }
}

/**
 * 得到月份数
 * @param year
 * @param month
 * @returns
 */
function getMonthAmount(year,month){
	   var endDate = new Date();
	   var startDate=year+"-"+month+"-"+"1";
	   startDate=new Date(startDate.replace(/-/g,'/'));
		
		var num=0;
		var year=endDate.getFullYear()-startDate.getFullYear();
		num+=year*12;
		var month=endDate.getMonth()-startDate.getMonth();
		num+=month;
		var day=endDate.getDate()-startDate.getDate();
		if(day>0){
		//if(day>15){ num+=1; }
		num+=1;
		}else if(day<0){
		//if(day<-15){num-=1; }
		//num-=1;
		}
		return num;
}

function getContractEffetiveDate(contractStartDate,contractEndDate){
	return (contractStartDate+"至"+contractEndDate);
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

function queryPackage(buyChance){
	var vin=$("#doc-vin").val();
	var carModel=getModelFromVin(vin);
	$.ajax( {
		url :'/maintenance/package/find.jspx',
		type : 'post',
		dataType:'json',
		data:{"carModel":carModel,"packageType":"EW"},
		success : function(result) {
			 result=getPackageListByBuyChance(result,buyChance);
			 $("#package_list").empty(); 
		   for(var i=0;i<result.length;i++){
			   $("#package_list").append('<tr><td>'+result[i].type+'</td><td>'+result[i].name+'</td><td>'+(result[i].price*1).toFixed(2)+'元</td></tr>');
		   }
		   $("#headTitle").html("您车型为"+carModel+"的车辆可购买以下延保方案");
           $(".popuptable").fadeIn();
                   
		},error:function(){
	      //alert('注意:网络异常！');
                 $(".popupmsg").fadeIn();
                 $(".pupupmsgtxt").html('注意:网络异常！');
	    }
	});
}

function getModelFromVin(vin){
	vin=vin.toUpperCase();
	var vin5=vin.substring(4,5);
	var vin11=vin.substring(10,11);
        var vin8=vin.substring(7,8);
	
	if(vin5=="F"&&vin11=="N"){
		return "新嘉年华";
	}
	if(vin5=="K"&&vin11=="F"){
		return "翼搏";
	}
	if(vin5=="C"&&vin11=="F"){
		return "经典福克斯";
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

function getBuyChance(){
	var year=$("#p_year").val();
	var month=$("#p_month").val();
	var buyMonths=getMonthAmount(year,month);
	var hasOdometer=$("#p_termOdometer").val();
	if(buyMonths<6){
		if(hasOdometer<5000){
			return "新购车";
		}
	}else if(buyMonths>=6&&buyMonths<30){
		if(hasOdometer>=5000&&hasOdometer<80000){
			return "在保车";
		}
	}
	
}

function getPackageListByBuyChance(packageList,buyChance){
	var retVal=[];
	for(var i=0;i<packageList.length;i++){
		if(packageList[i].buyChance==buyChance){
			retVal.push(packageList[i]);
		}
	}
	return retVal;
}