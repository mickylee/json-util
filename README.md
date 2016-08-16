# json-util
some ways to use json


	分别使用	json-lib, org.json, fastjson, gson, jackson, jsonpath 构造和解析json

###	org.json

	org.json包是用来beans,collections,maps,java arrays 和XML和JSON互相转换的包
	org.json不能直接与bean进行转换，需要通过map进行中转
	
###	jackson

	相比于其他的解析工具，Jackson 简单易用，不依赖于外部jar 包，而且更新速度比较快。
	其也是 SpringMVC 框架 json 格式化输出的默认实现。
	
	fasterxml 和 codehaus是 Jackson 的两大分支。
	从 2.0 版本开始，Jackson 开始改用新的包名 com.fasterxml.jackson；
	其源代码也托管到了 Github(FasterXML/Jackson)。
	1.x 版本现在只提供 bug-fix 。另外版本 1 和版本 2 的 artifactId 也不相同。

Jackson 主要有三部分组成，除了三个模块之间存在依赖，不依赖任何外部 jar 包。三个模块的 作用及 artifactId 如下：

    jackson-core: 核心包
    jackson-annotations : 注解包
    jackson-databind : 数据绑定（依赖 core 和 annotations）

使用方式

    Jackson 提供了三种 json 处理方式：

        Streaming API : 其他两种方式都依赖于它而实现，如果要从底层细粒度控制 json 的解析生成，可以使用这种方式;
        Tree Model : 通过基于内存的树形结构来描述 json 数据。json 结构树由 JsonNode 组成。不需要绑定任何类和实体，可以方便的对 JsonNode 来进行操作。
        Data Binding : 最常用的方式，基于属性的 get 和 set方法以及注解来实现 JavaBean 和 json 的互转，底层实现还是 Streaming API.