执行命令：
java -jar -Dspring.config.location=
当前jar文件路径.jar
java -jar -Dspring.config.location=config路径
-Dlogging.path= 日志路径
jar文件路径.jar
例如:
java -jar -Dspring.config.location=D:\eclipse-workspace\yjgb_sb\target\yjgb-sb-0.0.1-SNAPSHOT\application.properties -Dlogging.path=D:\eclipse-workspace\yjgb_sb\target\yjgb-sb-0.0.1-SNAPSHOT\log D:\eclipse-workspace\yjgb_sb\target\yjgb-sb-0.0.1-SNAPSHOT\yjgb-sb-0.0.1-SNAPSHOT.jar