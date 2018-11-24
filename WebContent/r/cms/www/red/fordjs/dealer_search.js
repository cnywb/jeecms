/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var dealer_info=[];
var loadData=function(){
	        if(dealer_info.length>0){
	        	return;
	        }
	        $.ajax( {
	            url :'../ocms/toolkit/dealer/bigarea/findAllJSONP.htm',
	            type : 'post',
	            dataType:'jsonp',
	            async:false,
	            jsonp:"jsonpcallback",
	            data: {},
	            success : function(result) {
	            	dealer_info=result;
	            },error:function(XMLHttpRequest, textStatus, errorThrown){
	                 alert( errorThrown);
	            }
	         });
	     
};
this.ford = this.ford || {};

(function() {
    var TotalOverlay = function(point, text, total, isdealer, clickfun, dealerInfo) {
        this.init(point, text, total, isdealer, clickfun, dealerInfo);
    };
    
    var p = TotalOverlay.prototype = new BMap.Overlay();
    
    p.init = function(point, text, total, isdealer, clickfun, dealerInfo) {
        this.$point = point;
        this.$text = text;
        this.$total = total;
        this.$isdealer = isdealer;
        this.$clickfun = clickfun;
        this.$dealerInfo = dealerInfo;
    //        console.log(this.$clickfun);
    };
    p.initialize = function(map, a, b) {
        if (!this.$isdealer) {
            return this.initTotalOverlay(map, a, b);
        } else {
            return this.initDealerOverlay(map, a, b);
        }
    };
    p.initTotalOverlay = function(map, a, b) {
        this._map = map;
        var div = this._div = document.createElement('div');
        div.className = 'tagDTotal';
        var title = document.createElement('div');
        title.innerHTML = this.$text;
        div.appendChild(title);
        var total = document.createElement('div');
        total.innerHTML = this.$total;
        div.appendChild(total);
        map.getPanes().labelPane.appendChild(div);
        if (this.$clickfun) {
            $(div).click({
                map:map
            }, this.$clickfun);
        }
        return div;
    };
    p.initDealerOverlay = function(map, a, b) {
        this._map = map;
        var div = this._div = document.createElement('div');
        div.className = 'tagD';
        map.getPanes().labelPane.appendChild(div);
        var content = '<div style="margin:0;line-height:20px;padding:2px;">'
        //                    '<img src="../img/baidu.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
        + '地址：' 
        + this.$dealerInfo.address
        + '<br/>电话：'
        + this.$dealerInfo.tel
        + '<br/>传真：'
        + this.$dealerInfo.fex
        + '<br/>电子邮件：'
        + this.$dealerInfo.email
        + '<br/>服务电话：'
        + this.$dealerInfo.service_tel
        + '</div>';
        var dealerWindow = new BMapLib.SearchInfoWindow(map, content, {
            title: this.$dealerInfo.dealer_name, //标题
            panel : "panel", //检索结果面板
            enableAutoPan : true, //自动平移
            searchTypes :[
            //                BMAPLIB_TAB_SEARCH,   //周边检索
            BMAPLIB_TAB_TO_HERE  //到这里去
            //                BMAPLIB_TAB_FROM_HERE //从这里出发
            ]
        });
        if (this.$clickfun) {
            $(div).click({
                dealerWindow : dealerWindow
            }, this.$clickfun);
        }
        return div;
    }
    p.draw = function() {
        var pixel = this._map.pointToOverlayPixel(this.$point);
        this._div.style.left = pixel.x -45 + 'px';
        this._div.style.top  = pixel.y - 66 + 'px';
    //        console.log('x:' + pixel.x + ', y:' + pixel.y);
    };
    ford.TotalOverlay = TotalOverlay;
})();

