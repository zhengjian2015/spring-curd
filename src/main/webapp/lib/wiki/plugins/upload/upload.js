_Upload = function(params){
    params = params || {}
    this._id          = params.id || ''
    this._flag1       = params.flag1 || ''
    this._bindid      = params.bindid || ''
    this._flowid      = params.flowid || ''
    this._taskid      = params.taskid || ''
    this._usercode    = params.usercode || ''
    this._flagbind_id = params.flagbind_id || ''

    this.uuid           = new UUID
    this.files          = []
    this._uuid          = this.uuid.createUUID()
    this.table_id       = 'table-' + this._uuid
    this.tbody_id       = 'tbody-' + this._uuid
    this.modal_id       = 'file_box-' + this._uuid
    this.input_id       = 'file_input' + this._uuid
    this.new_files      = []
    this.uploading      = []
    this.init_files     = []
    this.upload_files   = []
    this.checkboxs_uuid = []

    this.init()
}

_Upload.prototype = {
    init : function(){
        var self = this

        this.html()
        this.init_ajax()
        this.change()
        this.control_btn()
        jQuery(document).on('click','#'+this._id,function(){
            jQuery('#' + self.modal_id).modal('show')
        })
        window.onbeforeunload=onclose;
        function onclose() {
            if (self.uploading.length) {
                return "有附件正在上传，确定关闭或刷新吗？"
            }
        }
    },
    init_ajax : function(){
        var self = this
        jQuery.ajax({
            url  : '/x/workflow/getUploadFileBatch',
            type : 'GET',
            data : {
                flag1    : this._flag1,
                bindid   : this._bindid,
                flowid   : this._flowid,
                taskid   : this._taskid,
                usercode : this._usercode,
            },
            success : function(json){
                self.init_files = JSON.parse(json)
                for (var i = 0; i < self.init_files.length; i++) {
                    file = self.init_files[i]
                    file.md5 = SparkMD5.hash(file.filename + file.filesize)
                }
                self.init_tr()
            },

        })
    },
    init_tr : function(){
        var retval = ''
        var str = _getMultilines(function(){
            /*!
                <tr class="template-upload fade in" data-value="{uuid}">
                    <td class="name" ><div class="checker file-upload" data-type='checkbox'><span><input type="checkbox"></span></div><span><a href="download?atta_id={uuid}">{name}</a></span></td>
                    <td class="size" ><span>{size}</span></td>
                    <td  class="state" style="width:20%" >
                        <button  data-type='state' data-value='{uuid}' data-uploadState='success' class="btn file-upload" disabled>
                            <span>成功</span>
                        </button>
                    </td>
                    <td class="cancel">
                    <button class="btn red file-upload" data-type="delete">
                         <i class="icon-trash icon-white"></i>
                         <span>删除</span>
                    </button>
                    </td>
                </tr>
            */
        })
        for (var i = 0; i < this.init_files.length; i++) {
            var file = this.init_files[i]
            if (! file['flag1'] || file['flag1']==2 || (file['flag1'] == this._flag1 && file['create_user'] == this._usercode && file['flagbind_id'] == this._bindid)) {
                retval += _format(str,{
                    name  : file.filename || '',
                    uuid  : file.atta_id  || '',
                    size  : this.formatFileSize(file.filesize || 0),
                })
            }
        }
        jQuery('#' + this.tbody_id).append(retval)
        return retval
    },
    formatFileSize : function(fsize){
        // 格式化文件大小
        if (fsize < 1024) {
            return fsize+'B';
        } else if (fsize < 1024*1024) {
            return (fsize/1024).toFixed(2)+'KB';
        } else if (fsize < 1024*1024*1024) {
            return (fsize/1024/1024).toFixed(2)+'MB';
        } else {
            return (fsize/1024/1024/1024).toFixed(2)+'GB';
        }
    },
    change : function(){
        var self = this
        jQuery(document).on('change','#' + this.input_id,function(){
                self.add_files(this.files)
                this.value = ""
        })
    },
    add_files : function(files){
        this.new_files = []
        for (var i = 0; i  < files.length; i++) {
            var file = files[i];
                file.md5 = this.file_md5(file)
                file.uuid = this.uuid.createUUID().replace(/-/g,'')
            var index1 = window._indexOf(this.files,file.md5,function(f){return f.md5}) == -1       //不允许重复上传
            var index2 = window._indexOf(this.init_files,file.md5,function(f){return f.md5}) == -1
            if (index1 && index2){ // 如果想添加过滤条件，请添加到此处
                this.files.push(file)
                this.new_files.push(file)
            }
        }
        this.upload_tr(this.new_files)
    },
    file_md5 : function(file) {
        // 仅对文件的最后修改时间 大小 名称
        return SparkMD5.hash(file.name + file.size)
    },
    upload_tr : function(files){
        var retval = ''
        var str = _getMultilines(function(){
            /*
                <tr class="template-upload fade in" data-value="{uuid}">
                    <td class="name" ><div class="checker file-upload" data-type='checkbox'><span><input type="checkbox"></span></div><span>{name}</span></td>
                    <td class="size" ><span>{size}</span></td>
                    <td class="progress_" style="width:20%">
                        <progress id="progress-{uuid}" min="0" max="100" value="0">0</progress>
                    </td>
                    <td  class="start">
                        <button  data-type='start' data-value='{uuid}' class="btn file-upload">
                            <i class="icon-upload icon-white"></i>
                            <span>开始</span>
                        </button>
                    </td>
                    <td class="cancel">
                        <button data-type='cancel' class="btn yellow file-upload" data-value='{uuid}'>
                            <i class="icon-ban-circle icon-white"></i>
                            <span>取消</span>
                        </button>
                    </td>
                </tr>
            */
        })
        for (var i = 0; i < files.length; i++) {
            var file = files[i]
                retval += _format(str,{
                    name : file.name,
                    uuid : file.uuid,
                    size : this.formatFileSize(file.size),
                })
        }
        jQuery('#' + this.tbody_id).append(retval)
    },
    html :function(){
        if (!document.getElementById(this.modal_id)) {
            var str = _format(_getMultilines(function(){
                /*!
                    <div id="{modal_id}" class="modal fade container" tabindex="-1" role="dialog" aria-labelledby="myModal_confirm_big" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="h3ConfirmTitle_big_{modal_id}">文件上传</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row-fluid" id="preConfirmMessage_big_{modal_id}">
                                <div class="row-fluid fileupload-buttonbar">
                                        <div class="span7">
                                            <!-- The fileinput-button span is used to style the file input field as button -->
                                            <span class="btn green fileinput-button" onclick="jQuery('#{input_id}').click()">
                                                <i class="icon-plus icon-white"></i>
                                                <span>添加文件...</span>
                                            </span>
                                            <input type="file" id="{input_id}" style="display: none" multiple>
                                            <button type="submit" class="btn blue start file-upload" data-type='starts'>
                                            <i class="icon-upload icon-white"></i>
                                            <span>开始上传</span>
                                            </button>
                                            <button type="reset" class="btn yellow cancel file-upload" data-type='cancels'>
                                            <i class="icon-ban-circle icon-white"></i>
                                            <span>取消上传</span>
                                            </button>
                                            <button type="button" class="btn red delete file-upload" data-type='deletes'>
                                            <i class="icon-trash icon-white"></i>
                                            <span>删除</span>
                                            </button>
                                            <div class="checker file-upload" data-type="checkboxs"><span><input type="checkbox" class="toggle fileupload-toggle-checkbox"></span></div>
                                        </div>
                                </div>
                            </div>
                            <table id={table_id} role="presentation" class="table table-striped">
                                <tbody id={tbody_id} class="files files-tbody" data-toggle="modal-gallery" data-target="#modal-gallery">
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button class="btn modal_no" data-dismiss="modal" aria-hidden="true">关闭</button>
                        </div>
                    </div>
                */}),{
                    modal_id : this.modal_id,
                    table_id : this.table_id,
                    input_id : this.input_id,
                    tbody_id : this.tbody_id,
                })
            $('body').append(str)
        }
    },
    get_tr_uuid : function(event){
        // 获取一组tr里面的UUID
        var tr = jQuery(event).closest('tr')
        if (tr.length) {
            var _tr = tr[0]
            var _dataset = _tr.dataset || {}
            return _dataset.value
        }
        return ''
    },
    control_btn : function(){
        var self = this
        jQuery(document).on('click','.file-upload',function(){
                var type = this.dataset.type
                var value = self.get_tr_uuid(this)
                if (type=='start') {  // 开始

                    self.start(value,this)

                }else if (type == 'starts'){  // 批量开始
                    self.starts()

                }else if (type == 'cancel') { // 取消

                    self.cancel(value,this)

                }else if (type == 'cancels') { // 批量取消
                    self.cancels()

                }else if (type == 'delete') { // 删除
                    self.delete_file(value,this)

                }else if (type == 'deletes'){ // 批量删除
                    _confirm('确定批量删除文件吗',function(confirm){
                        if (!confirm) {
                            return
                        }
                        self.delete_files()
                    },'批量删除')
                }else if(type == 'checkbox'){

                    self.checkbox(value,this)

                }else if(type == 'checkboxs'){
                    self.checkboxs(this)

                }

        })
    },
    delete_files : function(){
        var attas_id = []
        for (var i = 0; i < this.checkboxs_uuid.length; i++) {
            var uuid = this.checkboxs_uuid[i]
            var event = jQuery('#' + this.tbody_id).find(_format('tr[data-value="{uuid}"]',{uuid:uuid}))

            for (var x = 0; x < this.files.length; x++) {
                var file = this.files[x]
                if (file.is_success && uuid == file.uuid && this.uploading.indexOf(uuid) == -1) {
                    // 成功
                    attas_id.push(file.response.atta_id)
                    if (event.length == 1) {
                        event.remove()
                    }
                }else if (!file.is_success && uuid == file.uuid && this.uploading.indexOf(uuid) == -1) {
                    // 失败
                    if (event.length == 1) {
                        event.remove()
                    }
                }
                this.files.splice(x,1)
                break
            }
            for (var y = 0; y < this.init_files.length; y++) {
                var file = this.init_files[y]
                if (uuid == file.atta_id) {
                    attas_id.push(uuid)
                    if (event.length == 1) {
                        event.remove()
                    }
                }
                this.init_files.splice(y,1)
                break
            }
        }
        if(attas_id.length){
            this.deletes_ajax(attas_id)
        }
        this.checkboxs_uuid = []
    },
    checkbox : function(uuid,event){
        // 单选
        var index = this.checkboxs_uuid.indexOf(uuid)
        if (index == -1){
            this.checkboxs_uuid.push(uuid)
        }else{
            this.checkboxs_uuid.splice(index,1)
        }

        var checker = jQuery(event).find('span')
        if (checker.length) {
            checker.toggleClass('checked')
        }
    },
    checkboxs : function(event){
        // 全选
        var checker = jQuery(event).find('span')
        var in_checked = checker.hasClass('checked')
        if (!in_checked) {
            var trs = jQuery('#' + this.tbody_id).find('tr.template-upload.fade.in')
            for (var i = 0; i < trs.length; i++) {
                var tr = trs[i]
                this.checkboxs_uuid.push(tr.dataset.value)
                var span_checed = jQuery(tr).find('td.name>div.checker.file-upload>span')
                if (span_checed.length) {
                    span_checed.addClass('checked')
                }
            }
            checker.toggleClass('checked')
        }else {
            checker.toggleClass('checked')
            this.checkboxs_uuid = []
            var span_checed = jQuery('#' + this.tbody_id).find('tr.template-upload.fade.in>td.name>div.checker.file-upload>span')
            for (var i = 0; i < span_checed.length; i++) {
                var span = span_checed[i]
                jQuery(span).removeClass('checked')
            }

        }
    },
    start  : function(uuid,event){
            this.upload(uuid)
            jQuery(event).closest('td').remove()
            var retval = this.search_table('cancel',uuid)
            if (retval) {
                jQuery(retval).find('button').attr('disabled','')
            }
    },
    starts  : function(){
        for (var i = 0; i < this.checkboxs_uuid.length; i++) {
            var uuid = this.checkboxs_uuid[i]
            for (var x = 0; x < this.files.length; x++) {
                var file = this.files[x]
                if (uuid == file.uuid && !('response' in file) && !('is_success' in file) && this.uploading.indexOf(uuid) == -1) {
                    var event = jQuery('#' + this.tbody_id).find(_format('tr[data-value="{uuid}"]',{uuid:uuid}))
                    this.start(uuid,jQuery(event).find('td.start'))
                    break
                }
            }

        }
    },
    upload  : function(uuid){
        // 上传
        var str_code = _getMultilines(function(){/*!
                var self = this
                for (var index = 0; index < this.files.length; index++) {
                    var file = this.files[index]
                    if(!file){
                        break
                    }
                    if ('{uuid}' && '{uuid}' == file.uuid) {
                        this.formData_{uuid} = new FormData();
                        this.formData_{uuid}.append('flowid', this._flowid)
                        this.formData_{uuid}.append('taskid', this._taskid)
                        this.formData_{uuid}.append('bindid', this._bindid)
                        this.formData_{uuid}.append('flag1', this._flag1)
                        this.formData_{uuid}.append('usercode', this._usercode)
                        this.formData_{uuid}.append('flagbind_id',this._flagbind_id)
                        this.formData_{uuid}.append('upload', file)

                        this.uploading.push("{uuid}")

                        for (var key in file){
                            this.formData_{uuid}.append(key, file[key])
                        }

                        this.xhr_{uuid} = new XMLHttpRequest();
                        this.xhr_{uuid}.open('POST', '/x/workflow/upload');
                        this.xhr_{uuid}.onload = function () {
                            if (self.xhr_{uuid}.status === 200) {
                                var data = JSON.parse(this.response)
                                if(data.error || !data.length){
                                    self.error_callback('{uuid}',this,file)
                                    file.is_success = false
                                    var del_index = self.uploading.indexOf("{uuid}")
                                    if (del_index != -1){
                                        self.uploading.splice(del_index,1)
                                    }
                                    return
                                }
                                file.is_success = true
                                for (var i = 0; i < data.length; i++) {
                                    var respose_file = data[i]
                                    respose_file.md5 = SparkMD5.hash(respose_file.filename + respose_file.filesize)
                                    if(file.md5 == respose_file.md5){
                                        file.response = respose_file
                                        break
                                    }
                                }

                                file.response_ =  data
                                self.success_callback('{uuid}',this, file)
                            } else {
                                file.is_success = false
                                self.error_callback('{uuid}',this,file)
                            }
                            var del_index = self.uploading.indexOf("{uuid}")
                            if (del_index != -1){
                                self.uploading.splice(del_index,1)
                            }
                        };
                        this.xhr_{uuid}.upload.onprogress = function (event) {
                                    // 进度条显示
                    　　　　if (event.lengthComputable) {
                                if(document.getElementById('progress-'+'{uuid}')){
                        　　　　　　var complete_{uuid} = (event.loaded / event.total * 100 | 0);
                        　　　　　　var progress_{uuid} = document.getElementById('progress-'+'{uuid}');
                        　　　　　　progress_{uuid}.value = progress_{uuid}.innerHTML = complete_{uuid};
                                }
                    　　　　}
                        };
                        this.xhr_{uuid}.send(this.formData_{uuid});


                    }
                }

        */})
        eval(_format(str_code,{uuid:uuid}))
    },
    cancel : function(val,event){
        var index = window._indexOf(this.files,val,function(f){return f.uuid})
        if (index != -1) {
            // file = this.files[index]
            // 删除一行 tr
            var retdom = jQuery(event).closest('tr')
            if (retdom && retdom.length == 1) {
                jQuery(retdom[0]).remove()
            }
            this.files.splice(index,1)
            eval(_format('this.xhr_{uuid}.abort()',{uuid:val}))
            var index_ = this.uploading.indexOf(val)
            if (index_ != -1) {
                this.uploading.splice(index_,1)
            }
            return ;
        }
    },
    cancels : function(){
        for (var i = 0; i < this.checkboxs_uuid.length; i++) {
            var uuid = this.checkboxs_uuid[i]
            for (var x = 0; x < this.files.length; x++) {
                var file = this.files[x]
                if (uuid == file.uuid && !('response' in file) && !('is_success' in file) && this.uploading.indexOf(uuid) == -1) {
                    this.cancel(uuid,jQuery('#' + this.tbody_id).find(_format('tr[data-value={uuid}]>td.cancel',{uuid:uuid})))
                    break
                }
            }
        }
    },
    success_callback : function(uuid,that,file){
        // 文件上传成功后的回调
        this.set_success(this.search_table('progress_', uuid))
        this.switch_cancel(this.search_table('cancel', uuid))
        this.del_start_td(this.search_table('start', uuid))
        this.switch_name(this.search_table('name',uuid),file)
    },
    error_callback  : function(uuid,that,file){
        // 文件上传失败后回调
        this.set_error(this.search_table('progress_', uuid),that)
        this.del_start_td(this.search_table('start', uuid))
        this.switch_cancel(this.search_table('cancel', uuid))
    },
    set_error : function(event,code){
        // 设置失败标志
        if (event) {
            jQuery(event).toggleClass('state')
            jQuery(event).toggleClass('progress_')
            var data = {}
            if (code.response && code.response != 'None') {
                data = JSON.parse(code.response)
            }
            if (data && data.error) {
                jQuery(event).html('<button type="button" data-uploadState="fail" class="btn btn-success red" disabled>失败</button>&nbsp;' + data.error)
            }
            jQuery(event).html('<button type="button" data-uploadState="fail" class="btn btn-success red" disabled>失败</button>&nbsp;' + code.status + '&nbsp;' + code.statusText)
        }
    },
    set_success : function(event){
        // 设置成功标志
        if (event) {
            jQuery(event).toggleClass('state')
            jQuery(event).toggleClass('progress_')
            jQuery(event).html('<button type="button" data-uploadState="success" class="btn btn-success" disabled>成功</button>')
        }
    },
    switch_cancel : function(event){
        // 成功之后将取消按钮换成删除按钮
        if (event) {
            var str = _getMultilines(function(){
                /*
                    <button class="btn red file-upload" data-type="delete">
                        <i class="icon-trash icon-white"></i>
                        <span>删除</span>
                    </button>
                */
            })
            jQuery(event).html(str)
            // this.control_btn()
        }
    },
    search_table : function(type,uuid){
        // 查找table下 某个type 值 == UUID的
        if (document.getElementById(this.table_id)) {
            var find_str = _format('tr[data-value={uuid}]>.{type}',{uuid:uuid,type:type})
            var retval = jQuery('#' + this.table_id).find(find_str)
            if (retval.length) {
                return retval[0]
            }else{
                return ''
            }
        }
    },
    del_start_td : function(event){
        // 删除开始按钮那一列
        if (event) {
            jQuery(event).remove()
        }
    },
    switch_name :function(event,file){
        // 将name转换成下载链接
        if (event) {
            var str = _getMultilines(function(){
                /*!<a href="download?atta_id={atta_id}">{name}</a>*/
            })
            var span = jQuery(event).find('>span')
            if (span && file && file.response && file.response.atta_id) {
                jQuery(span).html(_format(str,{
                    atta_id:file.response.atta_id,
                    name   :file.name,
                }))
            }
        }
    },
    delete_file : function(uuid,event){
        var self = this
        var state = ''
        var m = this.search_table('state',uuid)
        if (m) {
            var d = jQuery(m).find('button')
            if (d.length) {
                state =d[0].dataset.uploadstate
            }
        }

        _confirm('确定删除?',function(confirm){
            if (!confirm) {
                return ;
            }
            if (state) {
                if (state == 'fail') {
                    for (var i = 0; i < self.files.length; i++) {
                        var file = self.files[i]
                        if (file.uuid && file.uuid == uuid) {
                            self.cancel(uuid,event)
                            return
                        }
                    }
                }else if (state == 'success'){
                    for (var i = 0; i < self.init_files.length; i++) {
                        var file = self.init_files[i]
                        if (file.atta_id && file.atta_id == uuid) {
                            self.delete_ajax(uuid,function(){
                                self.cancel(uuid,event)
                            })
                            return
                        }
                    }

                    for (var i = 0; i < self.files.length; i++) {
                        var file = self.files[i]
                        if (file.uuid && file.uuid == uuid && file.response && file.response.atta_id) {
                            var id = file.response.atta_id
                            self.delete_ajax(id,function(){
                                self.cancel(uuid,event)
                            })
                            return
                        }
                    }
                    self.cancel(uuid,event)

                }

            }
        })
    },
    delete_ajax : function(id,callback){

        jQuery.ajax({
            url     : 'delfile',
            type    : 'GET',
            data    : {
                atta_id :id
            },
            success : function(json){
                var retval = JSON.parse(json)
                if (retval) {
                    callback()
                }
            },
        })
    },
    deletes_ajax :function(ids){
        jQuery.ajax({
            url     : 'delfiles',
            type    : 'GET',
            data    : {
                attas_id :JSON.stringify(ids)
            },
        })
    }
}


window.Upload = function(params){
    return new _Upload(params)
}