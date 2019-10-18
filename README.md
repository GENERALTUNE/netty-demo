## 用maven创建java工程，并运行
### 1.用maven建立一个工程 ： mvn archetype:generate



1. 创建步骤：
第一步：在某盘中建一个文件夹 ，例如：我在c盘创建了一个hello文件夹(C:\hello)
第二步：打开cmd，并切换到你刚建立的文件中
            执行命令语句：cd C:\hello ，然后按回车键
第三步：在cmd中执行创建java过程
            执行命令语句：
```java
/*
 -DgroupId=com.gerante.nettydemo创建包名
 -DartifactId=netty-demo创建工程名
     注意这里的com.gerante.nettydemo和netty-demo是任意命名，但要规范
参数说明：
　　1.1、-DgroupId：包名，如：com.google
　　1.2、-DartifactId: 项目名称，如：NumberGenerator
　　1.3、-DarchetypeArtifactId: 项目的类型，Maven提供了很多模板，如果你没写这个，创建的时候就会列出一大堆模板让你选择 1.4、-DinteractiveMode: 是否使用交互模式，如果是true，那么在创建过程中就要手动输入一些参数
 */
 mvn archetype:generate -DgroupId=com.gerante.nettydemo -DartifactId=netty-demo -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```
第四步：创建成功后，cd到工程名中 ，再回车
第五步：编译工程
    执行命令语句：mvn compile ，再回车
第六步：测试工程，使用命令mvn test ，再按回车
第七步：测试和编译工程，使用命令mvn test-compile ，再按回车
第八步：打包工程，使用命令mvn package，将工程打包成jar\war\ear等包，再按回车
第九步：清理工程，使用命令mvn clean，将清理工程的target目录
第十步：安装工程，使用命令mvn install，将工程安装到~\.m2\repository目录下，~代表当前用户根     
创建步骤完成

2. 运行步骤：
       第一步：在cmd中，先cd到C:\hello的myapp文件中
              执行命令语句：cd C:\hello\myapp，回车
       第二步：查看myapp文件下的文件
              执行命令语句：dir，回车
       第三步：切换到myapp文件下的target文件下
             执行命令语句：cd target，回车
       第四步：查看target文件下的文件
                执行命令语句：dir ，回车
         第五步：运行
              执行命令语句：java -classpath myapp-1.0-SNAPSHOT.jar com.demo.App，回车
                      伪代码：java -classpath .jar文件 包名.程序名
3. 要把这个项目转成IDEA项目
此处的JDK版本一定要写，我没写时运行就出错了（可能因为网络问题，一些文件没下载到，搭建私服后就不会了）。JDK版本可通过运行 java -version 获得。mvn idea:idea 命令详解：http://maven.apache.org/plugins/maven-idea-plugin/usage.html。
```shell
 mvn idea:idea -DjdkName=1.8.0_221
```

### 编译和运行 Echo 服务器和客户端
#### 编译
本例涉及到多模块 Maven 项目的组织
在例子 chapter2 目录下，执行
```shell
mvn clean package
```
注意事项：

Maven Reactor 构建顺序：先是 父 POM，然后是子项目
Netty artifact 没在用户的本地存储库中找到，所以 Maven 就会从互联网上下载
clean 和 compile 在构建生命周期的运行。事后 mavensurefire-plugin 插件运行，但不会有测试类存在。最后 mavenjar-plugin 执行

### 运行 Echo 服务器 和 客户端
我们使用 exec-maven-plugin 来运行项目。

在 chapter2/Server 目录，执行
```shell
mvn exec:java
```
发生了什么事：
客户端连接后，它发送消息：“Netty rocks！”
服务器输出接收到消息并将其返回给客户端
客户输出接收到的消息并退出。
每次运行客户端，你会看到在服务器的控制台输出：



[《Essential Netty In Action》](https://waylau.gitbooks.io/essential-netty-in-action/content/GETTING%20STARTED/Asynchronous%20and%20Event%20Driven.html)