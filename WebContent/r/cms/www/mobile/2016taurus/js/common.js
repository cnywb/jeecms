var secrect,inform=false;
var openid = urlRequest("openId"); 
console.log("wechatId: " + openid);
 
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-35461309-43');
  ga('send', 'pageview');

function ClickEvent(event,interaction) {
    event = event.toUpperCase();
    interaction=interaction||false;
    if (event.indexOf("BUT_") != -1) {
        
    try{ga('send', 'event', 'BUT', event, {'nonInteraction': interaction});}
        catch(err){};
    }
    else {
        
    try{ga('send', 'pageview',event, {'nonInteraction': interaction});}
        catch(err){};
    }

    console.log("ClickEvent("+event+")");
} 
  
 
//获取url参数方法
function urlRequest(paras) 
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
 
function saveinfor(dd){ 
    var jsonStr=JSON.stringify(dd);
    //alert(jsonStr);
    //return;

    $.ajax( {
        url :'../../../ocms/toolkit/infocollection/data/addJSONP.htm',
        type : 'post',
        dataType:'jsonp',
        jsonp:"jsonpcallback",
        data:{"jsonStr":jsonStr},
        success : function(result) { 
            //成功提交
                    if(result.status == "11"){ 
                        $(".successPage").removeClass("dn"); 
                    } 
                    else{alert(result.message);}
        },error:function(XMLHttpRequest, textStatus, errorThrown){
               alert(textStatus);
                }
    }); 
}

//活动规则
var poprule = function(){ 
    $(".rulePage").removeClass("dn");
};
//关闭活动规则
var clsrule = function(){ 
    $(".rulePage").addClass("dn");
};
 
//非凡推荐
var showform1 = function(){  
    ClickEvent('BUT_1.0_Referral');
    ClickEvent('P2.0',1); 
    $(".page2").removeClass("dn"); 
    $(".page2").fadeIn();
    $(".page1").addClass("dn"); 
};

//再购不凡
var showform2 = function(){ 
    ClickEvent('BUT_1.0_Repurchase');
    ClickEvent('P3.0',1);
    $(".page3").removeClass("dn"); 
    $(".page3").fadeIn();
    $(".page1").addClass("dn"); 
};

