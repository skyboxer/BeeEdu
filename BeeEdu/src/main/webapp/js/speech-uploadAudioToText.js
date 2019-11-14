$(function() {
    $.getJSON("../datas/machine.json", function (obj) {
        var options = ""
        $.each(obj, function (i, d) {
            options += '<option value="' + i + '">' + d + '</option>'
        })
        $("#languageType1").html(options)
        $("#languageType0").html(options)

    })

    $("#translateAudio").hide()
    var fileNameText;
    $("#submit").click(function () {
        startTime()
        $("#translateAudio").hide()
        var formData = new FormData($("#form")[0])
        $.ajax({
            url:'/fileUpload',
            type:'post',
            cache: false,
            data: formData,
            processData : false, // 使数据不做
            contentType: false,
            success: function(data){
                var jsonData = JSON.parse(data);
                if(jsonData.status="0"){
                    document.getElementById("uploadSpeech").style.width="100%"
                    var fileName = (jsonData.fileName).toString();

                    $.post("/speechToText",{"uploadFileName":fileName},function (data) {
                        fileNameText =data;
                    })
                }else{
                    alert(jsonData.message)
                }

                clearInterval(thisTime);

            },
            error:function(response){
                console.log(response);
                clearInterval(thisTime);
            }
        });
    })
    var thisTime=null;
    var startTime = function () {
        var timeNum = 0;
        thisTime = setInterval(function(){
            document.getElementById("uploadSpeech").style.width=timeNum+"%"
            timeNum ++
            },1000);
        // 停止时间加 clearInterval(startTime);
    }

    $("#copyText").click(function () {
        var url = "../upload/"+fileNameText;
        $.get(url,function (data) {
            if(data.message === "data is not defined"){
                alert("请稍等.....正在翻译.")
            }
            $("#out_data").text(data)
            $("#translateAudio").show()
        })

    })
    $("#translateAudio").click(function () {
        var text = $("#out_data").val();
        var from = $("#languageType0 option:selected").val();
        var to = $("#languageType1 option:selected").val();
        $.post("/textTranslation", {
            TEXT : text,
            FROM : from,
            TO : to
        }, function(data) {
            var dataJson = JSON.parse(data)
            $("#out_data").text(dataJson.dst);
        })
    })

})