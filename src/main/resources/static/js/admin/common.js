layui.define(['layer', 'form'], function (exports) {
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer;
    var common = {
        IP: "localhost:8080",
        layer: layer,
        $: $,
        form: form,
        init: function () {
            common.ajaxSteup();
            //绑定事件
            common.bindEvent();
        },
        bindEvent: function () {
            //refresh
            $('#refresh').click(function () {
                location.reload();
            });

            //ajax出现错误
            $(document).ajaxError(function (event, xhr, options, exc) {
                console.log(event);
                console.log(xhr);
                console.log(options);
                console.log(exc);
                setTimeout(function () {
                    layer.closeAll('loading');
                }, 2000);
                $(".layui-submit").attr('disabled', false);
            });
        },
        ajaxSteup: function () {
            //ajax默认设置
            $.ajaxSetup({
                cache: false,
                type: "post",
                timeout: 8000,
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                //如遇到登录后请求资源返回401，请在ajax加上下面headers请求头（一般是这个默认的ajax设置没加载完就去请求资源，没有带上token造成的）
                headers: {
                    'Authorization': localStorage.getItem("access_token") == null ? "" : "Bearer " + localStorage.getItem("access_token")
                },
                beforeSend: function (xhr) {
                    layer.load();
                },
                error: function (xhr, textStatus, errorThrown) {
                    common.ajaxError(xhr, textStatus, errorThrown);
                }
            });
        },
        ajaxForm: function (url, data, successFun, errorFun, async, contentType) {
            common.ajaxJson(url, data, successFun, errorFun, async, 'application/x-www-form-urlencoded');
        },
        ajaxJson: function (url, data, successFun, errorFun, async, contentType) {
            if (async == undefined || async == null) {
                async = true;
            }
            if (contentType == undefined || contentType == null || contentType.indexOf('application/json') == 0) {
                contentType = 'application/json;charset=utf-8';
                data = JSON.stringify(data);
            }
            //url = common.IP + url;
            $.ajax({
                cache: false,
                type: "POST",
                timeout: 8000,
                url: url,
                async: async,
                dataType: 'json',
                contentType: contentType,
                data: data,
                headers: {
                    'Authorization': localStorage.getItem("access_token") == null ? "" : "Bearer " + localStorage.getItem("access_token")
                },
                beforeSend: function (xhr) {
                    layer.load();
                },
                success: function (result, status, xhr) {
                    layer.closeAll('loading');
                    if (successFun != undefined && $.isFunction(successFun)) {
                        successFun(result, status, xhr);
                    }
                },
                error: function (xhr, textStatus, errorThrown) {
                    common.ajaxError(xhr, textStatus, errorThrown);
                    if (errorFun != undefined && $.isFunction(errorFun)) {
                        errorFun(xhr, textStatus, errorThrown);
                    }
                }
            });
        },
        ajaxError: function (xhr, textStatus, errorThrown) {
            layer.closeAll('loading');
            $("button[lay-filter='formDemo']").attr('disabled', false);
            var status = xhr.status; // http status
            var msg = xhr.responseText;
            var message = "";
            if (msg != undefined && msg != "") {
                console.log(msg)
                var response = JSON.parse(msg);
                var exception = response.message;
                var exception1 = response.error_description;
                if (exception) {
                    message = exception;
                } else if (exception1) {
                    message = exception1;
                } else {
                    message = response.message;
                }
            }
            var flag = typeof (layer) == "undefined";
            if (status == 400) {
                if (flag) {
                    alert(message);
                } else {
                    layer.msg(message, {
                        icon: 2,
                        time: 1300
                    });
                }
            } else if (status == 401) {
                console.log('access_token过期');
                message = "登录信息过期";
                sessionStorage.clear();
                localStorage.clear();
                layer.msg(message, {
                    icon: 2,
                    time: 1300
                }, function () {
                    parent.location.href = common.IP + "/admin/login.html";
                });
            } else if (status == 403) {
                message = "未授权";
                if (flag) {
                    alert(message);
                } else {
                    layer.msg(message, {
                        icon: 4,
                        time: 1300
                    });
                }
            } else if (status == 404) {
                message = "路径未找到";
                if (flag) {
                    alert(message);
                } else {
                    layer.msg(message, {
                        icon: 2,
                        time: 1300
                    });
                }
            } else if (status == 500) {
                message = '系统错误：' + message + '，请刷新页面，或者联系管理员';
                if (flag) {
                    alert(message);
                } else {
                    layer.msg(message, {
                        icon: 2,
                        time: 1300
                    });
                }
            }
        },
        initUserData: function () {

        },
        login_out: function () {

        },
        checkLogin: function () {

        },
        refreshToken: function () {
            var flag = false;
            $.ajax({
                url: common.IP + '/api/blog-oauth2/oauth2/refresh',
                type: 'POST',
                async: false,
                contentType: "application/x-www-form-urlencoded",
                data: {
                    access_token: localStorage.getItem("access_token"),
                    refresh_token: localStorage.getItem("refresh_token")
                },
                success: function (result, status, xhr) {
                    localStorage.setItem("access_token", result.access_token)
                    localStorage.setItem("token_type", result.token_type)
                    localStorage.setItem("refresh_token", result.refresh_token)
                    localStorage.setItem("expires_in", result.expires_in)
                    localStorage.setItem("scope", result.scope)
                    flag = true;
                }
            });
            return flag;
        },
        getUrlParam: function (key) {
            //获取url后的参数值
            var href = window.location.href;
            var url = href.split("?");
            if (url.length <= 1) {
                return "";
            }
            var params = url[1].split("&");
            for (var i = 0; i < params.length; i++) {
                var param = params[i].split("=");
                if (key == param[0]) {
                    return decodeURI(param[1]);
                }
            }
            return "";
        },
        bytesToSize: function (bytes) {
            if (bytes === 0) return '0 B';
            var k = 1024,
                sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
                i = Math.floor(Math.log(bytes) / Math.log(k));
            return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
        },
        checkHouZui: function (fileName) {
            var d = /\.[^\.]+$/.exec(fileName);
            if (d) {
                return true;
            }
            return false;
        },
        getCookie: function (cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i].trim();
                if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                }
            }
            return "";
        },
        setCookie: function (cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toGMTString();
            document.cookie = cname + "=" + cvalue + "; " + expires;
        },
        clearCookie: function (name) {
            common.setCookie(name, "", -1);
        },
        showRolesCheckbox: function (id) {
            var html_r = "";
            $.each(common.getRoles(), function (idx, obj) {
                html_r += "<input type='checkbox' data-id='" + obj.id + "' title='" + obj.name + "' lay-skin='primary'>"
            });
            $('#' + id).html(html_r);
        },
        getCategory: function () {
            //获取所有标签、分类
            var v = sessionStorage.getItem("Category");
            if (v == null || v == "" || v == undefined) {
                $.ajax({
                    url: '/admin/category',
                    type: 'GET',
                    async: false,
                    success: function (result, status, xhr) {
                        layer.closeAll('loading');
                        sessionStorage.setItem("Category", JSON.stringify(result.data));
                    }
                });
            }
            return JSON.parse(sessionStorage.getItem("Category"));
        },
        getRoles: function () {
            var v = sessionStorage.getItem("roles");
            if (v == null || v == "" || v == undefined) {
                $.ajax({
                    url: '/admin/role',
                    type: 'GET',
                    async: false,
                    success: function (result, status, xhr) {
                        layer.closeAll('loading');
                        sessionStorage.setItem("roles", JSON.stringify(result.data));
                    }
                });
            }
            return JSON.parse(sessionStorage.getItem("roles"));
        },
        getMenus: function () {
            //菜单类型，1：菜单是个url，2:是一个按钮(int)
            var menus;
            $.ajax({
                url: '/admin/permission',
                type: 'GET',
                async: false,
                success: function (result, status, xhr) {
                    layer.closeAll('loading');
                    menus = result.data;
                }
            });
            return common.MenuSort(menus);
        },
        MenuSort: function (menuArry) {
            var mymenus = [];
            GetData(0, menuArry);
            function GetData(id, arry) {
                var childArry = GetParentArry(id, arry);
                if (childArry.length > 0) {
                    for (var i in childArry) {
                        //构建一个树
                        mymenus.push(childArry[i]);
                        GetData(childArry[i].id, arry);
                    }
                }
            }
            //找该id权限的子权限们
            function GetParentArry(id, arry) {
                var newArry = new Array();
                for (var i in arry) {
                    if (arry[i].parentId == id)
                        newArry.push(arry[i]);
                }
                return newArry;
            }
            return mymenus;
        },
        toTree: function (data) {
            data.forEach(function (item) {
                delete item.children;
            });
            var map = {};
            data.forEach(function (item) {
                map[item.id] = item;
            });
            var val = [];
            data.forEach(function (item) {
                var parent = map[item.parentid];
                if (parent) {
                    (parent.children || (parent.children = [])).push(item);
                } else {
                    val.push(item);
                }
            });
            return val;
        },
        bubbleSort: function (arr) {
            var i = arr.length,
                j;
            var tempExchangVal;
            while (i > 0) {
                for (j = 0; j < i - 1; j++) {
                    if (arr[j].sort > arr[j + 1].sort) {
                        tempExchangVal = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tempExchangVal;
                    }
                }
                i--;
            }
            return arr;
        }
    };
    $(function () {
        common.init();
    });
    exports('common', common);
});