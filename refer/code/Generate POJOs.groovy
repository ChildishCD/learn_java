import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * 尚马超市限定版
 */

packageName = ""
tableName = ""
typeMapping = [
        (~/(?i)bigint/)                          : "Long",
        (~/(?i)tinyint/)                         : "Byte",
        (~/(?i)smallint|int/)                    : "Integer",
        (~/(?i)bool|bit/)                        : "Boolean",
        (~/(?i)float|double|decimal|real/)       : "Double",
        (~/(?i)datetime|timestamp|date|time/)    : "LocalDateTime",
        (~/(?i)blob|binary|bfile|clob|raw|image/): "InputStream",
        (~/(?i)/)                                : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
  SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}
//C:\Users\xxx\IdeaProjects\demo\src\com\javasm\user
def getPackageName(dir) {

    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.)?", "") + ";"
}
def generate(table, dir) {
  def className = javaName(table.getName(), true)
    className += "Model"
  def fields = calcFields(table)
  packageName = getPackageName(dir)
    tableName = table.getName()
  new File(dir, className + ".java").withPrintWriter("UTF-8") { out -> generate(out, className, fields) }
}

def generate(out, className, fields) {
  out.println "package $packageName"
  out.println ""
  out.println ""
  out.println "import lombok.*;"
  out.println "import java.io.Serializable;;"
    Set types = new HashSet()
    fields.each() {
        types.add(it.type)
    }

    if (types.contains("Date")) {
        out.println "import java.time.LocalDateTime;"
    }

    if (types.contains("InputStream")) {
        out.println "import java.io.InputStream;"
    }
    out.println ""
    out.println "import com.javasm.annotation.*;"
    out.println ""
    out.println "@Getter"
    out.println "@Setter"
    out.println "@NoArgsConstructor"
    out.println "@AllArgsConstructor"
    out.println "@ToString"
    out.println "@Table(\"$tableName\")"
  out.println "public class $className implements Serializable{"
  out.println ""
  out.println genSerialID()
    out.println ""
    i=0
  fields.each() {
      if (isNotEmpty(it.comment)) {
          out.println "\t/**"
          out.println "\t * ${it.comment.toString().trim()}"
          out.println "\t */"
      }
    if (it.annos != "") out.println "\t${it.annos}"
      
    out.println "\tprivate ${it.type} ${it.name};"
      out.println ""
  }
  out.println ""
  /*fields.each() {
    out.println ""
    out.println "  public ${it.type} get${it.name.capitalize()}() {"
    out.println "    return ${it.name};"
    out.println "  }"
    out.println ""
    out.println "  public void set${it.name.capitalize()}(${it.type} ${it.name}) {"
    out.println "    this.${it.name} = ${it.name};"
    out.println "  }"
    out.println ""
  }*/
    out.println "}"
}

def calcFields(table) {
  DasUtil.getColumns(table).reduce([]) { fields, col ->

    def spec = Case.LOWER.apply(col.getDataType().getSpecification())
    def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
      def tmp = [
              name : javaName(col.getName(), false),
              type : typeStr,
              colname : col.getName(),
              comment : col.getComment(),
              annos: "@Column(\""+col.getName()+"\")"]
      if("id".equals(Case.LOWER.apply(col.getName())))
          tmp.annos +="\n\t@ID(\"id\")"
    fields += [tmp]
  }
}

def javaName(str, capitalize) {
  def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
    .collect { Case.LOWER.apply(it).capitalize() }
    .join("")
    .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
  capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
def isNotEmpty(content) {
    return content != null && content.toString().trim().length() > 0
}

static String genSerialID()
{
    return "\tprivate static final long serialVersionUID =  1L;"
}