(function() {
    var FordMap = function() {
        this.init();
    }
    
    var p = FordMap.prototype;
    
    p.init = function() {
        this.$areaflag = false;
        this.$provinceflag = false;
        this.$cityflag = false;
        this.$dealerflag = false;
        //        this.$issearch = false;
        this.$map = new BMap.Map("map");
        this.$map.centerAndZoom(new BMap.Point(108.890303, 34.314949), 5);
        //        this.$map.centerAndZoom(new BMap.Point(121.43081486545,31.082730967747), 12);
        this.$map.disableDoubleClickZoom();
        //        this.$map.disableMapClick();
        this.$map.addControl(new BMap.NavigationControl());
        //        this.drawdealerOverlay();
        
        loadData();
        this.drawAreaOverlay();
        this.addEventListener();
    };
    p.addEventListener = function() {
        var cur = this;
        this.$map.addEventListener("zoomend", function(){
            if (this.getZoom() < 7 && !cur.$areaflag) {
                //                console.log(0);
                cur.$map.clearOverlays();
                cur.drawAreaOverlay();
            }
            if (this.getZoom() >= 7 && this.getZoom() < 9 && !cur.$provinceflag) {
                //                console.log(7);
                cur.$map.clearOverlays();
                cur.drawProvinceOverlay();
            }
            if (this.getZoom() >= 9 && this.getZoom() < 12 && !cur.$cityflag) {
                //                console.log(9);
                cur.$map.clearOverlays();
                cur.drawCityOverlay();
            }
            if (this.getZoom() >= 12 && !cur.$dealerflag) {
                //                console.log(12);
                cur.$map.clearOverlays();
                cur.drawdealerOverlay();
            }
        //            console.log("地图缩放至：" + this.getZoom() + "级");
        });
    }
    p.drawAreaOverlay = function() {
        var cur = this;
        $(dealer_info).each(function() {
            var point = new BMap.Point(this.pointx, this.pointy);
            var mc = new ford.TotalOverlay(point, this.title, this.total, false, function(event) {
                event.data.map.setZoom(7);
                event.data.map.panTo(point);
            });
            cur.$map.addOverlay(mc);
        });
        this.$areaflag = true;
        this.$provinceflag = false;
        this.$cityflag = false;
        this.$dealerflag = false;
    };
    p.drawProvinceOverlay = function() {
        var cur = this;
        $(dealer_info).each(function() {
            $(this.provinces).each(function() {
                var point = new BMap.Point(this.pointx, this.pointy);
                var mc = new ford.TotalOverlay(point, this.title, this.total, false, function(event) {
                    event.data.map.setZoom(9);
                    event.data.map.panTo(point);
                });
                cur.$map.addOverlay(mc);
            });
        });
        this.$areaflag = false;
        this.$provinceflag = true;
        this.$cityflag = false;
        this.$dealerflag = false;
    };
    p.drawCityOverlay = function() {
        var cur = this;
        $(dealer_info).each(function() {
            $(this.provinces).each(function() {
                $(this.citys).each(function() {
                    var point = new BMap.Point(this.pointx, this.pointy);
                    var mc = new ford.TotalOverlay(point, this.title, this.total, false, function(event) {
                        event.data.map.setZoom(12);
                        event.data.map.panTo(point);
                    });
                    cur.$map.addOverlay(mc);
                });
            });
        });
        this.$areaflag = false;
        this.$provinceflag = false;
        this.$cityflag = true;
        this.$dealerflag = false;
    };
    p.drawdealerOverlay = function() {
        var cur = this;
        $(dealer_info).each(function() {
            $(this.provinces).each(function() {
                $(this.citys).each(function() {
                    $(this.dealers).each(function() {
                        var point = new BMap.Point(this.pointx, this.pointy);
                        var mc = new ford.TotalOverlay(point, null, null, true, function(event) {
                            //                            event.data.map.setZoom(14);
                            //                            event.data.map.panTo(point);
//                            console.log(event.data.dealerWindow.open);
                            //                            console.log(event.data.dealerWindow._isOpen);
                            //                            console.log(event.data.dealerWindow._point);
                            event.data.dealerWindow.open(point);
                        //                            console.log(event.data.dealerWindow._isOpen);
                        //                            console.log(event.data.dealerWindow._point);
                        }, this);
                        cur.$map.addOverlay(mc);
                    });
                });
            });
        });
        this.$areaflag = false;
        this.$provinceflag = false;
        this.$cityflag = false;
        this.$dealerflag = true;
    };
    p.todealerInfoOverlay = function(dealer) {
        if (dealer) {
            //            console.log('x:' + dealer.pointx + ',y:' + dealer.pointy);
            var cur = this;
            var point = new BMap.Point(dealer.pointx, dealer.pointy);
            this.$map.setZoom(14);
            setTimeout(function() {
                cur.$map.panTo(point);
            }, 100);
            
        }
    };
    p.todealersOverlay = function(dealers) {
        if (dealers.length > 0) {
            //            this.drawdealerOverlay();
            //            console.log('x:' + dealers[0].pointx + ',y:' + dealers[0].pointy);
            var cur = this;
            var point = new BMap.Point(dealers[0].pointx, dealers[0].pointy);
            this.$map.setZoom(12);
            setTimeout(function() {
                cur.$map.panTo(point);
            }, 50);
        }
    };
    p.tocitysOverlay = function(citys) {
        if (citys.length > 0) {
            //            console.log('x:' + citys[0].pointx + ',y:' + citys[0].pointy);
            //            this.drawCityOverlay();
            var cur = this;
            var point = new BMap.Point(citys[0].pointx, citys[0].pointy);
            this.$map.setZoom(9);
            setTimeout(function() {
                cur.$map.panTo(point);
            }, 50);
        }
    };
    ford.FordMap = FordMap;
})();


