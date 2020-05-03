window.onload = function () {
    var articleId = getQueryVariable("articleId");

    // 添加文章信息
    $.ajax({
        type: "get",
        url: "/api/article/" + articleId,
        dataType: "json",
        success: function (json) {
            // 解析json对象，并向页面添加数据
            $("#articleTitle").html(json.title);
            $("#articleCreateBy").html(json.createBy);
            $("#articleContent").html(json.content);
            Prism.highlightAll();
            $("#articlePicture").attr("src", json.pictureUrl);
        }
    });

}
// 获取网页中的参数
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}

// 模态框确认按钮点击事件
$('#addConfirmBtn').click(function () {
    // 刷新页面
    location.reload();
});