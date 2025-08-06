package io.github.bootystar.mybatisplus.generator.config.specific;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

/**
 * 数据源配置
 *
 * @author bootystar
 */
@Data
@Accessors(chain = true)
public class DataSourceConfig {

    private DataSource dataSource;

    private String driverName;

    private String url;

    private String username;

    private String password;

    private String schemaName;

    private String[] includeTables;

    private String[] excludeTables;

    private String tablePrefix;

    private String fieldPrefix;

    public DataSourceConfig() {
    }

    public DataSourceConfig(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public DataSource getDataSource() throws SQLException {
        if (dataSource == null) {
            dataSource = new com.baomidou.mybatisplus.extension.ddl.history.DataSourceCreator()
                    .createDataSource(url, driverName, username, password);
        }
        return dataSource;
    }

    public Optional<String> getDriverName() {
        return Optional.ofNullable(driverName);
    }

    public Optional<String> getSchemaName() {
        return Optional.ofNullable(schemaName);
    }

    public Optional<String[]> getIncludeTables() {
        return Optional.ofNullable(includeTables);
    }

    public Optional<String[]> getExcludeTables() {
        return Optional.ofNullable(excludeTables);
    }

    public Optional<String> getTablePrefix() {
        return Optional.ofNullable(tablePrefix);
    }

    public Optional<String> getFieldPrefix() {
        return Optional.ofNullable(fieldPrefix);
    }
}