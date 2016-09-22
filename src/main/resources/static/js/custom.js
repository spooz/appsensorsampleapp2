function showTopicModal() {
    $("#topicModal").modal();
}


function submitTopicModal() {
    var form = $("#topic_form");

    alert(form.serializeArray());

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var data = $("#topic_form").serializeObject();

    $.ajax({
        url: "/topic/add",
        type: "POST",
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        data : JSON.stringify(data),
        success: function() {

           /* swal({
                title: i18n.t("asset_list_monitoring_success"),
                confirmButtonColor: "#2196F3",
                type: "success"
            }, function() {
                $("#modal_add_to_monitoring").modal("hide");
                reloadTable();
            });*/

           alert("OK");
        },
        error: function (xhr, ajaxOptions, thrownError) {
          /*  var message = xhr.responseText === "" ? i18n.t("asset_list_monitoring_error") : xhr.responseText
            swal({
                title: message,
                confirmButtonColor: "#2196F3",
                type: "error"
            });*/

          alert("ERROR");
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

function initTopicTable() {
    $("#topics-datatable").DataTable({
        "ajax":"/topic/all",
    });
}

$(document).ready(function(){
    initTopicTable();
});