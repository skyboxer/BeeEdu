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
        $("#translateAudio").hide()
        var formData = new FormData($("#form")[0])
        document.getElementById("uploadSpeech").style.width="50%"
        $.ajax({
            url:'/fileUpload',
            type:'post',
            cache: false,
            data: formData,
            processData : false, // 使数据不做
            contentType: false,
            success: function(data){
                var jsonData = JSON.parse(data);
                if(jsonData.status==0){
                    var fileName = (jsonData.fileName).toString();
                    if(fileName.search(".raw")!=-1 ||fileName.search(".pcm")!=-1||fileName.search(".speex")!=-1){
                        document.getElementById("getSpeechToTextFile").style.width="0%"
                        document.getElementById("uploadSpeech").style.width="100%"
                        $.post("/speechToTextController/speechToText",{"uploadFileName":fileName},function (e) {
                            if(e.code !=0){
                                alert("识别出错！")
                            }
                            fileNameText =e.data;
                            document.getElementById("getSpeechToTextFile").style.width="100%"
                            document.getElementById("uploadSpeech").style.width="0%"
                        })
                    }else {
                        alert("上传音频格式不正确")
                    }
                }else{
                    alert("上传出现bug")
                }

            },
            error:function(response){
                document.getElementById("getSpeechToTextFile").style.width="0%"
                document.getElementById("uploadSpeech").style.width="0%"
                console.log(response);
            }
        });
    })

    //刷新结果
    $("#copyText").click(function () {
        if(fileNameText!=undefined){
            var url = "../upload/"+fileNameText;
            $.ajax({
                type: "get",
                url: url,
                dataType: "text",
                async: true
            }).done(function (e) {
                $("#out_data").text(e)
                $("#translateAudio").show()
            });
        }else{
            alert("请稍等.....正在翻译.")
        }
    })
    $("#translateAudio").click(function () {
        var text = $("#out_data").val();
        var from = $("#languageType0 option:selected").val();
        var to = $("#languageType1 option:selected").val();
        if (from == to) {
            alert("请选择目标语言")
            return;
        }
        $.post("/machineTranslation/textToTranslation", {
            "text" : text,
            "from" : from,
            "to" : to
        }, function(e) {
            var data= e.data;
            if(e.code== 0){
                alert("翻译有问题！");
                return;
            }
            var dataJson = JSON.parse(data)
            $("#out_data").text(dataJson.dst+'\n'+dataJson.src);
        })
    })

})