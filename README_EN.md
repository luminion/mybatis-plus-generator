# mybatis-plus-generator

[![Maven Central](https://img.shields.io/maven-central/v/io.github.bootystar/mybatis-plus-generator)](https://mvnrepository.com/artifact/io.github.bootystar/mybatis-plus-generator)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

English | [中文](README.md)

MyBatis-Plus code generator, which provides richer functions and configurable items than the official code generator, aims to improve development efficiency and reduce repetitive code writing.

## Features

- **Basic code generation**: Generate basic code such as entity classes, Mapper, Service, Controller, etc.
- **Domain model generation**: Support the generation of domain models such as new DTO, modified DTO, query DTO, query VO, etc.
- **Selective method generation**: Support selective generation of methods such as add, delete, check, import and export
- **Dynamic SQL support**: Support dynamic SQL input parameter mapping query
- **Field suffix mapping**: Support custom field suffix mapping query
- **Multiple database support**: Support mainstream databases such as MySQL, PostgreSQL, Oracle, etc.
- **Template engine support**: Use Velocity template engine, support custom templates
- **Flexible configuration**: Provide a chain call configuration method, the configuration is simple and easy to use

## Repository address

- GitHub: https://github.com/bootystar/mybatis-plus-generator
- Maven Central: https://central.sonatype.com/artifact/io.github.bootystar/mybatis-plus-generator

## Maven dependency

The current latest version is:
[![Maven Central](https://img.shields.io/maven-central/v/io.github.bootystar/mybatis-plus-generator)](https://mvnrepository.com/artifact/io.github.bootystar/mybatis-plus-generator)

```xml
<dependency>
    <groupId>io.github.bootystar</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>latest</version>
</dependency>
```

## Quick start

Use [FastGenerator](src/main/java/io/github/bootystar/mybatisplus/generator/FastGenerator.java) to quickly generate code:

```java
public static void main(String[] args) {
    FastGenerator.create("jdbc:mysql://localhost:3306/your_database", "username", "password")
            .initialize() // One-click initialization of commonly used configuration items
            .execute("user","role") // Specify the table name to be generated
    ;
}
```

## Generated file description

The generator will generate the following types of files:

1. **Entity** - Entity class corresponding to the database table
2. **Mapper** - MyBatis Mapper interface
3. **Mapper XML** - MyBatis XML mapping file
4. **Service** - Service interface
5. **ServiceImpl** - Service implementation class
6. **Controller** - Controller class
7. **InsertDTO** - Insert data transfer object
8. **UpdateDTO** - Update data transfer object
9. **QueryDTO** - Query data transfer object
10. **VO** - View object

## Custom configuration example

```java
public static void main(String[] args) {
    FastGenerator.create("jdbc:mysql://localhost:3306/your_database", "username", "password")
            // Data source configuration
            .datasource(e -> e
                    .schema("your_schema")
            )
            // Global configuration
            .global(e -> e
                    .author("your_name")
                    .enableLombok()
                    .enableSwagger()
            )
            // Output configuration
            .output(e -> e
                    .outputDir("D:/project/src/main/java") // Specify the file output directory
                    .parentPackage("com.example.project") // Parent package name
                    .moduleName("system") // Module name
                    .insertDTO(f -> f
                            .formatPattern("%sInsertDTO") // Format name
                            .subPackage("dto") // Specify the subpackage where the file is located
                            .templatePath("/templates/insertDTO.java") // Specify the template path
                            .outputDir("D:/project/src/main/java") // Separately specify the file output directory
                            .disable() // Disable generation
                    )
                    .updateDTO(f -> f.formatPattern("%sUpdateDTO"))
                    .queryDTO(f -> f.formatPattern("%sQueryDTO"))
                    .queryVO(f -> f.formatPattern("%sVO"))
            )
            // Strategy configuration
            .strategy(e -> e
                    .enableCapitalMode()
                    .enableSkipView()
                    .disableSqlFilter()
                    .addInclude("table1", "table2") // Specify the table names that need to be generated
                    .addExclude("test") // Exclude the generated table names
                    .addTablePrefix("t_", "c_") // Add table prefix
                    .addFieldPrefix("is_", "has_") // Add field prefix
            )
            // Entity configuration
            .entity(e -> e
                    .enableTableFieldAnnotation()
                    .idType(IdType.AUTO)
                    .naming(NamingStrategy.underline_to_camel)
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .logicDeleteColumnName("deleted")
                    .versionColumnName("version")
            )
            // Mapper configuration
            .mapper(e -> e
                    .enableBaseResultMap()
                    .enableBaseColumnList()
                    .sortColumn("create_time", true)
                    .sortColumn("id", true)
            )
            // Service configuration
            .service(e -> e
                    .superServiceClass("com.baomidou.mybatisplus.extension.service.IService")
                    .superServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
            )
            // Controller configuration
            .controller(e -> e
                    .enableCrossOrigin()
            )
            // Model configuration
            .model(e -> e
                    .enableQueryDTOExtendsEntity()
            )
            // Specify the table name to be generated
            .execute("user","role");
    ;
}
```

## Configuration instructions

### Global Configuration (GlobalConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| author | String | Set the code author name |
| dateType | DateType | Set the time type strategy |
| commentDate | String | Specify the comment date formatting mode |
| enableLombok | - | Enable lombok model |
| enableChainModel | - | Enable chain getter and setter |
| enableCommentLink | - | Document comments add related class links |
| enableCommentUUID | - | Enable random UUID for class comments |
| disableValidated | - | Disable input parameter validation for new additions and modifications |
| enableSwagger | - | Use swagger documentation |
| enableSpringdoc | - | Use springdoc documentation |
| enableJavaxApi | - | Use javax package as javaEE api |
| enableEasyExcel | - | Use EasyExcel (default is FastExcel) |
| enableMybatisPlusEnhancer | - | Use mybatis-plus-enhancer (requires self-introduction of dependencies) |
| disableQuery | - | Do not generate query methods |
| disableInsert | - | Do not generate new methods |
| disableUpdate | - | Do not generate update methods |
| disableDelete | - | Do not generate delete methods |
| disableImport | - | Do not generate import methods |
| disableExport | - | Do not generate export methods |

### Output Configuration (OutputConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| outputDir | String | Set the file output directory |
| parentPackage | String | Set the parent package name |
| moduleName | String | Set the module name |
| enableGlobalFileOverride | - | Enable global file override |
| disableOpenOutputDir | - | Disable opening the output directory |
| entity | Function<TemplateFile.Adapter, TemplateFile.Adapter> | Entity class configuration |
| mapper | Function<TemplateFile.Adapter, TemplateFile.Adapter> | mapper configuration |
| mapperXml | Function<TemplateFile.Adapter, TemplateFile.Adapter> | mapperXml configuration |
| service | Function<TemplateFile.Adapter, TemplateFile.Adapter> | service configuration |
| serviceImpl | Function<TemplateFile.Adapter, TemplateFile.Adapter> | serviceImpl configuration |
| controller | Function<TemplateFile.Adapter, TemplateFile.Adapter> | controller configuration |
| insertDTO | Function<TemplateFile.Adapter, TemplateFile.Adapter> | insertDTO configuration |
| updateDTO | Function<TemplateFile.Adapter, TemplateFile.Adapter> | updateDTO configuration |
| queryDTO | Function<TemplateFile.Adapter, TemplateFile.Adapter> | queryDTO configuration |
| queryVO | Function<TemplateFile.Adapter, TemplateFile.Adapter> | vo configuration |

### Strategy Configuration (StrategyConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| addTablePrefix | String... | Add filter table prefix |
| addTableSuffix | String... | Add filter table suffix |
| addFieldPrefix | String... | Add filter field prefix |
| addFieldSuffix | String... | Add filter field suffix |
| addInclude | String... | Add included table names |
| addExclude | String... | Add excluded tables |
| likeTable | LikeTable | Included table names |
| notLikeTable | LikeTable | Not included table names |
| extraFieldSuffix | String, String | Extra field suffix |
| clearExtraFieldSuffix | - | Clear extra field suffix |
| extraFieldStrategy | BiFunction<String, TableField, Boolean> | Extra field strategy |
| enableCapitalMode | - | Enable uppercase naming |
| enableSkipView | - | Enable skip view |
| enableSchema | - | Enable schema |
| disableSqlFilter | - | Disable sql filtering |

### Entity Configuration (EntityConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| superClass | Class<?> or String | Custom inherited Entity class |
| versionColumnName | String | Set the optimistic lock database table field name |
| versionPropertyName | String | Set the optimistic lock entity attribute field name |
| logicDeleteColumnName | String | Logical deletion of database field name |
| logicDeletePropertyName | String | Logical deletion of entity attribute name |
| naming | NamingStrategy | Naming strategy for mapping database tables to entities |
| columnNaming | NamingStrategy | Naming strategy for mapping database table fields to entities |
| addSuperEntityColumns | String... | Add parent class public fields |
| addIgnoreColumns | String... | Add ignored fields |
| addTableFills | IFill... | Add table field filling |
| idType | IdType | Specify the ID type of the generated primary key |
| enableSerialAnnotation | - | Enable generation of @Serial |
| enableColumnConstant | - | Enable generation of field constants |

| enableRemoveIsPrefix | - | Enable removal of is prefix for Boolean type fields |
| enableTableFieldAnnotation | - | Enable generation of field annotations when generating entities |
| enableActiveRecord | - | Enable ActiveRecord mode |
| disableSerialVersionUID | - | Disable generation of serialVersionUID |

### Mapper Configuration (MapperConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| superClass | String or Class<?> | Parent class Mapper |
| mapperAnnotation | Class<? extends Annotation> | Mark MapperConfig annotation |
| enableBaseResultMap | - | Enable baseResultMap |
| enableBaseColumnList | - | Enable baseColumnList |
| cache | Class<? extends Cache> | Set the cache implementation class |
| clearSortColumnMap | - | Clear the sort fields |
| sortColumn | String, boolean | Add sort field |

### Service Configuration (ServiceConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| superServiceClass | Class<?> or String | Service interface parent class |
| superServiceImplClass | Class<?> or String | Service implementation class parent class |

### Controller Configuration (ControllerConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| superClass | Class<?> or String | Parent class controller |
| disableRestController | - | Disable @RestController controller |
| disableHyphenStyle | - | Disable camel case to hyphen |
| baseUrl | String | controller request prefix |
| enableCrossOrigin | - | Cross-origin annotation |
| disableBatchQueryPost | - | Prohibit batch data query using post request |
| enableRestful | - | Use restful style for adding, deleting, checking and modifying |
| disablePathVariable | - | Disable path variables |
| disableRequestBody | - | Disable message body to receive data |
| returnMethod | SFunction<Object, R> | Specify the return result packaging class and method of the controller |
| pageMethod | SFunction<IPage<O>, R> | Specify the paging packaging class and method returned by the controller |
| queryParam | Class<?> | Specify the class used for query |

### Model Configuration (ModelConfig)

| Configuration method | Parameter type | Detailed description |
|---|---|---|
| enableQueryDTOExtendsEntity | - | Query dto inherits entity class |
| enableQueryVOExtendsEntity | - | Query vo inherits entity class |

## Precautions

1. After enabling file overwrite, the generated code will overwrite files with the same name, please pay attention to backing up important files
2. Need to ensure that the database connection information is correct
3. Adjust configuration parameters according to actual needs
4. Special needs can be met by customizing the template

## Version dependencies
The recommended dependency versions are as follows:

| Generator version | mybatis-plus | mybatis-plus-enhancer | Description |
|---|---|---|---|
| 0.0.3 - 0.0.9 | 3.5.3.2 | - | Experimental version, not recommended for use |
| 1.0.0 - 1.0.1 | 3.5.3.2 - 3.5.5 | - | First version<br/>Depends on the official mybatis-plus generator |
| 1.2.0 - 1.2.7 | 3.5.7 - 3.5.11 | 1.2.0 - 1.2.4 | A version that aggregates the functions of mybatis-plus-enhancer (1.2.0 - 1.2.4)<br/>Depends on the official mybatis-plus generator and mybatis-plus-enhancer |
| 2.0.0 | 3.5.0 or above | 2.0.0 (optional) | 2.0 refactored version, runs independently, removes all unnecessary dependencies<br/>Compatible with mybatis-plus: 3.5.0 and above<br/>Aggregates mybatis-plus-enhancer: 2.0.0 version functions |