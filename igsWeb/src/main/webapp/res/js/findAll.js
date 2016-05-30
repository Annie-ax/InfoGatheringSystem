/**
 * Created by keaxiang on 2016/1/13.
 */

//页面加载时隐藏控件
$(document).ready(function(){
    document.getElementById("queryValue").style.visibility = "hidden";
    document.getElementById("date").style.visibility = "hidden";
    document.getElementById("dateLabel").style.visibility = "hidden";
    $('#queryCondition').css("width",200);
    $('#queryCondition').css("height",30);
    $('#queryValue').css("width",200);
    $('#queryValue').css("height",30);
    $('#date').css("width",200);
    $('#date').css("height",30);
    $('#pageShow').style.alignContent = "center";
});


function showChange(){
    var select = document.getElementById("queryCondition");
    var value = select.options[select.selectedIndex].value;
    if(value == 1){
        document.getElementById("queryValue").style.visibility = "hidden";
        document.getElementById("date").style.visibility = "visible";
        document.getElementById("dateLabel").style.visibility = "visible";
    }
    if(value == 2){
        document.getElementById("queryValue").style.visibility = "visible";
        document.getElementById("date").style.visibility = "hidden";
        document.getElementById("dateLabel").style.visibility = "hidden";
    }
    if(value == 0){
        document.getElementById("queryValue").style.visibility = "hidden";
        document.getElementById("date").style.visibility = "hidden";
        document.getElementById("dateLabel").style.visibility = "hidden";
    }
}


