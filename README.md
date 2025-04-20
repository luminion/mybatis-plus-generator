# mybatis-plus-generator
基于`mybatis-plus`的代码生成器, 提供了额外的功能
* 兼容`mybatis-plus代码生成器`的所有配置项目, 并提供更多额外的可选配置项
* 提供`新增DTO`,`修改DTO`,`导入DTO`,`导出DTO`,`出参VO`的额外生成
* 选择性生成`增删查改导入导出`相关方法及配套类 
* 支持`mybatis-plus-enhancer`的动态sql模式

基于`mybatis-plus`3.5.9及以上版本, 若出现运行时异常, 请检查`mybatis-plus`版本是否过低

## Maven依赖
```xml
<dependency>
    <groupId>io.github.bootystar</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>1.2.0</version>
</dependency>
```

### 如果需要使用Excel功能,请自行引入FastExcel依赖
```xml
<!-- (可选)Excel导入导出 -->
<dependency>
    <groupId>cn.idev.excel</groupId>
    <artifactId>FastExcel</artifactId>
    <version>1.1.0</version>
</dependency>
```

### 中央仓库地址
拉取SNAPSHOT或镜像仓库尚未同步的RELEASE版本时可配置在`pom.xml`文件中
```xml
<repositories>
    <repository>
        <id>snapshot</id>
        <name>snapshot</name>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
    <repository>
      <id>release</id>
      <name>release</name>
      <url>https://s01.oss.sonatype.org/content/repositories/releases/</url>
    </repository>
</repositories>
```
#### 若使用阿里云镜像拉取SNAPSHOT版本, 需在maven的`settings.xml`文件中配置`!snapshots`
```xml
<mirror>
  <id>aliyunmaven</id>
  <mirrorOf>*,!snapshots</mirrorOf>
  <name>aliyun</name>
  <url>https://maven.aliyun.com/repository/public</url>
</mirror>
```
## 代码生成器

