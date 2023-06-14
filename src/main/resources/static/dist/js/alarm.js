console.log("Alarm Module");
var xhr = new XMLHttpRequest();
var alarmService = (function () {
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
    function getList(obj, callback, error) {
        var url = "/api/alarm/member/" + obj.userNo + "/" + (obj.alarmNo || "");
        // console.log(url);
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
        getCount:getCount,
        // name:"AAAA",
        // add:add,
        getList:getList,
        // get:get,
        remove:remove,
        // modify:modify
    }
})();