/**
 * Created by ff on 16-4-1.
 */

function talk(friendid,friendname){
    console.log("userid"+userid);
    //更新聊天截面标题
    document.getElementById("c2headertitle").innerHTML = friendname;
    document.getElementById("c2talkfriend").innerHTML = friendid;

    //在c2body中添加聊天内容,基于websocket活取聊天内容
}

//用户头像,用户名，用户id
var userid = document.getElementById("item2");


//websocket发送接受消息
    var websocket = null;
    var address=window.location.href;
    var sll = address.split("/");
    var ip = sll[2];
    console.log("服务器ｉｐ"+ip);
    //判断当前浏览器是否支持WebSocket
    if('WebSocket' in window){

        //这边的websocket是注释的/websocket,之前的应该是工程目录，因为我用的是idea启动有点特别
        websocket = new WebSocket("ws://"+ ip + "/getmessage");
    }
    else{
        alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function(){
        setMessageInnerHTML("error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function(event){
        setMessageInnerHTML("open");
        console.log("open");
    }

    //接收到消息的回调方法
    websocket.onmessage = function(){
        setMessageInnerHTML(event.data);

        //如果不在当前页面则发出提示
        flash_title();

    }

    //连接关闭的回调方法
    websocket.onclose = function(){
        setMessageInnerHTML("close");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(responsemessage){
        var friendname = document.getElementById("c2headertitle").innerHTML;
        document.getElementById('message').innerHTML +=
            '<div class="contentleft">'+
            '<div class="chat_message1" >'
            +friendname+":"+responsemessage+'</div>'
            +'</div>';

        var ms = document.getElementById("message");
        //设置滚动条始终处于底部
        ms.scrollTop = ms.scrollHeight;
    }

    //关闭连接
    function closeWebSocket(){
        websocket.close();
    }

    //发送消息
    function send(userid){
        //组装消息
        var message = document.getElementById("c2input").value;
        var friendid = document.getElementById("c2talkfriend").innerHTML;
        var sendstr = userid+";"+friendid+";"+message;
        console.log("message"+sendstr);
        websocket.send(sendstr);
        //发送完成之后的显示
        document.getElementById("c2input").value="";

        //var info = document.createElement("div");
        //info.innerText=message;
        //var contenet = document.getElementById("chat-contentall");
        //contenet.appendChild(info);
        var ms = document.getElementById("message");
        document.getElementById("message").innerHTML +=
            '<div class="contentright">'+
            '<div class="chat_message2" >'
            +userid+":"+message+'</div>'
            +'</div>';
        //设置滚动条始终处于底部
        ms.scrollTop = ms.scrollHeight;

    }