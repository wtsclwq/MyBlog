layui.config({
    base: '/js/admin/' //静态资源所在路径
}).extend({
    common: 'common' //公共模块
}).use(['upload', 'common'], function () {
    var common = layui.common,
        layer = common.layer,
        $ = common.$,
        form = common.form;
    var article_update = {
        init: function () {
            article_update.initArticleData();
            article_update.initSubmit();
        },
        initSelect:function(id){
            var data = common.getCategory();
            $.each(data,function (idx,obj) {
                var html;
                if(obj.id == id){//对比重分类id，将文章的分类设为选中状态
                    html = "<option selected value="+obj.id+ ">"+obj.name+"</option>";
                }else {
                    html = "<option value="+obj.id+">"+obj.name+"</option>";
                }
                $('#categoryId').append(html);//添加选项，第一个参数是text，第二个参数是value
            });
            form.render();
            layer.closeAll("loading")
        },
        initArticleData: function () {
            var id = common.getUrlParam("id");
            $.ajax({
                //根据id获取文章信息
                url: '/admin/article/' + id,
                type: 'GET',
                success: function (result, status, xhr) {
                    layer.closeAll('loading');
                    $("input[name='id']").val(result.data.id);
                    $("input[name='title']").val(result.data.title);
                    $("textarea[name='summary']").val(result.data.summary);
                    $("input[name='pictureUrl']").val(result.data.pictureUrl);
                    $("textarea[name='content']").val(result.data.content);
                    if (result.data.isTop) {
                        $("input[name='isTop']").attr("checked", "checked");
                    }
                    article_update.initSelect(result.data.categoryId);
                    form.render();
                    //editor.txt.html(result.data.content);
                }
            });
        },
        initSubmit: function () {
            //监听提交
            form.on('submit(formDemo)', function (data) {
                var article_data = data.field;
                if (article_data.isTop){
                    article_data.isTop = "true";
                } else {
                    article_data.isTop = "false";
                }
                article_data = JSON.stringify(article_data);
                article_update.submit(article_data);
                return false;
            });
        },
        submit: function (article_data) {
            $.ajax({
                url: '/admin/article',
                type: 'PUT',
                data: article_data,
                success: function (result, status, xhr) {
                    layer.closeAll('loading');
                    layer.msg(result.message, {
                        icon: 1,
                        time: 1300
                    }, function () {
                        window.history.back(-1);
                    });
                }
            });
        }
    };
    $(function () {
        article_update.init();
    });
});