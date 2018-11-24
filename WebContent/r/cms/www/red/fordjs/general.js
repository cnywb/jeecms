/*
 * Create: 2009-12-1
 * Author: Zino Yue
 */
Element.addMethods({
    findElement: function(){
        return false;
    },
    preventDefault: function(){
        return;
    },
    stopPropagation: function(){
        return;
    }
});

var Animation = Class.create({
    initialize: function(fps){
        this.sideTop = 0;
        this.interval = Math.round(1000 / fps);
        this.dialog = false;
        this.objSpec = [];
    },
    _moveSideBar: function(){
        var bar = $('frame_sidebar').down('ul');
        var sh = utility.getScrollHeight() + this.frameTop;
        //bar.setStyle('top:' + sh + 'px');
        var distance = Math.abs(this.sideTop - sh);
        var unit = Math.PI / 2 / 600;
        this.frame++;
        var offset = distance * Math.sin(this.frame * unit);
        if (this.sideTop < sh) {
            this.sideTop += offset;
            bar.setStyle('top:' + this.sideTop + 'px');
        } else if (this.sideTop > sh) {
            this.sideTop -= offset;
            bar.setStyle('top:' + this.sideTop + 'px');
        }
        if (offset < 1 && Math.abs(this.sideTop - sh) < 1) {
            this.sideTop = sh;
            bar.setStyle('top:' + sh + 'px');
            clearInterval(this.timer);
            this.playing = false;
            this.frame = 0;
        }
        //if (this.frame == 600) {
        //    clearInterval(this.timer);
        //    this.playing = false;
        //    this.frame = 0;
        //}
    },
    bindSideBar: function(event){
        if (page.dialog) {
            //page.centerLayer();
        } else {
            this.frame = 0;
            if (!this.playing) {
                this.playing = true;
                this.timer = setInterval(this._moveSideBar.bind(this), this.interval);
            }
        }
    },
    initSideBar: function(){
        if ($('frame_sidebar')) {
            var offset = -1 * $('frame_sidebar').offsetTop;
            var bar = $('frame_sidebar').down('ul');
            this.sideTop = offset + 100 + 20;
            this.frameTop = this.sideTop;
            bar.setStyle('top:' + this.sideTop + 'px');
            Event.observe(window, 'scroll', animate.bindSideBar.bind(animate));
        }
    },
    _switchTab: function(event){
        var obj = event.findElement();
        var element = obj.nodeName.toLowerCase();
        var node = obj.up('.tabmenu');
        var nodes = node.select(element);
        var index = nodes.indexOf(obj);//find the tab index of the menu
        var prefix = node.identify().split('_')[1];
        if (!$(prefix + '_' + index)) {
            return;
        }
        for (var i = 0; i < nodes.length; i++) {//update status
            if (i == index) {
                $(nodes[i].parentNode).addClassName('cur');
                $(prefix + '_' + i).removeClassName('hide');
            } else {
                $(nodes[i].parentNode).removeClassName('cur');
                $(prefix + '_' + i).addClassName('hide');
            }
        }
    },
    bindTab: function(identify){
        if (!$(identify)) 
            return false;
        var tabmenu;
        if (identify != '') 
            tabmenu = $(identify).select('.tabmenu');
        else 
            tabmenu = $$('.tabmenu');
        tabmenu.each(function(ul){
            if (!ul.hasClassName('server')) {
                ul.select('a').each(function(a){
                    Event.observe(a, 'click', animate._switchTab.bind(animate));
                });
            }
        });
    },
    _marquee: function(item){
        var obj = $(item).select('table td.cont');
        if (obj[1].offsetWidth - item.scrollLeft <= 0) 
            item.scrollLeft -= obj[0].offsetWidth;
        else 
            item.scrollLeft++;
    },
    _loopMarquee: function(){
        var len = this.marqueeItems.length;
        for (i = 0; i < len; i++) {
            this._marquee(this.marqueeItems[i]);
        }
    },
    bindMarquee: function(identify){
        var marquee;
        if (identify != '') 
            marquee = $(identify).select('.marquee');
        else 
            marquee = $$('.marquee');
        this.marqueeItems = marquee;
        var dels = [];
        marquee.each(function(div, i){
            var obj = $(div).down('table td');
            if (obj.select('img').length <= 7) {
                var mid = marquee.indexOf(div);
                dels.push(mid);
                return;
            }
            var clone = obj.innerHTML;
            obj.next('td').innerHTML += clone;
            Event.observe(div, 'mouseover', function(){
                clearInterval(animate.mt);
            });
            Event.observe(div, 'mouseout', function(){
                animate.mt = setInterval(animate._loopMarquee.bind(animate), animate.interval);
            });
        });
        for (i = 0; i < dels.length; i++) {
            marquee.splice(dels[i], 1);
        }
        this.mt = setInterval(this._loopMarquee.bind(this), this.interval);
    },
    _showTip: function(event){
        var obj = event.findElement();
        var tip = $('tip');
        var x = obj.offsetLeft - tip.getWidth() + obj.getWidth();
        var y = obj.offsetTop - tip.getHeight() - 4;
        if (x < -3) 
            x = -3;
        tip.setStyle('left:' + x + 'px');
        tip.setStyle('top:' + y + 'px');
        var arrow = $('arrow');
        var ay = obj.offsetTop - arrow.getHeight() - 1;
        arrow.setStyle('left:' + obj.offsetLeft + 'px');
        arrow.setStyle('top:' + ay + 'px');
        var idx = Number(obj.innerHTML);
        if (page.calData[idx]) {
            var items = page.calData[idx];
            var content = '';
            for (i = 0; i < items.length; i++) {
                content = content + items[i];
                if (i < 2) {
                    content += '<br/>';
                } else {
                    if (items.length > i + 1) {
                        content += '...';
                        break;
                    }
                }
            }
            tip.update(content);
        }
        tip.removeClassName('hide');
        arrow.removeClassName('hide');
    },
    _hideTip: function(){
        $('tip').addClassName('hide');
        $('arrow').addClassName('hide');
    },
    bindTip: function(event){
        var cal = $('widget_calendar');
        if (cal) {
            cal.select('td.event').each(function(a){
                Event.observe(a, 'mouseover', animate._showTip.bind(animate));
                Event.observe(a, 'mouseout', animate._hideTip.bind(animate));
            });
        }
    },
    bindEditor: function(){
        $('pro_announce').down('p').observe('click', this.showEditor.bind(this));
    },
    showEditor: function(event){
        var obj = event.findElement();
        var text = obj.innerHTML;
        obj.addClassName('hide');
        var form = obj.next();
        $('tb_announce').setValue(text);
        form.removeClassName('hide');
        $('tb_announce').observe('keydown', this.textCounter.bind(this));
        $('tb_announce').observe('keyup', this.textCounter.bind(this));
        Event.observe(document, 'mousedown', this.updateAnnounce.bind(this));
        $('tb_announce').activate();
    },
    textCounter: function(event){
        var maxlimit = 80;
        var field = event.findElement();
        if (field.value.length > maxlimit) // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
        // otherwise, update 'characters left' counter
    },
    updateAnnounce: function(event){
        var obj = event.findElement();
        if (obj != $('tb_announce')) {
            if ($F('tb_announce') != $('announce_text').innerHTML) {
                var ajax = new Ajax.Request('speak.action', {
                    method: 'get',
                    parameters: {
                        'foscontent': $F('tb_announce')
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            if (json.message) {
                                page.showDialog(json.message);
                            } else {
                                $('announce_text').update($F('tb_announce'));
                            }
                        } else {
                            alert('数据获取失败。');
                        }
                    },
                    onFailure: function(){
                        alert('服务器连接失败。');
                    }
                });
            }
            $('announce_text').removeClassName('hide');
            $('announce_form').addClassName('hide');
            Event.stopObserving(document);
        }
    },
    bindSend: function(users){
        this.msgtoList = $H();
        $('useript').observe('click', function(){
            //$('typeuser').focus();
            animate._showList();
        });
        if ($('msgto').down('.showdrop')) {
            $('msgto').down('.showdrop').observe('click', this._showList.bind(this));
            $('msglist').select('div.list input').each(function(o){
                o.observe('click', animate._updateMsgTo.bind(animate)).checked = false;
            });
            $('msglist').down('select').observe('change', this._showGroup.bind(this));
            $('selectAll').observe('click', function(event){
                var obj = event.findElement();
                var list = animate.msgtoList;
                $('msglist').select('div.list input').each(function(o){
                    if (o.up().hasClassName('hide')) {
                        return;
                    }
                    o.checked = obj.checked;
                    if (obj.checked) {
                        var value = list.get($F(o));
                        if (!value) {
                            list.set($F(o), o.next().innerHTML);
                        }
                    } else {
                        list.unset(o.value);
                    }
                });
            });
            $('btnSelect').observe('click', function(){
                animate._genMsgTo();
                $('msglist').addClassName('hide');
            });
        }
        $('btnSend').observe('click', function(event){
            var obj = event.findElement();
            var form = obj.up('form');
            var data = form.serialize(true);
            if (!data.chkName && $('msgto').down('.showdrop')) {
                alert('请选择至少一个好友。');
                animate._showList();
                return;
            }
            if (!data.iTitle) {
                alert('请输入短消息标题。');
                form.down('input[name="iTitle"]').focus();
                return;
            }
            if (!data.txtContent) {
                alert('请输入短消息内容。');
                form.down('textarea').focus();
                return;
            }
            obj.up('form').submit();
        });
        if (users) {
            var list = this.msgtoList;
            users.split(',').each(function(o){
                var cbox = $('msglist').down('.list input[value="' + o + '"]');
                if (cbox) {
                    cbox.checked = true;
                    var name = cbox.next().innerHTML;
                    list.set(o, name);
                }
            });
            this._genMsgTo();
        }
    },
    _showList: function(event){
        //var obj = event.findElement();
        var pop = $('msglist');
        if (pop.hasClassName('hide')) {
            var pos = $('msgto').positionedOffset();
            var top = pos.top + $('msgto').getHeight();
            pop.setStyle({
                'top': top + 'px',
                'left': pos.left + 'px'
            }).removeClassName('hide');
            this._checkMsgSelect();
            $('typeuser').focus();
            $('typeuser').readOnly = true;
        } else {
            if (event) 
                pop.addClassName('hide');
        }
    },
    _showGroup: function(event){
        var obj = event.findElement();
        var gid = $F(obj);
        var pop = $('msglist');
        pop.down('.list').removeClassName('hide');
        pop.down('.empty').addClassName('hide');
        $('selectAll').removeClassName('hide').next('label').removeClassName('hide');
        if (gid > 0) {
            pop.select('.list li').each(function(o){
                o.addClassName('hide');
            });
            var empty = true;
            pop.select('.list li.' + gid).each(function(o){
                o.removeClassName('hide');
                empty = false;
            });
            if (empty) {
                pop.down('.list').addClassName('hide');
                pop.down('.empty').removeClassName('hide');
                $('selectAll').addClassName('hide').next('label').addClassName('hide');
            }
        } else {
            pop.select('.list li').each(function(o){
                o.removeClassName('hide');
            });
        }
        this._checkMsgSelect();
    },
    _updateMsgTo: function(event){
        var obj = event.findElement();
        var list = this.msgtoList;
        if (obj.checked) {
            var value = list.get($F(obj));
            if (!value) {
                list.set($F(obj), obj.next().innerHTML);
            }
        } else {
            list.unset(obj.value);
        }
        $('msglist').select('div.list input[value="' + obj.value + '"]').each(function(o){
            o.checked = obj.checked;
        });
        this._checkMsgSelect();
    },
    _checkMsgSelect: function(){
        var selectAll = true;
        var list = $('msglist').select('div.list input');
        for (i = 0; i < list.length; i++) {
            var o = list[i];
            if (!o.up().hasClassName('hide')) {
                selectAll = o.checked;
                if (!selectAll) {
                    break;
                }
            }
        }
        $('selectAll').checked = selectAll;
    },
    _removeMsgTo: function(event){
        var obj = event.findElement();
        var list = this.msgtoList;
        var parentObj = obj.up('div');
        var id = parentObj.className.split(' ')[1];
        $('msglist').select('div.list input[value="' + id + '"]').each(function(o){
            o.checked = false;
        });
        list.unset(id);
        parentObj.remove();
    },
    _genMsgTo: function(){
        var list = this.msgtoList;
        var container = $('useript').update();
        list.each(function(item){
            var o = new Element('div');
            o.addClassName('uit');
            o.addClassName(item.key);
            o.update(item.value + '<a><img src="' + baseURL + 'assets/images/bbs/del.gif" alt="删除" /></a>');
            container.appendChild(o);
        });
        var ipt = new Element('input', {
            type: 'text',
            maxlength: "5",
            id: 'typeuser'
        });
        container.appendChild(ipt);
        container.select('div a').each(function(o){
            o.observe('click', animate._removeMsgTo.bind(animate));
            $('msglist').addClassName('hide');
        });
        container.down('#typeuser').observe('keypress', function(event){
            var obj = event.findElement();
            if (event.keyCode == 8) {
                var parentObj = obj.previous('div');
                if (parentObj) {
                    var id = parentObj.className.split(' ')[1];
                    $('msglist').select('div.list input[value="' + id + '"]').each(function(o){
                        o.checked = false;
                    });
                    list.unset(id);
                    parentObj.remove();
                }
            }
        });
    },
    _startSpec: function(event){
        var obj = event.findElement();
        var li = obj.up('li');
        var cnt = obj.next('div');
        var tH = li.getHeight();
        var index = 0;
        if (this.timerSpec) {
            if (this.objSpec == li) {
                clearInterval(this.timerSpec);
                this.specEnd = tH;
            } else {
                return false;
            }
        }
        this.objSpec = li;
        if (li.hasClassName('cur')) {
            li.removeClassName('cur');
            this.specEnd = tH;// + this.tHeight;
            this.tHeight = obj.getHeight();
            this.frame = 0;
            this.timerSpec = setInterval(function(){
                animate._closeSpec(li);
            }, this.interval);
        } else {
            li.addClassName('cur');
            this.tHeight = tH;
            li.setStyle({
                'height': tH + 'px'
            });
            this.specEnd = cnt.getHeight() + obj.getHeight();
            this.frame = 0;
            cnt.removeClassName('hide');
            this.timerSpec = setInterval(function(){
                animate._openSpec(li);
            }, this.interval);
        }
    },
    _openSpec: function(obj){
        var distance = this.specEnd - this.tHeight;
        var unit = Math.PI / 2 / 24;
        var offset = distance * Math.sin(this.frame++ * unit);
        var tH = this.tHeight + offset;
        obj.setStyle({
            'height': tH + 'px'
        });
        if (tH >= this.specEnd) {
            this.frame = 0;
            clearInterval(this.timerSpec);
            this.timerSpec = false;
        }
    },
    _closeSpec: function(obj, idx){
        var distance = this.specEnd - this.tHeight;
        var unit = Math.PI / 2 / 24;
        var offset = distance * Math.sin(this.frame++ * unit);
        var tH = this.specEnd - offset;
        obj.setStyle({
            'height': tH + 'px'
        });
        if (tH <= this.tHeight) {
            obj.down('div').addClassName('hide');
            this.frame = 0;
            clearInterval(this.timerSpec);
            this.timerSpec = false;
        }
    },
    bindSign: function(){
        var sign = $('pro_sign');
        var original;
        function updateSign(){
            var val = $F('tb_sign');
            if (original != val) {
                var ajax = new Ajax.Request('signature.action', {
                    method: 'get',
                    parameters: {
                        'fscontent': val
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            if (json.message) {
                                page.showDialog(json.message);
                            } else {
                                sign.down('.l a').update(val);
                            }
                        } else {
                            page.showDialog('数据获取失败。');
                        }
                    },
                    onFailure: function(){
                        page.showDialog('服务器连接失败。');
                    }
                });
            }
            $('tb_sign').replace('<a href="javascript:;">' + original + '</a>');
            sign.down('.l a').observe('click', initSign);
            Event.stopObserving(document);
        }
        function initSign(event){
            var obj = event.findElement();
            original = obj.innerHTML;
            obj.replace('<input type="text" id="tb_sign" value="' + original + '" />');
            $('tb_sign').activate().observe('keypress', function(event){
                if (event.keyCode == 13) {
                    updateSign();
                } else if (event.keyCode == 27) {
                    $('tb_sign').replace('<a>' + original + '</a>');
                    Event.stopObserving(document);
                    sign.down('.l a').observe('click', initSign);
                }
            });
            Event.observe(document, 'mousedown', function(event){
                var evtobj = event.findElement();
                if (evtobj != $('tb_sign')) {
                    updateSign();
                } else {
                    $('tb_sign').activate();
                }
            });
        }
        if (sign && !sign.hasClassName('up')) {
            sign.down('.l a').observe('click', initSign);
        }
    },
    bindUpload: function(){
        var hasError = false;
        swfu = new SWFUpload({
            upload_url: "addphoto.action",
            flash_url: contextPath + "assets/flash/swfupload.swf",
            file_size_limit: "6 MB",
            button_placeholder_id: "upload_placeholder",
            button_image_url: contextPath + "assets/images/bbs/upload.png",
            button_width: 78,
            button_height: 22,
            button_cursor: SWFUpload.CURSOR.HAND,
            file_types: "*.bmp;*.jpg;*.jpeg;*.gif;",
            file_types_description: "图片文件",
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            
            file_queued_handler: addFiles,
            file_dialog_complete_handler: dialogClose,
            
            upload_start_handler: uploadStart,
            upload_progress_handler: uploadProgress,
            upload_error_handler: uploadError,
            upload_success_handler: uploadSuccess,
            upload_complete_handler: uploadComplete
        });
        $('btnGo').observe('click', function(event){
            $('upload_filelist').select('a').each(function(a){
                a.remove();
            });
            $('upload_filelist').select('span.hide').each(function(span){
                span.removeClassName('hide').update('0%');
            });
            $('upload_clear').stopObserving('click');
            swfu.addPostParam('fpaid', $F('upload_fpaid'));
            swfu.addPostParam('order', $('upload_order').checked ? 'last' : '');
            
            swfu.startUpload();
        });
        $('upload_clear').observe('click', removeAll);
        $('upload_filelist').update();
        function removeAll(event){
            swfu.cancelQueue();
            $('upload_filelist').update().addClassName('hide').previous().removeClassName('hide');
            $('upload_status').update('0个文件待上传');
        }
        function removeFile(event){
            var stats = swfu.getStats();
            if (stats.in_progress == 0) {
                var obj = event.findElement().up('li');
                var fid = obj.identify();
                swfu.cancelUpload(fid);
                obj.remove();
                stats = swfu.getStats();
                $('upload_status').update(stats.files_queued + '个文件待上传');
            }
        }
        function uploadStart(file){
            //$(file.id).down('a').remove();
            //$(file.id).down('span').removeClassName('hide').update('0%');
            return true;
        }
        function uploadSuccess(f, d, r){
            var json = d.evalJSON() || false;
            if (json) {
                if (json.addphotoMSG) {
                    swfu.cancelQueue();
                    $(f.id).down('span').update('上传失败');
                    $('upload_clear').observe('click', removeAll);
                    hasError = true;
                    alert(json.addphotoMSG);
                } else {
                    var p = json.fpid;
                    var val = $F('upload_forward');
                    if (val != '') {
                        val += ',' + p;
                    } else {
                        val = p;
                    }
                    $('upload_forward').setValue(val);
                }
            }
        }
        function addFiles(file){
            var obj = $('upload_filelist');
            var li = new Element('li');
            var filename = file.name;
            if (filename.length > 24) {
                filename = filename.substr(0, 24) + '...';
            }
            li.innerHTML = '<span class="hide"></span><a title="删除"></a>' + filename;
            li.title = file.name;
            li.id = file.id;
            obj.removeClassName('hide').previous().addClassName('hide');
            obj.appendChild(li);
            li.down('a').observe('click', removeFile);
        }
        function dialogClose(n, q, t){
            $('upload_status').update(t + '个文件待上传');
        }
        function uploadProgress(f, b, t){
            var obj = $(f.id);
            obj.down('span').update(Math.floor(b / t * 100) + '%');
        }
        function uploadError(f, c, m){
            if (c != SWFUpload.UPLOAD_ERROR.FILE_CANCELLED) {
                alert(m + ' (' + c + ')');
            }
        }
        function uploadComplete(file){
            var stats = this.getStats();
            $('upload_status').update(stats.files_queued + '个文件待上传');
            if (stats.files_queued == 0 && !hasError) {
                $('upload_form').submit();
            }
        }
    },
    bindEssay: function(){
        $('essay_own').observe('focus', function(){
            if ($F('essay_own') == '请输入您的随笔') {
                $('essay_own').setValue('');
            } else {
                $('essay_own').activate();
            }
        });
        $('essay_post').observe('click', function(){
            var val = $F('essay_own');
            if (val == '' || val == '请输入您的随笔') {
                page.showDialog('请填写随笔的内容。', function(){
                    $('essay_own').setValue('');
                    $('essay_own').activate();
                });
                return;
            }
            var ajax = new Ajax.Request('addjotting.action', {
                method: 'post',
                parameters: {
                    'fjcontent': val
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        if (json.jottingmsg) {
                            page.showDialog(json.jottingmsg);
                        } else {
                            $('essay_own').setValue('');
                            var list = $('essay_post').up('li');
                            var odd = '';
                            var nobj = list.next();
                            if (nobj) {
                                if (!nobj.down('div').hasClassName('odd')) {
                                    odd = ' class="odd"';
                                }
                            }
                            var li = new Element('li');
                            li.update('<dl class="ru"><dd class="left"></dd><dd class="right"></dd><dd class="c"></dd></dl>\
                <div' + odd + '>\
                  <b>' +
                            json.createdate +
                            '</b>\
                  <span>\
                  <a class="edit" href="#edit" rel="' +
                            json.fjid +
                            '">编辑</a>\
                  <a class="del" href="#del">删除</a>\
                  <b><a href="showjottingdetail.action?fjid=' +
                            json.fjid +
                            '">评论</a>(0)</b>\
                  </span>\
                  <p>' +
                            json.fjcontent +
                            '</p>\
                </div>\
                <dl class="rd"><dd class="left"></dd><dd class="right"></dd><dd class="c"></dd></dl>');
                            list.insert({
                                after: li
                            });
                            li.down('a.edit').observe('click', essayEdit);
                            li.down('a.del').observe('click', essayDelete);
                        }
                    } else {
                        page.showDialog('数据获取失败。');
                    }
                },
                onFailure: function(){
                    page.showDialog('服务器连接失败。');
                }
            });
        });
        var editing = '';
        var original;
        var val = '';
        $('bbs_essay').select('li a.edit').each(function(item){
            item.observe('click', essayEdit);
        });
        function essayEdit(event){
            var item = event.findElement();
            var p = item.up().next('p');
            var text = p.innerHTML;
            var id = item.readAttribute('rel');
            if (editing != '') {
                $(editing).update(original);
            }
            if (editing != p.identify()) {
                editing = p.identify();
                original = text;
            } else {
                editing = '';
                return;
            }
            if (val != '') {
                p.update('<textarea cols="50" rows="3" id="text_' + id + '">' + val + '</textarea>\
					<a class="btn" id="save_' +
                id +
                '">保存修改</a>');
            } else {
                p.update('<textarea cols="50" rows="3" id="text_' + id + '">' + text + '</textarea>\
					<a class="btn" id="save_' +
                id +
                '">保存修改</a>');
            }
            $('save_' + id).observe('click', function(){
                val = $F('text_' + id);
                if (val == '') {
                    page.showDialog('随笔的内容不能为空。', function(){
                        $('text_' + id).activate();
                    });
                    return;
                }
                var ajax = new Ajax.Request('editfj.action', {
                    method: 'post',
                    parameters: {
                        'fjcontent': val,
                        'fjid': id
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            if (json.jottingmsg) {
                                page.showDialog(json.jottingmsg);
                            } else {
                                p.update(json.fjcontent);
                                val = '';
                            }
                        } else {
                            page.showDialog('数据获取失败。');
                            p.update(text);
                        }
                    },
                    onFailure: function(){
                        page.showDialog('服务器连接失败。');
                        p.update(text);
                    }
                });
            });
        }
        $('bbs_essay').select('li a.del').each(function(item){
            item.observe('click', essayDelete);
        });
        function essayDelete(event){
            var item = event.findElement();
            page.showConfirm('该随笔的评论将被删除<br />是否继续？', function(){
                var id = item.previous().readAttribute('rel');
                var ajax = new Ajax.Request('delfj.action', {
                    method: 'get',
                    parameters: {
                        'fjid': id,
                        'seed': Math.random()
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            if (json.jottingmsg) {
                                page.showDialog(json.jottingmsg);
                            } else {
                                item.up('li').remove();
                            }
                        } else {
                            page.showDialog('数据获取失败。');
                        }
                    },
                    onFailure: function(){
                        page.showDialog('服务器连接失败。');
                    }
                });
            });
        }
    },
    bindEssayC: function(id){
        $('bbs_essay').select('li a.msgBtn').each(function(item){
            item.observe('click', reply);
        });
        function reply(event){
            var item = event.findElement();
            if (item.hasClassName('own')) {
                $('essay_reply').focus();
            } else {
                var name = item.previous().down('span a').innerHTML;
                var val = $F('essay_reply');
                var reg = /(回复[^：]+：)/;
                if (reg.test(val)) {
                    val = val.replace(reg, '回复' + name + '：');
                } else {
                    val = '回复' + name + '：' + val;
                }
                $('essay_reply').setValue(val).focus();
            }
        }
        $('essay_post').observe('click', function(){
            var val = $F('essay_reply');
            if (val == '') {
                page.showDialog('请输入回复内容。', function(){
                    $('essay_reply').activate();
                });
                return;
            }
            var ajax = new Ajax.Request('addfjc.action', {
                method: 'post',
                parameters: {
                    'fjccontent': val,
                    'fjid': id
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        if (json.jottingmsg) {
                            page.showDialog(json.jottingmsg);
                        } else {
                            var list = $('comment');
                            $('essay_reply').setValue('');
                            
                            var li = new Element('li');
                            li.update('<dl class="ru"><dd class="left"></dd><dd class="right"></dd><dd class="c"></dd></dl>\
				<div class="eImgOuterr">\
					<a href="personal.action"><img src="' + json.uimg + '" width="55" height="55" alt="' + json.uname + '" /></a>\
	                <div>\
	                  <span>\
		                  <a href="personal.action">' +
                            json.uname +
                            '</a>\
		                  <i>说道：</i>\
					  </span>\
	                  <b>' +
                            json.createdate +
                            '</b>\
	                  <p>' +
                            json.fjccontent +
                            '</p>\
	                </div>\
				<a class="msgBtn">回复</a>\
				</div>\
                <dl class="rd"><dd class="left"></dd><dd class="right"></dd><dd class="c"></dd></dl>');
                            list.insert({
                                before: li
                            });
                            $('st').update('评论 (' + json.fjcSum + ')');
                            li.down('a.msgBtn').observe('click', reply);
                        }
                    } else {
                        page.showDialog('数据获取失败。');
                    }
                },
                onFailure: function(){
                    page.showDialog('服务器连接失败。');
                }
            });
        });
    },
    bindSubnav: function(){
        if (!$('frame_subnav'))
            return;
        $('frame_subnav').select('.sub p a').each(function(a){
            a.observe('click',function(evt){
                evt.stop();
                if (a.up('li').hasClassName('cur')){
                    return false;
                }
                var cur = $('frame_subnav').down('li.cur');
                if (cur != undefined)
                    cur.removeClassName('cur');
                a.up('li').addClassName('cur');
            });
        });
    }
});
var Utility = Class.create({
    initialize: function(){
    },
    getScrollHeight: function(){
        var yScroll;
        if (self.pageYOffset != undefined) {
            yScroll = self.pageYOffset;// Explorer 6 Strict
        } else if (document.documentElement && document.documentElement.scrollTop) {
            yScroll = document.documentElement.scrollTop;
        } else if (document.body) {// all other Explorers
            yScroll = document.body.scrollTop;
        }
        return yScroll;
    },
    getDimensions: function(){
        if (window.innerWidth) 
            winWidth = window.innerWidth;
        else if ((document.body) && (document.body.clientWidth)) 
            winWidth = document.body.clientWidth;
        //获取窗口高度 
        if (window.innerHeight) 
            winHeight = window.innerHeight;
        else if ((document.body) && (document.body.clientHeight)) 
            winHeight = document.body.clientHeight;
        //通过深入Document内部对body进行检测，获取窗口大小 
        if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
            winHeight = document.documentElement.clientHeight;
            winWidth = document.documentElement.clientWidth;
        }
        return [winWidth, winHeight];
    },
    getQueryString: function(){
        var url = window.location.href;
        if (url.indexOf('?') > -1) 
            return url.split('?')[1];
        else 
            return false;
    },
    MD5: function(string){
    
        function RotateLeft(lValue, iShiftBits){
            return (lValue << iShiftBits) | (lValue >>> (32 - iShiftBits));
        }
        
        function AddUnsigned(lX, lY){
            var lX4, lY4, lX8, lY8, lResult;
            lX8 = (lX & 0x80000000);
            lY8 = (lY & 0x80000000);
            lX4 = (lX & 0x40000000);
            lY4 = (lY & 0x40000000);
            lResult = (lX & 0x3FFFFFFF) + (lY & 0x3FFFFFFF);
            if (lX4 & lY4) {
                return (lResult ^ 0x80000000 ^ lX8 ^ lY8);
            }
            if (lX4 | lY4) {
                if (lResult & 0x40000000) {
                    return (lResult ^ 0xC0000000 ^ lX8 ^ lY8);
                } else {
                    return (lResult ^ 0x40000000 ^ lX8 ^ lY8);
                }
            } else {
                return (lResult ^ lX8 ^ lY8);
            }
        }
        
        function F(x, y, z){
            return (x & y) | ((~ x) & z);
        }
        function G(x, y, z){
            return (x & z) | (y & (~ z));
        }
        function H(x, y, z){
            return (x ^ y ^ z);
        }
        function I(x, y, z){
            return (y ^ (x | (~ z)));
        }
        
        function FF(a, b, c, d, x, s, ac){
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(F(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        };
        
        function GG(a, b, c, d, x, s, ac){
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(G(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        };
        
        function HH(a, b, c, d, x, s, ac){
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(H(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        };
        
        function II(a, b, c, d, x, s, ac){
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(I(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        };
        
        function ConvertToWordArray(string){
            var lWordCount;
            var lMessageLength = string.length;
            var lNumberOfWords_temp1 = lMessageLength + 8;
            var lNumberOfWords_temp2 = (lNumberOfWords_temp1 - (lNumberOfWords_temp1 % 64)) / 64;
            var lNumberOfWords = (lNumberOfWords_temp2 + 1) * 16;
            var lWordArray = Array(lNumberOfWords - 1);
            var lBytePosition = 0;
            var lByteCount = 0;
            while (lByteCount < lMessageLength) {
                lWordCount = (lByteCount - (lByteCount % 4)) / 4;
                lBytePosition = (lByteCount % 4) * 8;
                lWordArray[lWordCount] = (lWordArray[lWordCount] | (string.charCodeAt(lByteCount) << lBytePosition));
                lByteCount++;
            }
            lWordCount = (lByteCount - (lByteCount % 4)) / 4;
            lBytePosition = (lByteCount % 4) * 8;
            lWordArray[lWordCount] = lWordArray[lWordCount] | (0x80 << lBytePosition);
            lWordArray[lNumberOfWords - 2] = lMessageLength << 3;
            lWordArray[lNumberOfWords - 1] = lMessageLength >>> 29;
            return lWordArray;
        };
        
        function WordToHex(lValue){
            var WordToHexValue = "", WordToHexValue_temp = "", lByte, lCount;
            for (lCount = 0; lCount <= 3; lCount++) {
                lByte = (lValue >>> (lCount * 8)) & 255;
                WordToHexValue_temp = "0" + lByte.toString(16);
                WordToHexValue = WordToHexValue + WordToHexValue_temp.substr(WordToHexValue_temp.length - 2, 2);
            }
            return WordToHexValue;
        };
        
        function Utf8Encode(string){
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";
            
            for (var n = 0; n < string.length; n++) {
            
                var c = string.charCodeAt(n);
                
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }
                
            }
            
            return utftext;
        };
        
        var x = Array();
        var k, AA, BB, CC, DD, a, b, c, d;
        var S11 = 7, S12 = 12, S13 = 17, S14 = 22;
        var S21 = 5, S22 = 9, S23 = 14, S24 = 20;
        var S31 = 4, S32 = 11, S33 = 16, S34 = 23;
        var S41 = 6, S42 = 10, S43 = 15, S44 = 21;
        
        string = Utf8Encode(string);
        
        x = ConvertToWordArray(string);
        
        a = 0x67452301;
        b = 0xEFCDAB89;
        c = 0x98BADCFE;
        d = 0x10325476;
        
        for (k = 0; k < x.length; k += 16) {
            AA = a;
            BB = b;
            CC = c;
            DD = d;
            a = FF(a, b, c, d, x[k + 0], S11, 0xD76AA478);
            d = FF(d, a, b, c, x[k + 1], S12, 0xE8C7B756);
            c = FF(c, d, a, b, x[k + 2], S13, 0x242070DB);
            b = FF(b, c, d, a, x[k + 3], S14, 0xC1BDCEEE);
            a = FF(a, b, c, d, x[k + 4], S11, 0xF57C0FAF);
            d = FF(d, a, b, c, x[k + 5], S12, 0x4787C62A);
            c = FF(c, d, a, b, x[k + 6], S13, 0xA8304613);
            b = FF(b, c, d, a, x[k + 7], S14, 0xFD469501);
            a = FF(a, b, c, d, x[k + 8], S11, 0x698098D8);
            d = FF(d, a, b, c, x[k + 9], S12, 0x8B44F7AF);
            c = FF(c, d, a, b, x[k + 10], S13, 0xFFFF5BB1);
            b = FF(b, c, d, a, x[k + 11], S14, 0x895CD7BE);
            a = FF(a, b, c, d, x[k + 12], S11, 0x6B901122);
            d = FF(d, a, b, c, x[k + 13], S12, 0xFD987193);
            c = FF(c, d, a, b, x[k + 14], S13, 0xA679438E);
            b = FF(b, c, d, a, x[k + 15], S14, 0x49B40821);
            a = GG(a, b, c, d, x[k + 1], S21, 0xF61E2562);
            d = GG(d, a, b, c, x[k + 6], S22, 0xC040B340);
            c = GG(c, d, a, b, x[k + 11], S23, 0x265E5A51);
            b = GG(b, c, d, a, x[k + 0], S24, 0xE9B6C7AA);
            a = GG(a, b, c, d, x[k + 5], S21, 0xD62F105D);
            d = GG(d, a, b, c, x[k + 10], S22, 0x2441453);
            c = GG(c, d, a, b, x[k + 15], S23, 0xD8A1E681);
            b = GG(b, c, d, a, x[k + 4], S24, 0xE7D3FBC8);
            a = GG(a, b, c, d, x[k + 9], S21, 0x21E1CDE6);
            d = GG(d, a, b, c, x[k + 14], S22, 0xC33707D6);
            c = GG(c, d, a, b, x[k + 3], S23, 0xF4D50D87);
            b = GG(b, c, d, a, x[k + 8], S24, 0x455A14ED);
            a = GG(a, b, c, d, x[k + 13], S21, 0xA9E3E905);
            d = GG(d, a, b, c, x[k + 2], S22, 0xFCEFA3F8);
            c = GG(c, d, a, b, x[k + 7], S23, 0x676F02D9);
            b = GG(b, c, d, a, x[k + 12], S24, 0x8D2A4C8A);
            a = HH(a, b, c, d, x[k + 5], S31, 0xFFFA3942);
            d = HH(d, a, b, c, x[k + 8], S32, 0x8771F681);
            c = HH(c, d, a, b, x[k + 11], S33, 0x6D9D6122);
            b = HH(b, c, d, a, x[k + 14], S34, 0xFDE5380C);
            a = HH(a, b, c, d, x[k + 1], S31, 0xA4BEEA44);
            d = HH(d, a, b, c, x[k + 4], S32, 0x4BDECFA9);
            c = HH(c, d, a, b, x[k + 7], S33, 0xF6BB4B60);
            b = HH(b, c, d, a, x[k + 10], S34, 0xBEBFBC70);
            a = HH(a, b, c, d, x[k + 13], S31, 0x289B7EC6);
            d = HH(d, a, b, c, x[k + 0], S32, 0xEAA127FA);
            c = HH(c, d, a, b, x[k + 3], S33, 0xD4EF3085);
            b = HH(b, c, d, a, x[k + 6], S34, 0x4881D05);
            a = HH(a, b, c, d, x[k + 9], S31, 0xD9D4D039);
            d = HH(d, a, b, c, x[k + 12], S32, 0xE6DB99E5);
            c = HH(c, d, a, b, x[k + 15], S33, 0x1FA27CF8);
            b = HH(b, c, d, a, x[k + 2], S34, 0xC4AC5665);
            a = II(a, b, c, d, x[k + 0], S41, 0xF4292244);
            d = II(d, a, b, c, x[k + 7], S42, 0x432AFF97);
            c = II(c, d, a, b, x[k + 14], S43, 0xAB9423A7);
            b = II(b, c, d, a, x[k + 5], S44, 0xFC93A039);
            a = II(a, b, c, d, x[k + 12], S41, 0x655B59C3);
            d = II(d, a, b, c, x[k + 3], S42, 0x8F0CCC92);
            c = II(c, d, a, b, x[k + 10], S43, 0xFFEFF47D);
            b = II(b, c, d, a, x[k + 1], S44, 0x85845DD1);
            a = II(a, b, c, d, x[k + 8], S41, 0x6FA87E4F);
            d = II(d, a, b, c, x[k + 15], S42, 0xFE2CE6E0);
            c = II(c, d, a, b, x[k + 6], S43, 0xA3014314);
            b = II(b, c, d, a, x[k + 13], S44, 0x4E0811A1);
            a = II(a, b, c, d, x[k + 4], S41, 0xF7537E82);
            d = II(d, a, b, c, x[k + 11], S42, 0xBD3AF235);
            c = II(c, d, a, b, x[k + 2], S43, 0x2AD7D2BB);
            b = II(b, c, d, a, x[k + 9], S44, 0xEB86D391);
            a = AddUnsigned(a, AA);
            b = AddUnsigned(b, BB);
            c = AddUnsigned(c, CC);
            d = AddUnsigned(d, DD);
        }
        
        var temp = WordToHex(a) + WordToHex(b) + WordToHex(c) + WordToHex(d);
        
        return temp.toLowerCase();
    },
    NumDivide: function(num){
        var revalue = "";
        var strf = String(num);
        if (strf.length > 3) {
            while (strf.length > 3) {
                revalue = "," + strf.substring(strf.length - 3, strf.length) + revalue;
                strf = strf.substring(0, strf.length - 3);
            }
        }
        revalue = strf + revalue;
        return revalue;
    }
});
var Page = Class.create({
    initialize: function(){
        this.calDate = new Date();
        this.calData;
        this.layerPrepared = false;
        this.pageIndex = 0;
    },
    showMask: function(){
        var mask = $('mask');
        if (mask) {
            mask.removeClassName('hide');
        } else {
            mask = new Element('div');
            mask.id = 'mask';
            $('frame_outer').appendChild(mask);
        }
        var isIE6 = /msie|MSIE 6/.test(navigator.userAgent);
        if (isIE6) {
            var html = '<iframe style="position:absolute;display:block;' +
            'z-index:0;width:100%;height:100%;top:0;left:0;' +
            'filter:mask();background-color:#ffffff;"></iframe>';
            mask.innerHTML += html;
            var olddisplay = mask.style.display;
            mask.style.display = 'none';
            mask.style.display = olddisplay;
        }
        this.resizeMask();
    },
    resizeMask: function(){
        var mask = $('mask');
        if (mask) {
            var height = document.body.clientHeight + 20;
            var square = utility.getDimensions();
            if (square[1] > height) 
                height = square[1];
            mask.setStyle("height:" + height + "px");
            mask.setStyle("width:" + document.body.clientWidth + "px");
            mask.setOpacity(.5);
        }
    },
    showDialog: function(txt, action){
        if (!this.dialog) {
            //this.centerLayer();
            //Event.observe(window, 'resize', this.centerLayer.bind(this));
            this.showMask();
            var layer = $('msgbox');
            if (layer) {
                if (txt != '') 
                    layer.down('strong').update(txt);
                else 
                    return;
                layer.removeClassName('hide');
            } else {
                layer = new Element('div');
                layer.id = 'msgbox';
                layer.addClassName('layer');
                layer.update('<a class="close"></a><strong class="c">' + txt + '</strong><p class="btn w1 c"><a>确定</a></p>');
                document.body.appendChild(layer);
                layer.down('.close').observe('click', this.hideDialog.bind(this));
                layer.down('.btn a').observe('click', this.hideDialog.bind(this));
            }
            layer.absPosition();
            this.dialog = true;
            this.action = action;
        }
    },
    showConfirm: function(txt, action){
        if (!this.dialog) {
            //this.centerLayer();
            //Event.observe(window, 'resize', this.centerLayer.bind(this));
            this.showMask();
            var layer = $('cnfbox');
            if (layer) {
                if (txt != '') 
                    layer.down('strong').update(txt);
                else 
                    return;
                layer.removeClassName('hide');
            } else {
                layer = new Element('div');
                layer.id = 'cnfbox';
                layer.className = 'layer';
                layer.update('<a class="close"></a><strong class="c">' + txt + '</strong><p class="btn w11 c"><a class="rs">是</a><a>否</a></p>');
                document.body.appendChild(layer);
                layer.down('.close').observe('click', function(event){
                    page.action = null;
                    page.hideDialog(event);
                });
                layer.select('.btn a').each(function(item){
                    item.observe('click', function(event){
                        if (!item.hasClassName('rs')) {
                            page.action = null;
                        }
                        page.hideDialog(event);
                    });
                });
            }
            layer.absPosition();
            this.dialog = true;
            this.action = action;
        }
    },
    hideDialog: function(event){
        var mask = $('mask');
        var layer = event.findElement().up('div');
        mask.addClassName('hide');
        layer.addClassName('hide');
        this.dialog = false;
        animate.bindSideBar();
        if (this.action) {
            this.action();
        }
    },
    centerLayer: function(){
        this.resizeMask();
        var layer = $('layer');
        var square = utility.getDimensions();
        var x = (square[0] - layer.getWidth()) / 2;
        var y = (square[1] - layer.getHeight()) / 2 + utility.getScrollHeight();
        layer.setStyle('left:' + x + 'px');
        layer.setStyle('top:' + y + 'px');
    },
    setCurrent: function(){
        var section = $('page');
        if (section) {
            var list = section.classNames().toArray();
            var index = Number(list[0]);
            var nav = $('top_nav').select('li');
            nav[index].addClassName('cur');
            var side = $('frame_sidebar').select('li');
            var a = side[index].down('a');
            var c = a.className;
            var t = a.innerHTML;
            var span = new Element('span');
            span.addClassName(c);
            span.update(t);
            a.remove();
            side[index].appendChild(span);
            if (list.length > 1) {
                var subidx = Number(list[1]) + 1;
                if (subidx) {
                    this.setSubnav(subidx);
                }
            }
        }
    },
    setSubnav: function(index){
        var subnav = $('frame_subnav').select('li').each(function(item){
            if (item.hasClassName('cur')) {
                item.removeClassName('cur');
            }
        });
        subnav[index].addClassName('cur');
    },
    getVerifyCode: function(event){
        var obj = event.findElement();
        var img = obj.src.split('?')[0];
        obj.src = img + '?' + Math.random();
        obj.up('div').down('input').clear().activate();
    },
    signin: function(event){
        if (!$('ipt_username').present()) {
            this.showDialog('请输入用户名！', function(){
                $('ipt_username').focus();
            });
            return false;
        }
        if (!$('ipt_password').present()) {
            this.showDialog('请输入密码！', function(){
                $('ipt_password').focus();
            });
            return false;
        }
        if ($('widget_signin').hasClassName('long')) {
            if (!$('ipt_verify').present()) {
                this.showDialog('请输入验证码！', function(){
                    $('ipt_verify').focus();
                });
                return false;
            }
        }
        var md5str = utility.MD5($F('ipt_password'));
        var pass = $('ipt_password');
        pass.setValue(md5str).readOnly = true;
        var val = $('form_login').serialize(true);
        var isHome = false;
        var url = 'ajaxLogin.action';
        var section = $('page');
        if (section) {
            var list = section.classNames().toArray();
            if (list[0] == '0') {
                isHome = true;
                url = 'indexLogin.action';
            }
        }
        var ajax = new Ajax.Request(url, {
            method: 'post',
            parameters: val,
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    if (json.Flag == 1) {
                        var nick;
                        if (json.clubUserVO.urname) {
                            nick = json.clubUserVO.urname;
                        } else {
                            nick = json.clubUserVO.uname;
                        }
                        W.setCookie('singleUid', json.clubUserVO.singleUid);
                        if (val.auto) {
                            var exp = new Date();
                            exp.setFullYear(exp.getFullYear() + 1);
                            W.setCookie('fuyu_uuid', json.clubUserVO.uuid, exp);
                            W.setCookie('fuyu_pass', val.pwd, exp);
                        }
                        var txt;
                        var url;
                        var score;
                        var section = $('page');
                        if (section) {
                            if (section.hasClassName('refresh')) {
                                window.location.reload();
                                return;
                            }
                            var list = section.classNames().toArray();
                            if (list[0] == '6') {
                                txt = '我的空间';
                                url = 'personal.action';
                                score = json.clubUserVO.uscore;
                            } else {
                                txt = '个人中心';
                                url = 'initMember.action';
                                score = json.clubUserVO.score;
                            }
                        } else {
                            if (val.hid_path.search('community') > -1) {
                                txt = '我的空间';
                                url = 'personal.action';
                                score = json.clubUserVO.uscore;
                            } else {
                                txt = '个人中心';
                                url = 'initMember.action';
                                score = json.clubUserVO.score;
                            }
                        }
                        //$('widget_signin').update('<strong>欢迎你，' + nick + '</strong><span>当前积分：' + score + '</span><ul><li class="left"><a href="' + url + '" class="button">' + txt + '</a></li><li><a id="ipt_signout" class="button">退出登录</a><input type="hidden" id="hid_path" value="' + val.hid_path + '" /></li></ul>');
                        $('widget_signin').update('<strong>欢迎你，' + nick + '</strong><ul><li class="left"><a href="' + url + '" class="button">' + txt + '</a></li><li><a id="ipt_signout" class="button">退出登录</a><input type="hidden" id="hid_path" value="' + val.hid_path + '" /></li></ul><p><a href="'+root_path+'initbindcar.action"><img src="'+root_path+'assets/images/ucert.gif" alt="即刻认证车主 体验车主尊享" /></a></p>');
                        if ($('widget_certifate')) {
                            $('widget_certifate').remove();
                        } 
                        $('widget_signin').removeClassName('long').writeAttribute('id', 'widget_member');
                        $('ipt_signout').observe('click', page.signout.bind(page));
                        /*if (isHome) {
                            var mine = new Element('div', {
                                'id': 'dealer_promo'
                            });
                            mine.update('<h3 class="dealer">我的经销商</h3><ul><li>' + json.clubUserVO.dlname + '</li><li>地址：' + json.clubUserVO.dladdress + '</li><li>电话：' + json.clubUserVO.dltel + '</li></ul><a href="dealers.action" class="more">查看更多</a><br class="clearfloat" />');
                            $('frame_dealer').down('h2').insert({
                                after: mine
                            });
                            if (json.clubUserVO.dact.length > 0) {
                                var gift = new Element('div', {
                                    'id': 'dealer_gift'
                                });
                                var li = '';
                                for (idx in json.clubUserVO.dact) {
                                    var p = json.clubUserVO.dact[idx].split(':');
                                    li += '<li><a href="promo_1.action?maid=' + p[0] + '">' + p[1] + '</a></li>';
                                }
                                gift.update('<h3 class="promo">我的经销商礼遇</h3><ul>' + li + '</ul><a href="promo.action" class="more">查看更多</a><br class="clearfloat" />');
                                mine.insert({
                                    after: gift
                                });
                            }
                        }*/
                    } else {
                        page.showDialog(json.Msg, function(){
                            $('ipt_username').activate();
                            if (json.loginNumber >= 3) {
                                if ($('widget_signin').hasClassName('long')) {
                                    var obj = $('ipt_verify');
                                    var img = obj.src.split('?')[0];
                                    obj.src = img + '?' + Math.random();
                                    obj.up('div').down('input').clear();
                                    $('ipt_username').activate();
                                } else {
                                    $('widget_signin').addClassName('long');
                                    $('widget_signin').down('form ul').insert({
                                        before: '<div id="ipt_code"><div class="textbox short"><label for="ipt_verify">验证码</label><input type="text" name="ver" id="ipt_verify" maxlength="5" /></div><img id="img_verify" src="/kaptcha.jpg" alt="验证码" height="20" width="78" title="单击刷新验证码" /><input type="hidden" name="captchaFlag" value="1" /></div>'
                                    });
                                    Event.observe('img_verify', 'click', page.getVerifyCode.bind(page));
                                }
                            }
                        });
                    }
                } else {
                    page.showDialog('数据解析失败！');
                }
            },
            onFailure: function(){
                page.showDialog('服务器连接失败！');
            }
        });
        pass.clear().readOnly = false;
        return false;
    },
    signout: function(event){
        var ajax = new Ajax.Request('ajaxLogout.action', {
            method: 'get',
            parameters: {
                'seed': Math.random()
            },
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    if (json.Flag == 1) {
                        var exp = new Date();
                        W.setCookie('fuyu_uuid', '', exp);
                        W.setCookie('fuyu_pass', '', exp);
                        
                        var url = $F('hid_path');
                        var passurl = url + '/password.jsp';
                        var regurl = url + '/register.jsp';
                        $('widget_member').update('<form action="" method="post" id="form_login" onsubmit="return page.signin();"><div class="textbox"><label for="ipt_username">用户名</label><input type="text" name="uid" id="ipt_username" /></div><div class="textbox"><label for="ipt_password">密　码</label><input type="password" name="pwd" id="ipt_password" /></div><ul><li class="left"><input type="checkbox" name="auto" id="ipt_remember" /> <label for="ipt_remember" class="cb">记住密码</label></li><li class="dot"><a href="' + passurl + '">忘记密码</a></li><li class="left"><a id="ipt_signin" class="button">登录</a></li><li><a href="' + regurl + '" id="ipt_signup" class="button">注册</a><input type="hidden" name="hid_path" value="' + url + '" /></li></ul><input type="submit" class="submit" /></form>');
                        $('widget_member').writeAttribute('id', 'widget_signin');
                        $('ipt_signin').observe('click', page.signin.bind(page));
                        var section = $('page');
                        if (section) {
                            if (section.hasClassName('refresh')) {
                                window.location.reload();
                                return;
                            }
                            var list = section.classNames().toArray();
                            if (list[0] == '0') {
                                $('dealer_promo').remove();
                                if ($('dealer_gift')) 
                                    $('dealer_gift').remove();
                            }
                        }
                    } else {
                        page.showDialog(json.Msg);
                    }
                } else {
                    page.showDialog('数据解析失败！');
                }
            },
            onFailure: function(){
                page.showDialog('服务器连接失败！');
            }
        });
    },
    initCalendar: function(){
        this._getCalData(this.calDate);
        $('prev').observe('click', this._goLastMonth.bind(this));
        $('next').observe('click', this._goNextMonth.bind(this));
    },
    _showCalendar: function(){
        var date = this.calDate;
        var days = this._getMonthDay(date);
        var today = new Date();
        var lMonth = this._getMonthDay(this._addMonth(date, -1));
        var cal = '<table cellpadding="0" cellspacing="0">';
        date.setDate(1);
        var sWeek = date.getDay() - 1;//一周的第一天：星期一/星期日:date.getDay()
        if (sWeek < 0) 
            sWeek = 6;
        var weeks = Math.ceil((days + sWeek) / 7);
        var datenum = 1;
        var outdate = lMonth - sWeek + 1;
        for (i = 0; i < weeks; i++) {
            cal += '<tr>';
            for (j = 0; j < 7; j++) {
                cal += '<td';
                if ((i == 0 && j < sWeek) || datenum > days) {
                    cal += ' class="out"';
                    cal += '>' + outdate++;
                } else {
                    var hasClass = false;
                    if (this.calDate.getFullYear() == today.getFullYear() && this.calDate.getMonth() == today.getMonth() && datenum == today.getDate()) {
                        cal += ' class="today';
                        hasClass = true;
                    }
                    if (this.calData && this.calData[datenum]) {
                        if (hasClass) 
                            cal += ' event';
                        else 
                            cal += ' class="event"';
                    }
                    if (hasClass) 
                        cal += '"';
                    cal += '>' + datenum++;
                    outdate = 1;
                }
                cal += '</td>';
            }
            cal += '</tr>';
        }
        cal += '</table>';
        cal += '\n<span id="tip" class="hide"></span>\n<span id="arrow" class="hide"></span>';
        var cb = $('widget_calendar').down('div.body').update(cal);
        animate.bindTip();
        var disp = $('date').down('div.date');
        disp.update(date.getFullYear() + '-' + String(date.getMonth() + 1).fillChar(2, '0'));
    },
    _getCalData: function(date){
        /*var ajax = new Ajax.Request('login.txt', {
         method: 'get',
         parameters: {
         year: date.getFullYear(),
         month: date.getMonth()
         },
         onSuccess: function(transport){
         page.calData = transport.responseText.evalJSON();*/
        page._showCalendar();
        //    }
        //});
    },
    _goNextMonth: function(){
        this.calDate = this._addMonth(this.calDate, 1);
        this._getCalData(this.calDate);
    },
    _goLastMonth: function(){
        this.calDate = this._addMonth(this.calDate, -1);
        this._getCalData(this.calDate);
    },
    _getMonthDay: function(date){
        var monthDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var m = date.getMonth();
        var y = date.getFullYear();
        if (m != 1) {
            return monthDay[m];
        } else {
            if (y % 400 == 0 || (y % 100 != 0 && y % 4 == 0)) {
                return 29;
            } else {
                return 28;
            }
        }
    },
    _addMonth: function(date, num){
        var m = date.getMonth();
        m += num;
        var y = date.getFullYear();
        if (num > 0) {
            if (m > 11) {
                m -= 12;
                y++;
            }
        } else {
            if (m < 0) {
                m += 12;
                y--;
            }
        }
        var result = new Date();
        result.setFullYear(y);
        result.setMonth(m);
        return result;
    },
    bindForecast: function(){
        var cnt = $('widget_weather');
        if (!cnt) 
            return false;
        var dl = cnt.down('.drop');
        cnt.down('.city').observe('click', function(){
            dl.toggleClassName('hide');
            if (dl.hasClassName('hide')) {
                $('dl_city').addClassName('hide');
                Event.stopObserving(document);
                var cur = dl.down('.cur');
                if (cur) 
                    cur.removeClassName('cur');
            } else {
                Event.observe(document, 'mousedown', function(evt){
                    var evtObj = evt.findElement();
                    var sub = $('dl_city');
                    if (!evtObj.up('.drop') && evtObj != dl && evtObj != sub && evtObj != cnt.down('.city')) {
                        dl.addClassName('hide');
                        sub.addClassName('hide');
                        var cur = dl.down('.cur');
                        if (cur) 
                            cur.removeClassName('cur');
                        Event.stopObserving(document);
                    }
                });
            }
        });
        cnt.select('.drop a[rel]').each(function(a){
            a.observe('click', function(event){
                var obj = event.findElement();
                var sub = $('dl_city');
                var cur = obj.up('ul').down('.cur');
                if (cur) {
                    cur.removeClassName('cur');
                    if (cur.down() != obj) 
                        obj.up().addClassName('cur');
                    else {
                        sub.addClassName('hide');
                        return;
                    }
                } else {
                    obj.up().addClassName('cur');
                }
                page._getCity(obj.readAttribute('rel'));
                var offset = obj.positionedOffset();
                sub.setStyle({
                    top: (offset.top + 42) + 'px',
                    left: (offset.left + 107) + 'px'
                });
            });
        });
    },
    _getForecast: function(p, c){
        var ajax = new Ajax.Request('weather.action', {
            method: 'get',
            parameters: {
                'p': p,
                'c': c
            },
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    page._showForecast(json);
                } else {
                    alert('天气数据获取失败。');
                }
            },
            onFailure: function(){
                alert('天气数据获取失败。');
            }
        });
    },
    _showForecast: function(json){
        var cnt = $('widget_weather');
        cnt.down('.city').update(json.city);
        var today = new Date();
        var dt = cnt.select('.date span');
        dt[0].update(today.getMonth() + 1);
        dt[0].update(dt[0].innerHTML.fillChar(2, '0'));
        dt[1].update(today.getDate());
        dt[1].update(dt[1].innerHTML.fillChar(2, '0'));
        var week = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
        dt[2].update(week[today.getDay()] + '<br />' + today.getFullYear());
        cnt.down('.weather span').update(json.tmp_l + '<em>&#176;C</em>~' + json.tmp_h + '<em>&#176;C</em>');
        cnt.down('.info').update(json.t + '<br />风力 ' + json.wind + '级');
    },
    _getCity: function(pid){
        var ajax = new Ajax.Request('getCity.action', {
            method: 'get',
            parameters: {
                'pid': pid
            },
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    var city = json.cs;
                    var pin = json.cpy;
                    var sub = $('dl_city').update();
                    var ul = new Element('ul');
                    for (i = 0; i < city.length; i++) {
                        var li = new Element('li');
                        var an = new Element('a');
                        an.update(city[i]).writeAttribute('name', pin[i]).observe('click', page._setCity.bind(page));
                        li.appendChild(an);
                        ul.appendChild(li);
                    }
                    sub.appendChild(ul).removeClassName('hide');
                    sub.removeClassName('hide');
                } else {
                    alert('城市数据获取失败。');
                }
            },
            onFailure: function(){
                alert('城市数据获取失败。');
            }
        });
    },
    _setCity: function(event){
        var obj = event.findElement();
        var city = obj.readAttribute('name');
        var cnt = $('widget_weather');
        var prov = cnt.down('.drop .cur a');
        if (prov) {
            cnt.down('.drop').addClassName('hide');
            $('dl_city').addClassName('hide');
            cnt.down('.drop .cur').removeClassName('cur');
            var p = prov.readAttribute('name');
            var exp = new Date();
            W.setCookie("cityForWeather", city + "_" + p, exp);
            exp = new Date(2012, 11, 31);
            W.setCookie("cityForWeather", city + "_" + p, exp);
            this._getForecast(p, city);
            Event.stopObserving(document);
        }
    },
    bindSearch: function(){
        var search = $('frame_search');
        if (search) {
            search.down('a').observe('click', this._goSearch.bind(this));
            var form = search.down('form');
            form.observe('submit', this._checkSearch.bind(this));
            form.down('input[name="domains"]').setValue(location.hostname);
            form.down('input[name="sitesearch"]').setValue(location.hostname);
            var box = search.down('input[name="q"]');
            box.observe('focus', function(){
                if ($F(box) == '搜索') {
                    box.clear();
                }
            });
            box.observe('blur', function(){
                if ($F(box).blank()) {
                    box.setValue('搜索');
                }
            });
        }
    },
    _checkSearch: function(event){
        var form = event.findElement() || event;
        var data = form.serialize(true);
        if (data.q.blank() || data.q == '搜索') {
            alert('请输入一个关键字');
            form.down('input[name="q"]').focus();
            this._cancelEvent(event);
            return false;
        }
        return true;
    },
    _goSearch: function(event){
        var obj = event.findElement();
        var form = obj.up('form');
        if (this._checkSearch(form)) 
            form.submit();
    },
    _cancelEvent: function(evt){
        evt.returnValue = false;
        evt.preventDefault();
        
    },
    bindLinks: function(){
        var link = $('frame_link');
        if (link) {
            link.down('a').observe('click', this._showLinks.bind(this));
        }
    },
    _showLinks: function(event){
        var obj = event.findElement();
        var ul = obj.next('ul').toggleClassName('hide');
        ul.select('li a').each(function(a){
            a.observe('click', function(){
                ul.addClassName('hide');
                Event.stopObserving(document);
            });
        });
        if (!ul.hasClassName('hide')) {
            Event.observe(document, 'mousedown', function(evt){
                var evtObj = evt.findElement();
                if (!evtObj.up('ul') && evtObj != ul && evtObj != obj) {
                    ul.addClassName('hide');
                    Event.stopObserving(document);
                }
            });
        }
    },
    bindLive: function(last){
        this.liveMsg = last;
        setTimeout(this._updateLive.bind(this), 1000);
        var cont = $('kb_record').down('.scroll');
        cont.scrollTop = cont.scrollHeight;
    },
    _updateLive: function(){
        var ajax = new Ajax.Request('ajaxOnline.action', {
            method: 'get',
            parameters: {
                'eoid': page.liveMsg
            },
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    if (json.flag == 1) {
                        page.liveMsg = 0;
                        $('kb_ask').remove();
                        page.showDialog('直播结束');
                        return;
                    }
                    if (page.liveMsg != json.eoid) {
                        var cont = $('kb_record').down('.scroll');
                        var needScroll = false;
                        if (cont.scrollTop >= cont.scrollHeight - cont.getHeight() - 1) 
                            needScroll = true;
                        cont.insert({
                            bottom: json.content
                        });
                        page.liveMsg = json.eoid;
                        if (needScroll) 
                            cont.scrollTop = cont.scrollHeight;
                    }
                } else {
                    alert('直播数据获取失败。');
                }
            },
            onFailure: function(){
                alert('直播数据获取失败。');
            }
        });
        if (page.liveMsg > 0) 
            setTimeout(this._updateLive.bind(this), 5000);
    },
    bindSpec: function(){
        $$('h3').each(function(item){
            item.observe('click', animate._startSpec.bind(animate));
        });
        $$('a.close').each(function(item){
            item.observe('click', function(){
                window.close();
            });
        });
    },
    bindLayer: function(){
        if ($('layer')) {
            $('layer').down('.close').observe('click', function(){
                $('layer').addClassName('hide');
                $('mask').addClassName('hide');
            });
            $('layer').select('.cancel').each(function(item){
                item.observe('click', function(){
                    $('layer').addClassName('hide');
                    $('mask').addClassName('hide');
                });
            });
            $('layer').addClassName('layer');
            this.layerPrepared = true;
        }
    },
    showLayer: function(){
        if (this.layerPrepared) {
            this.showMask();
            $('layer').removeClassName('hide').absPosition();
        }
    },
    showAdvice: function(){
        this.showMask();
        var win = $('advice').absPosition().removeClassName('hide');
        win.down('textarea').focus();
        this.dialog = true;
        mask.observe('click', function(event){
            win.addClassName('hide');
            mask.addClassName('hide');
            page.dialog = true;
        });
    },
    bindBasket: function(ajax){
        var slts = $('exchg_center').select('.info select');
        slts[0].observe('change', function(){
            var ajax = new Ajax.Request('getExCities.action', {
                method: 'get',
                parameters: {
                    'pvid': $F(slts[0])
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        slts[1].update();
                        var def = new Element('option', {
                            value: '0'
                        });
                        def.update('请选择市');
                        slts[1].appendChild(def);
                        for (i = 0; i < json.cities.length; i++) {
                            var data = json.cities[i].split(':');
                            var opt = new Element('option', {
                                value: data[0]
                            });
                            opt.update(data[1]);
                            slts[1].appendChild(opt);
                        }
                    } else {
                        alert('数据获取失败。');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        });
        slts[1].observe('change', function(){
            var ajax = new Ajax.Request('getExDealers.action', {
                method: 'get',
                parameters: {
                    'ciid': $F(slts[1])
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        slts[2].update();
                        var def = new Element('option', {
                            value: '0'
                        });
                        def.update('请选择经销商');
                        slts[2].appendChild(def);
                        for (i = 0; i < json.dealers.length; i++) {
                            var data = json.dealers[i].split(':');
                            var opt = new Element('option', {
                                value: data[0]
                            });
                            opt.update(data[1]);
                            slts[2].appendChild(opt);
                        }
                    } else {
                        alert('数据获取失败。');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        });
        slts[2].observe('change', function(){
            var ajax = new Ajax.Request('getExDealerInfo.action', {
                method: 'get',
                parameters: {
                    'dlid': $F(slts[2])
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        var dd = slts[2].up('.info').select('dd');
                        dd[0].update(json.dn);
                        dd[1].update(json.dt);
                        dd[2].update(json.da);
                    } else {
                        alert('数据获取失败。');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        });
        $('exchg_center').down('.info a').observe('click', function(event){
            var obj = event.findElement();
            obj.addClassName('hide');
            var info = $('exchg_center').select('.info dd');
            var filed = $('exchg_center').select('.info form input');
            filed[0].setValue(info[0].innerHTML);
            filed[1].setValue(info[1].innerHTML);
            filed[2].setValue(info[2].innerHTML);
            filed[3].setValue(info[3].innerHTML);
            filed[4].setValue(info[4].innerHTML);
            $('exchg_center').down('.info dl').addClassName('hide');
            $('exchg_center').down('.info form').removeClassName('hide');
            $('exchg_center').down('.info form input[name="seed"]').setValue(Math.random());
        });
        $('exchg_center').down('.info dd a').observe('click', function(){
            var ajax = new Ajax.Request('orderUserUpdate.action', {
                method: 'get',
                parameters: $('exchg_center').down('.info form').serialize(true),
                onSuccess: function(transport){
                    $('exchg_center').down('.info form').addClassName('hide');
                    $('exchg_center').down('.info dl').removeClassName('hide');
                    $('exchg_center').down('.info p a').removeClassName('hide');
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        });
    },
    bindExhgTab: function(){
        var arrayli = $('exchg_center').select('ul.tabmenu li');
        arrayli.each(function(li){
            if (li.hasClassName('search')) {
                li.down('a').observe('click', function(){
                    $('tid').setValue(5);
                    $('exchg_center').down('form').submit();
                });
                li.down('input[type="submit"]').observe('click', function(){
                    $('tid').setValue(5);
                });
            } else {
                li.down('a').observe('click', function(){
                    var val = arrayli.indexOf(li) - 1;
                    $('tid').setValue(val);
                    $('exchg_center').down('form').submit();
                });
            }
        });
    },
    bindFriend: function(){
        $('fnd_list').select('.firendslist li').each(function(li){
            var arrayobj = li.select('dt a');
            if (arrayobj && arrayobj.length > 0) {
                arrayobj[0].observe('click', function(){
                    $('userid').setValue(arrayobj[0].readAttribute('rel'));
                    var val = li.down('dl').className;
                    if (val.blank()) 
                        val = '0';
                    $('layer').down('select').setValue(val);
                    page.showLayer();
                });
            }
        });
        $('layer').down('p.btn a.rs').observe('click', function(){
            $('layer').down('form').submit();
        });
    },
    bindReg: function(){
        var chk = [null, null];
        $('rname').observe('focus', function(){
            var sta = $('rname').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkname(){
            var val = $F('rname').strip();
            var sta = $('rname').previous('span');
            if (val.blank()) {
                sta.update('<em></em>请输入用户名').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else if (val.lenB() < 6 || val.lenB() > 16) {
                sta.update('<em></em>用户名长度为6-16个字符，中文按2个计算').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                if (val == chk[0]) {
                    sta.update().className = "ok";
                } else {
                    sta.update().className = "load";
                    var ajax = new Ajax.Request('checkUname.action', {
                        method: 'get',
                        parameters: {
                            'uname': val
                        },
                        onSuccess: function(transport){
                            var json = transport.responseText.evalJSON() || false;
                            if (json) {
                                if (json.message) {
                                    sta.update('<em></em>此用户名已经存在').className = "err";
                                    sta.next('.desc').addClassName('hide');
                                    chk[0] = null;
                                } else {
                                    sta.update().className = "ok";
                                    chk[0] = val;
                                }
                            } else {
                                alert('数据获取失败。');
                                sta.update().className = "hide";
                            }
                        },
                        onFailure: function(){
                            alert('服务器连接失败。');
                            sta.update().className = "hide";
                        }
                    });
                }
                return true;
            }
        }
        $('rname').observe('blur', chkname);
        $('rpass').observe('focus', function(){
            var sta = $('rpass').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkpass(){
            var val = $F('rpass').strip();
            var sta = $('rpass').previous('span');
            if (val.blank()) {
                sta.update('<em></em>请输入密码').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else if (!/^.{6,16}$/.test(val)) {
                sta.update('<em></em>密码长度必须大于等于6位').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "ok";
                return true;
            }
        }
        $('rpass').observe('blur', chkpass);
        $('rpassr').observe('focus', function(){
            var sta = $('rpassr').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkrept(){
            var val = $F('rpassr').strip();
            var sta = $('rpassr').previous('span');
            if (val.blank()) {
                sta.update('<em></em>确认密码不能为空').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else if ($F('rpass') != val) {
                sta.update('<em></em>输入的密码不匹配').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "ok";
                return true;
            }
        }
        $('rpassr').observe('blur', chkrept);
        $('rmail').observe('focus', function(){
            var sta = $('rmail').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkmail(){
            var val = $F('rmail').strip();
            var sta = $('rmail').previous('span');
            if (val.blank()) {
                sta.update('<em></em>请输入电子邮件').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(val)) {
                sta.update('<em></em>电子邮件地址格式不正确').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                if (val == chk[1]) {
                    sta.update().className = "ok";
                } else {
                    sta.update().className = "load";
                    var ajax = new Ajax.Request('checkUemail.action', {
                        method: 'get',
                        parameters: {
                            'uemail': val
                        },
                        onSuccess: function(transport){
                            var json = transport.responseText.evalJSON() || false;
                            if (json) {
                                if (json.message) {
                                    sta.update('<em></em>此Email已使用，请更换一个并重试').className = "err";
                                    sta.next('.desc').addClassName('hide');
                                    chk[1] = null;
                                } else {
                                    sta.update().className = "ok";
                                    chk[1] = val;
                                }
                            } else {
                                alert('数据获取失败。');
                                sta.update().className = "hide";
                            }
                        },
                        onFailure: function(){
                            alert('服务器连接失败。');
                            sta.update().className = "hide";
                        }
                    });
                }
                return true;
            }
        }
        $('rmail').observe('blur', chkmail);
        $('rcode').clear();
        $('rcode').observe('focus', function(){
            var sta = $('rcode').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkcode(){
            var val = $F('rcode').strip();
            var sta = $('rcode').previous('span');
            if (val.blank()) {
                sta.update('<em></em>请输入验证码').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else if (!/^[0-9a-zA-Z]{5}$/.test(val)) {
                sta.update('<em></em>验证码格式不正确').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "load";
                var ajax = new Ajax.Request('checkVer.action', {
                    method: 'get',
                    parameters: {
                        'checkVer': val
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            if (json.message) {
                                sta.update('<em></em>').className = "err";
                                sta.next('.desc').addClassName('hide');
                                chk[1] = null;
                            } else {
                                sta.update().className = "ok";
                                chk[1] = val;
                            }
                        } else {
                            alert('数据获取失败。');
                            sta.update().className = "hide";
                        }
                    },
                    onFailure: function(){
                        alert('服务器连接失败。');
                        sta.update().className = "hide";
                    }
                });
                return true;
            }
        }
        $('rcode').observe('blur', chkcode);
        $('rverify').observe('click', this.getVerifyCode.bind(this));
        function chkform(event){
            var step = [chkname, chkpass, chkrept, chkmail, chkcode];
            var returnValue = true;
            for (i = 0; i < step.length; i++) {
                var tp = step[i]();
                if (!tp) {
                    returnValue = tp;
                }
            }
            if (!returnValue || !chk[0] || !chk[1]) {
                if (event) 
                    Event.stop(event);
                return false;
            }
            var val = $F('rterm');
            if (!val) {
                page.showDialog('必须接受使用条款才能继续！');
                $F('rterm').focus();
                Event.stop(event);
                return false;
            }
            var md5str = utility.MD5($F('rpass'));
            $('hidpass').setValue(md5str);
            return true;
        }
        $('form_reg').down('.bottom a').observe('click', function(){
            if (chkform()) 
                $('form_reg').down('form').submit();
        });
        $('form_reg').down('form').observe('submit', chkform);
    },
    bindChgpwd: function(){
        $('opass').observe('focus', function(){
            var sta = $('opass').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkold(){
            var val = $F('opass').strip();
            var sta = $('opass').previous('span');
            if (!/^.{6,16}$/.test(val)) {
                sta.update('<em></em>请输入您现在的密码').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "hide";
                return true;
            }
        }
        $('opass').observe('blur', chkold);
        $('rpass').observe('focus', function(){
            var sta = $('rpass').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkpass(){
            var val = $F('rpass').strip();
            var sta = $('rpass').previous('span');
            if (!/^.{6,16}$/.test(val)) {
                sta.update('<em></em>密码长度必须大于等于6位').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "ok";
                return true;
            }
        }
        $('rpass').observe('blur', chkpass);
        $('rpassr').observe('focus', function(){
            var sta = $('rpassr').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkrept(){
            var val = $F('rpassr').strip();
            var sta = $('rpassr').previous('span');
            if (val.blank()) {
                sta.update('<em></em>确认密码不能为空').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            }
            if ($F('rpass') != val) {
                sta.update('<em></em>输入的密码不匹配').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "ok";
                return true;
            }
        }
        $('rpassr').observe('blur', chkrept);
        function chkform(event){
            var step = [chkold, chkpass, chkrept];
            var returnValue = true;
            for (i = 0; i < step.length; i++) {
                var tp = step[i]();
                if (!tp) {
                    returnValue = tp;
                }
            }
            if (!returnValue) {
                if (event) 
                    Event.stop(event);
                return false;
            }
            var md5str = utility.MD5($F('opass'));
            $('opass').setValue(md5str);
            md5str = utility.MD5($F('rpass'));
            $('rpass').setValue(md5str);
            return true;
        }
        $('form_reg').down('.bottom a').observe('click', function(){
            if (chkform()) 
                $('form_reg').down('form').submit();
        });
        $('form_reg').down('form').observe('submit', chkform);
    },
    bindRecovery: function(){
        $('rname').observe('focus', function(){
            var sta = $('rname').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkname(){
            var val = $F('rname').strip();
            var sta = $('rname').previous('span');
            if (val.lenB() < 6 || val.lenB() > 16) {
                sta.update('<em></em>请输入用户名').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "hide";
                return true;
            }
        }
        $('rname').observe('blur', chkname);
        $('rmail').observe('focus', function(){
            var sta = $('rmail').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkmail(){
            var val = $F('rmail').strip();
            var sta = $('rmail').previous('span');
            if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(val)) {
                sta.update('<em></em>电子邮件地址格式不正确').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "hide";
                return true;
            }
        }
        $('rmail').observe('blur', chkmail);
        $('rcode').clear();
        $('rcode').observe('focus', function(){
            var sta = $('rcode').previous('span');
            sta.className = "hide";
            sta.next('.desc').removeClassName('hide');
        });
        function chkcode(){
            var val = $F('rcode').strip();
            var sta = $('rcode').previous('span');
            if (!/^[0-9a-zA-Z]{5}$/.test(val)) {
                sta.update('<em></em>验证码为空或过短').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "load";
                var ajax = new Ajax.Request('checkVer.action', {
                    method: 'get',
                    parameters: {
                        'checkVer': val
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            if (json.message) {
                                sta.update('<em></em>').className = "err";
                                sta.next('.desc').addClassName('hide');
                                chk[1] = null;
                            } else {
                                sta.update().className = "ok";
                                chk[1] = val;
                            }
                        } else {
                            alert('数据获取失败。');
                            sta.update().className = "hide";
                        }
                    },
                    onFailure: function(){
                        alert('服务器连接失败。');
                        sta.update().className = "hide";
                    }
                });
                return true;
            }
        }
        $('rcode').observe('blur', chkcode);
        $('rverify').observe('click', this.getVerifyCode.bind(this));
        function chkform(event){
            var step = [chkname, chkmail];
            var returnValue = true;
            for (i = 0; i < step.length; i++) {
                var tp = step[i]();
                if (!tp) {
                    returnValue = tp;
                }
            }
            if (!returnValue) {
                if (event) 
                    Event.stop(event);
                return false;
            }
            return true;
        }
        $('form_reg').down('.bottom a').observe('click', function(){
            if (chkform()) 
                $('form_reg').down('form').submit();
        });
        $('form_reg').down('form').observe('submit', chkform);
    },
    bindMember: function(){
        var slts = $('fDealer').select('select');
        slts[0].observe('change', function(){
            var ajax = new Ajax.Request('getExCities.action', {
                method: 'get',
                parameters: {
                    'pvid': $F(slts[0])
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        slts[1].update();
                        var def = new Element('option', {
                            value: '0'
                        });
                        def.update('请选择市');
                        slts[1].appendChild(def);
                        for (i = 0; i < json.cities.length; i++) {
                            var data = json.cities[i].split(':');
                            var opt = new Element('option', {
                                value: data[0]
                            });
                            opt.update(data[1]);
                            slts[1].appendChild(opt);
                        }
                    } else {
                        alert('数据获取失败。');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        });
        slts[1].observe('change', function(){
            var ajax = new Ajax.Request('getExDealers.action', {
                method: 'get',
                parameters: {
                    'ciid': $F(slts[1])
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        slts[2].update();
                        var def = new Element('option', {
                            value: '0'
                        });
                        def.update('请选择经销商');
                        slts[2].appendChild(def);
                        for (i = 0; i < json.dealers.length; i++) {
                            var data = json.dealers[i].split(':');
                            var opt = new Element('option', {
                                value: data[0]
                            });
                            opt.update(data[1]);
                            slts[2].appendChild(opt);
                        }
                    } else {
                        alert('数据获取失败。');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        });
        slts[2].observe('change', function(){
            var ajax = new Ajax.Request('getMemberDealerInfo.action', {
                method: 'get',
                parameters: {
                    'dlid': $F(slts[2])
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        var li = $('dealer_promo').select('li');
                        li[0].update(json.dn);
                        li[1].update(json.dt);
                        li[2].update(json.da);
                    } else {
                        alert('数据获取失败。');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        });
        var layer = $('layer');
        $('frame_subnav').down('.i3').observe('click', function(){
            layer.update('<a class="close"></a><form action="" method="post"><strong>请填写好友邮箱地址：</strong><p><label class="l">好友1:</label><input type="text" name="e1" /></p><p><label class="l">好友2:</label><input type="text" name="e2" /></p><p><label class="l">好友3:</label><input type="text" name="e3" /></p><p><label class="l">好友4:</label><input type="text" name="e4" /></p><p><label class="l">好友5:</label><input type="text" name="e5" /></p><p class="btn w11 c"><a class="rs">清除</a><a>发送</a></p>');
            layer.down('.close').observe('click', page.hideDialog.bind(page));
            var btns = layer.select('.btn a');
            btns[0].observe('click', function(){
                layer.select('form input[name]').each(function(ipt){
                    ipt.clear();
                });
                layer.down('input').activate();
            });
            btns[1].observe('click', function(){
                var ipts = layer.select('form input[name]');
                for (i = 0; i < ipts.length; i++) {
                    if ($F(ipts[i]).blank()) {
                        if (i == 0) {
                            alert('请至少填写一个邮箱地址。');
                            ipts[0].activate();
                            return;
                        }
                    } else {
                        if (!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test($F(ipts[i]))) {
                            alert('请输入正确的邮箱地址。');
                            ipts[i].activate();
                            return;
                        }
                    }
                }
                var ajax = new Ajax.Request('msm.action', {
                    method: 'get',
                    parameters: layer.down('form').serialize(true),
                    onSuccess: function(transport){
                        layer.update('<a class="close"></a><strong class="c">您的邀请已发出</strong><p class="btn w1 c"><a>确定</a></p>');
                        layer.down('.close').observe('click', page.hideDialog.bind(page));
                        layer.down('.btn a').observe('click', page.hideDialog.bind(page));
                    },
                    onFailure: function(){
                        alert('服务器连接失败。');
                    }
                });
            });
            page.showMask();
            layer.absPosition().removeClassName('hide');
            layer.down('input').focus();
        });
        if ($('sltP')) {
            var obj = $('sltP');
            obj.observe('change', function(){
                var city = obj.next('select');
                var ajax = new Ajax.Request('getExCities.action', {
                    method: 'get',
                    parameters: {
                        'pvid': $F(obj)
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            city.update();
                            var def = new Element('option', {
                                value: '0'
                            });
                            def.update('请选择市');
                            city.appendChild(def);
                            for (i = 0; i < json.cities.length; i++) {
                                var data = json.cities[i].split(':');
                                var opt = new Element('option', {
                                    value: data[0]
                                });
                                opt.update(data[1]);
                                city.appendChild(opt);
                            }
                        } else {
                            alert('数据获取失败。');
                        }
                    },
                    onFailure: function(){
                        alert('服务器连接失败。');
                    }
                });
            });
        }
        if ($('user_info')) {
            if ($('user_info').down('.bind')) 
                $('user_info').down('.bind').observe('click', verifySelect);
            if ($('user_info').down('.verify')) 
                $('user_info').down('.verify').observe('click', verifySelect);
        }
        if ($('content_user').down('.carlist')) {
            var btns = $('content_user').select('form .cbtn a');
            btns[0].observe('click', function(){
                btns[0].up('form').submit();
            });
            btns[1].observe('click', verifySelect);
        }
        function verifySelect(){
            window.location.href = 'initbindcar.action';
            /*layer.update('<a class="close"></a><strong class="c">您是否持有车主专属会员卡？</strong><p class="btn w11 c"><a class="rs">是</a><a>否</a></p>');
             layer.down('.close').observe('click', page.hideDialog.bind(page));
             var btns = layer.select('.btn a');
             btns[0].observe('click', bindCard);
             btns[1].observe('click', function(){
             window.location.href = "initbindcar.action";
             });
             page.showMask();
             layer.absPosition().removeClassName('hide');*/
        }
        function bindCard(){
            layer.update('<a class="close"></a><span class="load hide"></span><form action="" method="post"><strong>为顺利完成认证，<br />请填写申请会员卡时所留的信息：</strong><p><label class="l">会员卡号：</label><input type="text" name="cd" /></p><p><label class="l">姓名：</label><input type="text" name="cn" /></p><p><label class="l">手机：</label><input type="text" name="cm" /></p><p class="btn w11 c"><a class="rs">清除</a><a>提交</a></p></form>');
            layer.down('.close').observe('click', page.hideDialog.bind(page));
            var btns = layer.select('.btn a');
            btns[0].observe('click', function(){
                layer.select('form input[name]').each(function(ipt){
                    ipt.clear();
                });
                layer.down('input').activate();
            });
            btns[1].observe('click', submitCard);
            page.showMask();
            layer.absPosition().removeClassName('hide');
            layer.down('input').focus();
        }
        function submitCard(){
            var btns = layer.select('.btn a');
            var ipts = layer.select('form input[name]');
            if ($F(ipts[0]).blank()) {
                alert('请输入您的会员卡号。');
                ipts[0].activate();
                return;
            }
            if ($F(ipts[1]).blank()) {
                alert('请输入您的姓名。');
                ipts[1].activate();
                return;
            }
            if ($F(ipts[2]).blank()) {
                alert('请输入您的手机号。');
                ipts[2].activate();
                return;
            }
            btns[1].stopObserving();
            layer.select('form input[name]').each(function(ipt){
                ipt.disable();
            });
            layer.down('.load').removeClassName('hide');
            var ajax = new Ajax.Request('memberApproval.action', {
                method: 'get',
                parameters: layer.down('form').serialize(true),
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        if (json.flag == "1") {
                            layer.update('<a class="close"></a><strong class="c">恭喜！您已成功认证</strong><p class="btn w2 c"><a class="wide">回到个人中心</a></p>');
                            layer.down('.close').observe('click', page.hideDialog.bind(page));
                            layer.down('.btn a').observe('click', function(){
                                window.location.reload();
                            });
                        } else {
                            layer.update('<a class="close"></a><strong class="c">抱歉，您提交的信息未验证通过</strong><p class="btn w2 c"><a class="wide">返回重新认证</a></p>');
                            layer.down('.close').observe('click', page.hideDialog.bind(page));
                            layer.down('.btn a').observe('click', bindCard);
                        }
                    } else {
                        alert('数据获取失败。');
                        btns[1].observe('click', submitCard);
                        layer.select('form input[name]').each(function(ipt){
                            ipt.enable();
                        });
                        layer.down('.load').addClassName('hide');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                    btns[1].observe('click', submitCard);
                    layer.select('form input[name]').each(function(ipt){
                        ipt.enable();
                    });
                    layer.down('.load').addClassName('hide');
                }
            });
        }
    },
    //bindFindMember: function(){
    //    var obj = $('fnd_find').down('.firendslist');
    //    if (!obj) {
    //        return;
    //    }
    //},
    bindInfo: function(){
        var form = $('form_reg');
        function resume(event){
            var obj = event.findElement();
            var sta = obj.previous('span');
            sta.className = 'hide';
            sta.next('.desc').removeClassName('hide');
        }
        function chkName(){
            var val = $F('iname').strip();
            var sta = $('iname').previous('span');
            if (!/^\w{2,}$/.test(val)) {
                sta.update('<em></em>请输入姓名').className = "err";
                sta.next('.desc').addClassName('hide');
                return false;
            } else {
                sta.update().className = "ok";
                return true;
            }
        }
        $('iname').observe('blur', chkName);
        $('iname').observe('focus', resume);
        function chkIdNum(){
            var val = $F('inum').strip();
            var sta = $('inum').previous('span');
            var tp = $F('itype');
            if (!val.blank() && tp == '身份证') {
                if (!val.isIDCard()) {
                    sta.update('<em></em>请输入正确的身份证号').className = "err";
                    sta.next('.desc').addClassName('hide');
                    return false;
                } else {
                    sta.update().className = "ok";
                    var y, m, d;
                    if (val.length == 18) {
                        y = Number(val.substr(6, 4));
                        m = Number(val.substr(10, 2));
                        d = Number(val.substr(12, 2));
                    } else {
                        y = Number('19' + val.substr(6, 2));
                        m = Number(val.substr(8, 2));
                        d = Number(val.substr(10, 2));
                    }
                    $('iyear').setValue(y).next('select').setValue(m).next('select').setValue(d);
                }
            } else {
                sta.className = 'hide';
                sta.next('.desc').removeClassName('hide');
            }
            return true;
        }
        $('inum').observe('blur', chkIdNum);
        $('inum').observe('focus', resume);
        function chkMobi(){
            var val = $F('imobile').strip();
            var sta = $('imobile').previous('span');
            if (!val.blank()) {
                if (!/^1[3458]\d{9}$/.test(val)) {
                    sta.update('<em></em>请输入正确的手机号').className = "err";
                    sta.next('.desc').addClassName('hide');
                    return false;
                } else {
                    sta.update().className = "ok";
                    return true;
                }
            }
        }
        $('imobile').observe('blur', chkMobi);
        $('imobile').observe('focus', resume);
        function chkPhone(event){
            var obj = event.findElement();
            if (obj.hasClassName('narrow')) {
                obj = obj.next('input');
                if (!obj) {
                    obj = event.findElement().previous('input');
                }
            }
            var val = $F(obj).strip();
            var sta = obj.previous('span');
            var p = obj.previous('input');
            var n = obj.next('input');
            if (!$F(p).blank() || !$F(n).blank() || !val.blank()) {
                if (!/^\d{6,}$/.test(val)) {
                    sta.update('<em></em>请输入正确的电话号码').className = "err";
                    sta.next('.desc').addClassName('hide');
                    return false;
                } else {
                    sta.update().className = "ok";
                    return true;
                }
            }
        }
        function chkArea(event){
            var obj = event.findElement();
            if (event.charCode == 43 || event.keyCode == 43) {
                if (!$F(obj).blank()) {
                    Event.stop(event);
                }
            } else if (event.keyCode == 8 || event.keyCode == 46) {
                return;
            } else if ((event.charCode < 48 || event.charCode > 57) && (event.keyCode < 48 || event.keyCode > 57)) {
                Event.stop(event);
            }
        }
        function chkExt(event){
            if (event.keyCode == 8 || event.keyCode == 46) {
                return;
            } else if ((event.charCode < 48 || event.charCode > 57) && (event.keyCode < 48 || event.keyCode > 57)) {
                Event.stop(event);
            }
        }
        $('ihome').observe('blur', chkPhone);
        $('ihome').observe('focus', resume);
        $('ihome').previous('input').observe('blur', chkPhone);
        $('ihome').previous('input').observe('focus', resume);
        $('ihome').previous('input').observe('keypress', chkArea);
        $('ihome').next('input').observe('blur', chkPhone);
        $('ihome').next('input').observe('focus', resume);
        $('ihome').next('input').observe('keypress', chkExt);
        $('ioffice').observe('blur', chkPhone);
        $('ioffice').observe('focus', resume);
        $('ioffice').previous('input').observe('blur', chkPhone);
        $('ioffice').previous('input').observe('focus', resume);
        $('ioffice').previous('input').observe('keypress', chkArea);
        $('ioffice').next('input').observe('blur', chkPhone);
        $('ioffice').next('input').observe('focus', resume);
        $('ioffice').next('input').observe('keypress', chkExt);
        function finalChk(){
            var mval = $F('imobile').strip();
            var hval = $F('ihome').strip();
            var oval = $F('ioffice').strip();
            var msta = $('imobile').previous('span');
            var hsta = $('ihome').previous('span');
            var osta = $('ioffice').previous('span');
            if (mval.blank() && hval.blank() && oval.blank()) {
                msta.update('<em></em>请至少输入一个联系电话').className = "err";
                msta.next('.desc').addClassName('hide');
                return false;
            }
        }
        $('form_reg').down('.bottom a').observe('click', function(){
            var step = [chkName, chkIdNum, chkMobi, finalChk];
            var returnValue = true;
            for (i = 0; i < step.length; i++) {
                var tp = step[i]();
                if (!tp) {
                    returnValue = tp;
                }
            }
            if (returnValue) {
                $('form_reg').down('form').submit();
            }
        });
        var objYear = $('iyear');
        var objMonth = objYear.next('select');
        var objDay = objMonth.next('select');
        var monthDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        objYear.observe('change', function(){
            if (objMonth.getValue() == '2') {
                setDay();
            }
        });
        objMonth.observe('change', setDay);
        function setDay(){
            var selected = $F(objDay);
            objDay.update();
            var year = Number($F(objYear));
            var month = Number($F(objMonth));
            var days = monthDay[month - 1];
            if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    days = 29;
                }
            }
            if (selected > days) {
                selected = days;
            }
            for (i = 0; i < days; i++) {
                var opt = new Element('option');
                opt.value = i + 1;
                opt.innerHTML = i + 1;
                if (opt.value == selected) {
                    opt.selected = true;
                }
                objDay.appendChild(opt);
            }
        }
    },
    bindVideo: function(){
        var pidx = this.pageIndex + 1;
        this.nowPlaying = 0;
        var list;
        var data;
        var ajax = new Ajax.Request('data/v' + pidx + '.json', {
            method: 'get',
            parameters: {
                'seed': Math.random()
            },
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    data = json;
                    showContent();
                } else {
                    $('down_video').down('ul').update();
                    alert('数据获取失败。');
                }
            },
            onFailure: function(){
                $('down_video').down('ul').update();
                alert('服务器连接失败。');
            }
        });
        function showContent(){
            var begin = page.pageIndex * 9;
            var end = begin + 9;
            if (end > data.length) {
                end = data.length;
            }
            $('down_video').down('ul').update();
            for (i = begin; i < end; i++) {
                var obj = data[i];
                var li = new Element('li');
                li.innerHTML = '<a><img src="../assets/images/down/' + obj.thumbnail + '" alt="' + obj.title + '" height="52" width="80"/></a><dl><dt><a>' + obj.title + '</a></dt></dl>';
                if (i == begin) {
                    li.innerHTML += '<div><div class="mask"></div><div class="icon"></div></div>';
                }
                $('down_video').down('ul').appendChild(li);
            }
            totalPages = Math.ceil(data.length / 9);
            page.showPager(9, totalPages, showContent);
            $('down_video').down('div.mask').setOpacity(.5);
            if (begin != page.nowPlaying) {
                page.nowPlaying = begin;
                thisMovie("tvc_player").gotoSpecVideo(page.nowPlaying);
            }
            list = $('down_video').select('li');
            $('down_video').select('li a').each(function(item){
                item.observe('click', changePlaying);
            });
        }
        function changePlaying(event){
            var item = event.findElement();
            var index = list.indexOf(item.up('li'));
            if (index != page.nowPlaying) {
                $('down_video').down('li div').remove();
                item.up('li').insert({
                    bottom: '<div><div class="mask"></div><div class="icon"></div></div>'
                });
                $('down_video').down('div.mask').setOpacity(.5);
                page.nowPlaying = index;
                thisMovie("tvc_player").gotoSpecVideo(page.nowPlaying);
            }
        }
        function thisMovie(movieName){
            if (navigator.appName.indexOf("Microsoft") != -1) {
                return window[movieName];
            } else {
                return document[movieName];
            }
        }
    },
    setNowPlaying: function(idx){
        if (idx != page.nowPlaying) {
            var obj = $('down_video').select('li')[idx];
            $('down_video').down('li div').remove();
            obj.insert({
                bottom: '<div><div class="mask"></div><div class="icon"></div></div>'
            });
            $('down_video').down('div.mask').setOpacity(.5);
            page.nowPlaying = idx;
        }
    },
    showPager: function(size, total, callback){
        $('frame_right').select('.pager').each(function(item){
            var inner = '';
            if (page.pageIndex == 0) {
                inner += '<span>&lt;&lt;首页</span><span>&lt;上一页</span>';
            } else {
                inner += '<a class="first">&lt;&lt;首页</a><a class="prev">&lt;上一页</a>';
            }
            for (i = 1; i <= total; i++) {
                if (i != page.pageIndex + 1) {
                    inner += '<a class="num">' + i + '</a>';
                } else {
                    inner += '<span class="num">' + i + '</span>';
                }
            }
            if (page.pageIndex + 1 == total) {
                inner += '<span>下一页&gt;</span><span>尾页&gt;&gt;</span>';
            } else {
                inner += '<a class="next">下一页&gt;</a><a class="last">尾页&gt;&gt;</a>';
            }
            item.update(inner);
            if (callback) {
                item.select('a.num').each(function(a){
                    a.observe('click', function(){
                        var pidx = Number(a.innerHTML) - 1;
                        setPageAndGo(pidx);
                    });
                });
                var objF = item.down('.first');
                if (objF) {
                    objF.observe('click', function(){
                        var pidx = 0;
                        setPageAndGo(pidx);
                    });
                    item.down('.prev').observe('click', function(){
                        var pidx = page.pageIndex - 1;
                        setPageAndGo(pidx);
                    });
                }
                var objL = item.down('.last');
                if (objL) {
                    objL.observe('click', function(){
                        var pidx = total - 1;
                        setPageAndGo(pidx);
                    });
                    item.down('.next').observe('click', function(){
                        var pidx = page.pageIndex + 1;
                        setPageAndGo(pidx);
                    });
                }
            }
        });
        function setPageAndGo(idx){
            page.pageIndex = idx;
            callback();
        }
    },
    bindWallpaper: function(){
        var list;
        var current = 0;
        var data;
        var totalPages = 0;
        var ajax = new Ajax.Request('data/w1.json', {
            method: 'get',
            parameters: {
                'seed': Math.random()
            },
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    data = json;
                    showContent();
                } else {
                    $('down_wallpaper').down('ul').update();
                    alert('数据获取失败。');
                }
            },
            onFailure: function(){
                $('down_wallpaper').down('ul').update();
                alert('服务器连接失败。');
            }
        });
        function showContent(){
            var begin = page.pageIndex * 9;
            var end = begin + 9;
            if (end > data.length) {
                end = data.length;
            }
            $('down_wallpaper').down('ul').update();
            for (i = begin; i < end; i++) {
                var obj = data[i];
                var li = new Element('li');
                li.innerHTML = '<a><img src="' + obj.thumbnail + '" alt="' + obj.title + '" height="52" width="80"/></a><dl><dt><a>' + obj.title + '</a></dt></dl>';
                $('down_wallpaper').down('ul').appendChild(li);
                if (i == begin) {
                    showPreview(i);
                }
            }
            totalPages = Math.ceil(data.length / 9);
            page.showPager(9, totalPages, showContent);
            list = $('down_wallpaper').select('li');
            $('down_wallpaper').select('li a').each(function(item){
                item.observe('click', setPreview);
            });
        }
        function setPreview(event){
            var item = event.findElement();
            var index = list.indexOf(item.up('li')) + page.pageIndex * 9;
            if (index != current) {
                showPreview(index);
            }
        }
        function showPreview(idx){
            current = idx;
            var obj = data[idx];
            $('down_wallpaper').down('.big img').src = obj.preview;
            $('down_wallpaper').down('.big dl').update();
            for (var key in obj.files) {
                var dd = new Element('dd');
                dd.innerHTML = '<a href="' + obj.files[key] + '" target="_blank">' + key + '</a>';
                $('down_wallpaper').down('.big dl').appendChild(dd);
            }
        }
    },
    bindScreensaver: function(){
        var list;
        var current = 0;
        var data;
        var totalPages = 0;
        var ajax = new Ajax.Request('data/s1.json', {
            method: 'get',
            parameters: {
                'seed': Math.random()
            },
            onSuccess: function(transport){
                var json = transport.responseText.evalJSON() || false;
                if (json) {
                    data = json;
                    showContent();
                } else {
                    $('down_screensaver').down('ul').update();
                    alert('数据获取失败。');
                }
            },
            onFailure: function(){
                $('down_screensaver').down('ul').update();
                alert('服务器连接失败。');
            }
        });
        function showContent(){
            var begin = page.pageIndex * 9;
            var end = begin + 9;
            if (end > data.length) {
                end = data.length;
            }
            $('down_screensaver').down('ul').update();
            for (i = begin; i < end; i++) {
                var obj = data[i];
                var li = new Element('li');
                li.innerHTML = '<a><img src="' + obj.thumbnail + '" alt="' + obj.title + '" height="52" width="80"/></a><dl><dt><a>' + obj.title + '</a></dt></dl>';
                $('down_screensaver').down('ul').appendChild(li);
                if (i == begin) {
                    showPreview(i);
                }
            }
            totalPages = Math.ceil(data.length / 9);
            page.showPager(9, totalPages, showContent);
            list = $('down_screensaver').select('li');
            $('down_screensaver').select('li a').each(function(item){
                item.observe('click', setPreview);
            });
        }
        function setPreview(event){
            var item = event.findElement();
            var index = list.indexOf(item.up('li')) + page.pageIndex * 9;
            if (index != current) {
                showPreview(index);
            }
        }
        function showPreview(idx){
            current = idx;
            var obj = data[idx];
            $('down_screensaver').down('.big img').src = obj.preview;
            $('down_screensaver').down('.big dd').update('<a href="' + obj.file + '">点击下载</a>');
        }
    },
    setShowRoom: function(idx){
        var subidx = Number(idx) + 1;
        if (subidx) {
            this.setSubnav(subidx);
        }
    },
    addFriend: function(){
        $('frame_forum').select('.detail .add a').each(function(item){
            item.observe('click', function(){
                var ajax = new Ajax.Request('addFriendByPost.action', {
                    method: 'get',
                    parameters: {
                        'fuid': item.readAttribute('rel'),
                        'seed': Math.random()
                    },
                    onSuccess: function(transport){
                        var json = transport.responseText.evalJSON() || false;
                        if (json) {
                            if (json.addfrimsg == "0") {
                                item.replace('添加成功');
                            } else if (json.addfrimsg == "1") {
                                item.replace('对方已经是你好友');
                            } else {
                                page.showDialog(json.addfrimsg);
                            }
                        } else {
                            $('down_screensaver').down('ul').update();
                            alert('数据获取失败。');
                        }
                    },
                    onFailure: function(){
                        $('down_screensaver').down('ul').update();
                        alert('服务器连接失败。');
                    }
                });
            });
        });
    },
    dealerEvent: function(){
        $('popUpMask').setOpacity(0.5);
        $('popUpRule').down('.popUpClose').observe('click', function(){
            $('popUpRule').addClassName('hide');
            $('popUpMask').addClassName('hide');
        });
        $('popUpDealer').down('.popUpClose').observe('click', function(){
            $('popUpDealer').addClassName('hide');
            $('popUpMask').addClassName('hide');
        });

        $('popUpResult').down('.popUpClose').observe('click', function(){
            $('popUpResult').addClassName('hide');
            $('popUpMask').addClassName('hide');
        });
        $('BRule').observe('click', function(){
            $('popUpMask').removeClassName('hide');
            $('popUpRule').removeClassName('hide');
        });
        $('BSearch').observe('click', function(){
            $('popUpMask').removeClassName('hide');
            $('popUpDealer').removeClassName('hide');
        });
        $('BResult').observe('click', function(){
            $('popUpMask').removeClassName('hide');
            $('popUpResult').removeClassName('hide');
        });

        
        $('popUpDealer').select('.tabs a').each(function(n){
            n.observe('click', function(){
                if (n.hasClassName('current')) {
                    return false;
                }
                $('popUpDealer').down('.tabs a.current').removeClassName('current');
                $('popUpDealer').select('ul').each(function(u){
                    u.addClassName('hide');
                });
                n.addClassName('current');
                var ulid = n.readAttribute('rel');
                $(ulid).removeClassName('hide');
                return false;
            });
        });
        
        $('popUpWinning').select('.tabs a').each(function(n){
            n.observe('click', function(){
                if (n.hasClassName('current')) {
                    return false;
                }
                $('popUpWinning').down('.tabs a.current').removeClassName('current');
                $('popUpWinning').select('ul').each(function(u){
                    u.addClassName('hide');
                });
                n.addClassName('current');
                var ulid = n.readAttribute('rel');
                $(ulid).removeClassName('hide');
                return false;
            });
        });
        
        $('popUpResult').select('.tabs a').each(function(n){
            n.observe('click', function(){
                if (n.hasClassName('current')) {
                    return false;
                }
                $('popUpResult').down('.tabs a.current').removeClassName('current');
                $('popUpResult').select('ul').each(function(u){
                    u.addClassName('hide');
                });
                n.addClassName('current');
                var ulid = n.readAttribute('rel');
                $(ulid).removeClassName('hide');
                return false;
            });
        });
       
        
        var due = new Date(2011, 8, 16);
        var now = new Date();
        var left = due - now;
        if (left < 0) {
            //alert('活动结束');
            return;
        }
        left = left / 1000 / 3600 / 24;
        var leftDay = Math.ceil(left);
        $('days').update();
        var parts = new Array();
        while (leftDay > 0) {
            parts[parts.length] = leftDay % 10;
            leftDay = Math.floor(leftDay / 10);
        }
        var str = '';
        for (i = 0; i < parts.length; i++) {
            str = '<i>' + parts[i] + '</i>' + str;
        }
        str += '<em></em>';
        $('days').update(str);
    },
    bindBbsMgmt: function(){
        $('pro_management').select('.Mform input[type=text]').each(function(i){
            i.observe('focus', function(event){
                var item = event.findElement();
                if (item.value == '请输入ID') {
                    item.value = '';
                }
            });
        });
        $('pro_management').select('.Mform textarea').each(function(i){
            i.observe('focus', function(event){
                var item = event.findElement();
                if (item.value == '请输入公告') {
                    item.value = '';
                }
            });
        });
        $('pro_management').select('a.submit').each(function(i){
            i.observe('click', function(event){
                var item = event.findElement();
                item.up('form').submit();
            });
        });
        $('pro_management').select('a.reset').each(function(i){
            i.observe('click', function(event){
                var item = event.findElement();
                item.up('form').reset();
            });
        });
        var fl = {};
        $('pro_management').select('a.edit').each(function(i){
            i.observe('click', function(event){
                var item = event.findElement();
                var fmid = item.readAttribute('rel');
                var mli = [];
                var cnt = item.up('dd');
                cnt.select('span').each(function(n, idx){
                    this[idx] = n.innerHTML;
                }, mli);
                fl[fmid] = true;
                var ic = '<form action="' + path + 'updateModerator.action" method="post"><input type="hidden" name="fmid" value="' + fmid + '" />';
                for (idx = 0; idx < mli.length; idx++) {
                    ic += '<span><input type="text" value="' + mli[idx] + '" name="uname" /><a class="MFormClose"></a></span>';
                }
                ic += '<a class="btnLGray add">增加</a><a class="submit">提交</a></form>';
                cnt.update(ic);
                $('management_3').select('a.MFormClose').each(function(a){
                    a.observe('click', function(event){
                        var item = event.findElement();
                        item.up('span').remove();
                    });
                });
                $('management_3').select('a.add').each(function(a){
                    a.observe('click', function(event){
                        var item = event.findElement();
                        var ni = new Element('span').update('<input type="text" value="" name="uname" /><a class="MFormClose"></a>');
                        ni.down('a').observe('click', function(event){
                            var item = event.findElement();
                            item.up('span').remove();
                        });
                        ni.down('input').observe('blur', function(event){
                            var ipt = event.findElement();
                            var d = ipt.up('form').serialize(true);
                            var v = ipt.getValue();
                            if (v != '') {
                                fl[d.fmid] = false;
                                chkName(ipt.getValue(), d.fmid);
                            }
                        });
                        item.insert({
                            before: ni
                        });
                    });
                });
                cnt.select('span input').each(function(ipt){
                    ipt.observe('blur', function(event){
                        var d = ipt.up('form').serialize(true);
                        var v = ipt.getValue();
                        if (v != '') {
                            fl[d.fmid] = false;
                            chkName(v, d.fmid);
                        }
                    });
                });
                $('management_3').select('a.submit').each(function(a){
                    a.observe('click', function(event){
                        var f = a.up('form');
                        var d = f.serialize(true);
                        if (fl[d.fmid]) {
                            f.submit();
                        }
                    });
                });
            });
        });
        function chkName(str, fmid){
            var ajax = new Ajax.Request(path + 'checkUname.action', {
                method: 'get',
                parameters: {
                    'uname': str
                },
                onSuccess: function(transport){
                    var json = transport.responseText.evalJSON() || false;
                    if (json) {
                        if (json.message) {
                            //alert('此用户名已经存在');
                            fl[fmid] = true;
                        } else {
                            alert('此用户名不存在');
                            fl[fmid] = false;
                        }
                    } else {
                        alert('数据获取失败。');
                    }
                },
                onFailure: function(){
                    alert('服务器连接失败。');
                }
            });
        }
    }
});

var animate = new Animation(120);
var utility = new Utility();
var page = new Page();
var swfu;
function initPage(){
    animate.initSideBar();
    animate.bindTab('frame_right');
    //page.showDialog();
    page.setCurrent();
    if ($('widget_signin')) {
        if ($('widget_signin').hasClassName('long')) {
            Event.observe('img_verify', 'click', page.getVerifyCode.bind(page));
        }
    }
    if ($('ipt_signout')) {
        $('ipt_signout').observe('click', page.signout.bind(page));
    }
    page.bindLayer();
    page.bindForecast();
    page.bindLinks();
    page.bindSearch();
    if ($('upload_placeholder')) {
        animate.bindUpload();
    }
}

Event.observe(window, 'load', initPage);