### 代码生成(Lambda链式)
对`Lambda表达式`不熟悉的见[代码生成(编码式)](#代码生成编码式)

```java
String url = "jdbc:postgresql://localhost:5432/test?useUnicode=true&characterEncoding=UTF-8";
String username = "postgres";
String password = "root";
GeneratorHelper
//        .extraCodeGenerator(url, username, password) // 额外代码生成器
//        .dynamicSqlGenerator(url, username, password) // 动态SQL生成器
        .dynamicFieldGenerator(url, username, password) // 动态字段生成器
        .initialize() // 初始化常用配置
        .packageConfig(pkg -> pkg.parent("io.github.bootystar" ))// 父包名
//        .mapperXmlResource("static/mapper") // mapper.xml文件在Resources下的路径
        .execute("sys_user" )// 要生成的表(不输入为全部)
;
```

### 可选配置项
```java
String url = "jdbc:postgresql://localhost:5432/test?useUnicode=true&characterEncoding=UTF-8";
String username = "postgres";
String password = "root";
GeneratorHelper
        // .extraCodeGenerator(url, username, password) // 额外代码生成器
        // .dynamicSqlGenerator(url, username, password) // 动态SQL生成器
        .dynamicFieldGenerator(url, username, password) // 动态字段生成器
        .enableGlobalFileOverwrite() // 全局文件覆盖生成(覆盖所有的文件)
        .mapperXmlResource("static/mapper") // mapper.xml文件在Resources下的路径
        .initialize() // 初始化常用配置
        .customConfig(custom -> {
        // 自定义配置
            custom
                .enableJakartaApi() // 启用Jakarta API, springboot3及以上需要开启
                .enableFileOverride() // 文件覆盖生成(DTO、VO)
                .disableInsert() // 不生成新增
                .disableUpdate() // 不生成更新
                .disableDelete() // 不生成删除
                .disableSelect() // 不生成查询
                .disableImport() // 不生成导入
                .disableExport() // 不生成导出
                // 略... 更多可配置项见下文说明
        ;})
        .dataSourceConfig(dataSource -> {
        // 数据源配置(参考mybatis-plus官方文档)
        })
        .globalConfig(global -> {
        // 全局配置(参考mybatis-plus官方文档)
        })
        .packageConfig(pkg -> {
        // 包配置(参考mybatis-plus官方文档)
        })
        .strategyConfig(strategy -> {
        // 策略配置(参考mybatis-plus官方文档)
        })
        .execute("sys_user") // 要生成的表(不输入为全部)
        ;
```

### 自定义配置内容
* 通用配置项:[BaseBuilder.java](src/main/java/io/github/bootystar/mybatisplus/generator/config/builder/BaseBuilder.java)
* 特殊配置项:[BaseEnhanceBuilder.java](src/main/java/io/github/bootystar/mybatisplus/generator/config/builder/BaseEnhanceBuilder.java)
* 额外代码生成器特殊配置项:[ExtraCodeBuilder.java](src/main/java/io/github/bootystar/mybatisplus/generator/config/builder/ExtraCodeBuilder.java)
* 动态sql生成器特殊配置项:[DynamicSqlBuilder.java](src/main/java/io/github/bootystar/mybatisplus/generator/config/builder/DynamicSqlBuilder.java)
* 动态字段生成器特殊配置项:[DynamicFieldBuilder.java](src/main/java/io/github/bootystar/mybatisplus/generator/config/builder/DynamicFieldBuilder.java)

自定义配置示例:(或因版本不同或修改有所变动, 详见源码)
```java
custom
    // 文件相关
    .enableFileOverride() // 文件覆盖生成(DTO、VO)
    .disableInsert() // 不生成新增
    .disableUpdate() // 不生成更新
    .disableDelete() // 不生成删除
    .disableSelect() // 不生成查询
    .disableImport() // 不生成导入
    .disableExport() // 不生成导出
    .package4DTO("dto") // DTO的包名
    .path4DTO("C:/Project/test21/") // DTO的路径(全路径或相对路径)
    .package4VO("vo") // VO的包名
    .path4VO("C:/Project/test21/") // VO的路径(全路径或相对路径)
    .editExcludeColumns("create_time", "update_time") // 新增/修改时忽略的字段
    // controller额外生成项
    .baseUrl("/api") // 请求url前缀
    .enableCrossOrigins() // 启用跨域
    .enableJakartaApi() // 启用Jakarta API, springboot3以上需要开启
    .enableAutoWired() // 使用@Autowired替换@Resource
    .returnMethod(R1::new) // 返回值对象封装的方法
    .pageMethod(P1::of) // 分页对象封装的方法(需要接收IPage作为参数)
    .disableRestful() // 禁用restful
    .disableRequestBody() // 禁用请求体
    .disableValidated() // 禁用参数校验
    .disablePostQuery() // 复杂查询不使用post请求(使用get请求, 并关闭@RequestBody)
    // mapper额外生成项
    .sortColumnClear() // 清空排序
    .sortColumn("create_time",true) // 添加排序(字段,是否倒序)
    .sortColumn("id",true) // 添加排序(字段,是否倒序)
    // 特殊配置项, 因不同生成器而异
    .disableOverrideMethods() // 不生成重写的父类方法
    .withMapSelectDTO() // 使用Map作为查询入参DTO
    .withSqlHelperSelectDTO() // 使用SqlHelper作为查询入参DTO
    .extraFieldGenerateStrategy((sqlOperator,tableFiled)->{
            // 根据比较符号和字段信息决定是否生成额外字段
            return true;
        }) // 自定义额外字段策略(字段何时生成/不生成对应后缀的额外字段)
    .extraFieldSuffixBuilder(builder -> { 
    // 该项默认无需配置, 配置后, 只会根据已配置的字段生成额外后缀, 未配置的类型不会生成后缀
                    builder
                        .defaultSimpleSuffix() // 添加默认的后缀字符(默认生成LIKE,IN,<=,>=的特殊后缀)
                        .defaultCompleteSuffix() // 添加支持的所有后缀字符
                        .ne("Ne") // 不等于字段额外后缀
                        .lt("Lt") // 小于字段额外后缀
                        .le("Le") // 小于等于字段额外后缀
                        .ge("Ge") // 大于等于字段额外后缀
                        .gt("Gt") // 大于字段额外后缀
                        .like("Like") // 模糊匹配字段额外后缀
                        .notLike("NotLike") // 反模糊匹配字段额外后缀
                        .in("In") // 包含字段额外后缀
                        .notIn("NotIn") // 不包含字段额外后缀
                        .isNull("IsNull") // 空字段额外后缀
                        .isNotNull("IsNotNull") // 非空字段额外后缀
        ;})// 自定义字段额外后缀
```

### 代码生成(编码式)

```java
String url = "jdbc:postgresql://localhost:5432/test?useUnicode=true&characterEncoding=UTF-8";
String username = "postgres";
String password = "root";
//ExtraCodeGenerator generator = new ExtraCodeGenerator(url, username, password); // 额外代码生成器
//DynamicSqlGenerator generator = new DynamicSqlGenerator(url, username, password); // 动态SQL生成器
DynamicFieldGenerator generator = new DynamicFieldGenerator(url, username, password); // 动态字段生成器

generator.enableGlobalFileOverwrite() // 全局文件覆盖生成(覆盖所有的文件)
        .mapperXmlResource("static/mapper") // mapper.xml文件在Resources下的路径
        .initialize() // 初始化常用配置
;
generator.getCustomConfigBuilder() // 自定义配置
        .enableJakartaApi() // ...略
;
generator.getDataSourceConfigBuilder() // 数据源配置(参考mybatis-plus官方文档)
    //.driverClassName("org.postgresql.Driver")
;
generator.getGlobalConfigBuilder() // 全局配置(参考mybatis-plus官方文档)
    //.author("bootystar")
;
generator.getPackageConfigBuilder() // 包配置(参考mybatis-plus官方文档)
    //.parent("io.github.bootystar")
;
generator.getStrategyConfigBuilder() // 策略配置(参考mybatis-plus官方文档)
    //.addTablePrefix("sys_")
;
generator.execute("sys_user"); // 要生成的表(不输入为全部)
```

## 生成器

### 额外代码生成器`ExtraCodeGenerator`
* 兼容性最强
* 该生成器增强方式为`代码注入`, 可生成后直接复制代码到其他`mybatis-plus`项目使用
* 运行时除`mybatis-plus`外无其他依赖, 依赖耦合低
* 支持通过`属性`+`特殊后缀`的方式自动映射不同类型的查询
* `Service`和`ServiceImpl`内硬编码了`DTO`,`VO`
* `mapper.xml`内代码量较多
* 若生成后的实体数据库模型发生变化, 需要修改对应的`mapper.xml`和`SelectDTO`

### 动态sql生成器`DynamicSqlGenerator`
* 灵活性最强
* 默认使用`SqlHelper`入参, 支持`lambda`链式调用, `灵活`性极高
* 可动态映射`属性`和`值`为查询条件, 并支持嵌套子条件
* 可动态映射`排序属性`和`升降`序
* 可添加`非本表字段`的动态映射
* 支持直接使用`实体类`和`Map`入参,可根据入参动态映射
* 可自定义额外参数, 在`mapper.xml`中可直接使用
* `Service`继承实现, 无需实现, 无额外代码
* `ServiceImpl`继承实现, 无需实现, 无额外代码
* `mapper.xml`中内容`简洁`且`兼容性`强, 可无缝衔接自行编写的sql

### 动态字段生成器`DynamicFieldGenerator`
* 实用性最强
* 入参为`SqlHelper`时, 兼容`DynamicSqlGenerator`的动态映射功能
* 支持通过`属性`+`特殊后缀`的方式自动映射不同类型的查询
* 可自定义额外参数, 在`mapper.xml`中可直接使用
* `Service`继承实现, 无需实现, 无额外代码
* `ServiceImpl`继承实现, 无需实现, 无额外代码
* `mapper.xml`中内容`简洁`且`兼容性`强, 可无缝衔接自行编写的sql

