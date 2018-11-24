$(document).ready(function(){
	initBtnEvent();
	loadAskAndAnswer();
});




function doLuckyDraw(){
	var submitData={};
	submitData["code"]=$("#lucky_draw_code").val();
	
	for(var i=0;i<askCodeList.length;i++){
		var askCode=askCodeList[i];
		submitData["askAndAnswers["+i+"].askCode"]=askCode;
		submitData["askAndAnswers["+i+"].answerCodes"]=askAndAnswersMap.get(askCode);
	}
	
	//showBlock();
	 $.ajax( {
			url :'../luckydraw/doDrawForAnswerContest.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				//$.unblockUI();
				
				if(result["status"]==9){
					//alert("恭喜你抽中"+result.name+"请确认联系方式以便我们的工作人员联系到您!");
					$("#draw_result").attr("src","/r/cms/www/red/campaign/answercontest/images/c0.png");
				
				}else{
					$("#draw_result").attr("src","/r/cms/www/red/campaign/answercontest/images/c1.png");
				}
				 $(".guess").show();
				 $('#canvas').eraser({
					 completeFunction: function(p) {
			          }
			        });
				 hideAllQuestionContainer();
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
	       }
		});
	
}
var askCodeList=new Array();
function loadAskAndAnswer(){
	var submitData={};
	submitData["group"]=$("#lucky_draw_code").val();
	$.ajax( {
		url :'findAllAskByGroup.jspx',
		type : 'post',
		data:submitData,
		dataType:'json',
		success : function(result) {
				for(var i=0;i<result.length;i++){
				var ask=result[i];
				askCodeList.push(ask.code);
				$("#question_content_"+i).append('<li class="quesDes">'+ask.content+'</li>');
				var answersList=ask.answersList;
				for(var k=0;k<answersList.length;k++){
					var answer=answersList[k];
					$("#question_content_"+i).append('<li id="li_'+answer.code+'" onmousedown="chageAnswerStyle(\''+answer.code+'\')"  onmouseup="checkAnswer('+i+',\''+ask.code+'\''+',\''+answer.code+'\','+answer.isCorrectAnswer+');" ><span class="select" ></span >'+answer.content+'</li>');
				}
				if(ask.code=="ZC_003_E"){
					$("#img_q5").after('<img class="mask" src="/r/cms/www/red/campaign/answercontest/images/mask.png" />');
					
				}
			}
			
		
        },error:function(){
        	$.unblockUI();
            alert("网络或数据异常，操作失败！");
       }
	});
}



function chageAnswerStyle(answerCode){
	$("#li_"+answerCode).find("span").addClass("on");
	$("#li_"+answerCode).siblings().find("span").removeClass("on");
}

var askAndAnswersMap=new HashMap();
var currentAskSeq=0;
function checkAnswer(askSeq,askCode,answerCode,isCorrectAnswer){
	currentAskSeq=askSeq;
	askAndAnswersMap.put(askCode,answerCode);
	if(isCorrectAnswer==true){
		if(askSeq<4){
		showQuestion((askSeq+1));//显示下一题
		}
		if(askSeq==4){
			
			  $(".overlayForm").css({"top":"50px"});
				$("#div_user_info").show();
			
		}
	}else{
		
		$("#question_content_container_"+askSeq).hide();
		$(".lose").show();
	}
}

function reContest(){
	$(".lose").hide();
	showAllQuestionContainer();
	showQuestion(currentAskSeq);
}

function showAllQuestionContainer(){
	$("#question_content_container_0").show();
	$("#question_content_container_1").show();
	$("#question_content_container_2").show();
	$("#question_content_container_3").show();
	$("#question_content_container_4").show();
}
function hideAllQuestionContainer(){
	$("#question_content_container_1").hide();
	$("#question_content_container_2").hide();
	$("#question_content_container_3").hide();
	$("#question_content_container_4").hide();
}

function initBtnEvent(){
	$("#btn_get_tiket").click(function(){
                $(".guess").hide();
		var userId=$("#user_id").val();
		if(userId==""){
			showLoginWin();
			return ;
		}
		var groupId=$("#group_id").val();
		if(groupId!="2"){
			showCertificationWin();
			return ;
		}
		showQuestion(0);
	});
	
	$("#top_banner_login").click(function(){
		showLoginWin();
	});
	$("#top_banner_logout").click(function(){
		doLogout();
	});
	
	$("#top_banner_regist").click(function(){
		showRegistWin();
	});
	
	$("#login_submit").click(function(){
		 doLogin();
	});
	
	$("#reg_submit").click(function(){
		doRegister();
	});
	
	$("#auth_submit").click(function(){
		carOwnerCheck();
	});
	
	$("#btn_go_register").click(function(){
		$("#div_login").hide();
		showRegistWin();
	});
}

