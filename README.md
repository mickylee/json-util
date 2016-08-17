# json-util
some ways to use json


	分别使用json-lib, org.json, fastjson, gson, jackson, jsonpath 构造或解析json
	
###	json-lib

	Json-lib可以将Java对象转成json格式的字符串，也可以将Java对象转换成xml格式的文档，
	同样可以将json字符串转换成Java对象或是将xml字符串转换成Java对象。
	
	需要以下类库支持:

    jakarta commons-lang
    jakarta commons-beanutils
    jakarta commons-collections
    jakarta commons-logging
    ezmorph

###	org.json

	org.json包是用来beans,collections,maps,java arrays 和XML和JSON互相转换的包
	org.json不能直接与bean进行转换，需要通过map进行中转
	
### fastjson
	
	Fastjson是一个Java语言编写的高性能功能完善的JSON库。
	fastjson采用独创的算法，将parse的速度提升到极致，超过所有json库，包括曾经号称最快的jackson。
	并且还超越了google的二进制协议protocol buf。Fastjson完全支持http://json.org的标准，也是官方网站收录的参考实现之一。
	支持各种JDK类型。包括基本类型、JavaBean、Collection、Map、Enum、泛型等。
	
	fastjson反射机制比gson更准确， 例如 id = 1001 通过fastjson反射仍是 id = 1001 , 而通过gson反射结果 为 id =1001.0 
	
	fastJson在复杂类型的Bean转换Json上会出现一些问题，可能会出现引用的类型，导致Json转换出错，需要制定引用。
	
	新版本fastJson还提供处理大文件的stream api,处理日期的api,定制序列化,jsonpath等功能。
	
### gson

	Gson是目前功能最全的Json解析神器，Gson的应用主要为toJson与fromJson两个转换函数，无依赖，不需要例外额外的jar，能够直接跑在JDK上。
	而在使用这种对象转换之前需先创建好对象的类型以及其成员才能成功的将JSON字符串成功转换成相对应的对象。
	类里面只要有get和set方法，Gson完全可以将复杂类型的json到bean或bean到json的转换。
	Gson在功能上面无可挑剔，但是性能上面比FastJson有所差距。
	
	如果只是功能要求，没有性能要求，可以使用google的Gson，如果有性能上面的要求,
	可以使用Gson将bean转换json确保数据的正确，使用FastJson将Json转换Bean。
	
###	jackson

	相比于其他的解析工具，Jackson 简单易用，不依赖于外部jar 包，而且更新速度比较快。
	其也是 SpringMVC 框架 json 格式化输出的默认实现。
	
	fasterxml 和 codehaus是 Jackson 的两大分支。
	从 2.0 版本开始，Jackson 开始改用新的包名 com.fasterxml.jackson；
	其源代码也托管到了 Github(FasterXML/Jackson)。
	1.x 版本现在只提供 bug-fix 。另外版本 1 和版本 2 的 artifactId 也不相同。
	
	Jackson对于复杂类型的json转换bean会出现问题，一些集合Map，List的转换出现问题。
	Jackson对于复杂类型的bean转换Json，转换的json格式不是标准的Json格式。

	Gson和Jackson的区别是：
	如果你的应用经常会处理大的JSON文件，那么Jackson应该是你的菜。GSON在大文件上表现得相当吃力。
	如果你主要是处理小文件请求，比如某个微服务或者分布式架构的初始化，那么GSON当是首选。
	Jackson在小文件上的表现则不如人意。

Jackson 主要有三部分组成，除了三个模块之间存在依赖，不依赖任何外部 jar 包。三个模块的 作用及 artifactId 如下：

    jackson-core: 核心包
    jackson-annotations : 注解包
    jackson-databind : 数据绑定（依赖 core 和 annotations）

使用方式

    Jackson 提供了三种 json 处理方式：

        Streaming API : 其他两种方式都依赖于它而实现，如果要从底层细粒度控制 json 的解析生成，可以使用这种方式;
        Tree Model : 通过基于内存的树形结构来描述 json 数据。json 结构树由 JsonNode 组成。不需要绑定任何类和实体，可以方便的对 JsonNode 来进行操作。
        Data Binding : 最常用的方式，基于属性的 get 和 set方法以及注解来实现 JavaBean 和 json 的互转，底层实现还是 Streaming API.
        
###	jsonpath

	JsonPath 对于 JSON 来说相当于 XPATH 对于 XML。
	这是一个简单的从文档中快速抽取指定信息的工具，提供多种语言实现版本，包括：Javascript, Java, Python 和 PHP。
	Json-path底层默认使用的就是JsonSmart。
	
	Operators
	---------
	
	| Operator                  | Description                                                        |
	| :------------------------ | :----------------------------------------------------------------- |
	| `$`                       | The root element to query. This starts all path expressions.       |
	| `@`                       | The current node being processed by a filter predicate.            |
	| `*`                       | Wildcard. Available anywhere a name or numeric are required.       |
	| `..`                      | Deep scan. Available anywhere a name is required.                  |
	| `.<name>`                 | Dot-notated child                                                  |
	| `['<name>' (, '<name>')]` | Bracket-notated child or children                                  |
	| `[<number> (, <number>)]` | Array index or indexes                                             |
	| `[start:end]`             | Array slice operator                                               |
	| `[?(<expression>)]`       | Filter expression. Expression must evaluate to a boolean value.    |
	
	
	Functions
	---------
	
	Functions can be invoked at the tail end of a path - the input to a function is the output of the path expression.
	The function output is dictated by the function itself.
	
	| Function                  | Description                                                        | Output    |
	| :------------------------ | :----------------------------------------------------------------- |-----------|
	| min()                    | Provides the min value of an array of numbers                       | Double    |
	| max()                    | Provides the max value of an array of numbers                       | Double    |
	| avg()                    | Provides the average value of an array of numbers                   | Double    |
	| stddev()                 | Provides the standard deviation value of an array of numbers        | Double    |
	| length()                 | Provides the length of an array                                     | Integer   |
	
	
	Filter Operators
	-----------------
	
	Filters are logical expressions used to filter arrays. A typical filter would be `[?(@.age > 18)]` where `@` represents the current item being processed. More complex filters can be created with logical operators `&&` and `||`. String literals must be enclosed by single or double quotes (`[?(@.color == 'blue')]` or `[?(@.color == "blue")]`).   
	
	| Operator                 | Description                                                       |
	| :----------------------- | :---------------------------------------------------------------- |
	| ==                       | left is equal to right (note that 1 is not equal to '1')          |
	| !=                       | left is not equal to right                                        |
	| <                        | left is less than right                                           |
	| <=                       | left is less or equal to right                                    |
	| >                        | left is greater than right                                        |
	| >=                       | left is greater than or equal to right                            |
	| =~                       | left matches regular expression  [?(@.name =~ /foo.*?/i)]         |
	| in                       | left exists in right [?(@.size in ['S', 'M'])]                    |
	| nin                      | left does not exists in right                                     |
	| size                     | size of left (array or string) should match right                 |
	| empty                    | left (array or string) should be empty                            |


	