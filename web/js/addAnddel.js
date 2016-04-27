/**
 * Created by ff on 16-3-31.
 */
//添加一个好友

function adduser(){
    var userid = prompt("请输入账号","");
    if(userid){//如果返回的有内容
        //ajax添加好友，并获取返回结果
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest();
        }else {
            xmlhttp = ActiveXObject("microsoft.XMLHTTP");
        }
        console.log(userid);
        var url="Addfriend";
        xmlhttp.open("POST",url,true);
        xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlhttp.setRequestHeader("Content-length",userid.length);
        xmlhttp.send("userid="+userid);


        //回调,获取返回值
        xmlhttp.onreadystatechange=function(){
            if(xmlhttp.readyState==4 && xmlhttp.status == 200){
                //得到返回值，弹出提示
                var result = xmlhttp.responseText;
                if(result=="success"){
                    alert("添加成功");
                }else {
                    alert("添加失败");
                }
            }
        }
    }
}
//从好友列表中删除某个好友
function deluser(){
    var userid = prompt("请输入账号","");
    if(userid){//如果返回的有内容
        //ajax添加好友，并获取返回结果
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest();
        }else {
            xmlhttp = ActiveXObject("microsoft.XMLHTTP");
        }
        console.log(userid);
        var url="Addfriend";
        xmlhttp.open("POST",url,true);
        xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlhttp.setRequestHeader("Content-length",userid.length);
        xmlhttp.send("userid="+userid);

        //直接发送删除请求，无返回值．
    }
}

