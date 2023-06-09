var xhr = new XMLHttpRequest();
var attendService = (function () {
    function goToWork(attend, callback, error) {
        console.log("attend : " + attend.userNo + " year : " + attend.godate);
        var url = "/attend/" + attend.userNo;
        console.log(url);
        $.ajax({
            url : url,
            method: 'post',
            dataType : 'json',
            data : JSON.stringify(attend),
            contentType : "application/json; charset=utf-8"
        })
            .done(function(data){
                if(callback) {
                    callback(data);
                }
            })
    }

    function leaveWork(attend, callback, error) {
        console.log("attend : " + attend.attendNo + "leavedate : " + attend.leavedate);
        var url = "/attend/" + attend.attendNo;
        $.ajax({
            url : url,
            method: 'put',
            dataType : 'json',
            data : JSON.stringify(attend),
            contentType : "application/json; charset=utf-8"
        })
            .done(function(data){
                if(callback) {
                    callback(data);
                }
            })
    }

    function getAttendNo(userNo ,callback, error) {
        console.log(userNo);
        let url = "/attend/" + userNo;
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
        goToWork : goToWork
        , leaveWork : leaveWork
        , getAttendNo : getAttendNo
    }
})();