//提交表单
var submitform2 = function(){ 
    ClickEvent('BUT_2.0_Referral');
    console.log("非凡推荐提交表单 000004");
    // 验证信息
    var chann = urlRequest("channel");
    window.channel = chann.split("#")[0];
    var submitData={"campaignCode": "000004",
        "channelCode":chann };
    var dataListAll =[];
    var object ={};

    var pattern = /^(1(([3589][0-9])|(47)|[8][01236789]))\d{8}$/;
    var a = $(".page2 .formwrap2 .inputName").val();
    var b = $(".page2 .formwrap2 .inputMobi").val();
    var cartp = $("#ownerCar").find("option:selected").text(); 
//alert(cartp);
    var tempid = 1;

    var maybename ;
    var maybephone;
    var prov ;
    var city ;
    var dealer ;
    var maybetime ;

    var returnthis = function(i){return i;}
    //必填姓名、手机
    if (a == "" || a == undefined) {
        alert('请填写您的姓名！');
        $(".page2 .formwrap2 .inputName").focus();
        return false;

    }

    if (b == "" || b == undefined) {
        alert('请填写手机号码，谢谢！');
        $(".page2 .formwrap2 .inputMobi").focus(); 
        return false;
    }

    if (!pattern.test(b.trim())) {
        alert('您的手机号码格式有误，请检查！');
        $(".page2 .formwrap2 .inputMobi").focus();
        return false; 
    }

    if (cartp == "请选择" || cartp == undefined) {
        alert('请选择所购车型，谢谢！'); 
        return false;  
    }   
    $(".page2 .formwrap2 .appends .items .item").each(function(eachi){
        maybename = $(this).find(".inputsuggName").val();
        maybephone = $(this).find(".inputsuggMobi").val(); 
        prov = $(this).find(".province").find("option:selected").text();
        city = $(this).find(".city option:selected").text();
        dealer = $(this).find(".dealer option:selected").text();
        maybetime = $(this).find(".dealTime option:selected").text();

        if (maybename == "") {
            alert('请填写被推荐人'+ tempid + '的姓名！'); 
            return returnthis(false);
        }

        if (maybephone == "") {
            alert('请填写被推荐人'+ tempid + '的手机号码，谢谢！'); 
            return returnthis(false);
        }

        if (!pattern.test(b.trim())) {
            alert('被推荐人'+ tempid + '的手机号码格式有误，请检查！'); 
            return returnthis(false);
        }
        if (prov == "请选择"|| prov == "" ||prov == undefined) {
            alert('请选择被推荐人'+ tempid + '的省份，谢谢！'); 
            return returnthis(false);
        }

        if (city== "请选择"|| city=="" || city== undefined) {
            alert('请选择被推荐人'+ tempid + '的城市，谢谢！'); 
            return returnthis(false);
        }

        if (dealer == "请选择"|| dealer == "" || dealer == undefined) {
            alert('请选择被推荐人'+ tempid + '的经销商，谢谢！'); 
            return returnthis(false);
        }    
        if (maybetime == "请选择" || maybetime == ""|| maybetime == undefined) {
            alert('请选择被推荐人'+ tempid + '的预计购车时间，谢谢！'); 
            return returnthis(false);
        }
//alert(cartp);

        var object = new Object();
        object["buyerName"]=a;
        object["potentialBuyerName"]=a; 
        object["buyerMobilePhoneNo"]=b;  
        object["potentialBuyerName"]=maybename; 
        object["potentialBuyerMobilePhoneNo"]=maybephone; 
        object["purchasedProductModel"]=cartp;  
        object["potentialBuyTime"]=maybetime; 
        object["potentialBuyerProvince"]=prov;
        object["potentialBuyerCity"]=city; 
        object["potentialBuyerDealer"]=dealer;
        object["potentialBuyProductModel"]="福特金牛座";
        object["buyerGender"]="UNKNOWN";
        object["potentialBuyerGender"]="UNKNOWN";
        //object["purchasedProductModel"]="";    
        object["wechatId"]=openid;     
        dataListAll.push(object); 
        tempid ++;
    });

    if(dataListAll.length < $(".page2 .formwrap2 .appends .items .item").length || dataListAll == undefined){
        return false;
    }
     
    if(agree == false){
        alert('接受相关条款，谢谢！');
        return false;
    }  

    ga('set','campaignContent',a+"-"+b); 
                
    submitData["dataList"] = dataListAll; 
    console.log(submitData);
    saveinfor(submitData);    
};


