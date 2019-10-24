$(function() {
    $.getJSON("../datas/voice.json", function (obj) {
        var options = ""
        $.each(obj, function (i, d) {
            options += '<option value="' + i + '">' + d + '</option>'
        })
        $("#languageType1").html(options)
    })

    $("#translateAudio").hide()

    $("#submit").click(function () {
        startTime()
        //var fileName = $('#exampleInputFile').files[0].name
        var formData = new FormData($("#form")[0])
       // formData.append("fileName",fileName);
        $.ajax({
            url:'/fileUpload',
            type:'post',
            cache: false,
            data: formData,
            processData : false, // 使数据不做
            contentType: false,
            success: function(data){
                var JsonData = JSON.parse(data)
                if (JsonData.status == '0') {
                    $("#translateAudio").show()
                    clearInterval(thisTime);
                    document.getElementById("uploadSpeech").style.width="100%"
                }else{
                    clearInterval(thisTime);
                }

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
   $("#translateAudio").click(function() {
       var fileName=document.getElementById("exampleInputFile").files[0].name;
        $.post("/speechToText",{"uploadFileName":fileName},function (data) {
            var jsonData = JSON.parse(data)

        })
    })

})