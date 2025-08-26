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
                                .enableSwagger()
                                .enableSpringdoc()
                                .enableCommentLink()
                                .enableCommentUUID()
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
//                        .disableOpenOutputDir()
                        .parentPackage("com.example.test")
                        .createDTOPackage("entity")
                        .updateDTOPackage("entity")
                        .queryDTOPackage("entity")
                        .queryVOPackage("entity")
                        .importDTOPackage("entity")
                        .importDTODisableGenerate()
                        .exportVOPackage("entity")
                        .exportVODisableGenerate()
                )
                .strategy(e -> e
                )
                .entity(e -> e
//                        .disable()
                                .logicDeleteColumnName("deleted")
                                .versionColumnName("version")
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