/**
 * 
 */

$(function(){
	$("#question").keyup(questionKeyuped);
});
function openChat(){
	setConnectStated(true);//챗창보이게처리
	connect();
	//mqConnect();
}

function showMessage(message) {
    $("#chat-content").append(message);
	//대화창 스크롤을 항상 최하위에 배치   
    $("#chat-content").scrollTop($("#chat-content").prop("scrollHeight"));
}

function setConnectStated(isTrue){
	if(isTrue){//true
		$("#btn-chat-open").hide();
		$("#chat-disp").show();
	}else{
		$("#btn-chat-open").show();
		$("#chat-disp").hide();
	}
	//챗봇창 화면 클리어
	$("#chat-content").html("");
}

function disconnect() {
    setConnectStated(false);
    console.log("Disconnected");
}
//버튼클릭시 접속
function connect() {
	var formData={message:"안녕", order:0};
	sendMessage(formData);
}

function sendMessage(formData){
	
	//주소에 대한 좌표확인
	
	if(formData.order==1){
		console.log("입력된 주소의 좌표값확인");
		//getPositionOfAddress(formData);
		return;
	}
	
	exec(formData);
	
}

function exec(formData){
	$.ajax({
		url:`/moviebot/${formData.order}`,
		type:"post",
		data:formData,
		success:function(responsedHtml){
			showMessage(responsedHtml);
			if(formData.order==1){
				getDailyBoxOffice(formData.repNationCd);
			} else if(formData.order==2){
				movieInfoNaverApi(formData.movieName);
			}			
			
		}
	});
}

//네이버 api를 이용하여 영화 상세 정보를 HTML 마지막 내 응답메시지 내부에 뿌려주기
function movieInfoNaverApi(movieName){
	$.ajax({
		url:"/moviebot/search/naverApi",
		data:{movieName:movieName},
		success:function(movieDetailHTML){
			var targets=$(".select-list");
			$(targets[targets.length-1]).html(movieDetailHTML);
		}
	});
}


function movieNameClicked(el){
	var movieName=$(el).text();
	
	//선택한 영화 제목이 채팅창에 출력
	//showMessage(inputTagString(movieName));
	
	var order=$(el).parents(".menu-item").find(".order").val();
	var repNationCd=$(el).parents(".menu-item").find(".repNationCd").val();
	var formData={
		message: movieName,
		order: order,
		repNationCd: repNationCd,
		movieName: movieName
	}
	
	exec(formData);
	
	showMessage(inputTagString(movieName));
}


function movieMenuClicked(el, repNationCd){
	console.log("repNationCd: " + repNationCd);
	var target=$(el).parents(".menu-item").find(".form");
	
	var formData={
		message:"",
		order:target.find(".order").val(),
		repNationCd: repNationCd
	};
	exec(formData);
	
	showMessage(inputTagString($(el).text()));
}

function getDailyBoxOffice(repNationCd){
	$.ajax({
		url:`/moviebot/dailyBoxOffice`,
		data:{repNationCd:repNationCd},
		success:function(dailyBoxOfficeHtml){
			var targets=$(".select-list");
			$(targets[targets.length-1]).html(dailyBoxOfficeHtml);
		}
		
	});
}


function inputTagWrontText(text){
	var now=new Date();
	var ampm=(now.getHours()>11)?"오후":"오전";
	var time= ampm + now.getHours()%12+":"+now.getMinutes();
	var message=`
		
		<div class="msg bot flex">
			<div  class="icon">
				<img src="/images/icon/robot-solid.svg">
			</div>
			<div class="message">
				<div class="part">
					<p>${text}</p>
				</div>
				<div class="time">${time}</div>
			</div>
		</div>
	`;
	return message;
}


function inputTagString(text){
	var now=new Date();
	var ampm=(now.getHours()>11)?"오후":"오전";
	var time= ampm + now.getHours()%12+":"+now.getMinutes();
	var message=`
		<div class="msg user flex end">
			<div class="message">
				<div class="part">
					<p>${text}</p>
				</div>
				<div class="time">${time}</div>
			</div>
		</div>
	`;
	return message;
}
//메뉴클릭시 메뉴 텍스트 화면에 표현 
function menuclicked(el){
	var text=$(el).text().trim();
	var fToken=$(el).siblings(".f-token").val();
	console.log("-----> fToken:"+fToken+"----");
	var message=inputTagString(text);
	showMessage(message);
}

//엔터가 입력이되면 질문을 텍스트 화면에 표현 
function questionKeyuped(event){
	if(event.keyCode!=13)return;
	btnMsgSendClicked()
}

//전송버튼 클릭이되면 질문을 텍스트 화면에 표현
function btnMsgSendClicked(){
	var question=$("#question").val().trim();//질문에대한답
	var forms=$(".form");
	var form=$(forms[forms.length-1]);
	var formData={
		message:question,
		order:form.find(".order").val(),
		//areaInfo:form.find(".areaInfo").val(),
		//nx:form.find(".nx").val(),
		//ny:form.find(".ny").val(),
		
	};
	
	
	if(question=="" || question.length<2)return;
	//메세지 서버로 전달
	console.log(formData);
	//var order=$(forms[forms.length-1]).find(".order");
	sendMessage(formData);
	 
	var message=inputTagString(question);
	showMessage(message);//사용자가 입력한 메세지 채팅창에 출력
	$("#question").val("");//질문 input 리셋
}