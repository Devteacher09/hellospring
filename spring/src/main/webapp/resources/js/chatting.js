/**
 * 채팅서버 기능
 */
//ws : http
//wss : https
const server=new WebSocket("ws://localhost:9090/spring/chatting");

server.onopen=(response)=>{
	const msg=new Message("open",loginId);
	/*console.log(msg);*/
	server.send(msg.convert());
}

server.onmessage=response=>{
	const receiveMsg=Message.deconvert(response.data);
	switch(receiveMsg.type){
		case "open" : alertMessage(receiveMsg);break;//새로운사람
		case "msg" : messagePrint(receiveMsg);break;//메세지왔을때
		case "attend" : addAttend(receiveMsg);break;
		case "close" : alertMessage(receiveMsg);break;
	}
}

const addAttend=(msg)=>{
	const clients=JSON.parse(msg.msg);
	const $attendContainer=document.querySelector("#attendContainer");
	$attendContainer.innerHTML="";
	const $ul=document.createElement("ul");
	$ul.classList.add("listcontainer");
	clients.map(e=>{
		const $li=document.createElement("li");
		$li.innerText=e;
		$li.classList.add("listfont");
		return $li;
	}).forEach(e=>{
		$ul.appendChild(e);
	});
	$attendContainer.appendChild($ul);
}
const messagePrint=(msg)=>{
	const $div=document.createElement("div");
	const $profile=document.createElement("img");
	$profile.setAttribute("src",`${path}/resources/images/pu.jpg`);
	$profile.style.width="50px";
	$profile.style.height="50px";
	$profile.style.borderRadius="100px";
	$div.appendChild($profile);
	//보낸사람 태그 생성
	const $sender=document.createElement("sup");
	$sender.innerText=msg.sender;
	//메세지출력 태그
	const $content=document.createElement("span");
	$content.innerText=msg.msg;
	
	$div.appendChild($sender);
	$div.appendChild($content);
	
	//메세지컨테이너 디자인하기
	$div.classList.add("msgcontainer");
	if(msg.sender==loginId){
		$div.classList.add("right");
	}else{
		$div.classList.add("left");
	}
	document.querySelector("#chattingcontent").appendChild($div);
}
const sendMessage=()=>{
	const inputData=document.querySelector("#msg").value;
	if(inputData.length>0){
		const msgObj=new Message("msg",loginId,"","",inputData).convert();
		server.send(msgObj);
	}else{
		alert("메세지를 입력하세요!");
		document.querySelector("#msg").focus();
	}
}
const alertMessage=(msg)=>{
	const $container=$("<div>").addClass("alertContainer");
	const status=msg.type=="open"?"접속":"퇴장";
	const $content=$("<h4>").text(`${msg.sender}님이 ${status}하셨습니다`);
	$container.append($content);
	$("#chattingcontent").append($container);
}


class Message{
	constructor(type="",sender="",receiver="",room="", msg=""){
		this.type=type;
		this.sender=sender;
		this.receiver=receiver;
		this.room=room;
		this.msg=msg;
	}
	convert(){
		return JSON.stringify(this);
	}
	static deconvert(data){
		return JSON.parse(data);
	}
}
