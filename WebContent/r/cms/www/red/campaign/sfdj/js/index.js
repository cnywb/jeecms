initEditor();
$(document).ready(function(){
	initLogin();
	initRegister();
	initBtnEvent();
	initCarOwnerCheck();
	initUpdateProfile();
   $("#story_image_url").val("");
   $("#story_content").val("");
   $("#switch_L7_content").css({"filter":"alpha(opacity=100)"});
   initContentHint();
   
});
function initEditor(){
	KindEditor.ready(function(K) {
		initUploadBtn(K,'image');
	});
}

function initCarOwnerCheck(){
	$("#auth_submit").click(function(){
		carOwnerCheck();
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
					alert("该VIN码不存在!");
				}else if(msg=="99"){
					alert("用户未登录！");
				}
	      },error:function(){
	    	  $.unblockUI();
	            alert("网络或数据异常，操作失败！");
	     }
	});
}
function initBtnEvent(){
	$("#delegate_btn_upload_image").click(function(){
		showCommonAlert1("活动已经结束！");
		return;
		var userId=$("#user_id").val();
		if(userId==""){
			$("#switch_L2").show();
			return ;
		}
		var groupId=$("#group_id").val();
		if(groupId!="2"){
			$("#switch_L4").show();
			return ;
		}
		$("#switch_L7").show();
	});
	$("#btn_save").click(function(){
		showCommonAlert1("活动已经结束！");
		return;
		var userId=$("#user_id").val();
		if(userId==""){
			$("#switch_L2").show();
			return ;
		}
		doSave();
	});
	
	$("#story_content").click(function(){
		showCommonAlert1("活动已经结束！");
		return;
		var userId=$("#user_id").val();
		if(userId==""){
			$("#switch_L2").show();
			return ;
		}
	});
	$("#award_rule_confrim").click(function(){
		$("#switch_award_rule").hide();
		$("#switch_L3_1").show();
	});
}
/**
 * ie 下不能通过调用上传按钮的click事件来触发上传
 * 只能人为操作，所以不能用代理点击的方法来实现ke的上传操作
 * 只能重写ke上传按钮的样实来达到实际需求
 * @param K
 * @param type
 */
function initUploadBtn(K,type){
	var uploadbutton = K.uploadbutton({
		button : K('#btn_upload_'+type)[0],
		fieldName : 'imgFile',
		url : '/common/kindeditor/upload.jhtml?dir='+type,
		afterUpload : function(data) {
			$.unblockUI();
			if (data.error === 0) {
				var url = K.formatUrl(data.url, 'absolute');
				$("#story_image_url").val(url);
				$("#hs_showbox").attr("href",url).click();//弹出预览窗口
			}else{
				$.unblockUI();
				alert(data.message);
			}
		},
		afterError : function(str) {
			$.unblockUI();
			alert("上传文件出错！");
		}
	});
	uploadbutton.fileBox.change(function(e) {
		$("#switch_L7").hide();
		showBlock();
		uploadbutton.submit();
	});
	
	
	$("input[type='button']").each(function(){
		if($(this).hasClass("ke-button-common")){
			$(this).removeClass("ke-button-common").addClass("ke-button-common-custom");
			$(this).css({"width":"340px","background-color":"#F4AD49","cursor": "hand"});
		}
	});
	$("span").each(function(){
		if($(this).hasClass("ke-button-common")){
			$(this).removeClass("ke-button-common").addClass("ke-button-common-custom");
			$(this).css({"width":"340px","background-color":"#F4AD49","cursor": "hand"});
		}
	});
	$("input[type='file']").each(function(){
		if($(this).hasClass("ke-upload-file")){
			$(this).css({"background":""});
			$(this).css({"width":"340px","background-color":"#F4AD49","cursor": "hand"});
		}
	});
	$("#btnc01").hide();
	$("#switch_L7").hide();
}