$(function() {
	loadData();
    var provinces = $('#provinces');
    provinces.html('');
    var citys = $('#citys');
    var infos = $('.infor');
    var infotemp = $('#infotemp');
    var infolist = $('#infolist');
    var infomap = $('#infomap');
    var provinceBtn = $('.provinceBtn');
    var cityBtn = $('.cityBtn');
    var mapBtn = $('#mapBtn');
    var listBtn = $('#listBtn');
    
    fordMap = new ford.FordMap();
    
    $(dealer_info).each(function() {
        $(this.provinces).each(function() {
            var province = $('<li class="province">' + this.title + '</li>');
            province.data({
                'citys': this.citys, 
                'title': this.title
            });
            province.click(function() {
                var self = $(this);
                var citys_data = self.data('citys');
                provinceBtn.html(self.data('title'));
                provinceBtn.data('citys', self.data('citys'));
                provinces.slideUp(300);
                citys.html('');
                if (citys_data && citys_data.length) {
                    $(citys_data).each(function() {
                        var city = $('<li class="city">' + this.title + '</li>');
                        city.data({
                            'titls' : this.title,
                            'dealers' : this.dealers
                        });
                        city.click(function() {
                            var self = $(this);
                            cityBtn.html(self.data('titls'));
                            cityBtn.data('dealers', self.data('dealers'));
                            citys.slideUp(300);
                        });
                        citys.append(city);
                    });
                }
            //                console.log($(this).data('title'));
            });
            provinces.append(province);
        });
    });
    provinceBtn.click(function() {
        //        console.log('province');
        provinces.slideDown(500);
    });
    cityBtn.click(function() {
        //        console.log('city');
        citys.slideDown(500);
    });
    listBtn.click(function() {
        infolist.show();
        infomap.hide();
        infos.html('');
        if (cityBtn.data('dealers')) {
            var dealers = cityBtn.data('dealers');
            if(dealers.length > 0){
                $(dealers).each(function() {
                    var dealer = this;
                    var infotempclone = infotemp.clone();
                    infotempclone.find('h3 label').html(this.dealer_name);
                    infotempclone.find('p label:eq(0)').html(this.address);
                    infotempclone.find('p label:eq(1)').html(this.tel);
                    infotempclone.find('p label:eq(2)').html(this.fex);
                    infotempclone.find('p label:eq(3)').html(this.email);
                    infotempclone.find('a').click(function() {
                        $('html,body').animate({
                            scrollTop: parseInt($('.art').offset().top)
                        },
                        1000, function() {
                            fordMap.todealerInfoOverlay(dealer);
                            infolist.hide();
                            infomap.show();
                        });
                    
                    });
                    infotempclone.appendTo(infos);
                });
                infos.children('div').show();
            }
        } else if(provinceBtn.data('citys')) {
            var citys = provinceBtn.data('citys');
            if (citys.length > 0) {
                $(citys).each(function() {
                    var dealers = this.dealers;
                    if(dealers.length > 0){
                        $(dealers).each(function() {
//                            console.log(this);
                            var dealer = this;
                            var infotempclone = infotemp.clone();
                            infotempclone.find('h3 label').html(this.dealer_name);
                            infotempclone.find('p label:eq(0)').html(this.address);
                            infotempclone.find('p label:eq(1)').html(this.tel);
                            infotempclone.find('p label:eq(2)').html(this.fex);
                            infotempclone.find('p label:eq(3)').html(this.email);
                            infotempclone.find('a').click(function() {
                                $('html,body').animate({
                                    scrollTop: parseInt($('.art').offset().top)
                                },
                                1000, function() {
                                    fordMap.todealerInfoOverlay(dealer);
                                    infolist.hide();
                                    infomap.show();
                                });
                    
                            });
                            infotempclone.appendTo(infos);
                        });
                        infos.children('div').show();
                    }
                });
            }
        }
        
        
    });
    mapBtn.click(function() {
        if (cityBtn.data('dealers')) {
            fordMap.todealersOverlay(cityBtn.data('dealers'));
        } else if(provinceBtn.data('citys')) {
            fordMap.tocitysOverlay(provinceBtn.data('citys'));
        }
        infolist.hide();
        infomap.show();
    });
    var isPanelShow = false;
    //显示结果面板动作
    $("#showPanelBtn").click(function(){
        var self = $(this);
        if (isPanelShow == false) {
            isPanelShow = true;
            self.css('left', 300).html('隐藏检索结果面板<br/>>');
            $('#panelWrap').css('width', 300);
            $('#map').css('margin-left', 300);
        } else {
            isPanelShow = false;
            self.css('left', 0).html('显示检索结果面板<br/><');
            $('#panelWrap').css('width', 0);
            $('#map').css('margin-left', 0);
        }
    });
    
    $(document).click(function(e) {
        if ($(e.target).hasClass('provinceBtn')) {
            citys.slideUp(300);
        } else if ($(e.target).hasClass('cityBtn')) {
            provinces.slideUp(300);
        } else {
            provinces.slideUp(300);
            citys.slideUp(300);
        }
    });
});
