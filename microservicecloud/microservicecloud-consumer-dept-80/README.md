Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
 
简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，
将Netflix的中间层服务连接在一起。Ribbon客户端组件提供一系列完善的配置项如连接超时，
重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，
Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法。
 
LB，即负载均衡(Load Balance)，在微服务或分布式集群中经常用的一种应用。
负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA。
常见的负载均衡有软件Nginx，LVS，硬件 F5等。
相应的在中间件，例如：dubbo和SpringCloud中均给我们提供了负载均衡，SpringCloud的负载均衡算法可以自定义。 
 
Feign通过接口的方法调用Rest服务（之前是Ribbon+RestTemplate），
该请求发送给Eureka服务器（http://MICROSERVICECLOUD-DEPT/dept/list）,
通过Feign直接找到服务接口，由于在进行服务调用的时候融合了Ribbon技术，所以也支持负载均衡作用。_