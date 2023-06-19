var xhr = new XMLHttpRequest();
var attendService = (function () {
    function goToWork(attend, callback, error) {
        console.log("attend : " + attend.userNo + " year : " + attend.godate);
        var url = "/attend/" + attend.userNo;
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

    return {
        goToWork : goToWork
    }


})();