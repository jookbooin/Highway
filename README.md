# 고속도로 경로 찾기

## 설명
api를 사용하지않고 서울,대구,광주,대전,울산,부산 에서 갈 수 있는 고속도로 경로를 직접 조사하여 파일로 만들었다

 공통기능의 재사용성 + 싱글톤 패턴 , 파사드 , 옵저버 패턴 맛보기
              
## 실행화면
### 1. 지역 선택 </br>
![image](https://user-images.githubusercontent.com/94632156/208581902-d972c81b-79ba-46ca-85eb-dfbda538670f.png)

출발 지점  도착 지점 선택후 경로 검색 가능 ( 같을시 검색 불가능 )

![image](https://user-images.githubusercontent.com/94632156/208582734-14386c1f-dc9b-4631-bde4-471b62e19043.png)
</br>

### 2. 경로내 휴게소 확인 </br>
![image](https://user-images.githubusercontent.com/94632156/208582419-76ad82b7-5d96-4437-b23f-fed51b3d7d4a.png)

 경로별 출발지점 선택 

 ![image](https://user-images.githubusercontent.com/94632156/208583697-ac3969e2-a550-4f7a-ac2c-41b60d85f782.png)

 ![image](https://user-images.githubusercontent.com/94632156/208583713-c51549a9-d749-40e5-97a3-d526f2fa6ac7.png)

 ![image](https://user-images.githubusercontent.com/94632156/208583724-82eb873c-1a46-4382-aaf6-ea76777d0c99.png)

### 3. 주유소 최저값 선택 </br>
출발지점 선택시 경로내 주유가격 최저값인 휴게소 선별

![image](https://user-images.githubusercontent.com/94632156/208584129-3e80364d-df64-4786-a6b7-bf0ac65e10b2.png)


휘발유 

![image](https://user-images.githubusercontent.com/94632156/208583984-a4c5f7a6-3a88-40fd-b914-2d2a3895948d.png)

 경유
  
![image](https://user-images.githubusercontent.com/94632156/208584003-9669dfbe-69cb-48db-80d0-52015825a67a.png)

lpg
  
![image](https://user-images.githubusercontent.com/94632156/208584016-7a2623ba-9267-496a-a5da-60d5a505a36b.png)


## 구성

### 1. 구조도 

![image](https://user-images.githubusercontent.com/94632156/208584702-78943b4f-5fd2-453f-baeb-f08f80aa7901.png)

### 2. 출력 형태 

![image](https://user-images.githubusercontent.com/94632156/208584766-ab1786ad-ba18-4104-8379-d3f755b18ddf.png)

### 3. 디렉토리 구성
 
  * mgr - Manager : 공통기능 
  * Map - Highway , Direction , Path , RestArea 입출력 
  * Gui - 프레임들 + 커스텀 + 패널클래스 

  이미지 파일
  * guiImg
  * roadImg : 경로 사진
 
  텍스트 파일

  서울 : 0 , 대구: 1 , 대전 : 2 , 울산 : 3 , 광주 : 4 , 부산 : 5 로 표현 
  * restarea.txt : 휴게소 정보
  * direction.txt : 출발지점 , 도착지점
  * path.txt : 경로별 있는 휴게소들 





