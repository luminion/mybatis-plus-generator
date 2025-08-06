# `StrategyConfig.java` 及相关配置文件修改计划

## 1. 目标

1.  为 `io.github.bootystar.mybatisplus.generator.config` 包下没有 `Builder` 的配置文件添加 `Builder` 模式。
2.  将 `src/main/java/io/github/bootystar/mybatisplus/generator/config/StrategyConfig.java` 中的 `builder` 和配置替换为当前项目中对应的配置和 `builder`。

## 2. 详细步骤

### 步骤 1: 为配置文件添加 Builder

我将为以下文件添加 `Builder` 内部类：

*   `ControllerConfig.java`
*   `EntityConfig.java`
*   `CustomFileConfig.java`
*   `DtoConfig.java`
*   `GlobalCustomConfig.java`
*   `MapperConfig.java`
*   `ServiceConfig.java`
*   `VoConfig.java`

**`ControllerConfig.java` 的 `Builder` 示例:**

```java
public static class Builder {
    private final ControllerConfig controllerConfig = new ControllerConfig();

    public Builder superClass(String superClass) {
        this.controllerConfig.superClass = superClass;
        return this;
    }

    public Builder restControllerStyle(boolean restControllerStyle) {
        this.controllerConfig.restControllerStyle = restControllerStyle;
        return this;
    }

    public Builder hyphenStyle(boolean hyphenStyle) {
        this.controllerConfig.hyphenStyle = hyphenStyle;
        return this;
    }

    public Builder controllerSuffix(String controllerSuffix) {
        this.controllerConfig.controllerSuffix = controllerSuffix;
        return this;
    }

    public ControllerConfig build() {
        return this.controllerConfig;
    }
}

public static Builder builder() {
    return new Builder();
}
```

**`EntityConfig.java` 的 `Builder` 示例:**

```java
public static class Builder {
    private final EntityConfig entityConfig = new EntityConfig();

    // ... (为所有字段添加 setter 方法)

    public EntityConfig build() {
        return this.entityConfig;
    }
}

public static Builder builder() {
    return new Builder();
}
```

### 步骤 2: 修改 `StrategyConfig.java`

1.  **修改导入语句:**
    *   删除 `com.baomidou.mybatisplus.generator.config.builder.*`
    *   添加 `io.github.bootystar.mybatisplus.generator.config.*`
    *   添加 `lombok.Getter`

2.  **修改成员变量:**
    *   将 `private final Entity.Builder entityBuilder;` 替换为 `private final EntityConfig entityConfig;`
    *   ... (以此类推)
    *   添加 `private final DtoConfig dtoConfig;`
    *   添加 `private final VoConfig voConfig;`

3.  **修改构造函数:**
    *   更新构造函数以匹配新的成员变量。

4.  **修改 `Builder` 类:**
    *   将 `private final Entity.Builder entityBuilder = new Entity.Builder();` 替换为 `private final EntityConfig.Builder entityBuilder = EntityConfig.builder();`
    *   ... (以此类推)
    *   添加 `private final DtoConfig.Builder dtoBuilder = DtoConfig.builder();`
    *   添加 `private final VoConfig.Builder voBuilder = VoConfig.builder();`
    *   更新 `entity()`、`controller()`、`mapper()` 和 `service()` 方法。
    *   添加 `dto()` 和 `vo()` 方法。
    *   更新 `build()` 方法。

5.  **添加 Getter:**
    *   在类上添加 `@Getter` 注解。

## 3. 下一步

如果您同意此计划，我将请求切换到 **Code** 模式来执行这些修改。