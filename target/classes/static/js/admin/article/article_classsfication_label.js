layui.config({
    base: '/js/admin/' //静态资源所在路径
}).extend({
    common: 'common' //公共模块
}).use([ 'common','table'], function () {
    var common = layui.common,
        table = layui.table,
        layer = common.layer,
        form = common.form,
        $ = common.$;
    var category_list = {
        init: function () {
            category_list.initTableData();
            category_list.bindEvent();
        },
        initTableData: function () {
            //数据表格实例
            table.render({
                id: 'idTest',
                elem: '#demo',
                url: '/admin/category',
                height: 'full-180',
                method: 'get',
                page: true,
                even: true,
                cols: [
                    [{
                        field: 'id',
                        title: 'ID',
                        hide: true
                    }, {
                        field: 'name',
                        title: '分类名称',
                        width: 240
                    }, {
                        field: 'number',
                        title: '文章数量',
                        width: 90
                    }, {
                        field: 'createBy',
                        title: '创建时间',
                        sort: true
                    }, {
                        field: 'modifiedBy',
                        title: '修改时间',
                        sort: true
                    }, {
                        fixed: 'right',
                        title: '操作',
                        align: 'center',
                        width: 150,
                        toolbar: '#barDemo'
                    }]
                ],
                request: {
                    pageName: 'page',
                    limitName: 'pageSize'
                },
                response: {
                    statusName: 'code',
                    statusCode: 200,
                    msgName: 'message',
                    countName: 'count',
                    dataName: 'data'
                },
                done: function (res, curr, count) {
                    layer.closeAll('loading');
                },
                parseData: function (res) {
                    return category_list.parseResultData(res);
                }
            });
        },
        parseResultData: function (res) {
            var data = res.data;
            var paseData = [];
            $.each(data, function (idx, obj) {
                paseData.push(obj)
            });
            return {
                "code": res.code,
                "message": res.message,
                "count": res.count,
                "data": paseData
            };
        },
        bindEvent: function(){
            //数据表格监听工具条(查看、编辑、删除按钮)
            table.on('tool(test)', function(obj) {
                var data = obj.data;
                var layEvent = obj.event;
                var id = data.id;
                var name = data.name;
                if(layEvent === 'del') {
                    category_list.deleteById(name, id);
                } else if(layEvent === 'edit') {
                    form.val("update_category", {
                        "id": id,
                        "name": name
                    });
                    layer.open({
                        type: 1,
                        area: ['500px', '300px'],
                        title: ['编辑分类'],
                        content: $('#update_category')
                    });
                }
            });

            $("#addButton").click(function() {
                layer.open({
                    type: 1,
                    area: ['500px', '300px'],
                    title: ['添加标签、分类'],
                    content: $('#add_category')
                });
            });
            //监听(添加)提交
            form.on('submit(addForm)', function(data) {
                var add_data = data.field;
                category_list.add_update(add_data);
                return false;
            });
            //监听(编辑)提交
            form.on('submit(updForm)', function(data) {
                var upd_data = data.field;
                category_list.add_update(upd_data);
                return false;
            });

        },

        add_update: function (category_data) {
            $.ajax({
                url: '/admin/category',
                type: 'POST',
                data: JSON.stringify(category_data),
                success: function (result, status, xhr) {
                    layer.closeAll();
                    layer.msg(result.message, {
                        icon: 1,
                        time: 1300
                    },function () {
                        table.reload('idTest',{});
                    });
                }
            });
        },
        deleteById: function(ts, id){
            layer.confirm('真的删除' + ts + "吗？", {
                icon: 3
            }, function(index) {
                layer.close(index);
                $.ajax({
                    url: '/admin/category/'+id,
                    type: "DELETE",
                    success: function(result, status, xhr) {
                        layer.closeAll('loading');
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1300
                        }, function() {
                            table.reload('idTest', {});
                        });
                    }
                });
            });
        }
    };
    $(function () {
        category_list.init();
    });
});