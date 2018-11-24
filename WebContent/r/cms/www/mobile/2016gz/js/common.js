var secrect,inform=false,timer;
var openid = urlRequest("openId"); 
console.log("wechatId: " + openid);

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-69201345-21', 'auto');
  ga('send', 'pageview');

function doTrack(name) {
    console.log('doTrack', name)
    name = name.toUpperCase();
    ga('send', {
        'hitType': 'event', // Required.
        'eventCategory': "Guangzhou_2016Autoshow", // Required.
        'eventAction': 'Clicks', // Required.
        'eventLabel': name,
        'eventValue': 1
    });
};
function doPageview(name) {
    console.log('doPageview', name);
    ga('send', 'pageview', {
    'page': name,
    'title': "Guangzhou_2016Autoshow",
    });
};
 
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

var loadtouch =function() {
      var startY = ''; // 触摸开始时的纵坐标
       var moveY = ''; // 触摸移动中的纵坐标
       var distance = 0;
      var stime = 0;
      // var endY = '';     // 触摸结束的纵坐标

       document.addEventListener('touchstart', touch, false);
      document.addEventListener('touchmove', touch, false);
      document.addEventListener('touchend', touch, false);
} 

var unloadtouch = function(){
    document.removeEventListener('touchstart', touch, false);
    document.removeEventListener('touchmove', touch, false);
    document.removeEventListener('touchend', touch, false);
}

