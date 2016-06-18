# CQUTAPP_API

Android API Set of CQUT APP. Disassembled from Android Version.

## How to use

修改 cn/sunflyer/cqutapi/test/Test.java的第32行，getInstance的参数分别为学号和密码。

调用CService实例的对应功能（比如getScoreRecordAll()）来获取对应的信息。

如需要自行开发，请务必遵循以下调用流程：

CService s = CService.getService(User.getInstance(学号, 密码));

s.initFunctions();

## About

此项目API来源于重庆云华所开发的“知行理工”APP（Android客户端），我对此APP进行了反编译并从中提取了关键部分。

已打包的功能包括一卡通消费记录查询和历史成绩查询。

## Liscence

GNU v3

## Warnings

请不要滥用此接口。