function showQuestion(no){
	showAllQuestionContainer();
	$(".lose").hide();
	$(".win").hide();
	var i=0;
	$("#question_container div").each(function(){
		if($(this).hasClass("question")){
			if(i<no){//隐藏当前题目之前的题
				$(this).hide();
			}if(i==no){//前题目移动到第一张的位置
				var left="-"+130*i+"px";
				$(this).animate({"left":left});
			}else{//当前题目之后的题移动到相应位置
				var left=(410-130*no)+"px";
				$(this).animate({"left":left});
			}
			for(var k=0;k<5;k++){//隐藏不需要的显示的题目
				if(k!=no){
					$(".q"+(k+1)).hide();
				}
			}
			$(".q"+(no+1)).fadeIn();//显示题目
			
			i=i+1;
		}
	});
}

function showLoginWin(){
	$("#login_username").val("");
	$("#login_password").val("");
	$("#login_validatecode").val("");
	$(".overlayForm").css({"top":"50px"});
	$("#div_login").show();
}
function showRegistWin(){
	$("#reg_username").val("");
	$("#reg_password").val("");
	$("#reg_email").val("");
	$("#reg_validatecode").val("");
	$(".overlayForm").css({"top":"50px"});
	$("#div_register").show();
}

function showCertificationWin(){
	$(".overlayForm").css({"top":"50px"});
	$("#div_certification").show();
}

function doLogin(){
	var submitData={};
	submitData["username"]=$("#login_username").val();
	submitData["password"]=$("#login_password").val();
	submitData["captcha"]=$("#login_validatecode").val();
	submitData["password"]=utility.MD5(submitData["password"]);
	if(submitData["username"]==""){
		alert("用户名不能为空!");
		return;
	}
	if(submitData["password"]==""){
		alert("密码不能为空!");
		return;
	}
	if(submitData["captcha"]==""){
		alert("验证码不能为空!");
		return;
	}
	showBlock();
	 $.ajax( {
			url :'../../loginAjax.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				$("#img_captcha").click();
				$.unblockUI();
				if(result.status==1){
					window.location.reload();
			    }else{
					alert(result.message);
				}
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
	       }
		});
}

function doRegister(){
	var submitData={};
	submitData["username"]=$("#reg_username").val();
	submitData["password"]=$("#reg_password").val();
	submitData["email"]=$("#reg_email").val();
	submitData["captcha"]=$("#reg_validatecode").val();
	var cmfPassword=$("#reg_cmf_password").val();
	if(submitData["username"]==""){
		alert("用户名不能为空!");
		return;
	}
	if(submitData["password"]==""){
		alert("密码不能为空!");
		return;
	}
	if(submitData["password"]!=cmfPassword){
		alert("密码与确认密码不一致!");
		return;
	}
	if(submitData["email"]==""){
		alert("邮箱不能为空!");
		return;
	}
	if(submitData["captcha"]==""){
		alert("验证码不能为空!");
		return;
	}
	submitData["password"]=utility.MD5(submitData["password"]);
	showBlock();
	 $.ajax( {
			url :'../../registerAjax.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				$("#reg_img_captcha").click();
				$("#img_captcha").click();
				$.unblockUI();
				if(result.status==1){
					$("#div_register").hide();
					showLoginWin();
					
					$("#login_username").val($("#reg_username").val());
					$("#login_password").val($("#reg_password").val());
					alert(result.message+"请到会员中心完善个信息,以便奖品发放!");
				}else{
					alert(result.message);
				}
			    
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
	       }
		});
}

