$(document).ready(function(){

    function urlRequest(paras)  //获取url参数方法
	{ 
	var url = location.href; 
	var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
	var paraObj = {} 
	for (i=0; j=paraString[i]; i++){ 
	paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
	} 
	var returnValue = paraObj[paras.toLowerCase()]; 
	if(typeof(returnValue)=="undefined"){ 
	return ""; 
	}else{ 
	return returnValue; 
	} 
    }

    var chann = urlRequest("channel");
    window.channel = chann.split("#")[0];
    var chann = urlRequest("openid");
    window.openid= chann.split("#")[0];
    $(".popup").css("height",document.body.scrollHeight);

    $(".submitbtn").bind("click",tgSubmit);

    var urlHost = "" ;
    initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", ""); 

    function initSelectData(provinceSelectId,defaultProvince,citySelectId,defaultCity,dealerSelectId,defaultDealer){
        loadProvince(provinceSelectId,defaultProvince,citySelectId,defaultCity,dealerSelectId,defaultDealer);
        $("#"+provinceSelectId).change(function(){
            $("#"+citySelectId).html("");
            $("#"+dealerSelectId).html("");
            var provinceId=$(this).find("option:selected").attr("data");
            loadCity(citySelectId,'',provinceId,'','');
        });
        $("#"+citySelectId).change(function(){
            $("#"+dealerSelectId).html("");
            var cityId=$(this).find("option:selected").attr("data");
            loadDealer(dealerSelectId,'',cityId);
        }); 
    } 

/**
 * 加载省
 */
function loadProvince(selectId,defaultValue,citySelectId,defaultCity,dealerSelectId,defaultDealer){
    var submitData={};
    $.ajax( {
        url :urlHost + '/dealer/findAllProvince.jspx',
        type : 'post',
        data:submitData,
        dataType:'json',
        success : function(result) {
            $("#"+selectId).empty();
            $("#"+selectId).append('<option value="" data="" selected>请选择</option>');
            for(var i=0;i<result.length;i++){
                var t=result[i];
                if(defaultValue==t.name){
                    $("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
                    loadCity(citySelectId, defaultCity, t.id,dealerSelectId,defaultDealer);
                }else{
                    $("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
                }
            }
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
    });
}
/**
 * 加载城市
 */
function loadCity(selectId,defaultValue,provinceId,dealerSelectId,defaultDealer){
    if(provinceId==""){
        return;
    }
    var submitData={"provinceId":provinceId};
    $.ajax( {
        url :urlHost + '/dealer/findAllCityByProvinceId.jspx',
        type : 'post',
        data:submitData,
        dataType:'json',
        success : function(result) {
            $("#"+selectId).empty();
            $("#"+selectId).append('<option value="" data="" selected>请选择</option>');
            for(var i=0;i<result.length;i++){
                var t=result[i];
                if(defaultValue==t.name){
                    $("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
                    loadDealer(dealerSelectId, defaultDealer, t.id);
                }else{
                    $("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
                }
            }
            if (provinceId == "16") {
                $("#"+selectId).append("<option value='莱州市' data='158' >莱州市</option>");
            }
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
    });
}
/**
 * 加载经销商
 */
function loadDealer(selectId,defaultValue,cityId){
    if(cityId==""){
        return ;
    }
    var submitData={"cityId":cityId};
    $.ajax( {
        url :urlHost + '/dealer/findAllDealerByCityId.jspx',
        type : 'post',
        data:submitData,
        dataType:'json',
        success : function(result) {
            $("#"+selectId).empty();
            $("#"+selectId).append('<option value="" data="" selected>请选择</option>');
            for(var i=0;i<result.length;i++){
                var t=result[i];
                if(defaultValue==t.name){
                    $("#"+selectId).append('<option selected="selected" value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
                }else{
                    $("#"+selectId).append('<option value="'+t.name+'" data="'+t.id+'" >'+t.name+'</option>');
                }
            }
        },error:function(){
          alert("网络或数据异常，操作失败！");
       }
    });
}
  
    function showTip(tipTxt) {
        var div = document.createElement('div');
        div.innerHTML = '<div class="deploy_ctype_tip"><p>' + tipTxt + '</p></div>';
        var tipNode = div.firstChild;
        $("#wrap").after(tipNode);
        setTimeout(function () {
            $(tipNode).remove();
        }, 2500);
    }


    function tgSubmit(){ 
        var pattern=/^(1(([3589][0-9])|(47)|[8][01236789]))\d{8}$/;
 
        var b = $("#buyingDealerProvince").val();
        var c = $("#buyingDealerCity").val();
        var d = $("#form-name").val();
        var e = $("#form-tel").val();
        var f = $("#maybebuytime").val();
        var g = $("#supplieraddress").val();
        var src=(""==""?'esq':"");
 

        if($("#buyingDealerProvince").val()=="")
        {
            showTip('请选择试驾省份！');
            $("#buyingDealerProvince").focus();
            return false;
        }

        if($("#buyingDealerCity").val()=="")
        {
            showTip('请选择试驾城市！');
            $("#buyingDealerCity").focus();
            return false;
        } 
        
        if($("#form-name").val()=="")
        {
            showTip('请填写您的姓名！');
            $("#form-name").focus();
            return false;
        }

        if($("#form-tel").val()=="")
        {
            showTip('请填写手机号码！');
            $("#form-tel").focus();
            return false;
        }

        if($("#maybebuytime").val()=="")
        {
            showTip('请选择意向购车时间！');
            $("#maybebuytime").focus();
            return false;
        }

        if(!pattern.test($("#form-tel").val().trim()))
        {
            showTip('您的手机号码格式有误，请检查！');
            $("#form-tel").focus();
            return false;
        } 
        
        $.ajax({
            url: '/infocollection/potentialcustomer/add.jspx',
        data:{
            'infoList[0].carOwnerName':d,
            'infoList[0].carOwnerMobilePhoneNo':e,   
            'infoList[0].customerProvince':b,
            'infoList[0].customerCity':c,
            'infoList[0].intentionalDealer':g,
            'infoList[0].intentionalBuyDateRange':f,
            'infoList[0].carModel':'微客服预约试驾',
            'infoList[0].activityName':'微客服预约试驾',
            'infoList[0].wechatId':openid,
            'infoList[0].channel':'1'
        },
        type: 'post',
        cache: false,
        async: false,
        dataType: 'json', 
            success: function (data) {
                $(".popup").removeClass("dn");
                $(".popup").show();
                
                $('#subscribepop button').bind("click", function(){
                    $(".popup").addClass("dn");
                    $(".popup").hide();
                });

            }

        });

        return true;
    }
  
    var isIos;
    var isAndroid;
    function isIosOrAndroid(){
        var browser={
            versions:function(){
                var u = navigator.userAgent, app = navigator.appVersion;
                return {
                    trident: u.indexOf('Trident') > -1,
                    presto: u.indexOf('Presto') > -1,
                    webKit: u.indexOf('AppleWebKit') > -1,
                    gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,
                    mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/),
                    ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
                    android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
                    iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1,
                    iPad: u.indexOf('iPad') > -1,
                    webApp: u.indexOf('Safari') == -1
                };
            }()
        }
        isIos = browser.versions.ios;
        isAndroid = browser.versions.android;
    }

    
    isIosOrAndroid(); 
    if(isAndroid){  //ios
        $('.isIOS').addClass('select_bg');
    }  

    $("#subscribepop, #shadow").bind("click", function(){
        $(".popup").addClass("dn");
        $(".popup").hide();
    });

}); 
 
    var logo ="http://fe.huangu001.cn/Cycmsdata/attachments/image/20140124/20140124133632_18635.jpg";
    var shareUrl ='fe.huangu001.cn'+"/index.php?g=Wap&m=Car&a=listcars&id=1&token=jvvktf1390570573";
    var dataForWeixin={
        debug:false,
        appId:'wxbc330df0e013e079',
        jstimestamp:'1469761679',
        nonceStr:'s7mKn9QMatqsZLaU',
        jssignature:'40ed20355576f7e8f8003516076c0b9da1642693',
        jsapiTicket:'bxLdikRXVbTPdHSM05e5u24O7N9eHEs9zj6W9QCOgczqbrPQE7tkMlCeSAGIaeRV1DPvHgYHb7N5bDPy2RwZyg',
        imgUrl:logo,
        MsgImg:logo,
        TLImg:logo,
        link:shareUrl,
        title:"长安福特微客服",
        desc:""
    }; 