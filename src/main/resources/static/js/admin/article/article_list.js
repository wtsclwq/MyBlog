layui.config({
	base: '/js/admin' //静态资源所在路径
}).extend({
	common: '/common' //公共模块
}).use(['table','common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		table = layui.table;
	var article_list={
		init: function(){
			//监听提交
			form.on('submit(formDemo)', function(data) {
				table.reload('idTest', {
					where: data.field
				});
				return false;
			});
			article_list.initTableData();
			article_list.bindEvent();

        },
		initTableData: function(){
            //数据表格实例
			table.render({
				id: 'idTest',
				elem: '#demo',
				url: '/admin/article',
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
						field: 'title',
						title: '标题',
						width: 240
					}, {
						field: 'isTop',
						title: '是否顶置',
						width: 90
					},{
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
				done: function(res, curr, count) {
                    layer.closeAll('loading');
				},
				parseData: function(res) {
                    return article_list.parseResultData(res);
				}
			});
        },
		parseResultData: function(res){


            var data = res.data;
			var paseData = [];
			$.each(data, function(idx, obj) {
				var bid = obj.id;
				// 是否顶置 1:顶置，0:默认不顶置
				if(obj.isTop) {
					obj.isTop = "<input id='" + bid + "' lay-filter='isTop' type='checkbox' name='istop' lay-skin='switch' lay-text='是|否' checked>";
				} else{
					obj.isTop = "<input id='" + bid + "' lay-filter='isTop' type='checkbox' name='istop' lay-skin='switch' lay-text='是|否'>";
				}
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
				if(layEvent === 'del') {
					var ts = data.title;
					article_list.deleteById(ts, data.id);
				} else if(layEvent === 'edit') {
					//请求更新页面
                    window.location = "article_update.html?id=" + data.id;
				}
			});
			//时候顶置（switch）
			form.on('switch(isTop)', function(data) {
				var obj = data.elem;
				var id = obj.getAttribute("id");
				var article_data = JSON.stringify({
					id: id,
					isTop: obj.checked ? 'true' : 'false'
				});
				console.log("niuzi"+JSON.stringify(article_data));
				//暂时设置为禁用状态
				obj.setAttribute('disabled', 'disabled');
				form.render();
				article_list.updateByCheckBox(obj, article_data);
			});
		},
		updateByCheckBox: function(obj, data){
			$.ajax({
				url: '/admin/article/switch',
				type: "PUT",
				data: data,
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					layer.msg(result.message, {
						icon: 1,
						time: 1300
					}, function() {
						//成功的话就接触禁用状态
						obj.removeAttribute('disabled', 'disabled');
						form.render();
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
					url: '/admin/article/'+id,
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
	$(function(){
		article_list.init();
	});
});