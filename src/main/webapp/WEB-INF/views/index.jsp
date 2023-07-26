<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유기동물 정보 조회</title>
<style>
* {
	box-sizing: border-box;
}
input, select, button {
	padding : 4px 8px;
	height: 40px;
}
</style>
</head>
<body style="margin: 0">
	<div style="text-align: center">
		<h1>
			유기동물 정보 조회 <small>(OPEN API 활용)</small>
		</h1>
		<div>
			<form action="/index" method="get" style="display: flex; align-items: center; 
				justify-content: center; gap : 10px">
				<input type="date" name="bgnde" value="${param.bgnde }" /> ~ <input
					type="date" name="endde" value="${param.endde }" /> <select
					name="upr_cd">
					<option value="" ${param.upr_cd eq '' ? 'selected' : '' }>전국</option>
					<c:forEach items="${sidos }" var="obj">
						<option value="${obj.orgCd }"
							${param.upr_cd eq obj.orgCd ? 'selected' : '' }>${obj.orgdownNm }</option>
					</c:forEach>
				</select> <select name="upkind">
					<option value="" ${param.upkind eq '' ? 'selected' : '' }>전체</option>
					<option value="417000"
						${param.upkind eq '417000' ? 'selected' : '' }>개</option>
					<option value="422400"
						${param.upkind eq '422400' ? 'selected' : '' }>고양이</option>
					<option value="429900"
						${param.upkind eq '429900' ? 'selected' : '' }>기타</option>
				</select>
				<button type="submit">조회</button>
			</form>
			<div style="text-align: right">
				<c:forEach begin="1" end="${10 }" var="p">
					<a href="/index?pageNo=${p }&bgnde=${param.bgnde }&endde=${param.endde }&upr_cd=${param.upr_cd }&upkind=${param.upkind}">${p }</a>
				</c:forEach>
			</div>
		</div>
		<div>총 ${total } 건의 유기동물정보가 존재합니다.</div>
		<div style="display: flex; flex-wrap: wrap;">
			<c:forEach items="${datas }" var="obj">
				<div style="width: 33.3%; padding: 10px; height: 280px; cursor: pointer;" 
					onclick="location.href='/detail?no=${obj.desertionNo}'">
					<div
						style="width: 100%; border: 1px solid #dddddd; padding: 4px; height: 100%;">
						<div style="height: 10%">
							<b>${obj.kindCd }</b>
						</div>
						<div style="height: 50%">
							<img src="${obj.filename }" style="height: 100%;" />
						</div>
						<div style="height: 10%">
							<b>${obj.happenDt }</b>
						</div>
						<div style="height: 10%; text-overflow: ellipsis; white-space: nowrap; overflow: hidden">
							<span>${obj.orgNm } ${obj.happenPlace }</span>
						</div>
						<div
							style="height: 20%; text-overflow: ellipsis; white-space: nowrap; overflow: hidden">
							${obj.specialMark }</div>
					</div>
				</div>
			</c:forEach>
		</div>


	</div>
</body>
</html>



