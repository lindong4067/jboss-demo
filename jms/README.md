Using JMS in Wildfly

* Step 1 在JBoss服务器创建消息队列，以下4种方式都行：
    
    1）使用Management Console创建消息队列
        
        a. ./standalone.sh -c standalone-full.xml启动
        
        b. 登录http://localhost:9990/console，选择Configuration → Messaging → Destinations → default → View，接下来点击Add按钮，创建消息队列“java:/jms/queue/TestQueue1”
         
    2) 使用Management CLI创建消息队列
        
        a ./jboss-cli.sh -> 连接到JBoss Management CLI 
        
        b cd /subsystem=messaging/hornetq-server=default -> 进入到messaging subsystem
        
        c ./jms-queue=TestQueue2:add(durable=false,entries=["java:/jms/queue/TestQueue2"])
        
    3) 使用部署*-jms.xml 文件到 deployments目录的方式创建消息队列
        
        a  创建XML文件，任意命名，比如我们创建sample-jms.xml
        
            ```
                <?xml version="1.0" encoding="UTF-8"?>
                <messaging-deployment xmlns="urn:jboss:messaging-deployment:1.0">
                    <hornetq-server>
                        <jms-destinations>
                            <jms-queue name="TestQueue3">
                                <entry name="java:/jms/queue/TestQueue3"/>
                            </jms-queue>
                        </jms-destinations>
                    </hornetq-server>
                </messaging-deployment>
            ```
         b 部署sample-jms.xml到JBoss
         
    4) 使用编辑JBoss配置文件的方式创建消息队列
        
        a 打开JBOSS_HOME/standalone/configuration/standalone-full.xml文件
        
        b 在<subsystem xmlns="urn:jboss:domain:messaging中的</jms-connection-factories> 之后和</hornetq-server>之前添加如下内容：
        ```xml
           <jms-destinations>
                   <jms-queue name="TestQueue4">
                       <entry name="java:/jms/queue/TestQueue4"/>
                   </jms-queue>
           </jms-destinations>
        ```
        
        c 保存并关闭打开的文件
        
* Step 2 在JBoss服务器上创建安全用户

    1) 打开命令行终端根据自己操作系统执行创建用户启动脚本
    
    2) 创建Application User用户和密码
    
    3) 给创建的用户分配guest角色
    
* Step 3 使用创建好的用户名、密码和消息队列，运行项目

    