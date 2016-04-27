/**
 * Created by ff on 16-4-16.
 */
function flash_title() {
    //当窗口最小化，或者美柚焦点的状态下才闪动
    if (isMinstatus() || !Window.focus) {
        newMsgCount();
    } else {
        document.title = "talk";
        window.clearInterval();
    }
}

//消息提示
var flag=false;
function newMsgCount(){
    if(flag){
        flag=false;
        document.title='【新消息】';
    }else{
        flag=true;
        document.title='【　　　】';
    }
    window.setTimeout('flash_title(0)',380);

}
//判断窗口是否最小化
function isMinstatus(){
    var isMin=false;
    //除了Internet Explorer浏览器，其他主流浏览器均支持Window outerHeight 和outerWidth 属性
    if(window.outerWidth != undefined && window.outerHeight != undefined){
        isMin = window.outerWidth <= 160 && window.outerHeight <= 27;
    }else{
        isMin = window.outerWidth <= 160 && window.outerHeight <= 27;
    }
    //除了Internet Explorer浏览器，其他主流浏览器均支持Window screenY 和screenX 属性
    if(window.screenY != undefined && window.screenX != undefined ){
        isMin = window.screenY < -30000 && window.screenX < -30000;//FF Chrome
    }else{
        isMin = window.screenTop < -30000 && window.screenLeft < -30000;//IE
    }
    return isMin;
}
