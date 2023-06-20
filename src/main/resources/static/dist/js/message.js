var xhr = new XMLHttpRequest();
var messageService = (function () {
    //
    // function add(review, callback, error) {
    //     console.log("add() :: " + review);
    //     console.log(review);
    //     $.ajax({
    //         url :  cp + "/review/new",
    //         method: 'post',
    //         dataType : 'json',
    //         data : JSON.stringify(review),
    //         contentType : "application/json; charset=utf-8"
    //     })
    //         .done(function(data){
    //             if(callback) {
    //                 callback(data);
    //             }
    //         })
    // }

    // function get(alarmNo, callback) {
    //     var url = cp + "/api/alarm/" + alarmNo;
    //     console.log(url);
    //     $.getJSON(url)
    //         .done(function(data) {
    //             if(callback) {
    //                 callback(data);
    //             }
    //         })
    // }

    // 알림 수 출력
    function getCount(userNo, callback, error) {
        var url = "/api/alarm/count/" + userNo;
        $.getJSON(url)
            .done(function (data){
                if(callback) {
                    callback(data);
                }
            })
            .fail(function (xhr) {
                if(error) {
                    error(xhr);
                }
            })
    }

    // 사용자에 따른 알림 리스트 출력
    function getListByReceiver(obj, callback, error) {
        var url = "/api/message/receiver/" + obj.userNo + "/" + (obj.pageNum ? "?page=" + obj.pageNum : "");
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

    function getListBySender(obj, callback, error) {
        var url = "/api/message/sender/" + obj.userNo + "/" + (obj.pageNum ? "?page=" + obj.pageNum : "");
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
    // function modify(obj, callback, error) {
    //     $.ajax({
    //         url : "/api/alarm/" + obj.alarmNo,
    //         method: 'put',
    //         data : JSON.stringify(obj),
    //         dataType : "json",
    //         contentType : "application/json; charset=utf-8"
    //     })
    //         .done(function(data){
    //             if(callback) {
    //                 callback(data);
    //             }
    //         })
    // }

    function remove(alarmNo, callback, error) {
        $.ajax({
            url : "/api/alarm/" + alarmNo,
            method: 'delete',
            dataType : 'json'
        })
            .done(function(data){
                if(callback) {
                    callback(data);
                }
            })
            .fail(function(xhr){
                console.log(xhr);
            })
    }
    return {
        // getCount:getCount,
        // add:add,
        getListByReceiver:getListByReceiver,
        getListBySender:getListBySender,
        // get:get,
        remove:remove,
        // modify:modify
    }
})();