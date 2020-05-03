layui.config({
    base: '/js/admin/' //静态资源所在路径
}).extend({
    common: 'common' //公共模块
}).use(['common'], function () {
    var common = layui.common,
        layer = common.layer,
        $ = common.$,
        form = common.form;
    var article_add = {
        init: function () {
            article_add.initSelect();
            article_add.initSubmit();
        },
        initSelect: function () {
            var data = common.getCategory();
            $.each(data, function (idx, obj) {
                $('#categoryId').append("<option value='" + obj.id + "'>" + obj.name + "</option>");//添加选项，第一个参数是text，第二个参数是value
            });
            form.render();
            layer.closeAll("loading")
        },
        initSubmit: function () {
            //监听提交
            form.on('submit(formDemo)', function (data) {
                var article_data = data.field;
                if (article_data.isTop) {
                    article_data.isTop = "true";
                } else {
                    article_data.isTop = "false";
                }
                article_data = JSON.stringify(article_data);
                article_add.submit(article_data);
                return false;
            });
        },
        submit: function (article_data) {
            $.ajax({
                url: '/admin/article',
                type: 'POST',
                data: article_data,
                success: function (result, status, xhr) {
                    layer.closeAll('loading');
                    layer.msg(result.message, {
                        icon: 1,
                        time: 1300
                    }, function () {
                        $('#文章列表', top.document).trigger('click')
                        window.location.reload()
                    });
                }
            });
        }
    };
    $(function () {
        article_add.init();
    });
});
