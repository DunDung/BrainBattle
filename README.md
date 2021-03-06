﻿# **Brain Battle**
더 똑똑하고 순발력 좋은 사람이 승리하는 1 대 1 퀴즈 게임 

 ## 사용 기술
 Java 8, Socket, Swing, Thread

 ## 목차
* 개요
* 시연영상
* 실행화면
* 게임진행
* 제한시간
* 힌트
* BGM, 효과음
* 채팅
* 게임종료
* 제작

### 개요
퀴즈를 먼저 맞춘 플레이어가 1점을 획득한다. 랜덤으로 지정된 한명의 사용자가 설정한  
목표점수에 먼저 도달하는 플레이어가 승리하는 퀴즈게임
### 시연영상
[YouTube Link](https://youtu.be/LsK2tTIxsrU)  
### 실행화면
![intro](src/main/resources/static/images/example/intro.png)
![wait-screen](src/main/resources/static/images/example/wait-screen.png)
![target-score-setting](src/main/resources/static/images/example/target-score-setting.png)
![game-example](src/main/resources/static/images/example/game-example.png)
![hint-example](src/main/resources/static/images/example/hint-example.png)
### 게임진행
클라이언트 사용자는 서버PC의 IP와 닉네임을 입력 한 후 게임에 접속한다.  
서버 사용자는 닉네임을 입력한 후 게임에 접속한다.  
준비가 완료된 사용자는 Ready버튼을 클릭한다. 두 명의 사용자가 전부 준비완료 됐을 시에 게임을 시작한다.  
랜덤으로 지정된 한 명의 사용자가 목표점수를 설정한다. 설정이 완료되면 퀴즈가 시작된다. 
### 제한시간
한 문제당 제한시간은 60초로 오른쪽 상단에 몇초가 남았는지 보여준다.
### 힌트
제한시간의 절반인 30초가 지났을 시 약간의 힌트가 문제에 표시된다.
### BGM, 효과음
게임시작 전 인트로 음악과 게임진행 중 음악이 나온다. 게임 중간중간 효과음을 삽입하였다.  
효과음을 삽입한 때는 다음과 같다.  
1 내가 퀴즈를 맞췄을 때  
2 상대방이 퀴즈를 맞췄을 때  
3 내가 낸 답이 틀렸을 때  
4 제한시간이 10초 이하로 남았을 때  
5 내가 게임에서 승리 했을 때  
6 상대방이 게임에서  승리 했을 때  
### 채팅
우측에 채팅창으로 플레이어간 채팅을 주고받을 수 있다.
### 게임종료
한 플레이어가 목표점수를 달성하면 게임이 종료된다.  
게임종료 시 내가 이겼는지 졌는지 표시해주며 다시 게임을 하겠냐고 묻는다.  
상대방과 내가 둘 다 Yes 버튼 클릭 시엔 게임이 다시 시작되며 No버튼을 클릭시엔 프로그램이 종료된다. 

### 제작
홍준성