var touch = function(event) {
        var event = event || window.event;
        var oInp = document.getElementById("inp");
        switch (event.type) {
            case "touchstart":
                startY = event.touches[0].clientY;
                stime = Date.parse(new Date()); 
                break;
            case "touchend":
                
                break;
            case "touchmove":
                event.preventDefault();
                moveY = event.touches[0].clientY;
                if (moveY > startY) {
                    //alert('下划+moveY'+(moveY-startY));
                } else {
                    distance = startY - moveY;
                    sHeight = window.screen.height;
                    speed = distance / sHeight;
                    unloadtouch();
                    showform();
                    //alert('上划+moveY'+(startY-moveY));
                }
                break;
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
                        doPageview("/Guangzhou_2016Autoshow/Result_Page");
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
    doTrack("/Guangzhou_2016Autoshow/Landing_Page/Rule");
    doPageview("/Guangzhou_2016Autoshow/Rule_Page");
    console.log("poprule");
    $(".rulePage").removeClass("dn");
};
//关闭活动规则
var clsrule = function(){
    console.log("clsrule");
    $(".rulePage").addClass("dn");
};

var donghua = function(){
    $(".page2 .title").animate({opacity:'0'},"slow");
    $(".page2 .title").animate({opacity:'1'},"slow");
    $(".page2 .title").animate({opacity:'0'},"slow");
}

var gamerun = function(){
    doPageview("/Guangzhou_2016Autoshow/Transitional_Page");
    openCarAnimation();//开启汽车动画   

    setTimeout(function(){
        $(".page2 .cars").removeClass("dn");
        $(".page2 .cars").fadeIn();
        $(".page2 .cars .car1").addClass("active"); 
    },1100);

    setTimeout(function(){$(".page2 .cars .car2").addClass("active"); },2200);
    setTimeout(function(){$(".page2 .cars .car3").addClass("active"); },3300);
    setTimeout(function(){$(".page2 .cars .car4").addClass("active"); },4400);
    setTimeout(function(){$(".page2 .cars .car5").addClass("active"); },5500); 
    setTimeout(function(){$(".page2 .cars .car6").addClass("active"); },6600);
  
    function showCar(){
        inform = true; 
        console.log("出现了神秘车.inform = " + inform +"random = "+ randomNum);
        donghua(); 

        //randomNum = Math.random()*6; 
        //randomNum = parseInt(randomNum);
        //$(".page2 .cars .car").css("top",offsetY[randomNum]);
        $(".page2 .cars .car").addClass("active");
        setTimeout(function(){
            inform = false;
            $(".page2 .cars .car").removeClass("active");
            console.log("消失了神秘车.inform = " + inform);
        },3200);
    };
    showCar();
    secrect = setInterval(showCar,8000);
}

//即刻追踪
var startgame = function(){
    doTrack("/Guangzhou_2016Autoshow/Landing_Page/Clicks");
    
    console.log("startgame");
    $(".page2").removeClass("dn");
    $(".page2").fadeIn();
    $(".page1").addClass("dn"); 

    gamerun();


};
//捕捉魅影
var catchShdow = function(){ 
    clearInterval(secrect);
    clearInterval(timer);
    $(".page2 .cars .car1").removeClass("active"); 
    $(".page2 .cars .car2").removeClass("active"); 
    $(".page2 .cars .car3").removeClass("active"); 
    $(".page2 .cars .car4").removeClass("active"); 
    $(".page2 .cars .car5").removeClass("active"); 
    $(".page2 .cars .car6").removeClass("active");
    $(".page2 .cars .car").removeClass("active");
    $(".page3 .next").addClass("active");

    console.log("catchShdow"); 
    if(inform == true){
        doPageview("/Guangzhou_2016Autoshow/Successful_Page");
        console.log("在框框里面");
        loadtouch();
        $(".page3 .btngroup").addClass("dn");        
        $(".page3 .txt").removeClass("dn");
        $(".page3 .next").removeClass("dn");
        $(".page3 .success").attr("src","/r/cms/www/mobile/2016gz/img/page3success.png");
    }
    else{
        doPageview("/Guangzhou_2016Autoshow/Replay_Page");
        $(".page3 .btngroup").removeClass("dn");
        $(".page3 .txt").addClass("dn");
        $(".page3 .next").addClass("dn");
        console.log("不在框框里面");
        randomNum = Math.random()*3; 
        randomNum = parseInt(randomNum);
        $(".page3 .success").attr("src","/r/cms/www/mobile/2016gz/img/"+faillist[randomNum]);
    }
    
    // 捕捉
    $(".page3").removeClass("dn");
    $(".page3").fadeIn();
    $(".page2").addClass("dn");
    $(".page2").fadeOut();
};

var restart = function(){//再玩一次
    doTrack("/Guangzhou_2016Autoshow/Replay_Page/Again");
    $(".page3").addClass("dn");
    $(".page2").fadeIn();
    $(".page2").removeClass("dn");
    $(".page3").fadeOut();
    gamerun();
};
//打开表单
var showform = function(){
    doTrack("/Guangzhou_2016Autoshow/Successful_Page/Next");
    doPageview("/Guangzhou_2016Autoshow/Form_Page");
    console.log("showform");
    $(".page4").removeClass("dn"); 
    $(".page4").fadeIn();
    $(".page1").addClass("dn");
    $(".page2").addClass("dn");
    $(".page3").addClass("dn");
};
//提交表单
var submitform = function(){
    doTrack("/Guangzhou_2016Autoshow/Form_Page/Clicks");
    console.log("submitform");
    // 验证信息
    var chann = urlRequest("channel");
    window.channel = chann.split("#")[0];
    var submitData={"campaignCode": "000003",
        "channelCode":chann };
    var dataListAll =[];
    var object ={};

    var pattern = /^(1(([3589][0-9])|(47)|[8][01236789]))\d{8}$/;
    var a = $(".name").val();
    var b = $(".phone").val();
    var cartp = $(".cartype").find("option:selected").text();
    var maybetime = $(".maybebuytime").find("option:selected").text();
    var prov = $('.province').find("option:selected").text();
    var city = $('.city').find("option:selected").text();
    var dealer = $(".dealer").find("option:selected").text();
    var cartype = $(".cartype").find("option:selected").text();
  
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

    if (cartp == "请选择" || cartp == undefined) {
        alert('请选择意向车型，谢谢！');
        $(".cartype").focus();
        return false;
    }

    if (maybetime == "请选择"|| maybetime == undefined) {
        alert('请选择购车时间，谢谢！');
        $(".maybebuytime").focus();
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

    if (dealer == "请选择"|| dealer == undefined) {
        alert('请选择经销商，谢谢！');
        $(".dealer").focus();
        return false;
    }
    var object = new Object();
    object["buyerName"]=a;
    object["potentialBuyerName"]=a; 
    object["buyerMobilePhoneNo"]=b;  
    object["potentialBuyerMobilePhoneNo"]=b; 
    object["potentialBuyProductModel"]=cartp; 
  
    object["potentialBuyTime"]=maybetime; 
    object["potentialBuyerProvince"]=prov;
    object["potentialBuyerCity"]=city; 
    object["potentialBuyerDealer"]=dealer;
    object["potentialBuyProductModel"]=cartype;
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

// 开启动画
var openCarAnimation = function(){ 
    var canvas1 = document.getElementById('frameAnimateCar1');
    var canvas2 = document.getElementById('frameAnimateCar2');
    var canvas3 = document.getElementById('frameAnimateCar3');
    var canvas4 = document.getElementById('frameAnimateCar4');
    var canvas5 = document.getElementById('frameAnimateCar5');
    var canvas6 = document.getElementById('frameAnimateCar6');
    var canvas0 = document.getElementById('frameAnimateCar');

    var frame1 = new frameAnimate(canvas1,fpsobj , stopfn);
    var frame2 = new frameAnimate(canvas2,fpsobj , stopfn);
    var frame3 = new frameAnimate(canvas3,fpsobj , stopfn);
    var frame4 = new frameAnimate(canvas4,fpsobj , stopfn);
    var frame5 = new frameAnimate(canvas5,fpsobj , stopfn);
    var frame6 = new frameAnimate(canvas6,fpsobj , stopfn);
    var frame0 = new frameAnimate(canvas0,fpsobj , stopfn);

    frame1.play();
    frame2.play();
    frame3.play();
    frame4.play();
    frame5.play();
    frame6.play();
    frame0.play();
}

// 添加所有事件
function addEvents(){
    console.log("addEvents");
    // 首页
    $(".page1 .js_start").bind("click",startgame);//即刻追踪
    $(".page1 .js_rule").bind("click",poprule);     //活动规则
    $(".rulePage .content .cls").bind("click",clsrule);//关闭活动规则

    //游戏页面
    $(".page2 .js_catch").bind("click",catchShdow);//捕捉魅影

    //成功页面
    $(".page3 .next").bind("click",showform);//打开表单
    //失败页面
    $(".page3 .js_tryagain").bind("click",restart);//再玩一次
    $(".page3 .js_getticket").bind("click",showform);//提交表单

    //表单页面
    $(".page4 .js_submit").bind("click",submitform);//提交表单

    //成功关闭按钮
    $(".successPage .content .cls").bind("click",clssuccess);
};

var urlHost = "/";
 
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
        url :urlHost + 'dealer/findAllProvince.jspx',
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
    doPageview("/Guangzhou_2016Autoshow/Landing_Page");
    addEvents();

    var canvasbg = document.getElementById('frameAnimatebg');
    var ih = $(window).height();
    var framebg = new frameAnimate(canvasbg,{width: 750,height: ih*2/3, fps:10,frame: 36,loop: true,imgtype:".jpg"},stopfn);
    framebg.play();

    var canvasbg0 = document.getElementById('frameAnimatebg0');
    var framebg0 = new frameAnimate(canvasbg0,{width: 750,height: ih, fps:5,frame: 2,loop: true,imgtype:".jpg"},stopfn);
    framebg0.play();

    $(".page1").removeClass("dn");
    $(".initPage").addClass("dn");
    console.log("show_page");
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
            //if(loadingScale < (loaded / instance.images.length)){
            loadingScale = loaded / instance.images.length; 
            // $(".initPage .loading").html('LOADING' + parseInt(loadingScale * 100) + '%');
            $(".initPage .loading").html('LOADING...');
            if (loaded == instance.images.length) { 
                show_page(me);
            }
            //}
        });
    } 
}

var offsetY = ["0px","40px","180px","100px","140px"];
var faillist = ["page3fail.png","page3fail2.png","page3fail3.png","page3fail4.png"];
var randomNum;

var fpsobj={width: 569,height: 307, fps:20,frame: 3,loop: true,imgtype:".png"};
var stopfn = function(){console.log("==stop==")};

doPageview("/Guangzhou_2016Autoshow/Loading_Page");
imgLoad();
//加载
var canvasld = document.getElementById('ldAnimateCar');
var frameld = new frameAnimate(canvasld,{width: 750,height: 185, fps:10,frame: 3,loop: true,imgtype:".jpg"} , stopfn);
frameld.play();  
//按钮动画
var canvasbtnbg1 = document.getElementById('btnAnimate');
var framebtn1 = new frameAnimate(canvasbtnbg1 ,{width: 386,height: 85, fps:10,frame: 5,loop: true,imgtype:".png"} , stopfn);
framebtn1.play();  
initSelectData("buyingDealerProvince","","buyingDealerCity","", "carBuyingDealer", "");



