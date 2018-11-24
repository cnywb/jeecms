(function ($) {
    Mgm = function() {
		this.init();
    };
    $.extend(Mgm.prototype, {
		
        init: function(data) {
         doPageview('page2_setinfo')
			this.year = '2015';
			this.num = '0';
			this.submitData = {};
                        this.block = false;   
			this.channel = window.location.search.split('?channel=')[1];
                      $('.page').css('height',$(document).height()+20)
			$.each(Resource_Province, function(index, item) {
				$('#provinceDDL').append('<option value="' + item.key + '">' + item.val + '</option>');
			});
			this.month(this.year)
			this.initEvent();
			this.inivitation =$('.invitation .num0').html();

                     
			var self = this;
			$('.shareFri a').click(function(){
				self.info('invite');
				
			})
			
			$('.btn a:nth-child(2)').click(function(){
				$('.popup').fadeIn()
			})
			
			$('.btn a:nth-child(1)').click(function(){
				if(self.block == false){
					self.block = true;
					self.info('save');
				}
				
			})
			
			$('.popup').click(function(){
				$(this).hide()
			})
		
		},
		
		
		initEvent:function (){
			var self = this;
			$('.province').change(function(e) {
				var pKey = $(this).val();
				var ptxt = $(this).find('option:selected').text();
				$(this).prev().html(ptxt)
				var cityArray = GetCitiesByProvinceKey(pKey);
				$(this).parent().next().find('select').empty();
				var $div = $(this);
					$(this).parent().next().find('select').append('<option value="0">请选择市</option>')
				$.each(cityArray, function(index, item) {
					$div.parent().next().find('select').append('<option value="' + item.val + '">' + item.val + '</option>');
				});
			});
			
			$('.year').change(function(e){
				var selectyear = $(this).val()
				self.year = selectyear;
				$(this).prev().html(self.year)
				self.month(self.year)
			})
			
			$('.city').change(function(e) {
				var pKey = $(this).val();
				$(this).prev().html(pKey)
			});
			
		
			$('.carModel').change(function(e) {
				var model = $(this).val();
				$(this).prev().html(model)
			});
			
			
		},
		
		info:function(from){
			var self = this;
			var OwnerName = $('.carOwnerName').val();
			var OwnerMobile = $('.carOwnerMobilePhoneNo').val();
			var Ownercar =$('.selfinfo .selectCar').html();
			if(OwnerName == '' || OwnerMobile== ''){
				alert('请输入您的姓名联系跟联系方式')
				return false;
			}
			
			if(OwnerMobile != ''){
				var len = OwnerMobile.length;
				console.log(len)
				if(!(/^1[34578]\d{9}/.test(OwnerMobile)) && len!=11) {
					alert('手机号码有误，请重新输入')
					return false;
				}
			}
		
			var custmerName =$('.num'+self.num+' .customerName').val();
			var custmerMobile =$('.num'+self.num+' .customerMobile').val();
			
			if(custmerMobile != ''){
				var clen = custmerMobile.length;
				if(!(/^1[34578]\d{9}/.test(custmerMobile)) && clen!=11) {
					alert('手机号码有误，请重新输入')
					return false;
				}
			}
			
			var custmerProvince = $('.num'+self.num+' .selectProvince').html();
			var custmerCity =$('.num'+self.num+' .selectCity').html();
			var custmerCar =$('.num'+self.num+' .setType .selectval').html();
			var year = $('.num'+self.num+' .selectYear').html();
			var month = $('.num'+self.num+' .selectMonth').html();
			var day = $('.num'+self.num+' .selectDay').html();
			var date = '';
			if(year == '年' ||month == '月' || day == '日'){
				date = '';
			}else{
				date = year + '-'+month+'-'+day;
			}
			
			
		/*	if(custmerName == '' || custmerProvince == '请选择省' || custmerCity == '请选择市' || year == '年' ||month == '月' || day == '日'){
				alert('请填写推荐人信息')
				return false;
			}*/

				self.submitData["infoList["+self.num+"].channel"]=self.channel
				self.submitData["infoList["+self.num+"].carOwnerName"]=OwnerName;
				self.submitData["infoList["+self.num+"].carOwnerMobilePhoneNo"]=OwnerMobile;
				self.submitData["infoList["+self.num+"].carModel"]=Ownercar;
				self.submitData["infoList["+self.num+"].customerName"]=custmerName;
				self.submitData["infoList["+self.num+"].customerMobilePhoneNo"]=custmerMobile;
				self.submitData["infoList["+self.num+"].customerProvince"]=custmerProvince;
				self.submitData["infoList["+self.num+"].customerCity"]=custmerCity;
				self.submitData["infoList["+self.num+"].intentionalCarModel"]=custmerCar;
				self.submitData["infoList["+self.num+"].intentionalBuyDate"]=date;
			        self.submitData["infoList["+self.num+"].activityName"]='MGM';
			
			if(from=='save'){
				self.save(self.submitData);
			}else{
				self.num++;
				self.newfriend(self.num);
			}
		
		},
		
		newfriend:function(num){
			$('.invitation>div').append('<form class="num'+num+'"></form>')
			$('.invitation>div .num'+num+'').html(this.inivitation)
			var winhei = $('.page').height() + 285
			$('.page').css('height',winhei)
			this.clear(num)
			this.initEvent();
		},
		
		
		clear:function(snum){
			$('.num'+snum+' .name').empty()
			$('.num'+snum+' .mobile').empty()
			$('.num'+snum+' .city').empty()
			$('.num'+snum+' .selectProvince').html('请选择省')
			$('.num'+snum+' .selectCity').html('请选择市')
			$('.num'+snum+' .selectval').html('请选择车型')
			$('.num'+snum+' .selectYear').html('年')
			$('.num'+snum+' .selectMonth').html('月')
			$('.num'+snum+' .selectDay').html('日')
		},
		
		month:function(month){
			$('.month').empty();
			$('.month').append('<option value="0">月</option>')
			if(month == 2015){
				for(var i=8;i<=12;i++){
					$('.month').append('<option value='+i+'>'+i+'</option>')
				}
			}else{
				for(var i=1;i<=12;i++){
					$('.month').append('<option value='+i+'>'+i+'</option>')
				}
			}
			var self= this;
			$('.month').change(function(e){
				var selectmonth = $(this).val()
				$(this).prev().html(selectmonth)
				self.day(selectmonth)
			})
			
		},
		
		day:function(month){
			$('.day').empty();
			$('.day').append('<option value="0">日</option>')
			if(month == 2){
				for(var i=1;i<=28;i++){
					$('.day').append('<option value='+i+'>'+i+'</option>')
				}
			}
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||  month == 10 || month == 12){
				for(var i=1;i<=31;i++){
					$('.day').append('<option value='+i+'>'+i+'</option>')
				}
				
			}else{
				for(var i=1;i<=30;i++){
					$('.day').append('<option value='+i+'>'+i+'</option>')
				}
			}	
				
			$('.day').change(function(e){
				var selectday = $(this).val()
				$(this).prev().html(selectday)
			})
		},
		
		save:function(data){
			console.log(data)
			var self =this;
			$.ajax( {
				url :'add.jspx',
				type : 'post',
				data:data,
				dataType:'json',
				success : function(result) {
				 alert(result.message);
				 self.block = false;
				},error:function(){
					self.block = false;
				   alert("网络或数据异常，操作失败！");
			   }
			});
		}


	
    });
})(jQuery);



