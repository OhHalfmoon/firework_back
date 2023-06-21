var xhr = new XMLHttpRequest();
var messageService = (function () {

    function add(obj, callback, error) {
        console.log("add() :: " + obj);
        console.log(obj);
        $.ajax({
            url : "/api/message/send",
            method: 'post',
            dataType : 'json',
            data : JSON.stringify(obj),
            contentType : "application/json; charset=utf-8"
        })
            .done(function(data){
                if(callback) {
                    callback(data);
                }
            })
    }

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

    // function remove(alarmNo, callback, error) {
    //     $.ajax({
    //         url : "/api/alarm/" + alarmNo,
    //         method: 'delete',
    //         dataType : 'json'
    //     })
    //         .done(function(data){
    //             if(callback) {
    //                 callback(data);
    //             }
    //         })
    //         .fail(function(xhr){
    //             console.log(xhr);
    //         })
    // }

    function getAllUser(obj, callback, error) {
        var url = "/api/message/sender/" + obj.senderNo + "/memberList"
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
        // getCount:getCount,
        add:add,
        getListByReceiver:getListByReceiver,
        getListBySender:getListBySender,
        getAllUser:getAllUser,
        // get:get,
        // remove:remove,
        // modify:modify
    }
})();