//提交表单
var submitform1 = function(){ 
    console.log("再购不凡提交表单 000005");
    ClickEvent('BUT_3.0_Repurchase');
    // 验证信息
    var chann = urlRequest("channel");
    window.channel = chann.split("#")[0];
    var submitData={"campaignCode": "000005",
        "channelCode":chann };
    var dataListAll =[];
    var object ={};

    var pattern = /^(1(([3589][0-9])|(47)|[8][01236789]))\d{8}$/;
    var a = $(".page3 .formwrap1 .inputName").val();
    var b = $(".page3 .formwrap1 .inputMobi").val();
    var cartp = $(".page3 .formwrap1 .ownerCar").find("option:selected").text(); 
    var prov = $('.page3 .formwrap1 .province').find("option:selected").text();
    var city = $('.page3 .formwrap1 .city').find("option:selected").text();
    var dealer = $(".page3 .formwrap1 .dealer").find("option:selected").text();
    var ownerbuytime = $(".page3 .formwrap1 .buyTime").find("option:selected").text();
    var maybetime = $(".page3 .formwrap1 .dealTime").find("option:selected").text(); 
    //必填姓名、手机
    if (a == "") {
        alert('请填写您的姓名！');
        $(".name").focus();
        return false;
    }

    if (b == "") {
        alert('请填写手机号码，谢谢！');
        $(".phone").focus();
        return false;
    }
    if (!pattern.test(b.trim())) {
        alert('您的手机号码格式有误，请检查！');
        $(".phone").focus();
        return false;
    }

    if (prov == "请选择"|| prov == undefined) {
        alert('请选择省份，谢谢！');
        $(".province").focus();
        return false;
    }

    if (city== "请选择"|| city== undefined) {
        alert('请选择城市，谢谢！');
        $(".city").focus();
        return false;
    }

    if (cartp == "请选择" || cartp == undefined) {
        alert('请选择现有车型，谢谢！');
        $(".cartype").focus();
        return false;
    }
    if (ownerbuytime == "请选择"|| ownerbuytime == undefined) {
        alert('请选择现有车型购车时间，谢谢！');
        $(".maybebuytime").focus();
        return false;
    }

    if (dealer == "请选择"|| dealer == undefined) {
        alert('请选择原经销商，谢谢！');
        $(".dealer").focus();
        return false;
    }

    if (maybetime == "请选择"|| maybetime == undefined) {
        alert('请选择预计购车时间，谢谢！');
        $(".maybebuytime").focus();
        return false;
    }

    if(agree == false){
        alert('接受相关条款，谢谢！');
        return false;
    }
 
    var object = new Object();
    object["buyerName"]=a;
    object["potentialBuyerName"]=a; 
    object["buyerMobilePhoneNo"]=b;  
    object["potentialBuyerMobilePhoneNo"]=b; 
   // object["potentialBuyProductModel"]=cartp; 
object["purchasedProductModel"]=cartp; 
  
    object["buyTime"]=ownerbuytime; //车主购车时间
    object["potentialBuyTime"]=maybetime; //预计购车时间
    object["potentialBuyerProvince"]=prov;
    object["potentialBuyerCity"]=city; 
    object["potentialBuyerDealer"]=dealer;
    object["potentialBuyProductModel"]="福特金牛座";
    object["buyerGender"]="UNKNOWN";
    object["potentialBuyerGender"]="UNKNOWN";  
    object["wechatId"]=openid;     
    dataListAll.push(object); 

    ga('set','campaignContent',a+"-"+b); 
                
    submitData["dataList"] = dataListAll; 
    console.log(submitData);
    saveinfor(submitData);    
};

var clssuccess = function(){
    console.log("clssuccess");
    $(".successPage").addClass("dn"); 
};

var agreeFn = function () { 
    if(agree == true){
        agree = false;
        $(".page3 .formwrap1 .chk1").attr("src","/r/cms/www/mobile/2016taurus/img/page2ck.jpg");
    }else{
        agree = true;
        $(".page3 .formwrap1 .chk1").attr("src","/r/cms/www/mobile/2016taurus/img/page2cked.jpg");
    }
    
}

var agreeFn2 = function () { 
    if(agree == true){
        agree = false;
        $(".page2 .formwrap2 .chk2").attr("src","../img/page2ck.jpg");
    }else{
        agree = true;
        $(".page2 .formwrap2 .chk2").attr("src","../img/page2cked.jpg");
    }
    
}


var additems = function(){
    if(itemcount < 3){
        itemcount++;
        var its = '<div class="item"><img src="/r/cms/www/mobile/2016taurus/img/page2info.jpg" class="infobg ab00 "alt=""><input type="text" class="ab inputsuggName"><input type="text" class="ab inputsuggMobi" maxlength="11"><select class="ab province" id="province'+ itemcount +'"><option value="0" selected="">请选择</option></select><select class="ab city" id="city'+ itemcount +'"><option value="0">请选择</option></select><select class="ab dealer" id="dealer'+ itemcount +'"><option value="0">请选择</option></select><select class="ab dealTime"><option value="0"selected="">请选择</option><option value="5">0-3个月内</option><option value="1">4-6个月</option><option value="2">7-12个月</option><option value="3">1-2年</option><option value="4">有购车计划但没决定时间</option><option value="4">暂无购车计划</option></select></div>';
        $(".page2 .formwrap2 .appends .items").append(its);
        
        $(".page2 .formwrap2 .tipdown").removeClass("dn");
        $(".page2 .formwrap2 .tipdown").animate({opacity:'1'},"slow");
        $(".page2 .formwrap2 .tipdown").animate({opacity:'0'},"slow");
        $(".page2 .formwrap2 .tipdown").animate({opacity:'1'},"slow");
        $(".page2 .formwrap2 .tipdown").animate({opacity:'0'},"slow");

        initSelectData("province"+itemcount,"","city"+itemcount,"", "dealer"+itemcount, "");
    }

    if(itemcount == 3){
        $(".page2 .formwrap2 .js_addmore").addClass("dn");
    }


}

