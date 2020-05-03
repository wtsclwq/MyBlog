layui.config({
    base: '/js/admin/' //静态资源所在路径
}).extend({
    common: 'common' //公共模块
}).use(['common', 'table'], function () {
    var common = layui.common,
        layer = common.layer,
        $ = common.$,
        form = common.form,
        table = layui.table;
    var role_list = {
        init: function () {
            role_list.initTableData();
            role_list.bindEvent();
        },
        initTableData: function () {
            //数据表格实例
            table.render({
                id: 'idTest',
                elem: '#demo',
                url: '/admin/role',
                height: 'full-180',
                method: 'get',
                page: true,
                even: true,
                cols: [
                    [
                        {
                        field: 'id',
                        title: 'ID',
                        hide: true
                    }, {
                        field: 'name',
                        title: '角色标识示',
                        width: 130
                    }, {
                        field: 'description',
                        title: '角色描述',
                        width: 300
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
                        width: 200,
                        title: '操作',
                        align: 'center',
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
                    msgName: 'msg',
                    countName: 'count',
                    dataName: 'data'
                },
                done: function (res, curr, count) {
                    layer.closeAll('loading');
                }
            });
        },
        bindEvent: function () {
            //监听提交
            form.on('submit(formDemo)', function (data) {
                table.reload('idTest', {
                    where: data.field
                });
                return false;
            });
            //数据表格监听工具条(编辑、删除按钮)
            table.on('tool(test)', function (obj) {
                var data = obj.data;
                var layEvent = obj.event;
                if (layEvent === 'del') {
                    var ts = " " + data.name + " ";
                    role_list.deleteById(ts, data.id);
                } else if (layEvent === 'edit') {
                    window.location = "role_update.html?id=" + data.id;
                }
            });
            //复选框批量操作
            table.on('checkbox(test)', function (obj) {
                var checkStatus = table.checkStatus('idTest');
                if (checkStatus.data.length > 0) {
                    $('#deleteall').show();
                } else {
                    $('#deleteall').hide();
                }
            });
            //添加按钮事件
            $('#adduser').on('click', function () {
                window.location = "role_add.html";
            });
        },
        deleteById: function (ts, id) {
            layer.confirm('真的删除' + ts + "吗？", {
                icon: 3
            }, function (index) {
                layer.close(index);
                $.ajax({
                    url: '/admin/role/'+id,
                    type: "DELETE",
                    success: function (result, status, xhr) {
                        layer.closeAll('loading');
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1300
                        }, function () {
                            table.reload('idTest', {});
                        });
                    }
                });
            });
        }
    };
    $(function () {
        role_list.init();
    })
});