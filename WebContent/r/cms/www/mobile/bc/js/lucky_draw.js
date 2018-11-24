var city_py={"TBD":"tbd",
"大连":"Dalian",
"东莞":"Dongguan",
"福州":"Fuzhou",
"贵阳":"guiyang",
"哈尔滨":"Harbin ",
"合肥":"hefei",
"呼和浩特":"Hohehot",
"济南":"jinan",
"昆明":"kunming",
"兰州":"Lanzhou ",
"南昌":"Nanchang",
"南京":"Nanjing",
"南宁":"Nanning",
"南通":"natong",
"宁波":"Ningbo",
"厦门":"xiamen",
"深圳":"Shenzhen",
"苏州":"suzhou",
"太原":"Taiyuan",
"天津":"tianjan",
"潍坊":"huaifang",
"温州":"wenzhou",
"乌鲁木齐":"Urumqi",
"武汉":"wuhan",
"西安":"xian",
"西宁":"Xining",
"徐州":"xuzhou",
"烟台":"yantai",
"义乌":"yuwu",
"银川":"Yinchuan",
"长沙":"Changsha",
"郑州":"zhenzhou",
"重庆":"chongqing"};

function load(){
var beauty = new Image();  
beauty.src = "http://images.cnblogs.com/cnblogs_com/html5test/359114/r_test.jpg"; 
if(beauty.complete){
   drawBeauty(beauty);
}else{
   beauty.onload = function(){
     drawBeauty(beauty);
   };
   beauty.onerror = function(){
     window.alert('美女加载失败，请重试');
   };
};   
}//load



function doLuckyDraw(city,mobilePhoneNo){
      var code="BC_"+city_py[city].toUpperCase();
      $.ajax({
             url:'../../../toolkit/luckydraw/doDrawForPotentialCustomerUserJSONP.htm',
             dataType:"jsonp",
             data:{"mobilePhoneNo":mobilePhoneNo,"code":code};
             jsonp:"jsonpcallback",
             success:function(data){
              // alert(data.message);//抽奖结果消息
              // alert(data.resultId);//中奖序列号,只有data.status为9时才有值
              // alert(data.status);//状态，9表示中奖,其它均表示未中奖
              if (data.status == '9') {
              	$(".lucky").show();
              	LuckyCard.case({
			        ratio: .7
			    }, function() {
			        $(".notice").fadeIn();
			        this.clearCover();
			    });
			    $(".luckyDraw").fadeIn();
              } else {
              	$(".unlucky").show();
              	LuckyCard.case({
			        ratio: .9
			    }, function() {
			        //$(".notice").fadeIn();
			        this.clearCover();
			    });
			    $(".luckyDraw").fadeIn();
              }
             }
        });
}