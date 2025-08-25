package io.github.bootystar.mybatisplus.generator.config.po;

/**
 * 
 * @author bootystar
 */
public class TemplateFile {
    public String formatPattern;
    public String packageName;
    public String templatePath;
    public boolean fileOverride;

    public TemplateFile(String formatPattern, String packageName, String templatePath) {
        this.formatPattern = formatPattern;
        this.packageName = packageName;
        this.templatePath = templatePath;
    }

    public String className(String entityName) {
        return String.format(formatPattern, entityName);
    }

    public String packageName(String parentPackage){
        if (parentPackage == null) {
            return packageName;
        }
        return parentPackage + "." + packageName;
    }

    public String outputPath(String parentPath){
        return parentPath + "/" + packageName.replace(".", "/");
    }
}