function doSave(){
	var submitData={};
	submitData["imageUrl"]=$("#story_image_url").val();
	submitData["content"]=$("#story_content").val();
	if(submitData["imageUrl"]==""){
		$("#switch_L8").show();
	    return ;
	}
	if(submitData["content"]==""){
		$("#switch_L8_1").show();
	    return ;
	}
	if(submitData["content"].length>5000){
		$("#switch_L8_2").show();
	    return ;
	}
	showBlock();
	 $.ajax( {
			url :'addMemberStory.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				$.unblockUI();
				if(result.status==0){
					$("#switch_L2").show();
				}else if(result.status==1){
					$("#switch_L4").show();
				}else if(result.status==2){
					$("#switch_L8_3").show();
				}else if(result.status==3){
					$("#switch_L5").show();
				}else if(result.status==777){
					showCommonAlert1("操作失败,活动已经结束！");
					
				}
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
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

function addMemberStoryPraise(memberStoryId){
	var submitData={};
	submitData["memberStory.id"]=memberStoryId;
	showBlock();
	 $.ajax( {
			url :'addMemberStoryPraise.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				$.unblockUI();
				if(result.status==999){
					$("#switch_L2").show();
				}else if(result.status==888){
					$("#switch_L4").show();
				}else if(result.status==1){
					$("#switch_L9").show();
				}else if(result.status==0){
					$("#switch_L10").show();
				}else if(result.status==777){
					showCommonAlert1("活动已经结束！");
					
				}
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
	       }
		});
}

function initLogin(){
	$("#login_submit").click(function(){
		 doLogin();
	});
}

function initRegister(){
	$("#reg_submit").click(function(){
		doRegister();
	});
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
					$("#switch_L3").hide();
					$('#switch_L2').show();
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

function goToShare(id){
	$("#share_id").val(id);
	$("#bdUrl").val(window.location.href);
	$("#share_frame").attr("src","share.jspx?"+$("#shareForm").serialize());
	$("#switch_L10_1").show();
	//$("#shareForm").submit();
}

function memberStoryPass(id){
	    if(!confirm('进行此操作吗？')){
		 return;
	    }
	    var submitData={};
		submitData["id"]=id;
		showBlock();
		 $.ajax( {
				url :'memberStoryPass.jspx',
				type : 'post',
				data:submitData,
				dataType:'json',
				success : function(result) {
					$.unblockUI();
					if(result.status==1){
						alert("操作成功!");
						window.location.reload();
					}else if(result.status==2){
						alert("操作失败,用户未登录!");
						
					}else if(result.status==3){
						alert("操作失败,用户没有权限!");
					
					}
		        },error:function(){
		        	$.unblockUI();
		            alert("网络或数据异常，操作失败！");
		       }
			});
}
function memberStoryUnPass(id){
	      if(!confirm('进行此操作吗？')){
			 return;
		    }
		    var submitData={};
			submitData["id"]=id;
			showBlock();
			 $.ajax( {
					url :'memberStoryUnPass.jspx',
					type : 'post',
					data:submitData,
					dataType:'json',
					success : function(result) {
						$.unblockUI();
						if(result.status==1){
							alert("操作成功!");
							window.location.reload();
						}else if(result.status==2){
							alert("操作失败,用户未登录!");
							
						}else if(result.status==3){
							alert("操作失败,用户没有权限!");
						}
			        },error:function(){
			        	$.unblockUI();
			            alert("网络或数据异常，操作失败！");
			       }
				});
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

function initContentHint(){
	if($.browser.msie){
		$("#story_content").val("输入祝福语并上传全家福(限定5000字以内)，图片文件大小：小于2M");
		$("#story_content").click(function(){
			if($("#story_content").val()=="输入祝福语并上传全家福(限定5000字以内)，图片文件大小：小于2M"){
				$("#story_content").val("");	
			}
		});
	}
}

function initUpdateProfile(){
	$("#update_profile_submit").click(function(){
		doUpdateProfile();
	});
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
					$('#switch_L3_1').hide();
					showCommonAlert("没有开启会员功能!");
			    }else if(result.status==1){
			    	$('#switch_L3_1').hide();
			    	showCommonAlert("用户未登录!");
			    }else if(result.status==2){
			    	$('#switch_L3_1').hide();
			    	showCommonAlert("联系方式不能为空!");
			    }else if(result.status==3){
			    	$('#switch_L3_1').hide();
			    	showCommonAlert("联系方式不能为空!");
			    }else if(result.status==4){
			    	$('#switch_L3_1').hide();
			    	showCommonAlert("手机号码不合法!");
			    }else if(result.status==5){
			    	//alert("保存成功!");
			    	//$("#award_flash").resetFun();
			    	$("#switch_L3_1").hide();
			    	$("#switch_L10_2").show();//显示大转盘
			    }
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
	       }
		});
}

function showAwardRule(){
	var userId=$("#user_id").val();
	if(userId==""){
		$("#switch_L2").show();
		return ;
	}
	$("#switch_award_rule").show();
}

function showCommonAlert(info){
	$("#common_info_alert").show();
	$("#common_info_alert_content").html(info);
}
function showCommonAlert1(info){
	$("#common_info_alert_1").show();
	$("#common_info_alert_content_1").html(info);
}