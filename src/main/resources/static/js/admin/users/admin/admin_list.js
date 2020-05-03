layui.config({
	base: '/js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use([ 'table', 'common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		table = layui.table;
	var adminUser = {
		init: function() {
			//用户状态select
			form.render('select');
			adminUser.initTableData();
			adminUser.bindEvent();
		},
		initTableData: function() {
			//数据表格实例
			table.render({
				id: 'idTest',
				elem: '#demo',
				url: '/admin/user',
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
						field: 'username',
						title: '用户名'
					}, {
						field: 'nickname',
						title: '昵称'
					}, {
						field: 'sex',
						title: '性别',
						width: 80
					}, {
						field: 'status',
						title: '状态',
						width: 100
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
					msgName: 'message',
					countName: 'count',
					dataName: 'data'
				},
				done: function(res, curr, count) {
					layer.closeAll('loading');
				},
				parseData: function(res) {
					return adminUser.parseResultData(res);
				}
			});
		},
		parseResultData: function(res) {
			var data = res.data;
			var paseData = [];
			$.each(data, function(idx, obj) {
				//性别
				if (obj.sex == 1){
					obj.sex = '男';
				}else {
					obj.sex = '女';
				}
				//状态
				if (obj.status == 1){
					obj.status = ' 正常';
				}else if (obj.status == 0){
					obj.status = '锁定';
				} else {
					obj.status = '作废';
				}
				paseData.push(obj);
			});
			return {
				"code": res.code,
				"message": res.message,
				"count": res.count,
				"data": paseData
			};
		},
		bindEvent: function() {
			//监听提交
			form.on('submit(formDemo)', function(data) {
				table.reload('idTest', {
					where: data.field
				});
				return false;
			});
			//数据表格监听工具条(查看、编辑、删除按钮)
			table.on('tool(test)', function(obj) {
				var data = obj.data;
				var layEvent = obj.event;
				if(layEvent === 'detail') {
					adminUser.viewUserDetail(data.id);
				} else if(layEvent === 'del') {
					var ts = data.username;
					adminUser.deleteByUids(ts, data.id);
				} else if(layEvent === 'edit') {
					window.location = "admin_update.html?id=" + data.id;
				}
			});
			//复选框批量操作
			table.on('checkbox(test)', function(obj) {
				var checkStatus = table.checkStatus('idTest');
				if(checkStatus.data.length > 0) {
					$('#deleteall').show()
				} else {
					$('#deleteall').hide()
				}
			});
			//添加按钮事件
			$('#adduser').on('click', function() {
				window.location = 'admin_add.html';
			});
		},
		deleteByUids: function(ts, id) {
			layer.confirm('真的删除' + ts + "吗？", {
				icon: 3
			}, function(index) {
				layer.close(index);
				$.ajax({
					url: '/admin/user/'+id,
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
		},
		viewUserDetail: function(uid) {
			layer.open({
				type: 2,
				skin: 'layui-layer-molv',
				area: ['600px', '340px'],
				maxmin: true,
				content: 'admin_query.html?id=' + id
			});
		}
	};
	$(function() {
		adminUser.init();
	});
});