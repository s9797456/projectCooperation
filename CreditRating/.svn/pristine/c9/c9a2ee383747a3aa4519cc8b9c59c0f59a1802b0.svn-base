 Ext.define('Ext.ux.ExtKindEditor', {
      extend: 'Ext.form.field.TextArea',
      alias: 'widget.extkindeditor',//xtype名称
      initComponent: function () {
          this.html = "<textarea id='" + this.getId() + "-input' name='" + this.name + "'></textarea>";
          this.callParent(arguments);
          this.on("boxready", function (t) {
              this.inputEL = Ext.get(this.getId() + "-input");
              this.editor = KindEditor.create('textarea[name="' + this.name + '"]', {
                 height: t.getHeight()-18,//有底边高度，需要减去
                 width: t.getWidth() - t.getLabelWidth(),//宽度需要减去label的宽度
                 basePath: 'kindeditor-4.1.10/',
                 uploadJson: _ctxPath+'/kindeditor-4.1.10/jsp/upload_json.jsp',//路径自己改一下
                 fileManagerJson: _ctxPath+'/kindeditor-4.1.10/jsp/file_manager_json.jsp',//路径自己改一下
                 resizeType: 0,
                 wellFormatMode: true,
                 newlineTag: 'br',
                 allowFileManager: true,
                 allowPreviewEmoticons: true,
                 allowImageUpload: true,
                 filterMode: false,//是否开启过滤模式
                 items: [
                     'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
						'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
						'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
						'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
						'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
						'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
						'anchor', 'link', 'unlink', '|', 'about'
                 ]
             });
         });
         this.on("resize", function (t, w, h) {
             this.editor.resize(w - t.getLabelWidth(), h-18);
         });
     },
     setValue: function (value) {
         if (this.editor) {
             this.editor.html(value);
         }
     },
     reset: function () {
         if (this.editor) {
             this.editor.html('');
         }
     },
     setRawValue: function (value) {
         if (this.editor) {
             this.editor.text(value);
         }
     },
     getValue: function () {
         if (this.editor) {
             return this.editor.html();
         } else {
             return '';
         }
     },
     getRawValue: function () {
         if (this.editor) {
             return this.editor.text();
         } else {
             return '';
         }
     }
 });