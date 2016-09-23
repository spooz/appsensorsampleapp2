function showTopicModal() {
    $("#topicModal").modal();
}


function submitTopicModal() {
    var form = $("#topic_form");

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var data = $("#topic_form").serializeObject();

    $.ajax({
        url: "/json/topic/add",
        type: "POST",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        data : JSON.stringify(data),
        success: function() {
            swal({
                title: "Topic added",
                confirmButtonColor: "#2196F3",
                type: "success"
            });

            reloadTopicTable();
            $("#topicModal").modal("hide");

        },
        error: function (xhr, ajaxOptions, thrownError) {

            swal({
                title: "error",
                confirmButtonColor: "#2196F3",
                type: "error"
            });


        }

    });
}

$.fn.serializeObject = function() {
    var arrayData, objectData;
    arrayData = this.serializeArray();
    objectData = {};

    $.each(arrayData, function() {
        var value;

        if (this.value != null) {
            value = this.value;
        } else {
            value = '';
        }

        if (objectData[this.name] != null) {
            if (!objectData[this.name].push) {
                objectData[this.name] = [objectData[this.name]];
            }

            objectData[this.name].push(value);
        } else {
            objectData[this.name] = value;
        }
    });

    return objectData;
};

function reloadTopicTable() {
    var table = $("#topics-datatable").DataTable();
    table.ajax.reload();
}


function initTopicTable() {
    $("#topics-datatable").DataTable({
        "ajax":"/json/topic/all",
        "columns" : [
            {"data":"title",
                "fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a href='/topic/" + oData.id +"/messages'>" + oData.title + "</a>");
                }
            },
            {"data" : "description"},
            {"data" : "author"}
        ]
    });
}

$(document).ready(function(){
    initTopicTable();
});