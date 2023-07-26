<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.animal-info {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
}

.image-wrapper {
	position: relative;
	overflow: hidden;
	width: 300px;
	height: 300px;
	border-radius: 50%;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
}

img {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 100%;
	height: auto;
}

.comment-wrapper {
	margin-top: 20px;
	width: 100%;
}

textarea {
	width: 100%;
	height: 60px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 10px;
	resize: none;
}

button {
	margin-top: 10px;
	padding: 10px;
	background-color: #4CAF50;
	color: #fff;
	border: none;
	border-radius: 10px;
	cursor: pointer;
}

.comment-list {
	margin-top: 20px;
	width: 100%;
}

h3 {
	margin-bottom: 10px;
	font-size: 16px;
}

ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

li {
	padding: 10px;
	background-color: #f5f5f5;
	border: 1px solid #ccc;
	border-radius: 10px;
	margin-bottom: 10px;
}
</style>
<body>
	<div class="animal-info">
		<div class="image-wrapper">
			<img src="${item.popfile }" alt="${item.kindCd}" />
		</div>
		<h2>${item.kindCd }- (${item.colorCd })</h2>
		<p>발견장소 : ${item.orgNm } ${item.happenPlace}</p>
		<p>특징 : ${item.specialMark }</p>
		<div id="map"
			style="width: 100%; height: 300px; margin: auto; display: flex; justify-content: center; align-items: center; border: 1px solid #dddddd">

			<c:choose>
				<c:when test="${empty address }">
						지도정보를 확보 하지 못해 렌더링에 실패하였습니다.
					</c:when>
				<c:otherwise>
						지도를 불러옵니다.
					</c:otherwise>
			</c:choose>
		</div>



		<div class="comment-wrapper">
			<form action="/write" method="post">
				<input type="hidden" name="target" value="${item.desertionNo }">
				<textarea name="body" placeholder="댓글을 입력해주세요"></textarea>
				<textarea name="pass" placeholder="****"
					style="width: 70px; height: 20px;" maxlength="4"></textarea>
				<div>
					<button>댓글 작성</button>
				</div>
			</form>
		</div>
		<div class="comment-list">
			<h4>
					응원의 한마디 (<span id="cnt">${fn:length(messages) }</span>   건) <span id="refresh"
						style="cursor: pointer;">5</span>초 후 갱신
				</h4>
			<div id="messages">
				<ul>
					<c:forEach items="${messages }" var="m">
						<div>${m.body }</div>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<script>
		const getMessages = function() {
			const xhr = new XMLHttpRequest();
			xhr.open("get", "/api/message?no=${param.no}", true);
			xhr.send();
			xhr.onreadystatechange=function(){
				if(this.readyState===4) {
					const json = JSON.parse(this.responseText);	// 아마 객체 배열일 듯
					if(json.result){
						
					const messages = document.querySelector("#messages");
					messages.innerHTML = "";
					console.log(json.result);
					for(let o of json.items) {
						console.log(o);
						messages.innerHTML += "<div class='messages'>"+o.body+"</div>";
						}
						document.querySelector("#cnt").innerHTML = json.total;
					} 
					
				}
			}
		};
		
		
		setInterval(function(){
			let value = parseInt(document.querySelector("#refresh").innerHTML);
			value--;
			if(value == -1) {
				getMessages();
				value = 5;
			}
			document.querySelector("#refresh").innerHTML = value;
		}, 1000);
		
	</script>
	<c:if test="${!empty address }">
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fdd7a2dbaa2570a180f3c39b9a412437"></script>
		<script>
		let pos = new kakao.maps.LatLng(${address.lng}, ${address.lat}); //지도의 중심좌표.
		
		let container = document.querySelector('#map'); //지도를 담을 영역의 DOM 레퍼런스
		let options = { //지도를 생성할 때 필요한 기본 옵션
			center : pos, 
			level : 4
		//지도의 레벨(확대, 축소 정도)
		};

		let map = new kakao.maps.Map(document.querySelector('#map'), options); //지도 생성 및 객체 리턴
		
		let marker = new kakao.maps.Marker({
		    position: pos
		});
		
		marker.setMap(map);
		
		
	</script>
	</c:if>
</body>
</html>