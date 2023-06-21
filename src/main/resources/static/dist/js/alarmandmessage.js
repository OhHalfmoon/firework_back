$(function () {
    var userNo = 1;
    // user.userNo로 바꿀 예정!!
    moment.locale('ko');
    alarmService.getCount(userNo, function (result) {
        $(".alarmcount").html(result);
    })

    alarmService.getList({userNo: userNo}, function (result) {
        var str = "";
        for (var i in result.content) {
            str += getAlarmLiStr(result.content[i]);
        }
        $(".alarms").html(str);
    });

    function getAlarmLiStr(obj) {
        return `<div class="alarm-divider dropdown-divider" data-alarmno="${obj.alarmNo}"></div>
				    <a href="#" class="alarm dropdown-item" data-alarmno="${obj.alarmNo}" style="background: lightgrey">
                    ${obj.alarmTitle}
				    <span class="float-right text-muted text-sm">${moment(obj.regdate).fromNow()}</span>
                    </a>
				    `;

    }


    // $(".alarm").on("click", "a", function () {
    //     event.preventDefault();
    //     var $this = $(this);
    //     console.log($this);
    //     var obj = {alarmNo: $this.data("alarmno"), alarmCheck: true}
    //     alarmService.modify(obj, function (result) {
    //         // console.log(result);
    //     });
    // });

    $(".alarms").on("click", ".alarm", function () {
        event.stopPropagation();
        var $this = $(this);
        var alarmNo = $this.data("alarmno");
        alarmService.remove(alarmNo, function (result) {
            // console.log(result);
        });
    });

    $(".btn-more").on("click", function () {
        event.stopPropagation();
        var pageNum = 2;
        alarmService.getList({userNo: userNo, pageNum: pageNum}, function (result) {
            if (!result.content.length) {
                $(".btn-more").prop("disabled", true);
                return;
            }
            var str = "";
            for (var i in result.content) {
                str += getAlarmLiStr(result.content[i]);
            }
            $(".alarms").append(str);
        });
        pageNum++;
    })

    var divison = false;

    messageService.getListByReceiver({userNo: userNo}, function (result) {
        var pageStr = "";
        var str = "";
        for (var i in result.messageList.content) {
            str += getReceiveLiStr(result.messageList.content[i]);
        }

        $(".messagepagination").html(getPage(result.pageResponseDTO));
        $(".messages").html(str);

    });

    function getPage(obj) {
        let str = "";
        if (obj.hasPrevBlock) {
            str += "<li class='page-item'><a class='page-link' href='#' data-pagenum='" + (obj.startPageNum - 1) + "'>" + "<i class='fas fa-angle-double-left'></i></a></li>";
        }
        if (obj.prev) {
            str += "<li class='page-item'><a class='page-link' href='#' data-pagenum='" + (obj.page - 1) + "'>" + "<i class='fas fa-angle-left'></i></a></li>";
        }
        for (let i = obj.startPageNum; i <= obj.endPageNum; i++) {
            if (obj.page == i)
                str += "<li class='page-item active'><a href='#' class='page-link' data-pagenum='" + i + "'>" + i + "</a></li>";
            else
                str += "<li class='page-item'><a href='#' class='page-link' data-pagenum='" + i + "'>" + i + "</a></li>";
        }
        if (obj.next) {
            str += "<li class='page-item'><a class='page-link' href='#' data-pagenum='" + (obj.page + 1) + "'>" + "<i class='fas fa-angle-right'></i></a></li>";
        }
        if (obj.hasNextBlock) {
            str += "<li class='page-item'><a class='page-link' href='#' data-pagenum='" + (obj.endPageNum + 1) + "'>" + "<i class='fas fa-angle-double-right'></i></a></li>";
        }
        return str;
    }

    function getReceiveLiStr(obj) {
        return `<div class="col-1 text-center"><input type="checkbox" data-messageno="${obj.messageNo}"></div>
                    <div class="col-2 text-center">
				        ${obj.senderName}
                    </div>
                    <div class="col-5 text-center">
				        ${obj.messageTitle}
                    </div>
				    <div class="col-4 text-center">
				        ${moment(obj.regdate).locale("ko").format('YYYY/MM/D HH:mm')}
                    </div>
                    </a>
				    `;

    }


    function getSendLiStr(obj) {
        return `<div class="col-1 text-center"><input type="checkbox" data-messageno="${obj.messageNo}"></div>
                    <div class="col-2 text-center">
				        ${obj.receiverName}
                    </div>
                    <div class="col-5 text-center">
				        ${obj.messageTitle}
                    </div>
				    <div class="col-4 text-center">
				        ${moment(obj.regdate).locale("ko").format('YYYY/MM/D HH:mm')}
                    </div>
                    </a>
				    `;
    }

    function getDivision(obj) {
        if (!obj)
            return `<div class="col-1">

                        </div>
                        <div class="col-2 text-center">
                            받는 사람
                        </div>
                        <div class="col-5 text-center">
                            제목
                        </div>
                        <div class="col-4 text-center">
                            날짜
                        </div>`
        else
            return `<div class="col-1">

                        </div>
                        <div class="col-2 text-center">
                            보낸 사람
                        </div>
                        <div class="col-5 text-center">
                            제목
                        </div>
                        <div class="col-4 text-center">
                            날짜
                        </div>`
    }

    $("#messageListModal").on("click", "#messageWrite", function () {
        event.stopPropagation();
        var str = "";
        str += ` <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">쪽지쓰기</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="card-body">
                    <div class="row">
                        <div class="col-2 text-center">
                            <h5>제목</h5>
                        </div>
                        <div class="col-10">
                            <input type="text" size=70 maxlength=35>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col-2 text-center">
                            <button class="btn btn-outline-secondary btn-xs" data-toggle="modal" data-target="#selectReceiverModal">받는 사람</button>
                        </div>
                        <div class="col-10 toReceiver">

                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col-2 text-center">
                            <h5>내용</h5>
                        </div>
                        <div class="col-10">
                            <textarea rows="7" cols="70" style="resize: none" maxlength="300"></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-success btn-sm">전송</button>
                <button type="button" class="btn btn-outline-primary btn-sm" id="toMessageList">목록</button>
            </div>`

        $(".messageModal").html(str);
    })

    $("#messageListModal").on("click", "#toMessageList", function () {
        var str = "";
        str += `<!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">쪽지</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="card-body">
                    <div class="row">
                        <div class="col-10 messageCategory">
                            <a class="receive" style="color:purple" href="#">받은 쪽지</a> | <a class="send" style="color:green" href="#">보낸 쪽지</a>
                        </div>
                        <div class="col-2">
                            <button type="button" class="btn btn-warning" id="messageWrite">쪽지 쓰기</button>
                        </div>
                    </div>
                    <hr/>
                    <div class="division row">
                        <div class="col-1">

                        </div>
                        <div class="col-2 text-center">
                            보낸 사람
                        </div>
                        <div class="col-5 text-center">
                            제목
                        </div>
                        <div class="col-4 text-center">
                            날짜
                        </div>
                    </div>
                    <hr/>
                    <div class="messages row">

                    </div>
                    <br/>
                    <div class="pagination justify-content-center messagepagination">

                    </div>
                    </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger btn-sm">삭제</button>
                <button type="button" class="btn btn-outline-primary btn-sm" data-dismiss="modal">닫기</button>
            </div>`

        $(".messageModal").html(str);
        messageService.getListByReceiver({userNo: userNo}, function (result) {
            var pageStr = "";
            var str = "";
            for (var i in result.messageList.content) {
                str += getReceiveLiStr(result.messageList.content[i]);
            }

            $(".messagepagination").html(getPage(result.pageResponseDTO));
            $(".messages").html(str);

        });
        $(".messagepagination").on("click", ".page-link", function () {
            var pageNum = $(this).data("pagenum");
            if (divison) {
                messageService.getListByReceiver({userNo: userNo, pageNum: pageNum}, function (result) {
                    var str = "";
                    for (var i in result.messageList.content) {
                        str += getReceiveLiStr(result.messageList.content[i]);
                    }
                    $(".messagepagination").html(getPage(result.pageResponseDTO));
                    $(".messages").html(str);
                });
            } else {
                messageService.getListBySender({userNo: userNo, pageNum: pageNum}, function (result) {
                    var str = "";
                    for (var i in result.messageList.content) {
                        str += getSendLiStr(result.messageList.content[i]);
                    }
                    $(".messagepagination").html(getPage(result.pageResponseDTO));
                    $(".messages").html(str);
                });
            }
        })

        $(".messageCategory").on("click", "a", function () {
            if ($(this).attr('class') == 'send') {
                divison = false;
                messageService.getListBySender({userNo: userNo}, function (result) {
                    var str = "";
                    for (var i in result.messageList.content) {
                        str += getSendLiStr(result.messageList.content[i]);
                    }
                    $(".messagepagination").html(getPage(result.pageResponseDTO));
                    $(".messages").html(str);
                    $(".division").html(getDivision(divison));
                });
            } else {
                divison = true;
                messageService.getListByReceiver({userNo: userNo}, function (result) {
                    var str = "";
                    for (var i in result.messageList.content) {
                        str += getReceiveLiStr(result.messageList.content[i]);
                    }
                    $(".messagepagination").html(getPage(result.pageResponseDTO));
                    $(".messages").html(str);
                    $(".division").html(getDivision(divison));
                });
            }
        })
    })
    $(".messagepagination").on("click", ".page-link", function () {
        var pageNum = $(this).data("pagenum");
        if (divison) {
            messageService.getListByReceiver({userNo: userNo, pageNum: pageNum}, function (result) {
                var str = "";
                for (var i in result.messageList.content) {
                    str += getReceiveLiStr(result.messageList.content[i]);
                }
                $(".messagepagination").html(getPage(result.pageResponseDTO));
                $(".messages").html(str);
            });
        } else {
            messageService.getListBySender({userNo: userNo, pageNum: pageNum}, function (result) {
                var str = "";
                for (var i in result.messageList.content) {
                    str += getSendLiStr(result.messageList.content[i]);
                }
                $(".messagepagination").html(getPage(result.pageResponseDTO));
                $(".messages").html(str);
            });
        }
    })

    $(".messageCategory").on("click", "a", function () {
        if ($(this).attr('class') == 'send') {
            divison = false;
            messageService.getListBySender({userNo: userNo}, function (result) {
                var str = "";
                for (var i in result.messageList.content) {
                    str += getSendLiStr(result.messageList.content[i]);
                }
                $(".messagepagination").html(getPage(result.pageResponseDTO));
                $(".messages").html(str);
                $(".division").html(getDivision(divison));
            });
        } else {
            divison = true;
            messageService.getListByReceiver({userNo: userNo}, function (result) {
                var str = "";
                for (var i in result.messageList.content) {
                    str += getReceiveLiStr(result.messageList.content[i]);
                }
                $(".messagepagination").html(getPage(result.pageResponseDTO));
                $(".messages").html(str);
                $(".division").html(getDivision(divison));
            });
        }
    })
    messageService.getAllUser({senderNo: userNo}, function (result) {
        var str = "";
        for (var i in result.memberList.content) {
            str += getMemberLiStr(result.memberList.content[i]);
        }
        $(".memberList").html(str);
        $(".memberpagination").html(getPage(result.pageResponseDTO));
    })

    function getMemberLiStr(obj) {
        return `<div class="row memberCheck">
                    <div class="col-2">
                        <input type="checkbox" class="mchk" id="memberInfo${obj.userNo}" value="${obj.userNo}">
                    </div>
                    <div class="col-3 text-center">
                        <label for="memberInfo${obj.userNo}">
                            ${obj.name}
                        </label>
                    </div>
                    <div class="col-4 text-center">
                        <label for="memberInfo${obj.userNo}">
                            ${obj.deptName}
                        </label>
                    </div>
                    <div class="col-3 text-center">
                        <label for="memberInfo${obj.userNo}">
                            ${obj.positionName}
                        </label>
                    </div>
                </div>
                `

    }

    $(".memberpagination").on("click", ".page-link", function () {
        var pageNum = $(this).data("pagenum");
        messageService.getAllUser({senderNo: userNo, pageNum: pageNum}, function (result) {
            var str = "";
            for (var i in result.memberList.content) {
                str += getMemberLiStr(result.memberList.content[i]);
            }
            $(".memberpagination").html(getPage(result.pageResponseDTO));
            $(".memberList").html(str);
        });
    })

    var arrUser = new Array();

    $(".selectConfirm").on("change", ".mchk", function (){
        if($(this).is(":checked")){
            arrUser.push($(this).val())
        }
        else if(!$(this).is(":checked")) {
            arrUser.push($(this).val())
        }
    })
});