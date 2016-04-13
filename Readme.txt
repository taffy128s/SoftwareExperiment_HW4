[Game]
MainActivity->
	放音樂，打開window
MyWindow->
	打開typing thread跟gaming thread
Typing->
	先把known跟unknown的詞存起來
	再用hashmap把known的value存起來
	利用random來隨機選字
	已經輸入過正確的字會記錄下來
	
	每次ENTER都會檢查input跟value有沒有符合
	符合就會亮綠色並且隨機選擇下一個不重複的字
	不符合是紅色而且使用者必須修正到對才可以打下一個字
	打字會恢復成白色
	
	如果滿分了就會停止動作，且output至output.txt
GameStage->
	一開始會把所有圖片的檔案跟位置都處理好
	然後利用run來讓鴨子、球會上下移動
	再根據分數的變化讓background或是鴨子移動
	(nowScore < 50動background，nowScore <= 100動鴨子)
	直到鴨子碰到球就會顯示贏的訊息，就可以按叉叉結束遊戲了
[Reassembler]
	開一個二維陣列，把output.txt全部讀進來
	再跑一個雙層迴圈把東西全部印出來
	
[遇到的困難]
	一開始在想GameStage跟Typing要放哪裡花了很多時間
	後來就想到可以都放在MyWindow裡面，然後把gs傳進typing
	就可以利用gs的public method來加分數
	
	接下來在構思要如何排版也是花了很多時間
	後來才發現...要setLayout(null)才可以手動排版
	然後排好版之後，就開始實作各式method
	比較不熟悉的地方就去google，大致上沒有什麼問題
	只是最後Reassembler在處理01-01.png這個string出現麻煩
	但是後來發現可以用
	scanner.next().replaceAll("[^0-9]", " ").split(" ");
	把他變成01 01就可以把idx給取出來啦!開心

# 2016/4/11 新增正確、錯誤音效