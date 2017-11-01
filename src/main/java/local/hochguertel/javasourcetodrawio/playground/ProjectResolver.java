package local.hochguertel.javasourcetodrawio.playground;

import java.io.File;
import java.io.IOException;
import com.github.javaparser.ParseException;
import com.github.javaparser.symbolsolver.SourceFileInfoExtractor;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

/**
 * ProjectResolver
 * <p>
 * Created on 01.11.17
 * <p>
 */
public class ProjectResolver {

    public static void main(String[] args) throws IOException, ParseException {
        final String testCase = "/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/TestCases/uml-parser-test-1";
        File src = new File(testCase);
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver());
        combinedTypeSolver.add(new JavaParserTypeSolver(src));
        combinedTypeSolver.add(new JavaParserTypeSolver(new File(testCase + "/generated")));

        String jars = "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/charsets.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/deploy.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/cldrdata.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/dnsns.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/jaccess.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/jfxrt.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/localedata.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/nashorn.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/sunec.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/ext/zipfs.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/javaws.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/jce.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/jfr.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/jfxswt.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/jsse.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/management-agent.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/plugin.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/resources.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/rt.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/ant-javafx.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/dt.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/javafx-mx.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/jconsole.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/packager.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/sa-jdi.jar\n"
                + "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/tools.jar\n"
                + "/Users/hochguertelto/.m2/repository/com/github/javaparser/javaparser-core/3.5.0/javaparser-core-3.5.0.jar\n"
                + "/Users/hochguertelto/.m2/repository/ch/qos/logback/logback-classic/1.0.13/logback-classic-1.0.13.jar\n"
                + "/Users/hochguertelto/.m2/repository/ch/qos/logback/logback-core/1.0.13/logback-core-1.0.13.jar\n"
                + "/Users/hochguertelto/.m2/repository/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar\n"
                + "/Users/hochguertelto/.m2/repository/com/github/javaparser/java-symbol-solver-core/0.6.0/java-symbol-solver-core-0.6.0.jar\n"
                + "/Users/hochguertelto/.m2/repository/com/github/javaparser/java-symbol-solver-logic/0.6.0/java-symbol-solver-logic-0.6.0.jar\n"
                + "/Users/hochguertelto/.m2/repository/com/javaslang/javaslang/2.0.0-beta/javaslang-2.0.0-beta.jar\n"
                + "/Users/hochguertelto/.m2/repository/io/javaslang/javaslang/2.0.3/javaslang-2.0.3.jar\n"
                + "/Users/hochguertelto/.m2/repository/io/javaslang/javaslang-match/2.0.3/javaslang-match-2.0.3.jar\n"
                + "/Users/hochguertelto/.m2/repository/com/github/javaparser/java-symbol-solver-model/0.6.0/java-symbol-solver-model-0.6.0.jar\n"
                + "/Users/hochguertelto/.m2/repository/org/javassist/javassist/3.19.0-GA/javassist-3.19.0-GA.jar\n"
                + "/Users/hochguertelto/.m2/repository/com/google/guava/guava/21.0/guava-21.0.jar\n"
                + "/Users/hochguertelto/.m2/repository/com/mxgraph/mxgraph-core/3.7.5/mxgraph-core-3.7.5.jar\n"
                + "/Users/hochguertelto/.m2/repository/commons-cli/commons-cli/1.4/commons-cli-1.4.jar\n"
                + "/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar\n";

/*
        final List<String> listOfJars = Arrays.asList(jars.split("\n"));
        listOfJars.forEach(jar -> {
            try {
                combinedTypeSolver.add(new JarTypeSolver(jar));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
*/

        SourceFileInfoExtractor sourceFileInfoExtractor = new SourceFileInfoExtractor();
        sourceFileInfoExtractor.setVerbose(true);
        sourceFileInfoExtractor.setPrintFileName(true);
        sourceFileInfoExtractor.setOut(System.out);
        sourceFileInfoExtractor.setTypeSolver(combinedTypeSolver);
        sourceFileInfoExtractor.solve(src);
        System.out.println("OK " + sourceFileInfoExtractor.getOk());
        System.out.println("KO " + sourceFileInfoExtractor.getKo());
        System.out.println("UNSUPPORTED " + sourceFileInfoExtractor.getUnsupported());
        sourceFileInfoExtractor.solveMethodCalls(src);
    }

}
