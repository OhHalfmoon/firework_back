var xhr = new XMLHttpRequest();
var approvalService = (function () {
    function getList(obj, callback, error) {
        var url = "approvalList" + (obj.pageNum ? "?page=" + obj.pageNum : "");
        $.getJSON(url)
            .done(function(data) {
                if(callback) {
                    callback(data);
                }
            })
            .fail(function(xhr) {
                if(error) {
                    error(xhr);
                }
            })
    }
    return {
        getList:getList
    }
})();