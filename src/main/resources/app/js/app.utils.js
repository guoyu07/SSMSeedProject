// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (fmt) {
    "use strict";
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 从datepicker格式化年月日
 * @param id
 */
var dateFormat = function (id) {
    "use strict";
    var date = $(('#' + id)).datepicker('getDate');
    if (isNullOrEmpty(date)) {
        return "";
    } else {
        return date.format("yyyy-MM-dd");
    }
};

/**
 * 是空或null或undefined嘛
 * @param value
 * @returns {boolean}
 */
var isNullOrEmpty = function (value) {
    "use strict";
    value = $.trim(value);

    if (!value && typeof value === "object") {
        return true;
    }

    if (value === "") {
        return true;
    }

    if (value === " ") {
        return true;
    }

    if (typeof(value) === "undefined") {
        return true;
    }
};

/**
 * 如果为Null或空就返回空字符串
 * @param data
 * @returns {*}
 */
var ifIsNullReturnEmptyString = function ifIsNullReturnEmptyString(data) {
    "use strict";
    if (isNullOrEmpty(data)) {
        return "";
    } else {
        return data;
    }
};

/**
 * 是不是数字？
 * @param value
 * @returns {*}
 */
var checkNaN = function (value) {
    "use strict";
    value = $.trim(value);

    if (isNaN(value) || isNullOrEmpty(value)) {
        return 0;
    } else {
        return value;
    }
};

/**
 * 分页
 * @param url url，不带 pageNumber和pageSize
 * @param tableId
 * @param updateTable
 * @param pageNumber
 * @param pageSize
 * @constructor
 */
var Paging = function Paging(url, tableId, updateTable, pageNumber, pageSize) {
    "use strict";
    $.ajax({
        type: "Get",
        url: url + "/pageNumber/" + pageNumber + "/pageSize/" + pageSize,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var pagination = $("#pagination");
            pagination.empty();
            var previousPage = data["previousPage"];
            var currentPage = data["currentPage"];
            var pageCount = data["pageCount"];
            var nextPage = data["nextPage"];
            //拼接上一页
            if (previousPage < 1 || previousPage >= currentPage) {//上一页不可达
                pagination.append("<li class='disabled paging'><a pageNumber='1'>&laquo;</a></li>");
            } else {
                pagination.append("<li class='paging'><a pageNumber=" + previousPage + ">&laquo;</a></li>");
            }

            //拼接页码
            for (var page = 1; page <= pageCount; page++) {
                if (page === currentPage) {
                    pagination.append("<li class='active paging'><a pageNumber=" + page + ">" + page + "</a></li>");
                } else {
                    pagination.append("<li class='paging'><a pageNumber=" + page + ">" + page + "</a></li>");
                }
            }

            //拼接下一页
            if (nextPage < 1 || nextPage <= currentPage) {//下一页不可达
                pagination.append("<li class='disabled paging'><a pageNumber=" + nextPage + ">&raquo;</a></li>");
            } else {
                pagination.append("<li class='paging'><a pageNumber=" + nextPage + ">&raquo;</a></li>");
            }

            $(".paging").click(function () {
                var selectedPageNumber = this.getElementsByTagName("a")[0].getAttribute("pageNumber");
                Paging(url, tableId, updateTable, selectedPageNumber, pageSize);
            });
            //清空table
            $(('#' + tableId)).empty();
            //更新table
            updateTable(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};

/**
 * 手动绑定跳转事件
 */
var bindRedirect = function bindRedirect() {
    "use strict";
    $(".redirect").click(function () {
        $.ajax({
            type: 'GET',
            url: this.getAttribute("url"),
            //data: 'name=John&location=Boston',
            success: function (msg) {
                $.ajaxSetup({
                    async: false
                });
                $("#mainPage").empty().append(msg);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal({
                    title: "出错了！",
                    text: "错误信息" + XMLHttpRequest.status,
                    type: "error",
                    confirmButtonText: "知道了"
                });
            }
        });
    });
};

/**
 * 请求自带_csrf头
 */
$(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        if (options.type === "POST") {
            xhr.setRequestHeader(header, token);
        }
    });
});