$(document).ready(function(){
	    loadQuestion();
	$("#a_submit").click(function(){
		doSave();
	});
 });


var questionList=new Array();
function loadQuestion(){
	var userId=$("#user_id").val();
	if(userId==""){
		
		$("#switch_L11").hide();
		$("#a_submit").hide();
		$("#switch_L17").show();
		return;
	}
	
	var enable=$("#enable").val();
	var msg=$("#msg").val();
	if(enable!=1){
		$("#switch_L11").hide();
		$("#a_submit").hide();
		showCustomAlert(msg);return;
	}
	var surveyId=$("#surveyId").val();
	if(surveyId==""){
		return;
	}
	var submitData={"surveyId":surveyId};
	 $.ajax( {
			url :'findAllQuestionBySurveyId.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				questionList=result;
				setQuestions(result);
	        },error:function(){
	        	alert("加载问题发生异常，请刷新重试！");
		       }
		});
}

function setQuestions(result){
	var html='';
	for(var i=0;i<result.length;i++){
		var q=result[i];
		var cls;
		if((i+1)%2==0){
			cls='qnQA qnQAgrey';
		}else{
			cls='qnQA';
		}
		html=html+'<div class="'+cls+'" >'+
        '<h3>'+(i+1)+"."+q.name+'</h3>'+
        '<ul>';
		var answerList=q.answerList;
		var inputType="radio";
		if(q.type==1){
			inputType="checkbox";
		}
		for(var j=0;j<answerList.length;j++){
			var a=answerList[j];
		    var memo='';
			if(a.needMemo==true){
				memo='<input type="text" value="(请填写）" id="m_a_'+a.id+'" class="reason" onFocus="if (value ==\'(请填写\'){value =\'\'}"  onBlur="if (value ==\'\'){value=\'(请填写）\'};" />';
			}
			html=html+'<li><input  id="a_'+a.id+'" type="'+inputType+'" value="'+a.id+'" name="a_'+q.id+'"   needMemo="'+a.needMemo+'" /><label for="a_'+a.id+'">'+a.name+'</label>'+memo+'</li>';
		}
            
      html=html+'</ul>'+
        '</div>';
	}
	$("#a_submit").before(html);
}

function showBlock(){
	$.blockUI({
        message: '<h1>正在执行操作,请稍后...</h1>',
        css: {
            border: 'none',                   // 无边界
            width:"180px",
            top:"50%",                        // 高居中
            left:(($(this).width()-180)/2)+"px"                        // 左居中
       }
    });
}
function doSave(){
	var submitData={};
	var surveyCode=$("#surveyCode").val();
	submitData["surveyCode"]=surveyCode;
	var j=0;
	for(var i=0;i<questionList.length;i++){
		var q=questionList[i];
		var a_name='a_'+q.id;
		if(q.type==1){//多选框处理
			var checkBoxCount=0;
			$("input[name='"+a_name+"']").each(function (){
				if($(this).is(":checked")){
					submitData["answerSheetlist["+j+"].answer.id"]=$(this).val();
					j=j+1;
					if($(this).attr("needMemo")==true){
						   submitData["answerSheetlist["+j+"].answerMemo"]=$("#m_"+$(this).attr("id")).val();
					}
					checkBoxCount=checkBoxCount+1;
				}
			});
			if(checkBoxCount==0){
				$("#switch_L15").show(); return;
			}
		}else{//单选框处理
		   var rd=$("input[name='"+a_name+"']:checked");
		   var  value=$(rd).val();
		   if(value==undefined){
			$("#switch_L15").show(); return;
		   }
		   submitData["answerSheetlist["+j+"].answer.id"]=value;
		   if($(rd).attr("needMemo")=="true"){
			   var memo=$("#m_"+$(rd).attr("id")).val();
			   if(memo==""||memo=="(请填写）"){
				   showCustomAlert("您有未完成的问题补充说明"); return;
			   }
			   submitData["answerSheetlist["+j+"].answerMemo"]=memo;
		   }
		   j=j+1;
		}
	}
	
	showBlock();
	 $.ajax( {
			url :'submitAnswers.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				 $.unblockUI();
				
				if(result.status==1){
					 $("#p_point").text(result.point);
					 $("#switch_L13").show();
				}else{
					showCustomAlert(result.message);
				}
	        },error:function(){
	        	 $.unblockUI();
	        	alert("保存问卷发生异常，请刷新重试！");
		    }
		});
	
}

function doLuckyDraw(){
	var submitData={};
	submitData["code"]="CZZM";
	showBlock();
	 $.ajax( {
			url :'../campaign/luckydraw/doDraw.jspx',
			type : 'post',
			data:submitData,
			dataType:'json',
			success : function(result) {
				$.unblockUI();
				if(result["status"]==9){
					$("#switch_L12").hide();
					$("#switch_L14").show();
				}else{
					alert(result.message);
				}
	        },error:function(){
	        	$.unblockUI();
	            alert("网络或数据异常，操作失败！");
	       }
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
			    	alert("保存成功!");
			    	$("#switch_L14").hide();
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

function showCustomAlert(msg){
	$("#switch_L16").find("td").html(msg);
	$("#switch_L16").show();
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