function checkFields(){
	var vname = $("#vname").val().trim();
	var vmobile = $("#vmobile").val().trim();
	var vvin = $("#vvin").val().trim();
	if(vname == ""){
		alert("姓名不能为空!");;
		return false;
	}
	if(!validateMobilePhoneNo(vmobile)){
		alert("电话号码不正确!");
		return false;
	}
	if(!validateVin(vvin)){
		alert("VIN码不正确!");
		return false;
	}
	return true;
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

function validateMobilePhoneNo(mobilePhoneNo){
	var reg=/^((13[0-9])|(15[^4,\D])|(18[0-9]))\d{8}$/;
	return mobilePhoneNo.match(reg);
}

function doLogout(){
	var submitData={};
	showBlock();
	 $.ajax( {
			url :'../../logout.jspx?returnUrl=/',
			type : 'post',
			data:submitData,
			dataType:'text',
			success : function(result) {
				window.location.reload();
	        },error:function(){
	        	$.unblockUI();
	           
	       }
		});
}

function showBlock(){
	$.blockUI({
        message: '<h1>正在执行操作,请稍后...</h1>',
        css: {
            border: 'none',                   // 无边界
            width:"180px",
            top:"50%",                        // 高居中
            left:"50%"                        // 左居中
       }
    });
}

function carOwnerCheck(){
	if(!checkFields()){
		return;
	}
	showBlock();
	var requestJson={};
	requestJson["vvin"]=$("#vvin").val().trim();
	requestJson["vname"]=$("#vname").val().trim();
	requestJson["vmobile"]=$("#vmobile").val().trim();
	showBlock();
	 $.ajax( {//提交数据
			url : '../../czrz/carOwnerCheckAjax.jspx',
			type : 'post',
			dataType:'json',
			data: requestJson,
			success : function(result) {
				$.unblockUI();
				var msg=result.status;
			    if(msg == "1"){
			    	alert("认证成功!");
			    	window.location.reload();
				}else if(msg == "3"){
					alert("认证失败,因为该VIN码已经被验证过!");
				}else if(msg == "4"){
					alert("认证失败,电话号码错误!");
		   		}else if(msg == "5"){
		   			alert("认证失败,姓名错误!");
				}else if(msg == "6"){
					alert("认证失败,姓名和电话都错误!");
				}else if(msg == "7"){
					
					showManualCert();
					//alert("该VIN码不存在!");
				}else if(msg=="99"){
					alert("用户未登录！");
				}
	      },error:function(){
	    	  $.unblockUI();
	            alert("网络或数据异常，操作失败！");
	     }
	});
}


function showManualCert(){
	$("#div_certification").hide().hide();
	$("#div_manual_cert").show();
}


function setManualCertLink(){
$("#form_certification").submit();
	
}



function doUpdateProfile(){
	var submitData={};
	submitData["mobile"]=$("#update_mobile").val();
	submitData["comefrom"]=$("#update_comefrom").val();
	submitData["realname"]=$("#update_realname").val();
	showBlock();
	 $.ajax( {
			url :'../../member/updateProfile.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				$.unblockUI();
				if(result.status==0){
					
					showCommonAlert("没有开启会员功能!");
			    }else if(result.status==1){
			    	
			    	showCommonAlert("用户未登录!");
			    }else if(result.status==2){
			    	
			    	showCommonAlert("联系方式不能为空!");
			    }else if(result.status==3){
			    
			    	showCommonAlert("联系方式不能为空!");
			    }else if(result.status==4){
			    	
			    	showCommonAlert("手机号码不合法!");
			    }else if(result.status==5){
			    	doLuckyDraw();
			    	$("#div_user_info").hide();
			    }
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
	       }
		});
}

function showCommonAlert(msg){
	alert(msg);
}

function HashMap(){  
    //定义长度  
    var length = 0;  
    //创建一个对象  
    var obj = new Object();  
  
    /** 
    * 判断Map是否为空 
    */  
    this.isEmpty = function(){  
        return length == 0;  
    };  
  
    /** 
    * 判断对象中是否包含给定Key 
    */  
    this.containsKey=function(key){  
        return (key in obj);  
    };  
  
    /** 
    * 判断对象中是否包含给定的Value 
    */  
    this.containsValue=function(value){  
        for(var key in obj){  
            if(obj[key] == value){  
                return true;  
            }  
        }  
        return false;  
    };  
  
    /** 
    *向map中添加数据 
    */  
    this.put=function(key,value){  
        if(!this.containsKey(key)){  
            length++;  
        }  
        obj[key] = value;  
    };  
  
    /** 
    * 根据给定的Key获得Value 
    */  
    this.get=function(key){  
        return this.containsKey(key)?obj[key]:null;  
    };  
  
    /** 
    * 根据给定的Key删除一个值 
    */  
    this.remove=function(key){  
        if(this.containsKey(key)&&(delete obj[key])){  
            length--;  
        }  
    };  
  
    /** 
    * 获得Map中的所有Value 
    */  
    this.values=function(){  
        var _values= new Array();  
        for(var key in obj){  
            _values.push(obj[key]);  
        }  
        return _values;  
    };  
  
    /** 
    * 获得Map中的所有Key 
    */  
    this.keySet=function(){  
        var _keys = new Array();  
        for(var key in obj){  
            _keys.push(key);  
        }  
        return _keys;  
    };  
  
    /** 
    * 获得Map的长度 
    */  
    this.size = function(){  
        return length;  
    };  
  
    /** 
    * 清空Map 
    */  
    this.clear = function(){  
        length = 0;  
        obj = new Object();  
    };  
}  