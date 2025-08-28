package io.github.bootystar.mybatisplus.generator;

import io.github.bootystar.mybatisplus.generator.config.rules.DateType;

/**
 * @author booty
 */
public class Generator4Mysql {


    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        FastGenerator
                .create(url, username, password)
                .initialize()
                .global(e -> e

                                .author("bootystar")
                                .dateType(DateType.SQL_PACK)
                                .commentDate("yyyy-MM-dd HH:mm:ss")
                                .enableLombok()
                                .enableChainModel()
//                                .enableSwagger()
//                                .enableSpringdoc()
//                                .enableCommentLink()
//                                .enableCommentUUID()
                                .enableJavaxApi()
                                .enableEasyExcel()
//                                .disableQuery()
//                                .disableInsert()
//                                .disableUpdate()
//                                .disableImport()
//                                .disableExport()

                )
                .output(e -> e
                        .outputDir(System.getProperty("user.dir") + "/src/test/java")
                        .enableGlobalFileOverride()
                        .parentPackage("com.example.test")
                        .disableOpenOutputDir()
                        .entity(f -> f
                                .formatPattern("%sPOJO")
                        )
                        .mapper(f -> f
                                .subPackage("mapper")
                                .disable()
                        )
                        .mapperXml(f -> f
                                .subPackage("mapper.xml")
                                .disable()
                        )
                        .service(f -> f
                                .subPackage("service")
                                .disable()
                        )
                        .serviceImpl(f -> f
                                .subPackage("service.impl")
                                .disable()
                        )
                        .controller(f -> f
                                .subPackage("controller")
                                .disable()
                        )
                        .createDTO(f -> f
                                .subPackage("dto")
//                                .disable()
                        )
                        .updateDTO(f -> f
                                .subPackage("dto")
//                                .disable()
                        )
                        .queryDTO(f -> f
                                .subPackage("dto")
//                                .disable()
                        )
                        .queryVO(f -> f
                                .subPackage("vo")
//                                .disable()
                        )
                )
                .model(e -> e
                                .enableQueryDTOExtendsEntity()
                        .enableQueryVOExtendsEntity()

                )
                .strategy(e -> e
                        .extraFieldSuffix("In", "IN")
                        .extraFieldSuffix("NotIn", "NOT IN")
                        .extraFieldSuffix("Like", "LIKE")
                        .extraFieldSuffix("NotLike", "NOT LIKE")
                        .extraFieldSuffix("IsNull", "IS NULL")
                        .extraFieldSuffix("IsNotNull", "IS NOT NULL")
                        .extraFieldSuffix("Ne", "!=")
                        .extraFieldSuffix("Lt", "<")
                        .extraFieldSuffix("Le", "<=")
                        .extraFieldSuffix("Ge", ">=")
                        .extraFieldSuffix("Gt", ">")
                        .extraFieldSuffix("WithBit", "&>")
                        .extraFieldSuffix("WithoutBit", "&=")

                )
                .entity(e -> e
//                        .disable()
                                .logicDeleteColumnName("deleted")
                                .versionColumnName("version")
                                .enableActiveRecord()
                )
                .mapper(e -> e

                )
                .service(e -> e
                )
                .controller(e -> e
                )

                .execute("sys_user")
        ;
    }


}