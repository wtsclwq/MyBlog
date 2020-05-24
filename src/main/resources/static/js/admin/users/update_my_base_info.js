layui.config({
	base: '/js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common', 'laydate'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		laydate = layui.laydate;
	var currentUser = {
		init: function() {
			//执行一个laydate实例
			laydate.render({
				elem: '#birthday',
				max: 0,
				format: 'yyyy-MM-dd HH:mm:ss'
			});

			currentUser.initData();
			currentUser.initSubmit();
			currentUser.initFormCheck();
		},
		initData: function() {
			form.render();
			//用户数据
			$.ajax({
				url: '/admin/user/current',
				type: 'get',
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					form.val("my_base_info", result.data)
					$("input[name='id']").val(result.data.id);
					$("input[name=upModel][value='0']").attr("checked", result.data.sex == 0 ? true : false);
					$("input[name=upModel][value='1']").attr("checked", result.data.sex == 1 ? true : false);
					form.render();
				}
			});
		},
		initSubmit: function() {
			//表单监听提交
			form.on('submit(formDemo)', function(data) {
				var user_data = JSON.stringify(data.field);
				$.ajax({
					url: '/admin/user/current',
					type: 'PUT',
					data: user_data,
					async: false,
					success: function(result, status, xhr) {
						common.refreshToken();
						layer.closeAll('loading');
						layer.msg(result.message, {
							icon: 1,
							time: 1300
						}, function() {
							window.location.reload();
						});
					}
				});
				return false;
			});
		},
		initFormCheck: function() {
			form.verify({
				username: function(value, item) { //value：表单的值、item：表单的DOM对象
					if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
						return '昵称不能有特殊字符';
					}
					if(!(/^[\S]{2,10}$/.test(value))) {
						return '昵称必须2到10位，且不能出现空格';
					}
					if(/(^\_)|(\__)|(\_+$)/.test(value)) {
						return '昵称首尾不能出现下划线\'_\'';
					}
					if(/^\d+$/.test(value)) {
						return '昵称不能全为数字';
					}
				},
				myphone: function(value, item) {
					if(value) {
						if(!(/^[1][3,4,5,7,8][0-9]{9}$/.test(value))) {
							return '请填写正确的电话';
						}
					}
				},
				myemail: function(value, item) {
					if(value) {
						if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value))) {
							return "请填写正确的邮箱"
						}
					}
				}
			});
		},
	};
	$(function() {
		currentUser.init();
	});
});