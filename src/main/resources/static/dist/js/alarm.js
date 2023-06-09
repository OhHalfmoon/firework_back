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

    function getList(userNo, callback, error) {
        var url = "/api/alarm/member/" + userNo;
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
    //
    // function modify(reply, callback, error) {
    //     console.log(reply);
    //     $.ajax({
    //         url : cp + "/review/" + review.rno,
    //         method: 'put',
    //         data : JSON.stringify(review),
    //         dataType : "json",
    //         contentType : "application/json; charset=utf-8"
    //     })
    //         .done(function(data){
    //             if(callback) {
    //                 callback(data);
    //             }
    //         })
    // }
    //
    // function remove(reviewNo, callback, error) {
    //     $.ajax({
    //         url : cp + "/review/" + reviewNo,
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
    return {
        // name:"AAAA",
        // add:add,
        getList:getList,
        // get:get,
        // remove:remove,
        // modify:modify
    }
})();