// 添加所有事件
function addEvents(){
    console.log("addEvents");
    // 首页
    $(".page1 .btn1").bind("click",showform2);//非凡推荐
    $(".page1 .btn2").bind("click",showform1);//再购不凡

    $(".page1 .btnrule").bind("click",poprule);     //活动规则  
    $(".rulePage .content .clsbtn").bind("click",clsrule);//关闭活动规则
  
    //表单页面
    $(".page2 .formwrap2 .js_submit").bind("click",submitform2);//提交表单
    $(".page2 .formwrap2 .chk2").bind("click",agreeFn2);
    $(".page2 .formwrap2 .js_addmore").bind("click",additems);

    $(".page3 .formwrap1 .submitBtn1").bind("click",submitform1);//再购不凡提交表单
    $(".page3 .formwrap1 .chk1").bind("click",agreeFn);

    $(".page2 .formwrap2 .appends .items").on("touchstart", function() {
        $(document).off('touchmove.prevent');
    });
    $(".page2 .formwrap2 .appends .items").on("touchend", function() {
        $(document).on('touchmove.prevent', function(e) {
            e.preventDefault();
        });
    });

    //成功关闭按钮
    $(".successPage .content .clsbtn").bind("click",clssuccess);

    //音乐播放按钮
    $(".playp").on("touchstart",function(){
        if($audio.paused){
            $(".playp").addClass('p');
            $(".playp").removeClass('paused');
            $audio.play();
            mp3play = true;
        } else {
            $audio.pause();
            $(".playp").addClass('paused');
            $(".playp").removeClass('p');
            mp3play = false;
        } 
    });
};

var urlHost = "/";
//var urlHost = "http://yiche.changanfordwechat.com/";
 
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
        url : '../../../dealer/findAllProvince.jspx',
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
        url :urlHost + 'dealer/findAllCityByProvinceId.jspx',
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
        url :urlHost + 'dealer/findAllDealerByCityId.jspx',
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

function show_page(me){  
    addEvents(); 
    ClickEvent('P1.0',1);
    $(".page1").removeClass("dn");
    $(".initPage").addClass("dn"); 
} 

var loadingScale, t;
var imgLoad = function () {
    var loaded = 0;
    var me = this;
    var temp = '';
    if ($('body img').length === 0 || navigator.userAgent.indexOf('MSIE 9') != -1) {
        show_page();
    } else {
        $('body img').imagesLoaded().progress(function (instance, image) {
            loaded++; 
            loadingScale = loaded / instance.images.length; 
            // $(".initPage .loading").html('LOADING' + parseInt(loadingScale * 100) + '%');
            $(".initPage .loading").html('LOADING...');
            if (loaded == instance.images.length) { 
                $(".initPage .loading").html('COMPLETED！');
                show_page(me);
            } 
        });
    } 
}
 
imgLoad();

var $audio = $('#chatAudio')[0];
$audio.play();

//Loading动画
var loaders ={ 
    width: 100,
    height: 50,
    padding: 10,
    stepsPerFrame: 2,
    trailLength: 1,
    pointDistance: .03,
    strokeColor: '#FF7B24',
    
    step: 'fader',
    multiplier: 2,
    setup: function() {
        this._.lineWidth = 5;
    },
    path: [
    
        ['arc', 10, 10, 10, -270, -90],
        ['bezier', 10, 0, 40, 20, 20, 0, 30, 20],
        ['arc', 40, 10, 10, 90, -90],
        ['bezier', 40, 0, 10, 20, 30, 0, 20, 20]
    ]
};

var container = document.getElementById('loadingCavas'); 
var loadAni = new Sonic(loaders); 
container.appendChild(loadAni.canvas);
loadAni.play();

var agree = true; //同意条款

var itemcount = 0;
additems();

//防止页面滚动
$(document).on('touchmove.prevent', function(e) {      e.preventDefault();	}); 
initSelectData("buyingDealerProvince2","","buyingDealerCity2","", "carBuyingDealer2", "");