$(function() {

  var mgm = new Mgm();

});


function isChinese(str) {
    var str = str.replace(/(^\s*)|(\s*$)/g, '');
    if(!(/^[\u4E00-\uFA29]*$/.test(str) && (!/^[\uE7C7-\uE7F3]*$/.test(str)))) {
        return false;
    }
    return true;
}
function isMobile(str) {
	var len = str.length;
	console.log(len)
    if(/^1[3578]\d{9}/.test(str) && len==11) {
        return true;
    }else{
		alert('手机号码有误，请重新输入')
		return;
	}
	
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return decodeURI(r[2]); return null;
}

function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
		$('.shareTips').show();
		$('.shareTips .sharewx').show();
		doPageview('Levis_501CT_weixin_Share')
    }else{
		$('.shareTips').show();
		$('.sharebtn_wb').show();
		$('.shareTips .sharewb').show()
		doPageview('Levis_501CT_weibo_Share')
    }
}

function doTrack(name) {
	console.log('track',name)
	ga('send', {
	  'hitType': 'event',          // Required.
	  'eventCategory': "",   // Required.
	  'eventAction': 'click',      // Required.
	  'eventLabel': name,
	  'eventValue': 1
	});
};
function doPageview(name) {
	console.log('doPageview',name)
	ga('send', {
	  'hitType': 'event',          // Required.
	  'eventCategory': "mgm",   // Required.
	  'eventAction': 'page',      // Required.
	  'eventLabel': name,
	  'eventValue': 1
	});
};