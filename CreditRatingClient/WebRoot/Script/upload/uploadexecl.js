jQuery(function() {
    var $ = jQuery,
        $list = $('#thelist'),
        $btn = $('#ctlBtn'),
        $upload = $("#picker"),
        state = 'pending',
        uploader;
 
    uploader = WebUploader.create({
 
        // 不压缩image
        resize: false,
 
        // swf文件路径
        swf:  _ctxPath + '/Script/upload/Uploader.swf',
 
        // 文件接收服务端。
        server: _ctxPath + '/initEnterprise/uploadExecl.do',
         
        fileVal:'upload',
 
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick : {
        	id : '#picker',
            //只能选择一个文件上传
            multiple: false
            },
        //限制只能上传一个文件
        fileNumLimit: 1, 
        accept: {
            title: 'Execl',
            extensions : 'xlsx',
            mimeTypes : 'application/xlsx'
        },

    });
 
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
    	$upload.css("display","none");
    	$btn.css("display","block");
    	$list.text("");
        $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
        '</div>' );
    });
 
    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');
 
        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo( $li ).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');
        $percent.css( 'width', percentage * 100 + '%' );
    });
 
    uploader.on( 'uploadSuccess', function( file,data ) {
        $( '#'+file.id ).find('p.state').text('文件可重复上传，每次上传自动更新');
        $( '#'+file.id ).addClass('upload-state-done');
        popup('文件上传成功');
    });
 
    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });
 
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });
 
    uploader.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {
            state = 'done';
        }
        if ( state === 'uploading' ) {
            $btn.text('暂停上传');
        } else if(state === 'done'){
            $btn.text('文件上传成功,点击重新上传');
        }else{
        	$btn.text('开始上传');
        }
    });
    uploader.onError = function (code) {
    	if(code=="F_DUPLICATE"){
    		popup('该文件已经被选择了!');
    	}else if(code=="Q_EXCEED_NUM_LIMIT"){
    		popup('上传文件数量超过限制!');
    	}else if(code=="F_EXCEED_SIZE"){
    		popup('文件大小超过限制!');
    	}else if(code=="Q_EXCEED_SIZE_LIMIT"){
    		popup('所有文件总大小超过限制!');
    	}else if(code=="Q_TYPE_DENIED"){
    		popup('文件类型不正确或者是空文件!');
    	}else{
    		popup('未知错误!(Eroor:' + code+')');
    	}
        
    };
    $btn.on( 'click', function() {
        if ( state === 'uploading' ) {
            uploader.stop();
        } else if(state === 'done' ){
        	judgeFinance();
        	$upload.css("display","block");
        	$btn.css("display","none");
        	$list.text("警告：报表内容中只能填写数值，否则无法通过校检");
        	 for (var i = 0; i < uploader.getFiles().length; i++) {
                 uploader.removeFile(uploader.getFiles()[i]);
                 var $li = $('#' + uploader.getFiles()[i].id);
                 $li.off().remove();
             }
        	 state= 'pedding';
             fileCount = 0;
             fileSize = 0;
             uploader.reset();
             updateStatus();
        } else{
        	uploader.upload();
        }
    });
     
}); 